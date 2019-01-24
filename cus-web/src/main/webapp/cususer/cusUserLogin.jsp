<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>欢迎登录后台管理系统</title>
    <link href="${ctPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ctPath}/css/jquery-confirm.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
    <script language="JavaScript" src="${ctxStatic}/common/jquery.base64.js"></script>
    <script src="${ctPath}/js/cloud.js" type="text/javascript"></script>
    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
        var userLogin = userLogin || (function () {
            var userLogin = {};
            var account;var password;
            userLogin.validate = function () {
                account = $("#account").val();
                password = $("#password").val();
                if (account == null || account == "") {
                    $("#account").css("color", "red").val("请填写用户名！");
                    return;
                }
                if (password == null || password == "") {
                    document.getElementById("password").type = "text";
                    $("#password").css("color", "red").val("请填写密码！");
                    return;
                }
                return true;
            }
            userLogin.login = function () {
                if (userLogin.validate()) {
                    password = jQuery.base64.encode(password);
                    $.ajax({
                        url: "${ctxF}/cus/cusUser/userLogin.async",
                        type: "POST",
                        dataType:"json",
                        data: {
                            "loginName":account,
                            "password":password
                        },
                        success: function (successData) {
                            if(successData.OPT_CODE == 100) {
                            }else{
                                document.location.href = "${ctPath}/indexController/goIndex.do"
                            }
                        },
                        error: function (errorData) {
                        },
                    });
                }
            }
            return userLogin;
        })();
    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>
    <form id="userLoginForm">
        <div class="loginbox">
            <ul>
                <li><input name="account" id="account" type="text" class="loginuser" placeholder="用户名"
                           onclick="JavaScript:this.value='';this.style.color='black'"/></li>
                <li><input name="password" id="password" type="password" class="loginpwd" placeholder="密码"
                           onclick="JavaScript:this.value='';this.style.color='black';this.type='password'"/></li>
                <li><input type="button" class="loginbtn" value="登录"
                           onclick="javascript:userLogin.login();"/></li>
            </ul>
        </div>
    </form>
</div>
<div class="loginbm">让教学更生动，让学习更有效，让成长更全面</div>
</body>
</html>
