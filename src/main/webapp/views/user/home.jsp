
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Home | Ma5booz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/shop-style.css">
</head>
<body>
    <%@ include file="header.jsp"%>

<div class="home1_banner_outer position-relative">
    
    <%@ include file="navbar.jsp"%>

    <!-- Banner -->
    <section class="banner-con position-relative">
        <figure class="banner-lefttopimage mb-0">
            <img src="${pageContext.request.contextPath}/static/img/bannertest2.png" alt="image" class="img-fluid">
        </figure>
        <div class="container position-relative">
            <div class="row">
                <div class="col-xl-7 col-lg-6 col-md-12 col-sm-12 col-12">
                    <div class="banner_content" data-aos="fade-up">
                        <h3>Made to Delight</h3>
                        <h1><span>Breads & <br>Pastries</span></h1>
                        <p>Sweet, buttery, and baked with love.</p>
                        <a href="${pageContext.request.contextPath}/shop" class="text-decoration-none all_button">
                            Shop the Menu <i class="fa-solid fa-arrow-right"></i>
                        </a>
                    </div>
                    
                </div>
                <div class="col-xl-5 col-lg-6 col-md-12 col-sm-12 col-12"></div>
            </div>
            <figure class="banner-image mb-0" data-aos="zoom-in">
                <img src="${pageContext.request.contextPath}/static/img/bannertest2.png" alt="image" class="img-fluid">
            </figure>
        </div>
    </section>
</div>

<!-- Classic -->
<section class="classic-con position-relative">
    <figure class="classic-leftimage mb-0">
        <img src="${pageContext.request.contextPath}/static/img/left2.png" alt="image" class="img-fluid">
    </figure>

    <div class="container position-relative">
        <div class="row">
            <div class="col-12">
                <div class="classic_content text-center" data-aos="fade-up">
                    <h2>Our <span>Classic</span> Favorites</h2>
                    <p>Check out our top products that our customers love.</p>
                </div>
            </div>
        </div>
        <div class="classic_wrapper" data-aos="fade-up">
            <div class="row">
                <div class="col-12">
                    <div class="owl-carousel owl-theme">

                        <c:forEach var="product" items="${classicProducts}">
                            <c:url value="product_details" var="detailsUrl">
                                <c:param name="productID" value="${product.id}"/>
                            </c:url>
                            <div class="item">
                                <div class="classic-box">
                                    <div class="classic_image_box box1">
                                        <figure class="mb-0">
                                           <a href="${detailsUrl}"> <img src="${pageContext.request.contextPath}/product-image/${product.imageUrl}" alt="image" class="img-fluid"></a>
                                        </figure>
                                        <i class="fa-regular fa-heart"></i>
                                    </div>
                                    <div class="classic_box_content">
                                        <div class="text_wrapper position-relative">
                                            <h6>${product.name}</h6>
                                            <div class="rating">
                                                <i class="fa-solid fa-star"></i>
                                                <span>4.9/5</span>
                                            </div>
                                        </div>
                                        <p class="text-size-16">${product.description}</p>
                                        <div class="price_wrapper position-relative">
                                            <span class="dollar">EGP <span class="counter">${product.price}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Follow -->
<c:if test="${not empty prefs}">
    <section class="follow-con position-relative">
        <div class="container">
            <div class="row">
                <div class="col-12 position-relative">
                    <div class="follow_content text-center" data-aos="fade-up">
                        <h2>Based on your  <span>preferences</span></h2>
                        <p>Shop with ease and discover top-quality products tailored to fit your lifestyle and preferences</p>
                    </div>
                    <ul class="list-unstyled mb-0" data-aos="fade-up">
                            <c:forEach var="pref" items="${prefs}">
                                <li>
                                    <c:url value="/shop" var="catUrl">
                                        <c:param name="catID" value="${pref.id}" />
                                    </c:url>
                                    <a href="${catUrl}">
                                        <figure class="image mb-0">
                                            <img src="${pageContext.request.contextPath}/static/img/${pref.imageUrl}" alt="image" class="img-fluid">
                                        </figure>
                                        <div class="icon"><i aria-hidden="true"></i></div>
                                    </a>
                                </li>
                            </c:forEach>
                       
                        
                    </ul>
                    <div class="circle1"></div>
                    <div class="circle2"></div>
                </div>
            </div>
        </div>
    </section>
</c:if>

<!-- Categories -->
<section class="categories-con position-relative" style="background: linear-gradient(to right, #f8f0ff, #fff0f5);
">
    <div class="container position-relative">
        <div class="row">
            <div class="col-12">
                <div class="categories_content text-center" data-aos="fade-up">
                    <h2>Explore Our <span>Categories</span></h2>
                    <p>Browse through our different categories to find your favorite ice cream treats.</p>
                </div>
            </div>
        </div>
        <div class="categories_wrapper" data-aos="fade-up">
            <div class="row">
                <c:forEach var="category" items="${homeCategories}">
                    <div class="col-lg-3 col-md-6 col-sm-6 col-12">
                        <div class="categories-box">
                            <figure class="image mb-0">
                                <img src="${pageContext.request.contextPath}/static/img/${category.imageUrl}" 
                                     alt="${category.name}" class="img-fluid">
                            </figure>
                            <div class="content">
                                <h5 class="mb-0">${category.name}</h5>
                                <c:url value="/shop" var="catUrl">
                                    <c:param name="catID" value="${category.id}" />
                                </c:url>
                                <a href="${catUrl}">
                                    <i class="fa-solid fa-arrow-right"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
    <%@ include file="footer.jsp"%>
</body>
</html>