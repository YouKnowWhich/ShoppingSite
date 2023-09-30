<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン画面(login.jsp)</title>
		<link href="./css/common.css" rel="stylesheet" type="text/css" />
		<link href="./css/login.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="contents">
			<h2>セラクショッピングサイト</h2>
			<p class="login_message">ログインIDとパスワードを入力して下さい</p>
			<form action="/ShoppingSite/LoginServlet" method="post">
				<input type="text" name="userId" placeholder="ログインID">
				<input type="password" name="password" placeholder="パスワード">
				<input class="common_button" type="submit" value="ログイン">
			</form>
		</div>
	</body>
</html>
