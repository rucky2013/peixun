<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
  
<div style="margin-left: 200px" >
   <form id="fileupload" action="uploadFile" method="POST" enctype="multipart/form-data">								
		<div class="row fileupload-buttonbar">
			<div class="col-lg-7">				
				<span class="btn green fileinput-button">
					<i class="fa fa-plus"></i>
					<span>选择文件 </span>
					<input type="file" name="files[]" >
				</span>
				<button type="submit" class="btn blue start">
					<i class="fa fa-upload"></i>
					<span>开始上传 </span>
				</button>
				<button type="reset" class="btn warning cancel">
					<i class="fa fa-ban-circle"></i>
					<span>取消上传 </span>
				</button>
				<!-- 测试下载 -->	
				
			<a href="${ctx }/fileManager/download?fileId=22">下载文件</a>	
					
			</div>			
		</div>
		
	</form>
</div>









