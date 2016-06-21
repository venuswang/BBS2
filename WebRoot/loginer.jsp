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
	<link rel="stylesheet" href="css/basic.min.css" />
	<link rel="stylesheet" href="css/login.css" />
	<script src="js/jquery-1.12.3.min.js"></script>
	<script src="js/jquery.validate.min.js"></script>

</head>

<body>
	<!-- 登录 -->
	<div id="login-form-container">
		<div id="login-result">
			<div class="result-info" style="width: 300px;">
				<a class="btn-close">X</a>
				<span class="inform">${requestScope.warning}</span>
			</div>
		</div>
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
					<input type="hidden" name="operation" value="hloginCheck" />
				</p>
			</fieldset>
		</form>
	</div>
	<script>
		$(function(){
			var $form = $('#login-form'),
				$result = $('#login-result'),
				$btnClose =$('.btn-close');
			(function(){
				var winH = $(window).height(),
					formH = $form.height(),
					formW = $form.width();
				$form.css({
					marginLeft: -(formW / 2),
					top: ( winH / 2 - formH / 2 )
				});
				if ( !($result.find('.inform').eq(0).text().length > 0)) {
					$result.hide();
				}
			})();
			//表单验证 
			$form.validate({
				rules:{
					username: {
						required: true,
						rangelength: [6, 30]
					},
					password: {
						required: true,
						rangelength: [6, 16]
					}
				},
				messages: {
					username: {
						required: "请输入用户名",
						rangelength: "用户名为6-30位"
					},
					password: {
						required: "请输入密码",
						rangelength: "密码长度为6-16位"
					}
				},
				focusInvalid: true,
				errorClass: 'loginError',
				validClass: 'loginPass',
				errorElement: 'i',
				errorContainer: true
			});

			// 提交表单
			$('#submit').bind('click', function(event) {
				if ($('#login-form').valid()) {
					$('#login-form').submit();
				}
				return false;
			});

			// 关闭登录错误提示
			$btnClose.on("click", function(){
				$result.hide();
			});
			$result.on('click', function(){
				$(this).hide();
			});
		});
	</script>
</body>
</html>
