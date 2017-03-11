<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/2
  Time: 0:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/style/register-login.css"/>">

</head>
<body>
<%@include file="modal/deleteRoomModal.jsp" %>

<div id="box"></div>
<div class="cent-box">
    <div class="cent-box-header">
        <h1 class="main-title hide">约车</h1>
        <h2 class="sub-title">如果多一张车票，你会不会跟我走</h2>
    </div>

    <div class="cont-main clearfix">
        <div class="index-tab">
            <div class="index-slide-nav">
                <a href="login" class="active">登录</a>
                <div class="slide-bar"></div>
            </div>
        </div>

        <form method=POST id="loginForm">
            <div class="login form">
                <div class="group">
                    <div class="group-ipt email">
                        <select class="group-ipt">
                            <option class="group-ipt">同济大学</option>
                            <option>上海交通大学</option>
                            <option>华东师范大学</option>
                        </select>
                    </div>
                    <div class="group-ipt email">
                        <input type="text" name="username" id="email" class="ipt" placeholder="请输入您的学号" required>
                    </div>
                    <div class="group-ipt password">
                        <input type="password" name="password" id="password" class="ipt" placeholder="输入您的密码" required>
                    </div>
                    <div class="group-ipt verify">
                        <input type="text" name="verify" id="verify" class="ipt" placeholder="输入验证码" required>
                        <img id="codeValidateImg" onclick="flushValidateCode()"
                             class="imgcode">
                    </div>
                </div>
            </div>

            <div class="button">
                <input type="button" onclick="checkImgSubmit()" value="登录" class="login-btn register-btn" id="button"/>
            </div>
            <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="remember clearfix" style="display: none">
                <label class="remember-me"><span class="icon"><span class="zt"></span></span><input
                        type="checkbox"
                        name="remember-me"
                        id="remember-me"
                        class="remember-mecheck"
                        checked>记住我</label>
            </div>

        </form>
    </div>
</div>

<div class="footer">
    <p>约车 - 世界上没有约不到的车</p>
    <p>Designed By TJ SSE Carpool 2016</p>
</div>

<script src='<c:url value="/static/js/particles.js"/>' type="text/javascript"></script>
<script src='<c:url value="/static/js/background.js"/>' type="text/javascript"></script>
<script src='<c:url value="/static/js/jquery-3.1.1.min.js"/>' type="text/javascript"></script>
<script src='<c:url value="/static/js/layer/layer.js"/>' type="text/javascript"></script>
<script type="text/javascript" language="JavaScript">
    var cod = document.getElementById("verify");
    $('#verify').on('input', function () {
        checkImg()
    });

    $('.imgcode').hover(function () {
        layer.tips("看不清？点击更换", '.verify', {
            time: 6000,
            tips: [2, "#3c3c3c"]
        })
    }, function () {
        layer.closeAll('tips');
    }).click(function () {
        flushValidateCode();
//        $(this).attr('src', 'https://www.zhihu.com/captcha.gif?r=1480610738166&type=register&lang=cn');
    });

    $("#remember-me").click(function () {
        var n = document.getElementById("remember-me").checked;
        if (n) {
            $(".zt").show();
        } else {
            $(".zt").hide();
        }
    });

    $(document).ready(function () {
        flushValidateCode();//进入页面就刷新生成验证码
        if (window.location.search == '?error') {
            // 用户或密码错误
            alert("用户名或密码错误，请重试");
        }
    });

    /* 刷新生成验证码 */
    function flushValidateCode() {
        var validateImgObject = document.getElementById("codeValidateImg");
        validateImgObject.src = "/getLoginCode?time=" + new Date();
    }

    function checkImgSubmit() {
        var url = "/checkimagecode";
        code = cod.value;
        $.get(url, {"validateCode": code}, function (data) {
            if (data == "ok") {
//                alert("ok!");
                $('#loginForm').submit();
            } else {
//                alert("error!");

                cod.setAttribute('placeholder', '验证码错误');
                cod.style.color = "red";
                cod.value = "";
                flushValidateCode();
            }
        })
    }

    /*校验验证码输入是否正确*/
    function checkImg() {
        var url = "/checkimagecode";
        code = cod.value;
        $.get(url, {"validateCode": code}, function (data) {
            if (data == "ok") {
//                alert("ok!");
                cod.style.color = "black";
                return true;
            } else {
//                alert("error!");
                cod.setAttribute('placeholder', '验证码错误');
                cod.style.color = "red";
                return false;
            }
        })
    }


</script>
</body>
</html>
