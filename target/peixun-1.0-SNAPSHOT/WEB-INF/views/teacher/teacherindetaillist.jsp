<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
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
	
			<!-- <th class="sorting_asc" tabindex="0" aria-controls="sample_1"
				rowspan="1" colspan="1" aria-sort="ascending" style="width: 182px;">公司(单位)</th> -->
			<th class="sorting" rowspan="1" colspan="1"
				style="width: 130px;"  id="nameorder_id" >讲师名称
                    <%--  <select id="nameselect_id" name="projectTime">
				      <option value="0"> ${selectNameVal}  </option>
				        <option value="1">升序</option>
				        <option value="2">降序</option>
				      </select> --%>
                                
				</th>
			<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 304px;">讲师单位</th>
			 
			<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 183px;">讲师一级部门</th>
			<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 130px;">项目名称</th>
			<th class="sorting" rowspan="1" colspan="1"
				style="width: 160px;" id="projectname_id" >项目时间 <%-- <select id="select_id" name="nameselect">
				                               <option value="0"> ${selectVal} </option>
				                                <option value="1">升序</option>
				                                <option value="2">降序</option>
				                             </select> --%>
				     
				</th>
			<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 183px;">课程名称</th>
			<!-- 	<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 304px;">课程时间</th> -->
				<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 183px;">级别</th>
				<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 80px;">课时</th>
				<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 80px;">课酬积分</th>
				<th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 80px;">所得积分</th>
            <!-- <th class="sorting_disabled" rowspan="1" colspan="1"
				style="width: 80px;">操作</th> -->
		</tr>
	</thead>
	<tbody>
  
	  
		<c:forEach items="${teacherInDetailModels}" var="t">
			<tr class="gradeX odd" role="row">
				<!-- <td></td> --> 
	 
				<td class="sorting_1">${t.teacherName}</td>
				<td tlass="sorting_1">${t.parentDepartment}</td>
				<td class="sorting_1">${t.department}</td>
				<td class="sorting_1">${t.projectName}</td>
				<td class="sorting_1">
				    
				<fmt:formatDate  value="${t.projectStartTime}" pattern="yyyy.MM.dd HH:mm:ss"/>
				-
				<fmt:formatDate  value="${t.projectEndTime}" pattern="yyyy.MM.dd HH:mm:ss"/>
				 </td>	
				<td class="sorting_1">${t.name }</td>
				<%-- <td class="sorting_1">
				<fmt:formatDate  value="${t.beginTime}" pattern="yyyy.MM.dd HH:mm:ss"/>
				-
				<fmt:formatDate  value="${t.beginTime}" pattern="yyyy.MM.dd HH:mm:ss"/>
				 </td> --%>
				 
				 <td class="sorting_1">
				 <c:if test="${t.projectLevel eq 1}">
				      公司级别
				 </c:if>
				  <c:if test="${t.projectLevel ne 1}">
				      团建
				 </c:if>
				</td>
				<td class="sorting_1">${t.classHour}</td>
				<td class="sorting_1">${t.payClassScore}</td>
 
				<td class="sorting_1">${t.teacherScore}</td>
				 
				<%-- <td class="sorting_1"><a href="${ctx}/update/${t.id}">修改</a>| 
		     <a onclick="javascript:if (confirm('确定删除吗？')) { return true;}else{return false;};"  href="${ctx}/delete/${t.id}">删除</a>
		        </td> --%>
			</tr>
		</c:forEach>

	</tbody>
</table>
<!-- 
  <button type="button"   onclick="history.back()"  class="btn default">返回</button> -->
<script>
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
			loadListDataPaginatorPost (paginatorInfo,parameter);
		}
	};
	$('#paginator').bootstrapPaginator(options);
	 
	 
		 $("#projectname_id").click(function(){
			 projectnameorder();  
		 });  
		
		  
		   function projectnameorder(){
			   var yearVal = $("#year").val();
			   var selectVal = "";
			   if(classname==1){
				   selectVal="升序"; 
			   }
			   if(classname==2){
				   selectVal="降序"; 
			   }
			   paginatorInfo["page"] = 1;
			   parameter = {"selectVal":selectVal,"year":yearVal};
			   loadListDataPaginatorPostWithCallback(paginatorInfo,parameter,callback); 
		      } 
	 
	
	    function callback(){
	
	 	   if(classname==1){
				classname=2;  
				$("#projectname_id").attr("class","sorting_asc"); 
				
		     } else if(classname==2){ 
				classname=1; 
				$("#projectname_id").attr("class","sorting_desc"); 
		
		    } 
	        } 
	    
	    $("#nameorder_id").click(function(){
			 nameorder();  
		 });  
		
		  
		   function nameorder(){
			   var yearVal = $("#year").val();
			   var selectNameVal = "";
			   if(classname==1){
				   selectNameVal="升序"; 
			   }
			   if(classname==2){
				   selectNameVal="降序"; 
			   }
			   paginatorInfo["page"] = 1;
			   parameter = {"selectNameVal":selectNameVal,"year":yearVal};
			   loadListDataPaginatorPostWithCallback(paginatorInfo,parameter,callbackname); 
		      } 
	 
	
	    function callbackname(){
	
	 	   if(classname==1){
				classname=2;  
				$("#nameorder_id").attr("class","sorting_asc"); 
				
		     } else if(classname==2){ 
				classname=1; 
				$("#nameorder_id").attr("class","sorting_desc"); 
		
		    } 
	        } 
});
	       
</script>

