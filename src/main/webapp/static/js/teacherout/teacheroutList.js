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
	var conf=confirm("【你确定删除吗？】");
	 if(conf==true){
		 $.ajax({
				type : "post",
				url : path() + "/teacherouter/teacherouterdelete",
				data : {
					id : id
				},
				success : function(data) {
					$("#searchBtn").click();
				}
			});
		 return true;
	 }else{
		 return false;
	 }
	
}
 
 
function save() {
	

	$.ajax({
		
		type : "post",
		url : path() + "/teacherouter/teacheroutersave",
		data : $('#form_sample_2').serialize(),
		success : function(data) {
			$("#newTeacherouter").modal("hide");
			$("#form_sample_2")[0].reset();
			$("#searchBtn").click();
		}
	});
}
function resetForm() {
	$('#searchForm')[0].reset();
}
$.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xsmall input-sm input-inline";
var userType = $("#teacherouterrole").val();
var table = $("#datatable_ajax")
		.DataTable(
				{
					"dom": "<'col-md-8 col-sm-12'l><'#mytool.table-group-actions pull-right'>rtpi",
					initComplete: function () {  
		                $("#mytool").append('<button type="button" id="searchBtn" class="btn btn-default btn-sm" onclick="table.ajax.reload();">查询</button>');
		                $("#mytool").append('<button type="button" onclick="resetForm()" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">重置</button>');
		               
		                if(userType == "admin") {
							$("#mytool").append('<a class="btn btn-default btn-sm green-haze" data-toggle="modal" href="#newTeacherouter">新增</a>');
		                }
					},
					"ajax" : {
						"url" : path() + "/listdata/teacherouter/grid",
						"type": "POST", // request type
						"data" : function ( d ) {  
			              var company =  $.trim($('#searchCompany').val());  
			                var name =  $.trim($('#searchName').val());
			                d.teacherName = name;  
			                d.company = company;
			                
			           /*  var    teacherouterrole=  $("#teacherouterrole").val();
			                
			                  if(teacherouterrole=="user"){
			                	  alert("aa");
			                	  $(".btn btn-default btn-xs").hide();
			                  }else{
			                	  alert("bb");
			                	  $(".btn btn-default btn-xs").show();
			                  }*/
			               
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
								"data" : "company"
							},
							{
								"data" : "teacherName"  
							},
							{
								"data" :  "introduce"
							},
							{
								"data" :  "phone"
							},
							{
								"data" :  "subject"
							},
							{
								"data" :  "post"
							},
							{
								"data" :  "address"
							},
							 
							 {
								"data" : function(lineData) {
									var id = lineData.id;
									var edit = '<a class="btn btn-default btn-xs " href="'
											+ path()
											+ '/teacherouter/teacherouteredit/'
											+ id
											+ '">编辑</a>';
									var del = '<a class="btn btn-default btn-xs " onClick="del('
											+ id
											+ ')" href="javascript:;">删除</a>';
									 
									if("admin"==userType){
										return edit + del;
							         }else{
							        	 return "";
							         }
									 return "";
								}
							}]
				}); 