<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserBean"%>
<%
UserBean user=(UserBean)session.getAttribute("user");
String name=user.getName();
int coin=user.getCoin();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
</head>

<h1>メニュー画面</h1>
<body>
<p>
ようこそ<%=name %>さん<br>
coin<%=coin %>枚

<h2>セッションエラーへの遷移</h2>
<p>
ベット画面にてベットするコインの枚数を決定し、チェンジ画面へ遷移後、チェンジ画面もしくはリザルト画面にてブラウザバックを行い<br>
PokerServletへリクエストした場合、セッションエラー画面へ遷移(手札の引き直しを禁止)
</p>
<p>
チェンジ、リザルト、セッションエラー画面にてリロードを行うとセッションエラー画面へ遷移(手札の引き直しを禁止)
</p>
<p>
URLや履歴から直接PokerServletへリクエストした場合、ログインしていない場合はログインエラー画面へ<br>
ログインしていた場合はセッションエラー画面へ(不正操作禁止)
</p>

<p>
<form method="post">
	<button name="transition" value="bet" formaction="PokerServlet">ゲームをする</button>
	<button name="transition" value="payout" formaction="PokerServlet">配当表を見る</button>
	<button name="transition" formaction="Loginform">ログアウト</button>
</form>
</body>
</html>
