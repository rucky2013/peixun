var path = function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}
jQuery(document).ready(function() {
	UIAlertDialogApi.init();
	$('.date-picker').datepicker({
		rtl : Metronic.isRTL(),
		autoclose : true
	});
});
function del(id) {
	var conf = confirm("【你确定删除吗？】");
    if (conf == true) {
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
	   return true;
    } else {
        return false;
    }
}
function save() { 
	$.ajax({
		type : "post",
		url : path() + "/project/save",
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

function setStu(id){
	$.ajax({
		type : "post",
		url : path() + "/classes/issetClass/"+id,
		async:false,
		success : function(data) {
			if(data!=0){
				var sHref= path() + "/proStu/list/"+id;
				document.getElementById("msgTxt").innerHTML="<form id='hiddenlink' action='"+sHref+"' ></form>";
			    var s=document.getElementById("hiddenlink");
			    s.submit();
			}else{
				bootbox.alert("请先设置课程!");
			}
		}
	});
}

function setClass(id){
	$.ajax({
		type : "post",
		url : path() + "/classes/issetStu/"+id,
		async:false,
		success : function(data) {
			if(data==0){
				var sHref= path() + "/classes/list/"+id;
				document.getElementById("msgTxt").innerHTML="<form id='hiddenlink' action='"+sHref+"' ></form>";
			    var s=document.getElementById("hiddenlink");
			    s.submit();
			}else{
				bootbox.alert("已有设置学生不可以修改课程!");
			}
		}
	});
}
function changePrStatus(status,id) {
	if(status==5){
		var conf = confirm("【你确定关闭项目吗？】");
	    if (conf == true) {
	    	$.ajax({
				type : "post",
				url : path() + "/project/changeStatus",
				data : {
					id : id,
					status : status
				},
				success : function(data) {
						$("#searchBtn").click();
				}
			});
	    	return true;
	    }else{
	    	return false;
	    }
	}else{
		$.ajax({
			type : "post",
			url : path() + "/project/changeStatus",
			data : {
				id : id,
				status : status
			},
			success : function(data) {
				if(data=="pleaseSetClass"){
					bootbox.alert("请先设置课程");
				}else if(data=="pleaseSetScope"){
					bootbox.alert("请先设置范围");
				}else{
					$("#searchBtn").click();
				}
			}
		});
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
				$("#mytool").append('<a class="btn btn-default btn-sm blue" data-toggle="modal" href="#new">新增</a>');
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
				} 
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
					"data" : "name"
				},
				{
					"data" : function(data) {
						var str = getSmpFormatDateByLong(
							data.beginTime, true);
						return str;
					}
				},
				{
					"data" : function(data) {
						var str = getSmpFormatDateByLong(
							data.endTime, true);
						return str;
					}
				},
				{
					"data" : function(data) {
						var str = getSmpFormatDateByLong(
							data.ticketTime, true);
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
					"data" : function(data) {
						var str = "";
						if(data.status == 1) {
							str = "新建项目";
						}

						if(data.status == 2) {
							str = "抢票中...";
						}
						if(data.status == 3) {
							str = "暂停抢票";
						}
						if(data.status == 4) {
							str = "抢票已结束";
						}
						if(data.status == 5) {
							str = "项目已结束";
						}


						return str;
					}
				},
				{
					"data" : function(lineData) {
						var id = lineData.id;
						var status = lineData.status;
						var result = "";
						var ticketType = lineData.ticketType;
						var edit = '<a class="btn btn-default btn-xs purple" href="'
							+ path()
							+ '/project/edit/'
											+ 'content'
											+ '/'
							+ id
							+ '">编辑</a>';
									var editnum = '<a class="btn btn-default btn-xs " href="'
										+ path()
										+ '/project/edit/'
										+ 'num'
										+ '/'
										+ id
										+ '">修改人数</a>';
						var del = '<a class="btn btn-default btn-xs grey" onClick="del('
							+ id
							+ ')" href="javascript:;">删除</a>';
						var classes = '<button class="btn btn-default btn-xs green" onclick="setClass('
							+id+')'
							+ '">设置课程</button>';
						var viewclasses = '<a class="btn btn-default btn-xs green-stripe" href="'
							+ path()
							+ '/classes/viewlist/'
							+ 'admin/'
							+ id
							+ '">查看课程</a>';
						var projectStu =  '<button class="btn btn-default btn-xs blue" onclick="setStu('
							+id+')'
							+ '">设置学员</button>';
						var file = '<a class="btn btn-default btn-xs red-pink" href="'
							+ path()
							+ '/project/file/'
							+ id
							+ '">传附件</a>';
						var scope = '<a class="btn btn-default btn-xs yellow " href="'
							+ path()
							+ '/department/list/'
							+ id
							+ '">范围</a>';
						var studentDetail = '<a class="btn btn-default btn-xs blue" data-toggle="modal" href="'
							+ path()
							+ '/proStu/liststudent/'
							+ id + '">查看学员</a>';
						var status2 = '<button class="btn btn-default btn-xs red" onclick="changePrStatus(2,'
							+id+')'
							+ '">开始抢票</button>';
						var status3 = '<button class="btn btn-default btn-xs red" onclick="changePrStatus(3,'
							+id+')'
							+ '">暂停抢票</button>';
						var status4 = '<button class="btn btn-default btn-xs red-pink" onclick="changePrStatus(4,'
							+id+')'
							+ '">抢票结束</button>';
						var status5 = '<button class="btn btn-default btn-xs " onclick="changePrStatus(5,'
							+id+')'
							+ '">关闭项目</button>';
						if(status ==1 && ticketType == 1) {
							result = edit + del + scope + classes + projectStu +  file + status2 ;
						}
						if(status ==1 && ticketType == 2) {
							result = edit + del + viewclasses + classes + projectStu +  file + status5;
						}
						if(status ==2) {
							result = viewclasses + del + scope + status3 + status4 + studentDetail +  file;
						}
						if(status ==3) {
							result = viewclasses + del + scope + status2 + editnum + projectStu + studentDetail +  file;
						}
						if(status ==4) {
							result = viewclasses + del + status5 + studentDetail +  file;
						}
						if(status ==5) {
							result = viewclasses + del + studentDetail +  file;
						}

						return  result;
					}
				} ]
		});

