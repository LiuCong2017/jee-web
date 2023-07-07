<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%pageContext.setAttribute("name","pageName");%>
<%=pageContext.getAttribute("name") %>

<%request.setAttribute("req","req_scope");%>
<%=request.getAttribute("req")%>
<jsp:forward page="getContext.jsp"/>

<%
    session.setAttribute("sess","gareen");
    response.sendRedirect("getContext.jsp");
%>

<%
    application.setAttribute("global","gareen");
    response.sendRedirect("getContext.jsp");
%>

page:<%=page%>
<br>
this:<%=this%>