/**
 * Created by 91574 on 2016/12/15.
 */

function isSame() {
    var p1 = document.getElementById("npwd").value;
    var p2 = document.getElementById("rtpwd").value;
    var same = document.getElementById("unlike");
    if (p1 == p2) {
        same.style.display = "none";
        document.getElementById("save").disabled = false;
    }
    else {
        same.style.display = "block";
        document.getElementById("save").disabled = true;
    }
}

function canSubmit() {
    var save=document.getElementById("save1");
    var alipay=document.getElementById("aliPay");
    var qq=document.getElementById("QQ");
    var wechat=document.getElementById("WeChat");
    if(alipay!=""||qq!=""||wechat!="")
        save.disabled=false;
    else if(alipay==""&&qq==""&&wechat=="")
        save.disabled=true;
}

// 字符类别判断
function CharMode(iN) {
    if (iN >= 48 && iN <= 57)
        return 1;
    if (iN >= 65 && iN <= 90)
        return 2;
    if (iN >= 97 && iN <= 122)
        return 4;
    else
        return 8;
}

function bitTotal(num) {
    modes = 0;
    for (i = 0; i < 4; i++) {
        if (num & 1) modes++;
        num >>>= 1;
    }
    return modes;
}


function checkStrong(sPW) {
    if (sPW.length <= 4)
        return 0;
    Modes = 0;
    for (i = 0; i < sPW.length; i++) {
        Modes |= CharMode(sPW.charCodeAt(i));
    }
    return bitTotal(Modes);
}

function strength() {
    var pwd = document.getElementById("npwd").value;
    var white = "#eeeeee";
    var red = "#FF0000";
    var orange = "#FF9900";
    var green = "#33CC00";
    var Lcolor = white;
    var Mcolor = white;
    var Hcolor = white;
    if (pwd == null || pwd == '') {
        Lcolor = Mcolor = Hcolor = O_color;
    }
    else {
        S_level = checkStrong(pwd);
        switch (S_level) {
            case 0:
                Lcolor = Mcolor = Hcolor = white;
            case 1:
                Lcolor = red;
                Mcolor = Hcolor = white;
                break;
            case 2:
                Lcolor = Mcolor = orange;
                Hcolor = white;
                break;
            default:
                Lcolor = Mcolor = Hcolor = green;
        }
    }
    document.getElementById("strength_L").style.background = Lcolor;
    document.getElementById("strength_M").style.background = Mcolor;
    document.getElementById("strength_H").style.background = Hcolor;
}

function clear1() {
    document.getElementById("aliPay").value="";
    document.getElementById("QQ").value="";
    document.getElementById("WeChat").value="";
}

function clear2() {
    document.getElementById("cpwd").value="";
    document.getElementById("npwd").value="";
    document.getElementById("rtpwd").value="";
    document.getElementById("strength_L").style.background = "#eeeeee";
    document.getElementById("strength_M").style.background = "#eeeeee";
    document.getElementById("strength_H").style.background = "#eeeeee";
    document.getElementById("unlike").style.display = "none";
}

