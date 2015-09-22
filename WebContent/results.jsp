<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css">
<title>Quiz Results: ${quiz.quizName}</title>
</head>
<body>
	<h1>Quiz Results: ${quiz.quizName}</h1>
	<c:forEach var="ques" items="${quiz.questions}">
		<form id="formQuestion${ques.id}" method="get" action="" onsubmit="return false;">
		<b>Question ${ques.id}: ${ques.value}</b>
		<br />
		<c:forEach var="answers" items="${ques.answers}">
			<input type="radio" name="ans" disabled="disabled" value="${answers.value}"/> ${answers.value}
			<br />
		</c:forEach>
		<i>You answered: ${ques.givenAnswer}</i>
		<br />
		<b>Correct Answer: ${ques.correctAnswer.value}</b>
		</form>
		<br/>
		<br/>
	</c:forEach>
	<br/>
	Total Questions: ${quiz.numberOfQuestions}<br/>
	Total Correct Answers: ${TotalCorrect}<br/>
	Percent correct: <fmt:formatNumber value="${TotalCorrect / quiz.numberOfQuestions}" type="percent" /><br/>


<jsp:include page="debuginfo.jsp"/>
</body>
</html>