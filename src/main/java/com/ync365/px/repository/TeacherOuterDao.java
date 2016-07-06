package com.ync365.px.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ync365.px.entity.TeacherOuter;
import com.ync365.px.entity.Train;
import com.ync365.px.model.Paginator;

public interface TeacherOuterDao extends
		PagingAndSortingRepository<TeacherOuter, Long>,
		JpaSpecificationExecutor<TeacherOuter> {

	@Query(value = "select * from `px_teacher_outer` limit ?1,?2", nativeQuery = true)
	public List<TeacherOuter> getAllPagina(long start, int pageSizee);

	@Modifying
	@Query("delete from TeacherOuter teacherOuter where teacherOuter.id=?1")
	void deleteByTeacherOuterId(Long id);
    
	
}
