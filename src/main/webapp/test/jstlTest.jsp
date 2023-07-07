<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<c:set var="name" value="${'gareen'}" scope="request" />
 
通过标签获取name: <c:out value="${name}" /> <br>
 
<c:remove var="name" scope="request" /> <br>
 
通过标签获取name: <c:out value="${name}" /> <br>
通过 EL 获取name: ${name}
<c:set var="hp" value="${10}" scope="request" />
 
<c:if test="${hp<5}">
    <p>这个英雄要挂了</p>
</c:if>
 
<c:if test="${!(hp<5)}">
    <p>这个英雄觉得自己还可以再抢救抢救</p>
</c:if>
 
<%
    pageContext.setAttribute("weapon", null);
    pageContext.setAttribute("lastwords", "");
    pageContext.setAttribute("items", new ArrayList());
%>
 
<c:if test="${empty weapon}">
    <p>没有装备武器</p>
</c:if>
<c:if test="${empty lastwords}">
    <p>挂了也没有遗言</p>
</c:if>
<c:if test="${empty items}">
    <p>物品栏为空</p>
</c:if>

<c:set var="name" value="${'gareen-pageContext'}" scope="page" />
<c:set var="name" value="${'gareen-request'}" scope="request" />
<c:set var="name" value="${'gareen-session'}" scope="session" />
<c:set var="name" value="${'gareen-application'}" scope="application" />

<%
    Hero hero =new Hero();
    hero.setName("盖伦");
    hero.setHp(616);
     
    request.setAttribute("hero", hero);
%>
  
英雄名字 ： ${hero.name} <br>
英雄血量 ： ${hero.hp}

<%
request.setAttribute("killNumber", "10");
%>
 
c:if 的用法，运行结果：
<c:if test="${killNumber>=10}">
超神
</c:if>
<c:if test="${killNumber<10}">
还没超神
</c:if>
<br>
c:choose 的用法，运行结果：
 
<c:choose>
    <c:when test="${killNumber>=10}">
        超神
    </c:when>
    <c:otherwise>
        还没超神
    </c:otherwise>
</c:choose>
<br>
EL表达式eq的用法，运行结果：
${killNumber ge 10? "超神":"还没超神" }