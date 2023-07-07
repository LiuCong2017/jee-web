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
		Cookie[] cookies = request.getCookies();
		if(null != cookies){
			for(int d=0;d<=cookies.length;d++){
				out.print(cookies[d].getName()+":"+cookies[d].getValue()+"<br>");
			}
		}
	%>
</body>
</html>