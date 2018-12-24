<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	
	<%-- 静态包含，头部信息   base标签，css样式，js-jquery  --%>
	<%@ include file="/pages/common/header.jsp" %>
<script type="text/javascript">
	$(function(){
		$("a.addItemA").click(function(){
			//发起请求
			$.getJSON("cartServlet","action=ajaxAddItem&id=" + $(this).attr("itemId") ,function(msg){
// 				3、提示用户，购物车总的商品数量和最后一个添加的商品名称
// 				{lastProductName: "JavaScript从入门到精通", cartTotalCount: 1}	
				$("#totalCountSpan").html("您的购物车中有  " + msg.cartTotalCount + "  件商品");
				$("#lastProductNameDiv").html("您刚刚将<span style=\"color: red\">" + msg.lastProductName + "</span>加入到了购物车中");
			});
			
			return false;
		});
	});
</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<c:choose>
					<%-- 当用户没有登录的时候。显示登录和注册菜单  --%>
					<c:when test="${ empty sessionScope.user }">
						<a href="pages/user/login.jsp">登录</a> | 
						<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
					</c:when>
					<%-- 如果用户已经登录成功，则显示用户尼称(用户名)和注销 --%>
					<c:otherwise>
						<span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临尚硅谷书城</span>
						<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
						<input type="hidden" name="action" value="pageByPrice"/>
					价格：<input id="min" type="text" name="min" value="${ param.min }"> 元 - 
						<input id="max" type="text" name="max" value="${ param.max }"> 元 
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:choose>
					<c:when test="${ empty sessionScope.cart.items }">
						<%-- 购物车为空的情况 --%>
						<span id="totalCountSpan"></span>
						<div id="lastProductNameDiv">
							<span style="color: red">购物车为空！</span>
						</div>	
					</c:when>
					<c:otherwise>
					<%-- 购物车不为空的情况，显示出购物车的简单信息 --%>
						<span id="totalCountSpan">您的购物车中有 ${ sessionScope.cart.totalCount } 件商品</span>
						<div id="lastProductNameDiv">
							您刚刚将<span style="color: red">${ sessionScope.last_product_name }</span>加入到了购物车中
						</div>					
					</c:otherwise>
				</c:choose>
			</div>
			<c:forEach items="${ requestScope.page.items }" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${ book.imgPath }" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${ book.name }</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${ book.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${ book.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${ book.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${ book.stock }</span>
						</div>
						<div class="book_add">
							<a class="addItemA" itemId="${ book.id }" href="cartServlet?action=addItem&id=${ book.id }">加入购物车</a>
						</div>
					</div>
				</div>
			</c:forEach>
			
			
		</div>
		<%-- 引入分页条 --%>
		<%@ include file="/pages/common/page.jsp" %>
	
	</div>
	
	<%-- 使用静态包含，引入脚而部分的内容 --%>
	<%@ include file="/pages/common/footer.jsp" %>
	
</body>
</html>