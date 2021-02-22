<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<h1 class="h2"><fmt:message key="admin.main">Pending requests</fmt:message></h1>
				</div>
				<table class="table table-striped table-sm mt-3">
                    <thead>
                        <tr>
                            <th><fmt:message key="index.tab.current.table.head.user">user</fmt:message></th>
                            <th><fmt:message key="index.tab.current.table.head.category">category</fmt:message></th>
                            <th><fmt:message key="index.tab.current.table.head.activity">activity</fmt:message></th>
                            <th><fmt:message key="index.tab.current.table.head.state">state</fmt:message></th>
                            <th><fmt:message key="index.tab.current.table.head.actions">actions</fmt:message></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="activity" items="${requestScope.activities}">
                            <c:if test="${activity.state.name() == 'REQUESTED'}">
                                <tr>
                                    <td><span>${activity.email}</span></td>
                                    <td><span>${activity.category}</span></td>
                                    <td><span>${activity.activity}</span></td>
                                    <td><span>${activity.state.name()}</span></td>
                                    <td>
                                        <a type="button" class="btn btn-outline-success btn-sm px-2"
                                           href="${contextPath}/admin/request/confirm?id=${activity.id}">
                                            <fmt:message key="admin.user.activities.table.button.confirm">Confirm</fmt:message>
                                        </a>

                                        <a type="button" class="btn btn-outline-danger btn-sm px-2"
                                           href="${contextPath}/admin/request/delete?id=${activity.id}">
                                            <fmt:message key="admin.user.activities.table.button.delete">Delete</fmt:message>
                                        </a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
			</main>
		</div>
	</div>
	</div>
    <c:import url="/WEB-INF/view/fragments/admin_footer.jsp"/>
</body>

</html>