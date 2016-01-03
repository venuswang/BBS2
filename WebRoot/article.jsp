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
</head>

<body>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td width="140"><a href="http://www.scau.edu.cn"><img
						src="images/header-left.gif" alt="华南农业大学" border="0"></a></td>
				<td><img src="images/header-stretch.gif" alt="" border="0"
					height="57" width="100%"></td>
				<td width="1%"><img src="images/header-right.gif" alt=""
					border="0"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<div id="jive-forumpage">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="90%"><p class="jive-breadcrumbs">论坛: Java语言*初级版
							(模仿)</p>
						<p class="jive-description">探讨Java语言基础知识,基本语法等 大家一起交流
							共同提高！谢绝任何形式的广告</p></td>
						<c:choose>
							<c:when test="${sessionScope.login != null || (sessionScope.login).equles(\"login\")}">
								<c:url var="show" value="show.jsp">
									<c:param name="id" value="${sessionScope.id}"></c:param>
								</c:url>
								<td><a href="${show}"><c:out value="${sessionScope.uname}"></c:out></a></td>
								<td><a href="exit.jsp">退出</a></td>
							</c:when>
							<c:otherwise>
								
								<td><a href="login.jsp">登录</a></td>
								<td><a href="regist.jsp">注册</a></td>
							</c:otherwise>
						</c:choose>
				</tr>
			</tbody>
		</table><p></p>
		<div class="jive-buttons">
			<table summary="Buttons" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td class="jive-icon"><img
								src="images/post-16x16.gif" alt="发表新主题" border="0" height="16"
								width="16"></td>
						<td class="jive-icon-label"><a id="jive-post-thread"
							href="post.jsp?authorid=${sessionScope.id}">发表新主题</a>
					</tr>
				</tbody>
			</table>
		</div>
		<br>
		<table border="0" cellpadding="3" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td><span class="nobreak"> 页: 1,316 - <span
							class="jive-paginator"> [ <a
								href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;start=0&amp;isBest=0">上一页</a>
								| <a
								href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;start=0&amp;isBest=0"
								class="">1</a> <a
								href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;start=25&amp;isBest=0"
								class="jive-current">2</a> <a
								href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;start=50&amp;isBest=0"
								class="">3</a> <a
								href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;start=75&amp;isBest=0"
								class="">4</a> <a
								href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;start=100&amp;isBest=0"
								class="">5</a> <a
								href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;start=125&amp;isBest=0"
								class="">6</a> | <a
								href="http://bbs.chinajavaworld.com/forum.jspa?forumID=20&amp;start=50&amp;isBest=0">下一页</a>
								]
						</span>
					</span></td>
				</tr>
			</tbody>
		</table>

		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr valign="top">
					<td width="99%"><div class="jive-thread-list">
							<div class="jive-table">
								<table summary="List of threads" cellpadding="0" cellspacing="0"
									width="100%">
									<thead>
										<tr>
											<th class="jive-first" colspan="3">主题</th>
											<th class="jive-author"><nobr> 作者 &nbsp; </nobr></th>
											<th class="jive-view-count"><nobr> 浏览 &nbsp; </nobr></th>
											<th class="jive-msg-count" nowrap="nowrap">回复</th>
											<th class="jive-last" nowrap="nowrap">最新帖子</th>
										</tr>
									</thead>
									<tbody>
									<%-- 
										<!-- start -->
										<c:forEach items="${applicationScope.articles}" var="article"
											varStatus="num">
											<c:choose>
												<c:when test="${num.count % 2 == 1}">
													<tr class="jive-odd">
														<td class="jive-first" nowrap="nowrap" width="1%"><div
																class="jive-bullet">
																<img src="images/read-16x16.gif" alt="已读" border="0"
																	height="16" width="16">
																<!-- div-->
															</div></td>
														<td nowrap="nowrap" width="1%">&nbsp; &nbsp;</td>
														<td class="jive-thread-name" width="95%"><a
															id="jive-thread-2"
															href="http://bbs.chinajavaworld.com/thread.jspa?threadID=744234&amp;tstart=25">${article.title}</a></td>
														<td class="jive-author" nowrap="nowrap" width="1%"><span
															class=""> <a
																href="http://bbs.chinajavaworld.com/profile.jspa?userID=226028">admin</a>
														</span></td>
														<td class="jive-view-count" width="1%">52</td>
														<td class="jive-msg-count" width="1%">2</td>
														<td class="jive-last" nowrap="nowrap" width="1%"><div
																class="jive-last-post">
																${article.pdate } <br> by: <a
																	href="http://bbs.chinajavaworld.com/thread.jspa?messageID=780172#780172"
																	title="downing114" style="">downing114 &#187;</a>
															</div></td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr class="jive-even">
														<td class="jive-first" nowrap="nowrap" width="1%"><div
																class="jive-bullet">
																<img src="images/read-16x16.gif" alt="已读" border="0"
																	height="16" width="16">
																<!-- div-->
															</div></td>
														<td nowrap="nowrap" width="1%">&nbsp; &nbsp;</td>
														<td class="jive-thread-name" width="95%"><a
															id="jive-thread-1"
															href="http://bbs.chinajavaworld.com/thread.jspa?threadID=744236&amp;tstart=25">${article.title}</a></td>
														<td class="jive-author" nowrap="nowrap" width="1%"><span
															class=""> <a
																href="http://bbs.chinajavaworld.com/profile.jspa?userID=226030">admin</a>
														</span></td>
														<td class="jive-view-count" width="1%">104</td>
														<td class="jive-msg-count" width="1%">5</td>
														<td class="jive-last" nowrap="nowrap" width="1%"><div
																class="jive-last-post">
																${article.pdate } <br> by: <a
																	href="http://bbs.chinajavaworld.com/thread.jspa?messageID=780182#780182"
																	title="jingjiangjun" style="">jingjiangjun &#187;</a>
															</div></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<!-- end -->
								--%>
									</tbody>
								</table>
							</div>
						</div>
						<div class="jive-legend"></div></td>
				</tr>
			</tbody>
		</table>
		<br /> <br />
	</div>
</body>
</html>
