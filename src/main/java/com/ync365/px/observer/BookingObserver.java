package com.ync365.px.observer;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.px.entity.PxClass;
import com.ync365.px.entity.PxClassStudent;
import com.ync365.px.entity.PxProjectStudent;
import com.ync365.px.model.ProjectStudentBo;
import com.ync365.px.repository.PxClassDao;
import com.ync365.px.repository.PxClassStudentDao;
import com.ync365.px.repository.PxProjectStudentDao;
public class BookingObserver implements Observer {
    @Autowired(required=false)
    private PxProjectStudentDao pxProjectStudentDao;
    
    @Autowired(required=false)
    private PxClassStudentDao pxClassStudentDao;
    
    @Autowired
    private PxClassDao pxClassDao;
    
    private static Logger logger = LoggerFactory.getLogger(BookingObserver.class);
    
    @Override
    @Transactional
    public void update(Observable o, Object arg) {
        logger.info("抢票入库开始，messageId："+arg.toString());
        ProjectStudentBo projectStudentBo = (ProjectStudentBo)arg;
        PxProjectStudent pxProjectStudent = new PxProjectStudent();
        pxProjectStudent.setProjectId(projectStudentBo.getProjectId());
        pxProjectStudent.setUserId(projectStudentBo.getUserId());
        pxProjectStudent.setUserName(projectStudentBo.getUserName());
        pxProjectStudent.setCreateTime(new Date());
        pxProjectStudentDao.save(pxProjectStudent);
        List<PxClass> pxClassList = pxClassDao.findByProjectId(projectStudentBo.getProjectId());
        for(int i = 0;i<pxClassList.size();i++){
            PxClassStudent pxClassStudent = new PxClassStudent();
            pxClassStudent.setClassId(pxClassList.get(i).getId().intValue());
            pxClassStudent.setCome(new Integer(1));
            pxClassStudent.setMark("暂无");
            pxClassStudent.setReals(pxClassList.get(i).getClassHour());
            pxClassStudent.setS_score(pxClassList.get(i).getStudentScore());
            pxClassStudent.setScore(pxClassList.get(i).getStudentScore());
            pxClassStudent.setShould(pxClassList.get(i).getClassHour());
            pxClassStudent.setUser_id(projectStudentBo.getUserId());
            pxClassStudent.setUser_name(projectStudentBo.getUserName());
            pxClassStudentDao.save(pxClassStudent);
        }
        logger.info("抢票入库结束，messageId："+arg.toString());
    }

}
