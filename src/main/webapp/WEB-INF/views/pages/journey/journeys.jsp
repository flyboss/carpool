<%--
  Created by IntelliJ IDEA.
  User: qi
  Date: 2016/12/8
  Time: 0:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/remarkStar.css">
    <link rel="stylesheet" href="/static/css/commentPage.css">
    <script src="/static/js/remark.js"></script>
    <style type="text/css">
        .forDetail
        {
            cursor: pointer;
        }
        .peerDetail .commentLink
        {
            cursor: pointer;
        }
        .peerDetail
        {
            border-radius: 5px;
            font-size: smaller;
            padding-left: 15px;
        }
        .peerDetail .info
        {
            margin-left: 10px;
        }
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
        .journey
        {
            background: transparent none repeat scroll 0 0;
        }
        label
        {
            font-size: large;
        }
        .pagination {
            float: right !important;
            display: inline-block;
            padding-left: 0;
            margin: 24px 15px 75px 10px;
            border-radius: 4px;
        }
        #myfavorite
        {
            position: absolute;
            right: 20px;
            top:60px;
        }
    </style>
</head>
<body>
<c:set var="userid" value="${userId}"></c:set>
<c:set value="${journeys}" var="journeyPages"></c:set>
<c:set var="journeyIncurrentPage" value="${journeys.content}"></c:set>
<div class="border-head">
    <h3>我的出行：${type}
    </h3>
</div>
<div class="row">
    <c:choose>
        <c:when test="${journeyPages.totalElements==0}">
            <div class="no-journey" style="padding: 50px; font-size: larger;">
                <i class="icon-flickr">
                    <c:choose>
                        <c:when test="${type=='我是房主'}">
                            你还没有成为过房主
                            <a href="/home/main">去开房</a>
                        </c:when>
                        <c:otherwise>
                            当前无任何出行记录
                            <a href="/home/main">去拼车</a>
                        </c:otherwise>
                    </c:choose>
                </i>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="journey" items="${journeyIncurrentPage}">
                <div class="row">
                <div class="col-lg-2 col-md-1 col-sm-0"></div>
                <div class="col-lg-8 col-md-10 col-sm-12 journey">
                    <section class="z-depth-1 hoverable panel" style="padding: 15px 15px 25px;">
                        <div class="text-center text">
                                ${journey.room.roomname}
                            <span class="text-right">
                                <c:choose>
                                <c:when test="${journey.room.host.id==userid}">
                                    <i class="icon-user">
                                    </i>
                                    <strong>我是房主</strong>
                                </c:when>
                                <c:otherwise>
                                    <i class="icon-user">
                                        <strong>房主是 &nbsp;<a href="<c:url value="/comment/getOthersComment/${journey.room.host.id}"/>"><c:out value="${journey.room.host.username}">
                                        </c:out></a></strong>
                                    </i>
                                </c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                        <dl class="dl-horizontal">
                            <dt>出行人数：</dt>
                            <dd>${journey.peerNums}</dd>
                            <dt>起点：</dt>
                            <dd>${journey.startPoint}</dd>
                            <dt>终点：</dt>
                            <dd>${journey.endPoint}</dd>
                            <dt>出行时间:</dt>
                            <dd><fmt:formatDate value="${journey.startTime}" pattern="yyyy/MM/dd HH:mm:SS"></fmt:formatDate>
                            </dd>
                        </dl>
                            <input type="hidden" value="<c:url value="/journey/getJourneyCommentDetail/${journey.id}?userid=${userid}"/>">
                            <span class="forDetail pull-right" >同行人</span>
                    </section>
                    </div>
                <div class="col-lg-2 col-md-1 col-sm-0"></div>
                </div>
            </c:forEach>

            <div class="text-center">
                <nav class="">
                    <ul class="pagination pg-blue ">
                        <!--Arrow left-->

                        <li class="page-item">
                            <c:choose>
                                <c:when test="${journeyPages.hasPrevious()}">
                            <a class="page-link" href='<c:url value="${url}?currentPage=${journeyPages.number-1}&id=4"></c:url>' aria-label="Previous">
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
                        <c:forEach var="i" begin="0" end="${journeyPages.totalPages > 0 ? journeyPages.totalPages - 1: 0}" step="1">
                            <c:if test="${i==currentPage}">
                                <li class="page-item active"><a href="<c:url value="${url}?currentPage=${i}&id=4"></c:url>" class="page-link">${i+1}</a></li>
                            </c:if>
                            <c:if test="${i!=currentPage}">
                                <li class="page-item"><a href="<c:url value="${url}?currentPage=${i}&id=4"></c:url>" class="page-link">${i+1}</a></li>
                            </c:if>
                        </c:forEach>
                        <!--Arrow right-->
                        <li class="page-item">
                            <c:choose>
                            <c:when test="${journeyPages.hasNext()}">
                            <a class="page-link" href='<c:url value="${url}?currentPage=${currentPage+1}&id=4"></c:url>' aria-label="Next">
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
<div class="modal fade" id="remarkModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title">给他评价: <span id="targetUserName"></span></h4>
            </div>
            <form class="form-action" id="makeComment">
                <div class="modal-body" >
                    <input type="hidden" id="journerid" name="journey.id">
                    <input type="hidden" id="sourceuserid" name="sourceUser.id">
                    <input type="hidden" id="targetUserId" name="targetUser.id">
                    <div>
                        <label  class="col-lg-2 control-label">给他评星: </label>
                    <br>
                    <div id="star" class="col-lg-10">
                        <input type="radio" id="start5" style="display: none" name="credit" value="5">
                        <label for="start5"><span></span></label>

                        <input type="radio" style="display: none"   id="start4" name="credit" value="4">
                        <label for="start4"><span></span></label>

                        <input type="radio" style="display: none"  id="start3" name="credit" value="3">
                        <label for="start3"><span></span></label>

                        <input type="radio" style="display: none" id="start2" name="credit" value="2">
                        <label for="start2"><span></span></label>

                        <input type="radio"  style="display: none" id="start1" name="credit" value="1">
                        <label for="start1"><span></span></label>
                    </div>
                    </div>
                    <br>
                    <div id="remark" class="form-group">
                            <label for="remark"  class="col-lg-2 control-label">给他评论:</label>
                            <div class="col-lg-10">
                                <textarea name="commentText" id="remarkContent" class="form-control" cols="10" rows="5"placeholder="在这里输入评论"></textarea>
                            </div>
                    </div>
                </div>
                <div class="modal-footer" >
                    <input type="submit" class="btn btn-default" id="summitComment"  value="确定评论"></button>
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="关闭"></button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        document.title='${type}';
    });
</script>
</body>
</html>