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
                    <a class="px-2" style="text-decoration: none"
                       href="${contextPath}/admin/user/edit?id=${user.id}">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="16" height="16">
                        <path id="edit" fill-rule="evenodd" d="M11.013 1.427a1.75 1.75 0 012.474 0l1.086 1.086a1.75 1.75 0 010 2.474l-8.61 8.61c-.21.21-.47.364-.756.445l-3.251.93a.75.75 0 01-.927-.928l.929-3.25a1.75 1.75 0 01.445-.758l8.61-8.61zm1.414 1.06a.25.25 0 00-.354 0L10.811 3.75l1.439 1.44 1.263-1.263a.25.25 0 000-.354l-1.086-1.086zM11.189 6.25L9.75 4.81l-6.286 6.287a.25.25 0 00-.064.108l-.558 1.953 1.953-.558a.249.249 0 00.108-.064l6.286-6.286z"></path>
                      </svg>
                    </a>
                    <a class="px-2" style="text-decoration: none"
                       href="${contextPath}/admin/user/delete?id=${user.id}">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="16" height="16">
                        <path id="delete" fill-rule="evenodd" d="M6.5 1.75a.25.25 0 01.25-.25h2.5a.25.25 0 01.25.25V3h-3V1.75zm4.5 0V3h2.25a.75.75 0 010 1.5H2.75a.75.75 0 010-1.5H5V1.75C5 .784 5.784 0 6.75 0h2.5C10.216 0 11 .784 11 1.75zM4.496 6.675a.75.75 0 10-1.492.15l.66 6.6A1.75 1.75 0 005.405 15h5.19c.9 0 1.652-.681 1.741-1.576l.66-6.6a.75.75 0 00-1.492-.149l-.66 6.6a.25.25 0 01-.249.225h-5.19a.25.25 0 01-.249-.225l-.66-6.6z"></path>
                      </svg>
                    </a>
                    <a class="px-2" style="text-decoration: none"
                       href="${contextPath}/admin/user/activities?id=${user.id}">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="16" height="16">
                        <path id="view" fill-rule="evenodd" d="M2 4a1 1 0 100-2 1 1 0 000 2zm3.75-1.5a.75.75 0 000 1.5h8.5a.75.75 0 000-1.5h-8.5zm0 5a.75.75 0 000 1.5h8.5a.75.75 0 000-1.5h-8.5zm0 5a.75.75 0 000 1.5h8.5a.75.75 0 000-1.5h-8.5zM3 8a1 1 0 11-2 0 1 1 0 012 0zm-1 6a1 1 0 100-2 1 1 0 000 2z"></path>
                      </svg>
                    </a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <c:import url="/WEB-INF/view/fragments/modal_new_user.jsp"/>
        <c:if test="${requestScope.user_not_found_message == true}">
          <div class="alert alert-danger alert" role="alert">
            <fmt:message key="valid.admin.user.not.found.message"/>
            <a href="${contextPath}/admin/users">
              <fmt:message key="valid.admin.user.not.found.message.return">return</fmt:message>
            </a>
          </div>
        </c:if>
        <c:if test="${sessionScope.userCreatedMessage == true}">
          <div class="alert alert-success" role="alert">
            <fmt:message key="valid.admin.user.create.success"/>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <a href="<c:remove var='userCreatedMessage' scope="session"/>"
                 style="color: #6c757d; text-decoration: none"><span aria-hidden="true">&times;</span></a>
            </button>
          </div>
        </c:if>
        <c:if test="${sessionScope.userAlreadyExistsMessage == true}">
          <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <fmt:message key="valid.admin.user.create.failure"/>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <a href="<c:remove var='userAlreadyExistsMessage' scope="session"/>"
                 style="color: #6c757d; text-decoration: none"><span aria-hidden="true">&times;</span></a>
            </button>
          </div>
        </c:if>
        <c:if test="${sessionScope.category_error_message != null}">
          <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <fmt:message key="${sessionScope.errors}"/>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
              <a href="<c:remove var='errors' scope="session"/>"
                 style="color: #6c757d; text-decoration: none"><span aria-hidden="true">&times;</span></a>
            </button>
          </div>
        </c:if>
      </main>
    </div>
  </div>

  <c:import url="/WEB-INF/view/fragments/admin_footer.jsp"/>
</body>

</html>