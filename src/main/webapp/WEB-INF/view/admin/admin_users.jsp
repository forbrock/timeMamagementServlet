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
          <h1 class="h2"><fmt:message key="admin.users.title">users</fmt:message></h1>
        </div>
        <div class="table-responsive">
          <table class="table table-striped table-sm mt-3">
            <thead>
              <tr>
                <th><fmt:message key="admin.users.table.head.full.name">full name</fmt:message></th>
                <th><fmt:message key="admin.users.table.head.email">email</fmt:message></th>
                <th><fmt:message key="admin.users.table.head.state">state</fmt:message></th>
                <th><fmt:message key="admin.users.table.head.actions">actions</fmt:message></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="user" items="${requestScope.users}">
                <tr>
                  <td><span>${user.firstName} ${user.lastName}</span></td>
                  <td><span>${user.email}</span></td>
                  <td>
                    <c:if test="${user.enabled == true}"><fmt:message key="admin.users.table.state.active"/></c:if>
                    <c:if test="${user.enabled == false}"><fmt:message key="admin.users.table.state.disabled"/></c:if>
                  </td>
                  <td>
                    <a class="m-2" href="${contextPath}/admin/user/edit?id=${user.id}">
                      <fmt:message key="admin.users.table.button.edit">edit</fmt:message> </a>
                    <a href="${contextPath}/admin/user/delete?id=${user.id}">
                      <fmt:message key="admin.users.table.button.delete">delete</fmt:message></a>
                    <a href="${contextPath}/admin/user/activities?id=${user.id}">
                      <fmt:message key="admin.users.table.button.view">view activities</fmt:message></a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <c:import url="/WEB-INF/view/fragments/modal_new_user.jsp"/>
      </main>
    </div>
  </div>

  <c:import url="/WEB-INF/view/fragments/admin_footer.jsp"/>
</body>

</html>