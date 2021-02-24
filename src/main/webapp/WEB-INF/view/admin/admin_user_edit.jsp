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
              <h1 class="h2"><fmt:message key="admin.edit.user.title">edit user</fmt:message></h1>
            </div>

          <form action="${contextPath}/admin/user/edit.do?id=${requestScope.id}" method="post" id="edit-user">
            <div class="row">
              <div class="col-10">
                <div class="form-group form-inline">
                  <label for="firstName" class="col-3 px-0 justify-content-start">
                    <fmt:message key="admin.edit.user.form.label.first.name">first name</fmt:message></label>
                  <input type="text" class="form-control mb-1" id="firstName" name="firstName" value="${requestScope.firstName}"
                         placeholder="<fmt:message key='admin.edit.user.form.placeholder.first.name'/>">
                  <c:if test="${requestScope.first_name_not_valid != null}">
                    <div class="alert alert-danger p-2" role="alert">
                      <c:out value="${requestScope.first_name_not_valid}"/>
                    </div>
                  </c:if>
                </div>
                <div class="form-group form-inline">
                  <label for="lastName" class="col-3 px-0 justify-content-start">
                    <fmt:message key="admin.edit.user.form.label.last.name">last name</fmt:message></label>
                  <input type="text" class="form-control mb-1" id="lastName" name="lastName" value="${requestScope.lastName}"
                         placeholder="<fmt:message key='admin.edit.user.form.placeholder.last.name'/>">
                  <c:if test="${requestScope.last_name_not_valid != null}">
                    <div class="alert alert-danger p-2" role="alert">
                      <c:out value="${requestScope.last_name_not_valid}"/>
                    </div>
                  </c:if>
                </div>
                <div class="form-group form-inline">
                  <label for="email" class="col-3 px-0 justify-content-start">
                    <fmt:message key="admin.edit.user.form.label.email">email</fmt:message></label>
                  <input type="email" class="form-control mb-1" id="email" name="email" value="${requestScope.email}"
                         placeholder="<fmt:message key='admin.edit.user.form.placeholder.email'/>">
                  <c:if test="${requestScope.email_not_valid != null}">
                    <div class="alert alert-danger p-2" role="alert">
                      <c:out value="${requestScope.email_not_valid}"/>
                    </div>
                  </c:if>
                </div>
                <div class="form-group form-inline">
                  <label for="password" class="col-3 px-0 justify-content-start">
                    <fmt:message key="admin.edit.user.form.label.password">password</fmt:message></label>
                  <input type="password" class="form-control mb-1" id="password" name="password"
                         placeholder="<fmt:message key='admin.edit.user.form.placeholder.password'/>">
                  <c:if test="${requestScope.password_not_valid != null}">
                    <div class="alert alert-danger p-2" role="alert">
                      <c:out value="${requestScope.password_not_valid}"/>
                    </div>
                  </c:if>
                </div>
                <div class="form-group form-inline">
                  <label for="matchingPassword" class="col-3 px-0 justify-content-start">
                    <fmt:message key="admin.edit.user.form.label.confirm.password">confirm password</fmt:message></label>
                  <input type="password" class="form-control mb-1" id="matchingPassword" name="matchingPassword"
                         placeholder="<fmt:message key='admin.edit.user.form.placeholder.confirm.password'/>">
                  <c:if test="${requestScope.matching_password_not_valid != null}">
                    <div class="alert alert-danger p-2" role="alert">
                      <c:out value="${requestScope.matching_password_not_valid}"/>
                    </div>
                  </c:if>
                </div>
              </div>
              <div>
                <select class="form-control" style="width:auto" name="role" id="role">
                  <c:forEach var="r" items="${requestScope.roles}">
                    <option ${r eq requestScope.role ? 'selected' : ''}>
                      <c:out value="${r.name()}"/>
                    </option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <button type="submit" class="btn btn-sm btn-outline-secondary mt-3">
              <fmt:message key="admin.edit.user.form.button.save">save</fmt:message>
            </button>
          </form>
         </main>
      </div>
    </div>

  <c:import url="/WEB-INF/view/fragments/admin_footer.jsp"/>
 </body>
 </html>