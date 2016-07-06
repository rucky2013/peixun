<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="studnets" tabindex="-1" role="basic"
	aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
				<h4 class="modal-title">查看学员</h4>
			</div>
			<div class="modal-body">

				<div class="portlet-body form">
					<!-- BEGIN FORM-->
					<div class="form-body ">
						<div class="row">
							<div id="data_all" class="table-scrollable"> </div>
						</div>
					</div>
				</div>
				<!-- END FORM-->
			</div>
		</div>
		<div class="modal-footer">
			
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<script>

</script>
