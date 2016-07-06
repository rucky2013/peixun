package com.ync365.px.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ync365.px.entity.PxFile;

public interface PxFileDao  extends PagingAndSortingRepository<PxFile, Long>,JpaSpecificationExecutor<PxFile>{
    
    public List<PxFile> findByTypeAndForeignKey(Integer type,Integer foreignKey);
}
