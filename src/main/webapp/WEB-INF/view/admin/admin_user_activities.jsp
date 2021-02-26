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
			  <h1 class="h2"><fmt:message key="admin.user.activities.title">user activities</fmt:message> ${requestScope.email}</h1>
			</div>
			<div class="table-responsive">
			  <table class="table table-striped size-sm">
				<thead>
				  <tr>
					<th><fmt:message key="admin.user.activities.table.head.category">category</fmt:message></th>
					<th><fmt:message key="admin.user.activities.table.head.name">name</fmt:message></th>
					<th><fmt:message key="admin.user.activities.table.head.time">total time</fmt:message></th>
					<th><fmt:message key="admin.user.activities.table.head.state">state</fmt:message></th>
				  </tr>
				</thead>
				<tbody>
				  <c:forEach var="ua" items="${requestScope.activities}">
					<tr>
					  <td><span>${ua.category}</span></td>
					  <td><span>${ua.activity}</span></td>
					  <td><span>${ua.duration}</span></td>
					  <td><span>${ua.state}</span></td>
					</tr>
				  </c:forEach>
				</tbody>
			  </table>
			</div>
			<c:if test="${requestScope.user_not_found_message == true}">
				<div class="alert alert-danger alert" role="alert">
					<fmt:message key="valid.admin.user.activity.not.found.message"/>
					<a href="${contextPath}/admin/user/activities">
						<fmt:message key="valid.admin.user.activity.not.found.message.return">return</fmt:message>
					</a>
				</div>
			</c:if>
		  </main>
      </div>
    </div>
  <c:import url="/WEB-INF/view/fragments/admin_footer.jsp"/>
 </body>
 </html>