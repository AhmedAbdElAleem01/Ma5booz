<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Font & Icon Links -->
<link href="https://fonts.googleapis.com/css2?family=Fredoka:wght@400;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/nav.css">

<header class="header">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light p-0">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                <figure class="logo mb-0"><img src="${pageContext.request.contextPath}/static/img/testlogo.png" alt="image" class="img-fluid"></figure>
            </a>
            <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home"><i class="fa fa-home" style="color: #ff69b4;"></i> <span style="color: #880E4F;">Home</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/shop"><i class="fa fa-store" style="color: #ff69b4;"></i> <span style="color: #880E4F;">Shop</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/profile"><i class="fa fa-user" style="color: #ff69b4;"></i> <span style="color: #880E4F;">My Profile</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/profile"><i class="fa fa-user" style="color: #ff69b4;"></i> My Profile</a></li>
                </ul>
            </div>
            <div class="last_list d-flex align-items-center">
                <a class="text-decoration-none cart icon position-relative" href="${pageContext.request.contextPath}/cart">
                    <i class="fa fa-shopping-cart fa-lg" style="color: #ff69b4; font-size: 1.45em;"></i>
                    <span id="cartIcon">${empty cart ? 0 : cart.size()}</span>
                </a>
                <% Object user = session.getAttribute("user"); if (user != null) { %>
                    <a class="text-decoration-none contact_us ml-3" href="${pageContext.request.contextPath}/logout">
                        Log out <i class="fa-solid fa-arrow-right"></i>
                    </a>
                <% } else { %>
                    <a class="text-decoration-none contact_us ml-3" href="${pageContext.request.contextPath}/views/user/login.jsp">
                        Sign in <i class="fa-solid fa-arrow-right"></i>
                    </a>
                <% } %>
            </div>
        </nav>
    </div>
</header>
