package com.ync365.px.service.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ync365.px.entity.PxProject;
import com.ync365.px.entity.PxProjectStudent;
import com.ync365.px.model.ProjectStudentBo;
import com.ync365.px.observer.BookingObservable;
import com.ync365.px.repository.PxProjectStudentDao;
import com.ync365.px.service.account.ShiroDbRealm.ShiroUser;
import com.ync365.px.shiro.util.ShiroUserUtils;
import com.ync365.px.utils.JacksonMapperUtil;
import com.ync365.px.utils.redis.RedisShardClient;

// Spring Bean的标识.
@Component
public class BookingService {

    @Autowired
    private BookingObservable bookingObservable;

    @Autowired
    private PxProjectStudentDao pxProjectStudentDao;

    private RedisShardClient redisShardClient;

    private Integer userKeyExpireTime = 60 * 60 * 24 * 30;//一个月

    @Autowired
    public void setRedisShardClient(RedisShardClient redisShardClient) {
        this.redisShardClient = redisShardClient;
    }

    public boolean booking(int projectId) {
        String json = redisShardClient.get("PROJECT:" + projectId);
        PxProject pxProject = JacksonMapperUtil.jsonToBean(json, PxProject.class);
        //确保后台操作暂停时，前台页面长时间等待未刷新，仍可抢票的状态
        if (pxProject.getStatus() == 2) {
            ShiroUser user = ShiroUserUtils.getCurrentUser();
            String ticketId = redisShardClient.lpop("MQ:PROJECT:" + projectId);
            if (ticketId != null) {
                final ProjectStudentBo projectStudentBo = new ProjectStudentBo();
                projectStudentBo.setUserId(user.id.intValue());
                projectStudentBo.setProjectId(projectId);
                projectStudentBo.setUserName(user.name);
                //插入集合，用于查询，防止一人抢多张票
                Long re = redisShardClient.sadd("COLLECTION:PROJECT:" + projectId, user.id.toString());
                //防止同一个账号多客户端登录造成队列pop成功，而集合因重复插入失败，导致少卖
                if (re.equals(new Long(0))) {
                    //如发生上面情况，则把该票送回到队尾
                    redisShardClient.rpush("MQ:PROJECT:" + projectId, ticketId);
                    redisShardClient.expire("MQ:PROJECT:" + projectId, userKeyExpireTime);
                } else {
                    //更新计数器
                    redisShardClient.decr("NUM:PROJECT:" + projectId);
                }
                //观察者，异步处理redis队列中的数据到数据库
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        bookingObservable.notify(projectStudentBo);
                    }
                });
                thread.start();
                return true;
            }
        }
        return false;
    }

    public boolean isTaked(Long projectId) {
        ShiroUser user = ShiroUserUtils.getCurrentUser();
        return redisShardClient.sismember("COLLECTION:PROJECT:" + projectId, user.id.toString());
    }

    /**
     * 课程状态改变时调用，负责将project信息同步到redis
     */
    public boolean syncProjectinfo2redis(PxProject pxProject) {
        List<PxProjectStudent> pxProjectStudentList = pxProjectStudentDao.findByProjectId(pxProject.getId().intValue());
        String[] userIdarray = new String[pxProjectStudentList.size()];
        //num为剩余票数，存在redis中
        int num = pxProject.getTicketNum() - pxProjectStudentList.size();
        String[] array = new String[num];
        for (int i = 0; i < num; i++) {
            array[i] = String.valueOf(i);
        }
        redisShardClient.del("NUM:PROJECT:" + pxProject.getId());
        redisShardClient.set("NUM:PROJECT:" + pxProject.getId(), String.valueOf(num));
        redisShardClient.expire("NUM:PROJECT:" + pxProject.getId(), userKeyExpireTime);
        redisShardClient.del("MQ:PROJECT:" + pxProject.getId());
        redisShardClient.rpush("MQ:PROJECT:" + pxProject.getId(), array);
        redisShardClient.expire("MQ:PROJECT:" + pxProject.getId(), userKeyExpireTime);
        redisShardClient.del("PROJECT:" + pxProject.getId());
        redisShardClient.set("PROJECT:" + pxProject.getId(), JacksonMapperUtil.beanToJson(pxProject));
        redisShardClient.expire("PROJECT:" + pxProject.getId(), userKeyExpireTime);

        if(pxProjectStudentList.size()!=0){
            //初始化redis集合，如果数据库中无学生则不用初始化
            for (int i = 0; i < pxProjectStudentList.size(); i++) {
                userIdarray[i] = String.valueOf(pxProjectStudentList.get(i).getUserId());
            }
            redisShardClient.del("COLLECTION:PROJECT:" + pxProject.getId());
            redisShardClient.sadd("COLLECTION:PROJECT:" + pxProject.getId(), userIdarray);
            redisShardClient.expire("COLLECTION:PROJECT:" + pxProject.getId(), userKeyExpireTime);
        }
        return true;
    }
    
    /**
     * 删除课程时调用，删除信息同步到redis
     */
    public boolean syncProjectDel2redis(Long projectId) {
        redisShardClient.del("NUM:PROJECT:" + projectId);
        redisShardClient.del("MQ:PROJECT:" + projectId);
        redisShardClient.del("PROJECT:" + projectId);
        redisShardClient.del("COLLECTION:PROJECT:" + projectId);
        return true;
    }

    /**
     * 通过项目id从redis中获取项目信息（待抢票项目）
     */
    public PxProject getProjectFormRedis(Long projectId) {
        String json = redisShardClient.get("PROJECT:" + projectId);
        return JacksonMapperUtil.jsonToBean(json, PxProject.class);
    }
    
    /**
     * 通过项目id从redis中获取项目余票数量
     */
    public String getHaveTicketNum(Long projectId) {
        return redisShardClient.get("NUM:PROJECT:"+projectId);
    }
}
