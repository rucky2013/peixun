/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.service.account;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.ync365.px.common.config.GeneralStaticConfig;
import com.ync365.px.entity.Department;
import com.ync365.px.entity.ScoreSetting;
import com.ync365.px.entity.User;
import com.ync365.px.repository.DepartmentDao;
import com.ync365.px.repository.UserDao;
import com.ync365.px.repositoryimpl.UserDaoImpl;

import localhost.services.HrmService.HrmServiceLocator;
import localhost.services.HrmService.HrmServicePortType;
import weaver.hrm.webservice.DepartmentBean;
import weaver.hrm.webservice.SubCompanyBean;
import weaver.hrm.webservice.UserBean;

/**
 * oa 同步数据Service
 * 
 * @author liuxiaohang
 */
// Spring Service Bean的标识.
@Service
@Component
@Transactional
public class HrmService {

	private static Logger logger = LoggerFactory.getLogger(HrmService.class);
	
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired UserDaoImpl userDaoImpl;
	public List<User> getAllUser(){
		return userDaoImpl.getAllUser(); 
	}
	
	public User getUser(Long userid){
		return userDaoImpl.getUser(userid); 
	}
	
	public void saveDepartments(List<Department> departments){
		departmentDao.save(departments);
	}
	
	public void saveDepartment(Department department){
		departmentDao.save(department);
	}
	
	public Department getDepartment(Long id) {
		return departmentDao.findOne(id);
	}
	
	public Department getDepartmentRoot() {
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("EQ_parent", "-1");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Department> spec = DynamicSpecifications.bySearchFilter(filters.values(), Department.class);
		return departmentDao.findOne(spec);
	}
	public void saveDepartments(Set<Department> departments){
		departmentDao.save(departments);
	}
	
	public List<Department> getAllDepartmentByStatus(Integer status){
		return departmentDao.findDepartmentByStatus(status);
	}
	
	public List<Department> findDepartmentByStatusAndIsCompany(Integer status,Integer isCompany){
		return departmentDao.findDepartmentByStatusAndIsCompany(status,isCompany);
	}
	
	/***
	 * 从oa获取员工信息
	 * @UserBean[]
	 */
	public UserBean[] getAllUserOa(){
		UserBean[] users = null;
		HrmServiceLocator hrmServiceLocator = new HrmServiceLocator();
		try {
			HrmServicePortType hrmServicePortType = hrmServiceLocator.getHrmServiceHttpPort();
			users = hrmServicePortType.getHrmUserInfo(GeneralStaticConfig.OA_WEB_SERVICE_IP, null, null, null, null, null);
			
		} catch (ServiceException e) {
			logger.error("从oa获取员工信息时错误" + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			logger.error("从oa获取员工信息时错误" + e.getMessage());
			e.printStackTrace();
		}
		
		return users;
		
	}
	
	/**
	 * 从oa获取部门信息
	 * @DepartmentBean[]
	 */
	public DepartmentBean[] getAllDepartmentOa(){
		DepartmentBean[] departments = null;
		HrmServiceLocator hrmServiceLocator = new HrmServiceLocator();
		try {
			HrmServicePortType hrmServicePortType = hrmServiceLocator.getHrmServiceHttpPort();
			departments = hrmServicePortType.getHrmDepartmentInfo(GeneralStaticConfig.OA_WEB_SERVICE_IP, null);
			
		} catch (ServiceException e) {
			logger.error("从oa获取部门信息时错误" + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			logger.error("从oa获取部门信息时错误" + e.getMessage());
			e.printStackTrace();
		}
		return departments;
		
	}
	
	
	/***
	 * 从oa获取分部信息
	 * @return SubCompanyBean[]
	 */
	public SubCompanyBean[] getAllSubCompanyOa(){
		SubCompanyBean[] subCompanyBeans = null;
		HrmServiceLocator hrmServiceLocator = new HrmServiceLocator();
		try {
			HrmServicePortType hrmServicePortType = hrmServiceLocator.getHrmServiceHttpPort();
			subCompanyBeans = hrmServicePortType.getHrmSubcompanyInfo(GeneralStaticConfig.OA_WEB_SERVICE_IP);
			
		} catch (ServiceException e) {
			logger.error("从oa获取分部信息时错误" + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			logger.error("从oa获取分部信息时错误" + e.getMessage());
			e.printStackTrace();
		}
		return subCompanyBeans;
		
	} 
	
}
