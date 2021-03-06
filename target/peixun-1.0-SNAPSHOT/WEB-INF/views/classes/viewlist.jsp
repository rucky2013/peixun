<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>查看课程</title>
</head>

<body>
	<div class="page-container">
			<!-- BEGIN PAGE HEAD -->
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>查看课程</h1>
				</div>
				<!-- END PAGE TITLE -->
			</div>
		</div>
		<!-- END PAGE HEAD -->
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content">
			<div class="container">
				<!-- BEGIN PAGE BREADCRUMB -->
				<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="${ctx}/tohomepage">首页</a><i class="fa fa-circle"></i>
				</li>
				<li>
					<a href="${ctx }/project/list">项目管理</a>
					<i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 查看课程
				</li>
			</ul>
				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="row">
							<form class="form-horizontal" id="searchForm">
								<input
										type="hidden" id="projectId" name="projectId"
										value="${projectId}" />
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
									<div class="row container">
										<div class="form-body col-md-11 content">
											<div class="col-md-4">
												<div class="form-group">
													<input type="hidden" id="requrl" value="${requrl}"/>
													<shiro:user><shiro:hasRole name="admin">
														<input type="hidden" id="userrole" value="admin"/>
													</shiro:hasRole></shiro:user>
													<shiro:user><shiro:hasRole name="user">
														<input type="hidden" id="userrole" value="user"/>
													</shiro:hasRole></shiro:user>
													<label class="col-md-3 control-label">课程名称</label>
													<div class="col-md-9">
														<input type="text" class="form-control " name="className" id="searchName"/>
														<input type="hidden" id="projectStatus" value="${projectStatus}"/>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
						<div>
							<div id="data_my" class="portlet-body">
								<table class="table table-striped table-bordered table-hover"
									id="datatable_ajax">
									<thead>
										<tr role="row">
											<th>课程名称</th>
											<th>授课教师</th>
											<th>课时</th>
											<th>课酬</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="tbodyList">
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>

				<!-- END PAGE CONTENT INNER -->
			</div>
		</div>
		<!-- END PAGE CONTENT -->
	</div>
	<script src="${ctx}/static/js/classes/viewclassesList.js"></script>
		<script src="${ctx}/static/js/dateFormat.js"></script>
</body>
</html>
