<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Persons</h1>

<table>
	<tr>
		<td width="50">Id</td>
		<td width="150">First Name</td>
		<td width="150">Last Name</td>
		<td width="50">Money</td>
	</tr>
	<c:forEach items="${persons}" var="person">
		<tr>
			<td><c:out value="${person.id}" /></td>
			<td><c:out value="${person.firstName}" /></td>
			<td><c:out value="${person.lastName}" /></td>
			<td><c:out value="${person.money}" /></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>