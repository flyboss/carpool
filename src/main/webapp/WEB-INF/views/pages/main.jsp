<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/6
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="/static/css/room-list.css"/>

<div class="border-head">
    <div class="row">
        <h3 class="col-lg-3">当前车池</h3>

        <div class=" text text-center" style="padding-bottom: 8px">
            <div class="text text-center" style="padding-bottom: 8px">
                <i class="flag-finished fa fa-flag" aria-hidden="true">
                    <span>已完成  </span>
                </i>
                <i class="flag-started fa fa-flag" aria-hidden="true">
                    <span>在途中  </span>
                </i>
                <i class="flag-unlocked fa fa-flag" aria-hidden="true">
                    <span>开放  </span>
                </i>
                <i class="flag-locked fa fa-lock" aria-hidden="true">
                    <span>锁定 </span>
                </i>
            </div>
        </div>
    </div>

    <%@include file="../template/roomState.jsp" %>
    <div class="row">
        <c:choose>
            <c:when test="${roomPage==null||roomPage.size==0}">
                <div class="col-lg-4 col-md-6 col-sm-12">
                    Ooops, 车池空了...
                </div>
            </c:when>
            <c:otherwise>

                <c:forEach items="${roomPage.content}" var="li">
                    <div class="col-lg-4 col-md-6 col-sm-12">
                        <section class="z-depth-1 hoverable panel" style="padding: 15px;">
                            <a href="<c:url value="/room/detail?roomId=${li.id}"/>">
                                <div class="row">
                                    <div class="text room-title text-center col-lg-11 col-md-11 col-sm-11 col-xs-11">${li.roomname}</div>
                                    <c:set var="state" value="${li.state}"/>
                                    <c:choose>
                                        <c:when test="${state==ROOM_STATE_UNLOCKED}">
                                            <i class="flag-unlocked fa fa-flag" aria-hidden="true"></i>
                                        </c:when>
                                        <c:when test="${state==ROOM_STATE_LOCKED}">
                                            <i class="flag-locked fa fa-lock" aria-hidden="true"></i>
                                        </c:when>
                                        <c:when test="${state==ROOM_STATE_END}">
                                            <i class="flag-finished fa fa-flag" aria-hidden="true"></i>
                                        </c:when>
                                        <c:when test="${state==ROOM_STATE_STARTED}">
                                            <i class="flag-started fa fa-flag" aria-hidden="true"></i>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <dl class="dl-horizontal">
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
                            <c:forEach begin="1" end="${li.numberLimit-li.currentNums}">
                                <i class="icon-user" style="color: lightgrey"></i>
                            </c:forEach>
                        </span>
                                    </dd>
                                </dl>
                            </a>
                        </section>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>


    </div>
    <div class="text-center">
        <nav class="">
            <ul class="pagination pg-blue ">
                <!--Arrow left-->
                <li class="page-item">
                    <a class="page-link" aria-label="Previous">
                        <span aria-hidden="true">«</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li>

                <!--Numbers-->
                <c:forEach var="i" begin="0" end="${pageCount > 0 ? pageCount - 1: 0}" step="1">
                    <c:if test="${i==currentPage}">
                        <li class="page-item active"><a href="/home/main?page=${i}" class="page-link">${i+1}</a></li>
                    </c:if>
                    <c:if test="${i!=currentPage}">
                        <li class="page-item"><a href="/home/main?page=${i}" class="page-link">${i+1}</a></li>
                    </c:if>
                </c:forEach>
                <!--Arrow right-->
                <li class="page-item">
                    <a class="page-link" aria-label="Next">
                        <span aria-hidden="true">»</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>

</div>
<style type="text/css">
    .pagination {
        float: right !important;
        display: inline-block;
        padding-left: 0;
        margin: 24px 15px 75px 10px;
        border-radius: 4px;
    }
</style>