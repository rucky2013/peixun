<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>分数管理</title>
</head>

<body>
	<div class="page-container">
			<!-- BEGIN PAGE HEAD -->
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>分数管理</h1>
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
				<li>
					<a href="${ctx}/tohomepage">首页</a><i class="fa fa-circle"></i>
				</li>
				<li>
					<a href="${ctx }/project/list">分数管理</a>
					<i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 学员分数管理
				</li>
			</ul>
				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="row">
							<form class="form-horizontal" id="searchForm">
								<input type="hidden" id="projectId" name="projectId"
										value="${projectId}" />
								<input type="hidden" id="classId" name="classId"
										value="${classId}" />
								
							</form>
						</div>
						<div>
							<div id="data_my" class="portlet-body">
								<table class="table table-striped table-bordered table-hover"
									id="datatable_ajax">
									<thead>
										<tr role="row">
											<th>学员名称</th>
											<th>是否出勤</th>
											<th>应得学时</th>
											<th>应得学分</th>
											<th>所得学时</th>
											<th>所得学分</th>
											<th>操作</th>
										</tr>
									</thead>
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
		<!-- END PAGE CONTENT -->
		<%@ include file="/WEB-INF/views/classes/add.jsp"%>
	</div>
	<script>
	function backClass() {
		window.location.href = path() +"/classes/list/"+$('#projectId').val();
	}
	  var nEditing = null;
      var nNew = false;
	  var table = $("#datatable_ajax")
	.dataTable(
			{
				"dom": "<'col-md-8 col-sm-12'l><'#mytool.table-group-actions pull-right'>rtpi",
				initComplete: function () {
	               // $("#mytool").append('<button type="button" id="searchBtn" class="btn btn-default btn-sm" onclick="table.ajax.reload();">查询</button>');
	               // $("#mytool").append('<button type="button" class="btn btn-default btn-sm" onclick="backClass();">返回</button>');
				},
				"ajax" : {
					"url" : "${ctx}/score/updateScoreinit/grid",
					"type": "POST", // request type
					"data" : function ( d ) {  
		                var classId = $('#classId').val(); 
		                d.classId = classId;  
		            }  ,

				},
				
				"lengthChange": false,
				"bServerSide": true,     //服务端分页
				"bSort": false,//关闭排序功能
				"language": { // language settings
                    // metronic spesific
                    "metronicGroupActions": "_TOTAL_ records selected:  ",
                    "metronicAjaxRequestGeneralError": "无法连接到，请重新刷新页面",

                    // data tables spesific
                     
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
							"data" : "user_name"
						},
						{
							"data" :  function(data) {
								if(data.come==1){
									return "出勤";
								}else{
									return "未出勤";
								}
							}
						},
						{
							"data" : "should"
						},
						{
							"data" : "s_score"
						},
						{
							"data" : "reals"
						},
						{
							"data" : "score"
						},
						{
							"data" : function(lineData) {
								var id = lineData.id;
								var edit = '<a class="btn btn-default btn-xs edit" href="">编辑</a>';
								return edit;
							}
						} ]
			});
	  //数据自动更新finish后置空nEditing
	  table.on('xhr.dt', function ( e, settings, json, xhr ) {
		  nEditing = null;
	    });
	
     table.on('click', '.cancel', function (e) {
         e.preventDefault();

         if (nNew) {
             table.fnDeleteRow(nEditing);
             nNew = false;
         } else {
        	 	var nRow = $(this).parents('tr')[0];
        		 cancelEditRow(table,nRow)
             restoreRow(table, nEditing);
        		
             nEditing = null;
         }
     });

     table.on('click', '.edit', function (e) {
         e.preventDefault();
         /* Get the row as a parent of the link that was clicked on */
         var nRow = $(this).parents('tr')[0];
		// console.log(nRow);
         if (nEditing !== null && nEditing != nRow) {
             /* Currently editing - but not this row - restore the old before continuing to edit mode */
            // cancelEditRow(table,nEditing);
             restoreRow(table, nEditing);
             editRow(table, nRow);
             nEditing = nRow;
         } else if (nEditing == nRow && this.innerHTML == "保存") {
             /* Editing this row and want to save it */
            
             saveRow(table, nEditing);
             nEditing = null;
             
         } else {
             /* No edit in progress - let's start one */
             editRow(table, nRow);
             nEditing = nRow;
         }
     });
     
     function restoreRow(table, nRow) {
	
    	     var aData = table.fnGetData(nRow);
    	     console.log(aData);
    
    	     table.fnDraw(); 
         table.fnUpdate(aData.reals, nRow, 4, false);
         table.fnUpdate(aData.score, nRow, 5, false);

         
         
     }

     function editRow(table, nRow) {
    	
         var aData = table.fnGetData(nRow);
         var jqTds = $('>td', nRow);
         jqTds[4].innerHTML = '<input type="text" class="" value="' + aData['reals'] + '">';
         jqTds[5].innerHTML = '<input type="text" class="" value="' + aData['score'] + '"><input type="hidden" value="'+aData['id']+'" />';
         
         jqTds[6].innerHTML = '<a class="edit" href="">保存</a><br /><a class="cancel" href="">取消</a>';
         //jqTds[16].innerHTML = '<a class="cancel" href="">取消</a>';
     }

     function saveRow(table, nRow) {
         var jqInputs = $('input', nRow);
         
         var reals = jqInputs[0].value;
         var score = jqInputs[1].value;
         var id = jqInputs[2].value
        	 saveData(id,reals,score);
         table.fnUpdate(jqInputs[0].value, nRow, 4, false);
         table.fnUpdate(jqInputs[1].value, nRow, 5, false);
         table.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 6, false);
        
     }

     function cancelEditRow(table, nRow) {
         var jqInputs = $('input', nRow);
         console.log(jqInputs[0].value);
         table.fnUpdate(jqInputs[0].value, nRow, 4, false);
         table.fnUpdate(jqInputs[1].value, nRow, 5, false);
         table.fnUpdate('<a class="edit" href="">修改</a>', nRow, 6, false);
         table.fnDraw();
     }
     
     function saveData (id,reals,score){
    	 var url = "${ctx}/score/classscoreupdate";
			var parameter = {
					"id" : id,
					"score" : score,
					"reals" : reals
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
					 table.fnDraw();
				},
				error : function(data){
					
					alert("保存失败");
					 table.fnDraw();
				}
			});

     }
     
	</script>
</body>
</html>
