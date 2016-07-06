var path = function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}
jQuery(document).ready(function() {
	$('.date-picker').datepicker({
		rtl : Metronic.isRTL(),
		autoclose : true
	});
});
function del(id) {
	$.ajax({
		type : "post",
		url : path() + "/fileManager/del/"+id,
		data : {
			id : id
		},
		success : function(data) {
			table.ajax.reload();
		}
	});
}
function resetForm() {
	$('#searchForm')[0].reset();
	$exampleMulti.val(null).trigger("change");
}
function backProject() {
	var fileuploadrole =$("#fileuploadrole").val(); 
	 if(fileuploadrole=="admin"){
		 window.location.href = path() + "/project/list"; 
	 }else{
		 window.location.href = path() + "/myapplyproject/list"; 
	 }
	
}
function test(){
	alert(1);
}
$.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";
var table = $("#datatable_ajax")
		.DataTable(
				{
					"dom" : "<'col-md-8 col-sm-12'l><'#mytool.table-group-actions pull-right'>rtpi",
					initComplete : function() {
						$("#mytool")
								.append(
										'<button type="button" class="btn btn-default btn-sm" onclick="backProject();">返回课程</button>');
					},
					"ajax" : {
						"url" : path() + "/project/listFile/",
						"type" : "POST", // request type
						"data" : function(d) {
							var projectId = $('#projectId').val();
							d.projectId = projectId;
						},

					},
					"bSort" : false,//关闭排序功能
					"language" : { // language settings
						// metronic spesific
						"metronicGroupActions" : "_TOTAL_ records selected:  ",
						"metronicAjaxRequestGeneralError" : "无法连接到，请重新刷新页面",

						// data tables spesific
						"lengthMenu" : "<span class='seperator'></span>_MENU_",
						"info" : "<span class='seperator'></span>共_TOTAL_ 条",
						"infoEmpty" : "未找到数据",
						"emptyTable" : "未找到数据",
						"zeroRecords" : "未找到数据",
						"paginate" : {
							"previous" : "上一页",
							"next" : "下一页",
							"last" : "末页",
							"first" : "首页",
							"page" : "Page",
							"pageOf" : "of"
						}
					},
					"bFilter" : false, //开关，是否启用客户端过滤器
					"aoColumns" : [
							{
								"data" : function(data) {
									var id = data.id;
									var title = data.name
									var str = '<p class="name">' + '<a href="'
											+ path() + '/fileManager/download/'
											+ id + '" title="' + title
											+ '" download="' + title + '">'
											+ title + '</a>' + '</p>'
									return str;
								}
							},
							{
								"data" : function(data) {
									var id = data.id;
									var del = '<botton class="btn btn-default btn-xs" onclick="del('+id+')">删除</botton>';
									return del;
								}
							} ]
				});