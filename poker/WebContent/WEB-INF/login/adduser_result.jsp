<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Boolean adduser=(Boolean)session.getAttribute("adduser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
</head>
<body>
<h1>新規登録結果画面</h1>
<p>
<%if(adduser){ %>
登録完了
<%}else {%>
登録失敗<br>
ユーザー名がすでに存在しています
<%} %>
<form method="post">
	<button name="transition" formaction="Loginform">ログイン画面へ</button>
	<button name="transition" value="adduser" formaction="LoginServlet">新規登録画面へ</button>
</form>
</body>
</html>