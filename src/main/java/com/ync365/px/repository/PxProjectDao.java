package com.ync365.px.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ync365.px.entity.PxProject;

public interface PxProjectDao  extends PagingAndSortingRepository<PxProject, Long>, JpaSpecificationExecutor<PxProject> {
	
    @Query(value="select * from px_project limit ?1,?2",nativeQuery=true)
    public List<PxProject> getAllPagina(long start,int pageSizee);

    @Modifying
    @Query(value = " UPDATE  `px_project` SET `ticket_num`=?2 WHERE  `id`=?1", nativeQuery = true)
    public void updateTNumById(Long id, int num);
}