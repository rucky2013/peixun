package com.ync365.px.repositoryimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ync365.px.entity.User;
import com.ync365.px.utils.PackModelUtil;

public class UserDaoImpl {

	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	@PersistenceContext
	private EntityManager em;
	
	public List<User> getAllUser() {
		
		String sql = "select u.id as u_id"
				+ ",u.name as u_name"
				+ ",u.login_name as u_login_name"
				+ ",u.oaid as u_oaid "
				+ ",u.department_id as u_department_id"
				+ ",u.status as u_status"
				+ ",u.password as u_password"
				+ ",dep.name as dep_name"
				+ ",dep.parent_name as dep_parent_name"
				+ "  from px_user as u"
				+ " left join px_department as dep on u.department_id = dep.id"
				+ " where 1=1 and u.status = '0'";
		
		logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		return PackModelUtil.packUserModelFormNativeSql(q.getResultList());
		
	}
	
	
	
	public User getUser(Long userid) {
		String sql = "select u.id as u_id"
				+ ",u.name as u_name"
				+ ",u.login_name as u_login_name"
				+ ",u.oaid as u_oaid "
				+ ",u.department_id as u_department_id"
				+ ",u.status as u_status"
				+ ",u.password as u_password"
				+ ",dep.name as dep_name"
				+ ",dep.parent_name as dep_parent_name"
				+ "  from px_user as u"
				+ " left join px_department as dep on u.department_id = dep.id"
				+ " where 1=1 and u.status = '0' and u.id = '" + userid + "'";
		
		logger.debug(sql);
		Query q = em.createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);  
		List<User> users  = PackModelUtil.packUserModelFormNativeSql(q.getResultList());
		if (null != users && users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
}
