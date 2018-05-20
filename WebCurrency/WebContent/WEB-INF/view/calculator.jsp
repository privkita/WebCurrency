<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebCurrency</title>
<link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
	<div class="container">
		<div class="header">
			<h1 class="header-heading">KALKULATOR WALUTOWY</h1>
		</div>
		<div class="nav-bar">
			<ul class="nav">
				<li><a href="/WebCurrency/calculator">KALKULATOR</a></li>
			</ul>
		</div>
		<div class="content">
		<form method="post">
			<table class="table table-striped">
			<tr>
				<td>
				<input type="text" name="setAmount" size="10">
				</td>
				<td>
				<select name="setCurrencyFrom">
					<option value="">----------------</option>
					<c:forEach var="currency" items="${currencies }">
						<option value="${currency.currencyCode }">${currency.currencyName }</option>
					</c:forEach>
				</select>
				</td>
				<td>
					<p> >> </p>
				</td>
				<td>
				<select name="setCurrencyTo">
					<option value="">----------------</option>
					<c:forEach var="currency" items="${currencies }">
						<option value="${currency.currencyCode }">${currency.currencyName }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			</table>
			<input type="submit" value="Oblicz" class="btn">
		</form>
		<br>
		<h4>${amount }</h4>
		</div>
		<div class="footer">
			&copy; Copyright 2018
		</div>
	</div>
</body>
</html>