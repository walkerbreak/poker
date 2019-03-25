<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
</head>
<body>
<h1>新規登録画面</h1>
<p>
ユーザー名とパスワードを入力してください
</p>
	<form action="LoginServlet" method="post">
		<table>
			<tr>
				<td class="title">ユーザー名</td>
				<td><input type="text" name="name" maxlength="20"required>※必須</td>
			</tr>
			<tr>
				<td class="title">パスワード</td>
				<td><input type="password" name="password" maxlength="15"required>※必須</td>
			</tr>
		</table>
		<p>
		<button name="transition" value="adduser_process">登録</button>
		</p>
	</form>
	<form method="post">
	<button name="transition" formaction="Loginform">ログイン画面へ</button>
</form>
</body>
</html>