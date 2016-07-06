<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>云农场培训平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport">
<meta content="" name="description">
<meta content="" name="author">

<link href="${ctx}/static/metronic/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/metronic/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/metronic/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/metronic/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/metronic/theme/assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/metronic/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/metronic/theme/assets/global/plugins/bootstrap-datepicker/css/datepicker.css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${ctx}/static/metronic/theme/assets/global/css/components.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/metronic/theme/assets/global/css/plugins.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/metronic/theme/assets/admin/layout3/css/layout.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/metronic/theme/assets/admin/layout3/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
<link href="${ctx}/static/metronic/theme/assets/admin/layout3/css/custom.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/metronic/theme/assets/global/plugins/bootstrap-datetimepicker/css/datetimepicker.css">
<!-- END THEME STYLES -->
<!-- <link rel="shortcut icon" href="favicon.ico"/> -->


<script src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/admin/pages/scripts/components-pickers.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/metronic/theme/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>

 
 
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${ctx}/static/metronic/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/metronic/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}/static/metronic/theme/assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/static/metronic/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctx}/static/metronic/theme/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/admin/layout3/scripts/layout.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/admin/layout3/scripts/demo.js" type="text/javascript"></script>
<script src="${ctx}/static/metronic/theme/assets/global/scripts/datatable.js"></script>
<script src="${ctx}/static/metronic/theme/assets/admin/pages/scripts/table-ajax.js"></script>
 
	<!-- BEGIN 自定义 SCRIPTS -->
	<script src="${ctx}/static/bootstrap/2.3.2/js/bootstrap-paginator.js" type="text/javascript"></script>
	<script src="${ctx}/static/js/app.js" type="text/javascript"></script>
 
	<script src="${ctx}/static/metronic/theme/assets/admin/pages/scripts/form-validation.js" type="text/javascript"></script>
 	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<!-- END 自定义 SCRIPTS -->
<script src="${ctx}/static/metronic/theme/assets/admin/pages/scripts/ui-alert-dialog-api.js"></script>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>


<sitemesh:head/>
</head>

<body>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
	<div id="content">
		<sitemesh:body/>
	</div>
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	
</body>
</html>