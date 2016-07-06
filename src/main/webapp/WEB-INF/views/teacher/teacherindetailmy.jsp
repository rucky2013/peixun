<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" pageEncoding="UTF-8"%>
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
						  内部讲师明细管理
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
					内部讲师明细管理
				</li>
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
										<div class="col-md-6 col-sm-12">
											<div class="dataTables_length" id="sample_1_length">
												<label> 
												<select name="sample_1_length" id="pagesize_id"
													aria-controls="sample_1"
													class="form-control input-xsmall input-inline pagesize">
														<option value="5">5</option>
														<option value="15">15</option>
														<option value="20">20</option>
													<!-- 	<option value="-1">All</option> -->
												</select> 每页显示 
												</label>
												 &nbsp; &nbsp; &nbsp; 
								         	</div>			 
						 
												<br> 
										 <div >		 
	           <div style="float:left;width:1300px;" >
	    
		    
				 <input type="text" name="name" 
				class="form-control input-small input-inline"
	             aria-controls="sample_1" value="${name }" placeholder="讲师姓名"
	             id="teacherindetail_name" /> 
					 
	               <input type="text" name="company" 
				    class="form-control input-small input-inline"
	               aria-controls="sample_1"    
	               value="${company }" id="teacherindetail_company" placeholder="讲师单位" />
	        
			   
			        <input id="submit_search1" 
			        style="font-size: 12px;height:3%;padding: 4px;" 
			        class="btn btn-default btn-sm green-haze"  value="搜索" type="submit"/>
		            
		            <button  class="btn btn-default btn-sm green-haze"  style="font-size: 12px;height:3%;padding: 4px;" id="clear_id"  >清空</button>
	       </div>
	       <br>
	      
	        	
	   </div> 						
												
					</div>
				
				<div class="col-md-6 col-sm-12 text-right">
					 <div id="sample_1_filter" class="dataTables_filter">
					                           <label>
												选择年限 <select id="year"  name="sample_1_year"
													aria-controls="sample_1"
													class="form-control input-xsmall  input-inline pagesize" > 
							                             <option >${year }</option>
							                             <option>2015</option>
						                            	  
						                            	 
					                            </select> 
						  
					                            </label> 
					</div> 
				</div>
			</div>
									
									
	
	
	
									<div id="teacherindetail_my" class="table-scrollable"> </div>
									<div class="row">
									
										<div class="col-md-7 col-sm-12">
											<div>
													<!-- start分页控件 -->
												<ul id="paginator" class="pagination" style="visibility: visible;"></ul>
													<!-- end分页控件 -->
											</div>
											<!-- <button type="button"   onclick="history.back()"  class="btn default">返回</button> -->
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
	 
		var pagesize = $('#pagesize_id').val();
		var parameter = {};//url附加parameter
		//分页参数
		var paginatorInfo = {
			"id" : "teacherindetail_my",//加载list的div id
			"url" : "${ctx}/listdata/teacherindetail/teacherindetaillist",//url
			"page" : 1,//当前页
			"page_size": pagesize//每页大小
			
		};
		var classname = 1;
		
		
		
		$(document).ready(function() { 
			 
			/*  var selectVal= '${selectVal}'; 
			  var selectNameVal= '${selectNameVal}';
			
			  if(selectVal!=null){
				  parameter = {"selectVal":selectVal}; 
			  }
			  if(selectNameVal!=null){
				  parameter = {"selectNameVal":selectNameVal};
			  } */
			$("#pagesize_id").change(function(){
				pagesize = $(this).val();
				paginatorInfo["page_size"] = pagesize;
				  paginatorInfo["page"] = 1;
				loadListDataPaginator(paginatorInfo,parameter);
			});
			//初始化加载第一页数据
			loadListDataPaginator(paginatorInfo,parameter);
			
			
			
			
		});
		
		
		$("#year").change(function() { 
			 
			  $("#teacherindetail_name").val("");
     		$("#teacherindetail_company").val("");  
     		  yearVal = $("#year").val();
           parameter = {"year":yearVal};
           paginatorInfo["page"] = 1;
       loadListDataPaginatorPost (paginatorInfo,parameter);
			 
		});
		
	
		$("#submit_search1").click(function(){ 
			 
         	var nameVal =$.trim($("#teacherindetail_name").val());
         	var companyVal =$.trim($("#teacherindetail_company").val());
         	var yearVal = $("#year").val();
         	/* 
     		if(!(/.*[\u4e00-\u9fa5]+.*$/.test(nameVal)) && !(/.*[\u4e00-\u9fa5]+.*$/.test(companyVal))) 
     		{  
     		alert("必须含有汉字！"); 
     		$("#teacherindetail_name").val("");
     		$("#teacherindetail_company").val("");
     		return false; 
     		}   */
     		
     		paginatorInfo["page"] = 1;
     		parameter = {"name":nameVal,"company":companyVal,"year":yearVal};
     		 
     		loadListDataPaginatorPost (paginatorInfo,parameter);
     		 
     		return true; 
         });
		 
         
         $("#nameorder_id").click(function(){ 
        	/*  
        	 parameter['classorder'] = "";
        	 parameter['year'] = ""; */
        	 var yearVal = $("#year").val(); 
        	 paginatorInfo["page"] = 1;
        		parameter = {"nameorder":"1","year":yearVal};
        		loadListDataPaginatorPost (paginatorInfo,parameter);
        		 
         });
         
         $("#classorder_id").click(function(){
        	/*  alert("调用了"); */ 
        	/*  parameter['nameorder'] = "";
        	 parameter['year'] = ""; */
        	 var yearVal = $("#year").val();
        	 paginatorInfo["page"] = 1;
     		parameter = {"classorder":"1","year":yearVal};
     		loadListDataPaginatorPost (paginatorInfo,parameter);
     	
     		//$("#nameorder_id").unbind();
      });
        
            $("#clear_id").click(function(){
            	$("#teacherindetail_name").val("");
         		$("#teacherindetail_company").val("");
            });
	</script>

</body>
</html>
