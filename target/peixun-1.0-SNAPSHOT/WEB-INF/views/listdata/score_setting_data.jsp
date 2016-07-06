
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="dataTables_scrollHead" style="overflow: hidden; position: relative; border: 0px; width: 100%;">
		<div class="dataTables_scrollHeadInner" style="box-sizing: content-box; width: 1088px; padding-right: 12px;"><table class="table table-striped table-hover dataTable no-footer" role="grid" style="margin-left: 0px; width: 1088px;">
					<thead>
							<tr class="text-center" role="row" style="height: 0px; text-algin:center;">
								<th class="sorting_disabled" aria-controls="sample_5" rowspan="1" colspan="1" style="width: 248px; text-align:center;">
									分部
								</th>
								<th class="sorting_disabled" aria-controls="sample_5" rowspan="1" colspan="1" style="width: 194px;text-align:center; ">
									部门
								</th>
								<th class="" aria-controls="sample_5" rowspan="1" colspan="1" style="width: 194px; text-align:center;">
									 姓名
								</th>
								<c:forEach var="i" begin="1" end="12" step="1">
								<th class="" aria-controls="sample_5" rowspan="1" colspan="1" style="width: 194px;text-align:center;">
									 ${i}月
								</th>
								</c:forEach>
								<th class="sorting_disabled text-center" rowspan="1" colspan="1" style="width: 115px;">编辑</th>
								</tr>
							</thead>
						</table>
					</div>
</div>

<div class="dataTables_scrollBody" style="overflow: auto; height: 500px; width: 100%;">

<table
	style=""
	class="table table-striped table-hover table-bordered dataTable no-footer text-center"
	id="sample_editable_1" role="grid" aria-describedby="sample_1_info">
	
	<thead>
							<tr class="text-center" role="row" style="height: 0px; text-algin:center;">
								
								
								<th class="sorting_disabled" aria-controls="sample_5" rowspan="1" colspan="1" style="width: 248px; height:0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; text-align:center;">
									<div class="dataTables_sizing" style="height:0;overflow:hidden;">
									</div>
								</th>
								<th class="sorting_disabled" aria-controls="sample_5" rowspan="1" colspan="1" style="width: 194px;height:0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;text-align:center; ">
									<div class="dataTables_sizing" style="height:0;overflow:hidden;">
									</div>
								</th>
								<th class="" aria-controls="sample_5" rowspan="1" colspan="1" style="width: 194px;height:0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; text-align:center;">
									<div class="dataTables_sizing" style="height:0;overflow:hidden;">
									</div>
								</th>
								<c:forEach var="i" begin="1" end="12" step="1">
								<th class="" aria-controls="sample_5" rowspan="1" colspan="1" style="width: 194px;height:0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;text-align:center;">
									 
									<div class="dataTables_sizing" style="height:0;overflow:hidden;">
									</div>
								</th>
								
								</c:forEach>
								
								<th class="sorting_disabled text-center" rowspan="1" colspan="1" style="width: 115px;height:0px; padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px;">
									<div class="dataTables_sizing" style="height:0;overflow:hidden;">
									</div>
								</th>
								
				
								</tr>
	</thead>
	
	<tbody>
	<c:forEach items="${settings}" var="s">
		<tr role="row" class="text-center" id="${s.id}">
			<td class="sorting_disabled" rowspan="1" colspan="1" style="width:115px; vertical-align:middle" >${s.parentDepartmentName}</td>
			<td class="sorting_disabled" rowspan="1" colspan="1" style="width:115px;vertical-align:middle" >${s.departmentName}</td>
			<td class="sorting_disabled" rowspan="1" colspan="1" style="width:115px;vertical-align:middle" >${s.username}</td>
			<td class="sorting_disabled" id="s_${s.id}_1" editable="true" rowspan="1" colspan="1"  style="vertical-align:middle;width: 115px;">${s.score_1}</td>
			<td class="sorting_disabled" id="s_${s.id}_2" editable="true" rowspan="1" colspan="1"  style="vertical-align:middle;width: 115px;">${s.score_2}</td>
			<td class="sorting_disabled" id="s_${s.id}_3" editable="true" rowspan="1" colspan="1"  style="vertical-align:middle;width: 115px;">${s.score_3}</td>
			<td class="sorting_disabled" id="s_${s.id}_4" editable="true" rowspan="1" colspan="1"  style="vertical-align:middle;width: 115px;">${s.score_4}</td>
			<td class="sorting_disabled" id="s_${s.id}_5" editable="true" rowspan="1" colspan="1"  style="vertical-align:middle;width: 115px;">${s.score_5}</td>
			<td class="sorting_disabled" id="s_${s.id}_6" editable="true" rowspan="1" colspan="1"  style="vertical-align:middle;width: 115px;">${s.score_6}</td>
			<td class="sorting_disabled" id="s_${s.id}_7" editable="true" rowspan="1" colspan="1"  style="vertical-align:middle;width: 115px;">${s.score_7}</td>
			<td class="sorting_disabled" id="s_${s.id}_8" editable="true" rowspan="1" colspan="1"  style="vertical-align:middle;width: 115px;">${s.score_8}</td>
			<td class="sorting_disabled" id="s_${s.id}_9" editable="true" rowspan="1" colspan="1"  style="vertical-align:middle;width: 115px;">${s.score_9}</td>
			<td class="sorting_disabled" id="s_${s.id}_10" editable="true" rowspan="1" colspan="1" style="vertical-align:middle;width: 115px;">${s.score_10}</td>
			<td class="sorting_disabled" id="s_${s.id}_11" editable="true" rowspan="1" colspan="1" style="vertical-align:middle;width: 115px;">${s.score_11}</td>
			<td class="sorting_disabled" id="s_${s.id}_12" editable="true" rowspan="1" colspan="1" style="vertical-align:middle;width: 115px;">${s.score_12}</td>
			<td class="sorting_disabled" rowspan="1" colspan="1"  style="width: 115px;vertical-align:middle""><a class="edit" href="javascript:;"> 编辑 </a></td>

		</tr>
	</c:forEach> 
	</tbody>
</table>
</div>
<script>
	
	
	//$('#paginator').bootstrapPaginator(options);
	 jQuery(document).ready(function() {   
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
		 Metronic.init(); // init metronic core components
			
	   	TableEditable.init();
		 
	   	$('#paginator').bootstrapPaginator(options);
		 
	   		
	});

</script>

