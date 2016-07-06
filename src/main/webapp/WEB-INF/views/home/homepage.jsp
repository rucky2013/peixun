<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>首页</title>
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/flot/jquery.flot.min.js"></script>
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/flot/jquery.flot.resize.min.js"></script>
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/flot/jquery.flot.pie.min.js"></script>
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/flot/jquery.flot.stack.min.js"></script>
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/flot/jquery.flot.crosshair.min.js"></script>
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/flot/jquery.flot.categories.min.js"
	type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<script
	src="${ctx}/static/js/chart_index.js"></script>
<script type="text/javascript"
	src="http://cdn.staticfile.org/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>

</head>

<body>
	<!-- BEGIN PAGE CONTAINER -->
	<div class="page-container">
		<!-- BEGIN PAGE HEAD -->
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>常用功能</h1>
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
					<li class="active"><a href="#">首页</a></li>
				</ul>
				<!-- END PAGE BREADCRUMB -->
				<!-- BEGIN PAGE CONTENT INNER -->
				<shiro:user>
				<div class="row">
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<div class="dashboard-stat blue-madison">
							<div class="visual">
								<i class="fa fa-comments"></i>
							</div>
							<div class="details">
								<div class="number">项目</div>
							</div>
							<shiro:hasRole name="admin">
							<a class="more" href="${ctx }/project/list"> 进入 <i
								class="m-icon-swapright m-icon-white"></i>
							</a>
							</shiro:hasRole>
							<shiro:hasRole name="user">
							<a class="more" href="${ctx }/project/mylist"> 进入 <i
								class="m-icon-swapright m-icon-white"></i>
							</a>
							</shiro:hasRole>
						</div>
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<div class="dashboard-stat red-intense">
							<div class="visual">
								<i class="fa fa-bar-chart-o"></i>
							</div>
							<div class="details">
								<div class="number">分数</div>
							</div>
							<shiro:hasRole name="admin">
							<a class="more" href="${ctx }/score/all"> 进入 <i
								class="m-icon-swapright m-icon-white"></i>
							</a>
							</shiro:hasRole>
							<shiro:hasRole name="user">
							<a class="more" href="${ctx }/score/my"> 进入 <i
								class="m-icon-swapright m-icon-white"></i>
							</a>
							</shiro:hasRole>
						</div>
					</div>
					<div class="col-lg-4 col-md-3 col-sm-6 col-xs-12">
						<div class="dashboard-stat green-haze">
							<div class="visual">
								<i class="fa fa-shopping-cart"></i>
							</div>
							<div class="details">
								<div class="number">讲师</div>
							</div>
							<a class="more" href="${ctx }/teacherouter/teacherouterlist"> 进入 <i
								class="m-icon-swapright m-icon-white"></i>
							</a>
						</div>
					</div>
				</div>
				
				<!-- END PAGE CONTENT INNER -->
				<!-- BEGIN CHART PORTLETS-->
				<div class="row">
					<div class="table-scrollable table-scrollable-borderless col-md-12">
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i> <span
										class="caption-subject font-green-sharp bold uppercase">项目列表</span>
								</div>
								<shiro:hasRole name="admin">
								<div class="tools">
									<a href="${ctx }/project/list">更多</a>
								</div>
								</shiro:hasRole>
							</div>
							<table class="table table-hover table-light">
								<thead>
									<tr>
										<th>项目名称</th>
										<th>开课周期</th>
										<th>抢票时间</th>
										<th>学分</th>
										<th>抢票二维码</th>
									</tr>
								</thead>
								<c:forEach items="${projectList}" var="project">
									<tr>
										<td>${project.name }</td>
										<td><fmt:formatDate value="${project.beginTime}"
												pattern="yyyy-MM-dd HH:mm"></fmt:formatDate> －<fmt:formatDate
												value="${project.endTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
										<td><fmt:formatDate value="${project.ticketTime}"
															pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
										<td>${project.totalStudentScore }</td>
										<td><c:if test="${project.status ==2}"><button class="btn default"  href="#basic" onclick="createQrcode(${project.id})">抢票</button></c:if></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN INTERACTIVE CHART PORTLET-->
						<div class="portlet light">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-cogs font-green-sharp"></i> <span
										class="caption-subject font-green-sharp bold uppercase">月度统计</span>
								</div>
								<div class="tools">
									<a href="javascript:;" class="reload"> </a>
								</div>
							</div>
							<div class="portlet-body">
								<div id="chart_2" class="chart"></div>
							</div>
						</div>
						<!-- END INTERACTIVE CHART PORTLET-->

					</div>
				</div>
				<!-- END CHART PORTLETS-->
				</shiro:user>
			</div>
		</div>
		<!-- END PAGE CONTENT -->
	</div>
	<!-- END PAGE CONTAINER -->
	<div class="modal fade" id="basic" tabindex="-1" role="basic"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title">Modal Title</h4>
				</div>
				<div class="modal-body" id="qrcode"></div>
				<div class="modal-footer">
					<button type="button" class="btn default" data-dismiss="modal">Close</button>
					<button type="button" class="btn blue">Save changes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<script>
jQuery(document).ready(function() {       
  	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	Demo.init(); // init demo features
	var score_my = new Array();
	var period_my = new Array();
	<c:forEach items="${score.scoreMonthModels}" var="sm">
		score_my.push(new Array(parseInt("${sm.month}"),parseInt("${sm.scoreCount}")));
		period_my.push(new Array(parseInt("${sm.month}"),parseInt("${sm.periodCount}")));
		
	</c:forEach>
	Charts.initCharts(score_my,period_my);
  

});
function createQrcode(id) {
	var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
	$('#qrcode').html("");
	var url = "http://"+window.location.host+result+"/booking/tograbticket?projectId="+id;
	window.location.href = url;
	
}
</script>
</body>
</html>
