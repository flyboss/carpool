<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/6
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<div class="row text-center footer hidden-md hidden-lg navbar-fixed-bottom" style="z-index:2; background: white !important;border: 0 solid lightgray;border-top-width: 1px;">
    <div class="container">
        <ul class="clearfix" style="">
            <li>
                <a href="/index" class="on"><i class="fa fa-home"></i>
                    <div>首页</div></a>
            </li>
            <li>
                <a href="/room/create"><i class="fa fa-plus"></i>
                    <div>新建</div></a>
            </li>
            <li>
                <a href="/room/join"><i class="fa fa-search"></i>
                    <div>加入</div></a>
            </li>
            <li>
                <a href="/user"><i class="fa fa-user"></i>
                    <div>我的</div></a>
            </li>
        </ul>
    </div>
</div>
<style type="text/css">
    .container .clearfix li
    {
        padding-top: 18px;
        display: table-cell;
        width: 723px;
        text-align: center;
        vertical-align: middle;
    }
</style>