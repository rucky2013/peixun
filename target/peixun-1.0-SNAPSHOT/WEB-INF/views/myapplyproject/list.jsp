<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page
        import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException" %>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

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
                <h1>我的项目申请</h1>
            </div>
            <!-- END PAGE TITLE -->
        </div>
    </div>
    <!-- END PAGE HEAD -->
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content">
        <div class="container">
            <!-- BEGIN PAGE BREADCRUMB -->
            <ul class="page-breadcrumb breadcrumb">
                <li>
                    <a href="${ctx}/tohomepage">首页</a><i class="fa fa-circle"></i>
                </li>
                <li class="active">
                    我的项目申请
                </li>
            </ul>
            <div class="container">
                <!-- BEGIN PAGE CONTENT INNER -->
                <div class="col-md-12">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet light">
                        <div class="row">
                            <form class="form-horizontal" id="myApplysearchForm">
                                <div class="portlet-body form">
                                    <!-- BEGIN FORM-->
                                    <div class="row container">
                                        <div class="form-body col-md-11 content">

                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">项目名称</label>
                                                    <div class="col-md-9">
                                                        <input type="text" class="form-control " id="searchName">
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">开始时间 </label>
                                                    <div class="col-md-9">
                                                        <div class="input-group date form_datetime">
                                                            <input type="text" size="16" id="searchBeginTime" readonly
                                                                   class="form-control" name="beginTime">
												<span class="input-group-btn">
												<button class="btn default date-set" type="button"><i
                                                        class="fa fa-calendar"></i></button>
												</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">结束时间
                                                    </label>
                                                    <div class="col-md-9">
                                                        <div class="input-group date form_datetime">
                                                            <div class="input-icon right">
                                                                <i class="fa"></i>
                                                                <input type="text" id="searchEndTime"
                                                                       class="form-control" readonly name="endTime">
                                                            </div>
                                        <span class="input-group-btn">
                                        <button class="btn default date-set" type="button"><i
                                                class="fa fa-calendar"></i>
                                        </button>
                                        </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>  
                                       <!--      <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">开始时间</label>
                                                    <div class="col-md-9">
                                                        <div class="input-group date date-picker"
                                                            data-date-format="yyyy-mm-dd">
                                                            <input type="text" class="form-control " readonly
                                                                name="beginTime" id="searchBeginTime" /> <span
                                                                class="input-group-btn">
                                                                <button class="btn default" type="button">
                                                                    <i class="fa fa-calendar"></i>
                                                                </button>
                                                            </span>
                                                        </div>
                                                    </div>  
                                                </div> -->
                                           <!--  </div> -->
                                            	<!-- <div class="col-md-4">
                                                  <div class="form-group">
                                                        <label class="col-md-3 control-label">结束时间</label>
                                                        <div class="col-md-9">
                                                            <div class="input-group date date-picker"
                                                                data-date-format="yyyy-mm-dd">
                                                                <input type="text" class="form-control " readonly
                                                                    name="endTime" id="searchEndTime" /> <span
                                                                    class="input-group-btn">
                                                                    <button class="btn default" type="button">
                                                                        <i class="fa fa-calendar"></i>
                                                                    </button>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>  
                                                </div> -->
                                        </div>
                                    </div>
                                    <div class="row container">
                                        <div class="form-body col-md-11 content">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">门票类型</label>
                                                    <div class="col-md-9">
                                                        <select class="form-control" name="ticketType"
                                                                id="searchTicketType">
                                                            <option></option>
                                                            <c:forEach items="${ticketTypes}" var="tt">
                                                                <option value="${tt.id}">${tt.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <%-- <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="col-md-3 control-label">项目类别</label>
                                                    <div class="col-md-9">
                                                        <select class="form-control" name="projectLevel"
                                                            id="searchProjectLevel">
                                                            <option></option>
                                                            <c:forEach items="${projectLevels}" var="pL">
                                                                <option value="${pL.id}">${pL.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div> --%>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div>
                            <div id="data_my" class="portlet-body">
                                <table class="table table-striped table-bordered table-hover"
                                       id="datatable_ajax">
                                    <thead>
                                    <tr role="row">
                                        <th>项目名称</th>
                                        <th>开始时间</th>
                                        <th>结束时间</th>
                                        <th>抢票时间</th>
                                        <th>门票类型</th>
                                        <th>项目类别</th>
                                        <th>项目状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tbodyList">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>

                <!-- END PAGE CONTENT INNER -->
            </div>
        </div>
        <!-- END PAGE CONTENT -->
        <%@ include file="/WEB-INF/views/myapplyproject/add.jsp" %>
    </div>
</div>
<script>
    /*Javascript代码片段*/
    $(function () {
    });
    $(".form_datetime").datetimepicker({
        autoclose: true,
        isRTL: Metronic.isRTL(),
        format: "yyyy-mm-dd hh:ii",
        pickerPosition: (Metronic.isRTL() ? "bottom-right" : "bottom-left")
    });
</script>
<script

        src="${ctx}/static/metronic/theme/assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>

<script src="${ctx}/static/js/myapplyprojectlist/myapplyprojectlist.js"></script>
<script src="${ctx}/static/js/dateFormat.js"></script>
</body>
</html>
