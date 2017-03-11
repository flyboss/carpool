<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/5
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<header class="header white-bg">

    <script>
        var host = window.location.host;
        var websocketNotify;
        var msgCounter = 0;


        websocketNotify = new WebSocket("ws://" + host + "/unreadWebSocketServer");

        websocketNotify.onopen = function (evnt) {
            sendNotify();
        };
        websocketNotify.onmessage = function (evnt) {
            msgCounter++;
            var jsonMsg = JSON.parse(evnt.data);
//            alert('msgCount' + jsonMsg['count']);
            $('#msgNotify').show();
            $('#msgNotify').html(msgCounter);
            $('#msgCount').html('您有 '+ jsonMsg['count'] + ' 条新消息');

            var liStr = "<a href=\"/room/chat?roomId="+jsonMsg['roomId']+"\"> <span class=\"photo\"><img alt=\"avatar\" src=\"/static/img/avatar-mini.jpg\"></span> <span class=\"subject\"> <span class=\"from\">"+jsonMsg['sender']+"</span> <span class=\"time\">"+jsonMsg['time']+"</span> </span> <span class=\"message\">"+ jsonMsg['content'] +"</span> </a> </li>"
            var oldHtml = $('#msgWindow').html();
            $('#msgWindow').html(oldHtml+liStr);
        };
        websocketNotify.onerror = function (evnt) {
        };
        websocketNotify.onclose = function (evnt) {
        }


        function sendNotify(){
            var msg = JSON.stringify({"type": 2});
            websocketNotify.send(msg);
        }


        function afterClickNotify(){
            $('#msgNotify').hide();
            msgCounter = 0;
        }


    </script>
    <div class="sidebar-toggle-box">
        <div data-original-title="Toggle Navigation" data-placement="right" class="icon-reorder tooltips"></div>
    </div>
    <!--logo start-->
    <a href="/index" class="logo">Car<span>pool</span></a>
    <!--logo end-->
    <div class="nav notify-row pull-right" id="top_menu">
        <!--  notification start -->
        <ul class="nav top-menu">
            <!-- inbox dropdown start-->
            <li id="header_inbox_bar" class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#" onclick="afterClickNotify()">
                    <i class="icon-envelope-alt"></i>
                    <span class="badge bg-important" id = "msgNotify" ></span>
                </a>
                <ul class="dropdown-menu dropdown-menu-right extended inbox">
                    <div class="notify-arrow notify-arrow-red"></div>
                    <li>
                        <p class="red" id = "msgCount"></p>
                    </li>
                    <li id = 'msgWindow'></li>
                    <%--<c:forEach items="${unreadMsg}" var = "li">--%>
                        <%--<li>--%>
                            <%--<a href="#">--%>
                                <%--<span class="photo"><img alt="avatar" src="/static/img/avatar-mini.jpg"></span>--%>
                                <%--<span class="subject">--%>
                                    <%--<span class="from">${li.sender.username}</span>--%>
                                    <%--<span class="time">${li.commenttime}</span>--%>
                                    <%--</span>--%>
                                <%--<span class="message">--%>
                                        <%--${li.commenttext}--%>
                                    <%--</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                    <%--</c:forEach>--%>
                    <li>
                        <a href="#">See all messages</a>
                    </li>
                </ul>
            </li>
            <!-- inbox dropdown end -->
        </ul>
        <!--  notification end -->
    </div>
    <script>

    </script>
</header>