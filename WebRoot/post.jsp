<%@ page language="java" import="java.util.*,java.sql.*,cn.scau.edu.util.*" pageEncoding="UTF-8"%>
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>post  page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="ckeditor/ckeditor.js" ></script>
  </head>
  
  <body>
    <form action="servlet" method = "post">
  		<table border="1">
  			<tr>
  				<td>
  					标题：<input type="text" name="title" required="required"></input>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					内容：<textarea name="cont" rows="15" cols="60" required ></textarea>
  					<!-- add in tag "body",and below the tag "textarea" "cont" replace the name of  textarea-->
  					<script type="text/javascript">CKEDITOR.replace("cont");</script>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<input type="submit" value="提交" />
  					&nbsp;
  					<input type="reset" value="重置" />
  				</td>
  			</tr>
  			<input type="hidden" name="operation" value="postArticle" />
	    	<input type="hidden" name="authorid" value="${param.authorid}" />
  		</table>
  	</form>
  </body>
</html>
