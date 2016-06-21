<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>login page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/basic.min.css" />
	<link rel="stylesheet" href="css/login.css" />
	<script src="js/jquery-1.12.3.min.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/login.js"></script>
</head>

<body>
	<!-- 登录 -->
	<div id="login-form-container">
		<div id="login-result">
			<div class="result-info">
				<a class="btn-close">X</a>
				<span>${requestScope.state}</span>
			</div>
		</div>
		<form action="servlet" id="login-form" method="post">
			<fieldset class="form-fieldset">
				<legend class="form-legend">用户登录</legend>
				<p class="form-item">
					<lable for="username" class="item-lable">用户名</lable>
					<input type="text" id="username" name="username" class="item-scanf" />
				</p>
				<p class="form-item">
					<lable for="password" class="item-lable">密码</lable>
					<input type="password" id="password" name="password" class="item-scanf" />
				</p>
				<p class="form-item">
					<a href="javascript:void(0)" id="submit">登录</a>
				</p>
				<p>
					<input type="hidden" name="operation" value="loginCheck" />
				</p>
			</fieldset>
		</form>
	</div>
</body>
</html>
