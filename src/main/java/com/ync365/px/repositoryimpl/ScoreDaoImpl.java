package com.ync365.px.repositoryimpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ync365.px.model.Paginator;
import com.ync365.px.model.ScoreModel;
import com.ync365.px.model.ScoreYearModel;
import com.ync365.px.utils.PackModelUtil;
import com.ync365.px.utils.ResultSetUtils;

public class ScoreDaoImpl {

	private Logger logger = LoggerFactory.getLogger(ScoreDaoImpl.class);
	@PersistenceContext
	private EntityManager em;
	
	public Long countScore() {
		String sql = "select count(1) as count"
						+" from px_user u"
						+" LEFT JOIN px_department dep on u.department_id = dep.id"
						+" LEFT JOIN px_project_student ps on ps.user_id = u.id"
						+" LEFT JOIN px_score s on s.project_student_id = ps.id"
						+" LEFT JOIN px_project p on ps.project_id = p.id";
		logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		Map map = (Map) q.getResultList().get(0);
		return ResultSetUtils.null2Long(String.valueOf(map.get("count")));
		
	}
	
	public List<ScoreModel> getScore(Paginator paginator) {
		String sql = "select u.id as u_id,"
						+"u.name as u_name,"
						+"dep.id as dep_id,"
						+"dep.name as dep_name,"
						+"dep.parent_name as dep_parent_name,"
						+"ps.id as ps_id,"
						+"p.id as p_id,"
						+"p.project_level as p_project_level,"
						+"p.end_time as p_end_time,"
						+"s.id as s_id,"
						+"s.score as s_score"
						+" from px_user u"
						+" LEFT JOIN px_department dep on u.department_id = dep.id"
						+" LEFT JOIN px_project_student ps on ps.user_id = u.id"
						+" LEFT JOIN px_score s on s.project_student_id = ps.id"
						+" LEFT JOIN px_project p on ps.project_id = p.id"
						+" WHERE u.id in (select * from (select id from px_user limit "+paginator.getStart()+","+paginator.getLength()+") as ids)";
		logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		return PackModelUtil.packScoreModelFormNativeSql(q.getResultList());
		
	}
	
	
	
	public Long countScoreYear(String userid,String username,String year) {
		
		String sql = "select count(1) as count from px_user as u where 1=1";
		
		if (StringUtils.isNotEmpty(userid)) {
			sql += " and u.id = '" + userid + "'";
		}
		
		if (StringUtils.isNotEmpty(username)) {
			sql += " and u.name like '%" + username + "%" + "'";
		}
	    
		logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		Map map = (Map) q.getResultList().get(0);
		return ResultSetUtils.null2Long(String.valueOf(map.get("count")));
		
	}
	
	public List<ScoreYearModel> getScoreYear(Paginator paginator,String userid,String username,String year) {
		
		String subsql = "select id from px_user as u where 1=1";
		
		if (StringUtils.isNotEmpty(userid)) {
			subsql += " and u.id = '" + userid + "'";
		}
		
		if (StringUtils.isNotEmpty(username)) {
			subsql += " and u.name like '%" + username + "%" + "'";
		}
		
		subsql += " limit " + paginator.getStart() + "," + paginator.getLength();
		
		String sql = "select u.id as u_id,"
						+"u.name as u_name,"
						+"dep.id as dep_id,"
						+"dep.name as dep_name,"
						+"dep.parent_name as dep_parent_name,"
						+"ps.id as ps_id,"
						+"ps.period as ps_period,"
						+"p.id as p_id,"
						+"p.project_level as p_project_level,"
						+"p.begin_time as p_begin_time,"
						+"p.end_time as p_end_time,"
						+"p.name as p_name,"
						+"ss.*,"
						+"c.id as c_id,"
						+"c.teacher_type as c_teacher_type,"
						+"c.teacher_name as c_teacher_name,"
						+"c.teacher_id as c_teacher_id,"
						+"c.begin_time as c_begin_time,"
						+"c.end_time as c_end_time,"
						+"c.class_hour as c_class_hour,"
						+"c.sclass_hour as c_sclass_hour,"
						+"c.teacher_score as c_teacher_score,"
						+"c.steacher_score as c_steacher_score,"
						+"c.name as c_name,"
						+"cs.reals as cs_reals,"
						+"cs.should as cs_should,"
						+"cs.s_score as cs_sscore,"
						+"cs.score as cs_score"
						+" from px_user u"
						+" LEFT JOIN px_department dep on u.department_id = dep.id"
						+" LEFT JOIN px_project_student ps on ps.user_id = u.id"
						+" LEFT JOIN px_score_setting ss on ss.userid = u.id and ss.year = '" + year + "'"
						+" LEFT JOIN px_project p on ps.project_id = p.id and p.status = '5'"
						+" LEFT JOIN px_class c on c.project_id = p.id"
						+" LEFT JOIN px_class_student cs on cs.class_id = c.id and cs.user_id = u.id"
						+" WHERE u.id in (select * from ("+ subsql +") as ids )";

		if (StringUtils.isNotEmpty(year)) {
			String yearStart = year + "-01-01";
			String yearEnd = year + "-12-31";
			sql += " and (p.end_time between '" + yearStart + "' and '" + yearEnd + "' or p.end_time is null)";
		}
		logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		return PackModelUtil.packScoreModelYearFormNativeSql(q.getResultList());
		
	}
}
