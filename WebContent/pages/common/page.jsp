<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
		<%-- 分页条的开始 --%>
		<div id="page_nav">
			<%-- 如果当前页码是第一页，那么首页，上一页，没有出现的必要。 --%>
			<c:if test="${ requestScope.page.pageNo != 1 }">
				<a href="${ requestScope.page.url }&pageNo=1" >首页</a>
				<a href="${ requestScope.page.url }&pageNo=${ requestScope.page.pageNo-1 }">上一页</a>
			</c:if>
			<c:if test="${  requestScope.page.pageNo == 1  }">
				首页 上一页
			</c:if>
			
			<%-- 这里是页码输出的开始 --%>	
			<c:choose>
				<%-- 	情况一：假设  总页码数 小于等于5的情况			页码的范围是： 1 到 总页码 --%>
				<c:when test="${ requestScope.page.pageTotal <= 5 }">
					<c:set value="1" var="begin" />
					<c:set value="${ requestScope.page.pageTotal }" var="end" />
				</c:when>
				<%-- 	情况二：假设  总页码数大于5 --%>
				<c:otherwise>
					<c:choose>
						<%-- 情况一：	当前页码为前面三个   1,2,3			页码范围是		1到5, 固定 --%>
						<c:when test="${ requestScope.page.pageNo <= 3 }">
							<c:set var="begin" value="1" />
							<c:set var="end" value="5" />							
						</c:when>
						<%-- 情况二：	当前页码为最后三个	8,9,10			页码范围是   	总页码-4	到 总页码 --%>
						<c:when test="${ requestScope.page.pageNo >= requestScope.page.pageTotal-2 }">
							<c:set var="begin" value="${ requestScope.page.pageTotal-4 }" />
							<c:set var="end" value="${requestScope.page.pageTotal}" />	
						</c:when>
						<%-- 情况三：	中间的页码    4,5,6,7		页码范围是：	当前页码-2 到 当前页码+2 --%>
						<c:otherwise>
							<c:set var="begin" value="${ requestScope.page.pageNo-2 }" />
							<c:set var="end" value="${requestScope.page.pageNo+2}" />	
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${ begin }" end="${ end }" var="i">
				<c:if test="${ i == requestScope.page.pageNo }">
					【${ i }】
				</c:if>
				<c:if test="${ i != requestScope.page.pageNo }">
					<a href="${ requestScope.page.url }&pageNo=${ i }">${ i }</a>
				</c:if>
			</c:forEach>				
			<%-- 这里是页码输出的结束 --%>	
			
			
			
			<%-- 如果当前页码是最后一页，那么末页，下一页，没有出现的必要。 --%>
			<c:if test="${ requestScope.page.pageNo != requestScope.page.pageTotal }">
				<a href="${ requestScope.page.url }&pageNo=${ requestScope.page.pageNo+1 }">下一页</a>
				<a href="${ requestScope.page.url }&pageNo=${ requestScope.page.pageTotal }">末页</a>
			</c:if>
			共${  requestScope.page.pageTotal  }页，${  requestScope.page.pageTotalCount  }条记录 
			到第<input value="${ requestScope.page.pageNo }" name="pn" id="pn_input"/>页
			<input id="search_page" type="button" value="确定">
		</div>

		<script type="text/javascript">
			$(function(){
				$("#search_page").click(function(){
					// 获取输入框中的内容
					var pageNo = $("#pn_input").val();
// 					在js中提供了一个对象。location。它有一个属性是href.这个属性可读可写。
//					它可以设置和获取浏览器地址栏的地址信息。
// 					alert( location.href );
					location.href="${path}${ requestScope.page.url }&pageNo=" + pageNo;
				});
			});
		</script>
		
		<%-- 分页条的结束 --%>