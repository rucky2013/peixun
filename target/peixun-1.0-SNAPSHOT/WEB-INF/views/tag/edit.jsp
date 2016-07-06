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
<title>项目标签</title>
</head>

<body>
	<div class="page-container">
			<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>编辑项目标签</h1>
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
					 编辑项目标签
				</li>
			</ul>
				<div class="portlet light">

	                   <div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form  id="form_sample_2" class="form-horizontal" method="post" action="${ctx}/tag/update"   >
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
										<label class="control-label col-md-3">名称 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" id="tagName_id" name="tagName" value="${tag.tagName}" />
											</div>
										</div>
									</div> 
									
									<div class="form-group">
										<label class="control-label col-md-3">状态 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
										   <div class="input-icon right">
												<i class="fa"></i>
										<select class="form-control" name="isUse">
												<option value="1" <c:if test='${1 == tag.isUse}'> selected='selected' </c:if>>生效</option>
												<option value="0" <c:if test='${0 == tag.isUse}'> selected='selected' </c:if>>失效</option>
										</select>
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
				<%-- 	<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="${ctx}/tag/update" class="form-horizontal"
							method="post">
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-5 control-label">名称</label>
									<div class="col-md-4">
									<input type="hidden" name="id" value="${tag.id }"/>
										<input type="text" class="form-control " name="tagName"
											value="${tag.tagName}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">状态</label>
									<div class="col-md-4">
										<select class="form-control" name="isUse">
												<option value="1" <c:if test='${1 == tag.isUse}'> selected='selected' </c:if>>生效</option>
												<option value="0" <c:if test='${0 == tag.isUse}'> selected='selected' </c:if>>失效</option>
										</select>
									</div>
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
						</form>
						<!-- END FORM-->
					</div> --%>
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
		
			$('.date-picker').datepicker({
				rtl : Metronic.isRTL(),
				autoclose : true
			});
			
		});
		
		function cancle() {
			window.location.href = '${ctx}/tag/list';
		}
	</script>
</body>
</html>
