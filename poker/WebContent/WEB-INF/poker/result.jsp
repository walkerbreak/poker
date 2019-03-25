<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="trump.Trump" import="user.UserBean" %>
<%
Trump hand0=(Trump)session.getAttribute("hand0");
Trump hand1=(Trump)session.getAttribute("hand1");
Trump hand2=(Trump)session.getAttribute("hand2");
Trump hand3=(Trump)session.getAttribute("hand3");
Trump hand4=(Trump)session.getAttribute("hand4");
String judge=(String)session.getAttribute("judge");
int get=Integer.valueOf((Integer)session.getAttribute("get"));
int resultcoin=Integer.valueOf((Integer)session.getAttribute("resultcoin"));
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Poker</title>
</head>
<body>
<h1>ポーカー：リザルト画面</h1>
<p>
<%=hand0.getSuit() %><%=hand0.getNum() %>
<%=hand1.getSuit() %><%=hand1.getNum() %>
<%=hand2.getSuit() %><%=hand2.getNum() %>
<%=hand3.getSuit() %><%=hand3.getNum() %>
<%=hand4.getSuit() %><%=hand4.getNum() %>
</p>

<p>成立した役は......<%=judge %><br>
獲得枚数<%=get %><br>
合計枚数<%=resultcoin %>


</p>

<p>
<form method="post">
	<button name="transition" value="bet" formaction="PokerServlet">もう一度</button>
	<button name="transition" value="menu" formaction="LoginServlet">メニュー画面へ</button>
</form>
</body>
</html>