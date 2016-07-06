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
<title>学员分数</title>
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
						<form action="${ctx}/score/classscoreupdate" class="form-horizontal"
							method="post">
							<input type="hidden" value="${pxClassStudent.id }" name="id" />
							<input type="hidden" value="${pxClassStudent.user_id }" name="user_id" />
							<input type="hidden" value="${pxClassStudent.class_id }" name="class_id" />
							<input type="hidden" value="${projectId}" name="projectId" />
							<input type="hidden"  name="mark" value="暂无"/>
							<div class="form-body">
								<div class="form-group">
									<label class="col-md-5 control-label">学员名称</label>
									<div class="col-md-4">
										<input type="text" class="form-control " name="user_name"
											value="${pxClassStudent.user_name}" readonly >
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">是否出勤</label>
									<div class="col-md-4">
										<select class="form-control" name="come"
											id="come" ">
											<option value="1"
												<c:if test='${1 == pxClassStudent.come}'> selected='selected' </c:if>>已出勤</option>
											<option value="0"
												<c:if test='${0 == pxClassStudent.come}'> selected='selected' </c:if>>未出勤</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">课程课时</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="should"
											value="${pxClassStudent.should }" readonly>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">实际学时</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="reals"
											value="${pxClassStudent.reals }">
									</div>
								</div>
									<div class="form-group">
									<label class="col-md-5 control-label">应得学分</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="s_score"
											value="${pxClassStudent.s_score }" readonly>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-5 control-label">实得学分</label>
									<div class="col-md-4">
										<input type="text" class="form-control" name="score"
											value="${pxClassStudent.score }">
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
			window.location.href = '${ctx}/score/updateScoreinit/${pxClassStudent.class_id}/${projectId}';
		}
	</script>
</body>
</html>
