<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>登录页</title>
</head>

<body>
	<div class="page-container">
		<!-- BEGIN PAGE HEAD -->
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>
						积分设定
					</h1>
				</div>
				
			</div>
		</div>
		<!-- END PAGE HEAD -->
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content">
			<div class="container">
				<ul class="page-breadcrumb breadcrumb">
					<li><a href="${ctx}">首页</a><i class="fa fa-circle"></i></li>
					<li class="active">积分设定</li>
				</ul>
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet light">
							
							<div class="portlet-body">
								<div class="table-toolbar">
									
								</div>
								<div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
									<div class="row">
										<div class="col-md-4 text-left">
												<input id="search_name" placeholder="姓名" type="search" class="form-control input-sm input-inline" aria-controls="sample_1">
												<button id="search" class="btn blue btn-sm" type="button">搜索</button>
											
										</div>
										<div class="col-md-2 text-right">
												<select id="search_year" class="form-control input-sm">
													
												</select>
										</div>
									</div>
									<div id="data_all" class=""> </div>
									<div class="row">
									<div class="col-md-5 col-sm-12">
										<div class="dataTables_length" id="sample_1_length">
												<label> <select name="sample_1_length"
													aria-controls="sample_1"
													class="form-control input-xsmall input-inline pagesize">
														<option value="5">5</option>
														<option value="15">15</option>
														<option value="20" selected="selected">20</option></select> 每页显示
												</label>
											</div>
									</div>
										<div class="col-md-7 col-sm-12 text-right">
											<div>
										
													<!-- start分页控件 -->
												<ul id="paginator" class="pagination" style="visibility: visible;"></ul>
													<!-- end分页控件 -->
											</div>
										</div>
										
										<div class="col-md-6 col-sm-12">
											
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>

				<!-- END PAGE CONTENT INNER -->
			</div>
		</div>
		<!-- END PAGE CONTENT -->
	</div>
	<script>
		var pagesize = $('.pagesize').val();
		var parameter = {};//url附加parameter
		//分页参数
		var paginatorInfo = {
			"id" : "data_all",//加载list的div id
			"url" : "${ctx}/listdata/score/setting",//url
			"page" : 1,//当前页
			"page_size": pagesize//每页大小
		};
		
		$(document).ready(function() {
			initYear();
			$(".pagesize").change(function(){
				pagesize = $(this).val();
				paginatorInfo["page_size"] = pagesize;
				loadListDataPaginatorPost(paginatorInfo,parameter);
			});
			
			$("#search").click(function(){
				alert(1);
				 var name = $("#search_name").val();
				 parameter['name'] = name;
				 loadListDataPaginatorPost(paginatorInfo,parameter);
			});
			
			$("#search_year").change(function(){
				var year = $(this).val();
				parameter['year'] = year;
				window.location = "${ctx}/score/setting?year=" + year;
			});
			
		});
		
		function initYear(){
			var date = new Date();
			var year = date.getFullYear();
			var options = "";
			for(var i = parseInt(year); i <= parseInt(year) + 1 ;i++){
				if(i == "${year}"){
					
					options += "<option selected='selected'>"+(i)+"</option>"
					
				} else {
					options += "<option>"+(i)+"</option>"
				}
				
				
			}
       		
			
			$("#search_year").html(options);
			
			
		}
		var TableEditable = function () {

		    var handleTable = function () {

		        function restoreRow(oTable, nRow) {
		            var aData = oTable.fnGetData(nRow);
		            var jqTds = $('>td', nRow);

		            for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
		                oTable.fnUpdate(aData[i], nRow, i, false);
		            }

		            oTable.fnDraw();
		        }

		        function editRow(oTable, nRow) {
		            var aData = oTable.fnGetData(nRow);
		            var jqTds = $('>td', nRow);
 		            jqTds[0].innerHTML = aData[0];
		            jqTds[1].innerHTML = aData[1];
		            jqTds[2].innerHTML = aData[2];
		             
		            jqTds[3].innerHTML = '<input type="text" class="form-control" value="' + aData[3] + '">';
		            jqTds[4].innerHTML = '<input type="text" class="form-control" value="' + aData[4] + '">';
		            jqTds[5].innerHTML = '<input type="text" class="form-control" value="' + aData[5] + '">';
		            jqTds[6].innerHTML = '<input type="text" class="form-control" value="' + aData[6] + '">';
		            jqTds[7].innerHTML = '<input type="text" class="form-control" value="' + aData[7] + '">';
		            jqTds[8].innerHTML = '<input type="text" class="form-control" value="' + aData[8] + '">';
		            jqTds[9].innerHTML = '<input type="text" class="form-control" value="' + aData[9] + '">';
		            jqTds[10].innerHTML = '<input type="text" class="form-control" value="' + aData[10] + '">';
		            jqTds[11].innerHTML = '<input type="text" class="form-control" value="' + aData[11] + '">';
		            jqTds[12].innerHTML = '<input type="text" class="form-control" value="' + aData[12] + '">';
		            jqTds[13].innerHTML = '<input type="text" class="form-control" value="' + aData[13] + '">';
		            jqTds[14].innerHTML = '<input type="text" class="form-control" value="' + aData[14] + '">';
		            jqTds[15].innerHTML = '<a class="edit" href="">保存</a><br /><a class="cancel" href="">取消</a>';
		            //jqTds[16].innerHTML = '<a class="cancel" href="">取消</a>';
		        }

		        function saveRow(oTable, nRow) {
		        		
		            var jqInputs = $('input', nRow);
/* 		            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
		            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
		            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false); */
		            oTable.fnUpdate(jqInputs[0].value, nRow, 3, false);
		            oTable.fnUpdate(jqInputs[1].value, nRow, 4, false);
		            oTable.fnUpdate(jqInputs[2].value, nRow, 5, false);
		            oTable.fnUpdate(jqInputs[3].value, nRow, 6, false);
		            oTable.fnUpdate(jqInputs[4].value, nRow, 7, false);
		            oTable.fnUpdate(jqInputs[5].value, nRow, 8, false);
		            oTable.fnUpdate(jqInputs[6].value, nRow, 9, false);
		            oTable.fnUpdate(jqInputs[7].value, nRow, 10, false);
		            oTable.fnUpdate(jqInputs[8].value, nRow, 11, false);
		            oTable.fnUpdate(jqInputs[9].value, nRow, 12, false);
		            oTable.fnUpdate(jqInputs[10].value, nRow, 13, false);
		            oTable.fnUpdate(jqInputs[11].value, nRow, 14, false);
		            
		            oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 15, false);
		            //oTable.fnUpdate('<a class="delete" href=""></a>', nRow, 16, false);
		            
		            oTable.fnDraw();
		            //save setting 
		            
		            var score_setting_id = nRow.id;
		            var s_1 = jqInputs[0].value;
		            var s_2 = jqInputs[1].value;
		            var s_3 = jqInputs[2].value;
		            var s_4 = jqInputs[3].value;
		            var s_5 = jqInputs[4].value;
		            var s_6 = jqInputs[5].value;
		            var s_7 = jqInputs[6].value;
		            var s_8 = jqInputs[7].value;
		            var s_9 = jqInputs[8].value;
		            var s_10 = jqInputs[9].value;
		            var s_11 = jqInputs[10].value;
		            var s_12 = jqInputs[11].value;
		            saveScoreSetting(score_setting_id,s_1,s_2,s_3,s_4,s_5,s_6,s_7,s_8,s_9,s_10,s_11,s_12);
		            
		        }

		        function cancelEditRow(oTable, nRow) {
		            var jqInputs = $('input', nRow);
		            oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
		            oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
		            oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
		            oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
		            oTable.fnUpdate('<a class="edit" href="">修改</a>', nRow, 16, false);
		            oTable.fnDraw();
		        }

		        var table = $('#sample_editable_1');

		        var oTable = table.dataTable({

		            // set the initial value
		            "paging": false,
		            "autoWidth": false,
		            "searching": false,
		            "pageLength": 20,
					"ordering" : false,
					"lengthChange": false,
					"processing": true,
					"info": false,
		            "language": {
		                "lengthMenu": " _MENU_ records"
		            },
		            "columnDefs": [{ // set default column settings
		                'orderable': false,
		                'targets': [0]
		            }, {
		                "searchable": false,
		                "targets": [0]
		            }],
		            "order": [
		                [0, "asc"]
		            ] // set first column as a default sort by asc
		        });

		       
		        var nEditing = null;
		        var nNew = false;

		       

		        table.on('click', '.delete', function (e) {
		            e.preventDefault();

		            if (confirm("Are you sure to delete this row ?") == false) {
		                return;
		            }

		            var nRow = $(this).parents('tr')[0];
		            oTable.fnDeleteRow(nRow);
		            alert("Deleted! Do not forget to do some ajax to sync with backend :)");
		        });

		        table.on('click', '.cancel', function (e) {
		            e.preventDefault();

		            if (nNew) {
		                oTable.fnDeleteRow(nEditing);
		                nNew = false;
		            } else {
		                restoreRow(oTable, nEditing);
		                nEditing = null;
		            }
		        });

		        table.on('click', '.edit', function (e) {
		            e.preventDefault();
		            console.log(nEditing);
		            /* Get the row as a parent of the link that was clicked on */
		            var nRow = $(this).parents('tr')[0];

		            if (nEditing !== null && nEditing != nRow) {
		                /* Currently editing - but not this row - restore the old before continuing to edit mode */
		                restoreRow(oTable, nEditing);
		                editRow(oTable, nRow);
		                nEditing = nRow;
		            } else if (nEditing == nRow && this.innerHTML == "保存") {
		                /* Editing this row and want to save it */
		                saveRow(oTable, nEditing);
		                nEditing = null;
		                
		            } else {
		                /* No edit in progress - let's start one */
		                editRow(oTable, nRow);
		                nEditing = nRow;
		            }
		        });
		    }

		    return {

		        //main function to initiate the module
		        init: function () {
		            handleTable();
		        }

		    };

		}();



		function saveScoreSetting (score_setting_id,s_1,s_2,s_3,s_4,s_5,s_6,s_7,s_8,s_9,s_10,s_11,s_12){
			var url = "${ctx}/score/setting/update";
			var parameter = {
					"score_setting_id" : score_setting_id,
					"s_1" : s_1,
					"s_2" : s_2,
					"s_3" : s_3,
					"s_4" : s_4,
					"s_5" : s_5,
					"s_6" : s_6,
					"s_7" : s_7,
					"s_8" : s_8,
					"s_9" : s_9,
					"s_10" : s_10,
					"s_11" : s_11,
					"s_12" : s_12
			};
				$.ajax({
				url : url,
				method : 'POST',
				data : parameter,
				dataType : 'json',
				success : function (data){
					
					if(data.result == "fail"){
						alert("保存失败");
					}
				},
				error : function(data){
					
					alert("保存失败");
				}
			});
		}

		//初始化加载第一页数据
		loadListDataPaginatorPost(paginatorInfo,parameter);
	</script>

</body>
</html>
