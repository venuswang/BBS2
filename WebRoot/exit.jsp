<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	session.invalidate();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>退出</title>
	<link rel="stylesheet" href="css/basic.min.css" />
	<link rel="stylesheet" href="css/exit.min.css" />
	<link rel="stylesheet" href="css/form.min.css" />
	<script src="js/jquery-1.12.3.min.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/form.min.js"></script>
</head>

<body>
	<div id="container">
		<div id="header">
				<div class="common-left">
					<img src="images/header-left.gif" alt="bbs logo" class="bbs-log" />
				</div>
				<div class="common-right">
					<span class="info">还没有账号？</span>
					<a href="regist.jsp" class="btn-register">注册</a>
				</div>
			</div>
		<div id="main">
			<div class="operations">
				<a href="javascript:void(0);" id="btn-login" class="btn">重新登录</a>
				<a href="article.jsp" id="btn-index" class="btn">返回首页</a>
			</div>
		</div>
	</div>
	<div id="login-form-container">
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
