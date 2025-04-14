<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Checkout | Ma5booz</title>
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
        <%-- Sub Banner  --%>
        <section class="sub_banner position-relative">
            <div class="container position-relative">
                <div class="row">
                    <div class="col-12">
                        <div class="sub_banner_content text-center" data-aos="fade-up">
                            <h1>Checkout</h1>
                            <div class="box">
                                <a href="${pageContext.request.contextPath}/home" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <a href="${pageContext.request.contextPath}/cart" class="cart text-decoration-none">
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
                        <form id="contactpage" method="post" action="${pageContext.request.contextPath}/checkout" class="position-relative">
                            <div class="upper-form" data-aos="fade-up">
                                <span class="address">Billing Details:</span>
                                <div class="form-group input1 float-left">
                                    <label for="name">Name*</label>
                                    <input type="text" class="form_style" name="name" id="name" value="${sessionScope.user.name}" disabled>
                                </div>
                                <div class="form-group float-left">
                                    <label for="email">Email Address*</label>
                                    <input type="email" class="form_style" name="email" id="email" value="${sessionScope.user.email}" disabled>
                                </div>
                                <div class="form-group input1 float-left">
                                    <label for="phoneNumber">Phone Number*</label>
                                    <input type="tel" class="form_style" name="phoneNumber" id="phoneNumber" value="${sessionScope.user.phoneNumber}" required onblur="validatePhone()">
                                    <span id="validPhone"></span>
                                </div>
                                <div class="form-group float-left">
                                    <label>Country*</label>
                                    <select class="form-control" name="country" required>
                                        <option value="" disabled selected hidden>Select country</option>
                                        <option value="Cairo" ${"Cairo".equals(sessionScope.address.country) ? "selected" : ""}>Cairo</option>
                                        <option value="Giza" ${"Giza".equals(sessionScope.address.country) ? "selected" : ""}>Giza</option>
                                        <option value="Alexandria" ${"Alexandria".equals(sessionScope.address.country) ? "selected" : ""}>Alexandria</option>
                                        <option value="Dakahlia" ${"Dakahlia".equals(sessionScope.address.country) ? "selected" : ""}>Dakahlia</option>
                                        <option value="Sharqia" ${"Sharqia".equals(sessionScope.address.country) ? "selected" : ""}>Sharqia</option>
                                        <option value="Qalyubia" ${"Qalyubia".equals(sessionScope.address.country) ? "selected" : ""}>Qalyubia</option>
                                        <option value="Menofia" ${"Menofia".equals(sessionScope.address.country) ? "selected" : ""}>Menofia</option>
                                        <option value="Minya" ${"Minya".equals(sessionScope.address.country) ? "selected" : ""}>Minya</option>
                                        <option value="Sohag" ${"Sohag".equals(sessionScope.address.country) ? "selected" : ""}>Sohag</option>
                                        <option value="Fayoum" ${"Fayoum".equals(sessionScope.address.country) ? "selected" : ""}>Fayoum</option>
                                        <option value="Asyut" ${"Asyut".equals(sessionScope.address.country) ? "selected" : ""}>Asyut</option>
                                        <option value="Ismailia" ${"Ismailia".equals(sessionScope.address.country) ? "selected" : ""}>Ismailia</option>
                                        <option value="Suez" ${"Suez".equals(sessionScope.address.country) ? "selected" : ""}>Suez</option>
                                        <option value="Luxor" ${"Luxor".equals(sessionScope.address.country) ? "selected" : ""}>Luxor</option>
                                        <option value="Aswan" ${"Aswan".equals(sessionScope.address.country) ? "selected" : ""}>Aswan</option>
                                        <option value="Port Said" ${"Port Said".equals(sessionScope.address.country) ? "selected" : ""}>Port Said</option>
                                        <option value="Beheira" ${"Beheira".equals(sessionScope.address.country) ? "selected" : ""}>Beheira</option>
                                        <option value="Beni Suef" ${"Beni Suef".equals(sessionScope.address.country) ? "selected" : ""}>Beni Suef</option>
                                        <option value="Kafr El Sheikh" ${"Kafr El Sheikh".equals(sessionScope.address.country) ? "selected" : ""}>Kafr El Sheikh</option>
                                        <option value="Red Sea" ${"Red Sea".equals(sessionScope.address.country) ? "selected" : ""}>Red Sea</option>
                                        <option value="New Valley" ${"New Valley".equals(sessionScope.address.country) ? "selected" : ""}>New Valley</option>
                                        <option value="Matrouh" ${"Matrouh".equals(sessionScope.address.country) ? "selected" : ""}>Matrouh</option>
                                        <option value="North Sinai" ${"North Sinai".equals(sessionScope.address.country) ? "selected" : ""}>North Sinai</option>
                                        <option value="South Sinai" ${"South Sinai".equals(sessionScope.address.country) ? "selected" : ""}>South Sinai</option>
                                    </select>
                                </div>
                                <div class="form-group input1 float-left">
                                     <label for="city">City*</label>
                                     <input type="text" class="form-control" name="city" id="city" value="${sessionScope.address.city}" required onblur="validateCity()">
                                     <span id="validCity"></span>
                                </div>
                                <div class="form-group float-left">
                                    <label for="street">Street*</label>
                                    <input type="text" class="form-control" name="street" id="street" value="${sessionScope.address.street}" required onblur="validateStreet()">
                                    <span id="validStreet"></span>
                                </div>
                                <div class="form-group input1 float-left">
                                    <label for="BNo">Building Number*</label>
                                    <input type="number" class="form-control" name="BNo" id="BNo" min="1" value="${sessionScope.address.buildingNo == -1 ? "" : sessionScope.address.buildingNo}" required onblur="validateBuildingNo()">
                                     <span id="validBuildingNo"></span>
                                </div>
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
                            <c:forEach var="cartItem" items="${cart}">
                                <c:set var="product" value="${products[cartItem.value.productId]}"/>
                                <div class="each-item">
                                    <div class="product-items">
                                        <span class="heading">${cartItem.value.quantity} x ${product.name}</span>
                                        <p class="text-size-14 mb-0">${product.description}</p>
                                    </div>
                                   <div class="product-prices">
                                       <fmt:formatNumber value="${cartItem.value.quantity * product.price}" type="number" minFractionDigits="2" maxFractionDigits="2" />
                                   </div>
                                </div>
                            </c:forEach>

                            <div class="each-item">
                                <div class="product-items">
                                    <span class="heading">Grand Total</span>
                                </div>
                                <div class="product-prices">
                                    <span class="dollar total-price">EGP ${totalPrice}</span>
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
    <script src="${pageContext.request.contextPath}/static/js/checkout.js"> </script>
</body>
</html>