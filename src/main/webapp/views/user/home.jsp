
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Home | IceDelights</title>
</head>
<body>
    <%@ include file="header.jsp"%>

<div class="home1_banner_outer position-relative">
    
    <%@ include file="navbar.jsp"%>

    <!-- Banner -->
    <section class="banner-con position-relative">
        <figure class="banner-lefttopimage mb-0">
            <img src="${pageContext.request.contextPath}/static/img/banner-lefttopimage.png" alt="image" class="img-fluid">
        </figure>
        <div class="container position-relative">
            <div class="row">
                <div class="col-xl-7 col-lg-6 col-md-12 col-sm-12 col-12">
                    <div class="banner_content" data-aos="fade-up">
                        <h3>Welcome to The</h3>
                        <h1>Classic <span>Ice Cream</span> Parlor</h1>
                        <p>Savor the taste of traditional ice cream made with love and quality ingredients.</p>
                        <a href="${pageContext.request.contextPath}/views/user/shop.jsp" class="text-decoration-none all_button">Browse Our Classic Flavors<i class="fa-solid fa-arrow-right"></i></a>
                    </div>
                </div>
                <div class="col-xl-5 col-lg-6 col-md-12 col-sm-12 col-12"></div>
            </div>
            <figure class="banner-image mb-0" data-aos="zoom-in">
                <img src="${pageContext.request.contextPath}/static/img/banner-image.png" alt="image" class="img-fluid">
            </figure>
        </div>
    </section>
</div>
<!-- Relive -->
<section class="relive-con position-relative">
    <figure class="relive-rightbottomimage mb-0">
        <img src="${pageContext.request.contextPath}/static/img/relive-rightbottomimage.png" alt="image" class="img-fluid">
    </figure>
    <div class="container position-relative">
        <div class="row">
            <div class="col-lg-6 col-md-12 col-sm-12 col-12 text-lg-left text-center order-lg-1 order-2">
                <div class="relive_wrapper" data-aos="fade-up">
                    <figure class="relive-circle mb-0">
                        <img src="${pageContext.request.contextPath}/static/img/relive-circle.png" alt="image" class="img-fluid">
                    </figure>
                    <figure class="relive-doted mb-0">
                        <img src="${pageContext.request.contextPath}/static/img/relive-doted.png" alt="image" class="img-fluid">
                    </figure>
                </div>
            </div>
            <div class="col-lg-6 col-md-12 col-sm-12 col-12 order-lg-2 order-1">
                <div class="relive_content" data-aos="fade-up">
                    <h2>Relive the Sweet Memories of Classic <span>Ice Creams</span></h2>
                    <p>From rich chocolate fudge to creamy vanilla sundaes, discover our menu of classic ice cream creations.</p>
                    <a href="about.html" class="text-decoration-none all_button">Explore Our Menu<i class="fa-solid fa-arrow-right"></i></a>
                    <figure class="relive-triangle mb-0">
                        <img src="${pageContext.request.contextPath}/static/img/relive-triangle.png" alt="image" class="img-fluid">
                    </figure>
                </div>
            </div>
        </div>
        <figure class="relive-image mb-0" data-aos="zoom-in">
            <img src="${pageContext.request.contextPath}/static/img/relive-image.png" alt="image" class="img-fluid">
        </figure>
    </div>
</section>
<!-- Classic -->
<section class="classic-con position-relative">
    <figure class="classic-leftimage mb-0">
        <img src="${pageContext.request.contextPath}/static/img/classic-leftimage.png" alt="image" class="img-fluid">
    </figure>
    <figure class="classic-rightimage mb-0">
        <img src="${pageContext.request.contextPath}/static/img/classic-rightimage.png" alt="image" class="img-fluid">
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
                            <div class="item">
                                <div class="classic-box">
                                    <div class="classic_image_box box1">
                                        <figure class="mb-0">
                                            <img src="${pageContext.request.contextPath}/product-image/${product.imageUrl}" alt="image" class="img-fluid">
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
                                            <span class="dollar">$<span class="counter">${product.price}</span>
                                            <a href="cart.html"><img src="${pageContext.request.contextPath}/static/img/cart.png" alt="image" class="img-fluid"></a>
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
<!-- Categories -->
<section class="categories-con position-relative">
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

<!-- Follow -->
<section class="follow-con position-relative">
    <div class="container">
        <div class="row">
            <div class="col-12 position-relative">
                <div class="follow_content text-center" data-aos="fade-up">
                    <h2>Follow Us on <span>Instagram</span></h2>
                    <p>Join our Instagram community for updates, special deals, and more!</p>
                </div>
                <ul class="list-unstyled mb-0" data-aos="fade-up">
                    <li>
                        <a href="https://www.instagram.com/">
                            <figure class="image mb-0">
                                <img src="${pageContext.request.contextPath}/static/img/follow-image1.jpg" alt="image" class="img-fluid">
                            </figure>
                            <div class="icon"><i class="fa-brands fa-instagram" aria-hidden="true"></i></div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/">
                            <figure class="image mb-0">
                                <img src="${pageContext.request.contextPath}/static/img/follow-image2.jpg" alt="image" class="img-fluid">
                            </figure>
                            <div class="icon"><i class="fa-brands fa-instagram" aria-hidden="true"></i></div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/">
                            <figure class="image mb-0">
                                <img src="${pageContext.request.contextPath}/static/img/follow-image3.jpg" alt="image" class="img-fluid">
                            </figure>
                            <div class="icon"><i class="fa-brands fa-instagram" aria-hidden="true"></i></div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/">
                            <figure class="image mb-0">
                                <img src="${pageContext.request.contextPath}/static/img/follow-image4.jpg" alt="image" class="img-fluid">
                            </figure>
                            <div class="icon"><i class="fa-brands fa-instagram" aria-hidden="true"></i></div>
                        </a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/">
                            <figure class="image mb-0">
                                <img src="${pageContext.request.contextPath}/static/img/follow-image5.jpg" alt="image" class="img-fluid">
                            </figure>
                            <div class="icon"><i class="fa-brands fa-instagram" aria-hidden="true"></i></div>
                        </a>
                    </li>
                </ul>
                <div class="circle1"></div>
                <div class="circle2"></div>
            </div>
        </div>
    </div>
</section>

<!-- Footer -->
    <%@ include file="footer.jsp"%>
</body>
</html>