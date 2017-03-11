<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/11
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link href="/static/css/mdb.css" rel="stylesheet"/>--%>
<script type="text/javascript" src="../static/js/password.js"></script>
<script type="text/javascript" src="../static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../static/js/cropbox.js"></script>
<style type="text/css">
    .container {
        width: 400px;
        margin: 40px auto 0 auto;
        position: relative;
        font-family: 微软雅黑;
        font-size: 12px;
    }
    .container p {
        line-height: 12px;
        line-height: 0px;
        height: 0px;
        margin: 10px;
        color: #bbb
    }
    .action {
        width: 400px;
        height: 30px;
        margin: 10px 0;
    }
    .cropped {
        position: absolute;
        right: -230px;
        top: 0;
        width: 200px;
        border: 1px #ddd solid;
        height: 460px;
        padding: 4px;
        box-shadow: 0px 0px 12px #ddd;
        text-align: center;
    }
    .imageBox {
        position: relative;
        height: 400px;
        width: 400px;
        border: 1px solid #aaa;
        background: #fff;
        overflow: hidden;
        background-repeat: no-repeat;
        cursor: move;
        box-shadow: 4px 4px 12px #B0B0B0;
    }
    .imageBox .thumbBox {
        position: absolute;
        top: 50%;
        left: 50%;
        width: 200px;
        height: 200px;
        margin-top: -100px;
        margin-left: -100px;
        box-sizing: border-box;
        border: 1px solid rgb(102, 102, 102);
        box-shadow: 0 0 0 1000px rgba(0, 0, 0, 0.5);
        background: none repeat scroll 0% 0% transparent;
    }
    .imageBox .spinner {
        position: absolute;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        text-align: center;
        line-height: 400px;
        background: rgba(0,0,0,0.7);
    }
    .Btnsty_peyton{ float: right;
        width: 66px;
        display: inline-block;
        margin-bottom: 10px;
        height: 57px;
        line-height: 57px;
        font-size: 20px;
        color: #FFFFFF;
        margin:0px 2px;
        background-color: #f38e81;
        border-radius: 3px;
        text-decoration: none;
        cursor: pointer;
        box-shadow: 0px 0px 5px #B0B0B0;
        border: 0px #fff solid;}
    /*选择文件上传*/
    .new-contentarea {
        width: 165px;
        overflow:hidden;
        margin: 0 auto;
        position:relative;float:left;
    }
    .new-contentarea label {
        width:100%;
        height:100%;
        display:block;
    }
    .new-contentarea input[type=file] {
        width:188px;
        height:60px;
        background:#333;
        margin: 0 auto;
        position:absolute;
        right:50%;
        margin-right:-94px;
        top:0;
        right/*\**/:0px\9;
        margin-right/*\**/:0px\9;
        width/*\**/:10px\9;
        opacity:0;
        filter:alpha(opacity=0);
        z-index:2;
    }
    a.upload-img{
        width:165px;
        display: inline-block;
        margin-bottom: 10px;
        height:57px;
        line-height: 57px;
        font-size: 20px;
        color: #FFFFFF;
        background-color: #f38e81;
        border-radius: 3px;
        text-decoration:none;
        cursor:pointer;
        border: 0px #fff solid;
        box-shadow: 0px 0px 5px #B0B0B0;
    }
    a.upload-img:hover{
        background-color: #ec7e70;
    }

    .tc{text-align:center;}
</style>
<div class="row" style="margin-bottom: 90px;">
    <%@include file="profileHeader.jsp" %>
    <aside class="profile-info col-lg-9">
        <section class="panel card">
            <div class="bio-graph-heading">
                同济大学Carpool团队
            </div>
            <div class="panel-body bio-graph-info">
                <h1> 基本信息</h1>
                <form class="form-horizontal" role="form" action="/user/edit" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-group">
                        <label class="col-lg-2 control-label">支付宝账户</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="aliPay" name="aliPay" onkeyup="canSubmit()" placeholder="${user.alipay}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">QQ</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="QQ" name="QQ" onkeyup="canSubmit()" placeholder="${user.qqAccount}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label">WeChat</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="WeChat" name="WeChat" onkeyup="canSubmit()" placeholder="${user.wechatAccount}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <button type="submit" class="btn btn-info" id="save1" disabled=true>保存</button>
                            <button type="button" class="btn btn-default" onclick="clear1()">清空</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <section>
            <div class="panel card panel-primary">
                <div class="panel-heading"> 更改密码</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/user/password" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <div class="form-group">
                            <label class="col-lg-2 control-label">当前密码</label>
                            <div class="col-lg-6">
                                <input type="password" class="form-control" id="cpwd" name="currentPassword"
                                       placeholder=" ">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">新密码</label>
                            <div class="col-lg-6">
                                <input type="password" class="form-control" id="npwd" name="newPassword"
                                       onkeyup="strength()">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-2 control-label">密码强度</label>
                            <div class="col-lg-3">
                                <table width="300px" id="passStrength">
                                    <tr>
                                        <td width="33%" id="strength_L">弱</td>
                                        <td width="33%" id="strength_M">中</td>
                                        <td width="33%" id="strength_H">强</td>
                                    </tr>
                                </table>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-2 control-label">再次输入</label>
                            <div class="col-lg-6">
                                <input type="password" class="form-control" id="rtpwd" name="renewPassword"
                                       onkeyup="isSame()">
                            </div>
                            <div id="unlike" style="display:none; color:#F00">
                                <label class="col-lg-2 control-label">密码输入不一致</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <button type="submit" class="btn btn-info" id="save" disabled=true> 保存</button>
                                <button type="button" class="btn btn-default" onclick="clear2()">清空</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </section>


        <section>
            <div class="panel card panel-primary">
                <div class="panel-heading">设置头像</div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/user/photo" method="post" enctype="multipart/form-data">
                        <%--禁用防跨站攻击的功能--%>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <div class="container">
                            <div class="imageBox">
                                <div class="thumbBox"></div>
                                <div class="spinner" style="display: none">Loading...</div>
                            </div>
                            <div class="action">
                                <!-- <input type="file" id="file" style=" width: 200px">-->
                                <div class="new-contentarea tc"> <a href="javascript:void(0)" class="upload-img">
                                    <label for="upload-file">上传图像</label>
                                </a>
                                    <input type="file" class="" name="file" id="upload-file" />
                                </div>
                                <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"  >
                                <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-" >
                            </div>
                            <button type="submit" class="btn btn-info" id="" > 保存</button>
                        </div>
                    </form>
                </div>
                <script type="text/javascript">
                    $(window).load(function() {
                        var options =
                        {
                            thumbBox: '.thumbBox',
                            spinner: '.spinner',
                            imgSrc: "../static/images/cat.jpg"
                        }
                        var cropper = $('.imageBox').cropbox(options);
                        $('#upload-file').on('change', function(){
                            var reader = new FileReader();
                            reader.onload = function(e) {
                                options.imgSrc = e.target.result;
                                cropper = $('.imageBox').cropbox(options);
                            }
                            reader.readAsDataURL(this.files[0]);
                            this.files = [];
                        })
                        $('#btnCrop').on('click', function(){
                            var img = cropper.getDataURL();
                            $('.cropped').html('');
                            $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
                            $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
                            $('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
                        })
                        $('#btnZoomIn').on('click', function(){
                            cropper.zoomIn();
                        })
                        $('#btnZoomOut').on('click', function(){
                            cropper.zoomOut();
                        })
                    });
                </script>
            </div>
        </section>
    </aside>
</div>
