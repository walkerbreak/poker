<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="trump.Trump" %>
<%
Trump hand0=(Trump)session.getAttribute("hand0");
Trump hand1=(Trump)session.getAttribute("hand1");
Trump hand2=(Trump)session.getAttribute("hand2");
Trump hand3=(Trump)session.getAttribute("hand3");
Trump hand4=(Trump)session.getAttribute("hand4");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
</head>

<body>
<h1>ポーカー：チェンジ画面</h1>
<%=hand0.getSuit() %><%=hand0.getNum() %>
<%=hand1.getSuit() %><%=hand1.getNum() %>
<%=hand2.getSuit() %><%=hand2.getNum() %>
<%=hand3.getSuit() %><%=hand3.getNum() %>
<%=hand4.getSuit() %><%=hand4.getNum() %>
<form method="post" >
	<p>
	<input type="checkbox" name="keep0" value="1">keep
	<input type="checkbox" name="keep1" value="1">keep
	<input type="checkbox" name="keep2" value="1">keep
	<input type="checkbox" name="keep3" value="1">keep
	<input type="checkbox" name="keep4" value="1">keep
	</p>
	changeボタンをクリックすると手札の交換を行います<br>
	キープしたい手札はチェックボックスにチェックを入れてください
	<p>
	<button name="transition" value="result" formaction="PokerServlet">change</button>
</form>
</body>
</html>