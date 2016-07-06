/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.service.train;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ync365.px.entity.Train;
import com.ync365.px.model.Paginator;
import com.ync365.px.repository.TrainDao;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class TrainService {
	public final Logger logger = LoggerFactory.getLogger(TrainService.class);
	@PersistenceContext  
    private EntityManager em;  
	
	@Autowired
	private 	TrainDao trainDao;

	public long count() {
		return trainDao.count();
	}
	
	public Train getTrain(Long id) {
		return trainDao.findOne(id);
	}
	
	public List<Train> getTrainAll() {
		return (List<Train>) trainDao.findAll();
		
	}
	
	public List<Train> getTrainAll(Paginator paginator) {
		//查询之前先setCount
		paginator.setCount(count());
		logger.debug("page" + paginator.getPage());
		logger.debug("start" + paginator.getStart());
		logger.debug("pageSize" + paginator.getLength());
		return trainDao.getAllPagina(paginator.getStart(),paginator.getLength());
		
	}

	public long buildPageStart(int page,int pageSize){
		return (page - 1) * pageSize; 
	}
	
	public long getPgaeCount(long count ,int pageSize){
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count / pageSize + 1;
		}
	}
}
