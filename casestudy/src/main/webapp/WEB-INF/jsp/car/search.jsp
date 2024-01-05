<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../include/header.jsp"/>

<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Car Search</h1>
            </div>
        </div>
    </div>
    <div class="container pt-2">
            <form action="/car/search" class="d-flex justify-content-center">
                <input type="text" name="query" placeholder="Search by model or category" style="max-width: 300px;">
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
</section>
<section class="bg-light1 pt-5 pb-5 justify-content-center">

    <c:if test="${not empty carVar}">
        <h1 class="text-center pb-3">Car Found ${carVar.size()}</h1>

        <div class="container mt-4">
            <c:forEach items="${carVar}" var="car" varStatus="loop">
                <c:if test="${loop.index % 3 == 0}">
                    <div class="row">
                </c:if>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <img src="${car.imageUrl}" class="card-img-top" alt="Car Image" style="max-height: 200px;">
                        <div class="card-body">
                            <h5 class="card-title">Model: ${car.model}</h5>
                            <p class="card-text">
                                <strong>Category:</strong> ${car.category}<br>
                                <strong>Year:</strong> ${car.year}<br>
                                <strong>Price:</strong> ${car.price}<br>
                            </p>
                            <!-- Additional details or actions can be added here -->
                            <a href="/car/detail?id=${car.id}" class="btn btn-info">View Details</a>
                            <sec:authorize access="hasAnyAuthority('ADMIN')">
                            <a href="/car/edit/${car.id}" class="btn btn-info">EDIT</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
                <c:if test="${loop.index % 3 == 2 or loop.last}">
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:if>

</section>

<jsp:include page="../include/footer.jsp"/>
