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
					<h1>编辑项目申请</h1>
				</div>
				<!-- END PAGE TITLE -->
			</div>
		</div>
		<div class="page-content">
			<div class="container">
			<ul class="page-breadcrumb breadcrumb">
				<li>
					<a href="${ctx}/tohomepage">首页</a><i class="fa fa-circle"></i>
				</li>
				<li class="active">
					 编辑项目申请
				</li>
			</ul>
				<div class="portlet light">
				
				<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="${ctx}/myapplyproject/update" class="form-horizontal"
							  id ="form_sample_2" 
							method="post" > 
							<input type="hidden" value="${project.id }" name="id" />
							 <input type="hidden" value="${project.status }" name="status"/>
						<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										 对不起，验证失败，请检查下面字段是否为空或者联系方式字段是不是数字格式！
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										 恭喜你，验证成功！
									</div>
	
									<div class="form-group">
										<label class="control-label col-md-3">课程名称 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control"   id="projectname_id" value="${project.name}"  name="name"/>
											</div>
										</div>
									</div>
										
										 <div class="form-group">
                                        <label class="control-label col-md-3">开始时间 <span class="required">
										* </span></label>
                                        <div class="col-md-4">
                                            <div class="input-group date form_datetime">
                                             <div class="input-icon right">
												<i class="fa"></i>
                                                <input type="text" class="form-control" readonly name="beginTime"
                                                   id="beginTime_id"    value='<fmt:formatDate value="${project.beginTime}" pattern="yyyy-MM-dd HH:mm" ></fmt:formatDate>'/>
												  </div>
												<span class="input-group-btn">
												<button class="btn default date-set" type="button"><i
                                                        class="fa fa-calendar"></i></button>
												</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-md-3">结束时间 <span class="required">
										* </span>
                                        </label>
                                        <div class="col-md-4">
                                            <div class="input-group date form_datetime">
                                             <div class="input-icon right">
												<i class="fa"></i>
                                                <input type="text" class="form-control" readonly name="endTime"
                                                     id="endTime_id"  value='<fmt:formatDate value="${project.endTime}" pattern="yyyy-MM-dd HH:mm" ></fmt:formatDate>'/>
                                                  </div>
                                        <span class="input-group-btn">
                                        <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i>
                                        </button>
                                        </span>
                                            </div>
                                        </div>
                                    </div>	
                                    
                                       <div class="form-group">
                                        <label class="control-label col-md-3">抢票时间 <span class="required">
										* </span>
                                        </label>
                                        <div class="col-md-4">
                                            <div class="input-group date form_datetime">
                                             <div class="input-icon right">
												<i class="fa"></i>
                                                <input type="text" class="form-control" readonly name="ticketTime"
                                                       value='<fmt:formatDate value="${project.ticketTime}" pattern="yyyy-MM-dd HH:mm" ></fmt:formatDate>'/>
                                                </div>
                                        <span class="input-group-btn">
                                        <button class="btn default date-set" type="button"><i class="fa fa-calendar"></i>
                                        </button>
                                        </span>
                                            </div>
                                        </div>
                                    </div>
										<div class="form-group">
										<label class="control-label col-md-3">课程标签 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
										   <div class="input-icon right">
												<i class="fa"></i>
											<select class="form-control" name="tagId">
												<c:forEach items="${projectTags}" var="pt">
													<option value="${pt.id}">${pt.value}</option>
												</c:forEach>
											</select>
											</div>
										</div>
									</div>	
									<div class="form-group"> 
										<label class="control-label col-md-3">课程级别: <span class="required">
										* </span>
										
										</label>
										<div class="input-icon right">
										       <label class="control-label col-md-3"  style="text-align: left;" >
											    ${ projectLevels[0].value }
											    </label>
											    </div>
										 </div>  
									<div class="form-group">
										<label class="control-label col-md-3">取票类型 <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
										   <div class="input-icon right">
												<i class="fa"></i>
											<select class="form-control" name="ticketType" id="ticketType">
												<c:forEach items="${ticketTypes}" var="tt">
													<option value="${tt.id}">${tt.value}</option>
												</c:forEach>
											</select>
											</div>
										</div>
									</div>	
										 
									    <div class="form-group">
                                        <label class="control-label col-md-3">项目简介<span class="required">
												* </span>
                                        </label>
                                        <div class="col-md-4">
                                            <div class="input-icon right">
                                                <i class="fa"></i>
                                                <textarea class="form-control" rows="3" name="content"
                                                          id="content">${project.content}</textarea>
                                            </div>
                                        </div>
                                    </div>
									<div class="form-group">
										<label class="control-label col-md-3">抢票数量<span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<div class="input-icon right">
												<i class="fa"></i>
												<input type="text" class="form-control" value="${project.ticketNum}" name="ticketNum" id="ticketNum" />
											</div>
										</div>
									</div>
								 
								 
								</div>
							<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn green" id="submit_btn" onclick="save()">提交</button>
											<button type="button" class="btn default" onclick="cancle()" >取消</button>
										</div>
									</div>
								</div>
						</form>
						<!-- END FORM-->
					</div>

				</div>
			</div>
		</div>
	</div>
	<script>
		jQuery(document).ready(function() {
			// initiate layout and plugins
			/* FormValidation.init(); */
			
			Metronic.init(); // init metronic core components
	           Layout.init(); // init current layout
	Demo.init(); // init demo features
	   FormValidation.init();
	
	   
		$('#ticketType').change(function(e) {
			var selectVal = $('#ticketType').val();
			if(selectVal==1){
				$('#changeHidden').show();
			}
			if(selectVal==2){
				$('#changeHidden').hide();
				$('#ticketNum').val("");
			}
		});
			$('.date-picker').datepicker({
				rtl : Metronic.isRTL(),
				autoclose : true
			});
			
			
		});
		function cancle() {
			window.location.href = '${ctx}/myapplyproject/list';
		}
		
		  $(".form_datetime").datetimepicker({
	            autoclose: true,
	            isRTL: Metronic.isRTL(),
	            format: "yyyy-mm-dd hh:ii",
	            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left")
	        });  
		  $("#submit_btn").click(function(){
		     	 var startDateFrom=$("#beginTime_id").val();
					 var startDateTo= $("#endTime_id").val();
					 
					 if(startDateFrom>startDateTo){
						 bootbox.alert("开始时间不能大于结束时间");
						  return false;
					 }
					 
				
		     }); 
		  
		  
	</script>
</body>
</html>
