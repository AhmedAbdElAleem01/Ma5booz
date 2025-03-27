<html>
<head>
    <title>Shop | IceDelights</title>
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
                            <h1>Shop</h1>
                            <div class="box">
                                <a href="${pageContext.request.contextPath}/views/user/home.jsp" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <span class="mb-0 box_span">Shop</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- Shop -->
    <section class="shop1-con classic-con position-relative">
        <div class="container">
            <div class="row">
                <div class="sidebar sticky-sidebar col-lg-3">
                    <div class="theiaStickySidebar">
                        <div class="widget widget-newsletter" data-aos="fade-up">
                            <form id="widget-search-form-sidebar" class="form-inline">
                                <div class="input-group">
                                    <input type="text" aria-required="true" name="q"
                                        class="form-control widget-search-form" placeholder="Search">
                                    <div class="input-group-append">
                                        <span class="input-group-btn">
                                            <button type="submit" id="widget-widget-search-form-button" class="btn"><i
                                                    class="fa fa-search"></i></button>
                                        </span>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="widget widget-categories" data-aos="fade-up">
                            <div class="widget-title font_weight_600">Categories</div>
                            <ul class="list-unstyled mb-0">
                                <li class="cat-item">
                                    <a href="${pageContext.request.contextPath}/views/user/cart.jsp">Ice Cream(2)</a>
                                </li>
                                <li class="cat-item">
                                    <a href="${pageContext.request.contextPath}/views/user/cart.jsp">Seasonal Flavors(2)</a>

                                </li>
                                <li class="cat-item">
                                    <a href="${pageContext.request.contextPath}/views/user/cart.jsp">Cakes(1)</a>

                                </li>
                                <li class="cat-item">
                                    <a href="${pageContext.request.contextPath}/views/user/cart.jsp">Cupcakes(3)</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="row default-sorting-con">
                        <div class="col-12">
                            <div class="top-icons" data-aos="fade-up">
                                <div id="toolbar">
                                    <select class="form-control">
                                        <option value="">Default Sorting</option>
                                        <option value="all">Cakes</option>
                                        <option value="selected">Shakes</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row shop-products-con" data-aos="fade-up">
                        <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                            <div class="classic-box">
                                <a href="${pageContext.request.contextPath}/views/user/productDetails.jsp">
                                    <div class="classic_image_box box1">
                                        <figure class="mb-0">
                                            <img src="${pageContext.request.contextPath}/static/img/vanilla_icecream.png" alt="image" class="img-fluid">
                                        </figure>
                                    </div>
                                </a>
                                <div class="classic_box_content">
                                    <div class="text_wrapper position-relative">
                                        <h6>Classic Vanilla Ice Cream</h6>
                                        <div class="rating">
                                            <i class="fa-solid fa-star"></i>
                                            <span>4.9/5</span>
                                        </div>
                                    </div>
                                    <p class="text-size-16">Creamy vanilla ice cream topped with cherry.</p>
                                    <div class="price_wrapper position-relative">
                                        <span class="dollar">$<span class="counter">4</span><span>.99</span></span>
                                        <a href="${pageContext.request.contextPath}/views/user/cart.jsp"><img src="${pageContext.request.contextPath}/static/img/cart.png" alt="image" class="img-fluid"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                            <div class="classic-box">
                                <a href="${pageContext.request.contextPath}/views/user/productDetails.jsp">
                                    <div class="classic_image_box box2">
                                        <figure class="mb-0">
                                            <img src="${pageContext.request.contextPath}/static/img/chocolate_cake.jpg" alt="image" class="img-fluid">
                                        </figure>
                                    </div>
                                </a>
                                <div class="classic_box_content">
                                    <div class="text_wrapper position-relative">
                                        <h6>Chocolate Cake</h6>
                                        <div class="rating">
                                            <i class="fa-solid fa-star"></i>
                                            <span>4.9/5</span>
                                        </div>
                                    </div>
                                    <p class="text-size-16">Rich chocolate-flavored cake.</p>
                                    <div class="price_wrapper position-relative">
                                        <span class="dollar">$<span class="counter">5</span><span>.49</span></span>
                                        <a href="${pageContext.request.contextPath}/views/user/cart.jsp"><img src="${pageContext.request.contextPath}/static/img/cart.png" alt="image" class="img-fluid"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                            <div class="classic-box">
                                <a href="${pageContext.request.contextPath}/views/user/productDetails.jsp">
                                    <div class="classic_image_box box3">
                                        <figure class="mb-0">
                                            <img src="${pageContext.request.contextPath}/static/img/vanilla_cupcake.jpg" alt="image" class="img-fluid">
                                        </figure>
                                    </div>
                                </a>
                                <div class="classic_box_content">
                                    <div class="text_wrapper position-relative">
                                        <h6>Vanilla Cupcake</h6>
                                        <div class="rating">
                                            <i class="fa-solid fa-star"></i>
                                            <span>4.9/5</span>
                                        </div>
                                    </div>
                                    <p class="text-size-16">Cupcake with vanilla frosting</p>
                                    <div class="price_wrapper position-relative">
                                        <span class="dollar">$<span class="counter">5</span><span>.29</span></span>
                                        <a href="${pageContext.request.contextPath}/views/user/cart.jsp"><img src="${pageContext.request.contextPath}/static/img/cart.png" alt="image" class="img-fluid"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                            <div class="classic-box">
                                <a href="${pageContext.request.contextPath}/views/user/productDetails.jsp">
                                    <div class="classic_image_box box4">
                                        <figure class="mb-0">
                                            <img src="${pageContext.request.contextPath}/static/img/red_velvet_cupcake.jpg" alt="image" class="img-fluid">
                                        </figure>
                                    </div>
                                </a>
                                <div class="classic_box_content">
                                    <div class="text_wrapper position-relative">
                                        <h6>Red Velvet Cupcake</h6>
                                        <div class="rating">
                                            <i class="fa-solid fa-star"></i>
                                            <span>4.9/5</span>
                                        </div>
                                    </div>
                                    <p class="text-size-16">Fluffy red velvet cupcakes topped with cream cheese.</p>
                                    <div class="price_wrapper position-relative">
                                        <span class="dollar">$<span class="counter">3</span><span>.99</span></span>
                                        <a href="${pageContext.request.contextPath}/views/user/cart.jsp"><img src="${pageContext.request.contextPath}/static/img/cart.png" alt="image" class="img-fluid"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                            <div class="classic-box">
                                <a href="${pageContext.request.contextPath}/views/user/productDetails.jsp">
                                    <div class="classic_image_box box5">
                                        <figure class="mb-0">
                                            <img src="${pageContext.request.contextPath}/static/img/bluberry_cupcake.jpg" alt="image" class="img-fluid">
                                        </figure>
                                    </div>
                                </a>
                                <div class="classic_box_content">
                                    <div class="text_wrapper position-relative">
                                        <h6>Bluberry Cupcake</h6>
                                        <div class="rating">
                                            <i class="fa-solid fa-star"></i>
                                            <span>4.9/5</span>
                                        </div>
                                    </div>
                                    <p class="text-size-16">Cupcake with blueberry frosting</p>
                                    <div class="price_wrapper position-relative">
                                        <span class="dollar">$<span class="counter">5</span><span>.99</span></span>
                                        <a href="${pageContext.request.contextPath}/views/user/cart.jsp"><img src="${pageContext.request.contextPath}/static/img/cart.png" alt="image" class="img-fluid"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                            <div class="classic-box">
                                <a href="${pageContext.request.contextPath}/views/user/productDetails.jsp">
                                    <div class="classic_image_box box6">
                                        <figure class="mb-0">
                                            <img src="${pageContext.request.contextPath}/static/img/chocolate_chip.png" alt="image" class="img-fluid">
                                        </figure>
                                    </div>
                                </a>
                                <div class="classic_box_content">
                                    <div class="text_wrapper position-relative">
                                        <h6>Chocolate Chip Cookie Cone</h6>
                                        <div class="rating">
                                            <i class="fa-solid fa-star"></i>
                                            <span>4.9/5</span>
                                        </div>
                                    </div>
                                    <p class="text-size-16">Chocolate chip cookie dough ice cream in a cone.</p>
                                    <div class="price_wrapper position-relative">
                                        <span class="dollar">$<span class="counter">4</span><span>.49</span></span>
                                        <a href="${pageContext.request.contextPath}/views/user/cart.jsp"><img src="${pageContext.request.contextPath}/static/img/cart.png" alt="image" class="img-fluid"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <ul class="pagination" data-aos="fade-up">
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item next">
                                <a class="page-link" href="#"><i class="fas fa-angle-right"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Footer -->
    <%@ include file="footer.jsp"%>
</body>
</html>