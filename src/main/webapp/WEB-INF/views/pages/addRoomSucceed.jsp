<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/8
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/room-list.css"/>"/>
<section class="card col-center-block col-lg-6 col-md-10 col-sm-12" style="background: white;">
    <div class="panel-body">
        <div class="fade in">
            <h4>
                <i class="fa fa-check-circle fa-2x" style="color: green;line-height: 60px">
                    <span style="color: green; font-size: 25px; margin-bottom: 2px;vertical-align: middle;">创建成功!</span>
                </i>
            </h4>
            <h4>您的房间信息如下:</h4>
            <hr>
            <div class="text text-center room-title">${room.roomname}</div>
            <dl class="dl-horizontal">
                <dt>出发时间</dt>
                <dd>${room.startDate} ${room.startTime}</dd>
                <dt>起点</dt>
                <dd>${room.startPoint}</dd>
                <dt>终点</dt>
                <dd>${room.endPoint}</dd>
                <dt>人数上限</dt>
                <dd>${room.numberLimit}</dd>
                <dt>备注</dt>
                <dd>${room.note}</dd>
            </dl>
            <hr>
            <h4 class="text-center">分享房间</h4>
            <div class="row text-center col-center-block" style="margin-top: 24px;margin-bottom: 21px; margin-left: 60%">
                <div class="bdsharebuttonbox panel" style="background-color: white">
                    <a title="分享到QQ空间" class="bds_qzone"  href="#" data-id="${room.id}"  data-cmd="qzone"></a>
                    <a title="分享到QQ好友" class="bds_sqq "  href="#" data-id="${room.id}" data-cmd="sqq"></a>
                    <a title="分享到微信" class="bds_weixin"  href="#" data-id="${room.id}" data-cmd="weixin"></a>
                    <a class="bds_more  icons-sm drib-ic"  href="#"  data-id="${room.id}" data-cmd="more"></a>
                </div>
            </div>
            <hr>
        </div>
    </div>
</section>
<style type="text/css">
    .col-center-block {
        float: none;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<script>
    var ShareId = "";
    //绑定所有分享按钮所在A标签的鼠标移入事件，从而获取动态ID
    $(function () {
        $(".bdsharebuttonbox a").mouseover(function () {
            ShareId = $(this).attr("data-id");
        });
    });
    /*
     * 动态设置百度分享URL的函数,具体参数
     * cmd为分享目标id,此id指的是插件中分析按钮的ID
     *，我们自己的文章ID要通过全局变量获取
     * config为当前设置，返回值为更新后的设置。
     */
    function SetShareUrl(cmd, config) {
        if (ShareId) {
            config.bdUrl = "http://novemser.vicp.io:521/room/detail?roomId="+ShareId;
        }
        return config;
    }

    //插件的配置部分，注意要记得设置onBeforeClick事件，主要用于获取动态的文章ID
    window._bd_share_config = {
        "common": {
            onBeforeClick: SetShareUrl, "bdSnsKey": {}, "bdText": "加入我的房间吧"
            , "bdMini": "2", "bdMiniList": false, "bdPic": "", "bdStyle": "0", "bdSize": "24"
        }, "share": {}
    };
    with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
</script>