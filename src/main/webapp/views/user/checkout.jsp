<html>
<head>
    <title>Checkout | IceDelights</title>
</head>
<body>
    <%@ include file="header.jsp"%>

    <div class="sub_banner_outer position-relative">
        <%@ include file="navbar.jsp"%>
        <%-- Sub Banner  --%>
        <section class="sub_banner position-relative">
            <div class="container position-relative">
                <div class="row">
                    <div class="col-12">
                        <div class="sub_banner_content text-center" data-aos="fade-up">
                            <h1>Checkout</h1>
                            <div class="box">
                                <a href="${pageContext.request.contextPath}/views/user/home.jsp" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <a href="${pageContext.request.contextPath}/views/user/cart.jsp" class="cart text-decoration-none">
                                    <span class="mb-0">Cart</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <span class="mb-0 box_span">Checkout</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <%-- Checkout  --%>
    <div class="cart-con checkout-con position-relative">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-12">
                    <div class="product-detail-box">
                        <form id="contactpage" method="post" action="${pageContext.request.contextPath}/views/user/confirmation.jsp" class="position-relative">
                            <div class="upper-form" data-aos="fade-up">
                                <span class="address">Billing Address:</span>
                                <div class="form-group input1 float-left">
                                    <label for="fname">First name</label>
                                    <input type="text" class="form_style" name="fname" id="fname">
                                </div>
                                <div class="form-group float-left">
                                    <label for="lname">Last name</label>
                                    <input type="text" class="form_style" name="lname" id="lname">
                                </div>
                                <div class="form-group input1 float-left">
                                    <label for="email">Email address</label>
                                    <input type="email" class="form_style" name="email" id="email">
                                </div>
                                <div class="form-group float-left">
                                    <label>State</label>
                                    <select class="form-control" required="">
                                        <option value="" disabled="" selected="" hidden="">Select State</option>
                                        <option value="California">California</option>
                                        <option value="Texas">Texas</option>
                                        <option value="Florida">Florida</option>
                                    </select>
                                </div>
                                <div class="form-group input1 float-left">
                                    <label>City</label>
                                    <select class="form-control" required="">
                                        <option value="" disabled="" selected="" hidden="">Select City</option>
                                        <option value="Los Angeles">Los Angeles</option>
                                        <option value="San Francisco">San Francisco</option>
                                        <option value="San Diego">San Diego</option>
                                    </select>
                                </div>
                                <div class="form-group float-left">
                                    <label for="code">Zip/ postal code</label>
                                    <input type="text" class="form_style" name="code" id="code">
                                </div>
                            </div>
                            <div class="lower-form" data-aos="fade-up">
                                <span class="address">Payment Method:</span>
                                <div class="form-group float-left checkbox">
                                    <input type="checkbox" id="card">
                                    <label for="card">Credit card</label>
                                    <img src="${pageContext.request.contextPath}/static/img/checkout-cardimage.png" alt="image" class="img-fluid card">
                                </div>
                                <div class="form-group input2">
                                    <label for="cnumber">Card number</label>
                                    <input type="number" class="form_style" name="cnumber" id="cnumber">
                                </div>
                                <div class="form-group dates input1 float-left">
                                    <label>Expiration date</label>
                                    <select class="form-control input3 float-left" required="">
                                        <option value="" disabled="" selected="" hidden="">Month</option>
                                        <option value="January">January</option>
                                        <option value="February">February</option>
                                        <option value="March">March</option>
                                        <option value="April">April</option>
                                        <option value="May">May</option>
                                        <option value="June">June</option>
                                        <option value="July">July</option>
                                        <option value="August">August</option>
                                        <option value="September">September</option>
                                        <option value="October">October</option>
                                        <option value="November">November</option>
                                        <option value="December">December</option>
                                    </select>
                                    <select class="form-control input4 float-left" required="">
                                        <option value="" disabled="" selected="" hidden="">Year</option>
                                        <option value="2013">2013</option>
                                        <option value="2014">2014</option>
                                        <option value="2015">2015</option>
                                        <option value="2016">2016</option>
                                        <option value="2017">2017</option>
                                        <option value="2018">2018</option>
                                        <option value="2019">2019</option>
                                        <option value="2020">2020</option>
                                        <option value="2021">2021</option>
                                        <option value="2022">2022</option>
                                        <option value="2023">2023</option>
                                        <option value="2024">2024</option>
                                    </select>
                                </div>
                                <div class="form-group float-left">
                                    <label for="scord">Security Code</label>
                                    <input type="text" class="form_style" name="scord" id="scord">
                                </div>
                                <div class="form-group float-left dates checkbox">
                                    <input type="checkbox" id="cash">
                                    <label for="cash">Cash on Delivery</label>
                                    <i class="fa-solid fa-money-bill-wave"></i>
                                </div>
                                <p class="text-center text-size-14">By clicking the button, you agree to the <a href="terms-of-conditions.html" class="text-decoration-none terms-btn">Terms and Conditions</a></p>
                            </div>
                            <button data-aos="fade-up" type="submit" id="submit" class="submit_now text-decoration-none">Place Order<i class="fa-solid fa-arrow-right"></i></button>
                        </form>
                    </div>
                </div>

                <%-- Order Section --%>
                <div class="col-lg-4 col-12">
                    <div class="cart-total-outer" data-aos="fade-up">
                        <div class="top-heading">
                            <span class="product-items">Order</span>
                            <span class="product-prices">Price</span>
                        </div>
                        <div class="list-items">
                            <div class="each-item">
                                <div class="product-items">
                                    <span class="heading">3 x Classic Vanilla</span>
                                    <p class="text-size-14 mb-0">Creamy vanilla ice cream topped with cherry.</p>
                                </div>
                                <div class="product-prices">
                                    <span class="dollar">$13.00</span>
                                </div>
                            </div>
                            <div class="each-item">
                                <div class="product-items">
                                    <span class="heading">6 x Chocolate Brownie</span>
                                    <p class="text-size-14 mb-0">Rich chocolate ice cream with chunks of brownie.</p>
                                </div>
                                <div class="product-prices">
                                    <span class="dollar">$23.00</span>
                                </div>
                            </div>
                            <div class="each-item">
                                <div class="product-items">
                                    <span class="heading">4 x Stawberry Cake</span>
                                    <p class="text-size-14 mb-0">Strawberry ice cream layered with shortcake</p>
                                </div>
                                <div class="product-prices">
                                    <span class="dollar">$22.00</span>
                                </div>
                            </div>
                            <div class="each-item">
                                <div class="product-items">
                                    <span class="heading">2 x Mint Chocolate</span>
                                    <p class="text-size-14 mb-0">Refreshing mint ice cream with chocolate chips.</p>
                                </div>
                                <div class="product-prices">
                                    <span class="dollar">$07.00</span>
                                </div>
                            </div>
                            <div class="each-item">
                                <div class="product-items">
                                    <span class="heading">Grand Total</span>
                                </div>
                                <div class="product-prices">
                                    <span class="dollar total-price">$83.99</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- End Order  --%>
            </div>
        </div>
    </div>
    <%-- End Checkout --%>

    <%@ include file="footer.jsp"%>
</body>
</html>