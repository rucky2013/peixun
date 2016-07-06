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
<title>登录页</title>
</head>

<body>
	<div class="page-container">
	<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>
						  外部讲师管理
					</h1>
				</div>
				 
			</div>
		</div>
		<div class="page-content">
			<div class="container">
				<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="${ctx}/tohomepage">首页</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					外部讲师管理
				</li>
			</ul>
				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
		 
						<div class="row">
							<form class="form-horizontal"   id="searchForm">
								<div class="portlet-body form">
									<!-- BEGIN FORM-->
 									<div class="row container">
										<div class="form-body col-md-11 content">
											<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-3 control-label">单位</label>
													<div class="col-md-9">
														<input type="text" class="form-control " placeholder="讲师单位" name ="company" id="searchCompany">
													</div>
												</div>
											</div>
						 							<div class="col-md-4">
												<div class="form-group">
													<label class="col-md-3 control-label">姓名</label>
													<div class="col-md-9">
														<input type="text" name="teacherName" placeholder="讲师姓名" class="form-control " id="searchName">
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
										
										 <th>公司(单位)</th>
											<th>姓名</th>
											<th>简介</th>
											<th>联系方式</th>
											<th>课程</th>
											<th>职称</th>
											<th>地址</th>
										    
								          
											  <th id="caozuo_id">操作</th>  
										</tr>
									</thead>
									<tbody id="tbodyList">
									<shiro:user>
									    <shiro:hasRole name="admin">
											<input type="hidden"  id="teacherouterrole" value="admin"/>
										</shiro:hasRole>
								      </shiro:user>
									<shiro:user>
									    <shiro:hasRole name="user">
										    <input type="hidden" id="teacherouterrole" value="user"/>
										</shiro:hasRole>
									</shiro:user>
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
		<%@ include file="/WEB-INF/views/teacher/addteacherouter.jsp"%>
	</div>
	<script>
		/*Javascript代码片段*/
		$(function() {
			FormValidation.init();
			/* var userType = $("#teacherouterrole").val();
			if(userType=="user"){
				$("#caozuo_id").hide();
			}else{
				$("#caozuo_id").show();
			} */
		});
	</script>
	<script
		src="${ctx}/static/metronic/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script <script src="${ctx}/static/js/teacherout/teacheroutList.js"></script>
	<script src="${ctx}/static/js/dateFormat.js"></script>
</body>
</html>
