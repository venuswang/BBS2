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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<center><h2>欢迎来到BBS后台</h2></center>
  	
    <form action="servlet" method="post">
    	<table align="center">
    		<tr>
    			<td>${requestScope.warning}</td>
    		</tr>
    		<tr>
    			<td>用户名：<input type="text" name="username" size="30" maxlength="30" required/></td>
    		</tr>
    		<tr>
    			<td>密&nbsp;码：<input type="password" name="password" size="15" maxlength="12" required /></td>
    		</tr>
    		<tr>
    			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" />&nbsp;&nbsp;<input type="reset" /></td>
    		</tr>
    	</table>
    	<input type="hidden" name="operation" value="hloginCheck"/> 
    </form>
  </body>
</html>
