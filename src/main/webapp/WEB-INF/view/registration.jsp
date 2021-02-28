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
    <title><fmt:message key="reg.title"/></title>
</head>

<body>
<div class="container">
    <div><jsp:include page="fragments/lang.jsp"/></div>

    <form class="form-signin" action="${contextPath}/registration" method="post">
        <h2 class="form-signin-heading"><fmt:message key="reg.header">Create your account</fmt:message> </h2>
        <p>
            <input type="text"
                   name="firstName"
                   class="form-control"
                   id="firstName"
                   value="${requestScope.first_name}"
                   placeholder="<fmt:message key="reg.first.name">first name</fmt:message>"
                   required autofocus>
            <c:if test="${requestScope.first_name_error != null}">
                <div class="alert alert-danger" role="alert"><c:out value="${requestScope.first_name_error}"/></div>
            </c:if>
        </p>
        <p>
            <input type="text"
                   name="lastName"
                   class="form-control"
                   id="lastName"
                   value="${requestScope.last_name}"
                   placeholder="<fmt:message key="reg.last.name">last name</fmt:message>"
                   required>
            <c:if test="${requestScope.errors.get('last_name_error') != null}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${requestScope.errors.get('last_name_error')}"/></div>
            </c:if>
        </p>
        <c:if test="${requestScope.userAlreadyExistsMessage == true}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert"><fmt:message key="valid.login.email.not.unique"/>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <p>
            <input type="text"
                   name="email"
                   class="form-control"
                   id="email"
                   value="${requestScope.email}"
                   placeholder="<fmt:message key="reg.email">email</fmt:message>"
                   required>
            <c:if test="${requestScope.errors.get('email_error') != null}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${requestScope.errors.get('email_error')}"/></div>
            </c:if>
        </p>
        <p>
            <input type="password"
                   name="password"
                   class="form-control"
                   id="password"
                   value="${requestScope.password}"
                   placeholder="<fmt:message key="reg.password">password</fmt:message>"
                   required>
            <c:if test="${requestScope.errors.get('password_error') != null}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${requestScope.errors.get('password_error')}"/></div>
            </c:if>
        </p>
        <p>
            <input type="password"
                   name="matchingPassword"
                   class="form-control"
                   id="matchingPassword"
                   value="${requestScope.matchingPassword}"
                   placeholder="<fmt:message key="reg.matching.password">confirm password</fmt:message>"
                   required>
            <c:if test="${requestScope.errors.get('matching_password_error') != null}">
                <div class="alert alert-danger" role="alert"><fmt:message key="${requestScope.errors.get('matching_password_error')}"/></div>
            </c:if>
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="reg.register">create</fmt:message>
        </button>
        <div class="my-sm-3 m-3 text-right">
            <a href="${contextPath}/login">
                <fmt:message key="reg.sign.in">i already have an account</fmt:message>
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