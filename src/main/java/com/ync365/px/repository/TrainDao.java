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

import com.ync365.px.entity.Train;

public interface TrainDao extends PagingAndSortingRepository<Train, Long>,
		JpaSpecificationExecutor<Train> {

	@Query(value = "select * from px_train limit ?1,?2", nativeQuery = true)
	public List<Train> getAllPagina(long start, int pageSizee);
}
