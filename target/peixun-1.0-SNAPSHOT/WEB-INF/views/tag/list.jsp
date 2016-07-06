<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>课程管理</title>
</head>

<body>
	<div class="page-container">
		<!-- BEGIN PAGE HEAD -->
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>项目标签</h1>
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
					<li><a href="${ctx}/tohomepage">首页</a><i class="fa fa-circle"></i>
					</li>
					<li class="active">项目标签</li>
				</ul>
				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="row">
							<form class="form-horizontal" id="searchForm">
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
									<div class="row container">
										<div class="form-body col-md-11 content">
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-3 control-label">名称</label>
													<div class="col-md-9">
														<input type="text" class="form-control " id="searchName" />
													</div>
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-3 control-label">状态</label>
													<div class="col-md-9">
														<select class="form-control" name="isUse" id="isUse">
															<option></option>
															<option value='1'>生效</option>
															<option value='0'>失效</option>
														</select>
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
											<th>名称</th>
											<th>状态</th>
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
		<%@ include file="/WEB-INF/views/tag/add.jsp"%>
	</div>
	<script>
		/*Javascript代码片段*/
		$(function() {
			Layout.init(); // init current layout
		});
	</script>
	<script src="${ctx}/static/js/tag/tagList.js"></script>
	<script src="${ctx}/static/js/dateFormat.js"></script>
</body>
</html>
