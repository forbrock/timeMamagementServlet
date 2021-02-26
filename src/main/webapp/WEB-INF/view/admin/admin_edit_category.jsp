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
              <h1 class="h2"><fmt:message key="admin.edit.category.title">edit category</fmt:message></h1>
            </div>
            <form action="${contextPath}/admin/category/edit?id=${requestScope.id}" method="post" id="edit-category">
              <div class="form-group row">
                <label for="name" class="col-sm-2 col-form-label">
                    <fmt:message key="admin.edit.category.form.label.name">name</fmt:message>
                </label>
                <div class="col-sm-7">
                  <input type="text" class="form-control" id="name" name="name"
                         placeholder="<fmt:message key='admin.edit.category.form.placeholder.name'/>"
                         value="${requestScope.name}">
                </div>
                  <c:if test="${requestScope.category_empty_name == true}">
                      <div class="alert alert-danger alert" role="alert">
                          <fmt:message key="valid.admin.category.edit.empty"/>
                      </div>
                  </c:if>
                  <c:if test="${requestScope.category_already_exist == true}">
                      <div class="alert alert-danger alert" role="alert">
                          <fmt:message key="valid.admin.new.category.exists"/>
                      </div>
                  </c:if>
              </div>
                <button type="submit" class="btn btn-sm btn-outline-secondary m-2">
                    <fmt:message key="admin.edit.category.form.button.save">save</fmt:message>
                </button>
            </form>
            <c:if test="${requestScope.category_not_found == true}">
                <div class="alert alert-danger alert" role="alert">
                    <fmt:message key="valid.admin.category.not.found.message"/>
                    <a href="${contextPath}/admin/categories">
                        <fmt:message key="valid.admin.category.not.found.message.return">return</fmt:message>
                    </a>
                </div>
            </c:if>
          </main>    
      </div>
    </div>
  <c:import url="/WEB-INF/view/fragments/admin_footer.jsp"/>
 </body>
 </html>