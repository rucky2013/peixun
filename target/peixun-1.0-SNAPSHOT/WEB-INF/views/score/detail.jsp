<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
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
						积分详情
					</h1>
				</div>
				
			</div>
		</div>
		<!-- END PAGE HEAD -->
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content">
			<div class="container">
				
				<ul class="page-breadcrumb breadcrumb">
					<li><a href="${ctx}">首页</a><i class="fa fa-circle"></i></li>
					<li class="active">积分详情</li>
				</ul>
			
				<div class="row">
					<div class="col-md-18">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet light">
							
							<div class="portlet-body">
								<div class="table-toolbar">
									
								</div>
								<div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
									
									<div id="data_my" class="table-scrollable"> </div>
									<div class="row">
									
										<div class="col-md-7 col-sm-12">
											<div>
													<!-- start分页控件 -->
												<ul id="paginator" class="pagination" style="visibility: visible;"></ul>
													<!-- end分页控件 -->
											</div>
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
		var pagesize = 1;
		var parameter = {"userid":"${userid}","year":"${year}"};//url附加parameter
		//分页参数
		var paginatorInfo = {
			"id" : "data_my",//加载list的div id
			"url" : "${ctx}/listdata/score/detail",//url
			"page" : 1,//当前页
			"page_size": pagesize//每页大小
			
		};
		
		$(document).ready(function() {
			
			$(".pagesize").change(function(){
				pagesize = $(this).val();
				paginatorInfo["page_size"] = pagesize;
				loadListDataPaginator(paginatorInfo,parameter);
			});
			//初始化加载第一页数据
			loadListDataPaginator(paginatorInfo,parameter);
		});
		
	
	</script>

</body>
</html>
