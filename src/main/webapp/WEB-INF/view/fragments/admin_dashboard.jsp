<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="${contextPath}/admin">
                    <fmt:message key="admin.dashboard.main">Back to main</fmt:message>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/admin/users">
                    <fmt:message key="admin.dashboard.all.users">All users</fmt:message>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/admin/categories">
                    <fmt:message key="admin.dashboard.categories">Categories</fmt:message>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="${contextPath}/activities">
                    <fmt:message key="admin.dashboard.activities">Activities</fmt:message>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${contextPath}/report">
                    <fmt:message key="admin.dashboard.report">Report</fmt:message>
                </a>
            </li>
        </ul>
    </div>
</nav>