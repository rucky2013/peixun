<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="new" tabindex="-1" role="basic"
	aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">新增</h4>
			</div>
			<div class="modal-body">

				<div class="portlet-body form">
					<!-- BEGIN FORM-->
					<div class="form-body ">
						<div class="row">
							<form class="form-horizontal" id="projectAdd">
								<div class="col-md-12">
									<div class="form-group">
										<label class="col-md-5 control-label">课程名称</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="name">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-5">开始时间</label>
										<div class="col-md-4">
											<div class="input-group date date-picker"
												data-date-format="yyyy-mm-dd">
												<input type="text" class="form-control" readonly
													name=beginTime /> <span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-5">结束时间</label>
										<div class="col-md-4">
											<div class="input-group date date-picker"
												data-date-format="yyyy-mm-dd">
												<input type="text" class="form-control" readonly
													name="endTime" /> <span class="input-group-btn">
													<button class="btn default" type="button">
														<i class="fa fa-calendar"></i>
													</button>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-5 control-label">课程标签</label>
										<div class="col-md-4">
											<select class="form-control" name="tagId">
												<c:forEach items="${projectTags}" var="pt">
													<option value="${pt.id}">${pt.value}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-5 control-label">课程级别</label>
										<div class="col-md-4">
											<select class="form-control" name="projectLevel">
												<c:forEach items="${projectLevels}" var="pL">
													<option value="${pL.id}">${pL.value}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-5 control-label">取票类型</label>
										<div class="col-md-4">
											<select class="form-control" name="ticketType" id="ticketType">
												<c:forEach items="${ticketTypes}" var="tt">
													<option value="${tt.id}">${tt.value}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group" id="changeHidden">
										<label class="col-md-5 control-label">抢票数量</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="ticketNum" id="ticketNum">
										</div>
									</div>
							</form>
						</div>
					</div>
				</div>
				<!-- END FORM-->
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn default" data-dismiss="modal">取消</button>
			<button type="button" class="btn blue" onclick="save()">保存</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<script>
	jQuery(document).ready(function() {
		$('#ticketType').change(function(e) {
			var selectVal = $('#ticketType').val();
			if(selectVal==1){
				$('#changeHidden').show();
			}
			if(selectVal==2){
				$('#changeHidden').hide();
				$('#ticketNum').val("");
			}
		});
	})
</script>
