<header class="header">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light p-0">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                <figure class="logo mb-0"><img src="${pageContext.request.contextPath}/static/img/logo.png" alt="image" class="img-fluid"></figure>
            </a>
            <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    <span class="navbar-toggler-icon"></span>
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/shop">Shop</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/profile">My Profile</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle dropdown-color navbar-text-color" href="#"
                                id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false"> Pages </a>
                            <div class="dropdown-menu drop-down-content">
                                <ul class="list-unstyled drop-down-pages">
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="team.html">Team</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="review.html">Review</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="shop1.html">Shop 1</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="shop2.html">Shop 2</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="shop3.html">Shop 3</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="single-product1.html">Single Product 1</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="single-product2.html">Single Product 2</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="single-product3.html">Single Product 3</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="cart.html">Cart</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="checkout.html">Checkout</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="special-offer.html">Special Offers</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="404.html">404</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="coming-soon.html">Coming Soon</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="privacy-policy.html">Privacy Policy</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="terms-of-conditions.html">Terms & Conditions</a></li>
                                    <li class="nav-item"><a class="dropdown-item nav-link" href="thank-you.html">Thank You</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                <div class="last_list">
                    <a class="text-decoration-none search-box search icon" href="#search"><img src="${pageContext.request.contextPath}/static/img/header-magnifyingglass.png" alt="image" class="img-fluid"></a>
                    <a class="text-decoration-none cart icon position-relative" href="${pageContext.request.contextPath}/cart"><img src="${pageContext.request.contextPath}/static/img/header-cart.png" alt="image" class="img-fluid"><span id="cartIcon">${cart.size()}</span></a>
                    <a class="text-decoration-none contact_us" href="${pageContext.request.contextPath}/views/user/login.jsp">Sign in<i class="fa-solid fa-arrow-right"></i></a>
                </div>
            </div>
        </nav>
    </div>
</header>
<!-- Search Form -->
<div id="search" class=""> 
    <span class="close">X</span>
    <form role="search" id="searchform" method="get">
        <input value="" name="q" type="search" placeholder="Type to Search">
    </form>
</div>