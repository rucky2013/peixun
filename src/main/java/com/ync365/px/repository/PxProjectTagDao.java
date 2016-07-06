package com.ync365.px.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ync365.px.entity.PxProjectTag;

public interface PxProjectTagDao  extends PagingAndSortingRepository<PxProjectTag, Long>, JpaSpecificationExecutor<PxProjectTag> {
    
    List <PxProjectTag> findByIsUse(Integer isUse);
    
}
