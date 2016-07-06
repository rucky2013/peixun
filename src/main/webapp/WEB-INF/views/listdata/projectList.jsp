
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<table class="table table-striped table-bordered table-hover" id="sample_1">
	<thead>
		<tr role="row">
			
			<th class="sorting_asc" tabindex="0" aria-controls="sample_1"
				rowspan="1" colspan="1" aria-sort="ascending" >项目名称</th>
			<th class="sorting" rowspan="1" colspan="1" >开始时间</th>
			<th class="sorting" rowspan="1" colspan="1" >结束时间</th>
			<th class="sorting" rowspan="1" colspan="1" >门票类型</th>
			<th class="sorting" rowspan="1" colspan="1" >课程类别</th>
			<th class="sorting" rowspan="1" colspan="1">操作</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${projects}" var="project">
			<tr class="gradeX odd" role="row">
				<td>${project.name}</td>
				<td class="sorting_1">${project.beginTime}</td>
				<td class="sorting_1">${project.endTime}</td>
				<td class="sorting_1">${project.ticketType}</td>
				<td class="sorting_1">${project.projectLevel}</td>
				<td><a class="btn-group-xs green-haze btn-circle" data-toggle="modal" href="${ctx}/project/edit/${project.id}">编辑</a>
				<a class="btn-group-xs green-haze btn-circle" onClick="del(${project.id})" href="#">删除</a>
				</td>
			</tr>
		</c:forEach>

	</tbody>
</table>
<script>

	var options = {
		bootstrapMajorVersion : 3, //版本
		currentPage : "${paginator.page}", //当前页数
		totalPages : "${paginator.pageCount}", //总页数
		itemTexts : function(type, page, current) {
			switch (type) {
			case "first":
				return "首页";
			case "prev":
				return "上一页";
			case "next":
				return "下一页";
			case "last":
				return "末页";
			case "page":
				return page;
			}
		},//点击事件，用于通过Ajax来刷新整个list列表
		onPageClicked : function(event, originalEvent, type, page) {
			paginatorInfo["page"] = page;
			loadListDataPaginator(paginatorInfo,parameter);
		}
	};
	$('#paginator').bootstrapPaginator(options);
	//删除专业性
	function del(id){	
		if(confirm("您确认要删除此专业性吗？") == true) {
			$.ajax({
				type : "post",
				url : "${ctx}/project/del",	
				data : {
					id : id
				},
				success : function(data) {
					loadListDataPaginator(paginatorInfo,parameter);
				}
			});
		}
	}
</script>

