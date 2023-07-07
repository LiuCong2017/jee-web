<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.servlet.http.Cookie" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		Cookie c = new Cookie("name","Cody");
		c.setMaxAge(24*60*60);
		c.setPath("/");
		response.addCookie(c);
	%>

	<a href="getCookie.jsp">跳转到获取coolie的页面</a>
</body>
</html>