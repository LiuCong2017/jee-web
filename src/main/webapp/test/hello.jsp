<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	测试JSP<%=new Date().toLocaleString()%>
	<%
    List<String> words = new ArrayList<String>();
    words.add("today");
    words.add("is");
    words.add("a");
    words.add("great");
    words.add("day");
	%>
	
	<table width="200px" align="center" border="1" cellspacing="0">
		<%for (String word : words) {%>
		<tr>
		    <td><%=word%></td>
		</tr>
		<%}%>
	</table>
</body>
<%-- <%@include file="footer.jsp" %> --%>
<jsp:include page="footer.jsp">
	<jsp:param name="year" value="2017"/>
</jsp:include>
</html>