package com.ync365.px.repositoryimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ync365.px.entity.TeacherOuter;

public class TeacherOuterDaoImpl extends JdbcDaoSupport {

	public TeacherOuterDaoImpl() {
		super();
		 
	}

	@Autowired
	public TeacherOuterDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	 
	/**
	 * 根据名字查询总记录数
	 * @param name
	 * @return
	 */
	public long getCountByName(String name) {
		String sql="SELECT COUNT(*)  "
				+ "FROM (SELECT *  "
				+ "FROM `px_teacher_outer` pto "
				+ "WHERE pto.`name` LIKE '%"+name+"%') AS temp ";
		return getJdbcTemplate().queryForLong(sql) ;
		 
	}
    public List<TeacherOuter> findTeacheroutByNameAll() {
        String sql="SELECT  `id`,`company`,`name`, "
                + "`introduce`,`phone`, "
                + "`subject`,`post`, "
                + "`address` "
                + "FROM  `px_teacher_outer` pto "
                + "ORDER BY pto.`name` ";
                return  getJdbcTemplate().query(sql,new TeacherOuterRowMapper());   
    }
	 /**
	  * 根据单位查询出总记录数
	  * @param company
	  * @return
	  */
	public long  getCountByCompany(String company){
		String sql="SELECT COUNT(*)  "
				+ "FROM (SELECT *  "
				+ "FROM `px_teacher_outer` pto "
				+ "WHERE pto.`company` LIKE '%"+company+"%') AS temp ";
		return getJdbcTemplate().queryForLong(sql) ;
	}
	/**
	 * 根据公司查询出所有的外部老师
	 * 
	 * @param company
	 * @return
	 */
	 
	public List<TeacherOuter> findTeacherOuterByCompany(String company,
			 long start,  int pageSizee){
		
		String sql="SELECT  `company`,`name`, "
				          + "`introduce`,`phone`, "
				          + "`subject`,`post`, "
				          + "`address` "
				          + "FROM  `px_teacher_outer` pto "
				          + "WHERE pto.`company` "
				          + "LIKE  '%"+company+"%' "
				          + "ORDER BY pto.`company` "
				          + "LIMIT "+start+","+pageSizee+" ";
	
		return  getJdbcTemplate().query(sql,new TeacherOuterRowMapper());	
	}
	
	/**
	 * 根据名称查询出所有的外部讲师
	 * @param name
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<TeacherOuter> getTeacherOutByName(String name, long start,
			int pageSize) {
		 
		String sql="SELECT  `company`,`name`, "
		          + "`introduce`,`phone`, "
		          + "`subject`,`post`, "
		          + "`address` "
		          + "FROM  `px_teacher_outer` pto "
		          + "WHERE pto.`name` "
		          + "LIKE  '%"+name+"%' "
		          + "ORDER BY pto.`name` "
		          + "LIMIT "+start+","+pageSize+" ";
		 
	return  getJdbcTemplate().query(sql,new TeacherOuterRowMapper());
	}
	/**
     * 根据名称查询出所有的外部讲师
     * @param name
     * @param start
     * @param pageSize
     * @return
     */
    public List<TeacherOuter> getTeacherOutAll() {
         
        String sql="SELECT `id`, `company`,`name`, "
                  + "`introduce`,`phone`, "
                  + "`subject`,`post`, "
                  + "`address` "
                  + "FROM  `px_teacher_outer` pto ";
         
    return  getJdbcTemplate().query(sql,new TeacherOuterRowMapper());
    }
	
	class  TeacherOuterRowMapper  implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			 TeacherOuter teacherOuter=new TeacherOuter();
			 teacherOuter.setCompany(rs.getString("company"));
			 teacherOuter.setTeacherName(rs.getString("name"));
			 teacherOuter.setIntroduce(rs.getString("introduce"));
			 teacherOuter.setPhone(rs.getString("phone"));
			 teacherOuter.setPost(rs.getString("post"));
			 teacherOuter.setAddress(rs.getString("address"));
			 teacherOuter.setSubject(rs.getString("subject"));
			 teacherOuter.setId(Long.valueOf(rs.getString("id")));
			return teacherOuter;
		}

	}

	

	
	}
