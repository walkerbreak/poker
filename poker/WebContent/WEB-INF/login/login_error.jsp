<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/**
* URLや履歴等直接リクエストされた場合  servlet="doget"
*/
String servlet=(String)session.getAttribute("servlet");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
</head>
<body>
<h1>ログインエラー画面</h1>
<%if(servlet.equals("doget")){ %>
不正な操作です<br>
ログイン画面へ遷移します
<%}else{%>
データベース内にユーザーが存在しないか、ユーザー名とパスワードが一致しません<br>
ログイン画面へ遷移します
<%} %>
<p>
<form method="post">
	<button name="transition" formaction="Loginform">ログイン画面へ</button>
</form>
</body>
</html>