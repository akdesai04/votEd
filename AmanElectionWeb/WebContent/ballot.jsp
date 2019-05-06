<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>vote</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<h3>Please click to vote for one of:</h3>
<form method = "post" action="${pageContext.request.contextPath}/vote">
<c:forEach items="${applicationScope.candidates}" var="candidate">
<input type="submit" name="${ candidate.cid }" value="${ candidate.name }"> &nbsp;
</c:forEach>
</form>
</body>
</html>