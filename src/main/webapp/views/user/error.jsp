<html>
<head>
    <title>Error Page | Ma5booz</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    <!-- Error -->
    <div class="error_page position-relative">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="error_content position-relative text-center">
                        <h1>Error</h1>
                        <p class="text">Sorry! A problem ocurred ;(</p>
                        <p class="col-xl-8 col-lg-10 mx-auto text2" id="error-message"></p>
                        <a href="${pageContext.request.contextPath}/home" class="back_home text-decoration-none"><i class="fa-solid fa-arrow-left"></i>Back to Home</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer -->
    <%@ include file="footer.jsp"%>
    <script>
        let urlParams = new URLSearchParams(window.location.search);    
        if(urlParams.has("error-message")){
            let error =  document.getElementById('error-message'); 
            error.textContent = urlParams.get("error-message");
        }
    </script>
</html>