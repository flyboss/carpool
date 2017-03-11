<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 2016/12/18
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>$Title$</title>
    <style type="text/css">
        #mytrack span
        {
            margin-right: 10%;
        }
        .border-head
        {
            color: #2b577a;
        }
        .trackhead
        {
            font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti, MingLiu, sans-serif;
            color: rgba(1, 2, 2, 0.51);
            font-size: larger;
        }
    </style>
</head>
<body>
<c:set value="${mytracks}" var="mytrack"></c:set>
<div class="border-head">
    <h3>我的足迹
    </h3>
</div>
<div class="row">
    <c:choose>
        <c:when test="${mytrack.size()==0}">
        <div class="no-journey" style="padding: 50px; font-size: larger;">
            <i class="icon-flickr"></i>
            来无影，去无踪，你还没有任何出行
            <a href="/home/main">去拼车</a>
        </c:when>
            <c:otherwise>
                <div class="col-lg-2 col-md-1 col-sm-0"></div>
            <div class="col-lg-8 col-md-10 col-sm-12" id="mytrack">
                <section class="z-depth-1 hoverable panel" style="padding: 5%;">
                    <div class="text-center text">
                        <dl class="dl-horizontal">
                            <dt><span class="trackhead">去过的地方</span></dt>
                            <dd><span class="trackhead">到达次数</span></dd>
                        </dl>

                        <c:forEach var="track" items="${mytrack}">
                                <hr>
                                <div>
                                    <dl class="dl-horizontal">
                            <dt class="text-right"><span>${track.destination}</span>
                            </dt>
                            <dd class="text-center"><span>${track.nums}</span></dd>
                                        </dl>
                                </div>
                            </c:forEach>
                     </div>
                </section>
                </div>
            </c:otherwise>
    </c:choose>
</div>
</body>
</html>
