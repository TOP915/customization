﻿<%@ page contentType="text/html;charset=UTF-8" %>
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
<body>
<button id="customBtn" type="button">Google登录</button>
<div id="name"></div>
<button type="button" onclick="signOut();">Sign out</button>

<script src="https://apis.google.com/js/api:client.js"></script>
<script>
    var googleUser = {};
    var startApp = function() {
        gapi.load('auth2', function(){
            // Retrieve the singleton for the GoogleAuth library and set up the client.
            auth2 = gapi.auth2.init({
                client_id: '662426624378-p313vqiou5mhj80coqfkdacalmg5ddfk.apps.googleusercontent.com', //客户端ID
                cookiepolicy: 'single_host_origin',
                scope: 'profile' //可以请求除了默认的'profile' and 'email'之外的数据
            });
            attachSignin(document.getElementById('customBtn'));
        });
    };

    function attachSignin(element) {
        auth2.attachClickHandler(element, {},
            function(googleUser) {
                document.getElementById('name').innerText = "Signed in: " + googleUser.getBasicProfile().getName();
                var profile = auth2.currentUser.get().getBasicProfile();
                console.log('ID: ' + profile.getId());
                console.log('Full Name: ' + profile.getName());
                console.log('Given Name: ' + profile.getGivenName());
                console.log('Family Name: ' + profile.getFamilyName());
                console.log('Image URL: ' + profile.getImageUrl());
                console.log('Email: ' + profile.getEmail());
            }, function(error) {
                console.log(JSON.stringify(error, undefined, 2));
            });
    }
    startApp();

    //注销
    function signOut() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            alert('用户注销成功');
        });
    }
</script>

</body>
</html>
