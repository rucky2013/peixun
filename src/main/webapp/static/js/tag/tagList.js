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
		url : path() + "/tag/del",
		data : {
			id : id
		},
		success : function(data) {
			$("#searchBtn").click();
		}
	});
}
function save() {
	$.ajax({
		type : "post",
		url : path() + "/tag/save",
		data : $('#form_sample_2').serialize(),
		success : function(data) {
			$("#new").modal("hide");
			$("#form_sample_2")[0].reset();
			$("#searchBtn").click();
		}
	});
}
function resetForm() {
	$('#searchForm')[0].reset();
}
$.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";
var table = $("#datatable_ajax")
		.DataTable(
				{
					"dom": "<'col-md-8 col-sm-12'l><'#mytool.table-group-actions pull-right'>rtpi",
					initComplete: function () {
		                $("#mytool").append('<button type="button" id="searchBtn" class="btn btn-default btn-sm" onclick="table.ajax.reload();">查询</button>');
		                $("#mytool").append('<button type="button" onclick="resetForm()" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">重置</button>');
		                $("#mytool").append('<a class="btn btn-default btn-sm green-haze" data-toggle="modal" href="#new">新增</a>');
					},
					"ajax" : {
						"url" : path() + "/listdata/tag/grid",
						"type": "POST", // request type
						"data" : function ( d ) {  
			                var name = $('#searchName').val();  
			                var isUse = $('#isUse').val();
			                d.name = name;  
			                d.isUse = isUse;  
			            }  ,

					},
					"bSort": false,//关闭排序功能
					"language": { // language settings
                        // metronic spesific
                        "metronicGroupActions": "_TOTAL_ records selected:  ",
                        "metronicAjaxRequestGeneralError": "无法连接到，请重新刷新页面",

                        // data tables spesific
                        "lengthMenu": "<span class='seperator'></span>_MENU_",
                        "info": "<span class='seperator'></span>共_TOTAL_ 条",
                        "infoEmpty": "未找到数据",
                        "emptyTable": "未找到数据",
                        "zeroRecords": "未找到数据",
                        "paginate": {
                            "previous": "上一页",
                            "next": "下一页",
                            "last": "末页",
                            "first": "首页",
                            "page": "Page",
                            "pageOf": "of"
                        }
                    },
					 "bFilter": false, //开关，是否启用客户端过滤器
					 "bServerSide": true,     //服务端分页
					"aoColumns" : [
							{
								"data" : "tagName"
							},
							{
								"data" : function(data) {
									var str = "";
									if(data.isUse == 1) {
										str = "生效";
									}
									if(data.isUse == 0) {
										str = "失效";
									}
									return str;
								}
							},
							{
								"data" : function(lineData) {
									var id = lineData.id;
									var edit = '<a class="btn btn-default btn-xs " href="'
											+ path()
											+ '/tag/edit/'
											+ id
											+ '">编辑</a>';
									var del = '<a class="btn btn-default btn-xs " onClick="del('
											+ id
											+ ')" href="javascript:;">删除</a>';
									return edit + del ;
								}
							} ]
				});