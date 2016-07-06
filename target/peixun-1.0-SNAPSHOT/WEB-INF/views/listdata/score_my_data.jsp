
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<table
	class="table table-striped table-bordered table-hover dataTable no-footer"
	id="sample_1" role="grid" aria-describedby="sample_1_info">
	<thead>
		<tr role="row">
			<th class="sorting_disabled" rowspan="1" colspan="2" ></th>
			<c:forEach var="i" begin="1" end="12" step="1">  
				<th class="sorting_disabled" rowspan="1" colspan="2" >${i}月</th>
			</c:forEach>
			<th class="sorting_disabled" rowspan="2" colspan="1" >学时汇总</th>
			<th class="sorting_disabled" rowspan="2" colspan="1" >积分汇总</th>		
		</tr>
		<tr role="row">
			
			<th class="sorting_disabled" rowspan="1" colspan="2" ></th>
			
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1">学分</th>
		</tr>
	</thead>
	<tbody>

	<%-- 	<c:forEach items="${trains}" var="train">
			<tr class="gradeX odd" role="row">
				<td></td>
				<td>${train.lastUpdated}</td>
				<td class="sorting_1">${train.should}</td>
				<td class="sorting_1">${train.real}</td>
				<td class="sorting_1">${train.status}</td>
				<td class="sorting_1">${train.mark}</td>
			</tr>
		</c:forEach> --%>
		<tr role="row">
			<td class="sorting_disabled" rowspan="1" colspan="2" >公司级别学习积分</td>
			<c:forEach items="${score.scoreMonthModels}" var="sm">
				
					<td class="sorting_disabled" rowspan="1" colspan="1">${sm.periodCount - sm.periodCountDepartment - sm.periodCountTeacher}</td>
					<td class="sorting_disabled" rowspan="1" colspan="1">${sm.scoreCount}</td>
				
			</c:forEach>
			<td class="sorting_disabled" rowspan="3" colspan="1">${score.periodCount-score.periodCountDepartment}</td>
			<td class="sorting_disabled" rowspan="3" colspan="1">${score.scoreCount}</td>
				
		</tr>										
		
		<tr role="row">
			<td class="sorting_disabled" rowspan="1" colspan="2" >公司级别课酬积分</td>
			<c:forEach items="${score.scoreMonthModels}" var="sm">
					<td class="sorting_disabled" rowspan="1" colspan="1">${sm.periodCountTeacher}</td>
					<td class="sorting_disabled" rowspan="1" colspan="1">${sm.scoreCountTeacher}</td>
			</c:forEach>
				
		</tr>
		
		<tr role="row">
			<td class="sorting_disabled" rowspan="1" colspan="2" >月度学时/积分汇总</td>
			<c:forEach items="${score.scoreMonthModels}" var="sm">
		
					<td class="sorting_disabled" rowspan="1" colspan="1">${sm.periodCount - sm.periodCountDepartment}</td>
					<td class="sorting_disabled" rowspan="1" colspan="1">${sm.scoreCount}</td>
		
			</c:forEach>
				
		</tr>

		<tr role="row">
			<th class="sorting_disabled" rowspan="1" colspan="2" ></th>
			<c:forEach var="i" begin="1" end="12" step="1">  
				<th class="sorting_disabled" rowspan="1" colspan="2" >${i}月学时</th>
			</c:forEach>
			
			<th class="sorting_disabled" rowspan="1" colspan="2" >学时汇总</th>			
		</tr>
		
	
		<tr role="row">
			<td class="sorting_disabled" rowspan="1" colspan="2" >学时汇总</td>
			<c:forEach items="${score.scoreMonthModels}" var="sm">
					<td class="sorting_disabled" rowspan="1" colspan="2">${sm.periodCountDepartment}</td>
			</c:forEach>
			<td class="sorting_disabled" rowspan="1" colspan="2">${score.periodCountDepartment}</td>
				
		</tr>
	
		<tr role="row">
			<th class="sorting_disabled" rowspan="1" colspan="2" >积分规则</th>
			<th class="sorting_disabled" rowspan="1" colspan="28" ></th>
		</tr>
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

