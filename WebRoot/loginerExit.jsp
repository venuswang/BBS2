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

<title>LoginerExit page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/basic.min.css" />
<link rel="stylesheet" href="css/exit.min.css" />
<style type="text/css">
	.common-right .title {
		font-size: 18px;
		color: #4C4747;
	}
	#main .operations {
		background: none;
	}
</style>
</head>

<body>
	<div id="container">
		<div id="header">
				<div class="common-left">
					<img src="images/header-left.gif" alt="bbs logo" class="bbs-log" />
				</div>
				<div class="common-right">
					<h2 class="title">敬爱的管理员，您已经安全退出!!!欢迎下次登录</h2>
				</div>
			</div>
		<div id="main">
			<div class="operations">
				<a href="loginer.jsp" id="btn-login" class="btn">重新登录</a>
				<a href="article.jsp" id="btn-index" class="btn">返回首页</a>
			</div>
		</div>
	</div>
</body>
</html>