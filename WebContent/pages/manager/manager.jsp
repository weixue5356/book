<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>

<%-- 静态包含，头部信息   base标签，css样式，js-jquery  --%>
<%@ include file="/pages/common/header.jsp" %>


<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">后台管理系统</span>
		<%-- 这是静态包含，manager模块的菜单 --%>
		<%@ include file="/pages/common/manager_menu.jsp" %>
	</div>
	
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>