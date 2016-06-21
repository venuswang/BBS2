<%-- 论坛首页 --%>
<%@ page language="java"
	import="java.util.*,java.sql.*,cn.scau.edu.pojo.Article"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%--<c:out value="${applicationScope.count}"></c:out>--%>
<c:choose>
	<c:when test="${requestScope.articles == null}">
		<c:url var="showArticles" value="servlet">
			<c:param name="operation" value="showArticles"></c:param>
			<c:param name="pageNo" value="${requestScope.pageNo}"></c:param>
		</c:url>
		<c:redirect url="${showArticles}"></c:redirect>
	</c:when>
</c:choose>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Java|Java世界_中文论坛|ChinaJavaWorld技术论坛 : Java语言*初级版</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="images/style.css"
	title="Integrated Styles" />
<script language="JavaScript" type="text/javascript"
	src="images/global.js"></script>
<link rel="alternate" type="application/rss+xml" title="RSS"
	href="http://bbs.chinajavaworld.com/rss/rssmessages.jspa?forumID=20">
<script language="JavaScript" type="text/javascript"
	src="images/common.js"></script>
	<link rel="stylesheet" href="css/basic.min.css" />
	<link rel="stylesheet" href="css/form.min.css" />
	<link rel="stylesheet" href="css/article.css">
	<script src="js/jquery-1.12.3.min.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/form.min.js"></script>
	<style type="text/css">
		.article-item  {
			position: relative;
			margin-top: 15px;
		}
		.article-item:first-child {
			border-top: 1px solid #dedede;
			padding-top: 15px;
		}
		.item-delete {
			text-align: right;
			position: absolute;
			right: 50px;
			top: 30px;
			display: none;
		}
		.btn-delete {
			padding: 8px;
			background-color: #89F9C6;
			color: #363535!important;
			border-radius: 3px;
		}
		.btn-delete:hover {
			background-color: #58F6BE;
			color: #040404 !important;
		}
		.common-middle .title {
			color: #D8D3D3!important;
		}
	</style>
	<script>
		$(function(){
			var $item = $('.article-item');
			$item.hover(function(){
				$(this).find('.item-delete').show(100);
			}, function(){
				$(this).find('.item-delete').hide();
			});
		});
	</script>
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
		<div class="common-right">
			<img src="images/header-right.gif" alt="" />
			<div class="operations">
				<c:choose>
					<c:when test="${sessionScope.login != null && sessionScope.loginer == null && (sessionScope.login).equals('login')}">
						<c:url var="show" value="show.jsp">
							<c:param name="id" value="${sessionScope.id}"></c:param>
						</c:url>
						<a href="${show}" class="btn btn-user">
							<c:out value="${sessionScope.uname}"></c:out>
						</a>
						<a href="exit.jsp" class="btn">退出</a>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${sessionScope.loginer == null }">
								<a href="javascript:void(0);" id="btn-login" class="btn">登录</a>
								<a href="regist.jsp" class="btn">注册</a>
							</c:when>
							<c:otherwise>
								<span>&nbsp;</span>
								<a href="loginerExit.jsp" class="btn">退出</a>
							</c:otherwise>
						</c:choose>
					</c:otherwise>

				</c:choose>
			</div>
		</div>
	</header><!-- /header -->
	<div id="jive-forumpage">
		<div class="jive-buttons">
			<a id="jive-post-thread" href="post.jsp?authorid=${sessionScope.id}">发表新主题</a>
		</div>
		<div class="article-page">
				<c:url var="upArticle" value="servlet">
					<c:param name="operation" value="showArticles"></c:param>
					<c:param name="pageNo" value="${requestScope.pageNo - 1}"></c:param>
				</c:url>
				<c:url var="downArticle" value="servlet">
					<c:param name="operation" value="showArticles"></c:param>
					<c:param name="pageNo" value="${requestScope.pageNo + 1}"></c:param>
				</c:url>
				<c:url var="FirstArticle" value="servlet">
					<c:param name="operation" value="showArticles"></c:param>
					<c:param name="pageNo" value="1"></c:param>
				</c:url>
				<c:url var="LastArticle" value="servlet">
					<c:param name="operation" value="showArticles"></c:param>
					<c:param name="pageNo" value="${requestScope.total}"></c:param>
				</c:url>
				<span>页</span>
				<span class="nobreak">${requestScope.pageNo }</span>
				<span>/</span>
				<span>${requestScope.total }</span>
				<a href="${upArticle}" class="btn-page">上一页</a>
				<a href="${downArticle }" class="btn-page">下一页</a>
				<a href="${FirstArticle }" class="btn-page">首頁</a>
				<a href="${LastArticle}" class="btn-page">尾頁</a>
		</div>
		<!-- start -->
		<ul class="article-list">
			<c:forEach items="${requestScope.articles}" var="article"
				varStatus="num">
				<c:choose>
					<c:when test="${num.count % 2 == 1}">
						<li class="jive-odd article-item">
							<div class="jive-bullet">
								<img src="images/read-16x16.gif" alt="已读" />
							</div>
							<c:choose>
								<c:when test="${sessionScope.loginer == null }">
									<span>&nbsp;&nbsp;&nbsp;</span>
								</c:when>
								<c:otherwise>
									<c:url var="deleteArticle" value="servlet">
										<c:param name="rootid" value="${article.rootid}"></c:param>
										<c:param name="operation" value="deleteArticle"></c:param>
									</c:url>
									<div class="item-delete">
										<a href="${deleteArticle }" class="btn-delete">删除</a>
									</div>
								</c:otherwise>
							</c:choose>

							<div class="jive-thread-name">
								<span class="item-name">主题</span>
								<a id="jive-thread-2" href="detail.jsp?rootid=${article.rootid }">
									${article.title}
								</a>
							</div>
							<div class="jive-author">
								<span class="item-author">作者</span>
								<a href="show.jsp?id=${article.authorid}" class="btn-author">${article.authorName }</a>
							</div>
							<div class="item-feedback">
								<span>浏览</span>
								<span class="feedback-scan">${article.scan}</span>
								<span>回复</span>
								<span class="feedback-reply">${article.reply}</span>
							</div>
							<div class="jive-last-post">
								<span>最新帖子</span>
								<span>${article.pdate }</span>
								<span>by:</span>
								 <a href="show.jsp?id=${article.latestreply}" title="${article.lreplyName}" class="btn-user">${article.lreplyName}
								</a>
							</div>
						</li>
					</c:when>
					<c:otherwise>
						<li class="jive-even article-item">
							<div class="jive-bullet">
									<img src="images/read-16x16.gif" alt="已读" />
							</div>
							<c:choose>
								<c:when test="${sessionScope.loginer == null }">
									<span nowrap="nowrap" width="1%">&nbsp;&nbsp;&nbsp;</span>
								</c:when>
								<c:otherwise>
									<c:url var="deleteArticle" value="servlet">
										<c:param name="rootid" value="${article.rootid}"></c:param>
										<c:param name="operation" value="deleteArticle"></c:param>
									</c:url>
									<div class="item-delete">
										<a href="${deleteArticle }" class="btn-delete">删除</a>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="jive-thread-name">
								<span class="item-name">主题</span>
								<a id="jive-thread-2" href="detail.jsp?rootid=${article.rootid }">
									${article.title}
								</a>
							</div>
							<div class="jive-author">
								<span class="item-author">作者</span>
								<a href="show.jsp?id=${article.authorid}" class="btn-author">${article.authorName }</a>
							</div>
							<div class="item-feedback">
								<span>浏览</span>
								<span class="feedback-scan">${article.scan}</span>
								<span>回复</span>
								<span class="feedback-reply">${article.reply}</span>
							</div>
							<div class="jive-last-post">
								<span>最新帖子</span>
								<span>${article.pdate }</span>
								<span>by:</span>
								 <a href="show.jsp?id=${article.latestreply}" title="${article.lreplyName}" class="btn-user">${article.lreplyName}
								</a>
							</div>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
		<!-- end -->
	</div>
	<!-- 登录 -->
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
