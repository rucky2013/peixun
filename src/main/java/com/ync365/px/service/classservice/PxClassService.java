package com.ync365.px.service.classservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
  




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

 
 

















import com.ync365.px.entity.Department;
 
 
import com.ync365.px.entity.PxClass;
import com.ync365.px.entity.PxProject;
import com.ync365.px.entity.User;
import com.ync365.px.model.Paginator;
import com.ync365.px.model.TeacherInDetailModel;
import com.ync365.px.model.TeacherInSumModel;
 
 
import com.ync365.px.repository.DepartmentDao;
import com.ync365.px.repository.PxClassDao;
import com.ync365.px.repository.TrainDao;
import com.ync365.px.repositoryimpl.PxClassDaoImpl;
import com.ync365.px.service.account.AccountService;
import com.ync365.px.service.account.HrmService;
import com.ync365.px.service.project.ProjectService;

//Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class PxClassService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PxClassDao pxClassDao;
    
	private  PxClassDaoImpl pxClassDaoImpl;
	

	@Autowired
	private ProjectService projectService;

	@Autowired
   private HrmService  hrmService;
    
	@Autowired
	private AccountService accountService;
	
	public PxClassDaoImpl getPxClassDaoImpl() {
		return pxClassDaoImpl;
	}

   
    @Autowired
	public void setPxClassDaoImpl(PxClassDaoImpl pxClassDaoImpl) {
		this.pxClassDaoImpl = pxClassDaoImpl;
	}
    
   
	/**
     * 获取所有内部讲师明细表的数据
     * @param paginator
     * @return
     */
	/*public List<TeacherInDetailModel> getAllPagina(Paginator paginator) {
		paginator.setCount(count());
		List<TeacherInDetailModel> teacherInDetailModels = new ArrayList<TeacherInDetailModel>();

		List<PxClass> pxClasss = pxClassDaoImpl.getTeacherInDetailAll(paginator.getStart(),
				paginator.getPageSize());
		    
		if (null != pxClasss && pxClasss.size() > 0) {
			for (PxClass temp : pxClasss) {
				TeacherInDetailModel teacherInDetailModel = new TeacherInDetailModel();
				teacherInDetailModel.setName(temp.getName());
				teacherInDetailModel.setBeginTime(temp.getBegin_time());
				teacherInDetailModel.setEndTime(temp.getEnd_time());
				teacherInDetailModel.setClassHour(temp.getClassHour());
				teacherInDetailModel.setPayClassScore(temp.getPayClassScore());
				teacherInDetailModel.setTeacherName(temp.getTeacherName());
				teacherInDetailModel.setTeacherScore(temp.getTeacherScore());
				teacherInDetailModel.setTeacherType(temp.getTeacherType());
				if (temp.getProjectId() != 0) {
					PxProject project = projectService.getProject((long)temp
							.getProjectId());

					teacherInDetailModel.setProjectName(project.getName());
					teacherInDetailModel.setProjectStartTime(project
							.getBeginTime());
					teacherInDetailModel
							.setProjectEndTime(project.getEndTime());
					teacherInDetailModel.setProjectLevel(project
							.getProjectLevel());

				}

				if (temp.getTeacherId()!=0) {
					
					User user = accountService.getUser((long)temp.getTeacherId());
					 
					if(user.getDepartmentId()!=0){
						
						Department department = hrmService.
									getDepartment(user.getDepartmentId());
							teacherInDetailModel.setDepartment(department.getName());
							teacherInDetailModel.setParentDepartment(department
									.getParentName());
						}
					 }
					
               
				teacherInDetailModels.add(teacherInDetailModel);

			}
		}
		return teacherInDetailModels;
	}*/
	
	
	
	public long getCountInSumByNameAndCompany(String name,String company,String year) {
		 
		return pxClassDaoImpl.getCountInSumByNameAndCompany(name,company, year);
				 
	}
    /**
     * 根据讲师姓名查询出所有的内部讲师汇总记录数
     * @param name
     * @param year
     * @return
     */
	public long getCountBySumName(String name,String year) {
		 
		return pxClassDaoImpl.getCountBySumName(name, year);
				 
	}
	/**
	 * 根据公司或单位获取所有的内部讲师汇总记录数
	 * @param company
	 * @param year
	 * @return
	 */
	public long getCountBySumCompany(String company,String year){
		 
		return pxClassDaoImpl.getCountBySumCompany(company, year);
				 
	}
	  
	/**
     * 获取所有内部讲师汇总表的数据
     * @param paginator
	 * @param year 
     * @return
     */
	public List<TeacherInSumModel> getAllPaginaBySum(Paginator paginator, String year) {
		paginator.setCount(getCountByTeacherInSum(year));
		 
		return pxClassDaoImpl.getTeacherInSumInYear (paginator.getStart(),
				paginator.getLength(),year);
                            
		   
	}
	
	public List<TeacherInSumModel> getTeacherInSumByNameAndCompany(String name,
			String company, Paginator paginator, String year) {
		             paginator.setCount(getCountInSumByNameAndCompany(name,company,year));
		return  pxClassDaoImpl.getTeacherInSumByNameAndCompany(name,company,paginator.getStart(),paginator.getLength(),year);
	}
	
	 /**
	  * 根据人员姓名查询出所有的讲师内部汇总
	  * @param company
	  * @param paginator
	  * @param year
	  * @return
	  */
    public List<TeacherInSumModel> getTeacherInSumByName(String name,
			Paginator paginator, String year) {
		 
    	paginator.setCount(getCountBySumName(name,year));
		  
		return  pxClassDaoImpl.getTeacherInSumByName(name,paginator.getStart(),paginator.getLength(),year);
	}
	/**
	 * 根据单位获取所有的内部讲师汇总数据
	 * @param company
	 * @param paginator
	 * @param year
	 * @return
	 */
	public List<TeacherInSumModel> getTeacherInSumByCompany(String company,
			Paginator paginator, String year) { 
		paginator.setCount(getCountBySumCompany(company,year));
		  
		return  pxClassDaoImpl.getTeacherInSumByCompany(company,paginator.getStart(),paginator.getLength(),year);
	}
	
	 /**
	  * 获取初始的内部讲师明细所有数据
	  * @param paginator
	  * @param year
	  * @return
	  */
	/*public List<TeacherInDetailModel> getTeacherInDetailAll(
			Paginator paginator, String year) {
	 
		return pxClassDaoImpl.TeacherInDetailAll(paginator.getStart(),paginator.getPageSize(),year);
	}*/

	/**
	 * 根据单位获得所有的内部讲师明细
	 * @param company
	 * @param paginator
	 * @return
	 */
	public List<TeacherInDetailModel> getTeacherInDetailByCompany(
			String company, Paginator paginator,String year) {
		paginator.setCount(getCountByCompany(company,year));
		return  pxClassDaoImpl.getTeacherInDetailByCompany(company,paginator.getStart(),paginator.getLength(),year);
	}
    
	/**
	 * 根据单位查询出所有内部讲师明细的总记录数
	 * @param company
	 * @return
	 */
	public long getCountByCompany(String company ,String year){
		
		 long count=pxClassDaoImpl.getCountByCompany(company,year);
		 System.out.println("count:"+count);
		 return count;
	} 
		
	
	
	/**
	 * 查询内部讲师的汇总总记录数
	 * @return
	 */
	public long getCountByTeacherInSum(String year){
		return pxClassDaoImpl.getCountBySum(year);
	}
	/**
	 * 根据人员姓名查询出总记录数
	 * @param name
	 * @return
	 */
   public long getCountByName(String name,String year,long pageStart,int pageSize){
		
		return pxClassDaoImpl.getCountByName(name,year,pageStart,  pageSize);
	} 
	/**
	 * 根据姓名查询所有的内部讲师明细
	 * @param name
	 * @param paginator
	 * @return
	 */
	public List<TeacherInDetailModel> getTeacherInDetailByName(String name,
			Paginator paginator,String year) {
		 
		paginator.setCount(getCountByName(name,year,paginator.getStart(),paginator.getLength()));
		return  pxClassDaoImpl.getTeacherInDetailByName(name,paginator.getStart(),paginator.getLength(),year);
	}
   
	 /**
	  * 按姓名降序排序 内部讲师明细表
	  * @param paginator
	  * @return
	  */
	public List<TeacherInDetailModel> getTeacherInDetailByNameOrderDESC(
			Paginator paginator,String year) {
		  paginator.setCount(getCountByNameOrderAndProTime( year));
		  
		return  pxClassDaoImpl.getTeacherInDetailByNameOrderDESC(paginator.getStart(),paginator.getLength(),year);
			 
	}
    
	/**
	  * 按姓名升序排序 内部讲师明细表
	  * @param paginator
	  * @return
	  */
	public List<TeacherInDetailModel> getTeacherInDetailByNameOrderASC(
			Paginator paginator,String year) {
		  paginator.setCount(getCountByNameOrderAndProTime( year));
		  
		return  pxClassDaoImpl.getTeacherInDetailByNameOrderASC(paginator.getStart(),paginator.getLength(),year);
			 
	}
   
	
	/**
	 * 按项目开始时间排序 内部讲师明细表
	 * @param paginator
	 * @return
	 */
	public List<TeacherInDetailModel> getTeacherInDetailByProjectBeginTimeOrder(
			Paginator paginator,String year) {
		 paginator.setCount(getCountByNameOrderAndProTime( year));
		return  pxClassDaoImpl.getTeacherInDetailByProjectBeginTimeOrder (paginator.getStart(),paginator.getLength(), year);
	}
    /**
     * 在此年份的内部讲师明细
     * @param paginator
     * @param year
     * @return
     */
	public List<TeacherInDetailModel> getTeacherInDetailInYear(
			Paginator paginator, String year) {
		 paginator.setCount(getCountInYear(year)); 
			return  pxClassDaoImpl.getTeacherInDetailInYear(paginator.getStart(),paginator.getLength(),year);
		 
	}
	/**
	 * 根据单位和姓名 内部讲师明细表
	 * @param company
	 * @param name
	 * @param paginator
	 * @param year
	 * @return
	 */
	public List<TeacherInDetailModel> getTeacherInDetailInByCompanyAndName(
			String company, String name, Paginator paginator, String year) {
		 
		 paginator.setCount(getCountInDetailInByCompanyAndName(company,name,year)); 
			return  pxClassDaoImpl.TeacherInDetailInByCompanyAndName(company,name,paginator.getStart(),paginator.getLength(),year);
	}
  /**
   * 根据单位和姓名查询出 所有内部讲师明细表
   * @param company
   * @param name
   * @param year
   * @return
   */
	public long getCountInDetailInByCompanyAndName(String company,String name,String year) {
		// TODO Auto-generated method stub
		return  pxClassDaoImpl.getCountByCompanyAndName(company, name, year);
	}


	
    /**
     * 按项目时间升序排序，内部讲师明细
     * @param paginator
     * @param year
     * @return
     */
	public List<TeacherInDetailModel> getTeacherInDetailByProjectBeginTimeOrderAsc(
			Paginator paginator, String year) {
		 paginator.setCount(getCountByNameOrderAndProTime( year));
			return  pxClassDaoImpl.getTeacherInDetailByProjectBeginTimeOrderAsc (paginator.getStart(),paginator.getLength(), year);
		 
	}
	/**
	 * 获取按项目开始时间或姓名排序的总记录数
	 * @param year
	 * @return
	 */
	public long getCountByNameOrderAndProTime(String year){
		return pxClassDaoImpl.getCountByNameOrderAndProTime(year);
	} 
	/**
	 * 获取在此年份的所有内部讲师明细记录数
	 * @param year
	 * @return
	 */
	public long getCountInYear(String year){
		return pxClassDaoImpl.getCountInYear(year);
	} 
	public long count() {
		return pxClassDao.count();
	}

	public long buildPageStart(int page, int pageSize) {
		return (page - 1) * pageSize;
	}

	public long getPgaeCount(long count, int pageSize) {
		if (count % pageSize == 0) {
			return count / pageSize;
		} else {
			return count / pageSize + 1;
		}
	}




	



	


	


	


	
 
}
