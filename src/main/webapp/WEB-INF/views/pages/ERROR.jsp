<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/21
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Novemser">
    <meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>抱歉</title>

    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="/static/css/style.css" rel="stylesheet">
    <link href="/static/css/style-responsive.css" rel="stylesheet" />

</head>

<body class="body-500">

<div class="container">

    <section class="error-wrapper">
        <i class="icon-500"></i>
        <h1>天啦噜!</h1>
        <h2>${errCode}</h2>
        <p class="page-500">${errMsg} <a href="/home/main">返回首页</a></p>
    </section>

</div>


</body>
</html>
