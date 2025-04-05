<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title> Products Management| IceDelights</title>
    <%@ include file="header.jsp" %>
  </head>

  <body id="reportsPage">
    <%@ include file="navbar.jsp" %>
    <%-- Products Page --%>
    <div class="container mt-5">
      <div class="row tm-content-row">
        <%-- All Products Table --%>
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table tm-block-scroll">
                  <thead>
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">NAME</th>
                      <th scope="col">CATEGORY</th>
                      <th scope="col">DESCRIPTION</th>
                      <th scope="col">UNIT PRICE</th>
                      <th scope="col">IN STOCK</th>
                      <th scope="col">&nbsp;</th>
                      <th scope="col">&nbsp;</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="product" items="${products}">
                      <tr>
                        <td>${product.id}</td> 
                        <td>${product.name}</td>
                        <td>${product.categoryName}</td> 
                        <td>${product.description}</td> 
                        <td>${product.price}</td> 
                        <td>${product.stockQuantity}</td> 
                        <td>
                          <a href="${pageContext.request.contextPath}/admin/editProduct?id=${product.id}" class="tm-product-edit-link">
                              <i class="fas fa-edit tm-product-delete-icon"></i>
                          </a>
                        </td>
                        <td>
                          <a href="javascript:void(0);" class="tm-product-delete-link" onclick="deleteProduct(event,${product.id})">
                              <i class="far fa-trash-alt tm-product-delete-icon"></i>
                          </a>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
            </div>
            <!-- table container -->
            <a href="${pageContext.request.contextPath}/admin/addProduct" class="btn btn-primary btn-block text-uppercase mb-3">Add new product</a>
          </div>
        </div>        
        <%-- End All Products Table --%>
      </div>
    </div>
    <%@ include file="footer.jsp"%>   
    <script src="${pageContext.request.contextPath}/static/js/products.js"></script> 
  </body>
</html>
