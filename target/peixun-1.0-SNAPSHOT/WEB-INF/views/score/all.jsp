<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>登录页</title>
</head>

<body>
	<div class="page-container">
		<!-- BEGIN PAGE HEAD -->
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>
						所有积分
					</h1>
				</div>
		
			</div>
		</div>
		<div class="page-content">
			<div class="container">
				
				<ul class="page-breadcrumb breadcrumb">
					<li><a href="${ctx}">首页</a><i class="fa fa-circle"></i></li>
					<li class="active">所有积分</li>
				</ul>
	
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet light">
							
							<div class="portlet-body">
								<div class="table-toolbar">
									
								</div>
								<div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
									<div class="row">
										<div class="col-md-7 col-sm-12 text-left">
												<label>姓名<input id="search_name" type="search" class="form-control input-sm input-inline" aria-controls="sample_1"></label>
												<button id="search" class="btn blue btn-sm" type="button">搜索</button>
										</div>
									</div>
									<div id="data_all" class="table-scrollable"> </div>
									<div class="row">
									<div class="col-md-5 col-sm-12">
										<div class="dataTables_length" id="sample_1_length">
												<label> <select name="sample_1_length"
													aria-controls="sample_1"
													class="form-control input-xsmall input-inline pagesize">
														<option value="5">5</option>
														<option value="15">15</option>
														<option value="20" selected="selected">20</option></select> 每页显示
												</label>
											</div>
									</div>
										<div class="col-md-7 col-sm-12 text-right">
											<div>
													<!-- start分页控件 -->
												<ul id="paginator" class="pagination" style="visibility: visible;"></ul>
													<!-- end分页控件 -->
											</div>
										</div>
										
										<div class="col-md-6 col-sm-12">
											
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>

				<!-- END PAGE CONTENT INNER -->
			</div>
		</div>
		<!-- END PAGE CONTENT -->
	</div>
	<script>
		var pagesize = $('.pagesize').val();
		var parameter = {};//url附加parameter
		//分页参数
		var paginatorInfo = {
			"id" : "data_all",//加载list的div id
			"url" : "${ctx}/listdata/score/all",//url
			"page" : 1,//当前页
			"page_size": pagesize//每页大小
			
		};
		
		$(document).ready(function() {
			
			$(".pagesize").change(function(){
				pagesize = $(this).val();
				paginatorInfo["page_size"] = pagesize;
				loadListDataPaginatorPost(paginatorInfo,parameter);
			});
			
			$("#search").click(function(){
				 var name = $("#search_name").val();
				 parameter['name'] = name;
				 loadListDataPaginatorPost(paginatorInfo,parameter);
			});
			//初始化加载第一页数据
			loadListDataPaginatorPost(paginatorInfo,parameter);
		});
		
	
	</script>

</body>
</html>
