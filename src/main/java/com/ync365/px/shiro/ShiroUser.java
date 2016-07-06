package com.ync365.px.shiro;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;

import com.ync365.px.entity.SysResource;
import com.ync365.px.entity.User;


public class ShiroUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 当前用户
	private User user;
	// 当前用户的授权资源集合
	private List<SysResource> authorizationInfo;
	// 当前用户的菜单集合
	private List<Menu> menus;

	public ShiroUser() {

	}

	public ShiroUser(User user) {
		this.user = user;
	}
	public ShiroUser(User user,List<SysResource> authorizationInfo, List<Menu> menus) {
		this.user = user;
		this.authorizationInfo = authorizationInfo;
		this.menus = menus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Long getId(){
		return user.getId();
	}
	
	public List<SysResource> getAuthorizationInfo() {
		return authorizationInfo;
	}

	public void setAuthorizationInfo(List<SysResource> authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}



	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return user.getName();
	}
	public static void main(String[] args) {
		String temp = new SimpleHash("MD5","123456").toHex();
		System.out.println(temp);
	}

}
