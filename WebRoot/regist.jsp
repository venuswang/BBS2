<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>表单</title>
		<script language=JavaScript src="images/regcheckdata.js"></script>
		<meta http-equiv="content-type" content="text/html;charset=utf-8"> 
	</head>
	<body>
		<form name="form" action="servlet" method="post" onSubmit="return checkdata()">
			<table width="750" align="center" border="2">
				<tr>
					<td colspan="2" align="center">用户注册</td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td>
						<input type=text name="username" size="30" maxlength="10" >
					</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td>
						<input type=password name="pwd" size="15" maxlength="12">
					</td>
				</tr>
				<tr>
					<td>密码确认</td>
					<td>
						<input type=password name="pwd2" size="15" maxlength="12">
					</td>
				</tr>
				
				<tr>
					<td>性别</td>
					<td>
						<input type=radio name="sex" value="male">男
						<input type=radio name="sex" value="female">女
					</td>
				</tr>
				
				<tr>
					<td>你感兴趣</td>
					<td>
						<input type="checkbox" name="slikes" value="vc" checked>VC
						<input type="checkbox" name="slikes" value="vb">VB
						<input type="checkbox" name="slikes" value="c#">C#
						<input type="checkbox" name="slikes" value="java">Java
						<br>
						<input type="checkbox" name="slikes" value="python">Python
						<input type="checkbox" name="slikes" value="js">Javascript
						<input type="checkbox" name="slikes" value="c++">C++
						<input type="checkbox" name="slikes" value="delphi">Delphi
					</td>
				</tr>
				
				<!--必须选中JSP-->
				<!--  <input type="hidden" name="interest" value="jsp">-->
				
				<tr>
					<td>你感兴趣</td>
					<td>
						<select name="mlikes" size=8 multiple>
							<option value="篮球">篮球</option>
							<option value="足球">足球</option>
							<option value="游戏">游戏</option>
							<option value="动漫">动漫</option>
							<option value="看书">看书</option>
							<option value="连续剧">连续剧</option>
							<option value="编程" selected>编程</option>
							<option value="电影">电影</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td>你来自</td>
					<td>
						<select name="province">
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
					</td>
				</tr>
				
				<tr>
					<td>自我介绍</td>
					<td>
						<textarea rows="12" cols="80" name="intro"></textarea>
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td>
						<input type="submit" value="提交">
						<input type="reset" value="重置">
						<input type="hidden" name="operation" value="regist">
					</td>
				</tr>
				
			</table>
		</form>
	</body>
</html>