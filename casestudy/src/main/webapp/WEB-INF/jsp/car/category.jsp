<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp"/>


<c:if test="${not empty cars}">
    <h1 style="text-align: center; padding-top: 10px">Car Found ${cars.size()}</h1>

    <div class="container mt-4">
        <div class="row">
            <c:forEach items="${cars}" var="car" varStatus="loop">
                <div class="col-md-4 mb-4">
                    <div class="card">

                        <img src="${car.imageUrl}" class="card-img-top" alt="Car Image" style="height: 200px;">

                        <div class="card-body">
                            <h5 class="card-title">Model: ${car.model}</h5>
                            <p class="card-text">
                                <strong>Category:</strong> ${car.category}<br>
                                <strong>Year:</strong> ${car.year}<br>
                                <strong>Price:</strong> ${car.price}<br>
                            </p>
                            <!-- Additional details or actions can be added here -->
                            <a href="/car/detail?id=${car.id}" class="btn btn-info">View Details</a>
                            <!-- Add to Cart Button -->
                            <div class="card-body">
                                <form action="/order/addToCart" method="get">
                                    <input type="hidden" name="carId" value="${car.id}">
                                    <button type="submit" class="btn btn-success">Add to Cart</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- Start a new row every 3 cards --%>
                <c:if test="${loop.index % 3 == 2}">
                    </div>
                    <div class="row">
                </c:if>
            </c:forEach>
        </div>
    </div>
</c:if>

<jsp:include page="../include/footer.jsp"/>