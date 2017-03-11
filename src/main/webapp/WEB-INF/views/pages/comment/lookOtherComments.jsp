<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 2016/12/7
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="/static/css/commentPage.css" rel="stylesheet">
   <style type="text/css">
    .commentText
    {
    overflow: auto;
    }

    .border-head table
    {
    font-size: x-small;
    }
    .border-head table td
    {
    margin-right: 5px;
    }
    dt, dd {
    line-height: 1.42857143;
    }
    body
    {
    color: #2b577a;
    font-family: Helvetica,"Hiragino Sans GB W3",arial,sans-serif;
    line-height: 1;
    display: block;
    font-size: medium;
    background-color: #f1f2f7;
    }
    .pagination {
        float: right !important;
        display: inline-block;
        padding-left: 0;
        margin: 24px 15px 75px 10px;
        border-radius: 4px;
    }
   </style>
</head>
<body>
<c:set var="user" value="${otherUser}"></c:set>
<c:set var="commentsPage" value="${comments}"></c:set>
<c:set var="commentInCurrentPage" value="${comments.content}"></c:set>
<c:set  var="userid" value="${otherUser.id}"></c:set>
<div class="border-head">
    <c:choose>
        <c:when test="${empty user}">
            <h3>对不起，不存在的用户</h3>
        </c:when>
        <c:otherwise>
            <h3>${user.username}的信誉评论
                <table class="pull-right"><tr>
                    <td>信誉积分:</td>
                    <td>  <progress id="creditProgress" value="${user.credit}"
                                    max="5"></progress></td>
                    <td><fmt:formatNumber value="${user.credit}" pattern=".00"></fmt:formatNumber>分</td>
                </tr>
                </table>
            </h3>
        </c:otherwise>
    </c:choose>
</div>
<div class="row">
    <c:choose>
    <c:when test="${commentsPage.totalElements==0}">
        <c:out value=" 该用户目前没有收到任何评价"></c:out>
    </c:when>
    <c:otherwise>
        <c:forEach var="comment" items="${commentInCurrentPage}">
            <div class="row">
                <div class="col-lg-2 col-md-1 col-sm-0"></div>
            <div class="col-lg-8 col-md-10 col-sm-12">
                <section class="z-depth-1 hoverable panel" style="padding: 15px;">
                    <dl class="dl-horizontal">
                        <dt>收到评分：</dt>
                        <dd>
                            <c:forEach begin="0" end="${comment.credit-1}">
                                <i class="icon-star" style="color: yellow"></i>
                            </c:forEach>
                            <c:forEach begin="${comment.credit+1}" end="5">
                                <i class="icon-star" style="color: lightgrey"></i>
                            </c:forEach>
                                ${comment.credit}分
                        </dd>
                        <dt>评价内容：</dt>
                        <dd>
                            <div class="commentText">
                                    ${comment.commentText}
                            </div>
                        </dd>
                    </dl>
                    <p class="text-right">---------用户${comment.sourceUser.username}在<fmt:formatDate value="${comment.commentTime}" pattern="yyyy/MM/dd hh:mm:ss"></fmt:formatDate>
                        发表评论
                    </p>
                </section>
            </div>
                <div class="col-lg-2 col-md-1 col-sm-0"></div>
            </div>
        </c:forEach>
<!--            <div class="col-lg-12 col-md-12 col-sm-12" style="padding-left: 15px" >
                <ul class="pagination pagination-centered center-block">
                    <li>
                        <a href="<c:url value="/comment/getOthersComment/${userid}?currentPage=0"></c:url> ">
                            第一页
                        </a>
                    </li>

                    <c:if test="${commentsPage.hasPrevious()}">
                        <li>
                            <a  href="<c:url value='/comment/getOthersComment/${userid}?currentPage=${commentsPage.number-1}'></c:url>">
                                上一页
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <span class="text-info disabled">当前页数${commentsPage.number+1}/${commentsPage.totalPages}</span>
                    </li>
                    <c:if test="${commentsPage.hasNext()}">
                        <li>
                            <a  href="<c:url value='/comment/getOthersComment/${userid}?currentPage=${commentsPage.number+1}'></c:url>">
                                下一页
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <a href="<c:url value="/comment/getOthersComment/${userid}?currentPage=${getOthersComment.totalPages-1}"></c:url> ">
                            最后一页
                        </a>
                    </li>
                </ul>
            </div>!-->
        <div class="text-center col-lg-5 col-md-3 col-sm-1">
            <nav class="">
                <ul class="pagination pg-blue ">
                    <!--Arrow left-->

                        <li class="page-item">
                            <c:choose>
                                <c:when test="${commentsPage.hasPrevious()}">
                                <a class="page-link" href='<c:url value="/comment/getOthersComment/${userid}?currentPage=${commentsPage.number-1}"></c:url>' aria-label="Previous">
                                <span aria-hidden="true">«</span>
                                <span class="sr-only">Previous</span>
                                </a>
                            </c:when>
                                <c:otherwise>
                                    <a>
                                        <span aria-hidden="true">«</span>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </li>


                    <!--Numbers-->
                    <c:forEach var="i" begin="0" end="${commentsPage.totalPages > 0 ? commentsPage.totalPages - 1: 0}" step="1">
                        <c:if test="${i==commentsPage.number}">
                            <li class="page-item active"><a href="<c:url value="/comment/getOthersComment/${userid}?currentPage=${i}"></c:url>" class="page-link">${i+1}</a></li>
                        </c:if>
                        <c:if test="${i!=commentsPage.number}">
                            <li class="page-item"><a href="<c:url value="/comment/getOthersComment/${userid}?currentPage=${i}"></c:url>" class="page-link">${i+1}</a></li>
                        </c:if>
                    </c:forEach>
                    <!--Arrow right-->

                        <li class="page-item">
                            <c:choose>
                                <c:when test="${commentsPage.hasNext()}">
                            <a class="page-link" href='<c:url value="/comment/getOthersComment/${userid}?currentPage=${commentsPage.number+1}"></c:url>' aria-label="Next">
                                <span aria-hidden="true">»</span>
                                <span class="sr-only">Next</span>
                            </a>
                                </c:when>
                                <c:otherwise>
                                    <a>
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                </ul>
            </nav>
        </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
