<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../include/header.jsp"/>

<div class="container mt-5">
    <h1 class="mb-4">Item Added to Cart</h1>

    <p>The selected item has been successfully added to your cart.</p>

     <a href="/order/viewCart/${cartId}">View Cart</a>

    <!-- You can provide links or buttons to continue shopping or view the cart -->
    <a href="/home">Continue Shopping</a>
    <a href="/order/view">View Cart</a>
</div>

<jsp:include page="../include/footer.jsp"/>
