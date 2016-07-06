/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.repository;

import com.ync365.px.entity.Department;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DepartmentDao extends PagingAndSortingRepository<Department, Long>, JpaSpecificationExecutor<Department> {

	List<Department> findDepartmentByStatus(Integer status);
	

	List<Department> findDepartmentByStatusAndIsCompany(Integer status,Integer isCompany);
	
	List<Department> findByParent(Long parent);

}
