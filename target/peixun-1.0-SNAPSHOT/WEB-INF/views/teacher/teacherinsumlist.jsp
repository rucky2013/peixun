<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<table
	class="table table-striped table-bordered table-hover dataTable no-footer"
	id="sample_1" role="grid" aria-describedby="sample_1_info">
	<thead>
		<tr role="row">
			<!-- <th class="table-checkbox sorting_disabled" rowspan="1" colspan="1"
				style="width: 24px;">
				<div class="checker">
					<span><input type="checkbox" class="group-checkable"
						data-set="#sample_1 .checkboxes"></span>
				</div>
			</th> -->
			<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 100px;">讲师名称</th>
			<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 150px;">讲师单位</th> 
			<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 100px;">讲师一级部门</th> 
				<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 80px;">年度课时</th>
				<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 80px;">年度课酬积分</th>
				<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 80px;">年度所得积分</th>
		</tr>
	</thead>
	<tbody>
 
		<c:forEach items="${teacherInSumModels}" var="t">
			<tr class="gradeX odd" role="row">
				<!-- <td></td> --> 
				<td class="sorting_1">${t.name}</td>
				<td tlass="sorting_1">${t.parentDepartment}</td>
				 <td tlass="sorting_1">${t.department}</td> 
				<td class="sorting_1">${t.classScoreYear}</td>
				<td class="sorting_1">${t.payScoreYear}</td>
 
				<td class="sorting_1">${t.sumScoreYear}</td>
				 
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
			loadListDataPaginatorPost (paginatorInfo,parameter);
		}
	};
	$('#paginator').bootstrapPaginator(options);
	
	
</script>

