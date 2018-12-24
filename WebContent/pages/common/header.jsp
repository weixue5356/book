<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		getScheme()  获取请求协议
		getServerName() 获取请求的服务器ip
		getServerPort() 获取请求的服务器端口号
		getContextPath() 获取工程路径（工程名）
	*/
	
	String path = request.getScheme() 
			+ "://"
			+ request.getServerName()
			+ ":"
			+ request.getServerPort()
			+ request.getContextPath()
			+ "/";
	pageContext.setAttribute("path", path);
%>
 
<base href="<%=path %> " />
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    
    