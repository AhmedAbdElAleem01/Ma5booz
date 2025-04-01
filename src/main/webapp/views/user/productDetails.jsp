<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Product Details| IceDelights</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="sub_banner_outer position-relative">
        <%@ include file="navbar.jsp"%>
        <!-- Sub Banner -->
        <section class="sub_banner position-relative">
            <div class="container position-relative">
                <div class="row">
                    <div class="col-12">
                        <div class="sub_banner_content text-center" data-aos="fade-up">
                            <h1>Product Details</h1>
                            <div class="box">
                                <a href="${pageContext.request.contextPath}/home" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <a href="${pageContext.request.contextPath}/shop" class="cart text-decoration-none">
                                    <span class="mb-0">Shop</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <span class="mb-0 box_span">Product Detail</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- Types -->
    <section class="types-con">
        <div class="container">
            <div class="row">
                <div class="col-lg-7 col-12">
                    <div class="product-tab" data-aos="fade-up">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane tab-pane2 fade active show" id="first" role="tabpanel" aria-labelledby="first-tab">
                                <figure class="auction-img mb-0">
                                    <img class="img-fluid" src="${pageContext.request.contextPath}/static/img/${product.imageUrl}" alt="${product.name}">
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-12">
                    <div class="types_content" data-aos="fade-up">
                        <div class="rating">
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <i class="fa-solid fa-star"></i>
                            <span>(4.9/5)</span>
                        </div>
                        <c:if test="${not empty product}">
                            <h4>${product.name}</h4>
                            <span class="price">"$" ${product.price}</span>
                            <p class="text-size-16">${product.description}</p>
                            <c:choose>
                                <c:when test="${product.stockQuantity == 0}">
                                    <p style="color: red;">Out of Stock</p>
                                </c:when>
                                <c:when test="${product.stockQuantity < 10}">
                                    <p style="color: red;">Only ${product.stockQuantity} left in stock</p>
                                </c:when>
                                <c:otherwise>
                                    <p>In Stock</p>
                                </c:otherwise>
                            </c:choose>

                        </c:if>
                       
                        
                        <div class="colors">
                            <span class="heading">Color:</span>
                            <ul class="list-unstyled mb-0">
                                <li class="red"></li>
                                <li class="orange"></li>
                                <li class="green"></li>
                                <li class="brown"></li>
                            </ul>
                        </div>
                        <div class="sizes">
                            <span class="heading">Size:</span>
                            <ul class="list-unstyled mb-0">
                                <li>L</li>
                                <li>M</li>
                                <li>S</li>
                            </ul>
                        </div>
                        <div class="quatity_button_wrapper">
                            <div class="quantity-field">
                                <button class="value-button decrease-button" onclick="decreaseValue(this)" title="">-</button>
                                <div class="number">1</div>
                                <button class="value-button increase-button" onclick="increaseValue(this)" title="">+</button>
                            </div>                        
                            <a href="${pageContext.request.contextPath}/views/user/cart.jsp" class="text-decoration-none all_button">Add to Cart<i class="fa-solid fa-arrow-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- MORE INFORMATION SECTION -->
    <section class="more_information_section">
        <div class="container">
            <div class="tabs-box tabs-options">
                <ul class="nav nav-tabs" data-aos="fade-up">
                    <li><a class="active" data-toggle="tab" data-target="#description">Ingredients</a></li>
                </ul>
                <div class="tab-content">
                    <div id="description" class="tab-pane fade in active show">
                        <div class="row" data-aos="fade-up">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="description_content">
                                    <p class="text-size-16">${product.description}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Footer -->
    <%@ include file="footer.jsp"%>
</body>
</html>