package com.ync365.px.shiro.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ync365.px.entity.User;
import com.ync365.px.service.account.ShiroDbRealm.ShiroUser;


/**
 *  系统安全用户工具类
 * @author
 *
 */
public class ShiroUserUtils {
	
	private static final Logger  logger  = LoggerFactory.getLogger(ShiroUserUtils.class);
	/**
	 * 获取当前ShiroUser
	 * @return
	 */
	public static ShiroUser getCurrentShiroUser(){
		Object obj =getSubject().getPrincipal();
		if(obj==null){
			return null;
		}
		return (ShiroUser)obj;
	}
	/**
	 * 获取当前系统用户
	 * @return
	 */
	public static ShiroUser getCurrentUser(){
	    ShiroUser shiroUser =getCurrentShiroUser();
		if(shiroUser ==null){
			return null;
		}
		return shiroUser;
	}
	
	public static Subject  getSubject(){
		return SecurityUtils.getSubject();
	}
	/**
	 * 判断当前用户否已经过身份验证
	 * @return true  已验证   false  未验证
	 */
	public static boolean isAuthenticated(){
		return getSubject().isAuthenticated();
	}
	public static  Session getSession(){
		return getSubject().getSession();
	}
	
	public static void clearCachedAuthenticationInfo(String loginName){
		if(StringUtils.isBlank(loginName)){
			return ;
		}
		try {
			RealmSecurityManager securityManager =  (RealmSecurityManager) SecurityUtils.getSecurityManager();  
			CacheManager manager =securityManager.getCacheManager();
			Cache<Object, Object> cache = manager.getCache("nslm_admin_shiroAuthenticationCache");
			cache.remove(loginName);
		} catch (UnavailableSecurityManagerException e) {
			logger.error("清除用户认证信息出错",e);
		} catch (CacheException e) {
			logger.error("清除用户认证信息出错",e);
		}
	}


}
