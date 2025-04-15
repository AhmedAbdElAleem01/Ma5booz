<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in | Ma5booz</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="sub_banner_outer position-relative">
        <%@ include file="navbar.jsp"%>
        <%-- Sub Banner  --%>
        <section class="sub_banner position-relative">
            <div class="container position-relative">
                <div class="row">
                    <div class="col-12">
                        <div class="sub_banner_content text-center" data-aos="fade-up">
                            <h1>Sign in</h1>
                            <div class="box">
                                <a href="${pageContext.request.contextPath}/views/user/home" class="text-decoration-none">
                                    <span class="mb-0">Home</span>
                                </a>
                                <span class="mb-0 slash">/</span>
                                <span class="mb-0 box_span">Sign in</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <%-- Sign in  --%>
    <div class="cart-con checkout-con position-relative">
        <div class="container">
            <p id="error-message" style="visibility: hidden; color: red;"></p>
            <div class="row">
                <div class="col-lg-8 col-12">
                    <div class="product-detail-box">
                        <form id="login" method="post" action="${pageContext.request.contextPath}/login" class="position-relative">
                            <div class="upper-form" data-aos="fade-up">
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form_style" name="email" id="email" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form_style" name="password" id="password" required>
                                </div>
                                <p class="text-center text-size-14">
                                    Don't have an account? 
                                    <a href="${pageContext.request.contextPath}/views/user/register.jsp" class="text-decoration-none terms-btn">Sign up</a>
                                </p>
                            </div>
                            <button data-aos="fade-up" type="submit" id="submit" class="submit_now text-decoration-none">Log in</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%-- End Sign in --%>
    <%@ include file="footer.jsp"%>
    <script src="<c:url value='/static/js/login.js' />" type="text/javascript"></script>
</body>
</html>