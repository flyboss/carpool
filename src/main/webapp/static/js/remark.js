/**
 * Created by qi on 2016/12/8.
 */
$(function () {
    $(".forDetail").click(function () {
        var link = $(this).siblings("input").val();
        var action = $(this).html().trim();
        if (action == "同行人") {
            getDetail(link, $(this));
            $(this).html("隐藏");
        }
        else if (action == "隐藏") {
            $(this).next().remove();
            $(this).html("同行人");
        }
    });

    $("#makeComment").submit(function () {
        if ($(this).find("#star").find(":checked").length == 0) {
            $("#star").parent().find("#error").remove();
            $(this).find("#star").after($("<span  id='error' class='alert-danger' '>   请选择星级</span>"));
        }
        else {
            /*       $.post("/comment/makeComment", $(this).serializeArray(),
             function (data) {
             if(data=="success")
             {
             $("#makeComment #summitComment").attr("disabled",true);
             $("#remark").after($("<div class=' form-group'><label class='col-lg-2 control-label'>评价成功" +
             "你将在<span id='second'>3</span>秒内返回页面</label></div>"))
             var link = $("#link").val();
             reduceTime();
             var timeout = false;
             function reduceTime()
             {
             if(timeout==true)return;
             var second = $("#makeComment #second");
             var seconds = parseInt(second.html());
             second.html(seconds-1);
             setTimeout(reduceTime,1000);
             }
             window.setTimeout(function () {
             location.replace(location.href);
             timeout=true;
             $("#makeComment #summitComment").attr("disabled",false);
             },3000);
             }
             }).error(function (data) {
             alert(data.toString());
             })*/
            $.ajax({
                url: "/comment/makeComment",
                type: "post",
                data: $(this).serializeArray(),
                error: function (data) {
                    alert('出错了！' + data);
                },
                success: function (data) {
                    if (data == "success") {
                        $("#makeComment #summitComment").attr("disabled", true);
                        $("#remark").after($("<div class=' form-group'><label class='col-lg-2 control-label'>评价成功" +
                            "你将在<span id='second'>3</span>秒内返回页面</label></div>"))
                        var link = $("#link").val();
                        reduceTime();
                        var timeout = false;

                        function reduceTime() {
                            if (timeout == true)return;
                            var second = $("#makeComment #second");
                            var seconds = parseInt(second.html());
                            second.html(seconds - 1);
                            setTimeout(reduceTime, 1000);
                        }

                        window.setTimeout(function () {
                            location.replace(location.href);
                            timeout = true;
                            $("#makeComment #summitComment").attr("disabled", false);
                        }, 3000);
                    }
                }
            });
        }
        return false;
    })
});
function getDetail(link,nodeToInsert) {
    $.ajax({
        url: link,
        type: "get",
        error: function (data) {
            alert('出错了！' + data);
        },
        success: function (data) {
            var detail = $("<div class='peerDetail'><legend></legend></div>");
            var list = $("<ol></ol>");
            nodeToInsert.after(detail);
            if(data.length==0)
            {
                detail.append("<span class='alert-info col-lg-pull-2'>没有人和你一起出行哦!</span>")
                return;
            }
            detail.append(list);
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                var peer = $("<li></li>");
                var userid = $("<span class='info'>" + item.targetUserId+" 姓名:" +item.targetUserName+ "</span>");
                peer.append(userid);
                var commentTime = new Date(item.commentTime);
                if (item.hasCommented == false) {
                    var nocomment = $("<span class='info'>未评论</span>");
                    peer.append(nocomment);
                    var link = $("<a class='info commentLink'>评价该用户<i class='icon-comment'></i></a>");
                    var hideInfo= $("<input type='hidden'/>");
                    var info = item.targetUserName+":"+ item.journeyId+":"+item.targetUserId
                        +":"+item.sourceUserId;
                    hideInfo.val(info);
                    link.append(hideInfo);
                    link.click(function () {
                        var detail = $(this).find("input").val().split(":");
                        $("#remarkModal #targetUserName").text(detail[0]);
                        $("#remarkModal #journerid").val(detail[1]);
                        $("#remarkModal #targetUserId").val(detail[2])
                        $("#remarkModal #sourceuserid").val(detail[3]);
                        $("#remarkModal").modal();
                    });
                    peer.append(link);
                }
                else {
                    var star = $("<span class='mystar'></span>");
                    for(var m=0;m<item.credit;m++)
                    {
                        star.append($("<span>★</span>").css("color","yellow"));
                    }
                    for(;m<5;m++)
                    {
                        star.append($("<span>☆</span>"));
                    }
                    peer.append($("<span class='info'>你的评分:</span>"));
                    peer.append(star);
                    var commentText = $("<span class='info'>你的评价:   </span>");
                    commentText.html(commentText.html()+item.commentText);
                    peer.append(commentText);

                    var commentTime = $("<span class='info'>评价时间:   </span>");
                    var time = new Date(item.commentTime);
                    commentTime.html(commentTime.html()+time.toLocaleString());
                    peer.append(commentTime);
                }
                list.append(peer);
            }
        }
    });
}
