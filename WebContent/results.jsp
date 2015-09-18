<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="quiz.data.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quiz Results: ${quiz.quizName}</title>
</head>
<body>
	<h1>Quiz Results: ${quiz.quizName}</h1>
	<c:forEach var="ques" items="${quiz.questions}">
		<b>Question ${ques.id}: ${ques.value}</b>
		<br />
		<c:forEach var="answers" items="${ques.answers}">
			<input type="radio" name="ans" disabled="disabled" value="${answers.value}"/> ${answers.value}
			<br />
		</c:forEach>
		<i>You answered: ${ques.givenAnswer}</i>
		<br />
		<b>Correct Answer: ${ques.correctAnswer.value}</b>
		<br/>
		<br/>
	</c:forEach>
	<br/>
	Total Questions: ${TotalQuestions}<br/>
	Total Correct Answers: ${TotalCorrect}<br/>
	Percent correct: <fmt:formatNumber value="${TotalCorrect / TotalQuestions}" type="percent" /><br/>


<jsp:include page="debuginfo.jsp"/>
</body>
</html>