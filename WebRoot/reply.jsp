<%@ page language="java"
	import="java.util.*,java.sql.*,cn.scau.edu.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:choose>
	<c:when test="${sessionScope.login == null }">
		<%request.setAttribute("state","你还没有登录");%>
		<jsp:forward page="login.jsp"></jsp:forward>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${param.authorid == null }">
		<%request.setAttribute("warn", "无相关内容!!!"); %>
		<jsp:forward page="error.jsp"></jsp:forward>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${param.pid == null }">
		<%request.setAttribute("warn", "无相关内容!!!"); %>
		<jsp:forward page="error.jsp"></jsp:forward>
	</c:when>
</c:choose>
<c:choose>
	<c:when test="${param.rootid == null }">
		<%request.setAttribute("warn", "无相关内容!!!"); %>
		<jsp:forward page="error.jsp"></jsp:forward>
	</c:when>
</c:choose>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>post page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/basic.min.css" />
<link rel="stylesheet" href="css/reply.css" />
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
</head>

<body>
	<div id="container-reply-form">
		<form action="servlet" method="post" id="reply-form">
			<div class="post-title">
				<label for="post-title">标题</label>
				<input type="text" name="title" required="required" id="post-title" />
			</div>
			<div class="post-content">
				<label for="post-content" class="content-lable">内容</label>
				<textarea name="cont" required="required" id="post-content"></textarea>
				<script type="text/javascript">CKEDITOR.replace("cont");</script>
			</div>
			<div class="operation">
				<input type="submit" value="发表" id="post-submit" />
			</div>
			<div class="operation-hidden">
				<input type="hidden" name="operation" value="replyArticle" />
				<input type="hidden" name="authorid" value="${param.authorid}" />
				<input type="hidden" name="pid" value="${param.pid}" />
				<input type="hidden" name="rootid" value="${param.rootid}" />
				<input type="hidden" name="replyName" value="${param.replyName}" />
				<input type="hidden" name="floor" value="${param.floor}" />
			</div>
		</form>
	</div>
</body>
</html>
