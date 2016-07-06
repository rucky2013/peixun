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
	<script
			src="${ctx}/static/metronic/theme/assets/global/plugins/jstree/dist/jstree.min.js"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script
			src="${ctx}/static/metronic/theme/assets/admin/pages/scripts/ui-tree.js"></script>
	<script
			src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
			type="text/javascript"></script>
	<link rel="stylesheet" type="text/css"
		  href="${ctx}/static/metronic/theme/assets/global/plugins/jstree/dist/themes/default/style.min.css" />
</head>

<body>
<div class="page-container">
	<div class="page-head">
		<div class="container">
			<!-- BEGIN PAGE TITLE -->
			<div class="page-title">
				<h1>部门列表</h1>
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
				<li class="active">部门列表</li>
			</ul>
			<div class="container">
				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="row">
							<div class="col-md-12">
								<div class="portlet light">
									<div class="portlet-body">
										<div id="tree_4" class="tree-demo"></div>
										<shiro:user>
									    <shiro:hasRole name="admin">
											<input type="hidden"     id="projectscoperole" value="admin"/>
										</shiro:hasRole>
								      </shiro:user>
									<shiro:user>
									    <shiro:hasRole name="user">
										    <input type="hidden"     id="projectscoperole" value="user"/>
										</shiro:hasRole>
									</shiro:user>
										<input type="hidden" value="${id}" id="id"/>
									</div>
									<div class="portlet-body">
										<button type="button" class="btn default" data-dismiss="modal" onclick="submitTree()">保存</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	function submitTree() {
		var selectedElmsIds = $('#tree_4').jstree("get_selected");
		 var projectscoperoleVal= $("#projectscoperole").val();
		 
		$.ajax({
			type : "post",
			url :  "${ctx}/project/projectscope",
			data : {
				ids : selectedElmsIds+"",
				id : $('#id').val(),
			projectscopename : projectscoperoleVal,
			},
			success : function(data) {
				window.location.href = "${ctx}"+data;
			}
		});
	}
	jQuery(document).ready(function() {
		$('#ticketType').change(function(e) {
			var selectVal = $('#ticketType').val();
			if (selectVal == 1) {
				$('#changeHidden').show();
			}
			if (selectVal == 2) {
				$('#changeHidden').hide();
				$('#ticketNum').val("");
			}
		});
	})

	$("#tree_4")
			.jstree(
					{
						'plugins' : [ "wholerow", "checkbox", "types" ],
						'core' : {
							'data' : {
								"url" : "/peixun/department/getChildren",
								"data" : function (node) {
									return { "id" : node.id };
								}
							}
						},
						"types" : {
							"default" : {
								"icon" : "fa fa-folder icon-state-warning icon-lg"
							},
							"file" : {
								"icon" : "fa fa-file icon-state-warning icon-lg"
							}
						}
					});
</script>
</body>
</html>