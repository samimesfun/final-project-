<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<div class="container mt-5 d-flex flex-column align-items-center">
    <h1 class="mb-4">Car Details</h1>

    <c:if test="${not empty car}">

            <div class="col-md-6 mb-3">
                <div class="card mx-auto">
                    <img src="${car.imageUrl}" alt="Car Image" class="card-img-top">
                    <div class="card-body">
                        <h2 class="card-title">Model: ${car.model}</h2>
                        <p class="card-text">
                            <strong>Category:</strong> ${car.category}<br>
                            <strong>Year:</strong> ${car.year}<br>
                            <strong>Price:</strong> ${car.price}<br>
                        </p>

                        <!-- Add to Cart Button -->
                        <form action="/order/addToCart" method="get">
                            <input type="hidden" name="carId" value="${car.id}">
                            <button type="submit" class="btn btn-success">Add to Cart</button>
                        </form>


                        <!-- Edit button for admin -->
                        <c:if test="${pageContext.request.userPrincipal != null && pageContext.request.userPrincipal.authorities.contains('ADMIN')}">
                            <a href="/car/edit/${car.id}" class="btn btn-primary">Edit</a>
                        </c:if>
                    </div>
                </div>
            </div>

    </c:if>
    </div>

<jsp:include page="../include/footer.jsp"/>