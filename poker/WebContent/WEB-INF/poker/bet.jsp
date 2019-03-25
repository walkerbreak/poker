<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int resultcoin=Integer.valueOf((Integer)session.getAttribute("resultcoin"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
</head>

<body>
<h1>ポーカー：ベット画面</h1>
<p>
賭けるコインの枚数を決定後、手札の配布を行います<br>
現在のcoinの枚数：<%=resultcoin %>
</p>


<p>
<form method="post">
	<select name="bet">
		<option value="1">1枚</option>
		<option value="2">2枚</option>
		<option value="3">3枚</option>
	</select>
	<button name="transition" value="change" formaction="PokerServlet">OK</button>
</form>
</body>
</html>