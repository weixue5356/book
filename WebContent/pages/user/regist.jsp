<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>

<%-- 静态包含，头部信息   base标签，css样式，js-jquery  --%>
<%@ include file="/pages/common/header.jsp" %>


<script type="text/javascript">

	// 开发中都是使用js的框架---jquery
	$(function(){
		$("#username").blur(function(){
			//1 获取输入框中的用户名
			var username = this.value;
			//2发起请求给服务器
			$.get("userServlet","action=ajaxExistsUsername&username=" + username,function(msg){
			//3接收响应的结果，提示用户
				if (msg.isExists) {
					//已存在
					$("span.errorMsg").text("用户名已存在！");
				} else {
					// 可用
					$("span.errorMsg").text("用户名可用！");
				}
			},"json");
		});
		
		$("#codeImg").click(function(){
			// 在单击事件的function函数中，有一个this对象，这个this对象是当前正在响应事件的dom对象
			this.src = "kaptcha.jpg?t=" + new Date();
		});
		
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

// 			验证确认密码：和密码相同
			var repwdText = $("#repwd").val();
			if (repwdText != passwordText) {
				// 4 提示用户验证的结果
				$("span.errorMsg").text("确认密码和密码不一致！");
				return false;
			}
			
// 			邮箱验证：xxxxx@xxx.com
			//1 获取到邮箱内容
			var emailText = $("#email").val();
			//2 创建一个邮箱的正则表达式
			var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			//3 验证
			if (!emailPatt.test(emailText)) {
				//4 提示用户
				$("span.errorMsg").text("邮箱格式不合法！");
				return false;			
			}

			// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
			// 1 先获取到验证码输入框中的内容
			var codeText = $("#code").val();
			
// 			alert("去掉空格前：[" + codeText + "]");
			codeText = $.trim( codeText );
// 			alert("去掉空格后：[" + codeText + "]");
			
			// 2 判断是否等于空串
			if (codeText == "") {
			// 3 提示用户
				$("span.errorMsg").text("验证码不能为空！");
				return false;	
			}

			$("span.errorMsg").text("");

			return true;
		});
		
		
	});
</script>

<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
<%-- 									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %> --%>
									${ requestScope.msg }
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" 
										tabindex="1" name="username" id="username" 
										value="${ requestScope.username }"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" 
										tabindex="1" name="password" id="password"/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" 
										tabindex="1" name="repwd" id="repwd"/>
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" 
										tabindex="1" name="email" id="email" 
										value="${ requestScope.email }"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 120px;" name="code" id="code" value="abcde"/>
									<img id="codeImg" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 100px;height: 32px;">							
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
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
</body>
</html>