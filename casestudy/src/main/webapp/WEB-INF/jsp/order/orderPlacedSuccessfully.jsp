<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<!-- Add top padding using Bootstrap spacing utility class -->
<section class="mt-4">
    <c:if test="${not empty param.success}">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="alert alert-success text-center" role="alert">
                    ${param.success}
                    <p>Order is placed successfully</p>
                </div>
            </div>
        </div>
    </c:if>
</section>

<%@ include file="../include/footer.jsp" %>
