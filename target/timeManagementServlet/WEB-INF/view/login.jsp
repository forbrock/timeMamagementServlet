<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet"
          crossorigin="anonymous" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title><fmt:message key="login.title"/></title>
</head>

<body>
<div class="container">
    <div><jsp:include page="fragments/lang.jsp"/></div>

    <form class="form-signin" method="post" action="${contextPath}/app/login">
        <h2 class="form-signin-heading"><fmt:message key="login.header">sign in</fmt:message> </h2>
        <c:if test="${requestScope.logout != null}">
            <div class="alert alert-success" role="alert"><fmt:message key="valid.login.logged.out.success"/></div>
        </c:if>
        <c:if test="${requestScope.login_error != null}">
            <div class="alert alert-danger" role="alert"><fmt:message key="valid.login.wrong.credential"/></div>
        </c:if>
        <p>
            <input type="text" id="email" name="email" class="form-control"
                   placeholder="<fmt:message key="login.email.placeholder">email</fmt:message>"
                   autofocus>
        </p>
        <p>
            <input type="password" id="password" name="password" class="form-control"
                   placeholder="<fmt:message key="login.password.placeholder">password</fmt:message>">
        </p>

        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="login.login">log in</fmt:message>
        </button>
        <div class="my-sm-3 m-3 text-right">
            <a href="${contextPath}/app/registration">
                <fmt:message key="login.sign.up">sign up</fmt:message>
            </a>
        </div>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.js"></script>
</body>
</html>