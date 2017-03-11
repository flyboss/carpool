
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/1
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/static/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/static/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-theme.min.css" rel="stylesheet">
    <!--external css-->
    <link href="/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <%--<link rel="stylesheet" href="/static/css/owl.carousel.css" type="text/css">--%>
    <!-- Custom styles for this template -->
    <link href="/static/css/mdb.css" rel="stylesheet"/>
    <link href="/static/css/style.css" rel="stylesheet">
    <link href="/static/css/style-responsive.css" rel="stylesheet"/>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet"/>
    <%--<script src="/static/js/jquery-3.1.1.min.js "></script>--%>
    <script src="/static/js/jquery-1.8.3.min.js"></script>


</head>
<body>

<section id="container">
    <tiles:insertAttribute name="header"/>

    <tiles:insertAttribute name="menu"/>

    <section id="main-content">
        <section class="wrapper">
            <tiles:insertAttribute name="body"/>
        </section>
    </section>

    <tiles:insertAttribute name="footer"/>

</section>


<!-- js placed at the end of the document so the pages load faster -->
<script src="/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/js/tether.min.js" type="text/javascript"></script>
<script src="/static/js/mdb.min.js" type="text/javascript"></script>
<%--<script src="/static/js/owl.carousel.js"></script>--%>
<%--<script src="/static/js/jquery.customSelect.min.js"></script>--%>

<!--common script for all pages-->
<script src="/static/js/common-scripts.js"></script>
</body>
</html>