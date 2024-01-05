<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp"/>
<section>

<section>
    <div class="bg-light2 pt-5 pb-5">
        <div class="row">
            <div class="col-12 text-center">
                <h1 class="m-0">Create Car</h1>
            </div>
        </div>
    </div>
</section

<section class="pt-5 pb-5">
    <div class="container">
        <c:if test="${not empty success}">
            <div class="row justify-content-center">
                <div class="col-6 text-center">
                    <div class="alert alert-success" role="alert">
                            ${success}
                    </div>
                </div>
            </div>
        </c:if>

          <form method="get" action="/car/createSubmit">
             <input type="hidden" name="id" value="${form.id}">

                     <div class="mt-3">
                         <label for="category" class="form-label">Category</label>
                         <input type="text" class="form-control" id="category" name="category" value="${form.category}">
                     </div>
                     <c:if test="${errors.hasFieldErrors('category')}">
                                     <div style="color:red">
                                         <c:forEach items="${errors.getFieldErrors('category')}" var="error">
                                             ${error.defaultMessage}<br>
                                         </c:forEach>
                                     </div>
                                 </c:if>

                     <div class="mt-3">
                         <label for="model" class="form-label">Model</label>
                         <input type="text" class="form-control" id="model" name="model" value="${form.model}">
                     </div>
                     <c:if test="${errors.hasFieldErrors('model')}">
                                     <div style="color:red">
                                         <c:forEach items="${errors.getFieldErrors('model')}" var="error">
                                             ${error.defaultMessage}<br>
                                         </c:forEach>
                                     </div>
                                 </c:if>

                     <div class="mt-3">
                         <label for="year" class="form-label">Year</label>
                         <input type="text" class="form-control" id="year" name="year" value="${form.year}">
                     </div>
                     <c:if test="${errors.hasFieldErrors('year')}">
                                     <div style="color:red">
                                         <c:forEach items="${errors.getFieldErrors('year')}" var="error">
                                             ${error.defaultMessage}<br>
                                         </c:forEach>
                                     </div>
                                 </c:if>

                     <div class="mt-3">
                         <label for="price" class="form-label">Price</label>
                         <input type="text" class="form-control" id="price" name="price" value="${form.price}">
                     </div><c:if test="${errors.hasFieldErrors('price')}">
                                           <div style="color:red">
                                               <c:forEach items="${errors.getFieldErrors('price')}" var="error">
                                                   ${error.defaultMessage}<br>
                                               </c:forEach>
                                           </div>
                                       </c:if>
                     <div class="mt-3">
                            <label for="imageUrl" class="form-label">Image Url</label>
                            <input type="text" class="form-control" id="imageUrl" name="imageUrl" value="${form.imageUrl}">
                           </div>

                     <button type="submit" class="btn btn-primary">Submit</button>
          </form>
        </div>
</section>

<jsp:include page="../include/footer.jsp"/>