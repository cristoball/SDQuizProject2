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
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//$("#results").html('jQuery is here');
		$.ajax({
			//url: "http://localhost:8686/wordsapi/count",
			url: "questions.html?quiz",
			data: "",
			type: "GET",
			dataType: "json",
			success: function(json) {
				console.log("success ajax!");
				console.log(json);
				//$("#correct").html(text);		          
			},
			error: function(xmlhttp, status, json) {
				console.error("ajax fail");
				console.error(xmlhttp);
				console.error(status);
				console.error(json);
			}
		
		}) //ajax
		
	}) //document.ready

</script>
<body>
<!-- <div align="center"> -->
	<h1>Question # ${counter + 1} of ${quiz.numberOfQuestions }</h1>
	
	<form id="quizQuestion" method="POST" onsubmit="return true;">
	
	<input type="hidden" name="currentCounter" value="${counter}" />
		<ul>
		<li><!-- (Q.ID = ${question.id}) --> <strong>${question.value }</strong></li>
		<li>&nbsp;</li>
		<c:forEach var="a" items="${question.answers}" varStatus="loop">
			<li><input type="radio" name="ans" value="${a.value}" <c:if test="${loop.index == 0}">autofocus</c:if> tabindex="${loop.index}" /> ${a.value}</li>
			<br />
		</c:forEach>
		 
		<li><input class="btnSubmit" type="submit" value="Submit Answer" /></li>
		<li>&nbsp;</li> 
		<li>Progress: <progress title="Quiz Progress" value="${(counter + 1) / quiz.numberOfQuestions }"></progress></li>
		</ul>
	</form>
<!-- </div> -->
<jsp:include page="debuginfo.jsp"/>
</body>
</html>
