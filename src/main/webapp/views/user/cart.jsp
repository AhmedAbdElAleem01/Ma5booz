<html>
<head>
    <title>Shopping Cart | IceDelights</title>
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
                        <h1>Shopping Cart</h1>
                        <div class="box">
                            <a href="${pageContext.request.contextPath}/views/user/home.jsp" class="text-decoration-none">
                                <span class="mb-0">Home</span>
                            </a>
                            <span class="mb-0 slash">/</span>
                            <span class="mb-0 box_span">Cart</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Cart -->
    <div class="cart-con position-relative">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-12">
                    <div class="product-detail-box" data-aos="fade-up">
                        <div class="heading">
                            <span>Shopping Cart</span>
                            <span>(04 Items)</span>
                        </div>
                        <div class="shopping-cart">
                            <div class="column-labels">
                                <label class="product-details">Product Details</label>
                                <label class="product-price">Price</label>
                                <label class="product-quantity">Quantity</label>
                                <label class="product-line-price">Total</label>
                                <label class="product-removal"></label>
                            </div>
                            <div class="shopping-cart-info">
                                <div class="product d-sm-flex d-block align-items-center">
                                    <div class="product-details">
                                        <div class="product-image box1">
                                            <figure class="mb-0">
                                                <img src="${pageContext.request.contextPath}/static/img/cart-image1.png" alt="image" class="img-fluid">
                                            </figure>
                                        </div>
                                        <div class="product-content">
                                            <span class="product-title">Classic Vanilla</span>
                                            <span class="product-color text">Color: <span>White</span></span>
                                            <span class="product-size text mb-0">Size: <span>L</span></span>
                                        </div>
                                    </div>
                                    <div class="product-price"><span>4.99$</span></div>
                                    <div class="product-quantity d-flex">
                                        <div class="product-qty-details">
                                            <button class="value-button decrease-button" onclick="decreaseValue(this)" title="">-</button>
                                            <div class="number">3</div><button class="value-button increase-button" onclick="increaseValue(this)" title="">+</button>
                                        </div>
                                    </div>
                                    <div class="product-line-price"><span>13.00</span></div>
                                    <div class="product-removal">
                                        <button class="remove-product"><i class="fas fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="product d-sm-flex d-block align-items-center">
                                    <div class="product-details">
                                        <div class="product-image box2">
                                            <figure class="mb-0">
                                                <img src="${pageContext.request.contextPath}/static/img/cart-image2.png" alt="image" class="img-fluid">
                                            </figure>
                                        </div>
                                        <div class="product-content">
                                            <span class="product-title">Chocolate Brownie</span>
                                            <span class="product-color text">Color: <span>Brown</span></span>
                                            <span class="product-size text mb-0">Size: <span>S</span></span>
                                        </div>
                                    </div>
                                    <div class="product-price"><span>5.49$</span></div>
                                    <div class="product-quantity d-flex">
                                        <div class="product-qty-details">
                                            <button class="value-button decrease-button" onclick="decreaseValue(this)" title="">-</button>
                                            <div class="number">6</div><button class="value-button increase-button" onclick="increaseValue(this)" title="">+</button>
                                        </div>
                                    </div>
                                    <div class="product-line-price"><span>23.00</span></div>
                                    <div class="product-removal">
                                        <button class="remove-product"><i class="fas fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="product d-sm-flex d-block align-items-center">
                                    <div class="product-details">
                                        <div class="product-image box3">
                                            <figure class="mb-0">
                                                <img src="${pageContext.request.contextPath}/static/img/cart-image3.png" alt="image" class="img-fluid">
                                            </figure>
                                        </div>
                                        <div class="product-content">
                                            <span class="product-title">Stawberry Cake</span>
                                            <span class="product-color text">Color: <span>Red</span></span>
                                            <span class="product-size text mb-0">Size: <span>M</span></span>
                                        </div>
                                    </div>
                                    <div class="product-price"><span>5.29$</span></div>
                                    <div class="product-quantity d-flex">
                                        <div class="product-qty-details">
                                            <button class="value-button decrease-button" onclick="decreaseValue(this)" title="">-</button>
                                            <div class="number">4</div><button class="value-button increase-button" onclick="increaseValue(this)" title="">+</button>
                                        </div>
                                    </div>
                                    <div class="product-line-price"><span>22.00</span></div>
                                    <div class="product-removal">
                                        <button class="remove-product"><i class="fas fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="product d-sm-flex d-block align-items-center">
                                    <div class="product-details">
                                        <div class="product-image box4">
                                            <figure class="mb-0">
                                                <img src="${pageContext.request.contextPath}/static/img/cart-image4.png" alt="image" class="img-fluid">
                                            </figure>
                                        </div>
                                        <div class="product-content">
                                            <span class="product-title">Mint Chocolate</span>
                                            <span class="product-color text">Color: <span>Green</span></span>
                                            <span class="product-size text mb-0">Size: <span>L</span></span>
                                        </div>
                                    </div>
                                    <div class="product-price"><span>3.99$</span></div>
                                    <div class="product-quantity d-flex">
                                        <div class="product-qty-details">
                                            <button class="value-button decrease-button" onclick="decreaseValue(this)" title="">-</button>
                                            <div class="number">2</div><button class="value-button increase-button" onclick="increaseValue(this)" title="">+</button>
                                        </div>
                                    </div>
                                    <div class="product-line-price"><span>7.00</span></div>
                                    <div class="product-removal">
                                        <button class="remove-product"><i class="fas fa-times"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="buttun-shopping">
                            <a href="${pageContext.request.contextPath}/views/user/shop.jsp" class="text-decoration-none"><i class="fa-solid fa-arrow-left"></i>Continue Shopping</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-12">
                    <div class="cart-total-outer" data-aos="fade-up">
                        <h4 class="mb-0">Order Summary</h4>
                        <div class="coupon">
                            <div class="ticket">
                                <i class="fa-solid fa-ticket"></i>
                                <span class="heading">Apply Coupons</span>
                            </div>
                            <a href="cart.html" class="text-decoration-none">Apply</a>
                        </div>
                        <div class="detail">
                            <span class="heading">Product Details:</span>
                            <ul class="list-unstyled mb-0">
                                <li><span>Sub Total</span><span class="dollar">$63.99</span></li>
                                <li><span>Shipping</span><span class="dollar">$20.00</span></li>
                            </ul>
                            <div class="all-total">
                                <div class="total">
                                    <span class="text">Grand Total</span><span class="dollar">$89.99</span>
                                </div>
                                <a href="${pageContext.request.contextPath}/views/user/checkout.jsp" class="text-decoration-none all_button">Proceed to checkout<i class="fa-solid fa-arrow-right"></i></a>
                            </div>
                            <div class="note">
                                <i class="fa-solid fa-shield-halved"></i>
                                <span class="note-text">Safe and Secure Payments, Easy Returns. 100% Authentic Products</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer -->
    <%@ include file="footer.jsp"%>
</body>
</html>