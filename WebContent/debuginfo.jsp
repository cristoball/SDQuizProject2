<%@page import="java.util.*"%>
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
<br/>
<br/>
<br/>
<br/>
<br/>
<hr/>
<h2>Request Information</h2>
<table border="1">
	<tr>
		<th>Property</th>
		<th>Value</th>
	</tr>
	<tr>
		<td>method</td>
		<td>${pageContext.request.method}</td>
	</tr>
	<tr>
		<td>localName</td>
		<td>${pageContext.request.localName}</td>
	</tr>
	<tr>
		<td>localPort</td>
		<td>${pageContext.request.localPort}</td>
	</tr>
	<tr>
		<td>contextPath</td>
		<td>${pageContext.request.contextPath}</td>
	</tr>
	<tr>
		<td>servletPath</td>
		<td>${pageContext.request.servletPath}</td>
	</tr>
	<tr>
		<td>queryString</td>
		<td>${pageContext.request.queryString}</td>
	</tr>
	<tr>
		<td>requestURL</td>
		<td>${pageContext.request.requestURL}</td>
	</tr>
	<tr>
		<td>requestURI</td>
		<td>${pageContext.request.requestURI}</td>
	</tr>
	<tr>
		<td>remoteHost</td>
		<td>${pageContext.request.remoteHost}</td>
	</tr>
	<tr>
		<td>remotePort</td>
		<td>${pageContext.request.remotePort}</td>
	</tr>
</table>
<hr />

<h2>Request Headers</h2>
<table border="1">
	<tr>
		<th>Property</th>
		<th>Value</th>
	</tr>
	<tr>
		<td>host</td>
		<td>${header["host"]}</td>
	</tr>
	<tr>
		<td>user-agent</td>
		<td>${header["user-agent"]}</td>
	</tr>
	<tr>
		<td>accept</td>
		<td>${header["accept"]}</td>
	</tr>
	<tr>
		<td>accept-language</td>
		<td>${header["accept-language"]}</td>
	</tr>
	<tr>
		<td>accept-encoding</td>
		<td>${header["accept-encoding"]}</td>
	</tr>
</table>
<hr />
<h2>Response Information</h2>
<table border="1">
	<tr>
		<th>Property</th>
		<th>Value</th>
	</tr>
	<tr>
		<td>bufferSize</td>
		<td>${pageContext.response.bufferSize}</td>
	</tr>
	<tr>
		<td>characterEncoding</td>
		<td>${pageContext.response.characterEncoding}</td>
	</tr>
	<tr>
		<td>contentType</td>
		<td>${pageContext.response.contentType}</td>
	</tr>
	<tr>
		<td>locale</td>
		<td>${pageContext.response.locale}</td>
	</tr>
</table>
<hr>

<h2>Servlet Context Information</h2>
<table border="1">
	<tr>
		<th>Property</th>
		<th>Value</th>
	</tr>
	<tr>
		<td>majorVersion</td>
		<td>${pageContext.servletContext.majorVersion}</td>
	</tr>
	<tr>
		<td>minorVersion</td>
		<td>${pageContext.servletContext.minorVersion}</td>
	</tr>
	<tr>
		<td>serverInfo</td>
		<td>${pageContext.servletContext.serverInfo}</td>
	</tr>
</table>
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
		<td>${pageContext.session.maxInactiveInterval}seconds</td>
	</tr>
	<tr>
		<td>Servlet Context</td>
		<td>${pageContext.session.servletContext}</td>
	</tr>
	<tr>
		<td>Session Context</td>
		<td>${pageContext.session.sessionContext}</td>
	</tr>
	<tr>
		<td>User</td>
		<td>${user}</td>
	</tr>
	<tr>
		<td>Quiz</td>
		<td>${quiz}</td>
	</tr>
</table>
<hr />
<h2>Cookies</h2>
<c:forEach var="cookies" items="${cookie}">

    <strong><c:out value="${cookies.key}"/></strong>: Object=<c:out value="${cookies.value}"/>, value=<c:out value="${cookies.value.value}"/><br />
        
</c:forEach>
<hr/>


<%
	Cookie[] cookies = request.getCookies();
	if (cookies == null)
		cookies = new Cookie[0];
	Date thisVisit = new Date();
	// add data cookie to session
	Cookie c = new Cookie("lastVisit", "" + thisVisit);
	response.addCookie(c);
	// add cookie to session
	String name = request.getParameter("name");
	String value = request.getParameter("value");
	Cookie added = null;
	if (name != null && value != null && name.length() > 0)
	{
		added = new Cookie(name, value);
		response.addCookie(added);
	}
%>
<h2>Cookie Information</h2>
    <b>Cookie List</b>
    This visit: <%= thisVisit %><BR>
    Number of cookies: <%= cookies.length %><BR>
    <H2>Cookies</H2>
    <%
    for (int i=0; i<cookies.length; i++) {
      out.println(cookies[i].getName()+":\t"+
        cookies[i].getValue()+"<BR>");
      // check if added cookie already present
      if (added!=null && added.getName().equals(cookies[i].getName()))
        added = null;
    }
    if (added != null)
      out.println("new cookie: "+added.getName()+":\t"+
        added.getValue()+"<BR>");
    %>
<hr />

</html>