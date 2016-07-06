<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="new" tabindex="-1" role="basic"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">新增项目标签</h4>
			</div>
			
				<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form  id="form_sample_2" class="form-horizontal" method="post" action="${ctx }/tag/save" onsubmit="return validname();" >
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
												<input type="text" class="form-control" id="tagName_id" name="tagName" />
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
											<select class="form-control" name="isUse" id="isUse">
											<option value="1">生效</option>
											<option value="0">失效</option>
									</select>
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
		<!-- 	<form class="form-horizontal" id="tagAdd">
				<div class="modal-body">

					<div class="portlet-body form">
						BEGIN FORM
						<div class="form-body">
							<div class="form-group">
								<label class="col-md-5 control-label">名称</label>
								<div class="col-md-4">
									<input type="text" class="form-control"
										name="tagName"> 
								</div>
							</div>
						
							<div class="form-group">
								<label class="col-md-5 control-label">状态</label>
								<div class="col-md-4">
									<select class="form-control" name="isUse" id="isUse">
											<option value="1">生效</option>
											<option value="0">失效</option>
									</select>
								</div>
							</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn default" data-dismiss="modal">取消</button>
					<button type="button" class="btn blue" onclick="save()">保存</button>
				</div>
			</form>
		</div> -->
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
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
	var tagName_id = $("#tagName_id").val();
	 
	if(tagName_id=""||tagName_id==null||tagName_id==undefined){
		
		return false;
	}
	return true;
	
}
});
function cancle() {
	window.location.href='${ctx}/teacherouter/teacherouterlist';
}
</script>