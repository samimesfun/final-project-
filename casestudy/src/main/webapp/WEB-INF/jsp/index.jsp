<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="/pub/css/global-style.css">

<jsp:include page="include/header.jsp"/>

<div class="background-container">
    <div class="row">
        <div class="column">
            <div class="cards" onclick="showCars('SUV')">
                <img src="/pub/images/suv/suv-si.jpg" alt="Card 1">
                <div class="cards-text">SUV</div>
            </div>
            <div class="cards" onclick="showCars('ROADSTER')">
                <img src="/pub/images/roadster/roadster-y.jpg" alt="Card 2">
                <div class="cards-text">ROADSTER</div>
            </div>
        </div>
        <div class="column">
            <div class="cards" onclick="showCars('COUPES')">
                <img src="/pub/images/coupes/z.jpg" alt="Card 3">
                <div class="cards-text">COUPES</div>
            </div>
            <div class="cards" onclick="showCars('SEDAN')">
                <img src="/pub/images/sedan/a.jpg" alt="Card 4">
                <div class="cards-text">SEDAN</div>
            </div>
        </div>
    </div>
</div>

<script src="/pub/js/myScript.js"></script>
<jsp:include page="include/footer.jsp"/>
