package com.ync365.px.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

/**
 * 验证码登录认证Filter
 * 
 *
 */
public class CaptchaAuthenticationFilter extends FormAuthenticationFilter{
	
    
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response)
            throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        String url = this.getSuccessUrl();
        //抢票扫码需登录直接跳转到抢票页面
        CharSequence cs = "booking";
        /*if (savedRequest != null && savedRequest.getRequestUrl().contains(cs)) {
            savedRequest.getRequestUrl().split("booking/")
            url = savedRequest.getRequestUrl();
        }*/
        /*subject.getSession().setAttribute(SubjectUtil.CURRENT_USER, subject.getPrincipal());    //设置用户身份进session属性
         
        logger.info("用户 "+SubjectUtil.getShiroUser(subject).toString() + " 登陆成功");*/
         
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + url);    //页面跳转
        return false;
    }
	/**
	 * 重写父类方法，当登录失败将异常信息设置到request的attribute中
	 *//*
	@Override
	protected void setFailureAttribute(ServletRequest request,AuthenticationException ae) {
		if (ae instanceof IncorrectCredentialsException) {
			request.setAttribute(getFailureKeyAttribute(), "登录帐号密码不正确");
		}else if( ae instanceof CaptchaErrorException){ 
			request.setAttribute(getFailureKeyAttribute(), "验证码不正确");
		}else {
			request.setAttribute(getFailureKeyAttribute(), ae.getMessage());
		}
	}
	
	*//**
	 * 创建一个shiro的session,如果存在session就用现有的session，否则创建一个新的session
	 * 
	 * @return {@link Session}
	 *//*
	public static Session createSessionIfNull() {
		Session session = getSession();
		
		if (session == null) {
			session = getSession(true);
		}
		
		return session;
	}
	
	*//**
	 * 获取shiro的session
	 * 
	 * @return {@link Session}
	 *//*
	public static Session getSession() {
		return getSession(false);
	}
	
	*//**
	 * 
	 * 获取shiro的session
	 * 
	 * @param create true表示如果不存在，就创建，否则用现有的
	 * 
	 * @return {@link Session}
	 *//*
	public static Session getSession(boolean create) {
		return SecurityUtils.getSubject().getSession(create);
	}*/
	
	
}