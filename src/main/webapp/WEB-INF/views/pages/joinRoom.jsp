<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/10
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="modal/confirmServiceModal.jsp" %>

<div class="row">
    <div class="col-lg-8 col-lg-offset-2">
        <section class="z-depth-1 panel" style="margin-bottom: 100px">
            <div class="panel-body">
                <form:form name="createform" class="form-horizontal" onsubmit="return validateForm()"
                           modelAttribute="roomSelection" id="create-room-form" method="post"
                           action="/room/join/search">
                    <fieldset>
                        <legend><span class="glyphicon glyphicon-home"> </span> 选择地点</legend>
                        <div class="form-group">
                            <div class="form-inner-group2">
                                <div class="col-lg-5">
                                    <form:input id="start-point" type="text" class="form-control" placeholder="出发地点"
                                                path="startPoint"/>
                                </div>

                                <div id="switch-btn" class="col-lg-2 text-center">
                                    <span class="glyphicon glyphicon-refresh" style="margin-top: 30px;"></span>
                                    <span class="glyphicon-class"
                                          style="display: inline;text-align: center;word-wrap: break-word;">交换地点</span>
                                </div>

                                <div class="col-lg-5">
                                    <form:input id="end-point" type="text" class="form-control" placeholder="结束地点"
                                                path="endPoint"/>
                                </div>

                                <div class="col-lg-7">
                                    <form:errors cssClass="handle-error" path="startPoint"/>
                                </div>

                                <div class="col-lg-5">
                                    <form:errors cssClass="handle-error" path="endPoint"/>
                                </div>
                            </div>
                        </div>

                    </fieldset>

                    <fieldset>
                        <legend><span class="glyphicon glyphicon-time"> </span> 选择时间</legend>
                        <div class="form-group">
                            <div class="form-inner-group">

                                <div>
                                    <form:input path="startDate" type="date" class="form-control"/>
                                </div>
                                <div>
                                    <form:errors cssClass="handle-error" path="startDate"/>
                                </div>
                            </div>
                        </div>
                            <%--<div class="form-group">--%>
                            <%--<div class="form-inner-group">--%>

                            <%--<div>--%>
                            <%--<form:input path="startTime" type="time" class="form-control"/>--%>
                            <%--</div>--%>
                            <%--<div>--%>
                            <%--<form:errors cssClass="handle-error" path="startTime"/>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <%--</div>--%>


                    </fieldset>

                    <fieldset>
                        <legend><span class="glyphicon glyphicon-book"></span> 服务条款</legend>
                        <div class="form-group">
                            <div class="form-inner-group">

                                <ul class="col-lg-12">
                                    <li>本平台只提供约车信息交流服务，不提供叫车、联系司机等服务</li>
                                </ul>
                            </div>
                        </div>

                        <fieldset class="form-group text-center">
                            <input type="checkbox" id="checkbox1" tabindex="158">
                            <label for="checkbox1">我已查看并同意用户条款</label>
                        </fieldset>
                    </fieldset>

                    <input class="finish btn btn-outline-warning" type="submit" value="开始查找">
                </form:form>
            </div>
        </section>
    </div>
</div>

<style type="text/css">
    .handle-error {
        color: red;
    }
</style>

<script type="text/javascript">
    $(document).ready(function () {
        $('.form-group > div').addClass('col-lg-12');
        $('.form-inner-group > div').addClass('col-lg-12');

        $('#switch-btn').click(function () {
            var left = document.getElementById('start-point').value;
            document.getElementById('start-point').value = document.getElementById('end-point').value;
            document.getElementById('end-point').value = left;
        });
    });


</script>