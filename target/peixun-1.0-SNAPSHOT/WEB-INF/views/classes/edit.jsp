<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>登录页</title>
</head>

<body>
	<div class="page-container">
		<div class="page-head">
			<div class="container">
				
				  <div class="page-title">
					<h1>编辑课程</h1>
				</div>  
				<!-- END PAGE TITLE -->
			</div>
		</div>
		<div class="page-content">
			<div class="container">
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="${ctx}/tohomepage">首页</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					编辑课程
				</li>
			</ul>
				<div class="portlet light">
                  <div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form  id="form_sample_2" class="form-horizontal" method="post" action="${ctx}/classes/update"      >
								<div class="form-body">
									<input type="hidden" value="${classes.id }" name="id" /> <input
								type="hidden" value="${classes.createTime }" name="createTime" />
							<input type="hidden" value="${classes.status }" name="status" />
							<input type="hidden" value="${classes.projectId }"
								name="projectId" /> <input type="hidden"
								value="${classes.teacherName }" name="teacherName" />
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										 对不起，验证失败，请检查下面字段是否为空或者联系方式字段是不是数字格式！
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										 恭喜你，验证成功！
									</div>
	
								 
										<div class="form-group">
										<label class="control-label col-md-3">课程名称 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" value="${classes.className}" name="className" />
											</div>
										</div>
									</div>
								  	<div class="form-group">
										<label class="control-label col-md-3">教师类别 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
										   <div class="input-icon right">
												<i class="fa"></i>
										<select class="form-control" name="teacherType"
											id="teacherType" onchange="searchTeacher()">
											<option value="1"
												<c:if test='${1 == classes.teacherType}'> selected='selected' </c:if>>内部讲师</option>
											<option value="2"
												<c:if test='${2 == classes.teacherType}'> selected='selected' </c:if>>外部讲师</option>
										</select>
											</div>
										</div>
									</div>
										<div class="form-group">
										<label class="control-label col-md-3">教师 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
										   <div class="input-icon right">
												<i class="fa"></i>
											<select class="form-control input-medium select2me"
										data-size="8" data-live-search="true"
										data-placeholder="Select..." id="teacherId" name="teacherId">
									         </select>
											</div>
										</div>
									</div>
									 
									<div class="form-group">
										<label class="control-label col-md-3">课时 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" value="${classes.classHour }" class="form-control" name="classHour"/>
											</div>
										</div>
									</div>
								 <div class="form-group">
										<label class="control-label col-md-3">课酬<span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" value="${classes.payClassScore }" name="payClassScore"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">学生积分 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control"  value="${classes.studentScore }" name="studentScore"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">老师积分 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" value="${classes.teacherScore }" name="teacherScore"/>
											</div>
										</div>
									</div>
								 
								 
								</div>
							<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
							               <button type="submit" class="btn green" onclick="save()">提交</button>
											<button type="button" class="btn default" onclick="cancle()" >取消</button>
										</div>
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
					<%-- <div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="${ctx}/classes/update" class="form-horizontal"
							method="post">
							<input type="hidden" value="${classes.id }" name="id" /> <input
								type="hidden" value="${classes.createTime }" name="createTime" />
							<input type="hidden" value="${classes.status }" name="status" />
							<input type="hidden" value="${classes.projectId }"
								name="projectId" /> <input type="hidden"
								value="${classes.teacherName }" name="teacherName" />
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-5 control-label">课程名称</label>
									<div class="col-md-4">
										<input type="text" class="form-control " name="name"
											value="${classes.name}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">教师类别</label>
									<div class="col-md-4">
										<select class="form-control" name="teacherType"
											id="teacherType" onchange="searchTeacher()">
											<option value="1"
												<c:if test='${1 == classes.teacherType}'> selected='selected' </c:if>>内部讲师</option>
											<option value="2"
												<c:if test='${2 == classes.teacherType}'> selected='selected' </c:if>>外部讲师</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">教师</label>
									<div class="col-md-4">
										<select class="form-control input-medium select2me"
											data-size="8" data-live-search="true"
											data-placeholder="Select..." id="teacherId" name="teacherId">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">课时</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="classHour"
											value="${classes.classHour }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">课酬</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="payClassScore"
											value="${classes.payClassScore }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">学生积分</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="studentScore"
											value="${classes.studentScore }">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">老师积分</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="teacherScore"
											value="${classes.teacherScore }">
									</div>
								</div>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-5 col-md-9">
											<button type="submit" class="btn  blue">保存</button>
											<button type="button" onclick="cancle()" class="btn  default">取消</button>
										</div>
									</div>
								</div>
							</div>
						</form>
						<!-- END FORM-->
					</div> --%>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	  Metronic.init();  
      Layout.init();  
     Demo.init();  
FormValidation.init();
	$('.date-picker').datepicker({
		rtl : Metronic.isRTL(),
		autoclose : true
	});
	
		init();
		function init() {
			var teacherType = $('#teacherType').val();
			if (teacherType == 2) {
				$.ajax({
					type : "post",
					url : "${ctx}/classes/teaout",
					success : function(data) {
						$('#teacherId').html("");
						$('#teacherId').append('<option></option>');
						for ( var i in data) {
							var op = '<option value="'+data[i].id+'">'
									+ data[i].name + " &nbsp &nbsp	"
									+ data[i].company + '</option>';
							$('#teacherId').append(op);
						}
						initSelect();
					}
				});
			}
			if (teacherType == 1) {
				$.ajax({
					type : "post",
					url : "${ctx}/classes/teain",
					success : function(data) {
						$('#teacherId').html("");
						$('#teacherId').append('<option></option>');
						for ( var o in data) {
							var op = '<option value="'+data[o].id+'">'
									+ data[o].name + " &nbsp &nbsp	"
									+ data[o].departmentName + '</option>';
							$('#teacherId').append(op);
						}
						initSelect();
					}
				});
			}

		}
		function searchTeacher() {
			var teacherType = $('#teacherType').val();
			if (teacherType == 2) {
				$.ajax({
					type : "post",
					url : "${ctx}/classes/teaout",
					success : function(data) {
						$('#teacherId').html("");
						$('#teacherId').append('<option></option>');
						for ( var i in data) {
							var op = '<option value="'+data[i].id+'">'
									+ data[i].name + " &nbsp &nbsp	"
									+ data[i].company + '</option>';
							$('#teacherId').append(op);
						}
						$exampleMulti.val(null).trigger("change");
					}
				});
			}
			if (teacherType == 1) {
				$.ajax({
					type : "post",
					url : "${ctx}/classes/teain",
					success : function(data) {
						$('#teacherId').html("");
						$('#teacherId').append('<option></option>');
						for ( var o in data) {
							var op = '<option value="'+data[o].id+'">'
									+ data[o].name + " &nbsp &nbsp	"
									+ data[o].departmentName + '</option>';
							$('#teacherId').append(op);

						}
						$exampleMulti.val(null).trigger("change");
					}
				});
			}

		}
		var $exampleMulti = $('#teacherId').select2();
		function initSelect() {
			$exampleMulti.val("${classes.teacherId}").trigger("change");
		}
		jQuery(document).ready(function() {
			// initiate layout and plugins
			/* FormValidation.init(); */
			$('.date-picker').datepicker({
				rtl : Metronic.isRTL(),
				autoclose : true
			});
			
			
		});
		function cancle() {
			window.location.href = '${ctx}/classes/list/${classes.projectId}';
		}
	</script>
</body>
</html>
