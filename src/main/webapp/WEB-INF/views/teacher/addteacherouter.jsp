<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="newTeacherouter" tabindex="-1" role="basic"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
		  	 <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				  <h4 class="modal-title">新增外部讲师</h4>  
			</div>  
			
		<div class="row">
				<div class="col-md-12"> 
					<div class="portlet light">
						<!-- <div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">新增外部讲师</span>
							</div>
							 
						</div> -->
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form  id="form_sample_2" class="form-horizontal" method="post" action="${ctx }/teacherouter/teacheroutersave" onsubmit="return validname();" >
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
												<input type="text" class="form-control" name="company"/>
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
												<input type="text" class="form-control" id="teachername" name="teacherName" />
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
												<input type="text" class="form-control" name="subject"/>
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
												<input type="text" class="form-control" name="post"/>
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
												<input type="text" class="form-control" name="address"/>
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
												<input type="text" class="form-control" name="phone"/>
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
											<textarea name="introduce" data-provide="markdown" rows="3" data-error-container="#editor_error"></textarea>
											<!-- <div id="editor_error">
											</div> -->
											</div>
										</div>
									</div>
								 
								</div>
							<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn green" >提交</button>
											<button type="button" class="btn default" onclick="cancle()" >取消</button>
										</div>
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
					</div>
					 
				</div>
		<!-- /.modal-content -->
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
		
		
		function validname(){
			var teachername = $("#teachername").val();
			 
			if(teachername=""||teachername==null||teachername==undefined){
				
				return false;
			}
			return true;
			
		}
		});
		function cancle() {
			window.location.href='${ctx}/teacherouter/teacherouterlist';
		}
	</script>
