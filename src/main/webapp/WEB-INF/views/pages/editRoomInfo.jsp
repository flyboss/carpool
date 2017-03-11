<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/18
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="border-head">
    <div class="row">
        <h3 class="col-lg-3">房间信息</h3>
    </div>
</div>
<style type="text/css">
    dt {
        padding-top: 4px;
        padding-bottom: 4px;
    }
</style>
<%@include file="../template/roomState.jsp" %>

<div class="row center-block" style="margin-bottom: 90px;">
    <div class="col-lg-8 col-lg-offset-2 text-center detail_dev">
        <section class="z-depth-1 hoverable panel" style="padding: 15px;">
            <header class="panel-heading">
                房间具体信息
            </header>
            <div class="panel-body">

                <form:form name="editRoom" id="editRoom" modelAttribute="room" method="post" action="/room/edit">
                    <dl class="dl-horizontal">
                        <dt>房主姓名</dt>
                        <dd>${room.host.username}</dd>
                        <dt>房主信用等级</dt>
                        <dd>${room.host.credit}</dd>
                    </dl>

                    <fieldset>
                        <legend><span class="glyphicon glyphicon-home"></span> 房间信息</legend>
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
                        <div class="form-group">
                            <div class="form-inner-group">

                                <div>
                                    <form:input type="text" class="form-control" placeholder="房间名称" path="roomname"/>
                                </div>
                                <div>
                                    <form:errors cssClass="handle-error" path="roomname"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="form-inner-group">

                                <div>
                                    <form:input type="number" class="form-control" placeholder="人数上限"
                                                path="numberLimit"/>
                                </div>
                                <div>
                                    <form:errors cssClass="handle-error" path="numberLimit"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="form-inner-group">

                                <div>
                                    <form:input type="text" class="form-control" placeholder="备注" path="note"/>
                                </div>
                                <div>
                                    <form:errors cssClass="handle-error" path="note"/>
                                </div>
                            </div>
                        </div>

                    </fieldset>

                    <fieldset>
                        <legend><span class="glyphicon glyphicon-time"></span> 时间信息</legend>
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
                        <div class="form-group">
                            <div class="form-inner-group">

                                <div>
                                    <form:input path="startTime" type="time" class="form-control"/>
                                </div>
                                <div>
                                    <form:errors cssClass="handle-error" path="startTime"/>
                                </div>
                            </div>
                        </div>


                    </fieldset>


                    <hr>

                    <c:choose>
                        <c:when test="${room.state==ROOM_STATE_UNLOCKED}">
                            <div class="btn-effect btn btn-warning btn-lg" onclick="location.href='/room/lock/${room.id}'">
                                <i class="fa fa-lock"></i> 锁定房间
                            </div>
                            <div>
                                <span>锁定后，其他人无法加入</span>
                                <span>您可以随时解锁</span>
                            </div>
                        </c:when>
                        <c:when test="${room.state==ROOM_STATE_LOCKED}">
                            <div class="btn-effect btn btn-info btn-lg" onclick="location.href='/room/unlock/${room.id}'">
                                <i class="fa fa-unlock"></i><a href="/"> 解锁房间</a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="btn-effect btn btn-default btn-lg btn-block">
                                <i class="fa fa-exclamation-triangle"></i> ~
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="btn btn-outline-warning btn-lg" onclick="$('#editRoom').submit();">
                        <i class="fa fa-check"></i> 确认无误并提交
                    </div>
                    <form:input path="id" type="hidden"/>
                    <form:input path="state" type="hidden"/>
                </form:form>
            </div>
        </section>
    </div>
</div>

<style type="text/css">
    legend {
        text-align: left !important;
    }

    .btn-effect {
        width: 75%;
        margin: 10px;
    }

    .btn-effect a {
        color: white;
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