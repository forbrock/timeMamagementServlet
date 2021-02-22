<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="${contextPath}/">Time management</a>
    <form id="logout-form" action="${contextPath}/logout" method="POST"></form>
    <ul class="navbar-nav px-3 ml-auto">
        <li class="nav-item text-nowrap">
            <!-- Lang menu  -->
            <div class="btn-group nav-item text-nowrap shadow-0">
                <a class="btn dropdown-toggle text-white" data-mdb-toggle="dropdown"
                   aria-expanded="false"><fmt:message key="lang.change">lang</fmt:message></a>
                <ul class="dropdown-menu">
                    <li>
                        <a class="dropdown-item" href="?lang=en">
                            <img src="/public/img/united-kingdom.svg" alt="user-image" class="mr-1" height="18">
                            <span class="align-middle"><fmt:message key="lang.en">english</fmt:message></span>
                        </a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="?lang=uk">
                            <img src="/public/img/ukraine.svg" alt="user-image" class="mr-1" height="18">
                            <span class="align-middle"><fmt:message key="lang.uk">ukrainian</fmt:message></span>
                        </a>
                    </li>
                </ul>
            </div>
        </li>
        <li class="nav-item text-nowrap">
            <button type="submit" form="logout-form" class="btn nav-link"><fmt:message key="admin.sign.out">sign out</fmt:message></button>
        </li>
    </ul>
</nav>