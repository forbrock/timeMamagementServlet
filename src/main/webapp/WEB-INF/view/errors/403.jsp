<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>403 Error page</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container" style="margin-top: 50px;">
    <div class="error-body">
        <h3><fmt:message key="error.page.forbidden.message">something went wrong!</fmt:message></h3>
        <h1>403</h1>
        <p style="font-size: 22px;"><fmt:message key="valid.access.restricted">forbidden</fmt:message></p>
        <a href="${contextPath}/"><fmt:message key="error.page.back.home">back to Home Page</fmt:message></a>
    </div>
</div>
</body>

</html>