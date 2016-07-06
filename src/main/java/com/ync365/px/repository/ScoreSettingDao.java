/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ync365.px.entity.ScoreSetting;

public interface ScoreSettingDao extends PagingAndSortingRepository<ScoreSetting, Long>,
		JpaSpecificationExecutor<ScoreSetting> {
	
	@Query(value="select * from px_score_setting where year = ?1 and username like %?2% limit ?3,?4",nativeQuery=true)
	List<ScoreSetting> getScoreSettings(Integer year,String name,Long start,int pageSize);
	
}
