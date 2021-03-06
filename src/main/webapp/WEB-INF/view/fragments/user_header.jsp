<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<header class="d-flex flex-column flex-md-row align-items-center p-2 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <a style="text-decoration: none; font-size: 16pt;" class="brand my-0 mr-auto text-dark" href="${contextPath}/">Time Management</a>

    <c:if test="${sessionScope.loggedUser != null}">
        <div style="color: #6c757d"><c:out value="${sessionScope.loggedUser.email}">logged user email</c:out> </div>
    </c:if>

    <c:if test="${requestScope.loggedUser.role == 'ADMIN'}">
        <nav class="my-2 my-md-0 me-md-3">
            <a class="p-2 text-dark" href="#"><fmt:message key="index.admin.dashboard.link">dashboard</fmt:message></a>
        </nav>
    </c:if>
    <!-- Lang menu  -->
    <div><c:import url="/WEB-INF/view/fragments/lang.jsp"/></div>

    <button form="sign_out" type="submit" class="btn btn-outline-info ml-2" name="logout" value="logout">
        <fmt:message key="index.sign.out">sign out</fmt:message>
    </button>
    <form id="sign_out" action="${contextPath}/logout" name="logout" method="POST">
    </form>
</header>