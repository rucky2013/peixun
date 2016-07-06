package com.ync365.px.service.department;

import com.ync365.px.entity.Department;
import com.ync365.px.repository.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentService {
    @Autowired
    private DepartmentDao departmentDao;
    
    public List<Department> getDepartmentByParentId(Long parent) {
        List<Department> departments = departmentDao.findByParent(parent);
        return departments;
    }
}
