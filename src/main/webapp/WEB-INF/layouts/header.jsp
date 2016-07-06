<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- BEGIN HEADER -->
<div class="page-header">
	<!-- BEGIN HEADER TOP -->
	<div class="page-header-top">
		<div class="container">
			<!-- BEGIN LOGO -->
			<div class="page-logo">
				<a href="${ctx }/tohomepage"><img
					src="${ctx}/static/metronic/theme/assets/admin/layout3/img/logo-default.png"
					alt="logo" class="logo-default"></a>
			</div>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
					
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown dropdown-user dropdown-dark"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-close-others="true"> 
							<shiro:user>
								<span class="username username-hide-mobile"><shiro:principal
										property="name" /></span>
							</shiro:user>
					</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<shiro:hasRole name="user">
							<li><a href="extra_profile.html"> <i class="icon-user"></i>
									我的课程
							</a></li>
							<li class="divider"></li>
							</shiro:hasRole>
							<li><a href="${ctx }/logout"> <i class="icon-key"></i>
									退出
							</a></li>
						</ul></li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
			</div>
			<!-- END TOP NAVIGATION MENU -->
		</div>
	</div>
	<!-- END HEADER TOP -->
	<!-- BEGIN HEADER MENU -->
	<div class="page-header-menu">
		<div class="container">
			<!-- BEGIN MEGA MENU -->
			<!-- DOC: Apply "hor-menu-light" class after the "hor-menu" class below to have a horizontal menu with white background -->
			<!-- DOC: Remove data-hover="dropdown" and data-close-others="true" attributes below to disable the dropdown opening on mouse hover -->
			<div class="hor-menu ">
				<shiro:user>
				<ul id="nav" class="nav navbar-nav">
					<li id="tohomepage" class=""><a href="${ctx }/tohomepage">首页</a></li>
					<li id="project" class="menu-dropdown mega-menu-dropdown "><a
						data-hover="megamenu-dropdown" data-close-others="true"
						data-toggle="dropdown" href="javascript:;" class="dropdown-toggle">
							项目 <i class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown-menu pull-left">
							<shiro:hasRole name="admin">
							<li><a href="${ctx }/project/list" class="iconify"> <i
									class="icon-list"></i> 项目管理
							</a></li>
							
							<li><a href="${ctx }/tag/list" class="iconify"> <i
									class="icon-tag"></i> 项目标签
							</a></li>
							</shiro:hasRole>
							<shiro:hasRole name="user">
							<li><a href="${ctx }/project/mylist" class="iconify"> <i
									class="icon-home"></i> 我的项目
							</a></li>
							<li><a href="${ctx }/myapplyproject/list" class="iconify"> <i
									class="icon-home"></i> 我的项目申请
							</a></li>
							</shiro:hasRole>
						</ul></li>
					<li id="score" class="menu-dropdown mega-menu-dropdown"><a
						data-hover="megamenu-dropdown" data-close-others="true"
						data-toggle="dropdown" href="javascript:;"> 分数 <i
							class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown-menu pull-left">
							<shiro:hasRole name="user">
							<li ><a href="${ctx }/score/my"><i
									class="icon-bar-chart"></i> 我的积分 </a></li>
							</shiro:hasRole>
							<shiro:hasRole name="admin">
							<li ><a href="${ctx }/score/all">
									<i class="icon-bar-chart"></i> 所有积分
							</a></li>
														<li ><a href="${ctx }/score/setting">
									<i class="icon-equalizer"></i> 积分设定
							</a></li>
							</shiro:hasRole>
						</ul></li>
						<shiro:hasRole name="admin">
						<li id="teacherouter" class="menu-dropdown classic-menu-dropdown "><a
							data-hover="megamenu-dropdown" data-close-others="true"
							data-toggle="dropdown" href="javascript:;"> 讲师 <i
								class="fa fa-angle-down"></i>
						</a>
							<ul class="dropdown-menu pull-left">
								<li class=" "><a
									href="${ctx }/teacherouter/teacherouterlist"> <i
										class="icon-users"></i> 外部讲师管理
								</a></li>
								<li class=" "><a
									href="${ctx }/teacherindetail/teacherindetailmy"> <i
										class="icon-user"></i> 内部讲师明细管理
								</a></li>
									<li class=" "><a
										href="${ctx }/teacherinsum/teacherinsummy"> <i
											class="icon-users"></i> 内部讲师汇总管理
									</a></li>
							</ul></li>
						</shiro:hasRole>
				</ul>
				</shiro:user>
			</div>
			<!-- END MEGA MENU -->
		</div>
	</div>
	<!-- END HEADER MENU -->
</div>
<!-- END HEADER -->
<%
	String[] roots = request.getContextPath().split("/");
	String root = "";
	String path = "";
	String[] uris = request.getRequestURI().split("/");
	if (null != roots && roots.length == 2) {
		root = roots[1];
	}
	for (int i = 0; i < uris.length; i++) {
		String uri = uris[i];
		if (null != uri && uri.equals(root)) {
			if((i + 1) >= uris.length){
				path = "";
			} else {
				path = uris[i + 1];
			}
			
			break;
		}
	}
%>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		
		var currentPath = "<%=path%>";
		$('#nav > li').each(function(index, element) {
			$(element).removeClass('active');
		});
		if(currentPath == "tohomepage"){
			$("#tohomepage").addClass("active");
		} else if (currentPath == "project" || currentPath == "tag"){
			$("#project").addClass("active");
		} else if (currentPath == "score"){
			$("#score").addClass("active");
		} else if (currentPath == "teacherouter" || currentPath == "teacherindetail" || currentPath == "teacherinsum"){
			$("#teacherouter").addClass("active");
		}
		
	});
</script>

