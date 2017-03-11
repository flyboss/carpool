<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/13
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="modalServiceConfirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <!--Content-->
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <!--Body-->
            <div class="modal-body">
                <i class="fa fa-warning" aria-hidden="true" style="color: orange"></i> 您必须同意服务条款后才能继续
            </div>
            <!--Footer-->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">好的</button>
            </div>
        </div>
        <!--/.Content-->
    </div>
</div>
