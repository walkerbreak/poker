<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Poker</title>
</head>

<body>
<h1>ポーカー：配当表画面</h1>
<p>
<table>
  <caption>配当表</caption>
  <tr>
    <th>役</th>
    <th>配当</th>
    <th>役</th>
    <th>配当</th>
  </tr>
  <tr>
    <td>ワンペア</td>
    <td>×１</td>
    <td>ツーペア</td>
    <td>×２</td>
  </tr>
  <tr>
    <td>スリーカード</td>
    <td>×３</td>
    <td>フラッシュ</td>
    <td>×６</td>
  </tr>
  <tr>
    <td>フルハウス</td>
    <td>×８</td>
    <td>ストレート</td>
    <td>×１０</td>
  </tr>
  <tr>
    <td>フォーカード</td>
    <td>×３０</td>
    <td>ストレートフラッシュ</td>
    <td>×５０</td>
  </tr>
</table>

<p>
<form method="post">
	<button name="transition" value="menu" formaction="LoginServlet">メニュー画面へ</button>
</form>
</body>
</html>