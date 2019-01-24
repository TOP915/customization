<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${ctPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctPath}/js/jquery.js"></script>

<script language="javascript">
	$(function(){
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
	$(window).resize(function(){  
    $('.error').css({'position':'absolute','left':($(window).width()-490)/2});
    })  
});  
</script>

</head>
<body style="background:#edf6fa;">
    <div class="error">
        <p>登录信息已过期，请重新登录！</p>
    <div class="reindex"><a href="${ctPath}/cususer/login.jsp" target="_parent">重新登录</a></div>
    </div>
</body>
</html>
