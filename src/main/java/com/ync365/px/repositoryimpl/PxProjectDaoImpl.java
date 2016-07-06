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

import com.ync365.px.entity.PxProject;
import com.ync365.px.model.Paginator;
import com.ync365.px.utils.PackModelUtil;
import com.ync365.px.utils.ResultSetUtils;

public class PxProjectDaoImpl {

	private Logger logger = LoggerFactory.getLogger(PxProjectDaoImpl.class);
	@PersistenceContext
	private EntityManager em;
	
	
	public Long countPro(int userid) {
		
		String sql = "select count(1) as count"
						+" from px_project_student u"
						+" WHERE u.user_id ="+ userid ;

		logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		Map map = (Map) q.getResultList().get(0);
		return ResultSetUtils.null2Long(String.valueOf(map.get("count")));
		
	}
	
	public List<PxProject> getMyPro(Paginator paginator,int userid) {
		
		
		String subsql = " limit " + paginator.getStart() + "," + paginator.getLength();
		
		String sql = "select p.name ,"
						+"p.begin_time,"
						+"p.end_time,"
						+"p.ticket_type,"
						+"p.status,"
						+"p.id,"
						+"p.project_level"
						+" from px_project_student u"
						+" LEFT JOIN px_project p on u.project_id = p.id"
						+" WHERE u.user_id ="+userid+subsql;

		logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		return PackModelUtil.packPxProjectModelFormNativeSql(q.getResultList());
		
	}

	/**
	 * 获取请求项目的总记录数
	 * @param userid
	 * @return
	 */
/*public Long myApplyProjectCount(int userid) {
		
		String sql = "SELECT COUNT(1) as count "
			 	   + " FROM `px_project`  pp "
			 	   + "WHERE  pp.`cuser_id`="+ userid ; 
	 	logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		Map map = (Map) q.getResultList().get(0);
		return ResultSetUtils.null2Long(String.valueOf(map.get("count")));
		
	}
	*/
/**
 * 根据createUserId获取所有的项目数据
 * @param paginator
 * @param userid
 * @return
 */
	/*public List<PxProject> getMyApplyProjectList(Paginator paginator,int userid) {
		
		
		String subsql = " limit " + paginator.getStart() + "," + paginator.getLength();
		
		String sql = "SELECT pp.id,  pp.`name`,pp.`begin_time`, "
				             + "pp.`end_time`,pp.`ticket_type`, "
				             + "pp.`project_level`,pp.`status`  "
				             + "FROM `px_project` pp   "
				             + "WHERE   pp.`cuser_id`="+userid+subsql; 
		logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		return PackModelUtil.packPxProjectModelFormNativeSql(q.getResultList());
		
	}*/
}
