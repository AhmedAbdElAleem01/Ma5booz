<nav class="navbar navbar-expand-xl">
    <div class="container h-100">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/views/admin/home.jsp">
            <h1 class="tm-site-title mb-0">Admin</h1>
        </a>
        <button class="navbar-toggler ml-auto mr-0" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-bars tm-nav-icon"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mx-auto h-100">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/home">
                        <i class="fas fa-home"></i>
                        Home
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/products">
                        <i class="fas fa-shopping-cart"></i>
                        Products
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/customers">
                        <i class="far fa-user"></i>
                        Accounts
                    </a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link d-block" href="${pageContext.request.contextPath}/logout">
                        <b>Logout</b>
                    </a>
                </li>
            </ul>
        </div>
    </div>

</nav>