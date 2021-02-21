<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="property/messages"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div>
    <button class="btn btn-sm btn-outline-secondary m-2" data-toggle="modal" data-target="#activityModal">
        <span><fmt:message key="modal.new.user.activity.button.add">assign activity</fmt:message></span>
    </button>
    <!-- The Modal -->
    <div class="modal fade" id="activityModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title"><fmt:message key="modal.new.user.activity.header">activity assignment</fmt:message></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="${contextPath}/request_activity" method="post" id="create-activity">
                        <div class="row">
                            <div class="col-5">
                                <label for="activity" class="col-sm-2 col-form-label">
                                    <fmt:message key="modal.new.user.activity.label.activity">activity</fmt:message>
                                </label>
                                <select class="form-control" name="activityId" id="activity">
                                    <c:forEach var="activity" items="${requestScope.activities}">
                                        <option>
                                            <c:out value="${activity}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" form="create-activity" class="btn btn-md btn-outline-secondary m-2">
                        <fmt:message key="modal.new.user.activity.button.request">request activity</fmt:message>
                    </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">
                        <fmt:message key="modal.new.user.activity.button.close">close</fmt:message>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>