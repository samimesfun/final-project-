<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp" %>

<div class="container mt-5">
    <h1 class="mb-4">View Shopping Cart</h1>




    <c:if test="${not empty cartOrder and not empty cartOrder.orderDetails}">
        <div class="row">
            <!-- Displaying individual items -->
            <div class="col-lg-8">
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
                                    <img src="${orderDetail.car.imageUrl}" alt="${orderDetail.car.model}"
                                        style="max-width: 100px; max-height: 100px;">
                                </td>
                                <td>${orderDetail.car.model}</td>
                                <td>${orderDetail.quantity}</td>
                                <td>${orderDetail.car.price}</td>
                                <!-- Add more columns as needed -->
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Displaying total order price -->
            <div class="col-lg-4">
                <div class="card bg-success text-white rounded-3">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <p class="mb-2">Total</p>
                            <p class="mb-2">$${totalOrderPrice}</p>
                        </div>

                        <!-- Existing form inputs for card details -->
                        <form class="mt-4" action="/order/checkout" method="post">
                            <div class="form-outline form-white mb-4">
                                <input type="text" id="typeName" class="form-control form-control-lg" size="17"
                                    placeholder="Cardholder's Name" />
                                <label class="form-label" for="typeName">Cardholder's Name</label>
                            </div>

                            <div class="form-outline form-white mb-4">
                                <input type="text" id="typeText" class="form-control form-control-lg" size="17"
                                    placeholder="1234 5678 9012 3457" minlength="19" maxlength="19" />
                                <label class="form-label" for="typeText">Card Number</label>
                            </div>

                            <div class="row mb-4">
                                <div class="col-md-6">
                                    <div class="form-outline form-white">
                                        <input type="text" id="typeExp" class="form-control form-control-lg"
                                            placeholder="MM/YYYY" size="7" id="exp" minlength="7"
                                            maxlength="7" />
                                        <label class="form-label" for="typeExp">Expiration</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-outline form-white">
                                        <input type="password" id="typeText" class="form-control form-control-lg"
                                            placeholder="&#9679;&#9679;&#9679;" size="1" minlength="3"
                                            maxlength="3" />
                                        <label class="form-label" for="typeText">Cvv</label>
                                    </div>
                                </div>
                            </div>

                            <!-- Add a hidden input for total order price -->
                            <input type="hidden" name="totalOrderPrice" value="${totalOrderPrice}" />

                            <hr class="my-4">

                            <!-- Checkout button -->
                            <div class="d-flex justify-content-between">
                                <p class="mb-2">Total</p>
                                <p class="mb-2">$${totalOrderPrice}</p>
                            </div>

                            <button type="submit" class="btn btn-info btn-block btn-lg">
                                <div class="d-flex justify-content-between">
                                    <span>Checkout <i class="bi bi-arrow-right ms-2"></i></span>
                                </div>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${empty cartOrder or empty cartOrder.orderDetails}">
        <p>Your shopping cart is empty.</p>
    </c:if>
</div>

<%@ include file="../include/footer.jsp" %>
