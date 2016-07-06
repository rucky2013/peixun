package com.ync365.px.service.projectstudent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.px.entity.PxClass;
import com.ync365.px.entity.PxClassStudent;
import com.ync365.px.entity.PxProject;
import com.ync365.px.entity.PxProjectStudent;
import com.ync365.px.entity.User;
import com.ync365.px.model.ProjectStudentBo;
import com.ync365.px.repository.PxClassDao;
import com.ync365.px.repository.PxClassStudentDao;
import com.ync365.px.repository.PxProjectDao;
import com.ync365.px.repository.PxProjectStudentDao;
import com.ync365.px.repositoryimpl.UserDaoImpl;

//Spring Bean的标识.
@Component
//类中所有public函数都纳入事务管理的标识.
@Transactional
public class ProjectStudentService {

    @Autowired
    private PxProjectStudentDao pxProjectStudentDao;

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private PxProjectDao pxProjectDao;
    
    @Autowired
    private PxClassDao pxClassDao;
    
    @Autowired
    private PxClassStudentDao pxClassStudentDao;

    public void insertClassStudents(ProjectStudentBo projectStudentBo) {
        PxProject pxProject = pxProjectDao.findOne(Long.valueOf(projectStudentBo.getProjectId()));
        Date createTime = new Date();
        String[] stuIds = projectStudentBo.getStudents().indexOf(",")>0
                ?projectStudentBo.getStudents().substring(0, projectStudentBo.getStudents().length() - 1)
                .split(",") :  new String[]{};
        List<String> oldUserList = new ArrayList<String>();
        List<PxProjectStudent> proStus = pxProjectStudentDao.findByProjectId(projectStudentBo.getProjectId());//将项目已有人员加入到里表中，防止重复添加
        for (PxProjectStudent pxProjectStudent : proStus) {
            oldUserList.add(pxProjectStudent.getUserId().toString());
        }
        for (String userId : stuIds) {
            if (!oldUserList.contains(userId)) {
                User user = userDaoImpl.getUser(Long.valueOf(userId));
                PxProjectStudent pxProjectStudent = new PxProjectStudent();
                pxProjectStudent.setCreateTime(createTime);
                pxProjectStudent.setProjectId(Integer.valueOf(projectStudentBo.getProjectId()));
                pxProjectStudent.setProjectLevel(pxProject.getProjectLevel());
                pxProjectStudent.setUserId(Integer.valueOf(userId));
                pxProjectStudent.setUserName(user.getName());
                pxProjectStudentDao.save(pxProjectStudent);
                addClassStudent(pxProjectStudent);
            }
        }
        for(String userId : oldUserList) {
            if(projectStudentBo.getStudents().indexOf(userId) < 0) {
                for(PxProjectStudent pxProjectStudent : proStus) {
                    if(pxProjectStudent.getUserId().equals(Integer.valueOf(userId))){
                        pxProjectStudentDao.delete(pxProjectStudent.getId());
                        deleteClassStudent(pxProjectStudent);
                    }
                }
            } 
        } 
    }

    public List<ProjectStudentBo> searchAllProjectStudent(Integer projectId) {
        List<ProjectStudentBo> proBo = new ArrayList<ProjectStudentBo>();
        List<PxProjectStudent> proStus = pxProjectStudentDao.findByProjectId(projectId);
        for (PxProjectStudent pxProjectStudent : proStus) {
            ProjectStudentBo projectStudentBo = new ProjectStudentBo();
            User user = userDaoImpl.getUser(Long.valueOf(pxProjectStudent.getUserId()));
            projectStudentBo.setDepartmentName(user.getDepartmentName());
            projectStudentBo.setProjectId(projectId);
            projectStudentBo.setUserId(pxProjectStudent.getUserId());
            projectStudentBo.setUserName(user.getName());
            proBo.add(projectStudentBo);
        }
        return proBo;
    }

    public String getStudents(Integer projectId) {
        List<PxProjectStudent> proStus = pxProjectStudentDao.findByProjectId(projectId);
        StringBuffer userIds = new StringBuffer();
        for (PxProjectStudent pxProjectStudent : proStus) {
            userIds.append(pxProjectStudent.getUserId() + ",");
        }
        return userIds.toString();
    }
    public List<PxProjectStudent> getProjectByUserId(Integer userId){
        return pxProjectStudentDao.findByUserId(userId);
    }
    private void addClassStudent(PxProjectStudent pxProjectStudent){
        List<PxClass> pxClassList = pxClassDao.findByProjectId(pxProjectStudent.getProjectId());
        for(int i = 0;i<pxClassList.size();i++){
            PxClassStudent pxClassStudent = new PxClassStudent();
            pxClassStudent.setClassId(pxClassList.get(i).getId().intValue());
            pxClassStudent.setCome(new Integer(1));
            pxClassStudent.setMark("暂无");
            pxClassStudent.setReals(pxClassList.get(i).getClassHour());
            pxClassStudent.setS_score(pxClassList.get(i).getStudentScore());
            pxClassStudent.setScore(pxClassList.get(i).getStudentScore());
            pxClassStudent.setShould(pxClassList.get(i).getClassHour());
            pxClassStudent.setUser_id(pxProjectStudent.getUserId());
            pxClassStudent.setUser_name(pxProjectStudent.getUserName());
            pxClassStudentDao.save(pxClassStudent);
        }
    }
    private void deleteClassStudent(PxProjectStudent pxProjectStudent) {
        List<PxClass> pxClassList = pxClassDao.findByProjectId(pxProjectStudent.getProjectId());
        for(PxClass pxClass : pxClassList){
            pxClassStudentDao.deleteByClassId(Integer.valueOf(pxClass.getId().toString()));
        }
    }
}
