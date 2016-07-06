/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ync365.px.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> , JpaSpecificationExecutor<User>{
	User findByLoginName(String loginName);
	List<User> findByStatus(Integer status);
	
}
