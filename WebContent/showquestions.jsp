<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <% Question question = (Question) request.getAttribute("question"); %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quiz Questions</title>
</head>
<body>
	<h1>Question # ${question.id }</h1>
	<b>Question: ${question.value }</b>
	<br />
	<br />
	<form method="POST">
		<c:forEach var="a" items="${question.answers}">
			<input type="radio" name="ans" value="${a.value}"> ${a.value}</input>
			<br />
		</c:forEach>
		<input type="hidden" name="quesID" value="${question.id }" /> <br />
		<input type="submit" value="Submit Answer" />
	</form>

	
</body>
</html>
