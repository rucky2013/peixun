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
		url : path() + "/project/del",
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
		url : path() + "/project/save",
		data : $('#projectAdd').serialize(),
		success : function(data) {
			$("#new").modal("hide");
			$("#projectAdd")[0].reset();
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
						"url" : path() + "/listdata/project/grid",
						"type": "POST", // request type
						"data" : function ( d ) {  
			                var name = $('#searchName').val();  
			                var beginTime = $('#searchBeginTime').val();
			                var endTime = $('#searchEndTime').val();
			                var ticketType = $('#searchTicketType').val();
			                var projectLevel = $('#searchProjectLevel').val();
			                d.name = name;  
			                d.beginTime = beginTime;
			                d.ticketType = ticketType;
			                d.endTime = endTime;
			                d.projectLevel = projectLevel;
			            }  ,

					},
					"bSort": false ，//关闭排序功能
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
					"aoColumns" : [
							{
								"data" : "name"
							},
							{
								"data" : function(data) {
									var str = getSmpFormatDateByLong(
											data.beginTime, false);
									return str;
								}
							},
							{
								"data" : function(data) {
									var str = getSmpFormatDateByLong(
											data.endTime, false);
									return str;
								}
							},
							{
								"data" :  function(data) {
									var str = "";
									if(data.ticketType == 1) {
										str = "抢票";
									}
									if(data.ticketType == 2) {
										str = "指定人员";
									}
									return str;
								}
							},
							{
								"data" : function(data) {
									var str = "";
									if(data.projectLevel == 1) {
										str = "公司级别";
									}
									if(data.projectLevel == 2) {
										str = "部门级别";
									}
									return str;
								}
							},
							{
								"data" : function(lineData) {
									var id = lineData.id;
									var edit = '<a class="btn-group-xs green-haze btn-circle" data-toggle="modal" href="'
											+ path()
											+ '/project/edit/'
											+ id
											+ '">编辑</a>';
									var del = '<a class="btn-group-xs green-haze btn-circle" onClick="del('
											+ id
											+ ')" href="javascript:;">删除</a>';
									return edit + del;
								}
							} ]
				});