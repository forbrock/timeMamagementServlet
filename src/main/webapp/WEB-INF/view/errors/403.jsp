<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mft" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>403 Error page</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container" style="margin-top: 50px;">
    <div class="error-body">
        <h3><mft:message key="error.page.common.message">something went wrong!</mft:message></h3>
        <h1>403</h1>
        <p style="font-size: 22px;"><mft:message key="valid.access.restricted">forbidden</mft:message></p>
        <p><c:out value="${requestScope['javax.servlet.error.request_uri']}">url</c:out></p>
        <a href="${pageContext}/"><mft:message key="error.page.back.home">back to Home Page</mft:message></a>
        <%= exception %>
    </div>
</div>
</body>

</html>