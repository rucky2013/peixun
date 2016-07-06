<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page
        import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException" %>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>登录页</title>
    <link
            href="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"
            rel="stylesheet"/>
    <link
            href="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css"
            rel="stylesheet"/>
    <link
            href="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css"/>
</head>

<body>
<div class="page-container">
    <div class="page-head">
        <div class="container">
            <!-- BEGIN PAGE TITLE -->
            <div class="page-title">
                <h1>附件上传</h1>
            </div>
            <!-- END PAGE TITLE -->
        </div>
    </div>
    <!-- END PAGE HEAD -->
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content">
        <div class="container">
            <!-- BEGIN PAGE BREADCRUMB -->
            <ul class="page-breadcrumb breadcrumb">
                <li><a href="${ctx}/tohomepage">首页</a><i class="fa fa-circle"></i>
                </li>
                <li><a href="${ctx}/project/list">项目管理</a><i
                        class="fa fa-circle"></i></li>
                <li class="active">附件上传</li>
            </ul>
        </div>
        <div class="container">
            <!-- BEGIN PAGE CONTENT INNER -->
            <div class="col-md-12">
                <form id="fileupload"
                      action="${ctx}/project/uploadFile/${projectId}" method="POST"
                      enctype="multipart/form-data">
						<span class="btn green fileinput-button"> <i
                                class="fa fa-plus"></i> <span>选择文件 </span> <input type="file" id="fileuploadIn"
                                                                                  name="file">
						</span>

                </form>
            </div>
            <div class="col-md-12">
                <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet light">
                    <div>
                        <div id="data_my" class="portlet-body">
                            <input type="hidden" value="${projectId }" id="projectId"/>
                            <table class="table table-striped table-bordered table-hover"
                                   id="datatable_ajax">
                                <thead>
                                <tr role="row">
                                    <th>文件</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <shiro:user>
									    <shiro:hasRole name="admin">
											<input type="hidden"  id="fileuploadrole" value="admin"/>
										</shiro:hasRole>
								 </shiro:user>
								<shiro:user>
									    <shiro:hasRole name="user">
										    <input type="hidden" id="fileuploadrole" value="user"/>
										</shiro:hasRole>
								</shiro:user>
                                <tbody id="tbodyList">
                                
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>

            <!-- END PAGE CONTENT INNER -->
        </div>
    </div>
</div>
<script
        src="${ctx}/static/metronic/theme/assets/global/plugins/fancybox/source/jquery.fancybox.pack.js"></script>
<!-- END PAGE LEVEL PLUGINS-->
<!-- BEGIN:File Upload Plugin JS files-->
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script
        src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script
        src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/vendor/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script
        src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/vendor/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script
        src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/vendor/canvas-to-blob.min.js"></script>
<!-- blueimp Gallery script -->
<script
        src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script
        src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script
        src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->

<!-- The main application script -->
<!-- The XDomainRequest Transport is included for cross-domain file deletion for IE 8 and IE 9 -->
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/cors/jquery.xdr-transport.js"></script>
<![endif]-->
<!-- END:File Upload Plugin JS files-->
<script
        src="${ctx}/static/metronic/theme/assets/global/scripts/metronic.js"
        type="text/javascript"></script>
<script
        src="${ctx}/static/metronic/theme/assets/admin/layout3/scripts/layout.js"
        type="text/javascript"></script>
<script
        src="${ctx}/static/metronic/theme/assets/admin/layout3/scripts/demo.js"
        type="text/javascript"></script>
<script
        src="${ctx}/static/metronic/theme/assets/admin/pages/scripts/form-fileupload.js"></script>
<script
        src="${ctx}/static/metronic/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/static/js/fileUpload/fileUploadList.js"></script>
<script src="${ctx}/static/js/dateFormat.js"></script>
</body>
</html>
