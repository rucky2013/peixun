package com.ync365.px.repository;


import com.ync365.px.entity.ProjectScope;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ivan on 1/22/16.
 */
public interface PxProjectScopeDao extends PagingAndSortingRepository<ProjectScope, Long>, JpaSpecificationExecutor<ProjectScope> {

    void deleteByProjectId(Long projectId);

    void findByDepartmentId(Long dapartmentId);
    
    List<ProjectScope> findByProjectId(Long projectId);
}


