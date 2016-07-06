package com.ync365.px.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ync365.px.entity.PxProjectStudent;

public interface PxProjectStudentDao  extends PagingAndSortingRepository<PxProjectStudent, Long>, JpaSpecificationExecutor<PxProjectStudent> {

    List<PxProjectStudent> findByUserId(Integer isUse);
    
    List <PxProjectStudent> findByProjectId(Integer projectId);
    
    void deleteByProjectId(Integer projectId);
}
