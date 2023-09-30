<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.UserBean"%>
<%
	String message = null;
	//ログインユーザ情報を取得（暗黙オブジェクトsession)
	UserBean userBean = (UserBean)session.getAttribute("LoginUser");
	if (userBean != null) { 
		message = userBean.getUserName() + "さんはログアウトしました。";
	} else {
		message = "すでにログアウトまたはセッションタイムアウトしています。";
	}
	session.invalidate();	//セッション削除
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="./css/common.css" rel="stylesheet" type="text/css">
		<link href="./css/logout.css" rel="stylesheet" type="text/css">
		<title>ログアウト(logout.jsp)</title>
	</head>
	<body>
		<div id="contents">
		<div class="logout_message"><%= message %></div>
 		<a class="common_button" href="http://localhost:8080/ShoppingSite/LoginServlet">ログイン画面に戻る</a>
 		</div>
	</body>
</html>