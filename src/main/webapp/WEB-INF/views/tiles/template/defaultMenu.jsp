<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/5
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<aside>
    <div id="sidebar" class="nav-collapse " tabindex="5000"
         style="overflow-x: auto; overflow-y: visible; outline: none;">
        <c:set var="state" scope="session" value="${pageContext.request.getParameter('id')}"/>
        <c:if test="${state == null||state.equals('')}">
            ${state = 1}
        </c:if>

        <!-- sidebar menu start-->
        <ul class="yaHei sidebar-menu">
            <li class="menu-item-main mymenu-item">
                <a href="/home/main?id=1">
                    <i class="fa fa-car"></i>
                    <span>车池 </span>
                </a>
            </li>
            <li class="menu-item-select sub-menu mymenu-item">
                <a href="javascript:;">
                    <i class="icon-search"></i>
                    <span>约车</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a href="/room/create?id=2">新建房间</a></li>
                    <li><a href="/room/join?id=2">加入房间</a></li>
                </ul>
            </li>


            <li class="menu-item-person mymenu-item">
                <a href="/user?id=3">
                    <i class="icon-user"></i>
                    <span>个人中心 </span>
                </a>
            </li>


            <li class="menu-item-select sub-menu mymenu-item">
                <a href="javascript:;">
                    <i class="icon-flag"></i>
                    <span>我的出行</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a href="/journey/getMyJourneyAsHost?id=4">我是房主</a></li>
                    <li><a href="/journey/getAllJourneys?id=4">全部出行</a></li>
                    <li><a href="/journey/getMyTrack?id=4">我的足迹</a></li>
                </ul>
            </li>

            <li class="menu-item-select sub-menu mymenu-item">
                <a href="javascript:;">
                    <i class="icon-comment"></i>
                    <span>评论中心</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li><a href="/comment/getReceivedComment?id=5">收到评论</a></li>
                    <li><a href="/comment/getSendedComment?id=5">发出评论</a></li>
                </ul>
            </li>
            <li class="menu-item-logout mymenu-item mymenu-item">


                <form id="log-out-form" action="/logout" method=POST>
                    <%--<i class="icon-key"></i>--%>
                    <%--&lt;%&ndash;<input value="退出登录" type = "submit"/>&ndash;%&gt;--%>
                    <%--<span>退出登录</span>--%>
                    <a onclick="logOut()">
                        <i class="icon-key"></i>
                        <span>退出登录</span>
                    </a>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                </form>

            </li>
        </ul>
        <!-- sidebar menu end-->
    </div>
</aside>

<script type="text/javascript" language="JavaScript">
    $(".mymenu-item").eq(${state - 1}).addClass('active');

    // 提交登出表单
    function logOut() {
        $('#log-out-form').submit();
    }
</script>