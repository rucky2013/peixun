package com.ync365.px.repositoryimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

  
 












import com.ync365.px.model.TeacherInDetailModel;
import com.ync365.px.model.TeacherInSumModel;

  
 

public class PxClassDaoImpl  extends JdbcDaoSupport {

	public PxClassDaoImpl() {
		super();
		 
	}

	@Autowired
	public PxClassDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 根据单位和姓名查询出所有的内部讲师的汇总的记录数
	 * @param name
	 * @param company
	 * @param year
	 * @return
	 */
	public long getCountInSumByNameAndCompany(String name, String company,
			String year) {
		 String sql="SELECT  COUNT(*) "
		    		+ "FROM  (SELECT  pc.`teacher_name` "
		    		        + "FROM  `px_class` pc "
		    		        +" LEFT JOIN `px_project` pp "
		    		          +" ON   pc.`project_id`=pp.`id` "
	                        +"LEFT JOIN `px_user` pu "
	                        +" ON pc.`teacher_id`=pu.`id` "
	                        +" LEFT JOIN `px_department` pd "
	                        +"ON pu.`department_id`=pd.`id`"
	                        +"WHERE pc.`teacher_type`=1 "
	                        +"AND pc.`teacher_name` LIKE '%"+name+"%' "
	                          +"AND pd.`parent_name` LIKE '%"+company+"%' " 
	                        +" AND pp.`begin_time` "
	                        + " BETWEEN '"+year+"-01-01' AND '"+year+"-12-31'  "
	                        +" GROUP BY pc.`teacher_name`) AS temp ";
		    return getJdbcTemplate().queryForLong(sql) ;
	}

	
	
	/**
	 * 根据讲师姓名查询出所有的内部讲师汇总记录数
	 * @param name
	 * @param year
	 * @return
	 */
	public long getCountBySumName(String name, String year) {
		 
		 String sql="SELECT  COUNT(*) "
		    		+ "FROM  (SELECT  pc.`teacher_name` "
		    		        + "FROM  `px_class` pc "
		    		        +" LEFT JOIN `px_project` pp "
		    		          +" ON   pc.`project_id`=pp.`id` "
	                        +"LEFT JOIN `px_user` pu "
	                        +" ON pc.`teacher_id`=pu.`id` "
	                        +" LEFT JOIN `px_department` pd "
	                        +"ON pu.`department_id`=pd.`id`"
	                        +"WHERE pc.`teacher_type`=1 "
	                        +"AND pc.`teacher_name` LIKE '%"+name+"%' "
	                          +" AND pp.`begin_time` "
	                        + " BETWEEN '"+year+"-01-01' AND '"+year+"-12-31'  "
	                        +" GROUP BY pc.`teacher_name`) AS temp ";
		    return getJdbcTemplate().queryForLong(sql) ;
	}

	
	/**
	 * 根据单位查询出所有的内部讲师汇总记录数
	 * @param company
	 * @param year
	 * @return
	 */
	public long getCountBySumCompany(String company,String year){
		
		 String sql="SELECT  COUNT(*) "
		    		+ "FROM  (SELECT  pc.`teacher_name` "
		    		        + "FROM  `px_class` pc "
		    		        +" LEFT JOIN `px_project` pp "
		    		          +" ON   pc.`project_id`=pp.`id` "
	                        +"LEFT JOIN `px_user` pu "
	                        +" ON pc.`teacher_id`=pu.`id` "
	                        +" LEFT JOIN `px_department` pd "
	                        +"ON pu.`department_id`=pd.`id`"
	                        +"WHERE pc.`teacher_type`=1 "
	                        +"AND pd.`parent_name` LIKE '%"+company+"%' "
	                          +" AND pp.`begin_time` "
	                        + " BETWEEN '"+year+"-01-01' AND '"+year+"-12-31'  "
	                        +" GROUP BY pc.`teacher_name`) AS temp ";
		    return getJdbcTemplate().queryForLong(sql) ;
	}
	/**
	 * 查询讲师汇总记录数
	 * @return
	 */
	public long getCountBySum(String year){
		
	    String sql="SELECT  COUNT(*) "
	    		+ "FROM  (SELECT  pc.`teacher_name` "
	    		        + "FROM  `px_class` pc "
	    		        +" LEFT JOIN `px_project` pp "
	    		          +" ON   pc.`project_id`=pp.`id` "
                        +"LEFT JOIN `px_user` pu "
                        +" ON pc.`teacher_id`=pu.`id` "
                        +" LEFT JOIN `px_department` pd "
                        +"ON pu.`department_id`=pd.`id`"
                        +"WHERE pc.`teacher_type`=1 "
                        +"AND  pp. `begin_time` "
                        + "  BETWEEN '"+year+"-01-01' AND '"+year+"-12-31'  "
                        +" GROUP BY pc.`teacher_name`) AS temp ";
	    return getJdbcTemplate().queryForLong(sql) ;
	}
	
	
 
	/**根据单位和姓名查询出总记录数 内部讲师明细表
	 * 
	 * @param company
	 * @param year
	 * @return
	 */
	public long  getCountByCompanyAndName(String company,String name,String year){
		String sql=" SELECT COUNT(*) "
				  +"FROM ( "
				  +"SELECT pc.`teacher_name`,pd.`name` "  
				  +" FROM  `px_class` pc "
				  +" LEFT JOIN `px_user` pu "
				  +"ON  pc.`teacher_id`=pu.`id` "
				  +"  LEFT JOIN `px_project` pp " 
				  +" ON   pc.`project_id`=pp.`id`  "
				  +"LEFT JOIN `px_department` pd "
				  +" ON   pu.`department_id`=pd.`id` " 
				  +"WHERE pd.`parent_name` LIKE '%"+company+"%' "
				    +"AND pc.`teacher_name` LIKE '%"+name+"%' "
				    +" AND  pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
				  +" AND  pc.`teacher_type`=1 ) AS temp ";
		return getJdbcTemplate().queryForLong(sql) ;
	}

	 /**
	  * 根据单位查询所有内部讲师明细总记录数
	  * @param company
	  * @return
	  */
	public long  getCountByCompany(String company,String year){
		String sql=" SELECT COUNT(*) "
				  +"FROM ( "
				  +"SELECT pc.`teacher_name`,pd.`name` "  
				  +" FROM  `px_class` pc "
				  +" LEFT JOIN `px_user` pu "
				  +"ON  pc.`teacher_id`=pu.`id` "
				  +"  LEFT JOIN `px_project` pp " 
				  +" ON   pc.`project_id`=pp.`id`  "
				  +"LEFT JOIN `px_department` pd "
				  +" ON   pu.`department_id`=pd.`id` " 
				  +"WHERE pd.`parent_name` LIKE '%"+company+"%' "
				    +" AND  pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
				  +" AND  pc.`teacher_type`=1 ) AS temp ";
		return getJdbcTemplate().queryForLong(sql) ;
	}
	 
	/**
	 * 获取按时间或姓名排序的总记录数
	 * @param year
	 * @return
	 */
	public long getCountByNameOrderAndProTime(String year) {
		String sql=" SELECT COUNT(*) "
				  +"FROM ( "
				  +"SELECT pc.`teacher_name`,pd.`name` "  
				  +" FROM  `px_class` pc "
				  +" LEFT JOIN `px_user` pu "
				  +"ON  pc.`teacher_id`=pu.`id` "
				  +"LEFT JOIN `px_project` pp  "
				  +" ON   pc.`project_id`=pp.`id` " 
				  +"LEFT JOIN `px_department` pd "
				  +" ON   pu.`department_id`=pd.`id` " 
				    +"WHERE  pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
				  +" AND  pc.`teacher_type`=1) AS temp ";
		return getJdbcTemplate().queryForLong(sql) ;
	}
    /**
     * 根据人员姓名查询出所有的内部讲师明细记录数
     * @param name
     * @return
     */
	public long getCountByName(String name,String year,long start,int pageSize) {
		String sql=" SELECT COUNT(*) "
				  +"FROM ( "
				  +"SELECT pc.`teacher_name`,pd.`name` "  
				  +" FROM  `px_class` pc "
				  +" LEFT JOIN `px_user` pu "
				  +"ON  pc.`teacher_id`=pu.`id` "
				  +"LEFT JOIN `px_project` pp  "
				  +" ON   pc.`project_id`=pp.`id` " 
				  +"LEFT JOIN `px_department` pd "
				  +" ON   pu.`department_id`=pd.`id` " 
				  +"WHERE pc.`teacher_name` LIKE '%"+name+"%' "
				    +"AND   pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
				  +" AND  pc.`teacher_type`=1) AS temp ";
				  
		 System.out.println("sql:"+sql);
		return getJdbcTemplate().queryForLong(sql) ;
		 
	}
	
	/**
	 * 在此年内部讲师明细的总记录数
	 * @return
	 */
	public long getCountInYear(String year){
	  
		String sql=" SELECT  COUNT(*) "
				  + "FROM (SELECT pc.`teacher_name` AS teacherName"
				        +" FROM  `px_class` pc "  
		                +" LEFT JOIN `px_user` pu "   
		                +" ON  pc.`teacher_id`=pu.`id`"  
		                +" LEFT JOIN `px_project` pp  "
		                +" ON   pc.`project_id`=pp.`id`"  
		                +" LEFT JOIN `px_department` pd "  
		                +"ON   pu.`department_id`=pd.`id`"    
		                +" WHERE   pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59'"  
		                +"  AND  pc.`teacher_type`=1) temp " ;
				             
		return getJdbcTemplate().queryForLong(sql) ;
	}
    /**
     *根据公司单位查询出内部讲师的明细
     * @param start
     * @param pageSize
     * @return
     */
	public List<TeacherInDetailModel> getTeacherInDetailByCompany(String company,long start,
			int pageSize,String year) {
	
		String sql="SELECT pc.`teacher_name` AS teacherName, "
				          + " pd.`parent_name` AS parentDepartment, "
				          + "pd.`name` AS department, "
				          + "pp.`name` AS projectName , "
				          + "pp.`begin_time` AS projectStartTime, "
				          + "pp.`end_time` AS projectEndTime, "
				          + "pc.`name` AS NAME, "
				          + "pc.`begin_time` AS beginTime, "
				          + "pc.`end_time` AS endTime, "
				          + "pp.`project_level` AS projectLevel, "
				          + "pc.`class_hour` AS classHour, "
				          + "pc.`pay_class_score`  AS  payClassScore , "
				          + "pc.`teacher_score` AS teacherScore "
				          +" FROM  `px_class` pc " 
				          +" LEFT JOIN `px_user` pu "  
				          +" ON  pc.`teacher_id`=pu.`id` "
				          +" LEFT JOIN `px_project` pp "
				          +" ON   pc.`project_id`=pp.`id` "
				          +" LEFT JOIN `px_department` pd  "
				          +" ON   pu.`department_id`=pd.`id` "  
				          +" WHERE pd.`parent_name` LIKE '%"+company+"%' " 
				            +" AND   pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
				          +"  AND  pc.`teacher_type`=1 "
				          +" LIMIT "+start+","+pageSize+" ";
		
				 @SuppressWarnings("unchecked")
				List<TeacherInDetailModel> results=	getJdbcTemplate().query(sql,new TeacherInDetailRowMapper());
				 System.out.println("results:"+results);
				 return results;
	}
	 
	
	/**
	 * 根据人员姓名查询出所有的内部讲师明细
	 * @param name
	 * @param start
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TeacherInDetailModel> getTeacherInDetailByName(String name,
			long start, int pageSize,String year) {
		String sql="SELECT pc.`teacher_name` AS teacherName, "
		          + " pd.`parent_name` AS parentDepartment, "
		          + "pd.`name` AS department, "
		          + "pp.`name` AS projectName , "
		          + "pp.`begin_time` AS projectStartTime, "
		          + "pp.`end_time` AS projectEndTime, "
		          + "pc.`name` AS NAME, "
		          + "pc.`begin_time` AS beginTime, "
		          + "pc.`end_time` AS endTime, "
		          + "pp.`project_level` AS projectLevel, "
		          + "pc.`class_hour` AS classHour, "
		          + "pc.`pay_class_score`  AS  payClassScore , "
		          + "pc.`teacher_score` AS teacherScore "
		          +" FROM  `px_class` pc " 
		          +" LEFT JOIN `px_user` pu "  
		          +" ON  pc.`teacher_id`=pu.`id` "
		          +" LEFT JOIN `px_project` pp "
		          +" ON   pc.`project_id`=pp.`id` "
		          +" LEFT JOIN `px_department` pd  "
		          +" ON   pu.`department_id`=pd.`id` "  
		          +" WHERE pc.`teacher_name` LIKE '%"+name+"%' "
		            +" AND   pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
		          +"  AND  pc.`teacher_type`=1 "
		          +" LIMIT "+start+","+pageSize+" ";
return getJdbcTemplate().query(sql,new TeacherInDetailRowMapper());
	}


/**
 * 按姓名降序排序	内部讲师明细表
 * @param start
 * @param pageSize
 * @return
 */
public List<TeacherInDetailModel> getTeacherInDetailByNameOrderDESC(long start,
			int pageSize,String year) {
	 
	String sql="SELECT pc.`teacher_name` AS teacherName, "
	          + " pd.`parent_name` AS parentDepartment, "
	          + "pd.`name` AS department, "
	          + "pp.`name` AS projectName , "
	          + "pp.`begin_time` AS projectStartTime, "
	          + "pp.`end_time` AS projectEndTime, "
	          + "pc.`name` AS NAME, "
	          + "pc.`begin_time` AS beginTime, "
	          + "pc.`end_time` AS endTime, "
	          + "pp.`project_level` AS projectLevel, "
	          + "pc.`class_hour` AS classHour, "
	          + "pc.`pay_class_score`  AS  payClassScore , "
	          + "pc.`teacher_score` AS teacherScore "
	          +" FROM  `px_class` pc " 
	          +" LEFT JOIN `px_user` pu "  
	          +" ON  pc.`teacher_id`=pu.`id` "
	          +" LEFT JOIN `px_project` pp "
	          +" ON   pc.`project_id`=pp.`id` "
	          +" LEFT JOIN `px_department` pd  "
	          +" ON   pu.`department_id`=pd.`id` "  
	          +"WHERE pc.`teacher_type`=1 "
	          +"  AND  pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
	          +"ORDER BY pc.`teacher_name` DESC "  
	          +" LIMIT "+start+","+pageSize+" ";
	 
	return getJdbcTemplate().query(sql,new TeacherInDetailRowMapper());
	}

/**
 * 按姓名升序排序	内部讲师明细表
 * @param start
 * @param pageSize
 * @return
 */
public List<TeacherInDetailModel> getTeacherInDetailByNameOrderASC(long start,
			int pageSize,String year) {
	 
	String sql="SELECT pc.`teacher_name` AS teacherName, "
	          + " pd.`parent_name` AS parentDepartment, "
	          + "pd.`name` AS department, "
	          + "pp.`name` AS projectName , "
	          + "pp.`begin_time` AS projectStartTime, "
	          + "pp.`end_time` AS projectEndTime, "
	          + "pc.`name` AS NAME, "
	          + "pc.`begin_time` AS beginTime, "
	          + "pc.`end_time` AS endTime, "
	          + "pp.`project_level` AS projectLevel, "
	          + "pc.`class_hour` AS classHour, "
	          + "pc.`pay_class_score`  AS  payClassScore , "
	          + "pc.`teacher_score` AS teacherScore "
	          +" FROM  `px_class` pc " 
	          +" LEFT JOIN `px_user` pu "  
	          +" ON  pc.`teacher_id`=pu.`id` "
	          +" LEFT JOIN `px_project` pp "
	          +" ON   pc.`project_id`=pp.`id` "
	          +" LEFT JOIN `px_department` pd  "
	          +" ON   pu.`department_id`=pd.`id` "  
	          +"WHERE pc.`teacher_type`=1 "
	          +"  AND  pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
	          +"ORDER BY pc.`teacher_name` ASC "  
	          +" LIMIT "+start+","+pageSize+" ";
	 
	return getJdbcTemplate().query(sql,new TeacherInDetailRowMapper());
	}

/** 
 * 按项目开始时间排序
 * @param start
 * @param pageSize
 * @return
 */
public List<TeacherInDetailModel> getTeacherInDetailByProjectBeginTimeOrder(
		long start, int pageSize,String year) {
	String sql="SELECT pc.`teacher_name` AS teacherName, "
	          + " pd.`parent_name` AS parentDepartment, "
	          + "pd.`name` AS department, "
	          + "pp.`name` AS projectName , "
	          + "pp.`begin_time` AS projectStartTime, "
	          + "pp.`end_time` AS projectEndTime, "
	          + "pc.`name` AS NAME, "
	          + "pc.`begin_time` AS beginTime, "
	          + "pc.`end_time` AS endTime, "
	          + "pp.`project_level` AS projectLevel, "
	          + "pc.`class_hour` AS classHour, "
	          + "pc.`pay_class_score`  AS  payClassScore , "
	          + "pc.`teacher_score` AS teacherScore "
	          +" FROM  `px_class` pc " 
	          +" LEFT JOIN `px_user` pu "  
	          +" ON  pc.`teacher_id`=pu.`id` "
	          +" LEFT JOIN `px_project` pp "
	          +" ON   pc.`project_id`=pp.`id` "
	          +" LEFT JOIN `px_department` pd  "
	          +" ON   pu.`department_id`=pd.`id` "  
	          +"WHERE pc.`teacher_type`=1 "
	          +"  AND  pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
	          +"ORDER BY pp.`begin_time` DESC "  
	          +" LIMIT "+start+","+pageSize+" ";
	return getJdbcTemplate().query(sql,new TeacherInDetailRowMapper());
}

/**
 * 按项目时间升序排序 内部讲师明细表。
 * @param start
 * @param length
 * @param year
 * @return
 */
public List<TeacherInDetailModel> getTeacherInDetailByProjectBeginTimeOrderAsc(
		long start, int pageSize, String year) {
	String sql="SELECT pc.`teacher_name` AS teacherName, "
	          + " pd.`parent_name` AS parentDepartment, "
	          + "pd.`name` AS department, "
	          + "pp.`name` AS projectName , "
	          + "pp.`begin_time` AS projectStartTime, "
	          + "pp.`end_time` AS projectEndTime, "
	          + "pc.`name` AS NAME, "
	          + "pc.`begin_time` AS beginTime, "
	          + "pc.`end_time` AS endTime, "
	          + "pp.`project_level` AS projectLevel, "
	          + "pc.`class_hour` AS classHour, "
	          + "pc.`pay_class_score`  AS  payClassScore , "
	          + "pc.`teacher_score` AS teacherScore "
	          +" FROM  `px_class` pc " 
	          +" LEFT JOIN `px_user` pu "  
	          +" ON  pc.`teacher_id`=pu.`id` "
	          +" LEFT JOIN `px_project` pp "
	          +" ON   pc.`project_id`=pp.`id` "
	          +" LEFT JOIN `px_department` pd  "
	          +" ON   pu.`department_id`=pd.`id` "  
	          +"WHERE pc.`teacher_type`=1 "
	          +"  AND  pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
	          +"ORDER BY pp.`begin_time`"  
	          +" LIMIT "+start+","+pageSize+" ";
	return getJdbcTemplate().query(sql,new TeacherInDetailRowMapper());
}
/**
 * 在此年份的所内部讲师明细
 * @param start
 * @param pageSize
 * @param year
 * @return
 */
public List<TeacherInDetailModel> getTeacherInDetailInYear(long start,
		int pageSize, String year) {
	String sql="SELECT pc.`teacher_name` AS teacherName, "
	          + " pd.`parent_name` AS parentDepartment, "
	          + "pd.`name` AS department, "
	          + "pp.`name` AS projectName , "
	          + "pp.`begin_time` AS projectStartTime, "
	          + "pp.`end_time` AS projectEndTime, "
	          + "pc.`name` AS NAME, "
	          + "pc.`begin_time` AS beginTime, "
	          + "pc.`end_time` AS endTime, "
	          + "pp.`project_level` AS projectLevel, "
	          + "pc.`class_hour` AS classHour, "
	          + "pc.`pay_class_score`  AS  payClassScore , "
	          + "pc.`teacher_score` AS teacherScore "
	          +" FROM  `px_class` pc " 
	          +" LEFT JOIN `px_user` pu "  
	          +" ON  pc.`teacher_id`=pu.`id` "
	          +" LEFT JOIN `px_project` pp "
	          +" ON   pc.`project_id`=pp.`id` "
	          +" LEFT JOIN `px_department` pd  "
	          +" ON   pu.`department_id`=pd.`id` "  
	          +"  WHERE   pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
	          +"  AND  pc.`teacher_type`=1 "
	          +" LIMIT "+start+","+pageSize+" ";
	 
return getJdbcTemplate().query(sql,new TeacherInDetailRowMapper());
	 
}

/**
 *  根据单位和公司查询出所有的 内部讲师明细数据
 * @param company
 * @param name
 * @param start
 * @param pageSize
 * @param year
 * @return
 */
public List<TeacherInDetailModel> TeacherInDetailInByCompanyAndName(
		String company, String name, long start, int pageSize, String year) {
	String sql="SELECT pc.`teacher_name` AS teacherName, "
	          + " pd.`parent_name` AS parentDepartment, "
	          + "pd.`name` AS department, "
	          + "pp.`name` AS projectName , "
	          + "pp.`begin_time` AS projectStartTime, "
	          + "pp.`end_time` AS projectEndTime, "
	          + "pc.`name` AS NAME, "
	          + "pc.`begin_time` AS beginTime, "
	          + "pc.`end_time` AS endTime, "
	          + "pp.`project_level` AS projectLevel, "
	          + "pc.`class_hour` AS classHour, "
	          + "pc.`pay_class_score`  AS  payClassScore , "
	          + "pc.`teacher_score` AS teacherScore "
	          +" FROM  `px_class` pc " 
	          +" LEFT JOIN `px_user` pu "  
	          +" ON  pc.`teacher_id`=pu.`id` "
	          +" LEFT JOIN `px_project` pp "
	          +" ON   pc.`project_id`=pp.`id` "
	          +" LEFT JOIN `px_department` pd  "
	          +" ON   pu.`department_id`=pd.`id` "  
	          +"  WHERE   pp.`begin_time` BETWEEN '"+year+"-01-01 00:00:00' AND '"+year+"-12-31 23:59:59' " 
	          +" AND pc.`teacher_name` LIKE '%"+name+"%' "
	          +" AND pd.`parent_name` LIKE '%"+company+"%' " 
	          +"  AND  pc.`teacher_type`=1 "
	          +" LIMIT "+start+","+pageSize+" ";
return getJdbcTemplate().query(sql,new TeacherInDetailRowMapper());
}
 /**
  * 根据指定的年限查出所有的内部讲师汇总
  * @param start
  * @param pageSize
  * @param year
  * @return
  */
public List<TeacherInSumModel> getTeacherInSumInYear(long start, int pageSize, String year) {
	 
	String sql=" SELECT temp.teacherName, "
			   + "temp.parentName, "
	           + "temp.departmentName, "
	           + " SUM(temp.teacher_score) AS sum_teacher_score, "
	           + " SUM(temp.pay_class_score) AS sum_pay_class_score, "
	           + "SUM(temp.class_hour) AS sum_class_hour  "
	           + " FROM  (SELECT  pc.`teacher_name` AS teacherName , "       
	                          + "pd.`parent_name` AS parentName , "
                              + "pd.`name` AS departmentName, "
                              + "pc.`class_hour`, "
                              + "pc.`pay_class_score`, "
                              + "pc.`teacher_score` "
                              + " FROM `px_class` pc "
                              +" LEFT JOIN `px_project` pp "
                              +" ON pc. `project_id`=pp.id "
                              +" LEFT JOIN `px_user` pu "
                              +"ON pc.`teacher_id`=pu.`id` "
                              +"LEFT JOIN `px_department` pd "
                              +"ON  pu.`department_id`=pd.`id` "
                              +"WHERE pc.`teacher_type`=1 "
                              +"AND  pp. `begin_time` "
                              + "  BETWEEN '"+year+"-01-01' AND '"+year+"-12-31') AS temp "           
                     +" GROUP BY temp.teacherName "
                     +" LIMIT "+start+","+pageSize+" ";                  ;
	return getJdbcTemplate().query(sql,new TeacherInSumRowMapper());

}  
 
/**
 * 根据单位获取所有的内部讲师汇总数据
 * @param company
 * @param start
 * @param pageSize
 * @param year
 * @return
 */
public List<TeacherInSumModel> getTeacherInSumByCompany(String company,
		long start, int pageSize, String year) {
	 
	String sql=" SELECT temp.teacherName, "
			   + "temp.parentName, "
	           + "temp.departmentName, "
	           + " SUM(temp.teacher_score) AS sum_teacher_score, "
	           + " SUM(temp.pay_class_score) AS sum_pay_class_score, "
	           + "SUM(temp.class_hour) AS sum_class_hour  "
	           + " FROM  (SELECT  pc.`teacher_name` AS teacherName , "       
	                          + "pd.`parent_name` AS parentName , "
                           + "pd.`name` AS departmentName, "
                           + "pc.`class_hour`, "
                           + "pc.`pay_class_score`, "
                           + "pc.`teacher_score` "
                           + " FROM `px_class` pc "
                           +" LEFT JOIN `px_project` pp "
                           +" ON pc. `project_id`=pp.id "
                           +" LEFT JOIN `px_user` pu "
                           +"ON pc.`teacher_id`=pu.`id` "
                           +"LEFT JOIN `px_department` pd "
                           +"ON  pu.`department_id`=pd.`id` "
                           +"WHERE pc.`teacher_type`=1 "
                           +"AND  pd.`parent_name` LIKE '%"+company+"%' "
                           +" AND pp.`begin_time` "
                           + " BETWEEN '"+year+"-01-01' AND '"+year+"-12-31') AS temp "           
                  +" GROUP BY temp.teacherName "
                  +" LIMIT "+start+","+pageSize+" ";                  
	return getJdbcTemplate().query(sql,new TeacherInSumRowMapper());
}

/**
 * 根据讲师姓名查询出所有的内部讲师汇总数据
 * @param name
 * @param start
 * @param pageSize
 * @param year
 * @return
 */
public List<TeacherInSumModel> getTeacherInSumByName(String name,
		long start, int pageSize, String year) {
	 
	String sql=" SELECT temp.teacherName, "
			   + "temp.parentName, "
	           + "temp.departmentName, "
	           + " SUM(temp.teacher_score) AS sum_teacher_score, "
	           + " SUM(temp.pay_class_score) AS sum_pay_class_score, "
	           + "SUM(temp.class_hour) AS sum_class_hour  "
	           + " FROM  (SELECT  pc.`teacher_name` AS teacherName , "       
	                          + "pd.`parent_name` AS parentName , "
                        + "pd.`name` AS departmentName, "
                        + "pc.`class_hour`, "
                        + "pc.`pay_class_score`, "
                        + "pc.`teacher_score` "
                        + " FROM `px_class` pc "
                        +" LEFT JOIN `px_project` pp "
                        +" ON pc. `project_id`=pp.id "
                        +" LEFT JOIN `px_user` pu "
                        +"ON pc.`teacher_id`=pu.`id` "
                        +"LEFT JOIN `px_department` pd "
                        +"ON  pu.`department_id`=pd.`id` "
                        +"WHERE pc.`teacher_type`=1 "
                        +"AND  pc.`teacher_name` LIKE '%"+name+"%' "
                        +" AND pp.`begin_time` "
                        + " BETWEEN '"+year+"-01-01' AND '"+year+"-12-31') AS temp "           
               +" GROUP BY temp.teacherName "
               +" LIMIT "+start+","+pageSize+" ";                  
	return getJdbcTemplate().query(sql,new TeacherInSumRowMapper());
}

/**
 *根据姓名和单位查询出所有内部讲师汇总数据
 * @param name
 * @param start
 * @param pageSize
 * @param year
 * @return
 */
public List<TeacherInSumModel> getTeacherInSumByNameAndCompany(String name,
		String company,long start, int pageSize, String year) {
	String sql=" SELECT temp.teacherName, "
			   + "temp.parentName, "
	           + "temp.departmentName, "
	           + " SUM(temp.teacher_score) AS sum_teacher_score, "
	           + " SUM(temp.pay_class_score) AS sum_pay_class_score, "
	           + "SUM(temp.class_hour) AS sum_class_hour  "
	           + " FROM  (SELECT  pc.`teacher_name` AS teacherName , "       
	                          + "pd.`parent_name` AS parentName , "
                     + "pd.`name` AS departmentName, "
                     + "pc.`class_hour`, "
                     + "pc.`pay_class_score`, "
                     + "pc.`teacher_score` "
                     + " FROM `px_class` pc "
                     +" LEFT JOIN `px_project` pp "
                     +" ON pc. `project_id`=pp.id "
                     +" LEFT JOIN `px_user` pu "
                     +"ON pc.`teacher_id`=pu.`id` "
                     +"LEFT JOIN `px_department` pd "
                     +"ON  pu.`department_id`=pd.`id` "
                     +"WHERE pc.`teacher_type`=1 "
                     +"AND  pc.`teacher_name` LIKE '%"+name+"%' "
                       +"AND  pd.`parent_name` LIKE '%"+company+"%' "
                     +" AND pp.`begin_time` "
                     + " BETWEEN '"+year+"-01-01' AND '"+year+"-12-31') AS temp "           
            +" GROUP BY temp.teacherName "
            +" LIMIT "+start+","+pageSize+" ";                  
	return getJdbcTemplate().query(sql,new TeacherInSumRowMapper());
}


    //从数据库中查询出的数据封装到TeacherInDetailModel 对象中并返回
	class  TeacherInDetailRowMapper  implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		  
			TeacherInDetailModel teacherInDetailModel =new TeacherInDetailModel ();
 
		
			teacherInDetailModel.setName(rs.getString("NAME"));
			teacherInDetailModel.setTeacherName(rs.getString("teacherName"));     
			teacherInDetailModel.setBeginTime(rs.getDate("beginTime"));
			teacherInDetailModel.setEndTime(rs.getDate("endTime"));
/*			teacherInDetailModel.setTeacherType(rs.getInt("teacherType"));*/
			teacherInDetailModel.setClassHour(rs.getDouble("classHour"));
			teacherInDetailModel.setTeacherScore(rs.getDouble("teacherScore"));
			teacherInDetailModel.setPayClassScore(rs.getDouble("payClassScore"));
			teacherInDetailModel.setProjectName(rs.getString("projectName"));
			teacherInDetailModel.setProjectStartTime(rs.getDate("projectStartTime") );
			teacherInDetailModel.setProjectEndTime(rs.getDate("projectStartTime") );
			teacherInDetailModel.setProjectLevel(rs.getInt("projectLevel"));
			teacherInDetailModel.setParentDepartment(rs.getString("parentDepartment"));
			teacherInDetailModel.setDepartment(rs.getString("department"));
			
			return teacherInDetailModel;
		}
		
	}

     //从数据库中查询的数据封装到TeacherInSumModel 对象中
	class TeacherInSumRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			 
			TeacherInSumModel teacherInSumModel=new TeacherInSumModel();
			  
			teacherInSumModel.setName(rs.getString("teacherName"));
			teacherInSumModel.setParentDepartment(rs.getString("parentName") );
			teacherInSumModel.setDepartment(rs.getString("departmentName") ); 
			teacherInSumModel.setClassScoreYear(rs.getDouble("sum_class_hour"));
			teacherInSumModel.setPayScoreYear(rs.getDouble("sum_pay_class_score") );
			teacherInSumModel.setSumScoreYear(rs.getDouble("sum_teacher_score") );
			
			return teacherInSumModel;
		}
 
}

	 

}

 
