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
                                <a href="${pageContext.request.contextPath}/views/user/home.jsp" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <a href="${pageContext.request.contextPath}/views/user/shop.jsp" class="cart text-decoration-none">
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
                        <%-- <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item waves-effect waves-light">
                                <a class="nav-link active" id="first-tab" data-toggle="tab" data-target="#first" role="tab" aria-controls="first" aria-selected="false"><figure class="auction-img mb-0"><img class="img-fluid" src="${pageContext.request.contextPath}/static/img/product1-sm-image1.jpg" alt=""></figure></a>
                            </li>
                            <li class="nav-item waves-effect waves-light">
                                <a class="nav-link" id="second-tab" data-toggle="tab" data-target="#second" role="tab" aria-controls="second" aria-selected="false"><figure class="auction-img mb-0"><img class="img-fluid" src="${pageContext.request.contextPath}/static/img/product1-sm-image2.jpg" alt=""></figure></a>
                            </li>
                            <li class="nav-item waves-effect waves-light">
                                <a class="nav-link" id="third-tab" data-toggle="tab" data-target="#third" role="tab" aria-controls="third" aria-selected="true"><figure class="auction-img mb-0"><img class="img-fluid" src="${pageContext.request.contextPath}/static/img/product1-sm-image3.jpg" alt=""></figure></a>
                            </li>
                            <li class="nav-item waves-effect waves-light">
                                <a class="nav-link" id="fourth-tab" data-toggle="tab" data-target="#fourth" role="tab" aria-controls="fourth" aria-selected="true"><figure class="auction-img mb-0"><img class="img-fluid" src="${pageContext.request.contextPath}/static/img/product1-sm-image4.jpg" alt=""></figure></a>
                            </li>
                        </ul> --%>
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane tab-pane2 fade active show" id="first" role="tabpanel" aria-labelledby="first-tab">
                                <figure class="auction-img mb-0">
                                    <img class="img-fluid" src="${pageContext.request.contextPath}/static/img/product1-image1.jpg" alt="">
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
                        <h4>Classic Vanilla Ice Cream</h4>
                        <span class="price">$5.99</span>
                        <p class="text-size-16">Neque porro ruisquam est aui dolorem iesum ruia do sit amet consectetur, adipisci velit, sed quia non num eius modi 
                            tempoa incidunt ut labore et dolore magna.                    
                        </p>
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
                    <li><a class="active" data-toggle="tab" data-target="#description">Description</a></li>
                </ul>
                <div class="tab-content">
                    <div id="description" class="tab-pane fade in active show">
                        <div class="row" data-aos="fade-up">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="description_content">
                                    <p class="text-size-16">Ratione volurtatem serui nesciunt neaue porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur,
                                        adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam
                                        corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur.
                                    </p>
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