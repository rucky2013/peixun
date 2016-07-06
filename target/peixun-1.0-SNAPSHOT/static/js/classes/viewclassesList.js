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
function resetForm() {
	$('#searchForm')[0].reset();
	$exampleMulti.val(null).trigger("change");
	}
function backProject() {
	if($('#userrole').val()=="admin"){
		window.location.href = path() +"/project/list";
	}else{
		if($("#requrl").val()=="my"){
			window.location.href = path() +"/project/mylist";
		}else{
			window.location.href = path() +"/myapplyproject/list";
		}
	}
	
}
$.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";
var table = $("#datatable_ajax")
		.DataTable(
				{
					"dom": "<'col-md-8 col-sm-12'l><'#mytool.table-group-actions pull-right'>rtpi",
					initComplete: function () {
		                $("#mytool").append('<button type="button" id="searchBtn" class="btn btn-default btn-sm" onclick="table.ajax.reload();">查询</button>');
		                $("#mytool").append('<button type="button" onclick="resetForm()" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">重置</button>');
		                $("#mytool").append('<button type="button" class="btn btn-default btn-sm" onclick="backProject();">返回</button>');
					},
					"ajax" : {
						"url" : path() + "/listdata/classes/grid",
						"type": "POST", // request type
						"data" : function ( d ) {  
			                var projectId = $('#projectId').val(); 
			                var name = $('#searchName').val();
			                d.projectId = projectId;  
			                d.name = name;
			            }  ,

					},
					"bServerSide": true,     //服务端分页
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
					"aoColumns" : [
							{
								"data" : "className"
							},
							{
								"data" :  "teacherName"
							},
							{
								"data" : "classHour"
							},
							{
								"data" : "payClassScore"
							},
							{
								"data" : function(lineData) {
									var id = lineData.id;
									var result = "";
									var projectStatus = $('#projectStatus').val();
									var setscore = '<a class="btn btn-default btn-xs " href="'
										+ path()
										+ '/score/updateScoreinit/'
										+ id
										+ '/'
										+ $('#projectId').val()
										+ '">打分</a>';
									var settescore = '<a class="btn btn-default btn-xs " href="'
										+ path()
										+ '/score/updateteScoreinit/'
										+ id
										+ '/'
										+ $('#projectId').val()
										+ '">教师打分</a>';
									var url = window.location.href;
									if($('#userrole').val()!="admin"){
									}else{
										if(projectStatus == 1){
											result = setscore + settescore;
										}
										if(projectStatus != 5 && projectStatus != 1){
											result = setscore + settescore;
										}
									}
									return result;
								}
							} ]
				});