package com.ync365.px.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ync365.px.entity.PxClassStudent;

public interface PxClassStudentDao  extends PagingAndSortingRepository<PxClassStudent, Long>, JpaSpecificationExecutor<PxClassStudent> {
    
    public void deleteByClassId(Integer classId);
}
