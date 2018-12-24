<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

<%-- 静态包含，头部信息   base标签，css样式，js-jquery  --%>
<%@ include file="/pages/common/header.jsp" %>
<script type="text/javascript">
	$(function(){
		// change事件
		$("input.updateCart").change(function(){
			if (confirm("你确认要修改【" + $(this).parent().parent().find("td:first").text()  
					+ "】的数量为: " + this.value + " 吗?")) {
				// 确认修改商品数量
				location.href="${path}cartServlet?action=updateCount&id="+$(this).attr("itemid")+"&count=" + this.value;
			} else {
				// 取消修改商品数量
// 				this.value = this.defaultValue;
				this.value = $(this).attr("oldValue");
			}
		});
		
		//清空购物车的确认提示操作
		$("#clearCart").click(function(){
			return confirm("你确定要清空购物车吗?");
		});
		$("a.deleteA").click(function(){
			return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】吗?");
		});
		
	});
</script>

</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>

			<%-- 使用静态包含，引入登录成功之后的菜单  --%>
			<%@ include file="/pages/common/login_success_menu.jsp" %>

	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:choose>
				<c:when test="${ empty sessionScope.cart.items }">
					<tr>
						<td colspan="5"><a href="index.jsp">亲！您的购物车为空，赶快去购物车！！！</a></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ sessionScope.cart.items }" var="entry">	
						<tr>
							<td>${ entry.value.name }</td>
							<td>
								<input oldValue="${ entry.value.count }" itemid="${ entry.value.id }" 
									class="updateCart" value="${ entry.value.count }" 
									style="width: 40px;"/>
							</td>
							<td>${ entry.value.price }</td>
							<td>${ entry.value.totalPrice }</td>
							<td><a class="deleteA" href="cartServlet?action=deleteItem&id=${ entry.value.id }">删除</a></td>
						</tr>	
					</c:forEach>	
				</c:otherwise>
			</c:choose>
		</table>
		<c:if test="${ not empty sessionScope.cart.items }">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${ sessionScope.cart.totalCount }</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${ sessionScope.cart.toatlPrice }</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>