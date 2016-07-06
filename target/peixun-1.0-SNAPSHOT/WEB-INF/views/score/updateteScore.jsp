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
<title>教师分数</title>
</head>

<body>
	<div class="page-container">
		<!-- BEGIN PAGE HEAD -->
		<!-- END PAGE HEAD -->
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content">
			<div class="container">
				<div class="portlet light">

					<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="${ctx}/score/tescoreupdate" class="form-horizontal"
							method="post">
							<input type="hidden" value="${pxClass.id }" name="id" />
							<input type="hidden" value="${pxClass.projectId }" name="projectId" />
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-5 control-label">课程名称</label>
									<div class="col-md-4">
										<input type="text" class="form-control " name="name"
											value="${pxClass.className}" readonly >
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">讲师</label>
									<div class="col-md-4">
										<input type="text" class="form-control " name="teacherName"
											value="${pxClass.teacherName}" readonly >
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">课程学时</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="classHour"
											value="${pxClass.classHour }" readonly>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">课程积分</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="teacherScore"
											value="${pxClass.teacherScore }" readonly>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">实际学时</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="sclassHour"
											value="${pxClass.classHour }">
									</div>
								</div>
									<div class="form-group">
									<label class="col-md-5 control-label">讲师实际积分</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="steacherScore"
											value="${pxClass.teacherScore }" >
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">讲师课酬</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="payClassScore"
											value="${pxClass.payClassScore }">
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
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function cancle() {
			window.location.href = '${ctx}/classes/viewlist/${pxClass.projectId }';
		}
	</script>
</body>
</html>
