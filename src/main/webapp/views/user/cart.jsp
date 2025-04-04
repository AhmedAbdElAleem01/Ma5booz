<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
                                <span>${cart.size()}(Items)</span>
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
                                    <c:forEach var="cartItem" items="${cart}">
                                        <c:set var="product" value="${products[cartItem.value.productId]}" />
                                        <div class="product d-sm-flex d-block align-items-center">
                                            <div class="product-details">
                                                <div class="product-image box1">
                                                    <figure class="mb-0">
                                                        <img src="${pageContext.request.contextPath}/static/img/${product.imageUrl}" alt="image" class="img-fluid">
                                                    </figure>
                                                </div>
                                                <div class="product-content">
                                                    <span class="product-title">${product.name}</span>
                                                    <span class="product-color text"> <span>pistachio</span></span>
                                                    <span class="product-size text mb-0">Size: <span>L</span></span>
                                                </div>
                                            </div>

                                            <div class="product-price"><span>${product.price}$</span></div>
                                            <div class="product-quantity d-flex">
                                                <div class="product-qty-details">
                                                    <button id="decrease-${cartItem.key}" class="value-button decrease-button" onclick="decreaseValue('${cartItem.key}')" ${cartItem.value.quantity == 1 ? 'disabled' : ''}>-</button>
                                                    <div id="quantity-${cartItem.key}" class="number">${cartItem.value.quantity}</div>
                                                    <button id="increase-${cartItem.key}" class="value-button increase-button" onclick="increaseValue('${cartItem.key}', '${product.stockQuantity}')" ${cartItem.value.quantity >= product.stockQuantity ? 'disabled' : ''}>+</button>
                                                </div>
                                            </div>

                                            <div class="product-line-price">
                                                <span id="totalPrice-${cartItem.key}" data-price="${product.price}">
                                                    <fmt:formatNumber value="${cartItem.value.quantity * product.price}" type="number" maxFractionDigits="2"/>
                                                </span>
                                            </div>
                                            <div class="product-removal">
                                                <button onclick="updateCartAsync('${cartItem.key}',0)" class="remove-product"><i class="fas fa-times"></i></button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="buttun-shopping">
                                <a href="${pageContext.request.contextPath}/shop" class="text-decoration-none"><i class="fa-solid fa-arrow-left"></i>Continue Shopping</a>
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
                                    <li><span>Sub Total</span><span class="dollar" id="totalOrderPrice">$${totalPrice}</span></li>
                                </ul>
                                <div class="all-total">
                                    <div class="total">
                                        <span class="text" >Grand Total</span><span class="dollar" id="grandTotal">
                                            $<fmt:formatNumber value="${totalPrice}" type="number" maxFractionDigits="2"/>
                                        </span>
                                    </div>
                                    <a href="${pageContext.request.contextPath}/order-summary" class="text-decoration-none all_button">Proceed to checkout<i class="fa-solid fa-arrow-right"></i></a>
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
    </div>
    <!-- Footer -->
    <%@ include file="footer.jsp"%>
    <script src="${pageContext.request.contextPath}/static/js/cart.js"></script>
</body>
</html>
