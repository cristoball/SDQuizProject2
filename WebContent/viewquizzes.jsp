<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css">
<title>Quizzes</title>
</head>
<body>
	<h1>Available Quizzes</h1>
	<table border="1"  >
		<tr align="center">
			<td>ID</td>
			<td>Quiz Name</td>
			<td># of Questions</td>
			<td></td>
		</tr>
		<tr align="center">
			<td>${quiz.quizID}</td>
			<td>${quiz.quizName}</td>
			<td>${quiz.numberOfQuestions}</td>
			<td><a href="questions.html?startQuiz=1">Start!</a></td>
		</tr>
	</table>

<jsp:include page="debuginfo.jsp"/>
</body>
</html>