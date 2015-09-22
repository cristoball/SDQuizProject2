<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css">
<title>Quiz Questions</title>
</head>
<body>
	<h1>Question # ${counter + 1}</h1>
	<b>(Q.ID = ${question.id}) Question: ${question.value }</b>
	<br />
	<br />
	<form method="POST">
		<c:forEach var="a" items="${question.answers}">
			<input type="radio" name="ans" value="${a.value}"/> ${a.value}
			<br />
		</c:forEach>
		<input type="hidden" name="currentCounter" value="${counter}" /> <br />
		<input type="submit" value="Submit Answer" />
	</form>

<jsp:include page="debuginfo.jsp"/>
</body>
</html>
