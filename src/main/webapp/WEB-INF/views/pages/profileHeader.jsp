<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/11
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="profile-nav col-lg-3">
        <section class="panel card">
        <div class="user-heading round">
            <a href="#">
                <img src="${user.photo}" alt="">
            </a>
            <h1>${user.username}</h1>
            <p>${user.alipay}</p>
        </div>

        <ul id="profile-header-template" class="nav nav-pills nav-stacked">
            <li><a href="/user?id=3"> <i class="icon-user"></i> 个人信息</a></li>
            <li><a href="/user/journey?id=3"> <i class="icon-home"></i> 我的房间 </a></li>
            <li><a href="/user/edit?id=3"> <i class="icon-edit"></i> 编辑个人信息</a></li>
        </ul>

    </section>
</aside>

<c:set var="state" scope="session" value="${pageContext.request.getAttribute('active')}"/>
<script >

    $('#profile-header-template li:nth-child(${state})').addClass('active');

</script>
