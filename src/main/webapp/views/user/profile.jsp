<html>
<head>
    <title>My Profile | IceDelights</title>
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
                            <h1>My Profile</h1>
                            <div class="box">
                                <a href="${pageContext.request.contextPath}/views/user/home.jsp" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <span class="mb-0 box_span">Profile</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- Profile -->
    <div class="profile-con">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <div class="nav flex-column nav-pills" role="tablist" aria-orientation="vertical">
                        <a class="nav-link" id="payment-nav" data-toggle="pill" href="#payment-tab" role="tab">Credit Limit</a>
                        <a class="nav-link" id="address-nav" data-toggle="pill" href="#address-tab" role="tab">Address</a>
                        <a class="nav-link" id="account-nav" data-toggle="pill" href="#account-tab" role="tab">Account Details</a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="tab-content">
                        <div class="tab-pane fade" id="payment-tab" role="tabpanel" aria-labelledby="payment-nav">
                            <div class="cart-con checkout-con position-relative">
                                <div class="container">
                                    <div class="row justify-content-center">
                                        <div class="col-lg-8 col-12">
                                            <div class="product-detail-box text-center">
                                                <!-- Title -->
                                                <h3 class="text-center mb-3" data-aos="fade-up">Manage Your Credit Limit</h3>
                                                <p class="text-center text-size-14 mb-4" data-aos="fade-up">
                                                    Your credit limit allows you to make purchases up to a certain amount. You can adjust it as needed.
                                                </p>
                            
                                                <!-- Credit Limit Form -->
                                                <form id="creditLimitForm" method="post" action="${pageContext.request.contextPath}/profile?form=creditLimitForm" class="position-relative">
                                                    <div class="upper-form" data-aos="fade-up">
                                                        <div class="form-group input1 float-left w-100">
                                                            <label for="creditLimit">Credit Limit</label>
                                                            <input type="number" class="form_style w-100" name="creditLimit" id="creditLimit" value="${sessionScope.user.creditLimit}" disabled>
                                                        </div>
                                                    </div>
                            
                                                    <!-- Action Buttons -->
                                                    <div class="row mt-3 justify-content-center">
                                                        <div class="col-md-4 text-center">
                                                            <button data-aos="fade-up" type="button" id="edit" class="submit_now text-decoration-none">Edit</button>
                                                        </div>
                                                        <div class="col-md-4 text-center">
                                                            <button data-aos="fade-up" type="submit" id="submit" class="submit_now text-decoration-none">Save</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                                              
                        </div>
                        <div class="tab-pane fade" id="address-tab" role="tabpanel" aria-labelledby="address-nav">
                            <div class="cart-con checkout-con position-relative d-flex justify-content-center align-items-center min-vh-100">
                                <div class="container">
                                    <div class="row justify-content-center">
                                        <div class="col-lg-6 col-md-8 col-12">
                                            <div class="product-detail-box text-center">
                                                <!-- Title -->
                                                <h3 class="mb-3" data-aos="fade-up">Manage Your Shipping Address</h3>
                                                <p class="text-size-14 mb-4" data-aos="fade-up">
                                                    Ensure your address is up-to-date for a smooth delivery experience.
                                                </p>
                        
                                                <!-- Address Form -->
                                                <form id="addressForm" method="post" action="${pageContext.request.contextPath}/profile?form=addressForm" class="position-relative">
                                                    <div class="upper-form" data-aos="fade-up">
                                                        <div class="row">
                                                            <div class="col-6">
                                                                <label for="country">Country</label>
                                                                <input type="text" class="form_style w-100" name="country" id="country" value="${sessionScope.address.country}" disabled>
                                                            </div>
                                                            <div class="col-6 mt-3">
                                                                <label for="city">City</label>
                                                                <input type="text" class="form_style w-100" name="city" id="city" value="${sessionScope.address.city}" disabled>
                                                            </div>
                                                            <div class="col-6 mt-3">
                                                                <label for="street">Street</label>
                                                                <input type="text" class="form_style w-100" name="street" id="street" value="${sessionScope.address.street}" disabled>
                                                            </div>
                                                            <div class="col-6 mt-3">
                                                                <label for="BNo">Building Number</label>
                                                                <input type="text" class="form_style w-100" name="BNo" id="BNo" value="${sessionScope.address.buildingNo}" disabled>
                                                            </div>
                                                        </div>      
                                                        <br><br>                            
                                                        <div class="form-group w-100 mt-2">
                                                            <label for="mobile">Mobile Number</label>
                                                            <input type="text" class="form_style w-100" name="mobile" id="mobile" value="${sessionScope.user.phoneNumber}" disabled>
                                                        </div>
                                                    </div>
                        
                                                    <!-- Action Buttons -->
                                                    <div class="row mt-3 justify-content-center">
                                                        <div class="col-6 col-md-4">
                                                            <button type="button" id="editAddress" class="submit_now text-decoration-none w-100">Edit</button>
                                                        </div>
                                                        <div class="col-6 col-md-4">
                                                            <button type="submit" id="saveAddress" class="submit_now text-decoration-none w-100" disabled>Save</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>                        
                        <div class="tab-pane fade" id="account-tab" role="tabpanel" aria-labelledby="account-nav">
                            <div class="cart-con checkout-con position-relative d-flex justify-content-center align-items-center min-vh-100">
                                <div class="container">
                                    <div class="row justify-content-center">
                                        <div class="col-lg-6 col-md-8 col-12">
                                            <div class="product-detail-box text-center">
                                                <!-- Title -->
                                                <h3 class="mb-3" data-aos="fade-up">Manage Your Account</h3>
                                                <p class="text-size-14 mb-4" data-aos="fade-up">
                                                    Update your personal details and change your password securely.
                                                </p>
                                    
                                                <!-- Account Details Form -->
                                                <form id="accountForm" method="post" action="${pageContext.request.contextPath}/profile?form=accountForm" class="position-relative">
                                                    <div class="upper-form text-start" data-aos="fade-up">
                                                        <h6 class="mb-3">Account Details</h6>
                                                        <div class="row">
                                                            <div class="col-6 mt-3">
                                                                <label for="username">Username</label>
                                                                <input type="text" class="form_style w-100" name="username" id="username" value="${sessionScope.user.username}" disabled>
                                                                <p style="font-size: smaller; color: red;" id="isValid"></p>
                                                            </div>
                                                            <div class="col-6 mt-3">
                                                                <label for="job">Job</label>
                                                                <input type="text" class="form_style w-100" name="job" id="job" value="${sessionScope.user.job}" disabled>
                                                            </div>
                                                            <div class="col-12">
                                                                <label for="email">Email</label>
                                                                <input type="email" class="form_style w-100" name="email" id="email" value="${sessionScope.user.email}" disabled>
                                                            </div>
                                                        </div>
                                                    </div>
                                    
                                                    <!-- Edit and Save Buttons -->
                                                    <div class="row mt-4 justify-content-center">
                                                        <div class="col-6 col-md-4" style="margin-top: 10px;">
                                                            <button type="button" id="editAccount" class="submit_now text-decoration-none w-100">Edit</button>
                                                        </div>
                                                        <div class="col-6 col-md-4" style="margin-top: 10px;">
                                                            <button type="submit" id="saveAccount" class="submit_now text-decoration-none w-100" disabled>Save</button>
                                                        </div>
                                                    </div>
                                                </form>
                                    
                                                <!-- Password Change Form -->
                                                <form id="passwordForm" method="post" action="${pageContext.request.contextPath}/profile?form=passwordForm" class="position-relative mt-5">
                                                    <div class="upper-form text-start" data-aos="fade-up">
                                                        <h6 class="mb-3">Change Password</h6>
                                                        <div class="row">
                                                            <div class="col-12">
                                                                <label for="currentPass">Current Password</label>
                                                                <input type="password" class="form_style w-100" name="currentPass" id="currentPass">
                                                            </div>
                                                            <div class="col-12 mt-3">
                                                                <label for="newPass">New Password</label>
                                                                <input type="password" class="form_style w-100" name="newPass" id="newPass">
                                                            </div>
                                                            <div class="col-12 mt-3">
                                                                <label for="confirmPass">Confirm Password</label>
                                                                <input type="password" class="form_style w-100" name="confirmPass" id="confirmPass">
                                                            </div>
                                                        </div>
                                                    </div>
                                    
                                                    <!-- Save Password Button -->
                                                    <div class="row mt-4 justify-content-center">
                                                        <div class="col-6" style="margin-top: 10px;">
                                                            <button type="submit" id="savePassword" class="submit_now text-decoration-none w-100">Save</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                                                
                        </div>                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Profile -->
    <!-- Footer -->
    <%@ include file="footer.jsp"%>
    <script src="<c:url value='/static/js/profile.js' />" type="text/javascript"></script>
</body>
</html>