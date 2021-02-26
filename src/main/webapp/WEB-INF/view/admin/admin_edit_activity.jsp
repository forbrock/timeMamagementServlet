<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <c:import url="/WEB-INF/view/fragments/admin_head.jsp"/>
</head>
<body>
<c:import url="/WEB-INF/view/fragments/admin_header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <c:import url="/WEB-INF/view/fragments/admin_dashboard.jsp"/>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 mt-5">
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2"><fmt:message key="admin.edit.activity.title">edit activity</fmt:message></h1>
            </div>
            <form action="${contextPath}/admin/activity/edit?id=${requestScope.id}" method="post" id="edit-activity">
                <div class="row">
                    <div class="col-8">
                        <div class="form-group form-inline">
                            <label for="name" class="col-3 px-0 justify-content-start">
                                <fmt:message key="admin.edit.activity.form.label.name">name</fmt:message>
                            </label>
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="<fmt:message key='admin.edit.activity.form.placeholder.name'/>"
                                   value="${requestScope.name}">
                            <c:if test="${requestScope.activity_exists_message == true}">
                                <div class="alert alert-danger alert" role="alert">
                                    <fmt:message key="valid.admin.activity.exists"/>
                                </div>
                            </c:if>
                        </div>
                        <div class="form-group form-inline">
                            <label for="category" class="col-3 px-0 justify-content-start">
                                <fmt:message key="admin.edit.activity.form.label.category">category</fmt:message>
                            </label>
                            <select class="form-control" id="category" name="category">
                                <c:forEach var="category" items="${requestScope.categories}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-sm btn-outline-secondary m-2">
                    <fmt:message key="admin.edit.activity.form.button.save">save</fmt:message></button>
            </form>
        </main>
    </div>
</div>
<c:import url="/WEB-INF/view/fragments/admin_footer.jsp"/>
</body>
</html>