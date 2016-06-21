<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.*,cn.scau.edu.pojo.*,cn.scau.edu.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${requestScope.articles == null }">
		<c:url var="detailArticles" value="servlet">
			<c:param name="operation" value="detailArticles"></c:param>
			<c:param name="rootid" value="${param.rootid}"></c:param>
		</c:url>
		<c:redirect url="${detailArticles}"></c:redirect>
	</c:when>
</c:choose>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Java|Java世界_中文论坛|ChinaJavaWorld技术论坛 :
	初学java遇一难题！！望大家能帮忙一下 ...</title>
<meta http-equiv="content-type" content="text/html; charset=GBK">
<link rel="stylesheet" type="text/css" href="images/style.css"
	title="Integrated Styles">
<script language="JavaScript" type="text/javascript"
	src="images/global.js"></script>
<link rel="alternate" type="application/rss+xml" title="RSS"
	href="http://bbs.chinajavaworld.com/rss/rssmessages.jspa?threadID=744236">
	<link rel="stylesheet" href="css/basic.min.css" />
	<link rel="stylesheet" href="css/detail.css" />
	<script src="js/jquery-1.12.3.min.js"></script>
</head>
<body>
	<header class="header">
		<div class="common-left">
			<a href="http://www.scau.edu.cn" class="btn-logo">
				<img src="images/header-left.gif" alt="华南农业大学" />
			</a>
		</div>
		<div class="common-middle">
			<h2 class="title">Java开发者的交流天堂</h2>
		</div>
<!-- 		<div class="common-right"> -->
<!-- 			<img src="images/header-right.gif" alt="" /> -->
<!-- 			<div class="operations"> -->
<!-- 				<a href="exit.jsp" class="btn">退出</a> -->
<!-- 			</div> -->
<!-- 		</div> -->
	</header><!-- /header -->
	<!-- 帖子回复 -->
	<ul id="jive-message-holder">
		<c:forEach var="article" items="${requestScope.articles }" varStatus="num">
			<c:choose>
				<c:when test="${num.count == 1 }">
					<c:set var="sign" value="父贴"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="sign" value="第${num.count - 1}楼"></c:set>
				</c:otherwise>
			</c:choose>
			<li class="list-item">
				<!-- 个人信息 -->
				<div class="item-user-info">
					<div class="user-info-fund">
						<a href="show.jsp?id=${article.authorid}" title="${article.authorName}" class="user-info-name">
							${article.authorName}
						</a>
						<img class="user-info-level" src="images/level3.gif" title="世界新手" alt="" />
					</div>
					<div class="user-info-image">
						<img class="info-image" src="images/avatar-display.png" alt="" />
					</div>
					<div class="user-info-pub">
						<span>发表：</span>
						<span class="pub-num">34</span>
					</div>
					<div class="user-info-star">
						<span>点数：</span>
						<span class="star-num">100</span>
					</div>
					<div class="user-info-register">
						<span>注册：</span>
						<span class="register-time">07-5-10</span>
					</div>
					<div class="user-info-blog">
						<a href="show.jsp?id=${article.authorid}" target="_blank">访问我的Blog</a>
					</div>
				</div>
				<!-- 帖子内容 -->
				<div class="item-content">
					<div class="item-content-subject">
						<span class="jive-subject">${sign} @ ${article.title }</span>
					</div>
					<div class="item-content-reply">
						<a href="reply.jsp?pid=${article.id}&rootid=${article.rootid}&replyName=${article.authorName}&floor=${sign}&authorid=${sessionScope.id}" title="回复本主题" class="btn">
						回复
						</a>
					</div>
					<div class="item-content-detail">
						<p class="detail-content">${article.cont}</p>
					</div>
					<div class="item-content-footer">
						<img src="images/sigline.gif" />
						<p>学程序是枯燥的事情，愿大家在一起能从中得到快乐！</p>
					</div>
					<div class="item-content-friend">
						<a href="http://www.scau.edu.cn" class="btn-friend">http://www.scau.edu.cn</a>
					</div>
				</div>
			</li>
		</c:forEach>
		<div class="jive-message-list-footer">
			<a href="article.jsp" class="btn-back">返回到主题列表</a>
		</div>
	</ul>
</body>
</html>
