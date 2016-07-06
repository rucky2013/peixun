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
						Managed Datatables <small>managed datatable samples</small>
					</h1>
				</div>
				<!-- END PAGE TITLE -->
				<!-- BEGIN PAGE TOOLBAR -->
				<div class="page-toolbar">
					<!-- BEGIN THEME PANEL -->
					<div class="btn-group btn-theme-panel">
						<a href="javascript:;" class="btn dropdown-toggle"
							data-toggle="dropdown"> <i class="icon-settings"></i>
						</a>
						<div
							class="dropdown-menu theme-panel pull-right dropdown-custom hold-on-click">
							<div class="row">
								<div class="col-md-6 col-sm-6 col-xs-12">
									<h3>THEME COLORS</h3>
									<div class="row">
										<div class="col-md-6 col-sm-6 col-xs-12">
											<ul class="theme-colors">
												<li class="theme-color theme-color-default"
													data-theme="default"><span class="theme-color-view"></span>
													<span class="theme-color-name">Default</span></li>
												<li class="theme-color theme-color-blue-hoki"
													data-theme="blue-hoki"><span class="theme-color-view"></span>
													<span class="theme-color-name">Blue Hoki</span></li>
												<li class="theme-color theme-color-blue-steel"
													data-theme="blue-steel"><span class="theme-color-view"></span>
													<span class="theme-color-name">Blue Steel</span></li>
												<li class="theme-color theme-color-yellow-orange"
													data-theme="yellow-orange"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Orange</span></li>
												<li class="theme-color theme-color-yellow-crusta"
													data-theme="yellow-crusta"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Yellow Crusta</span></li>
											</ul>
										</div>
										<div class="col-md-6 col-sm-6 col-xs-12">
											<ul class="theme-colors">
												<li class="theme-color theme-color-green-haze"
													data-theme="green-haze"><span class="theme-color-view"></span>
													<span class="theme-color-name">Green Haze</span></li>
												<li class="theme-color theme-color-red-sunglo"
													data-theme="red-sunglo"><span class="theme-color-view"></span>
													<span class="theme-color-name">Red Sunglo</span></li>
												<li class="theme-color theme-color-red-intense"
													data-theme="red-intense"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Red Intense</span></li>
												<li class="theme-color theme-color-purple-plum"
													data-theme="purple-plum"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Purple Plum</span></li>
												<li class="theme-color theme-color-purple-studio"
													data-theme="purple-studio"><span
													class="theme-color-view"></span> <span
													class="theme-color-name">Purple Studio</span></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-12 seperator">
									<h3>LAYOUT</h3>
									<ul class="theme-settings">
										<li>Layout <select
											class="theme-setting theme-setting-layout form-control input-sm input-small input-inline tooltips"
											data-original-title="Change layout type"
											data-container="body" data-placement="left">
												<option value="boxed" selected="selected">Boxed</option>
												<option value="fluid">Fluid</option>
										</select>
										</li>
										<li>Top Menu Style <select
											class="theme-setting theme-setting-top-menu-style form-control input-sm input-small input-inline tooltips"
											data-original-title="Change top menu dropdowns style"
											data-container="body" data-placement="left">
												<option value="dark" selected="selected">Dark</option>
												<option value="light">Light</option>
										</select>
										</li>
										<li>Top Menu Mode <select
											class="theme-setting theme-setting-top-menu-mode form-control input-sm input-small input-inline tooltips"
											data-original-title="Enable fixed(sticky) top menu"
											data-container="body" data-placement="left">
												<option value="fixed">Fixed</option>
												<option value="not-fixed" selected="selected">Not
													Fixed</option>
										</select>
										</li>
										<li>Mega Menu Style <select
											class="theme-setting theme-setting-mega-menu-style form-control input-sm input-small input-inline tooltips"
											data-original-title="Change mega menu dropdowns style"
											data-container="body" data-placement="left">
												<option value="dark" selected="selected">Dark</option>
												<option value="light">Light</option>
										</select>
										</li>
										<li>Mega Menu Mode <select
											class="theme-setting theme-setting-mega-menu-mode form-control input-sm input-small input-inline tooltips"
											data-original-title="Enable fixed(sticky) mega menu"
											data-container="body" data-placement="left">
												<option value="fixed" selected="selected">Fixed</option>
												<option value="not-fixed">Not Fixed</option>
										</select>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- END THEME PANEL -->
				</div>
				<!-- END PAGE TOOLBAR -->
			</div>
		</div>
		<!-- END PAGE HEAD -->
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content">
			<div class="container">
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<div class="modal fade" id="portlet-config" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true"></button>
								<h4 class="modal-title">Modal title</h4>
							</div>
							<div class="modal-body">Widget settings form goes here</div>
							<div class="modal-footer">
								<button type="button" class="btn blue">Save changes</button>
								<button type="button" class="btn default" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- BEGIN PAGE BREADCRUMB -->
				<ul class="page-breadcrumb breadcrumb">
					<li><a href="#">Home</a><i class="fa fa-circle"></i></li>
					<li><a href="table_managed.html">Extra</a> <i
						class="fa fa-circle"></i></li>
					<li><a href="table_managed.html">Data Tables</a> <i
						class="fa fa-circle"></i></li>
					<li class="active">Managed Datatables</li>
				</ul>
				<!-- END PAGE BREADCRUMB -->
				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i> <span
										class="caption-subject font-green-sharp bold uppercase">外部讲师表
										</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="collapse"> </a> <a
										href="#portlet-config" data-toggle="modal" class="config">
									</a> <a href="javascript:;" class="reload"> </a> <a
										href="javascript:;" class="remove"> </a>
								</div>
							</div>
							<div class="portlet-body">
								<div class="table-toolbar">
									
								</div>
								<div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
									<div class="row">
										<div class="col-md-6 col-sm-12">
											<div class="dataTables_length" id="sample_1_length">
												<label> <select name="sample_1_length"
													aria-controls="sample_1"
													class="form-control input-xsmall input-inline pagesize">
														<option value="5">5</option>
														<option value="15">15</option>
														<option value="20">20</option>
														<option value="-1">All</option></select> 每页显示<br>
												
												</label>
												<br>
												<br>
												<br>
												<br>
												<label>
												<button   class="btn blue"><a  style="color: white;" href="${ctx }/create">新建讲师</a></button>
												</label>
											</div>
										</div>
										<div class="col-md-6 col-sm-12 text-right">
											<div id="sample_1_filter" class="dataTables_filter">
												<label>
												<!-- 
												搜索:<input type="search"
													class="form-control input-small input-inline"
													aria-controls="sample_1"> -->
													
		<div> 
			 人员：<input type="text" name="name" class="form-control input-small input-inline"
         aria-controls="sample_1" value="${name}" id="teacherInSum_name" /> 
				
			  &nbsp;    
				<input id="submit_search2" style="font-size: 12px;height:3%;padding: 4px;" class="btn btn-primary" type="submit"
					value="搜索" />&nbsp;   
		  
 
	<!-- </div> 
													
															<div>
 -->        
			
			公司：<input type="text" name="company" class="form-control input-small input-inline"
         aria-controls="sample_1"   value="${company }" id="teacherInSum_company" />

			  &nbsp;   
				<input id="submit_search1" style="font-size: 12px;height:3%;padding: 4px;" class="btn btn-primary" type="submit"
					value="搜索" />&nbsp;  
		        

	</div> 						
													</label>
											</div>
										</div>
									</div>
									
									
	
	
	
									<div id="teacherouter_my" class="table-scrollable"> </div>
									<div class="row">
									
										<div class="col-md-7 col-sm-12">
											<div>
													<!-- start分页控件 -->
												<ul id="paginator" class="pagination" style="visibility: visible;"></ul>
													<!-- end分页控件 -->
											</div>
											<button type="button"   onclick="history.back()"  class="btn default">返回</button>
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
			"id" : "teacherouter_my",//加载list的div id
			"url" : "${ctx}/listdata/teacherouter/teacherouterlist",//url
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
		
		 $("#submit_search2").click (function(){ 
         	var obj =$("#teacherInSum_name").val(); 
     		if(!(/.*[\u4e00-\u9fa5]+.*$/.test(obj))) 
     		{ 
     		alert("必须含有汉字！"); 
     		return false; 
     		} 
     		
     		 
     		parameter = {"name":obj};
     		loadListDataPaginatorPost (paginatorInfo,parameter);
			 
     		return true; 
         });
         
         $("#submit_search1").click (function(){
        	
         	var obj =$("#teacherInSum_company").val(); 
     		if(!(/.*[\u4e00-\u9fa5]+.*$/.test(obj))) 
     		{ 
     		alert("必须含有汉字!"); 
     		return false; 
     		}
     		 
     		parameter = {"company":obj};
     		loadListDataPaginatorPost (paginatorInfo,parameter)
     		return true; 
         });
         
	</script>

</body>
</html>
