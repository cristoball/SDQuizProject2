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
		//$("#results").html('jQuery is here');
		$.ajax({
			//url: "http://localhost:8686/wordsapi/count",
			url: "questions.html?correct",
			data: "",
			type: "GET",
			dataType: "text",
			success: function(text) {
				console.log("success ajax!");
				console.log("correct=" + text);
				$("#correct").html(text);		          
			},
			error: function(xmlhttp, status) {
				console.error("ajax fail");
				console.error(xmlhttp);
				console.error(status);
			}
		
		}) //ajax
		
	}) //document.ready

</script>
</head>
<body>
	<h1>Quiz Results: ${quiz.name}</h1>
	<form>
	<fieldset>
	<legend>Total Quiz Results</legend>
	Total Questions: ${quiz.numberOfQuestions}<br/>
	Total Correct Answers: <span id="correct"></span><br/>
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
		<i>You answered: ${ques.givenAnswer}</i>
		<br />
		<b>Correct Answer: ${ques.correctAnswer.value}</b>
		</form>
		<br/>
		<br/>
	</c:forEach>
	<br/>


<jsp:include page="debuginfo.jsp"/>
</body>
</html>