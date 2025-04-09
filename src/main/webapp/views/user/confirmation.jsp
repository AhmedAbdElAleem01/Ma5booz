<html>
<head>
    <title>Thank You | IceDelights</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    <div class="sub_banner_outer position-relative">
        <%@ include file="navbar.jsp"%>
        <!-- Sub Banner -->
        <section class="thank-you position-relative">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-12 mx-auto">
                        <div class="thank_you_content text-center" data-aos="fade-up">
                            <figure class="thankyou-image">
                                <img src="${pageContext.request.contextPath}/static/img/thankyou-image.png" alt="image" class="img-fluid">
                            </figure>
                            <h1>Thank You!</h1>
                            <p>We're delighted you've decided to treat yourself to our delectable products.
                                Your order has been received and is now being prepared with care. We've also sent you an email with your order details, be sure to check it out.
                            </p>
                            <a href="${pageContext.request.contextPath}/home" class="text-decoration-none all_button"><i class="fa-solid fa-arrow-left"></i>Back to Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div><%@ include file="footer.jsp"%>
</body>
</html>