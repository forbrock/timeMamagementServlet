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
                                        <a class="px-2" style="text-decoration: none"
                                           href="${contextPath}/admin/request/accept?id=${activity.id}">
                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="16" height="16">
                                                <path id="accept" fill-rule="evenodd" d="M8 16A8 8 0 108 0a8 8 0 000 16zm3.78-9.72a.75.75 0 00-1.06-1.06L6.75 9.19 5.28 7.72a.75.75 0 00-1.06 1.06l2 2a.75.75 0 001.06 0l4.5-4.5z"></path>
                                            </svg>
                                        </a>

                                        <a class="px-2" style="text-decoration: none"
                                           href="${contextPath}/admin/request/decline?id=${activity.id}">
                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="16" height="16">
                                                <path id="delete" fill-rule="evenodd" d="M2.343 13.657A8 8 0 1113.657 2.343 8 8 0 012.343 13.657zM6.03 4.97a.75.75 0 00-1.06 1.06L6.94 8 4.97 9.97a.75.75 0 101.06 1.06L8 9.06l1.97 1.97a.75.75 0 101.06-1.06L9.06 8l1.97-1.97a.75.75 0 10-1.06-1.06L8 6.94 6.03 4.97z"></path>
                                            </svg>
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