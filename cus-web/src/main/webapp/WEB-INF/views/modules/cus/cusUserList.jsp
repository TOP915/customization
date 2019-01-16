<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cus/cusUser/">用户列表</a></li>
		<%--<shiro:hasPermission name="cus:cusUser:edit"><li><a href="${ctx}/cus/cusUser/form">用户添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="cusUser" action="${ctx}/cus/cusUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="clearfix"></li>
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="userName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>登录名</th>
				<th>昵称</th>
				<th>邮件</th>
				<th>电话</th>
				<th>微信</th>
				<th>QQ</th>
				<th>更新时间</th>
				<shiro:hasPermission name="cus:cusUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cusUser">
			<tr>
				<td><%--<a href="${ctx}/cus/cusUser/form?id=${cusUser.id}"></a>--%>${cusUser.id}</td>
				<td>${cusUser.loginName}</td>
				<td>${cusUser.userName}</td>
				<td>${cusUser.userEmail}</td>
				<td>${cusUser.userPhone}</td>
				<td>${cusUser.userWechat}</td>
				<td>${cusUser.userQq}</td>
				<td><fmt:formatDate value="${cusUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<shiro:hasPermission name="cus:cusUser:edit"><td>
    				<%--<a href="${ctx}/cus/cusUser/form?id=${cusUser.id}">修改</a>--%>
					<a href="${ctx}/cus/cusUser/delete?id=${cusUser.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>