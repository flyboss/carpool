<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/8
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="modal/deleteRoomModal.jsp" %>
<%@include file="modal/unlockRoomModal.jsp" %>
<%@include file="modal/endJourneyModal.jsp" %>
<%@include file="modal/leaveRoomModal.jsp" %>

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
            <h3 class="panel-heading">
                房间具体信息
            </h3>
            <div class="panel-body">


                <dl class="dl-horizontal">
                    <dt>房主姓名</dt>
                    <dd>${room.host.username}</dd>
                    <dt>房主信用等级</dt>
                    <dd>${room.host.credit}</dd>
                </dl>
                <hr>
                <dl class="dl-horizontal">
                    <dt>房间名称</dt>
                    <dd>${room.roomname}</dd>
                    <dt>创建时间</dt>
                    <dd><fmt:formatDate value="${room.createTime}" pattern="yyyy-MM-dd HH:mm"/></dd>
                    <dt>房间状态</dt>
                    <dd>
                        <c:set var="state" value="${room.state}"/>
                        <c:choose>
                            <c:when test="${state==ROOM_STATE_UNLOCKED}">
                                <i class="flag-unlocked fa fa-flag" aria-hidden="true">
                                    <span>开放 </span>
                                </i>
                            </c:when>
                            <c:when test="${state==ROOM_STATE_LOCKED}">
                                <i class="flag-locked fa fa-lock" aria-hidden="true">
                                    <span>锁定 </span>
                                </i>
                            </c:when>
                            <c:when test="${state==ROOM_STATE_END}">
                                <i class="flag-finished fa fa-flag" aria-hidden="true">
                                    <span>已完成 </span>
                                </i>
                            </c:when>
                            <c:when test="${state==ROOM_STATE_STARTED}">
                                <i class="flag-started fa fa-flag" aria-hidden="true">
                                    <span>在途中 </span>
                                </i>
                            </c:when>
                        </c:choose>
                    </dd>
                    <dt>出发时间</dt>
                    <dd><fmt:formatDate value="${room.startTime}" pattern="yyyy-MM-dd HH:mm"/></dd>
                    <dt>起点</dt>
                    <dd>${room.startPoint}</dd>
                    <dt>终点</dt>
                    <dd>${room.endPoint}</dd>
                    <dt>当前人数</dt>
                    <dd>${room.currentNums} / ${room.numberLimit}</dd>
                    <dt>备注</dt>
                    <dd>${room.note}</dd>
                </dl>
                <hr>
                <%--如果是房主||加入了房间 显示房间人数--%>
                <c:if test="${roomOwner==true || inRoom==true}">
                    <h3>房间用户</h3>
                    <ul class="list-unstyled">
                        <c:forEach items="${roomUsers}" var="user">
                            <li>学号:${user.id} 姓名:${user.username} 信誉等级:${user.credit}</li>
                        </c:forEach>
                    </ul>
                </c:if>
                <div style="margin: 24px;width: 75%;" class="btn btn-lg btn-primary" onclick="window.location.href='/room/chat?roomId=${room.id}'">

                    <i class="fa fa-commenting"></i> 进入聊天室
                </div>
                <c:if test="${roomOwner==false && inRoom==true}">
                    <div style="margin: 24px;width: 75%;" class="btn btn-lg btn-info" onclick="leaveRoom()">
                        <i class="fa fa-sign-out"></i> 退出房间
                    </div>
                </c:if>

                <%--如果不是房主且没有加入房间--%>
                <c:if test="${roomOwner==false && inRoom==false}">
                    <form:form action="/room/user/join" method="post" id="addUserForm">
                        <input name="roomId" value="${room.id}" type="hidden">
                        <c:choose>
                            <c:when test="${reachLimit}">
                                <h3>该房间人数已满!暂时无法加入,再去池子里转转吧~</h3>
                            </c:when>
                            <c:when test="${room.state==ROOM_STATE_UNLOCKED && !reachLimit}">
                                <div class="btn-effect btn btn-success btn-lg" onclick="addUserFormSubmit()">
                                    <i class="fa fa-check"></i> 确定加入
                                </div>
                            </c:when>
                            <c:when test="${room.state==ROOM_STATE_LOCKED}">
                                <div class="btn-effect btn btn-warning btn-lg" disabled>
                                    <i class="fa fa-info"></i> 暂时无法加入 请等待房主解锁
                                </div>
                            </c:when>
                            <c:when test="${room.state==ROOM_STATE_STARTED}">
                                <div class="btn-effect btn btn-info btn-lg">
                                    <i class="fa fa-car"></i> 小伙伴们已经上路啦~
                                </div>
                            </c:when>
                        </c:choose>
                    </form:form>
                </c:if>

                <c:if test="${roomOwner==true}">
                    <c:choose>
                        <c:when test="${room.state==ROOM_STATE_UNLOCKED}">
                            <div style="margin: 24px;width: 75%;" class="btn btn-lg btn-success"
                                 onclick="location.href='/room/edit?roomId=${room.id}'">
                                <i class="fa fa-pencil"></i><span style="color: white"> 修改房间信息</span>
                            </div>

                            <div style="margin: 24px;width: 75%;" class="btn btn-lg btn-info"
                                 onclick="endRoom()">
                                <i class="fa fa-flag"></i><span style="color: white"> 结束旅程</span>
                            </div>

                            <div style="margin: 24px;width: 75%;" class="btn btn-lg btn-danger"
                                 onclick="deleteRoom()">
                                <i class="fa fa-close"></i><span style="color: white"> 关闭房间</span>
                            </div>
                        </c:when>
                        <c:when test="${room.state==ROOM_STATE_LOCKED}">
                            <div style="margin: 24px;width: 75%;" class="btn btn-lg btn-danger"
                                 onclick="unlockRoom()">
                                <i class="fa fa-close"></i><span style="color: white"> 解锁房间</span>
                            </div>
                        </c:when>
                        <c:when test="${room.state==ROOM_STATE_STARTED}">
                            <div class="btn-effect btn btn-info btn-lg">
                                <i class="fa fa-car"></i> 这位小伙伴已经上路啦 再转转吧~
                            </div>
                        </c:when>
                    </c:choose>
                </c:if>
                <div class="col-xs-offset-3">
                    <div class="bdsharebuttonbox btn-effect" style="background-color: white;margin-left: 10px;float: right">
                        <a title="分享到QQ空间" class="bds_qzone"  href="#" data-id="${room.id}"  data-cmd="qzone"></a>
                        <a title="分享到QQ好友" class="bds_sqq "  href="#" data-id="${room.id}" data-cmd="sqq"></a>
                        <a title="分享到微信" class="bds_weixin"  href="#" data-id="${room.id}" data-cmd="weixin"></a>
                        <a class="bds_more  icons-sm drib-ic"  href="#"  data-id="${room.id}" data-cmd="more"></a>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<style type="text/css">
    .btn-effect {
        width: 75%;
        margin: 10px;
    }

    .btn-effect a {
        color: white;
    }
</style>

<script>
    function deleteRoom() {
        $('#deleteRoomConfirm').modal('show');
    }

    function endRoom() {
        $('#endJourneyModal').modal('show');
    }
    function unlockRoom() {
        $('#unlockRoomConfirm').modal('show');
    }

    function leaveRoom() {
        $('#leaveRoomConfirm').modal('show');
    }

    function addUserFormSubmit() {
        $('#addUserForm').submit();
    }
    var ShareId = "";
    //绑定所有分享按钮所在A标签的鼠标移入事件，从而获取动态ID
    $(function () {
        $(".bdsharebuttonbox a").mouseover(function () {
            ShareId = $(this).attr("data-id");
        });
    });
    /*
     * 动态设置百度分享URL的函数,具体参数
     * cmd为分享目标id,此id指的是插件中分析按钮的ID
     *，我们自己的文章ID要通过全局变量获取
     * config为当前设置，返回值为更新后的设置。
     */
    function SetShareUrl(cmd, config) {
        if (ShareId) {
            config.bdUrl = "http://novemser.vicp.io:521/room/detail?roomId="+ShareId;
        }
        return config;
    }

    //插件的配置部分，注意要记得设置onBeforeClick事件，主要用于获取动态的文章ID
    window._bd_share_config = {
        "common": {
            onBeforeClick: SetShareUrl, "bdSnsKey": {}, "bdText": "加入我的房间吧"
            , "bdMini": "2", "bdMiniList": false, "bdPic": "", "bdStyle": "0", "bdSize": "24"
        }, "share": {}
    };
    with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
</script>
