<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Product Details | Ma5booz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/productDetails.css">
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
                                <a href="${pageContext.request.contextPath}/home" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <a href="${pageContext.request.contextPath}/shop" class="cart text-decoration-none">
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
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane tab-pane2 fade active show" id="first" role="tabpanel" aria-labelledby="first-tab">
                                <figure class="auction-img mb-0">
                                    <img class="img-fluid" src="${pageContext.request.contextPath}/product-image/${product.imageUrl}" alt="${product.name}">
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
                        <c:if test="${not empty product}">
                            <h4>${product.name}</h4>
                            <span class="price">EGP ${product.price}</span>
                            <p class="text-size-16">${product.description}</p>
                            <c:choose>
                                <c:when test="${product.stockQuantity <= 0}">
                                    <p style="color: red;">Out of Stock</p>
                                </c:when>
                                <c:when test="${product.stockQuantity < 10}">
                                    <p style="color: red;">Only ${product.stockQuantity} left in stock</p>
                                </c:when>
                                <c:otherwise>
                                    <p style="color: green;">In Stock</p>
                                </c:otherwise>
                            </c:choose>

                        </c:if>
                       
                        
                        <div class="sauce-picker">
                            <h6>Extra Sauce <span style="color: rgb(126, 229, 126); font-size: medium;">+EGP 0.50 each</span></h6>
                            <div class="sauce-options">
                                <!-- Strawberry -->
                                <label class="sauce-option">
                                    <input type="checkbox" name="sauce" class="strawberry">
                                    <span class="colored-checkbox"></span>
                                    <span>Strawberry</span>
                                </label>
                                
                                <!-- Mint -->
                                <label class="sauce-option">
                                    <input type="checkbox" name="sauce" class="mint">
                                    <span class="colored-checkbox"></span>
                                    <span>Pistachio</span>
                                </label>
                                
                                <!-- Chocolate -->
                                <label class="sauce-option">
                                    <input type="checkbox" name="sauce" class="chocolate">
                                    <span class="colored-checkbox"></span>
                                    <span>Chocolate</span>
                                </label>
                            </div>
                        </div>
                        <div class="sizes" style="margin-top: 20px;">
                            <span class="heading">Size:</span>
                            <ul class="list-unstyled mb-0">
                                <li>L</li>
                                <li>M</li>
                                <li>S</li>
                            </ul>
                        </div>
                        <div class="quatity_button_wrapper">
                            <div class="quantity-field">
                                <button class="value-button decrease-button" onclick="decreaseValue()" title="">-</button>
                                <div class="number" id="productQ">1</div>
                                <button class="value-button increase-button" onclick="increaseValue()" title="">+</button>
                            </div>     
                            
                            
                            <button  class="text-decoration-none all_button" onclick="addToCartAsync()">Add to cart<i class="fa-solid fa-arrow-right"></i></button>

                            
                
                        </div>
                        <p id="message" style="color:red;"></p>                        
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
                    <li><a class="active" data-toggle="tab" data-target="#description">Ingredients</a></li>
                </ul>
                <div class="tab-content">
                    <div id="description" class="tab-pane fade in active show">
                        <div class="row" data-aos="fade-up">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="description_content">
                                    <p class="text-size-16">${product.ingredients}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script>
        var productId = "${product.id}";
        var stock = '${product.stockQuantity}';
    </script>    
    <!-- Footer -->
    <%@ include file="footer.jsp"%>
    <script src="${pageContext.request.contextPath}/static/js/productDetails.js" type="text/javascript"></script>
</body>
</html>