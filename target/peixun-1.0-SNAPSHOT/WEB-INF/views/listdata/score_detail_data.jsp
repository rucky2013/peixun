
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<table
	class="table table-striped table-bordered table-hover dataTable no-footer"
	id="sample_1" role="grid" aria-describedby="sample_1_info">
	<thead>
	
		<tr>
			<th colspan="8" style="text-align:center">公司基本积分明细</th>
		</tr>
		<tr role="row">
			
			<th class="sorting_disabled" rowspan="1" colspan="1" style="width: 115px;">项目名称</th>
			<th class="sorting_disabled" rowspan="1" colspan="1" style="width: 354px;">项目时间</th>
			<th class="sorting_disabled" rowspan="1" colspan="1" style="width: 115px;">课程名称</th>
			<th class="sorting_disabled" rowspan="1" colspan="1" style="width: 354px;">课程时间</th>
			<th class="sorting_disabled" rowspan="1" colspan="1" style="width: 50px;">课程学时</th>
			<th class="sorting_disabled" rowspan="1" colspan="1" style="width: 50px;">课程积分</th>
			<th class="sorting_disabled" rowspan="1" colspan="1" style="width: 50px;">所得积分</th>
			
		</tr>
	</thead>
	<tbody>
		<c:set var="is_no_data" value="true" />
		<c:forEach items="${score.scoreMonthModels}" var="sm">
			<c:forEach items="${sm.scoreModel}" var="m">
				<c:if test="${m.projectId != null}">
					<c:choose>
						<c:when test="${m.projectLevel == 0}">
						<c:set var="is_no_data" value="false" />
							<c:forEach items="${m.classes}" var="c">
								<tr role="row">
									<td class="sorting_disabled" rowspan="1" colspan="1">${m.projectName}</td>
									<td class="sorting_disabled" rowspan="1" colspan="1">${m.projectStartTime}-${m.projectEndTime}</td>
									<td class="sorting_disabled" rowspan="1" colspan="1">${c.name}</td>
									<td class="sorting_disabled" rowspan="1" colspan="1">${c.startTime}-${c.endTime}</td>
									<td class="sorting_disabled" rowspan="1" colspan="1">${c.period}</td>
									<td class="sorting_disabled" rowspan="1" colspan="1">${m.projectName}</td>
									<td class="sorting_disabled" rowspan="1" colspan="1">${c.real}</td>
								</tr>
							</c:forEach>	
						</c:when>
					</c:choose>
				</c:if>
			</c:forEach>
		</c:forEach>
		<c:if test="${is_no_data == true}">
			<td class="sorting_disabled text-center" rowspan="1" colspan="7">没有数据</td>
		</c:if>
	</tbody>
</table>
<br />
<table
	class="table table-striped table-bordered table-hover dataTable no-footer"
	id="sample_1" role="grid" aria-describedby="sample_1_info">
	<thead>
		<tr>
			<th colspan="8" style="text-align:center">部门基本积分明细</th>
		</tr>
		<tr role="row">
			
			<th class="" rowspan="1" colspan="1" style="width: 115px;">项目名称</th>
			<th class="" rowspan="1" colspan="1" style="width: 354px;">项目时间</th>
			<th class="" rowspan="1" colspan="1" style="width: 115px;">课程名称</th>
			<th class="" rowspan="1" colspan="1" style="width: 354px;">课程时间</th>
			<th class="" rowspan="1" colspan="1" style="width: 50px;">课程学时</th>
			
		</tr>
	</thead>
	<tbody>
		<c:set var="is_no_data" value="true" />
		<c:forEach items="${score.scoreMonthModels}" var="sm">
			<c:forEach items="${sm.scoreModel}" var="m">
				<c:if test="${m.projectId != null}">
					<c:choose>
						<c:when test="${m.projectLevel == 1}">
						<c:set var="is_no_data" value="false" />
								<c:forEach items="${m.classes}" var="c">
									<tr role="row">
										<td class="sorting_disabled" rowspan="1" colspan="1">${m.projectName}</td>
										<td class="sorting_disabled" rowspan="1" colspan="1">${m.projectStartTime}-${m.projectEndTime}</td>
										<td class="sorting_disabled" rowspan="1" colspan="1">${c.name}</td>
										<td class="sorting_disabled" rowspan="1" colspan="1">${c.startTime}-${c.endTime}</td>
										<td class="sorting_disabled" rowspan="1" colspan="1">${c.period}</td>
									</tr>
								</c:forEach>	
						</c:when>
					</c:choose>
				</c:if>
			</c:forEach>
		</c:forEach>
		<c:if test="${is_no_data == true}">
			<td class="sorting_disabled text-center" rowspan="1" colspan="5">没有数据</td>
		</c:if>
	</tbody>
</table>


<table
	class="table table-striped table-bordered table-hover dataTable no-footer"
	id="sample_1" role="grid" aria-describedby="sample_1_info">
	<thead>
		<tr>
			<th colspan="8" style="text-align:center">讲师培训所得课时明细</th>
		</tr>
		<tr role="row">
			
			<th class="" rowspan="1" colspan="1" style="width: 115px;">项目名称</th>
			<th class="" rowspan="1" colspan="1" style="width: 354px;">项目时间</th>
			<th class="" rowspan="1" colspan="1" style="width: 115px;">课程名称</th>
			<th class="" rowspan="1" colspan="1" style="width: 354px;">课程时间</th>
			<th class="" rowspan="1" colspan="1" style="width: 354px;">级别</th>
			<th class="" rowspan="1" colspan="1" style="width: 50px;">课程课时</th>
			<th class="" rowspan="1" colspan="1" style="width: 50px;">实际课时</th>
			
		</tr>
	</thead>
	<tbody>
		<c:set var="is_no_data" value="true" />
		<c:forEach items="${score.scoreMonthModels}" var="sm">
			<c:forEach items="${sm.scoreModel}" var="m">
				<c:if test="${m.projectId != null}">
					<c:choose>
						<c:when test="${m.projectLevel == 1}">
								<c:forEach items="${m.classes}" var="c">
									<c:if test="${c.teacherType==1 && c.teacherid==m.userId}">
									<c:set var="is_no_data" value="false" />
										<tr role="row">
											<td class="sorting_disabled" rowspan="1" colspan="1">${m.projectName}</td>
											<td class="sorting_disabled" rowspan="1" colspan="1">${m.projectStartTime}-${m.projectEndTime}</td>
											<td class="sorting_disabled" rowspan="1" colspan="1">${c.name}</td>
											<td class="sorting_disabled" rowspan="1" colspan="1">${c.startTime}-${c.endTime}</td>
											<td class="sorting_disabled" rowspan="1" colspan="1">
												<c:choose>
													<c:when test="${m.projectLevel == 0}">
														部门
													</c:when>
													<c:otherwise>
														公司
													</c:otherwise>
												</c:choose>
											</td>
											<td class="sorting_disabled" rowspan="1" colspan="1">${c.peroidTeacherShould}</td>
											<td class="sorting_disabled" rowspan="1" colspan="1">${c.peroidTeacher}</td>

										</tr>
									</c:if>
								</c:forEach>	
						</c:when>
					</c:choose>
				</c:if>
			</c:forEach>
		</c:forEach>
		<c:if test="${is_no_data == true}">
			<td class="sorting_disabled text-center" rowspan="1" colspan="7">没有数据</td>
		</c:if>
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

