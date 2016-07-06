package com.ync365.px.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.plaf.DesktopPaneUI;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ync365.px.common.config.GeneralStaticConfig;
import com.ync365.px.entity.Department;
import com.ync365.px.entity.User;
import com.ync365.px.service.account.AccountService;
import com.ync365.px.service.account.HrmService;

import weaver.hrm.webservice.DepartmentBean;
import weaver.hrm.webservice.SubCompanyBean;
import weaver.hrm.webservice.UserBean;

/**
 * 更新部门人员task
 * @author liuxiaohang
 *
 */
@Component
public class UpdateHrmTask {
	public static Logger logger = LoggerFactory.getLogger(UpdateHrmTask.class);
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private HrmService hrmService;
	
	@Scheduled(fixedRate = 1000*60*1)
	public void run(){
		logger.info("start run update hrm task ");
//		updateDeparment();
		updateHrm();
	}
	
	
	public void updateHrm(){
		Map<String, User> mapUserDB = new HashMap<String, User>();
		List<User> hrmsDB = accountService.getAllUserByStatus(GeneralStaticConfig.DATA_ABLE);
		for(User user : hrmsDB){
			mapUserDB.put(user.getOaid() + "",user);
		}
		//get department from web service
		logger.info("before get");
		DepartmentBean[] departmentBeans = hrmService.getAllDepartmentOa();
		logger.info("after get");
		Map<String, DepartmentBean> mapIdDepartments = new HashMap<String,DepartmentBean>();
		
		for(DepartmentBean departmentBean : departmentBeans){
			mapIdDepartments.put(departmentBean.get_departmentid(), departmentBean);
		}
		
		//get hrm from oa web service
		UserBean[] usersOA = hrmService.getAllUserOa();
		//get department from db
		List<Department> departmentsDB = hrmService.findDepartmentByStatusAndIsCompany(GeneralStaticConfig.DATA_ABLE,0);
		Map<String,String> mapDepartmentOaidId = new HashMap<String,String>();
		for(Department department : departmentsDB){
			mapDepartmentOaidId.put(department.getOaid(), department.getId() + "");
		}
		Map<String, User> mapUserOA = new HashMap<String, User>();
		for(UserBean userBean : usersOA){
		
			if(StringUtils.isEmpty(userBean.getLoginid())){
				continue;
			}
			User user = new User();
			user.setOaid(userBean.getUserid() + "");
			user.setName(userBean.getLastname());
			user.setPassword(userBean.getPassword());
			user.setLoginName(userBean.getLoginid());
			DepartmentBean departmentBean = mapIdDepartments.get(userBean.getDepartmentid());
			logger.error(mapIdDepartments + "");
			logger.error(user.getDepartmentId() + "");
			logger.error(departmentBean + "");
			if (null == departmentBean) {
				continue;
			}
//			while(!departmentBean.get_supdepartmentid().equals("0")){
//				departmentBean = mapIdDepartments.get(departmentBean.get_supdepartmentid());
//			}
			user.setDepartmentId(Long.valueOf(mapDepartmentOaidId.get(departmentBean.get_departmentid())));
			user.setStatus(GeneralStaticConfig.DATA_ABLE);
			
			mapUserOA.put(user.getOaid() + "", user);
		}
		
		//筛选出需要新增和更新的人员信息
		Set<User> setAdd = new HashSet<User>();
		Set<User> setUpdate = new HashSet<User>();
		for(String key : mapUserOA.keySet()){
			User userOA = mapUserOA.get(key);
			User userDB = mapUserDB.get(key);
			if (null == userDB) {
				accountService.registerUserForSynchronizeData(userOA);
				setAdd.add(userOA);
			} else {
				if (userOA.getLoginName().equals("admin")) {
					continue;
				}
				if(userOA.getName().equals(userDB.getName()) &&
						userOA.getPassword().equals(userDB.getPassword()) &&
						userOA.getOaid().equals(userDB.getOaid()) &&
						userOA.getDepartmentId().equals(userDB.getDepartmentId())){
					continue;
				} else {
					userOA.setId(userDB.getId());
					accountService.registerUserForSynchronizeData(userOA);
					setUpdate.add(userOA);
				}
			}
		}
		
		//筛选出需要删除的人员信息
		Set<User> setDelete = new HashSet<User>();
		for(String key : mapUserDB.keySet()){
			User userOA = mapUserOA.get(key);
			User userDB = mapUserDB.get(key);
			if(null == userOA){
				userDB.setStatus(GeneralStaticConfig.DATA_DISABLE);
				accountService.registerUserForSynchronizeData(userDB);
				setDelete.add(userDB);
			}
		}
		
		
		//insert
			accountService.saveUsers(setAdd);
		//update
			accountService.saveUsers(setUpdate);
		//delete(set status = 1)
			accountService.saveUsers(setDelete);
		logger.info("新增人员 ===" + setAdd.size());
		logger.info("更新人员 ===" + setUpdate.size());
		logger.info("删除人员 ===" + setDelete.size()) ;
		
		
		
	}
	
	
	public void updateDeparment(){
		
		//插入根节点
		
		Department departmentRoot = hrmService.getDepartmentRoot();
		
		if (null == departmentRoot) {
			departmentRoot = new Department();
			departmentRoot.setName("北京天辰云农场科技股份有限公司");
			departmentRoot.setParent(-1l);
			departmentRoot.setParentName("");
			departmentRoot.setLeaf(0);
			departmentRoot.setIsCompany(1);
			departmentRoot.setStatus(GeneralStaticConfig.DATA_ABLE);
			hrmService.saveDepartment(departmentRoot);
		}
		
		
		//load department from db
		Map<String, Department> mapDepDB = new HashMap<String, Department>();
		List<Department> departmentDB = hrmService.getAllDepartmentByStatus(GeneralStaticConfig.DATA_ABLE);
		Set<String> partens = new HashSet<String>();
		for(Department dep : departmentDB){
			mapDepDB.put(dep.getOaid(),dep);
			partens.add(dep.getSubcompanyId());
		}
		//get company from oa web service
		
		SubCompanyBean[] companyBeans = hrmService.getAllSubCompanyOa();
		Map<String, SubCompanyBean> mapSubCompanyId = new HashMap<String,SubCompanyBean>();
		for(SubCompanyBean companyBean : companyBeans){
			mapSubCompanyId.put(companyBean.get_subcompanyid(), companyBean);
		}
		
		//get department from oa web service
		DepartmentBean[] dpeartmentsOA = hrmService.getAllDepartmentOa();
		Map<String, Department> mapOA = new HashMap<String, Department>();
		for(DepartmentBean depBean : dpeartmentsOA){
			//过滤有上级部门的部门
//			if (!depBean.get_supdepartmentid().equals("0")) {
//				continue;
//			}
			
			Department dep = new Department();
			dep.setOaid(depBean.get_departmentid());
			dep.setName(depBean.get_fullname());
			System.out.println("" + depBean.get_fullname() + " ===  " + depBean.get_supdepartmentid() + "====" + depBean.get_subcompanyid());
			if (StringUtils.isNotEmpty(depBean.get_supdepartmentid())) {
				if (depBean.get_supdepartmentid().equals("0")) {
					dep.setParentOaId(depBean.get_subcompanyid());
				} else {
					dep.setParentOaId(depBean.get_supdepartmentid());
					dep.setSubcompanyId(depBean.get_subcompanyid());
				}
				
			} /*else {
				if (StringUtils.isNotEmpty(depBean.get_subcompanyid())) {
					dep.setParentOaId(mapSubCompanyId.get(depBean.get_subcompanyid()).get_subcompanyid());
				}
			}*/
//			dep.setSubcompanyId(depBean.get_subcompanyid());
//			dep.setParent(Long.valueOf(depBean.get_supdepartmentid()));
			dep.setParentName(mapSubCompanyId.get(depBean.get_subcompanyid()).get_fullname());		
			dep.setLeaf(0);
			dep.setStatus(GeneralStaticConfig.DATA_ABLE);
			dep.setIsCompany(0);
			mapOA.put(dep.getOaid(), dep);
		}
		
		//筛选出需要新增和更新的数据
		Set<Department> setAdd = new HashSet<Department>();
		Set<Department> setUpdate = new HashSet<Department>();
		for(String key : mapOA.keySet()){
			Department depOA = mapOA.get(key);
			Department depDB = mapDepDB.get(key);
			if (null == depDB) {
				setAdd.add(depOA);
			} else {
				if(depOA.getName().equals(depDB.getName())
						&& depOA.getParentName().equals(depDB.getParentName())
						&& (null != depOA.getParentOaId() && null != depDB.getParentOaId() && depOA.getParentOaId().equals(depDB.getParentOaId()))
						&& depOA.getLeaf().equals(depDB.getLeaf())){
					continue;
				} else {
					depOA.setId(depDB.getId());
					setUpdate.add(depOA);
				}
			}
		}
		
		//筛选出需要删除的数据
		Set<Department> setDelete = new HashSet<Department>();
		for(String key : mapDepDB.keySet()){
			Department depOA = mapOA.get(key);
			Department depDB = mapDepDB.get(key);
			if(null == depOA){
				depDB.setStatus(GeneralStaticConfig.DATA_DISABLE);
				setDelete.add(depDB);
			}
		}
		
		for(SubCompanyBean subCompanyBean : companyBeans){
			if (partens.contains(subCompanyBean.get_subcompanyid())) {
				continue;
			}
			
			Department department = new Department();
			department.setName(subCompanyBean.get_fullname());
			department.setOaid(subCompanyBean.get_subcompanyid());
			department.setParent(departmentRoot.getId());
			department.setParentName(departmentRoot.getName());
			department.setSubcompanyId(departmentRoot.getId() + "");
			department.setStatus(GeneralStaticConfig.DATA_ABLE);
			department.setIsCompany(1);
			department.setLeaf(0);
			setAdd.add(department);
		}
		
		//insert
			hrmService.saveDepartments(setAdd);
		//update
			hrmService.saveDepartments(setUpdate);
		//delete(set status = 1)
			hrmService.saveDepartments(setDelete);
		//更新部门层级
		updateDepartmentTree();
			
		logger.info("insert department ===" + setAdd.size());
		logger.info("update department ===" + setUpdate.size());
		logger.info("delete department ===" + setDelete.size()) ;
		
		
	}

	
	public void updateDepartmentTree(){
		List<Department> departments = hrmService.getAllDepartmentByStatus(GeneralStaticConfig.DATA_ABLE);
		Map<String, Department> mapSubCompany = new HashMap<String,Department>();
		Map<String, Long> mapOaId = new HashMap<String,Long>();
		Set<String> parentOaIds = new HashSet<String>();
		List<Department> updates = new ArrayList<Department>();
		for(Department department : departments){
			if (null != department.getIsCompany()) {
				
				if (department.getIsCompany().equals(0)) {
					if (null != department.getOaid()) {
						mapOaId.put(department.getOaid(), department.getId());
					} 
				}
				if (department.getIsCompany().equals(1)) {
					if (null != department.getOaid()) {
						mapSubCompany.put(department.getOaid()+ "", department);
					}
				}
			}
				
		}
		
		for(Department department : departments){
			Integer isCompany = department.getIsCompany();
			if (null != isCompany) {
				if (null != mapOaId.get(department.getParentOaId())) {
					if (isCompany.equals(0)) {
						if (null != mapOaId.get(department.getParentOaId())) {
							department.setParent(mapOaId.get(department.getParentOaId()));
						}
						
					}
					if (isCompany.equals(1)) {
						department.setParent(mapSubCompany.get(department.getParentOaId()).getId());
					}
					updates.add(department);
				}
				
			}
		}
		hrmService.saveDepartments(updates);
	}
	
	
	
	
	
}
