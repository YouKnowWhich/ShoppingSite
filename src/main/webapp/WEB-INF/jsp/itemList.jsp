<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.ItemBean"%>
<%@ page import="servlet.CartOperationServlet"%>
<%@ page import="java.util.Iterator"%>


<%
	ArrayList<ItemBean> itemList = (ArrayList<ItemBean>)session.getAttribute("ItemList");
	Iterator<ItemBean> itemListIterator =  itemList.iterator();
%>
    
<!DOCTYPE html>
<html>
	<head>
		<title>商品一覧</title>
		<link href="./css/shopping.css" rel="stylesheet" type="text/css">
	    <link href="./css/common.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<form action="CartOperationServlet" method="post">
			<table class="table_list">
				<thead>
					<tr>
						<th class="goods_id">商品ID</th>
						<th class="goods_name">商品名</th>
						<th class="goods_price">価格</th>
						<th class="goods_volume">数量</th>
						<th class="goods_button"></th>
					</tr>
				</thead>
					
					<%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
					<% while(itemListIterator.hasNext()) { %>
						<% ItemBean itemBean = (ItemBean)itemListIterator.next(); %>
					<tr>
						<%-- 商品ID --%>
						<td><%=itemBean.getItemId()  %></td>
						<%-- 商品名 --%>
						<td><%=itemBean.getItemName() %></td>
						<%-- 価格 --%>
						<td class="int"><%=itemBean.getPrice() %></td>
					
						<%-- 購入数量 --%>
						<td><input class="textbox" name="<%=itemBean.getItemId()%>counter" type="number" value = "1"></td>
						<td class="button">
							<input class="common_button" type="submit" name="<%=itemBean.getItemId()%>" value="<%=CartOperationServlet.ADD_BUTTON_NAME %>">
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</form>
	</body>
</html>