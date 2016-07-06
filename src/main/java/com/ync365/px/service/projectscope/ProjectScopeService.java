package com.ync365.px.service.projectscope;

import com.ync365.px.entity.Department;
import com.ync365.px.entity.ProjectScope;
import com.ync365.px.repository.DepartmentDao;
import com.ync365.px.repository.PxProjectScopeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ivan on 1/22/16.
 */
@Component
@Transactional
public class ProjectScopeService {

    public final Logger logger = LoggerFactory.getLogger(ProjectScopeService.class);

    @Autowired
    private PxProjectScopeDao pxProjectScopeDao;

    @Autowired
    private DepartmentDao departmentDao;


    /**
     *
     * @param projectId
     * @param ids
     */
    public void insertScopes(Long projectId ,String ids) {
        pxProjectScopeDao.deleteByProjectId(projectId);
        Date date = new Date();
        if(ids.equals("1")) {
            ProjectScope projectScope = new ProjectScope();
            projectScope.setCreateTime(date);
            projectScope.setProjectId(projectId);
            projectScope.setDepartmentId(Long.valueOf(1));
            pxProjectScopeDao.save(projectScope);
            return;
        }

        pxProjectScopeDao.deleteByProjectId(projectId);
        List <ProjectScope> projectScopes = new ArrayList<ProjectScope>();
        for(String id : ids.split(",")) {
            ProjectScope projectScope = new ProjectScope();
            projectScope.setCreateTime(date);
            projectScope.setProjectId(projectId);
            projectScope.setDepartmentId(Long.valueOf(id));
            projectScopes.add(projectScope);
            insertToLeaf(Long.valueOf(id), date, projectId, projectScopes);
        }
        pxProjectScopeDao.save(projectScopes);
    }



    /**
     * @deprecated
     * @
     * @param projectId
     */
    public void deleteByProjectId(Long projectId) {
        pxProjectScopeDao.deleteByProjectId(projectId);
    }

    private void insertToLeaf(Long id ,Date date , Long projectId ,List <ProjectScope> projectScopes) {//递归插入全部权限节点
        List<Department> departments = departmentDao.findByParent(id);
        for(Department department : departments) {
            if(department.getLeaf().equals("1")) {
                return ;
            }

            ProjectScope projectScope = new ProjectScope();
            projectScope.setCreateTime(date);
            projectScope.setProjectId(projectId);
            projectScope.setDepartmentId(department.getId());
            projectScopes.add(projectScope);
            insertToLeaf(department.getId(),date,projectId,projectScopes);
        }
    }
}
