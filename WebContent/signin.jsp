<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style.css">
<title>Please login to continue</title>
</head>
<body>
<h1>Please login to continue</h1>
<form name="login" method="post" action="login.html">
	<font color="red">${error}</font><br/>
	Username: <input name="username" type="text" value="test@test.net" size="30"/><br/>
	Password: <input name="password" type="password" value="test" size="20"/><br/>
	<input type="submit" value="Login"/>
	<br/>
</form>
<br/>
<b>*Guests use:</b><br/>
username: test@test.net<br/>
password: test<br/>
<br/>
<jsp:include page="debuginfo.jsp"/>
</body>
</html>
