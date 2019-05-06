<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
<title>errorPage</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<h1>Election error</h1>
<p>The problem: <font color="red">${requestScope.message}</font></p>
<p>Please <a href="${pageContext.request.contextPath}"><b>try again</b></a>.</p>
</body>
</html>