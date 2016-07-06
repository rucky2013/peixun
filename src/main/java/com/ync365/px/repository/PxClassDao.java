package com.ync365.px.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ync365.px.entity.PxClass;

public interface PxClassDao extends PagingAndSortingRepository<PxClass, Long>, JpaSpecificationExecutor<PxClass> {
	 
		@Query(value = " SELECT * FROM  `px_class` WHERE  `teacher_type`=1  LIMIT ?1,?2", nativeQuery = true)
		public List<PxClass> getAllPagina(long start, int pageSizee);
	    
		@Modifying
		@Query(value = " UPDATE  `px_class` SET `pay_class_score`=?1,`sclass_hour`=?2,`steacher_score`=?3,`degree`=?4 WHERE  `id`=?5", nativeQuery = true)
		public void updateScore(double a,double b,double c,double e,int d);
		/*@Query(value=" SELECT temp.`id`, temp.teacher_name, "
				            + "SUM(temp.class_hour) AS sum_class_hour  , "
				            + "temp.`project_id`,temp.`teacher_id`,"
				            + "temp.`teacher_name`,temp.`teacher_score`, "
				            + "temp.`begin_time`,temp.`end_time`,"
				            + "temp.`status`,temp.`create_time`,"
				            + "temp.`teacher_type`,temp.`pay_class_score` " 
				            + "FROM (SELECT *  " 
				                  + " FROM `px_class` pc "   
				                  + " WHERE pc.`teacher_type`=1) AS temp " 
				      + " GROUP BY temp.teacher_name ",nativeQuery=true)
		public List<PxClass> getClassHourYear();*/
         
		
		public List<PxClass> findByProjectId(Integer projectId);
		
		public void deleteByProjectId(Integer projectId);
		
}
