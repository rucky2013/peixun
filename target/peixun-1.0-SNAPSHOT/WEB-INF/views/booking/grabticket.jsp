<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>抢票</title>
	<meta name="MobileOptimized" content="320">
	<link href="${ctx}/static/metronic/theme/assets/admin/pages/css/blog.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/static/metronic/theme/assets/admin/pages/css/news.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">
	<!-- BEGIN PAGE HEAD -->
	<div class="page-head">
		<div class="container">
			<!-- BEGIN PAGE TITLE -->
			<div class="page-title">
				<h1>抢票</h1>
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
					<a href="#">首页</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 抢票
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->
			
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="portlet light">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs font-green-sharp"></i>
						<span class="caption-subject font-green-sharp bold uppercase">抢票入口</span>
					</div>
					<div class="tools">
						<a href="javascript:;" class="reload">
						</a>
					</div>
				</div>
				<div class="portlet-body">
					<div class="row">
						<div class="col-md-12 blog-page">
							<div class="row">
								<div class="col-md-9 col-sm-8 article-block">
									<h1 style="margin-top:0px">${pxProject.name}</h1>
									<div class="row">
										<div class="col-md-4 blog-img blog-tag-data">
											<form  id="grabform" method="post" action="${ctx}/booking/grab">
											<input id="projectId" name="projectId" type="hidden" value="${pxProject.id}"/>
											</form>
											<img src="${ctx}/static/metronic/theme/assets/admin/pages/media/gallery/image5.jpg" alt="" class="img-responsive">
											<ul class="list-inline">
												<li>
													<i class="fa fa-calendar"></i>
													<a href="#">
													${pxProject.beginTime} </a>
												</li>
												<li>
													<i class="fa fa-eye"></i>
													<a href="#">
													剩${num}票 </a>
												</li>
											</ul>
										</div>
										<div class="col-md-8 blog-article">
											<h3>
											<a href="page_blog_item.html">
											${pxProject.name} </a>
											</h3>
											<p>
												 ${pxProject.content}
											</p>
											
											<c:choose>  
											   <c:when test="${pxProject.status==2}">
													<a id="grab" class="btn blue" >
													立即抢票 <i class="fa fa-paper-plane-o"></i>
													</a>
											   </c:when>  
											   <c:when test="${pxProject.status==3}">
											   		<a id="grab" class="btn blue disabled" >
													抢票暂停<i class="fa fa-pause"></i>
													</a>
											   </c:when> 
											   <c:when test="${pxProject.status==4}">
											   		<a id="grab" class="btn blue disabled" >
													抢票结束<i class="fa fa-stop"></i>
													</a>
											   </c:when> 
											   <c:when test="${pxProject.status==1}">
											   		<a id="grab" class="btn blue disabled" >
													准备抢票<i class="fa fa-circle-o"></i>
													</a>
											   </c:when> 
											   <c:otherwise>
											   		<a id="grab" class="btn blue disabled" >
													项目关闭<i class="fa fa-stop"></i>
													</a>
											   </c:otherwise>  
											</c:choose>  
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
			
		</div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->
<script>
        jQuery(document).ready(function() {       
           // initiate layout and plugins
           Metronic.init(); // init metronic core components
			Layout.init(); // init current layout
			Demo.init(); // init demo features
           UIAlertDialogApi.init();
        });
        $('#grab').click(function(){
        	var projectId = $("#projectId").val();
            bootbox.confirm("确认抢票?", function(result) {
            	if(result==true){
            		$("#grabform").submit();
            	}
            }); 
        });
    </script>
</body>
</html>
