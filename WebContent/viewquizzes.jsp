<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="quiz.data.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%
	Quiz quiz = (Quiz) request.getAttribute("quiz");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quizzes</title>
</head>
<body>
	<h1>Quizzes</h1>
	<table border="1">
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Num Questions</td>
			<td></td>
		</tr>
		<tr>
			<td><%= quiz.getQuizID() %></td>
			<td><%= quiz.getQuizName() %></td>
			<td><%= quiz.getNumberOfQuestions() %></td>
			<td><a href="questions.html?startQuiz=1">Start!</a></td>
		</tr>
	</table>

	<jsp:include page="sessioninfo.jsp"></jsp:include>
</body>
</html>