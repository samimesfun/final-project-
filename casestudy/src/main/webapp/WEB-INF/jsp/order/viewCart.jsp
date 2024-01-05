<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp"/>
<div class="container mt-5">
    <h1 class="mb-4">View Shopping Cart</h1>

    <c:if test="${not empty cartOrder and not empty cartOrder.orderDetails}">
        <table class="table">
            <thead>
                <tr>
                    <th>Product Image</th>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <!-- Add more columns as needed -->
                </tr>
            </thead>
            <tbody>
                <c:forEach var="orderDetail" items="${cartOrder.orderDetails}">
                    <tr>
                        <td>
                            <img src="${orderDetail.car.imageUrl}" alt="${orderDetail.car.model}" style="max-width: 100px; max-height: 100px;">
                        </td>
                        <td>${orderDetail.car.model}</td>
                        <td>${orderDetail.quantity}</td>
                        <td>${orderDetail.car.price}</td>
                        <!-- Add more columns as needed -->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
     </c:if>

    <c:if test="${empty cartOrder or empty cartOrder.orderDetails}">
        <p>Your shopping cart is empty.</p>
    </c:if>
</div>

<jsp:include page="../include/footer.jsp"/>
