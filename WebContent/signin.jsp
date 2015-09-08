<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
</body>
</html>
