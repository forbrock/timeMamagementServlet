<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<button class="btn btn-sm btn-outline-secondary m-2" data-toggle="modal" data-target="#categoryModal">
    <span><fmt:message key="modal.new.category.button.add">add new category</fmt:message></span>
</button>
<!-- The Modal -->
<div class="modal fade" id="categoryModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title"><fmt:message key="modal.new.category.header">add new category</fmt:message></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form action="${contextPath}/admin/category/create" method="post" id="create-category">
                    <div class="row">
                        <div class="col-10">
                            <div class="form-group form-inline">
                                <label for="name" class="col-5 px-0 justify-content-start">
                                    <fmt:message key="modal.new.category.label">category</fmt:message></label>
                                <input type="text" class="form-control mb-1" id="name" name="name"
                                       placeholder="<fmt:message key='modal.new.category.placeholder.name'/>">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <!-- <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button> -->
                <button type="submit" form="create-category" class="btn btn-md btn-outline-secondary m-2">
                    <fmt:message key="modal.new.category.button.create">create</fmt:message>
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">
                    <fmt:message key="modal.new.category.button.close">close</fmt:message>
                </button>
            </div>
        </div>
    </div>
</div>
