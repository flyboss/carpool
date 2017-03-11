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
<!DOCTYPE html>
<html>
<head>
    <title>约车</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/static/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/static/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap-theme.min.css" rel="stylesheet">
    <!--external css-->
    <link href="/static/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="/static/css/mdb.css" rel="stylesheet"/>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet"/>
    <script src="/static/js/jquery-3.1.1.min.js"></script>
    <link href="/static/css/index.css" rel="stylesheet"/>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle btn-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand" href="/index" id="brand">
                <img alt="Brand" src="<c:url value="/static/images/CARPOOL.png"/>" height="35px"/>
            </a>
        </div>

        <div class="collapse navbar-collapse yaHei" id="myNavbar">
            <ul class="nav navbar-nav navbar-right topNavbar smooth-scroll">
                <li><a id="idxBtn" href="#indexDiv" class="btn2"
                       onclick="removeSel('idxBtn')">主页</a></li>
                <li><a id="aboBtn" href="#about" onclick="removeSel('aboBtn');">关于</a></li>
                <li><a id="logBtn" href="/login"
                       onclick="removeSel('logBtn');window.location.href='/login';">登录</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="home-header">
    <div id="indexDiv" class="container">
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="text">
                    <h2 class="header-title yaHei">即刻开始,和同济的小伙伴们一起拼车回家.</h2>

                    <div class="card  text-center">
                        <h1 class="yaHei">你的旅途不再单调</h1>

                        <div class="row">
                            <hr class="col-lg-6 col-lg-offset-3 col-md-8 col-md-offset-2">
                        </div>
                        <p class="yaHei">仅面向同济大学在校师生开放注册，提供更安全、便捷的结伴出行信息服务</p>
                        <button class="btn btn-primary yaHei" type="button" name="button"
                                onclick="location.href='/home'"><i class="fa fa-car"></i> 和Ta们开始旅程
                        </button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="about" class="home-content container">
    <div class="text-center yc-text-center yaHei">
        <p>约车, </p>
        <p>
            <span class="word wisteria">方便.</span>
            <span class="word belize">安全.</span>
            <!-- <span class="word pomegranate">实用.</span> -->
            <span class="word green-yc">实惠.</span>
        </p>
    </div>

    <div class="row" style="margin-top: 36px;">
        <div class="col-lg-12">
            <h2 class="yaHei">关于CarPool</h2>
            <hr>
        </div>

    </div>
    <div class="row">
        <!--Second column-->
        <div class="col-md-6 mb-r">
            <!--Card-->
            <div class="card-overlay" style="background-image: url('/static/images/tj1.jpg')">

                <!--Content-->
                <div class="white-text text-xs-center">
                    <div class="card-block yaHei">
                        <h5 class="teal-text"><i class="fa fa-pie-chart"></i> 同济人自己的约车平台</h5>
                        <h3> 信誉评估系统</h3>
                        <p>
                            与教务系统对接,实名身份验证<br/>
                            每一次出行均会对您的信用评级产生影响<br/>
                            可不要放小伙伴的鸽子哟!
                        </p>
                        <!-- <a class="btn btn-lg btn-outline-white waves-effect waves-light"><i class="fa fa-clone left"></i> View project</a> -->
                    </div>
                </div>

            </div>
            <!--/.Card-->
        </div>
        <!--/Second column-->

        <!--Third column-->
        <div class="col-md-6 mb-r">
            <!--Card-->
            <div class="card-overlay" style="background-image: url('/static/images/tj2.jpg')">

                <!--Content-->
                <div class="white-text text-xs-center">
                    <div class="card-block yaHei">
                        <h5 class="cyan-text"><i class="fa fa-users"></i> 你的旅途不再孤单</h5>
                        <h3>方便、快捷地获取拼车信息</h3>
                        <p>
                            任何时间,任何地点<br/>
                            和小伙伴们<br/>
                            想去哪里,就约哪里
                        </p>
                        <!-- <a class="btn btn-lg btn-outline-white waves-effect waves-light"><i class="fa fa-clone left"></i> View project</a> -->
                    </div>
                </div>

            </div>
            <!--/.Card-->
        </div>
        <!--/Third column-->
    </div>

    <div class="row" style="margin-top: 120px;">
        <div class="col-lg-12">
            <h2 class="yaHei">开发人员</h2>
            <hr>
        </div>

    </div>
    <div class="row yaHei">
        <!--First column-->
        <div class="col-lg-4 col-md-6">
            <!--Rotating card-->
            <div class="card-wrapper">
                <div id="card-1" class="card-rotating effect__click">

                    <!--Front Side-->
                    <div class="face front">

                        <!-- Image-->
                        <div class="card-up">
                            <img src="/static/images/bg2.jpg" class="img-responsive">
                        </div>
                        <!--Avatar-->
                        <div class="avatar"><img
                                src="http://115.159.126.118:9000/uploads/user/avatar/17/12718118-small.jpg"
                                class="rounded-circle img-responsive">
                        </div>
                        <!--Content-->
                        <div class="card-block">
                            <h4>Novemser</h4>
                            <p>后端/UI设计</p>
                            <!--Triggering button-->
                            <a class="rotate-btn" data-card="card-1"><i class="fa fa-repeat"></i> 点击查看更多</a>
                        </div>
                    </div>
                    <!--/.Front Side-->

                    <!--Back Side-->
                    <div class="face back">

                        <!--Content-->
                        <h4>关于我</h4>
                        <hr>
                        <p>软件开发是一门深奥的玄学
                        </p>
                        <hr>
                        <!--Social Icons-->
                        <ul class="inline-ul">
                            <li><a class="icons-sm fb-ic"><i class="fa fa-facebook"></i></a></li>
                            <li><a class="icons-sm pin-ic"><i class="fa fa-pinterest"> </i></a></li>
                            <li><a class="icons-sm ins-ic"><i class="fa fa-instagram"> </i></a></li>
                            <li><a class="icons-sm tw-ic"><i class="fa fa-twitter"> </i></a></li>
                        </ul>
                        <!--Triggering button-->
                        <a class="rotate-btn" data-card="card-1"><i class="fa fa-undo"></i> 点击返回</a>

                    </div>
                    <!--/.Back Side-->

                </div>
            </div>
            <!--/.Rotating card-->
        </div>
        <!--/First column-->

        <!--Second column-->
        <div class="col-lg-4 col-md-6">

            <!--Rotating card-->
            <div class="card-wrapper">
                <div id="card-2" class="card-rotating effect__click">

                    <!--Front Side-->
                    <div class="face front">

                        <!-- Image-->
                        <div class="card-up">
                            <img src="/static/images/bg7.jpg" class="img-responsive">
                        </div>
                        <!--Avatar-->
                        <div class="avatar"><img src="/static/images/th.jpg"
                                                 class="rounded-circle img-responsive">
                        </div>
                        <!--Content-->
                        <div class="card-block">
                            <h4>Deadoggy</h4>
                            <p>后端</p>
                            <!--Triggering button-->
                            <a class="rotate-btn" data-card="card-2"><i class="fa fa-repeat"></i> 点击查看更多</a>
                        </div>
                    </div>
                    <!--/.Front Side-->

                    <!--Back Side-->
                    <div class="face back">

                        <!--Content-->
                        <h4>关于我</h4>
                        <hr>
                        <p>bug与代码齐飞，error共玄学一色
                        </p>
                        <hr>
                        <!--Social Icons-->
                        <ul class="inline-ul">
                            <li><a class="icons-sm fb-ic"><i class="fa fa-facebook"></i></a></li>
                            <li><a class="icons-sm tw-ic"><i class="fa fa-twitter"></i></a></li>
                            <li><a class="icons-sm gplus-ic"><i class="fa fa-google-plus"></i></a></li>
                            <li><a class="icons-sm drib-ic"><i class="fa fa-dribbble"> </i></a></li>
                        </ul>
                        <!--Triggering button-->
                        <a class="rotate-btn" data-card="card-2"><i class="fa fa-undo"></i> 点击返回</a>

                    </div>
                    <!--/.Back Side-->

                </div>
            </div>
            <!--/.Rotating card-->
        </div>
        <!--/Second column-->

        <!--Third column-->
        <div class="col-lg-4 col-md-6">

            <!--Rotating card-->
            <div class="card-wrapper">
                <div id="card-3" class="card-rotating effect__click">

                    <!--Front Side-->
                    <div class="face front">

                        <!-- Image-->
                        <div class="card-up">
                            <img src="/static/images/bg3.jpg" class="img-responsive">
                        </div>
                        <!--Avatar-->
                        <div class="avatar"><img src="/static/images/ldl.jpg"
                                                 class="rounded-circle img-responsive">
                        </div>
                        <!--Content-->
                        <div class="card-block">
                            <h4>罗大佬</h4>
                            <p>后端</p>
                            <!--Triggering button-->
                            <a class="rotate-btn" data-card="card-3"><i class="fa fa-repeat"></i> 点击查看更多</a>
                        </div>
                    </div>
                    <!--/.Front Side-->

                    <!--Back Side-->
                    <div class="face back">

                        <!--Content-->
                        <h4>关于我</h4>
                        <hr>
                        <p>如果多一张车票，你会不会跟我走
                        </p>
                        <hr>
                        <!--Social Icons-->
                        <ul class="inline-ul">
                            <li><a class="icons-sm gplus-ic"><i class="fa fa-google-plus"> </i></a></li>
                            <li><a class="icons-sm fb-ic"><i class="fa fa-facebook"> </i></a></li>
                            <li><a class="icons-sm git-ic"><i class="fa fa-github"> </i></a></li>
                            <li><a class="icons-sm li-ic"><i class="fa fa-linkedin"></i></a></li>
                        </ul>
                        <!--Triggering button-->
                        <a class="rotate-btn" data-card="card-3"><i class="fa fa-undo"></i> 点击返回</a>

                    </div>
                    <!--/.Back Side-->

                </div>
            </div>
            <!--/.Rotating card-->
        </div>
        <!--/Third column-->
        <!--Forth column-->
        <div class="col-lg-4 col-md-6">

            <!--Rotating card-->
            <div class="card-wrapper">
                <div id="card-4" class="card-rotating effect__click">

                    <!--Front Side-->
                    <div class="face front">

                        <!-- Image-->
                        <div class="card-up">
                            <img src="/static/images/bg6.jpg" class="img-responsive">
                        </div>
                        <!--Avatar-->
                        <div class="avatar"><img src="/static/images/lv.png"
                                                 class="rounded-circle img-responsive">
                        </div>
                        <!--Content-->
                        <div class="card-block">
                            <h4>李大佬</h4>
                            <p>后端</p>
                            <!--Triggering button-->
                            <a class="rotate-btn" data-card="card-4"><i class="fa fa-repeat"></i> 点击查看更多</a>
                        </div>
                    </div>
                    <!--/.Front Side-->

                    <!--Back Side-->
                    <div class="face back">

                        <!--Content-->
                        <h4>关于我</h4>
                        <hr>
                        <p>妹子都是我的！
                        </p>
                        <hr>
                        <!--Social Icons-->
                        <ul class="inline-ul">
                            <li><a class="icons-sm gplus-ic"><i class="fa fa-google-plus"> </i></a></li>
                            <li><a class="icons-sm fb-ic"><i class="fa fa-facebook"> </i></a></li>
                            <li><a class="icons-sm git-ic"><i class="fa fa-github"> </i></a></li>
                            <li><a class="icons-sm li-ic"><i class="fa fa-linkedin"></i></a></li>
                        </ul>
                        <!--Triggering button-->
                        <a class="rotate-btn" data-card="card-4"><i class="fa fa-undo"></i> 点击返回</a>

                    </div>
                    <!--/.Back Side-->

                </div>
            </div>
            <!--/.Rotating card-->
        </div>
        <!--/Forth column-->
        <!--Fifth column-->
        <div class="col-lg-4 col-md-6">

            <!--Rotating card-->
            <div class="card-wrapper">
                <div id="card-5" class="card-rotating effect__click">

                    <!--Front Side-->
                    <div class="face front">

                        <!-- Image-->
                        <div class="card-up">
                            <img src="/static/images/bg8.jpg" class="img-responsive">
                        </div>
                        <!--Avatar-->
                        <div class="avatar"><img src="/static/images/lx.jpg"
                                                 class="rounded-circle img-responsive">
                        </div>
                        <!--Content-->
                        <div class="card-block">
                            <h4>罗雪</h4>
                            <p>前端</p>
                            <!--Triggering button-->
                            <a class="rotate-btn" data-card="card-5"><i class="fa fa-repeat"></i> 点击查看更多</a>
                        </div>
                    </div>
                    <!--/.Front Side-->

                    <!--Back Side-->
                    <div class="face back">

                        <!--Content-->
                        <h4>关于我</h4>
                        <hr>
                        <p>Pain past is pleasure.
                        </p>
                        <hr>
                        <!--Social Icons-->
                        <ul class="inline-ul">
                            <li><a class="icons-sm gplus-ic"><i class="fa fa-google-plus"> </i></a></li>
                            <li><a class="icons-sm fb-ic"><i class="fa fa-facebook"> </i></a></li>
                            <li><a class="icons-sm git-ic"><i class="fa fa-github"> </i></a></li>
                            <li><a class="icons-sm li-ic"><i class="fa fa-linkedin"></i></a></li>
                        </ul>
                        <!--Triggering button-->
                        <a class="rotate-btn" data-card="card-5"><i class="fa fa-undo"></i> 点击返回</a>

                    </div>
                    <!--/.Back Side-->

                </div>
            </div>
            <!--/.Rotating card-->
        </div>
        <!--/Fifth column-->
        <!--Sixth column-->
        <div class="col-lg-4 col-md-6">

            <!--Rotating card-->
            <div class="card-wrapper">
                <div id="card-6" class="card-rotating effect__click">

                    <!--Front Side-->
                    <div class="face front">

                        <!-- Image-->
                        <div class="card-up">
                            <img src="/static/images/bg13.jpg" class="img-responsive">
                        </div>
                        <!--Avatar-->
                        <div class="avatar"><img src="/static/images/zyy.jpg"
                                                 class="rounded-circle img-responsive">
                        </div>
                        <!--Content-->
                        <div class="card-block">
                            <h4>张映艺</h4>
                            <p>前端</p>
                            <!--Triggering button-->
                            <a class="rotate-btn" data-card="card-6"><i class="fa fa-repeat"></i> 点击查看更多</a>
                        </div>
                    </div>
                    <!--/.Front Side-->

                    <!--Back Side-->
                    <div class="face back">

                        <!--Content-->
                        <h4>关于我</h4>
                        <hr>
                        <p>Where there is life, there is hope.
                        </p>
                        <hr>
                        <!--Social Icons-->
                        <ul class="inline-ul">
                            <li><a class="icons-sm gplus-ic"><i class="fa fa-google-plus"> </i></a></li>
                            <li><a class="icons-sm fb-ic"><i class="fa fa-facebook"> </i></a></li>
                            <li><a class="icons-sm git-ic"><i class="fa fa-github"> </i></a></li>
                            <li><a class="icons-sm li-ic"><i class="fa fa-linkedin"></i></a></li>
                        </ul>
                        <!--Triggering button-->
                        <a class="rotate-btn" data-card="card-6"><i class="fa fa-undo"></i> 点击返回</a>

                    </div>
                    <!--/.Back Side-->

                </div>
            </div>
            <!--/.Rotating card-->
        </div>
        <!--/Sixth column-->
    </div>
</div>

<!--Footer-->
<footer class="page-footer">

    <!--Footer Links-->

    <!--First column-->
    <div class="text text-center">
        <h5 class="title">- CARPOOL -</h5>
        <p class="yaHei">By 软件学院Java盗号小分队</p>
    </div>
    <!--/.First column-->

    <!--/.Footer Links-->
    <!--/.First column-->

    <hr class="hidden-md-up">

    <!--Second column-->
    <div class="text text-center yaHei">
        <h5 class="title">友情链接</h5>
        <ul>
            <li><a href="https://sse.tongji.edu.cn">同济大学软件学院</a></li>
        </ul>
    </div>

    <!--/.Second column-->

    <!--Call to action-->
    <div class="call-to-action yaHei">
        <ul>
            <li>
                <h5>免费体验</h5></li>
            <li><a href="/login" class="btn btn-secondary">现在登录!</a></li>
        </ul>
    </div>
    <!--/.Call to action-->

    <hr>

    <!--Social buttons-->
    <div class="social-section text-center">
        <ul>
            <li><a class="btn-floating btn-small btn-gplus"><i class="fa fa-envelope"> </i></a></li>
            <li><a class="btn-floating btn-small btn-git"><i class="fa fa-github"> </i></a></li>
            <li><a class="btn-floating btn-small btn-ins"><i class="fa fa-comments"> </i></a></li>
        </ul>
    </div>
    <!--/.Social buttons-->

    <div class="text-center">
        <h3 class="yaHei">~ 圣诞快乐 ~</h3>
        <svg width="123px" height="156px" viewBox="63 28 1163 1296" version="1.1" xmlns="http://www.w3.org/2000/svg">
            <rect id="base" stroke="none" fill="#8E550F" fill-rule="evenodd" x="541" y="1153" width="242"
                  height="171"></rect>
            <path d="M674.585992,142 C674.585992,142 614.37253,171.447011 614.007148,183.025055 C613.655271,194.17517 732.364815,191.223748 732.124198,203.082398 C731.856523,216.274629 566.053305,249.975836 565.560219,263.987246 C565.097978,277.122143 742.572873,267.189846 742.397936,280.89442 C742.17132,298.647452 546.557216,341.685733 545.869285,360.348146 C545.226266,377.792192 802.005422,381.910666 801.965627,399.858494 C801.915908,422.281727 492.722165,455.227875 491.345671,478.289129 C489.650945,506.681909 863.373944,515.739492 864.402732,544.433154 C865.213839,567.055574 404.664506,616.972084 402.836399,639.690068 C400.987439,662.667191 970.476589,666.605333 972.303147,689.017331 C974.616064,717.396994 339.30716,755.255509 332.795111,782.722953 C325.084507,815.245833 1040.3809,843.161248 1049.53938,873.049996 C1059.62067,905.950328 282.753182,956.579988 259.289589,985.385392 C239.204112,1010.0436 1122.69493,1032.86137 1145.57526,1052.19351 C1168.04375,1071.17768 429.514311,1120.47693 332.795111,1132.50406 C74.9070423,1164.5727 103.818505,1148.69062 103.818505,1148.69062"
                  id="tree" stroke="#29882F" stroke-width="22" stroke-linecap="square" fill="none"></path>
            <polygon id="star" stroke="none" fill="#EFF019" fill-rule="evenodd"
                     points="673.5 136.75 630.885569 159.153732 639.024201 111.701866 604.548403 78.0962679 652.192785 71.173134 673.5 28 694.807215 71.173134 742.451597 78.0962679 707.975799 111.701866 716.114431 159.153732"></polygon>
            <circle id="blu5" stroke="none" fill="#168BAF" fill-rule="evenodd" cx="541" cy="1041" r="40"></circle>
            <circle id="blu4" stroke="none" fill="#168BAF" fill-rule="evenodd" cx="239" cy="1001" r="40"></circle>
            <circle id="blu3" stroke="none" fill="#168BAF" fill-rule="evenodd" cx="1050" cy="889" r="40"></circle>
            <circle id="blu2" stroke="none" fill="#168BAF" fill-rule="evenodd" cx="757" cy="769" r="40"></circle>
            <circle id="blu1" stroke="none" fill="#168BAF" fill-rule="evenodd" cx="517" cy="374" r="40"></circle>
            <circle id="yel7" stroke="none" fill="#EFF119" fill-rule="evenodd" cx="103" cy="1153" r="40"></circle>
            <circle id="yel6" stroke="none" fill="#EFF119" fill-rule="evenodd" cx="1186" cy="1062" r="40"></circle>
            <circle id="yel5" stroke="none" fill="#EFF119" fill-rule="evenodd" cx="541" cy="849" r="40"></circle>
            <circle id="yel4" stroke="none" fill="#EFF119" fill-rule="evenodd" cx="381" cy="647" r="40"></circle>
            <circle id="yel3" stroke="none" fill="#EFF119" fill-rule="evenodd" cx="864" cy="534" r="40"></circle>
            <circle id="yel2" stroke="none" fill="#EFF119" fill-rule="evenodd" cx="646" cy="534" r="40"></circle>
            <circle id="yel1" stroke="none" fill="#EFF119" fill-rule="evenodd" cx="726" cy="300" r="40"></circle>
            <circle id="red6" stroke="none" fill="#C30E0E" fill-rule="evenodd" cx="846" cy="942" r="40"></circle>
            <circle id="red5" stroke="none" fill="#C30E0E" fill-rule="evenodd" cx="301" cy="788" r="40"></circle>
            <circle id="red4" stroke="none" fill="#C30E0E" fill-rule="evenodd" cx="970" cy="708" r="40"></circle>
            <circle id="red3" stroke="none" fill="#C30E0E" fill-rule="evenodd" cx="477" cy="494" r="40"></circle>
            <circle id="red2" stroke="none" fill="#C30E0E" fill-rule="evenodd" cx="806" cy="414" r="40"></circle>
            <circle id="red1" stroke="none" fill="#C30E0E" fill-rule="evenodd" cx="569" cy="260" r="40"></circle>
        </svg>
    </div>


    <!--Copyright-->
    <div class="footer-copyright">
        <div class="container-fluid">
            © 2016 Copyright: <a href="http://novemser.vicp.io:521"> Carpool.com </a>
        </div>
    </div>
    <!--/.Copyright-->

</footer>
<!--/.Footer-->


<!-- js placed at the end of the document so the pages load faster -->
<script src="/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/js/tether.min.js" type="text/javascript"></script>
<script src="/static/js/mdb.min.js" type="text/javascript"></script>
<script type="text/javascript">
    function removeSel(name) {
        $('.nav li a').removeClass('btn2');
        $('#' + name).addClass('btn2');
    }

    var words = document.getElementsByClassName('word');
    var wordArray = [];
    var currentWord = 0;

    words[currentWord].style.opacity = 1;
    for (var i = 0; i < words.length; i++) {
        splitLetters(words[i]);
    }

    function changeWord() {
        var cw = wordArray[currentWord];
        var nw = currentWord == words.length - 1 ? wordArray[0] : wordArray[currentWord + 1];
        for (var i = 0; i < cw.length; i++) {
            animateLetterOut(cw, i);
        }

        for (var i = 0; i < nw.length; i++) {
            nw[i].className = 'letter behind';
            nw[0].parentElement.style.opacity = 1;
            animateLetterIn(nw, i);
        }

        currentWord = (currentWord == wordArray.length - 1) ? 0 : currentWord + 1;
    }

    function animateLetterOut(cw, i) {
        setTimeout(function () {
            cw[i].className = 'letter out';
        }, i * 80);
    }

    function animateLetterIn(nw, i) {
        setTimeout(function () {
            nw[i].className = 'letter in';
        }, 340 + (i * 80));
    }

    function splitLetters(word) {
        var content = word.innerHTML;
        word.innerHTML = '';
        var letters = [];
        for (var i = 0; i < content.length; i++) {
            var letter = document.createElement('span');
            letter.className = 'letter';
            letter.innerHTML = content.charAt(i);
            word.appendChild(letter);
            letters.push(letter);
        }

        wordArray.push(letters);
    }

    changeWord();
    setInterval(changeWord, 4000);


</script>
</body>
</html>