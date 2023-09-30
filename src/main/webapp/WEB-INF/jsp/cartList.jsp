<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.ShoppingCartBean"%>
<%@ page import="model.CartItemBean"%>
<%@ page import="servlet.CartOperationServlet"%>
<%@ page import="java.util.Iterator"%>
<%
	ShoppingCartBean cartBean = (ShoppingCartBean)session.getAttribute("CartBean");
	Iterator<CartItemBean> cartItemBeanIter = cartBean.getCartItemBeanList();
%>
    
<!DOCTYPE html>
<html>
		<head>
		<title>カート内商品一覧</title>
		<link href="./css/shopping.css" rel="stylesheet" type="text/css">
	    <link href="./css/common.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h2 class="cart_title">ショッピングカート</h2>
		<form action="CartOperationServlet" method="post">
			<table class="table_list">
				<thead>
					<tr>
						<th class="goods_id">商品ID</th>
						<th class="goods_name">商品名</th>
						<th class="goods_price">単価</th>
						<th class="goods_volume">数量</th>
						<th class="goods_amount">金額</th>
						<th class="goods_button"></th>
					</tr>
				</thead>
					<%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
					<% for (int i = 0;cartItemBeanIter.hasNext();i++) { %>
					    <% CartItemBean cartItemBean = (CartItemBean)cartItemBeanIter.next(); %>
					<tr>
						<%-- 商品ID --%>
						<td><%=cartItemBean.getItemId()  %></td>
						<%-- 商品名 --%>
						<td><%=cartItemBean.getItemName() %></td>
						<%-- 数量 --%>
						<td class="int"><%=cartItemBean.getPrice() %></td>
						<%-- 価格 --%>
						<td class="int"><%=cartItemBean.getCount() %></td>
						<%-- 金額 --%>
						<td class="int"><%=cartItemBean.getAmount() %></td>
						<td class="button">
							<input class="common_button" type="submit" name="<%=i %>" value="<%=CartOperationServlet.REMOVE_BUTTON_NAME %>">
						</td>
					</tr>
					<% } %>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td class="int">
				 			<%=String.format("%,d個の商品",cartBean.getTotalCount()) %>
						</td>
						<td  class="int">
							<%=String.format("小計:%,d円",cartBean.getTotalAmount())%>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="button">
							<input class="common_button" type="submit" name="SHOPPING" 
							        value="<%=CartOperationServlet.GOTO_SHOPPING %>" >
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td class="button">
							<% if (cartBean.getTotalCount() != 0) { %>
							<input class="common_button payment" type="submit" 
							        name="PAYMENT" value="<%=CartOperationServlet.GOTO_PAYMENT %>" >
							<% } else { %>
							<input class="common_button disabled" type="submit" 
							        name="PAYMENT" value="<%=CartOperationServlet.GOTO_PAYMENT %>" disabled >
							<% } %> 
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>