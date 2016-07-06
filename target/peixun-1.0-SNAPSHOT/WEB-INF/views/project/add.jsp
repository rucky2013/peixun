<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page
        import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException" %>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="modal fade" id="new" tabindex="-1" role="basic"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true"></button>
                <h4 class="modal-title">新增项目</h4>
            </div>
            <div class="modal-body">


                <div class="portlet-body form">
                    <!-- BEGIN FORM-->
                    <form id="form_sample_2" class="form-horizontal" method="post" action="${ctx }/project/save"
                          onsubmit="return validname();">
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
                                        <input type="text" class="form-control" id="projectname_id" name="name"/>
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
                                        <input type="text" size="16"  id="beginTime_id" readonly class="form-control" name="beginTime">
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
                                        <input type="text" class="form-control" id="endTime_id" readonly name="endTime">
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
                                        <input type="text" class="form-control" readonly name="ticketTime">
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
                                <label class="control-label col-md-3">课程级别 <span class="required">
										* </span>
                                </label>
                                <div class="col-md-4">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <select class="form-control" name="projectLevel">
                                            <c:forEach items="${projectLevels}" var="pL">
                                                <option value="${pL.id}">${pL.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
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
                                <label class="control-label col-md-3">抢票数量<span class="required">
										* </span>
                                </label>
                                <div class="col-md-4">
                                    <div class="input-icon right">
                                        <i class="fa"></i>
                                        <input type="text" class="form-control" name="ticketNum" id="ticketNum"/>
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
                                            <textarea class="form-control" data-provide="markdown" rows="3" name="content"
                                                      id="content"></textarea>
                                    </div>
                                </div>
                            </div>


                        </div>
                        <div class="form-actions">
                            <div class="row">
                                <div class="col-md-offset-3 col-md-9">
                                    <button type="submit" id="submit_btn" class="btn green">提交</button>
                                    <button type="button" class="btn default" onclick="cancle()">取消</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- END FORM-->
                </div>

                <!-- END FORM-->
            </div>
        </div>
    </div>
    <!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<script>
    jQuery(document).ready(function () {
        $('#ticketType').change(function (e) {
            var selectVal = $('#ticketType').val();
            if (selectVal == 1) {
                $('#changeHidden').show();
            }
            if (selectVal == 2) {
                $('#changeHidden').hide();
                $('#ticketNum').val("");
            }
        });
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        Demo.init(); // init demo features
        FormValidation.init();
        $(".form_datetime").datetimepicker({
            autoclose: true,
            isRTL: Metronic.isRTL(),
            format: "yyyy-mm-dd hh:ii",
            pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left")
        });


        function validname() {
            var projectname = $("#projectname_id").val();

            if (projectname = "" || projectname == null || projectname == undefined) {

                return false;
            }
            return true;

        }
    });
    function cancle() {
        window.location.href = '${ctx}/project/list';
    }
    $("#submit_btn").click(function(){
    	 var startDateFrom=$("#beginTime_id").val();
			 var startDateTo= $("#endTime_id").val();
			 
			 if(startDateFrom>startDateTo){
				 bootbox.alert("开始时间不能大于结束时间");
				  return false;
			 }
			 
		
    }); 
</script>
