<%@ page language="java" import="java.util.*,java.sql.*,cn.scau.edu.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html> 	
	<head>
		
	</head>
	<body>
	
		<!--  Place this in the 'body' section of your page.-->
		恭喜您，发帖成功,<span id="timer" style="background:red" >5</span>秒后自动跳转到帖子列表页，如果不能跳转，请点击下面的连接
	
		<script language="JavaScript1.2" type="text/javascript">
		
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
		
		<!-- Replace article.jsp to your own url -->
		<a href="article.jsp">返回帖子首页</a>
		<script type="text/javascript">
			delayURL("article.jsp");
		</script>
		
	</body>
</html>  