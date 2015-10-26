<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css">
<title>Quiz Results: ${quiz.name}</title>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		
	}) //document.ready

</script>
</head>
<body>
	<h1>Quiz Results: ${quiz.name}</h1>
	<form>
	<fieldset>
	<legend>Total Quiz Results</legend>
	Total Questions: ${quiz.numberOfQuestions}<br/>
	Total Correct Answers: ${TotalCorrect }<br/>
	Percent correct: <fmt:formatNumber value="${TotalCorrect / quiz.numberOfQuestions}" type="percent" /><br/>
	Questions Skipped: ${TotalSkipped}<br/>
	</fieldset>
	</form>	
	
	<c:forEach var="ques" items="${quiz.questions}">
		<form id="formQuestion${ques.id}" method="get" action="" onsubmit="return false;">
		<b>Question ${ques.id}: ${ques.value}</b>
		<br />
		<c:forEach var="answers" items="${ques.answers}" varStatus="loop">
		
			<input type="radio" name="ans" disabled="disabled" value="${answers.value}" <c:if test="${answers.value == ques.givenAnswer}">checked='checked'</c:if> /> ${answers.value} 
			<br />
		</c:forEach>
		You answered: 
		<c:choose>
			<c:when test="${ques.correctAnswer.value == ques.givenAnswer}"><span id="correct">${ques.givenAnswer} &#10004;</span></c:when>
			<c:otherwise><span id="incorrect">${ques.givenAnswer} &#x2718;</span></c:otherwise>
		</c:choose>
		<br />
		<em>Correct Answer: ${ques.correctAnswer.value}</em>
		</form>
		<br/>
		<br/>
	</c:forEach>
	<br/>


</body>
</html>