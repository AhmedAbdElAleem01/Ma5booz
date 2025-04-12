<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Footer Section --%>
<section class="footer-con position-relative">
    <div class="container">
        <div class="middle_portion">
            <div class="row">
                <div class="col-lg-2 col-md-12 col-sm-6 col-12 order-md-1 order-sm-1 order-1">
                    <a href="${pageContext.request.contextPath}/views/user/home.jsp">
                        <figure class="footer-logo mb-0">
                            <img class="img-fluid" src="${pageContext.request.contextPath}/static/img/testlogo.png" alt="image">
                        </figure>
                    </a>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 col-12 order-md-2 order-sm-4 order-2">
                    <div class="links">
                        <h5 class="heading">Navigation</h5>
                        <div class="pages">
                            <ul class="list-unstyled mb-0 list1">
                                <li><i class="fas fa-circle"></i><a href="${pageContext.request.contextPath}/views/user/home.jsp">Home</a></li>
                                <li><i class="fas fa-circle"></i><a href="${pageContext.request.contextPath}/views/user/shop.jsp">Shop</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 col-12 order-md-3 order-sm-3 order-3">
                    <div class="info">
                        <ul class="list-unstyled mb-0">
                            <li>
                                <i class="fa-solid fa-location-dot"></i>
                                <div class="text">
                                    <span>Address:</span>
                                    <a href="https://www.google.com/maps/place/121+King+St,+Melbourne+VIC+3000,+Australia/@-37.8172467,144.9532001,17z/data=!3m1!4b1!4m6!3m5!1s0x6ad65d4dd5a05d97:0x3e64f855a564844d!8m2!3d-37.817251!4d144.955775!16s%2Fg%2F11g0g8c54h?entry=ttu"
                                        class="text-decoration-none address mb-0">ITI, Smart Village-Egypt
                                    </a>
                                </div>
                            </li>
                            <li>
                                <i class="fas fa-envelope-open-text"></i>
                                <div class="text">
                                    <span>Email:</span>
                                    <a href="mailto:info@icedelights.com" class="text-decoration-none">ma5boozbakeryshop@gmail.com</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 col-12 order-md-4 order-sm-2 order-4">
                    <div class="icon">
                        <div class="phone_wrapper">
                            <i class="fa-solid fa-phone phone"></i>
                            <div class="phone_content">
                                <a href="tel:+568925896325" class="text-decoration-none d-block">17002</a>
                                <span>Got Questions? Call us available 24/7</span>
                            </div>
                        </div>
                        <ul class="list-unstyled mb-0 social-icons">
                            <li><a href="https://w...content-available-to-author-only...k.com/"><i class="fa-brands fa-facebook-f" aria-hidden="true"></i></a></li>
                            <li><a href="https://t...content-available-to-author-only...r.com/"><i class="fa-brands fa-x-twitter" aria-hidden="true"></i></a></li>
                            <li><a href="https://w...content-available-to-author-only...m.com/"><i class="fa-brands fa-instagram" aria-hidden="true"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="copyright">
            <p class="mb-0">Copyright © 2024 icedelights All rights reserved.</p>
        </div>
    </div>
</section>
<%-- End Footer --%>

<%-- PRE LOADER  --%>
<div class="loader-mask">
    <div class="loader">
        <div></div>
        <div></div>
    </div>
</div>

<%-- JS Plugins --%>
<script src="<c:url value='/static/js/jquery-3.7.1.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/popper.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/bootstrap.min.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/aos.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/owl.carousel.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/carousel.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/animation.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/back-to-top-button.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/preloader.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/search.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/remove-product.js' />" type="text/javascript"></script>
<script src="<c:url value='/static/js/quantity.js' />" type="text/javascript"></script>

