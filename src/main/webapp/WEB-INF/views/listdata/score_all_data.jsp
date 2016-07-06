
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<table
	class="table table-striped table-hover table-bordered dataTable no-footer"
	id="sample_1" role="grid" aria-describedby="sample_1_info">
	
	<thead>
				<tr role="row">
					<th colspan="3">基本信息</th>
					<th colspan="12">积分</th>
					<th colspan="3" >年度汇总</th>
				</tr>
			<tr role="row" class="text-center">
				<th class="sorting_asc text-center" tabindex="0" aria-controls="sample_1"rowspan="1" colspan="1" aria-sort="ascending" style="width: 115px;">分部</th>
				<th class="sorting_disabled text-center" rowspan="1" colspan="1" style="width: 115px;">部门</th>
				<th class="sorting_disabled text-center" rowspan="1" colspan="1" style="width: 115px;">姓名</th>
				<c:forEach var="i" begin="1" end="12" step="1">
					<th class="sorting_disabled text-center" rowspan="1" colspan="1" style="width: 115px;">${i}月</th>	  
				</c:forEach>
				<th class="sorting_disabled text-center" rowspan="1" colspan="1" style="width: 115px;">应得积分</th>
				<th class="sorting_disabled text-center" rowspan="1" colspan="1" style="width: 115px;">积分汇总</th>
				<th class="sorting_disabled text-center" rowspan="1" colspan="1" style="width: 115px;">讲师积分</th>
				<th class="sorting_disabled text-center" rowspan="1" colspan="1" style="width: 115px;">部门学时</th>
			</tr>
			</thead>
	
	<tbody>
	<c:forEach items="${scores}" var="s">
		<tr role="row" class="text-center">
			<td class="sorting_disabled" rowspan="1" colspan="1" style="width:115px;" >${s.parentDepartmentName}</td>
			<td class="sorting_disabled" rowspan="1" colspan="1" style="width:115px;" >${s.departmentName}</td>
			<td class="sorting_disabled" rowspan="1" colspan="1"  style="width: 115px;"><a href="${ctx}/score/user?userid=${s.userid}" target="_blank">${s.username}</a></td>
			<c:forEach items="${s.scoreMonthModels}" var="sm">
				<td class="sorting_disabled" rowspan="1" colspan="1"  style="width: 115px;">${sm.scoreCount}</td>
			</c:forEach>
			<td class="sorting_disabled" rowspan="1" colspan="1"  style="width: 115px;">${s.scoreCountShould}</td>
			<td class="sorting_disabled" rowspan="1" colspan="1" style="width: 115px;"><a href="${ctx}/score/detail?userid=${s.userid}" target="_blank">${s.scoreCount}</a></td>
			<td class="sorting_disabled" rowspan="1" colspan="1" style="width: 115px;"><a href="${ctx}/score/detail?userid=${s.userid}" target="_blank">${s.scoreCountTeacher}</a></td>
			<td class="sorting_disabled" rowspan="1" colspan="1" style="width: 115px;"><a href="${ctx}/score/detail?userid=${s.userid}" target="_blank">${s.periodCountDepartment}</a></td>
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
			loadListDataPaginatorPost(paginatorInfo,parameter);
		}
	};
	$('#paginator').bootstrapPaginator(options);
</script>

