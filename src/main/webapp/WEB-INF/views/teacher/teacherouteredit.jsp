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
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>编辑外部讲师</h1>
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
					 编辑外部讲师
				</li>
			</ul>
				<div class="portlet light">
	 
					<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="${ctx}/teacherouter/teacherouterupdate"  id ="form_sample_2" class="form-horizontal"
							method="post" > 
							<input type="hidden" value="${teacherOuter.id  }" name="id" />
						<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										 对不起，验证失败，请检查下面字段是否为空或者联系方式字段是不是数字格式！
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										 恭喜你，验证成功！
									</div>
	
									<div class="form-group">
										<label class="control-label col-md-3">公司(单位) <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="company" value="${teacherOuter.company  }" />
											</div>
										</div>
									</div>
												
									<div class="form-group">
										<label class="control-label col-md-3">姓名 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="teacherName"   value="${teacherOuter.teacherName  }" />
											</div>
										</div>
									</div>
												
									<div class="form-group">
										<label class="control-label col-md-3">课程 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="subject" value="${teacherOuter.subject  }"/>
											</div>
										</div>
									</div>
												
									<div class="form-group">
										<label class="control-label col-md-3">职称 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="post" value="${teacherOuter.post  }"/>
											</div>
										</div>
									</div>
												
									<div class="form-group">
										<label class="control-label col-md-3">授课地点 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="address" value="${teacherOuter.address  }"/>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">联系方式 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="phone" value="${teacherOuter.phone  }"/>
											</div>
										</div>
									</div>
									<div class="form-group">
									<label class="control-label col-md-3">讲师简介 <span class="required">
										* </span> 
									</label>
										<div class="col-md-4">
										<div class="input-icon right">
									        	<i class="fa"></i>
											<textarea name="introduce" data-provide="markdown"  cols="46" rows="3" data-error-container="#editor_error">${teacherOuter.introduce  }</textarea>
											<!-- <div id="editor_error">
											</div> -->
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
				</div>
			</div>
		</div>
	</div>
	<script>
		jQuery(document).ready(function() {
			 
			  Metronic.init();  
	           Layout.init();  
	          Demo.init();  
	   FormValidation.init();
			$('.date-picker').datepicker({
				rtl : Metronic.isRTL(),
				autoclose : true
			});
		});
		function cancle() {
			window.location.href='${ctx}/teacherouter/teacherouterlist';
		}
	</script>
</body>
</html>
