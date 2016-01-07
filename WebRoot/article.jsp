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
							<c:when test="${sessionScope.login != null && (sessionScope.login).equals(\"login\")}">
								<c:url var="show" value="show.jsp">
									<c:param name="id" value="${sessionScope.id}"></c:param>
								</c:url>
								<td><a href="${show}"><c:out value="${sessionScope.uname}"></c:out></a></td>
								<td><a href="exit.jsp">退出</a></td>
							</c:when>
							<c:otherwise>
								
								<td><a href="login.jsp">登录</a></td>
								<td><a href="regist.jsp">注册</a></td>
								<c:choose>
									<c:when test="${sessionScope.loginer == null }">
									</c:when>
									<c:otherwise>
										<td nowrap="nowrap" width="1%"><a href="loginerExit.jsp">退出</a></td>
									</c:otherwise>
								</c:choose>
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
			<tbody>
				<tr valign="top">
					<td><span class="nobreak"> 页: ${requestScope.pageNo },${requestScope.total } - <span
							class="jive-paginator"> [ <a
								href="${upArticle}">上一页</a>
								| <a href="${FirstArticle }"
								class="">首頁</a> |<a
								href="${LastArticle}"
								>尾頁</a> | <a
								href="${downArticle }">下一页</a>
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
									 
										<!-- start -->
										<c:forEach items="${requestScope.articles}" var="article"
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
														<c:choose>
															<c:when test="${sessionScope.loginer == null }">
																<td nowrap="nowrap" width="1%">&nbsp;&nbsp;&nbsp;</td>
															</c:when>
															<c:otherwise>
																<c:url var="deleteArticle" value="servlet">
																	<c:param name="rootid" value="${article.rootid}"></c:param>
																	<c:param name="operation" value="deleteArticle"></c:param>
																</c:url>
																<td nowrap="nowrap" width="1%"><a href="${deleteArticle }">删除</a></td>
															</c:otherwise>
														</c:choose>
														
														<td class="jive-thread-name" width="90%"><a
															id="jive-thread-2"
															href="detail.jsp?rootid=${article.rootid }">${article.title}</a></td>
														<td class="jive-author" nowrap="nowrap" width="1%"><span
															class=""> <a
																href="show.jsp?id=${article.authorid}">${article.authorName }</a>
														</span></td>
														<td class="jive-view-count" width="1%">${article.scan}</td>
														<td class="jive-msg-count" width="1%">${article.reply}</td>
														<td class="jive-last" nowrap="nowrap" width="1%"><div
																class="jive-last-post">
																${article.pdate } <br> by: <a
																	href="show.jsp?id=${article.latestreply}"
																	title="${article.lreplyName}" style="">${article.lreplyName} &#187;</a>
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
														<c:choose>
															<c:when test="${sessionScope.loginer == null }">
																<td nowrap="nowrap" width="1%">&nbsp;&nbsp;&nbsp;</td>
															</c:when>
															<c:otherwise>
																<c:url var="deleteArticle" value="servlet">
																	<c:param name="rootid" value="${article.rootid}"></c:param>
																	<c:param name="operation" value="deleteArticle"></c:param>
																</c:url>
																<td nowrap="nowrap" width="1%"><a href="${deleteArticle }">删除</a></td>
															</c:otherwise>
														</c:choose>
														<td class="jive-thread-name" width="90%"><a
															id="jive-thread-1"
															href="detail.jsp?rootid=${article.rootid }">${article.title}</a></td>
														<td class="jive-author" nowrap="nowrap" width="1%"><span
															class=""> <a
																href="show.jsp?id=${article.authorid}">${article.authorName }</a>
														</span></td>
														<td class="jive-view-count" width="1%">${article.scan}</td>
														<td class="jive-msg-count" width="1%">${article.reply}</td>
														<td class="jive-last" nowrap="nowrap" width="1%"><div
																class="jive-last-post">
																${article.pdate } <br> by: <a
																	href="show.jsp?id=${article.latestreply}"
																	title="${article.lreplyName}" style="">${article.lreplyName} &#187;</a>
															</div></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<!-- end -->
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
