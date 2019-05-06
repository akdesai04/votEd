<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>welcome page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<h3>Welcome to the online election web site.</h3>
<p>To vote, enter your name and voter ID (password)). Then click <b>Sign In</b>.</p>
<form method="post" action="${pageContext.request.contextPath}/signin">
<font color="red">${requestScope.message}</font>
<table border="0">
	<tbody>
		<tr>
			<td>Voter ID </td>
			<td><input type="text" name="voterId" value="${requestScope.voterId}" size="3"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" size="12"></td>
		</tr>
	</tbody>
</table>
<input type="submit" name="Sign In" value="Sign in">
</form>
</body>
</html>