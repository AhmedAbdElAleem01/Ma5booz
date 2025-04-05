<html>
<head>
    <title>Error Page | IceDelights</title>
</head>
<body>
    <%@ include file="header.jsp"%>
    <!-- Error -->
    <div class="error_page position-relative">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="error_content position-relative text-center">
                        <h1 style="color: white;">Error</h1>
                        <h2 class="text" style="color: white;"><b>Sorry! A problem ocurred ;(</b></h2>
                        <h3 class="col-xl-8 col-lg-10 mx-auto text2" id="error-message" style="color: white;"></h3>
                        <a href="${pageContext.request.contextPath}/views/admin/home" class="back_home text-decoration-none" style="color: white;"><i class="fas fa-arrow-left"></i>Back to Home</a>
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