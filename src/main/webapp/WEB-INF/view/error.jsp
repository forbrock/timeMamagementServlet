<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<c:set var="httpStatus" scope="request" value="${requestScope['javax.servlet.error.status_code']}"/>
<c:set var="pageContext" value="${pageContext.request.contextPath}"/>
<div class="container" style="margin-top: 50px;">
    <div class="error-body">
        <h3><fmt:message key="error.page.common.message">something went wrong!</fmt:message></h3>
        <h1><c:out value="${httpStatus}">404</c:out></h1>
        <p style="font-size: 22px;"><fmt:message key="error.page"/></p>
        <p><c:out value="${requestScope['javax.servlet.error.request_uri']}">url</c:out></p>
        <a href="${pageContext}/"><fmt:message key="error.page.back.home">back to Home Page</fmt:message></a>
    </div>
</div>
</body>

</html>