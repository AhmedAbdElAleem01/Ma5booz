<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title> Admin Dashboard | IceDelights</title>
        <%@ include file="header.jsp"%>
    </head>

    <body id="reportsPage">
        <div class="" id="home">
            <%@ include file="navbar.jsp"%>
            
            <%-- Dashboard Page --%>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <p class="text-white mt-5 mb-5">Welcome , <b>Admin</b></p>
                    </div>
                </div>
                <!-- row -->
                <div class="row tm-content-row">
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block text-center text-white p-4">
                            <h2 class="tm-block-title text-white text-lg font-bold">Manage Products</h2>
                            <img src="${pageContext.request.contextPath}/static/img/manage-product.png" alt="Manage Products" class="img-fluid my-3">
                            <p class="text-white">View, add, edit, and remove product prices and quantities.</p>
                            <a href="${pageContext.request.contextPath}/admin/products" class="mt-5 btn btn-primary btn-block text-uppercase">
                                Go to Page <i class="fas fa-arrow-right"></i>
                            </a>
                        </div>
                    </div>

                <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                    <div class="tm-bg-primary-dark tm-block text-center text-white p-4">
                        <h2 class="tm-block-title text-white text-lg font-bold">Review Customer Profiles</h2>
                        <img src="${pageContext.request.contextPath}/static/img/customers.png" alt="Customer Profiles" class="img-fluid my-3">
                        <p class="text-white">Review customer profiles includig their order history efficiently.</p>
                        <a href="${pageContext.request.contextPath}/admin/customers" class="mt-5 btn btn-primary btn-block text-uppercase">
                            Go to Page <i class="fas fa-arrow-right"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <%-- End Dashboard Page --%>

        <%@ include file="footer.jsp"%>
    </body>
</html>