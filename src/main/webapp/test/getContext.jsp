<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%=pageContext.getAttribute("name")%>

<%=request.getAttribute("req")%>

<%=session.getAttribute("sess")%>

<%=application.getAttribute("global")%>