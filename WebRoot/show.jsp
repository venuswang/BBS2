<!-- 用户信息展示  -->
<%@ page language="java" import="java.util.*,cn.scau.edu.pojo.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:choose>
	<c:when test="${sessionScope.login == null}">
		<%request.setAttribute("state","你还没有登录");%>
		<jsp:forward page="login.jsp"></jsp:forward>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${requestScope.author == null}">
		<c:url var="show" value="servlet">
			<c:param name="id" value="${param.id}"></c:param>
			<c:param name="operation" value="authorShow"></c:param>
		</c:url>
		<c:redirect url="${show}"></c:redirect>
	</c:when>
</c:choose>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>show page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<table border="1" align="center">
		<tr>
			<td>&nbsp;</td>
			<td>用户信息</td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td>${sessionScope.uname}</td>
		</tr>
		<tr>
			<td>性别：</td>
			<td>${requestScope.author.sex}</td>
		</tr>
		<tr>
			<td>编程爱好：</td>
			<td>${requestScope.author.slikes}</td>
		</tr>
		<tr>
			<td>爱好：</td>
			<td>${requestScope.author.mlikes}</td>
		</tr>
		<tr>
			<td>简介：</td>
			<td>${requestScope.author.introduce}</td>
		</tr>
	</table>
	<center>
		<a href="article.jsp">返回首页</a>
	</center>
</body>
</html>
