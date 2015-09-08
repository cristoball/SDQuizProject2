<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	Date date;
	date = new Date(session.getCreationTime());
	String creationDate = date.toString();
	date = new Date(session.getLastAccessedTime());
	String lastAccessedDate = date.toString();
	
%>

<br/>
	<hr />
	<h2>Session Info</h2>
	<table border="1">
		<tr>
			<td>Creation Time</td>

			<td><%=creationDate%></td>
		</tr>
		<tr>
			<td>ID</td>
			<td>${pageContext.session.id}</td>
		</tr>
		<tr>
			<td>Last Accessed</td>
			<td><%=lastAccessedDate%></td>
		</tr>
		<tr>
			<td>Max Inactive Interval</td>
			<td>${pageContext.session.maxInactiveInterval} seconds</td>
		</tr>
		<% 
		Enumeration<String> sessEnum = session.getAttributeNames(); 
		int i = 0;
		while (sessEnum.hasMoreElements())
		{
			i++;
		}
		%>
		<tr><td colspan="2" align="center" >Session Attr Size: <%= i %></td></tr>

		
		<tr>
			<td>Attributes Name</td>
			<td></td>
		</tr>
		
		<tr>
			<td>Values</td>
			<td>${pageContext.session.valueNames}</td>
		</tr>
		<tr>
			<td>Servlet Context</td>
			<td>${pageContext.session.servletContext}</td>
		</tr>
		<tr>
			<td>Session Context</td>
			<td>${pageContext.session.sessionContext}</td>
		</tr>

	</table>

</html>