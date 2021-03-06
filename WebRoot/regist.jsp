<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>个人注册</title>
	<link rel="stylesheet" href="css/basic.min.css" />
	<link rel="stylesheet" href="css/register.min.css" />
	<link rel="stylesheet" href="css/sumoselect.min.css" />
	<link rel="stylesheet" href="css/form.min.css" />
	<script src="js/jquery-1.12.3.min.js"></script>
	<script src="js/jquery.sumoselect.min.js"></script>
	<script src="js/jquery.validate.min.js"></script>
	<script src="js/register.min.js"></script>
	<script src="js/form.min.js"></script>
</head>

<body>
	<center>
		<c:out value="${requestScope.warnning}"></c:out>
	</center>
	<div class="container">
		<div id="header">
			<div class="common-left">
				<img src="images/header-left.gif" alt="bbs logo" class="bbs-log" />
			</div>
			<div class="common-middle">
				<h1 class="title">欢<span class="appearence1">迎</span>注<span class="appearence2">册</span></h1>
			</div>
			<div class="common-right">
				<span class="info">已有账号</span>
				<a href="javascript:void(0)" class="btn-login" id="btn-login">登录</a>
			</div>
		</div>
		<div id="main">
			<form name="form" action="servlet" method="post" class="register-form" id="register-form">
				<!-- username -->
				<div class="register-item">
					<label for="register-username" class="register-label label-three">用户名</label>
					<input type="text" name="username" placeholder="您的账号名和登录名" id="register-username" class="register-content-text" />
				</div>
				<!-- password -->
				<div class="register-item">
					<label for="register-password" class="register-label">设置密码</label>
					<input type="password" name="pwd" placeholder="您登录时的密码" id="register-password" class="register-content-text" />
				</div>
				<!-- check password -->
				<div class="register-item">
					<label for="register-checkpwd" class="register-label">确认密码</label>
					<input type="password" name="pwd2" placeholder="请再次输入密码" id="register-checkpwd" class="register-content-text" />
				</div>
				<!-- sex -->
				<div class="register-item">
					<label class="register-label label-three">性&nbsp;别</label>
					<input type="radio" name="sex" value="male" id="register-sex-man" />
					<label for="register-sex-man">男</label>
					<input type="radio" name="sex" value="female" id="register-sex-women" class="register-content-radio" />
					<label for="register-sex-women">女</label>
				</div>
				<!-- 编程爱好 -->
				<div class="register-item register-item-checkbox">
					<label class="register-label">编程爱好</label>
					<input type="checkbox" name="slikes" value="vc" checked="checked" id="code-item-vc" class="register-content-checkbox" />
					<label for="code-item-vc">VC</label>
					<input type="checkbox" name="slikes" value="vb" id="code-item-vb" class="register-content-checkbox" />
					<label for="code-item-vb">VB</label>
					<input type="checkbox" name="slikes" value="c#" id="code-item-c#" class="register-content-checkbox" />
					<label for="code-item-c#">C#</label>
					<input type="checkbox" name="slikes" value="java" id="code-item-java" class="register-content-checkbox" />
					<label for="code-item-java">Java</label>
					<input type="checkbox" name="slikes" value="python" id="code-item-py" class="register-content-checkbox" />
					<label for="code-item-py">Python</label>
					<input type="checkbox" name="slikes" value="js" id="code-item-js" class="register-content-checkbox" />
					<label for="code-item-js">Javascript</label>
					<input type="checkbox" name="slikes" value="c++" id="code-item-c++" class="register-content-checkbox" />
					<label for="code-item-c++">C++</label>
					<input type="checkbox" name="slikes" value="delphi" id="code-item-delphi" class="register-content-checkbox" />
					<label for="code-item-delphi">Delphi</label>
				</div>
				<!-- 生活爱好 -->
				<div class="register-item register-item-select">
					<label for="register-life-love" class="register-label register-label-select">生活爱好</label>
					<select name="mlikes" multiple="multiple" id="register-life-love"  class="SlectBox">
						<option value="篮球">篮球</option>
						<option value="足球">足球</option>
						<option value="游戏">游戏</option>
						<option value="动漫">动漫</option>
						<option value="看书">看书</option>
						<option value="连续剧">连续剧</option>
						<option value="编程" selected="selected">编程</option>
						<option value="电影">电影</option>
					</select>
				</div>
				<!-- 居住地 -->
				<div class="register-item  register-item-select">
					<label for="register-address" class="register-label label-three">居住地</label>
					<select name="province" id="register-address"  class="SlectBox">
						<option value=0 selected>请选择</option>
						<option value=34>安徽</option>
						<option value=11>北京</option>
						<option value=50>重庆</option>
						<option value=35>福建</option>
						<option value=62>甘肃</option>
						<option value=44>广东</option>
						<option value=45>广西</option>
						<option value=52>贵州</option>
						<option value=46>海南</option>
						<option value=13>河北</option>
						<option value=23>黑龙江</option>
						<option value=41>河南</option>
						<option value=42>湖北</option>
						<option value=43>湖南</option>
						<option value=32>江苏</option>
						<option value=36>江西</option>
						<option value=22>吉林</option>
						<option value=21>辽宁</option>
						<option value=64>宁夏</option>
						<option value=15>内蒙古</option>
						<option value=63>青海</option>
						<option value=31>上海</option>
						<option value=14>山西</option>
						<option value=37>山东</option>
						<option value=51>四川</option>
						<option value=61>陕西</option>
						<option value=12>天津</option>
						<option value=54>西藏</option>
						<option value=65>新疆</option>
						<option value=53>云南</option>
						<option value=33>浙江</option>
						<option value=71>台湾</option>
						<option value=81>香港</option>
						<option value=82>澳门</option>
						<option value=0>其他</option>
					</select>
				</div>
				<!-- 自我介绍 -->
				<div class="register-item register-content-textarea">
					<label class="register-label" class="register-label">自我介绍</label>
					<textarea name="intro" class="intro-textarea" placeholder="简单的自我介绍...."></textarea>
				</div>
				<!-- 表单操作 -->
				<div class="register-item register-content-opera">
					<input type="submit" value="提交" class="form-opera" id="form-submit" />
					<input type="reset" value="重置" class="form-opera" />
					<input type="hidden" name="operation" value="regist" />
				</div>
			</form>
		</div>
	</div>
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