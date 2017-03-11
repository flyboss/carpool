<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/11
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row" style="margin-bottom: 90px;">
    <%@include file="profileHeader.jsp" %>
    <aside class="profile-info col-lg-9">
        <c:forEach items="${rooms}" var="li">
            <div class="col-md-6">
                <!--Card-->
                <div class="card" style="background: white; padding: 10px; margin-bottom: 20px">
                    <!--Card content-->
                    <div class="card-block" style="padding-top: 10px">
                        <!--Title-->
                        <span class="card-title" style="margin-left: 5px">${li.roomname}</span>
                        <c:if test="${li.host.id==userId}">
                            <span class="card-title" style="float: right;"><i class="fa fa-user"></i> 我是房主</span>
                        </c:if>
                        <hr>
                        <dl class="dl-horizontal text-xs-center">
                            <dt>出发时间</dt>
                            <dd><fmt:formatDate value="${li.startTime}" pattern="yyyy-MM-dd HH:mm"/></dd>
                            <dt>出发地点</dt>
                            <dd>${li.startPoint}</dd>
                            <dt>目的地</dt>
                            <dd>${li.endPoint}</dd>
                            <dt>人数</dt>
                            <dd>
                        <span style="text-align: center;">
                            <c:forEach begin="1" end="${li.currentNums}">
                                <i class="icon-user" style="color: #42b2c4;"></i>
                            </c:forEach>
                            <c:forEach begin="0" end="${li.numberLimit-li.currentNums}">
                                <i class="icon-user" style="color: lightgrey"></i>
                            </c:forEach>
                        </span>
                            </dd>
                        </dl>
                        <div class="text-center">
                            <a class="btn btn-primary" href="<c:url value="/room/detail?roomId=${li.id}"/>">点击查看更多</a>
                        </div>
                        <!--Text-->
                        <!-- <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
                    </div>
                    <!--/.Card content-->

                </div>
                <!--/.Card-->

            </div>
        </c:forEach>

    </aside>

</div>