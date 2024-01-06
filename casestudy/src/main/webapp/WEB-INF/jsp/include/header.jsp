<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AWESOME CAR DEALER</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Tangerine">

     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.0/css/all.min.css" integrity="sha384-xzvREUJp8waJkj66B1QlAv2sM8cc5pRYHzAwR7H3fnzBYKgn0RRqFIJ5cRbz5Z9T" crossorigin="anonymous">

    <link href="/pub/css/global-style.css" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-lg text-center ">
    <div class="container-fluid " >
        <a class="navbar-brand" href="/">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <sec:authorize access="hasAnyAuthority('ADMIN')">
                 <li class="nav-item">
                   <a class="nav-link" href="/car/create">Create Car</a>
                  </li>
                 </sec:authorize>
                <li class="nav-item">
                     <a class="nav-link" href="/car/search">Search Car</a>
                </li>
                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/auth/register">User Registration</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/auth/login">Login</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/index">Admin</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="/auth/logout">Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href=""><sec:authentication property="principal.username" /></a>
                    </li>
                   <li class="nav-item">
                       <a class="nav-link" href="/order/viewCart">
                           <i class="fas fa-shopping-cart"></i>Cart
                       </a>
                   </li>

                </sec:authorize>

                <li class="nav-item">
                    <a class="nav-link" href="/admin/index">Secured Request</a>
                </li>
            </ul>

        </div>
    </div>
</nav>