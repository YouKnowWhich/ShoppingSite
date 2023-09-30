<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.UserBean"%>
<%@ page import="model.ShoppingCartBean"%>
<%
    UserBean userBean = null;
    ShoppingCartBean cartBean = null;
	if (session != null) {
		userBean = (UserBean)session.getAttribute("LoginUser");
		cartBean = (ShoppingCartBean)session.getAttribute("CartBean");
		if (userBean == null || cartBean == null) {
			application.getRequestDispatcher("WEB-INF/jsp/logout.jsp").forward(request, response);
		}
	} else {
		application.getRequestDispatcher("WEB-INF/jsp/logout.jsp").forward(request, response);
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン情報ヘッダ表示(header.jsp)</title>
	</head>
	<body>
		<div id="head">
			<span class="welcom_message">
				ようこそ「<%= userBean.getUserName() %>」さん！
			</span>
			<a id="cart" class="icon_layers" href="http://localhost:8080/ShoppingSite/CartOperationServlet" >
				<img src="img/cart.png" >
				<i class="far fa-envelope icon_layers_icon"></i>
				<% if (cartBean.isPaymentCompleted()) { %>
					<span class="icon_layers_counter">0</span>
				<% } else { %>
					<span class="icon_layers_counter">
						<%
						int cartCount = cartBean.getTotalCount();
						if (cartCount >= 11) {
							out.print("10+");
						} else {
							out.print(cartCount);
						}
						%>
					</span>
				<% } %>
			</a>
			<a id="logout" href="http://localhost:8080/ShoppingSite/LogoutServlet">
				<img src="img/logout_white_24dp.svg">LOGOUT
			</a>
		</div>
	</body>
</html>
