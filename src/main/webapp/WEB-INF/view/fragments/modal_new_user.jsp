<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<button class="btn btn-sm btn-outline-secondary m-2" data-toggle="modal" data-target="#userModal">
    <span><fmt:message key="modal.new.user.button.add">add user</fmt:message></span>
</button>
<!-- The Modal -->
<div class="modal fade" id="userModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title"><fmt:message key="modal.new.user.header">add new user</fmt:message></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form action="${contextPath}/admin/user/create" method="post" id="create-user">
                    <div class="row">
                        <div class="col-10">
                            <div class="form-group form-inline">
                                <label for="firstName" class="col-5 px-0 justify-content-start">First name</label>
                                <input type="text" class="form-control mb-1" id="firstName" name="firstName"
                                       placeholder="<fmt:message key='modal.new.user.placeholder.first.name'/>">
                            </div>
                            <div class="form-group form-inline">
                                <label for="lastName" class="col-5 px-0 justify-content-start">Last name</label>
                                <input type="text" class="form-control mb-1" id="lastName" name="lastName"
                                       placeholder="<fmt:message key='modal.new.user.placeholder.last.name'/>">
                            </div>
                            <div class="form-group form-inline">
                                <label for="email" class="col-5 px-0 justify-content-start">Email</label>
                                <input type="email" class="form-control mb-1" id="email" name="email"
                                       placeholder="<fmt:message key='modal.new.user.placeholder.email'/>">
                            </div>
                            <div class="form-group form-inline">
                                <label for="password" class="col-5 px-0 justify-content-start">Password</label>
                                <input type="password" class="form-control mb-1" id="password" name="password"
                                       placeholder="<fmt:message key='modal.new.user.placeholder.password'/>">
                            </div>
                            <div class="form-group form-inline">
                                <label for="matchingPassword" class="col-5 px-0 justify-content-start">Confirm password</label>
                                <input type="password" class="form-control mb-1" id="matchingPassword" name="matchingPassword"
                                       placeholder="<fmt:message key='modal.new.user.placeholder.password.confirm'/>">
                            </div>
                        </div>
                        <div>
                            <select class="form-control" style="width:auto" name="role" id="role">
                                <c:forEach var="role" items="${requestScope.roles}">
                                    <option>
                                        <c:out value="${role.name()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="submit" form="create-user" class="btn btn-md btn-outline-secondary m-2">
                    <fmt:message key="modal.new.user.button.create">create</fmt:message>
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">
                    <fmt:message key="modal.new.user.button.close">close</fmt:message>
                </button>
            </div>
        </div>
    </div>
</div>
