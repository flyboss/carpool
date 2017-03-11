<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/8
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link href="/static/css/mdb.css" rel="stylesheet"/>--%>
<div class="border-head">
    <div class="row">
        <h3 class="col-lg-3">选择拼车房间</h3><q></q>
    </div>
</div>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<link rel="stylesheet" href="<c:url value="/static/style/cityselect.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/static/style/frozen.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/static/style/car.css"/>">

<div class="top">
    <div id="showTitle" class="fl tab tabActive">创建新组</div>
    <div id="hideTitle" class="fl tab">加入旧组</div>
</div>

<div class="centerMain">
    <form:form class="form1 clearfix" modelAttribute="room" action="/room/add?id=2" method="post" name="form1">
        <div id="roomTitle" class="ui-form">
            <div class="ui-form-item ui-border-b">
                <label>房间名称</label>
                <div class="">
                    <form:input path="roomname" type="text" placeholder="请输入房间名称"/>
                </div>
            </div>
            <div style="padding-left: 15px;">
                <form:errors path="roomname" class="has-error help-inline"/>
            </div>
        </div>

        <div class="tab-container ui-form">
            <div class="leftInput">
                <div class="demo fl">
                    <label for="from1" class="text cf">起点</label>
                    <form:input path="startPoint" type="text" class="inputSpot fromSpot" id="from1" name="fromSpot"
                                value="${room.startPoint}"/>
                </div>
                <div class="change">
                    <span class="ico circle"></span>
                </div>
                <div class="demo fr">
                    <label for="to1" class="text dd">终点</label>
                    <form:input path="endPoint" type="text" class="inputSpot toSpot" id="to1" name="toSpot"
                                value="${room.endPoint}"/>
                </div>
            </div>

        </div>

        <div style="background: #f5f5f5;" class="ui-form">
            <div class="ui-form-item ui-border-b">
                <div class="demo fl">
                    <form:errors path="startPoint" class="has-error help-inline"/>
                </div>
                <div class="demo fr">
                    <form:errors path="endPoint" class="fr has-error help-inline"/>
                </div>
            </div>
        </div>

        <div class="ui-form">
            <span class="time-tag">出发时间（过期自动删除本条信息）</span>
            <div class="ui-form-item ui-border-b">
                <label for="from1date">出发日期</label>
                <form:input path="startDate" id="from1date" type="date"/>
            </div>
            <div style="padding-left: 15px;">
                <form:errors path="startDate" class="has-error help-inline"/>
            </div>
            <div class="ui-form-item ui-border-b">
                <label>出发时间</label>
                <form:input path="startTime" type="time"/>
            </div>
            <div style="padding-left: 15px;">
                <form:errors path="startTime" class="has-error help-inline"/>
            </div>
        </div>
        <!-- end -->
        <div class="mt10 ui-form">
            <div class="ui-form-item ui-form-item-r ui-border-b">
                <label>
                    验证码
                </label>
                <input type="text" placeholder="请输入您收到的验证码" style="margin-left:95px;">
                <!-- 若按钮不可点击则添加 disabled 类 -->
                <button type="button" class="ui-border-l" style="color:#75afca;">重新发送</button>
            </div>
        </div>
        <div class="ui-btn-wrap mt20 ui-form">
            <button class="ui-btn-lg ui-btn-success">
                确认无误，发布拼车信息
            </button>
            <br>
            <button class="ui-btn-lg ui-btn-fail">
                取消
            </button>
            <div class="mt10 item">
                <input type="checkbox" class="check check1" name="checkbox">我已查看并同意用户条款
            </div>
        </div>

    </form:form>
    <form:form class="form2" modelAttribute="room" action="/room/add" method="post" name="form2">
        <div class="tab-container ui-form">
            <div class="leftInput">
                <div class="demo fl">
                    <label for="from1" class="text cf">起点</label>
                    <form:input path="startPoint" type="text" class="inputSpot fromSpot" id="from1" name="fromSpot"
                                value="${room.startPoint}"/>
                </div>
                <div class="change">
                    <span class="ico circle"></span>
                </div>
                <div class="demo fr">
                    <label for="to1" class="text dd">终点</label>
                    <form:input path="endPoint" type="text" class="inputSpot toSpot" id="to1" name="toSpot"
                                value="${room.endPoint}"/>
                </div>
            </div>

        </div>

        <div style="background: #f5f5f5;" class="ui-form">
            <div class="ui-form-item ui-border-b">
                <div class="demo fl">
                    <form:errors path="startPoint" class="has-error help-inline"/>
                </div>
                <div class="demo fr">
                    <form:errors path="endPoint" class="fr has-error help-inline"/>
                </div>
            </div>
        </div>

        <div class="ui-form">
            <span class="time-tag">出发时间</span>
            <div class="ui-form-item ui-border-b">
                <label for="from1date">出发日期</label>
                <form:input path="startDate" id="from1date" type="date"/>
            </div>
            <div style="padding-left: 15px;">
                <form:errors path="startDate" class="has-error help-inline"/>
            </div>
            <div class="ui-form-item ui-border-b">
                <label>出发时间</label>
                <form:input path="startTime" type="time"/>
            </div>
            <div style="padding-left: 15px;">
                <form:errors path="startTime" class="has-error help-inline"/>
            </div>
        </div>

        <div class="ui-btn-wrap mt20 ui-form">
            <button class="ui-btn-lg ui-btn-success">
                确认无误，搜索座位信息
            </button>
            <br>
            <button class="ui-btn-lg ui-btn-fail">
                取消
            </button>
            <div class="mt10 item">
                <input type="checkbox" class="check check1" name="checkbox">我已查看并同意用户条款
            </div>
        </div>

    </form:form>
</div>

<div class="clear"></div>
