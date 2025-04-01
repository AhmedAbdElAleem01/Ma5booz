<html>
<head>
    <title>Sign up</title>
</head>
<body>
    <%@ include file="header.jsp"%>

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <div class="alert alert-danger">
            <%= error %>
        </div>
    <% } %>

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
                        <form id="contactpage" method="post" action="http://localhost:9090/Ma5booz/register" class="position-relative">
                            <div class="upper-form" data-aos="fade-up">
                                <span class="address">Personal Information:</span>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="fname">First Name</label>
                                        <input type="text" class="form-control" name="fname" id="fname" value="<%= request.getAttribute("fname") != null ? request.getAttribute("fname") : "" %>" required onblur="validateFName()">
                                        <span id="validFName"></span>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="lname">Last Name</label>
                                        <input type="text" class="form-control" name="lname" id="lname" value="<%= request.getAttribute("lname") != null ? request.getAttribute("lname") : "" %>" required onblur="validateLName()">
                                        <span id="validLName"></span>
                                    </div>
                                </div>
                                <div class="row mt-5">
                                    <div class="col-md-6">
                                        <label for="phoneNumber">Phone Number</label>
                                        <input type="tel" class="form-control" name="phoneNumber" id="phoneNumber" value="<%= request.getAttribute("phoneNumber") != null ? request.getAttribute("phoneNumber") : "" %>" required onblur="validatePhone()">
                                        <span id="validPhone"></span>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="birthdate">Birth Date</label>
                                        <input type="date" class="form-control" name="birthdate" id="birthdate" value="<%= request.getAttribute("birthdate") != null ? request.getAttribute("birthdate") : "" %>" required>
                                    </div>
                                </div>
                                <div class="row mt-5">
                                    <div class="col-12">
                                        <label for="job">Job</label>
                                        <input type="text" class="form-control" name="job" id="job" value="<%= request.getAttribute("job") != null ? request.getAttribute("job") : "" %>" onblur="validateJob()">
                                        <span id="validJob"></span>
                                    </div>
                                </div>

                                <span class="address mt-5">Account Information:</span>
                                <div class="column">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="email">Email Address</label>
                                            <input type="email" class="form-control" name="email" id="email" placeholder="example@gmail.com" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required onblur="isEmailUnique()">
                                            <span id="uniqueEmail"></span>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="username">Username</label>
                                            <input type="text" class="form-control" name="username" id="username" value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>" required onblur="isUsernameAvailable()">
                                            <span id="valid"></span>
                                        </div>
                                    </div>
                                     <div class="row mt-5">
                                        <div class="col-md-6">
                                            <label for="password">Password</label>
                                            <input type="password" class="form-control" name="password" id="password" placeholder="Your password must be at least 5 characters" value="<%= request.getAttribute("password") != null ? request.getAttribute("password") : "" %>" required onblur="validatePassword()">
                                            <span id="validPassword"></span>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="creditLimit">Credit Limit</label>
                                            <input type="number" class="form-control" name="creditLimit" id="creditLimit" min="0" step="any" value="<%= request.getAttribute("creditLimit") != null ? request.getAttribute("creditLimit") : "" %>" required>
                                        </div>
                                     </div>
                                </div>
                            </div>

                            <div class="lower-form mt-5" data-aos="fade-up">
                                <span class="address">Address:</span>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Country</label>
                                        <select class="form-control" name="country" required>
                                            <option value="" disabled selected hidden>Select country</option>
                                            <option value="Cairo" <%= "Cairo".equals(request.getAttribute("country")) ? "selected" : "" %>>Cairo</option>
                                            <option value="Giza" <%= "Giza".equals(request.getAttribute("country")) ? "selected" : "" %>>Giza</option>
                                            <option value="Alexandria" <%= "Alexandria".equals(request.getAttribute("country")) ? "selected" : "" %>>Alexandria</option>
                                            <option value="Dakahlia" <%= "Dakahlia".equals(request.getAttribute("country")) ? "selected" : "" %>>Dakahlia</option>
                                            <option value="Sharqia" <%= "Sharqia".equals(request.getAttribute("country")) ? "selected" : "" %>>Sharqia</option>
                                            <option value="Qalyubia" <%= "Qalyubia".equals(request.getAttribute("country")) ? "selected" : "" %>>Qalyubia</option>
                                            <option value="Minya" <%= "Minya".equals(request.getAttribute("country")) ? "selected" : "" %>>Minya</option>
                                            <option value="Sohag" <%= "Sohag".equals(request.getAttribute("country")) ? "selected" : "" %>>Sohag</option>
                                            <option value="Fayoum" <%= "Fayoum".equals(request.getAttribute("country")) ? "selected" : "" %>>Fayoum</option>
                                            <option value="Asyut" <%= "Asyut".equals(request.getAttribute("country")) ? "selected" : "" %>>Asyut</option>
                                            <option value="Ismailia" <%= "Ismailia".equals(request.getAttribute("country")) ? "selected" : "" %>>Ismailia</option>
                                            <option value="Suez" <%= "Suez".equals(request.getAttribute("country")) ? "selected" : "" %>>Suez</option>
                                            <option value="Luxor" <%= "Luxor".equals(request.getAttribute("country")) ? "selected" : "" %>>Luxor</option>
                                            <option value="Aswan" <%= "Aswan".equals(request.getAttribute("country")) ? "selected" : "" %>>Aswan</option>
                                            <option value="Port Said" <%= "Port Said".equals(request.getAttribute("country")) ? "selected" : "" %>>Port Said</option>
                                            <option value="Beheira" <%= "Beheira".equals(request.getAttribute("country")) ? "selected" : "" %>>Beheira</option>
                                            <option value="Beni Suef" <%= "Beni Suef".equals(request.getAttribute("country")) ? "selected" : "" %>>Beni Suef</option>
                                            <option value="Kafr El Sheikh" <%= "Kafr El Sheikh".equals(request.getAttribute("country")) ? "selected" : "" %>>Kafr El Sheikh</option>
                                            <option value="Red Sea" <%= "Red Sea".equals(request.getAttribute("country")) ? "selected" : "" %>>Red Sea</option>
                                            <option value="New Valley" <%= "New Valley".equals(request.getAttribute("country")) ? "selected" : "" %>>New Valley</option>
                                            <option value="Matrouh" <%= "Matrouh".equals(request.getAttribute("country")) ? "selected" : "" %>>Matrouh</option>
                                            <option value="North Sinai" <%= "North Sinai".equals(request.getAttribute("country")) ? "selected" : "" %>>North Sinai</option>
                                            <option value="South Sinai" <%= "South Sinai".equals(request.getAttribute("country")) ? "selected" : "" %>>South Sinai</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="city">City</label>
                                        <input type="text" class="form-control" name="city" id="city" value="<%= request.getAttribute("city") != null ? request.getAttribute("city") : "" %>" onblur="validateCity()">
                                        <span id="validCity"></span>
                                    </div>
                                </div>
                                <div class="row mt-5">
                                    <div class="col-md-6">
                                        <label for="street">Street</label>
                                        <input type="text" class="form-control" name="street" id="street" onblur="validateStreet()" value="<%= request.getAttribute("street") != null ? request.getAttribute("street") : "" %>" >
                                        <span id="validStreet"></span>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="BNo">Building Number</label>
                                        <input type="number" class="form-control" name="BNo" id="BNo" min="1" value="<%= request.getAttribute("BNo") != null ? request.getAttribute("BNo") : "" %>" >
                                    </div>
                                </div>
                                <%
                                    String[] selectedInterests = (String[]) request.getAttribute("interests");
                                %>
                                <span class="address mt-5">Interests:</span>
                                <div class="form-group float-left dates checkbox">
                                    <input type="checkbox" id="category1" name="interests" value="Cakes"
                                         <% if (selectedInterests != null && java.util.Arrays.asList(selectedInterests).contains("Cakes")) { %>
                                            checked
                                         <% } %>
                                    >
                                    <label for="category1">Cakes</label>
                                </div>
                                <div class="form-group float-left dates checkbox">
                                    <input type="checkbox" id="category2" name="interests" value="Ice Cream"
                                         <% if (selectedInterests != null && java.util.Arrays.asList(selectedInterests).contains("Ice Cream")) { %>
                                            checked
                                         <% } %>
                                    >
                                    <label for="category2">Ice Cream</label>
                                </div>
                                <div class="form-group float-left dates checkbox">
                                    <input type="checkbox" id="category3" name="interests" value="Seasonal Flavors"
                                         <% if (selectedInterests != null && java.util.Arrays.asList(selectedInterests).contains("Seasonal Flavors")) { %>
                                            checked
                                         <% } %>
                                    >
                                    <label for="category3">Seasonal Flavors</label>
                                </div>
                                <div class="form-group float-left dates checkbox">
                                    <input type="checkbox" id="category4" name="interests" value="Cupcakes"
                                        <% if (selectedInterests != null && java.util.Arrays.asList(selectedInterests).contains("Cupcakes")) { %>
                                            checked
                                        <% } %>
                                    >
                                    <label for="category4">Cupcakes</label>
                                </div>
                            </div>
                            <button data-aos="fade-up" type="submit" id="submit" class="submit_now text-decoration-none">Sign up</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%-- End Sign up --%>

    <%@ include file="footer.jsp"%>
    <script src="${pageContext.request.contextPath}/static/js/register.js"> </script>
</body>
</html>
