<html>
<head>
    <title>Thank You</title>
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
                            <p> Your account has been successfully created. You can now log in and start exploring our services. </p>
                            <a href="${pageContext.request.contextPath}/login" class="text-decoration-none all_button">Log in Now?</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div><%@ include file="footer.jsp"%>
</body>
</html>