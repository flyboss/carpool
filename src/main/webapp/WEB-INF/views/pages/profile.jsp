<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/11
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link href="/static/css/mdb.css" rel="stylesheet"/>--%>

<div class="row" style="margin-bottom: 90px;">
    <%@include file="profileHeader.jsp"%>
    <aside class="profile-info col-lg-9">
        <section class="panel card">
            <div class="bio-graph-heading">
                同济大学 Carpool团队
            </div>
            <div class="panel-body bio-graph-info">
                <h1>个人信息</h1>
                <div class="row">
                    <div class="bio-row">
                        <p><span>学号 </span>: ${user.id}</p>
                    </div>
                    <div class="bio-row">
                        <p><span>姓名 </span>: ${user.username}</p>
                    </div>
                    <div class="bio-row">
                        <p><span>信誉值</span>: ${user.credit}</p>
                    </div>
                    <div class="bio-row">
                        <p><span>约车次数</span>: ${user.carpoolingCount}</p>
                    </div>
                    <div class="bio-row">
                        <p><span>金币数量 </span>: ${user.coins}</p>
                    </div>
                    <div class="bio-row">
                        <p><span>支付宝账户 </span>: ${user.alipay}</p>
                    </div>
                    <div class="bio-row">
                        <p><span>QQ </span>: ${user.qqAccount}</p>
                    </div>
                    <div class="bio-row">
                        <p><span>WeChat </span>: ${user.wechatAccount}</p>
                    </div>
                </div>
            </div>
        </section>
    </aside>
</div>
