<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!-- BEGIN PAGE LEVEL STYLES -->
<link
	href="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css"
	rel="stylesheet" />
<link
	href="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/css/jquery.fileupload.css"
	rel="stylesheet" />
<link
	href="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/css/jquery.fileupload-ui.css"
	rel="stylesheet" />
<!-- END PAGE LEVEL STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
<div class="page-container">
	<!-- BEGIN PAGE HEAD -->
	<div class="page-head">
		<div class="container">
			<!-- BEGIN PAGE TITLE -->
			<div class="page-title">
				<h1>
					Multiple File Upload <small>amazing file upload experience</small>
				</h1>
			</div>
			<!-- END PAGE TITLE -->
			<!-- BEGIN PAGE TOOLBAR -->
			<div class="page-toolbar">
				<!-- BEGIN THEME PANEL -->
				<div class="btn-group btn-theme-panel">
					<a href="javascript:;" class="btn dropdown-toggle"
						data-toggle="dropdown"> </a>
				</div>
				<!-- END THEME PANEL -->
			</div>
			<!-- END PAGE TOOLBAR -->
		</div>
	</div>
	<!-- END PAGE HEAD -->
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content">
		<div class="container">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">Widget settings form goes here</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li><a href="#">Home</a><i class="fa fa-circle"></i></li>
				<li><a href="form_fileupload.html">UI Components</a> <i
					class="fa fa-circle"></i></li>
				<li class="active">Multiple File Upload</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="portlet light">
				<div class="portlet-body">
					<div class="row">
						<div class="col-md-12">
							<form id="fileupload" action="${ctx}/project/uploadFile/${projectId}" method="POST"
								enctype="multipart/form-data">
								<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
								<div class="row fileupload-buttonbar">
									<div class="col-md-12">
										<!-- The fileinput-button span is used to style the file input field as button -->
										<span class="btn green fileinput-button"> <i
											class="fa fa-plus"></i> <span> Add files... </span> <input
											type="file" name="file" multiple="">
										</span>
										<button type="submit" class="btn blue start">
											<i class="fa fa-upload"></i> <span> Start upload </span>
										</button>
										<button type="reset" class="btn warning cancel">
											<i class="fa fa-ban-circle"></i> <span> Cancel upload
											</span>
										</button>
										<button type="button" class="btn red delete">
											<i class="fa fa-trash"></i> <span> Delete </span>
										</button>
										<input type="checkbox" class="toggle">
										<!-- The global file processing state -->
										<span class="fileupload-process"> </span>
									</div>
									<!-- The global progress information -->
									<div class="col-lg-5 fileupload-progress fade">
										<!-- The global progress bar -->
										<div class="progress progress-striped active"
											role="progressbar" aria-valuemin="0" aria-valuemax="100">
											<div class="progress-bar progress-bar-success"
												style="width: 0%;"></div>
										</div>
										<!-- The extended global progress information -->
										<div class="progress-extended">&nbsp;</div>
									</div>
								</div>
								<!-- The table listing the files available for upload/download -->
								<table role="presentation" class="table table-striped clearfix">
									<tbody class="files">
									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
		</div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger label label-danger"></strong>
        </td>
        <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
            <div class="progress-bar progress-bar-success" style="width:0%;"></div>
            </div>
        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn blue start" disabled>
                    <i class="fa fa-upload"></i>
                    <span>Start</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn red cancel">
                    <i class="fa fa-ban"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
        {% for (var i=0, file; file=o.files[i]; i++) { %}
            <tr class="template-download fade">
                <td>
                    <span class="preview">
                        {% if (file.thumbnailUrl) { %}
                            <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                        {% } %}
                    </span>
                </td>
                <td>
                    <p class="name">
                        {% if (file.url) { %}
                            <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                        {% } else { %}
                            <span>{%=file.name%}</span>
                        {% } %}
                    </p>
                    {% if (file.error) { %}
                        <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                    {% } %}
                </td>
                <td>
                    <span class="size">{%=o.formatFileSize(file.size)%}</span>
                </td>
                <td>
                    {% if (file.deleteUrl) { %}
                        <button class="btn red delete btn-sm" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                            <i class="fa fa-trash-o"></i>
                            <span>Delete</span>
                        </button>
                        <input type="checkbox" name="delete" value="1" class="toggle">
                    {% } else { %}
                        <button class="btn yellow cancel btn-sm">
                            <i class="fa fa-ban"></i>
                            <span>Cancel</span>
                        </button>
                    {% } %}
                </td>
            </tr>
        {% } %}
    </script>
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/respond.min.js"></script>
<script src="${ctx}/static/metronic/theme/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<!-- BEGIN PAGE LEVEL PLUGINS -->
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
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script
	src="${ctx}/static/metronic/theme/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-ui.js"></script>
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
<script>
        jQuery(document).ready(function() {
        FormFileUpload.init();
        });
    </script>
