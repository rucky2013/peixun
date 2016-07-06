<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>登录页</title>
</head>

<body>
	<div class="page-container">
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>查看学员</h1>
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
					<li class="active">查看学员</li>
				</ul>
				<input id="ticketNum" type="hidden" value="${ticketNum }"/>
				<div class="container">
					<div class="portlet light">
						
						<table class="table table-striped table-bordered table-hover"
							id="datatableTest">
							<thead>
								<tr role="row">
									<th>学员信息</th>
								</tr>
							</thead>
							<tbody id="tbodyList">
								<c:forEach items="${studentList}" var="user">
									<tr>
										<td>${user.userName}&nbsp &nbsp ${user.departmentName }<input
											type='hidden' id='row_${user.userId }' value='id' /></td>
										
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<form id="hiddenForm" action="${ctx}/proStu/addstudents"
							method="post">
							<input type="hidden" id="students" value="${students}"
								name="students" /> <input type="hidden" id="projectId"
								value="${projectId}" name="projectId" />
						</form>
					</div>
				</div>
			</div>
		</div>
		<script>
			function addRow(name, id) {
				var tmp = $('#students').val();
				tmp = tmp + id + ",";
				$('#students').val(tmp);
				if ($('#row_' + id).length == 0) {
					table.row
							.add(
									[
											name
													+ "<input type='hidden' id='row_"+id+"'value='id'/>",
											"<button id='delrow' value='"+id+"' >删除</button>" ])
							.draw();
				}
			}
			searchTeacher();
			function searchTeacher() {
				$.ajax({
					type : "post",
					url : "${ctx}/classes/teain",
					success : function(data) {
						$('#studentSelect').html("");
						$('#studentSelect').append('<option></option>');
						for ( var o in data) {
							var op = '<option value="'+data[o].id+'">'
									+ data[o].name + " &nbsp &nbsp	"
									+ data[o].departmentName + '</option>';
							$('#studentSelect').append(op);

						}
					}
				});
			}
			var $exampleMulti = $('#studentSelect').select2({
				allowClear : true,
			});
			$exampleMulti.on("change", function(e) {
				if($('#tbodyList').find('tr').length < ($('#ticketNum').val())){
					addRow(e.added.text, e.added.id);
				} else {
					alert("人数超过上限");
				}
			});
			var table = $('#datatableTest')
					.DataTable(
							{
								"dom" : "<'row col-sm-12'<' col-sm-10'fl><'#mytool.table-group-actions pull-right col-sm-2'><'col-md-4 col-sm-3'<'table-group-actions pull-right'>>r><'table-scrollable't><'row'<'col-sm-12'pi><'col-md-4 col-sm-12'>>", // datatable layout
								initComplete : function() {

								},
								"searching": false,
								"lengthChange": false,
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
							});
			$('#datatableTest').on('click', 'button', function(e) {
				e.preventDefault();
				var delId = e.currentTarget.value;
				var userIds = $('#students').val();
				var newUserIds = userIds.replace(delId + ",", "");
				$('#students').val(newUserIds);
				table.row($(this).parents('tr')).remove().draw();

			});
			function submitList() {
					var str = $('#teacherId').find("option:selected").val();
					$.ajax({
						type : "post",
						url : "${ctx}/proStu/addstudents",
						data :{
							students : $('#students').val(),
							projectId : $('#projectId').val()
						},
						success : function(data) {
							if(data =="ture") {
								window.location.href = "${ctx}/project/list";
							}
						}
					});
			}
			function backProject() {
				window.location.href = "${ctx}/project/list";
			}

		</script>
</body>
</html>
