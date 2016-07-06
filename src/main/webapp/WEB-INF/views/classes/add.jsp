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
				<h4 class="modal-title">新增课程</h4>
			</div>
			<!-- <form class="form-horizontal" id="classAdd"> -->
				<div class="modal-body">
	            <div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form  id="form_sample_2" class="form-horizontal" method="post" action="${ctx }/classes/save" onsubmit="return validname();" >
								<div class="form-body">
									<input
										type="hidden" id="projectId" name="projectId"
										value="${projectId}" />
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
												<input type="text" class="form-control" id="classname" name="className" />
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
										<option value="1">内部讲师</option>
										<option value="2">外部讲师</option>
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
												<input type="text" class="form-control" name="classHour"/>
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
												<input type="text" class="form-control" name="payClassScore"/>
											</div>
										</div>
									</div>
									<c:if test="${pxProject.projectLevel==1}">
									<div class="form-group">
										<label class="control-label col-md-3">学生积分 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="studentScore"/>
											</div>
										</div>
									</div>
									</c:if>
									<div class="form-group">
										<label class="control-label col-md-3">老师积分 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" name="teacherScore"/>
											</div>
										</div>
									</div>
								 
								 
								</div>
							<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn green" >提交</button>
										<button type="button" class="btn default" data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
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
	searchTeacher();
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
								+ data[i].teacherName + " &nbsp &nbsp	"
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
	var $exampleMulti = $('#teacherId').select2({
		allowClear : true,
	});
	  
	
	function validname(){
		var classname = $("#classname").val();
		 
		if(classname=""||classname==null||classname==undefined){
			
			return false;
		}
		return true;
		
	}
	  function cancle() {
	window.location.href='${ctx}/teacherouter/teacherouterlist';
}  
</script>