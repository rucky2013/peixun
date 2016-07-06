/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ync365.px.web.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String login() {
		return "account/login_soft";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fail(HttpServletRequest request,HttpServletResponse response) {
	    String msg = (String)request.getAttribute("shiroLoginFailure");
        if(StringUtils.length(msg)>=15){//防止出现未捕获的错误信息，提示在页面
            request.setAttribute("errorMsg", "用户名或密码错误");
        }else{
            request.setAttribute("errorMsg", msg);
        }
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            response.setStatus(901);
            return "account/login_soft";
        }
		return "redirect:/tohomepage";
	}

}
