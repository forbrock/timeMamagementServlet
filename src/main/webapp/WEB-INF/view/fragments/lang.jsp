<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="btn-group shadow-0 float-right">
    <button type="button" class="btn btn-link dropdown-toggle text-dark" data-mdb-toggle="dropdown"
            aria-expanded="false"><fmt:message key="lang.change">lang</fmt:message>
    </button>
    <ul class="dropdown-menu">
        <li>
            <a class="dropdown-item" href="?lang=en">
<%--                <img src="${contextPath}/public/img/united-kingdom.svg" alt="user-image" class="mr-1" height="18"/>--%>
                <img src="<c:url value='/public/img/united-kingdom.svg'/>" alt="user-image" class="mr-1" height="18"/>
                <span class="align-middle"><fmt:message key="lang.en">english</fmt:message></span>
            </a>
        </li>
        <li>
            <a class="dropdown-item" href="?lang=uk">
                <img src="${contextPath}/public/img/ukraine.svg" alt="user-image" class="mr-1" height="18"/>
                <span class="align-middle"><fmt:message key="lang.uk">ukrainian</fmt:message></span>
            </a>
        </li>
    </ul>
</div>