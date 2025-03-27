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
                        <a class="nav-link" id="orders-nav" data-toggle="pill" href="#orders-tab" role="tab">Orders</a>
                        <a class="nav-link" id="payment-nav" data-toggle="pill" href="#payment-tab" role="tab">Credit Limit</a>
                        <a class="nav-link" id="address-nav" data-toggle="pill" href="#address-tab" role="tab">Address</a>
                        <a class="nav-link" id="account-nav" data-toggle="pill" href="#account-tab" role="tab">Account Details</a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/views/user/home.jsp">Logout</a>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="tab-content">
                        <div class="tab-pane fade" id="orders-tab" role="tabpanel" aria-labelledby="orders-nav">
                            <div class="table-responsive">
                                <table class="table table-bordered">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>No</th>
                                            <th>Product</th>
                                            <th>Date</th>
                                            <th>Price</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Product Name</td>
                                            <td>01 Jan 2020</td>
                                            <td>$22</td>
                                            <td>Approved</td>
                                            <td><button>View</button></td>
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>Product Name</td>
                                            <td>01 Jan 2020</td>
                                            <td>$22</td>
                                            <td>Approved</td>
                                            <td><button>View</button></td>
                                        </tr>
                                        <tr>
                                            <td>1</td>
                                            <td>Product Name</td>
                                            <td>01 Jan 2020</td>
                                            <td>$22</td>
                                            <td>Approved</td>
                                            <td><button>View</button></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
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
                                                <form id="creditLimitForm" method="post" action="" class="position-relative">
                                                    <div class="upper-form" data-aos="fade-up">
                                                        <div class="form-group input1 float-left w-100">
                                                            <label for="creditLimit">Credit Limit</label>
                                                            <input type="number" class="form_style w-100" name="creditLimit" id="creditLimit" disabled>
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
                                                <form id="addressForm" method="post" action="" class="position-relative">
                                                    <div class="upper-form" data-aos="fade-up">
                                                        <div class="form-group input1 w-100">
                                                            <label for="address">Shipping Address</label>
                                                            <input type="text" class="form_style w-100" name="address" id="address" 
                                                                   placeholder="123 Shipping Street, Los Angeles, CA" disabled>
                                                        </div>
                                                        <div class="form-group w-100 mt-2">
                                                            <label for="mobile">Mobile Number</label>
                                                            <input type="text" class="form_style w-100" name="mobile" id="mobile" 
                                                                   placeholder="Mobile: 012-345-6789" disabled>
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
                                                <form id="accountForm" method="post" action="" class="position-relative">
                                                    <div class="upper-form text-start" data-aos="fade-up">
                                                        <h6 class="mb-3">Account Details</h6>
                                                        <div class="row">
                                                            <div class="col-6">
                                                                <label for="fname">First Name</label>
                                                                <input type="text" class="form_style w-100" name="fname" id="fname" disabled>
                                                            </div>
                                                            <div class="col-6 mt-3">
                                                                <label for="lname">Last Name</label>
                                                                <input type="text" class="form_style w-100" name="lname" id="lname" disabled>
                                                            </div>
                                                            <div class="col-6 mt-3">
                                                                <label for="email">Email Address</label>
                                                                <input type="email" class="form_style w-100" name="email" id="email" disabled>
                                                            </div>
                                                            <div class="col-6 mt-3">
                                                                <label for="job">Job</label>
                                                                <input type="text" class="form_style w-100" name="job" id="job" disabled>
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
                                                <form id="passwordForm" method="post" action="" class="position-relative mt-5">
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
</body>
</html>