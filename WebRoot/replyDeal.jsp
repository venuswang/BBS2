<%@ page language="java"
	import="java.util.*,java.sql.*,cn.scau.edu.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<title>成功</title>
	<link rel="stylesheet" href="css/basic.min.css" />
	<link rel="stylesheet" href="css/replyDeal.css" />
</head>
<body>

	<div class="container">
		<!--  Place this in the 'body' section of your page.-->
		<p>恭喜您，发帖成功</p>
		<p>
			<span id="timer" style="background:#45D29D; font-size: 16px; padding: 2px;color: #EFEDED;text-align: center;">5</span>秒后自动跳转到帖子列表页
		</p>
		<p>如果不能跳转，请点击下面的连接</p>

		<!-- Replace article.jsp to your own url -->
		<a href="article.jsp">返回帖子首页</a>
	</div>
	<script language="JavaScript1.2" type="text/javascript">
		window.onload = function() {
			delayURL("article.jsp");
		};
		function delayURL(url) {
			var timer = document.getElementById("timer").innerHTML;
			if(timer > 1) {
				timer --;
				document.getElementById("timer").innerHTML=timer;
			} else {
				window.top.location.href= url;
			}
		    setTimeout("delayURL('" + url + "')",1000);
		}
		
	</script>
</body>
</html>
