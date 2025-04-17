<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Shop | Ma5booz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/shop-style.css">
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="sub_banner_outer position-relative">
        <%@ include file="navbar.jsp"%>
        <!-- Sub Banner -->
        <!-- <section class="sub_banner position-relative">
            <div class="container position-relative">
                <div class="row">
                    <div class="col-12">
                        <div class="sub_banner_content text-center" data-aos="fade-up">
                            <h1>Shop</h1>
                            <div class="box">
                                <a href="${pageContext.request.contextPath}/home" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <span class="mb-0 box_span">Shop</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section> -->
    </div>
    <!-- Shop -->
    <section class="shop1-con classic-con position-relative">
        <div class="container">
            <div class="row">
                <div class="sidebar sticky-sidebar col-lg-3">
                    <div class="theiaStickySidebar">
                        <div class="widget widget-newsletter" data-aos="fade-up">
                            <form id="widget-search-form-sidebar" class="form-inline" action="${pageContext.request.contextPath}/shop" method="get">
                                <div class="input-group">
                                    <input type="text" aria-required="true" id="query" name="q" oninput="getSearchResultAsync(`${pageContext.request.contextPath}`)"
                                        class="form-control widget-search-form" placeholder="Search">
                                    <div class="input-group-append">
                                        <span class="input-group-btn">
                                            <button type="submit" id="widget-widget-search-form-button" class="btn"><i
                                                    class="fa fa-search"></i></button>
                                        </span>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="widget widget-categories category-box" data-aos="fade-up">
                            <div class="price-filter-title font_weight_600 mb-4">Categories</div>
                            <ul class="list-unstyled mb-0">
                                <c:forEach var="cat" items="${categories}">
                                    <div class="cat-item">
                                        <a href="#" onclick="markCategory(event); fetchProductsPerPage(event, 1,`${cat.id}`)" class="category-link">${cat.name}</a>
                                    </div>
                                </c:forEach>
                                <div class="cat-item" id="allCat">
                                    <a href="#" onclick="markCategory(event); fetchProductsPerPage(event, 1, 0)" class="category-link">all</a>
                                </div>
                            </ul>
                        </div>
                    
                            <div class="widget" data-aos="fade-up">
                                <!-- <div class="price-filter-title font_weight_600 mb-4">Price Range</div> -->
                                <div class="price-filter-widget">
                                    <div class="price-filter-title font_weight_600 mb-4">Price Range</div>
                                    <div class="price-input-group">
                                        <div class="price-input">
                                            <input type="number" 
                                                   id="minPrice" 
                                                   class="form-control price-input-field"
                                                   value="0" 
                                                   min="0" 
                                                   max="10000" 
                                                   step="10"
                                                   placeholder="Min">
                                        </div>
                                        <div class="price-input">
                                            <input type="number" 
                                                   id="maxPrice" 
                                                   class="form-control price-input-field"
                                                   value="1000" 
                                                   min="0" 
                                                   max="10000" 
                                                   step="10"
                                                   placeholder="Max">
                                        </div>
                                    </div>
                                    
                                    <button class="price-filter-apply" onclick="applyPriceFilter(event,1)">
                                        Apply Filters
                                    </button>
                                    
                                    <button class="price-filter-reset" onclick="resetHandler(event)">
                                        Reset Filters
                                    </button>
                                </div>
                            </div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="row default-sorting-con">
                        <div class="col-12">

                        </div>
                    </div>
                    <div class="d-flex justify-content-center mt-4 mb-4">
                        <ul class="pagination" id="pagination" data-aos="fade-up">
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="page-item">
                                    <a href="#" class="page-link" onclick="fetchProductsPerPage(event, `${i}`)">${i}</a>
                                </li>

                            </c:forEach>
                        </ul>
                    </div>
                    <div class="row shop-products-con" data-aos="fade-up">
                        <c:forEach var="product" items="${products}">
                            <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                                <div class="classic-box">
                                    <c:url value="product_details" var="detailsUrl">
                                        <c:param name="productID" value="${product.id}"/>
                                    </c:url>
                                    <a href="${detailsUrl}">
                                        <div class="classic_image_box box6">
                                            <figure class="mb-0">
                                                <img src="${pageContext.request.contextPath}/product-image/${product.imageUrl}" alt="image" class="img-fluid">
                                            </figure>
                                        </div>
                                    </a>
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
    </section>
    <script src="https://c...content-available-to-author-only...y.com/jquery-3.6.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/shop.js"></script>
    <script>
        window.contextPath = "${pageContext.request.contextPath}";
    </script>
    <!-- Footer -->
    <%@ include file="footer.jsp"%>
</body>
</html>