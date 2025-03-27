<html>
<head>
    <title>Sign up | IceDelights</title>
</head>
<body>
    <%@ include file="header.jsp"%>

    <div class="sub_banner_outer position-relative">
        <%@ include file="navbar.jsp"%>
        <section class="sub_banner position-relative">
            <div class="container position-relative">
                <div class="row">
                    <div class="col-12">
                        <div class="sub_banner_content text-center" data-aos="fade-up">
                            <h1>Sign up</h1>
                            <div class="box">
                                <a href="${pageContext.request.contextPath}/views/user/home.jsp" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <span class="mb-0 box_span">Sign up</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <%-- Sign up --%>
    <div class="cart-con checkout-con position-relative">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-12">
                    <div class="product-detail-box">
                        <form id="contactpage" method="post" action="${pageContext.request.contextPath}/views/user/confirmation.jsp" class="position-relative">
                            <div class="upper-form" data-aos="fade-up">
                                <span class="address">Personal Information:</span>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="fname">First Name</label>
                                        <input type="text" class="form-control" name="fname" id="fname" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="lname">Last Name</label>
                                        <input type="text" class="form-control" name="lname" id="lname" required>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <label for="birthdate">Birth Date</label>
                                        <input type="date" class="form-control" name="birthdate" id="birthdate" required>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-6">
                                        <label for="job">Job</label>
                                        <input type="text" class="form-control" name="job" id="job">
                                    </div>
                                </div>
                                <span class="address mt-4">Account Information:</span>
                                <div class="column">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="email">Email Address</label>
                                            <input type="email" class="form-control" name="email" id="email" required>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="password">Password</label>
                                            <input type="password" class="form-control" name="password" id="password" required>
                                        </div>
                                    </div>
                                    <div class="col-12 mt-3">
                                        <label for="creditLimit">Credit Limit</label>
                                        <input type="number" class="form-control" name="creditLimit" id="creditLimit" required>
                                    </div>
                                </div>
                            </div>

                            <div class="lower-form mt-4" data-aos="fade-up">
                                <span class="address">Address:</span>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Country</label>
                                        <select class="form-control" name="country" required>
                                            <option value="" disabled selected hidden>Select country</option>
                                            <option value="Cairo">Cairo</option>
                                            <option value="Giza">Giza</option>
                                            <option value="Alexandria">Alexandria</option>
                                            <option value="Dakahlia">Dakahlia</option>
                                            <option value="Sharqia">Sharqia</option>
                                            <option value="Qalyubia">Qalyubia</option>
                                            <option value="Minya">Minya</option>
                                            <option value="Sohag">Sohag</option>
                                            <option value="Fayoum">Fayoum</option>
                                            <option value="Asyut">Asyut</option>
                                            <option value="Ismailia">Ismailia</option>
                                            <option value="Suez">Suez</option>
                                            <option value="Luxor">Luxor</option>
                                            <option value="Aswan">Aswan</option>
                                            <option value="Port Said">Port Said</option>
                                            <option value="Beheira">Beheira</option>
                                            <option value="Beni Suef">Beni Suef</option>
                                            <option value="Kafr El Sheikh">Kafr El Sheikh</option>
                                            <option value="Red Sea">Red Sea</option>
                                            <option value="New Valley">New Valley</option>
                                            <option value="Matrouh">Matrouh</option>
                                            <option value="North Sinai">North Sinai</option>
                                            <option value="South Sinai">South Sinai</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="city">City</label>
                                        <input type="text" class="form-control" name="city" id="city">
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-6">
                                        <label for="street">Street</label>
                                        <input type="text" class="form-control" name="street" id="street">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="BNo">Building Number</label>
                                        <input type="text" class="form-control" name="BNo" id="BNo">
                                    </div>
                                </div>
                                <span class="address">Interests:</span>
                                <div class="form-group float-left dates checkbox">
                                    <input type="checkbox" id="category1">
                                    <label for="category1">Cake</label>
                                </div>
                                <div class="form-group float-left dates checkbox">
                                    <input type="checkbox" id="category2">
                                    <label for="category2">Ice Cream</label>
                                </div>
                                <div class="form-group float-left dates checkbox">
                                    <input type="checkbox" id="category3">
                                    <label for="category3">Seasonal Flavors</label>
                                </div>
                                <div class="form-group float-left dates checkbox">
                                    <input type="checkbox" id="category4">
                                    <label for="category4">Cupcakes</label>
                                </div>
                            </div>
                            <button data-aos="fade-up" type="submit" id="submit" class="submit_now text-decoration-none">Sign Up</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%-- End Sign up --%>

    <%@ include file="footer.jsp"%>
</body>
</html>
