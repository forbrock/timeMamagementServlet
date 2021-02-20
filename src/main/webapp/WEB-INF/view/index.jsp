<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Time Management</title>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title><fmt:message key="index.title"/></title>
</head>

<body>
<div><c:import url="/WEB-INF/view/fragments/user_header.jsp"/></div>

<div class="container">
    <!-- Tabs navigation -->
    <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home"
           role="tab" aria-controls="nav-home" aria-selected="true">
            <fmt:message key="index.tab.current">current</fmt:message>
        </a>
        <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile"
           role="tab" aria-controls="nav-profile" aria-selected="false">
            <fmt:message key="index.tab.new">new</fmt:message>
        </a>
        <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact"
           role="tab" aria-controls="nav-contact" aria-selected="false">
            <fmt:message key="index.tab.completed">completed</fmt:message>
        </a>
    </div>
    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            <table class="table table-striped table-sm mt-3">
                <thead>
                <tr>
                    <th><fmt:message key="index.tab.current.table.head.category">category</fmt:message></th>
                    <th><fmt:message key="index.tab.current.table.head.activity">activity</fmt:message></th>
                    <th><fmt:message key="index.tab.current.table.head.state">state</fmt:message></th>
                    <th><fmt:message key="index.tab.current.table.head.time">time</fmt:message></th>
                    <th><fmt:message key="index.tab.current.table.head.total.time">total time</fmt:message></th>
                    <th><fmt:message key="index.tab.current.table.head.actions">actions</fmt:message></th>
                </tr>
                </thead>
                <tbody>
                <div>
                    <c:forEach var="ua" items="${requestScope.userActivities}">
                        <c:if test="${ua.state.name() == 'ACCEPTED'}">
                            <tr>
                                <td><span>${ua.categoryName}</span></td>
                                <td><span>${ua.activityName}</span></td>
                                <td><span>${ua.state.name()}</span></td>
                                <td>
                                    <form method="post" action="${contextPath}/time/${ua.id}/add}">
                                        <fieldset title="<fmt:message key="valid.index.tab.current.table.content.time.tooltip">constraints</fmt:message>">
                                            <input type="number" name="time" min="0.5" max="12" step="0.5" required>
                                            <input type="submit" value="<fmt:message key="index.tab.current.table.content.add.time">add time</fmt:message>">
                                        </fieldset>
                                    </form>
                                </td>
                                <td><span>${ua.duration}</span></td>
                                <td>
                                    <a type="button" class="btn btn-outline-success btn-sm px-2"
                                       href="${contextPath}/complete/${ua.id}">
                                        <fmt:message key="index.tab.current.table.content.complete.task">complete task</fmt:message>
                                    </a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </div>
                </tbody>
            </table>
            <div><jsp:include page="fragments/modal_new_user_activity.jsp"/></div>
        </div>

        <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
            <table class="table table-striped table-sm mt-3">
                <thead>
                <tr>
                    <th><fmt:message key="index.tab.new.table.head.category">category</fmt:message></th>
                    <th><fmt:message key="index.tab.new.table.head.activity">activity</fmt:message></th>
                    <th><fmt:message key="index.tab.new.table.head.state">state</fmt:message></th>
                </tr>
                </thead>
                <tbody>
<%--
                        <div th:each="ua : ${userActivities}">
                            <tr>
                                <td><a th:text="${ua.getActivity().getCategory().getName()}">category</a></td>
                                <td><a th:text="${ua.getActivity().getName()}">name</a></td>
                                <td><a th:text="${ua.getState()}">accepted</a></td>
                                <td><a th:text="$some buttons">actions</a></td>
                            </tr>
                        </div>
--%>
                </tbody>
            </table></div>
        <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
            <table class="table table-striped table-sm mt-3">
                <thead>
                <tr>
                    <th><fmt:message key="index.tab.completed.table.head.category">category</fmt:message></th>
                    <th><fmt:message key="index.tab.completed.table.head.activity">activity</fmt:message></th>
                    <th><fmt:message key="index.tab.completed.table.head.state">state</fmt:message></th>
                    <th><fmt:message key="index.tab.completed.table.head.total.time">total time</fmt:message></th>
                </tr>
                </thead>
                <tbody>
                    <div>
                        <c:forEach var="ua" items="${requestScope.userActivities}">
                            <c:if test="${ua.state.name() == 'COMPLETED'}">
                                <tr>
                                    <td><span>${ua.categoryName}</span></td>
                                    <td><span>${ua.activityName}</span></td>
                                    <td><span>${ua.state.name()}</span></td>
                                    <td><span>${ua.duration}</span></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </div>
                </tbody>
            </table>
        </div>
    </div>
    <c:if test="${requestScope.request_success_message != null}">
        <div class="alert alert-success alert" role="alert">
            <fmt:message key="valid.index.requested.activity.message.success"/>
            <a href="${contextPath}/app/index">
                <fmt:message key="valid.index.requested.activity.message.success.return">return</fmt:message>
            </a>
        </div>
    </c:if>
    <c:if test="${requestScope.request_success_message != null}">
        <div class="alert alert-success alert" role="alert">
            <fmt:message key="valid.index.requested.activity.message.failure"/>
            <a href="${contextPath}/app/index">
                <fmt:message key="valid.index.requested.activity.message.failure.return">return</fmt:message>
            </a>
        </div>
    </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.js"></script>
</body>
</html>