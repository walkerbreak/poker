<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
</head>
<body>
    <h1>ログイン画面</h1>
        <form action="LoginServlet" method="post">
            <table>
                <tr>
                    <td class="title">ユーザー名</td>
                    <td><input type="text" name="name" maxlength="20"></td>
                </tr>
                <tr>
                    <td class="title">パスワード</td>
                    <td><input type="password" name="password" maxlength="15"></td>
                </tr>
            </table>
            <p>
                <button name="transition" value="login_process">ログイン</button>
            </p>
            <p>
                <button name="transition" value="adduser">新規登録</button>
            </p>
        </form>
</body>
</html>