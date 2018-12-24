<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>


<%-- 静态包含，头部信息   base标签，css样式，js-jquery  --%>
<%@ include file="/pages/common/header.jsp" %>

<script type="text/javascript">

	// 开发中都是使用js的框架---jquery
	$(function(){
		// 在页面加载完成之后给注册按钮绑定单击事件
		$("#sub_btn").click(function(){
			// 验证表单项是，只要有一个不合法，就返回false 阻止 提交
// 			验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
			// 1  获取用户名输入框中的内容
			var usernameText = $("#username").val();
			// 2 创建用户名的正则表达式
			var usernamePatt = /^\w{5,12}$/;
			// 3 使用正则验证
			if (!usernamePatt.test(usernameText)) {
				// 4 提示用户验证的结果
				$("span.errorMsg").text("用户名不合法！");
				return false;
			}
// 			验证密码：必须由字母，数字下划线组成，并且长度为5到12位
			// 1  获取密码输入框中的内容
			var passwordText = $("#password").val();
			// 2 创建密码的正则表达式
			var passPatt = /^\w{5,12}$/;
			// 3 使用正则验证
			if (!passPatt.test(passwordText)) {
				// 4 提示用户验证的结果
				$("span.errorMsg").text("密码不合法！");
				return false;
			}
			$("span.errorMsg").text("");
			return true;
		});
		
		
	});
</script>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									${ empty msg ? "请输入用户名和密码" : msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="get">
									<input type="hidden" name="action" value="login" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" 
										tabindex="1" name="username" id="username"
										value="${ username }"
										/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" 
										tabindex="1" name="password" id="password"/>
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
		<%
			session.removeAttribute("msg");
			session.removeAttribute("username");
		%>
</body>
</html>