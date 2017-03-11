<%--
  Created by IntelliJ IDEA.
  User: Novemser
  Date: 2016/12/23
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="endJourneyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
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
                <i class="fa fa-warning" aria-hidden="true" style="color: orange"></i> 确定结束本次约车？
            </div>
            <!--Footer-->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="confirmEnd(${room.id})">
                    是的
                </button>
            </div>
        </div>
        <!--/.Content-->
    </div>
</div>
<script>
    var sucCount = 0;
    function confirmEnd(roomId) {
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.status == 202) {
                if (sucCount++ == 0)
                    $.ajax(
                        {
                            url:"/journey/generateJourneyFromRoom?roomId="+roomId,
                            type:"get",
                            success:function (data) {
                                if(data=="true")
                                {
                                    toastr.success('结束旅程成功！');
                                    setTimeout(function () {
                                        window.location.href = '/room/detail?roomId=' + roomId;
                                    }, 2000);
                                }
                            }
                        }
                    );

            }
        };

        xmlHttp.open("POST", "/room/end", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send("roomId=" + roomId);
    }
</script>
