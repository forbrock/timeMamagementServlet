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
				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3">
					<h1 class="h2"><fmt:message key="admin.report.title">Report</fmt:message></h1>
				</div>
				<table class="table table-striped table-sm mt-3">
                    <thead>
                        <tr>
                            <th><fmt:message key="admin.report.table.head.user">user</fmt:message></th>
                            <th><fmt:message key="admin.report.table.head.category">category</fmt:message></th>
                            <th><fmt:message key="admin.report.table.head.activity">activity</fmt:message></th>
                            <th><fmt:message key="admin.report.table.head.state">state</fmt:message></th>
                            <th><fmt:message key="admin.report.table.head.total.time">total time</fmt:message></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="ua" items="${requestScope.ua_report}">
                            <tr>
                                <td><span><c:out value="${ua.email}"/></span></td>
                                <td><span><c:out value="${ua.category}"/></span></td>
                                <td><span><c:out value="${ua.activity}"/></span></td>
                                <td><span><c:out value="${ua.state}"/></span></td>
                                <td><span><c:out value="${ua.duration}"/></span></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
			</main>
      </div>
    </div>

  <c:import url="/WEB-INF/view/fragments/admin_footer.jsp"/>
 </body>
 </html>