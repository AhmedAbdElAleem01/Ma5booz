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
              <table class="table table-hover tm-table-small tm-product-table">
                  <thead>
                    <tr>
                      <th scope="col">&nbsp;</th>
                      <th scope="col">PRODUCT NAME</th>
                      <th scope="col">UNIT PRICE</th>
                      <th scope="col">IN STOCK</th>
                      <th scope="col">&nbsp;</th>
                    </tr>
                  </thead>
                  <tbody>
                        <tr>
                            <th scope="row"><input type="checkbox" /></th>
                            <td>
                              <a href="${pageContext.request.contextPath}/admin/editProduct" style="color: white;">Product 1</a>
                            </td>
                            <td>550</td>
                            <td>1,450</td>
                            <td>
                              <a href="${pageContext.request.contextPath}/admin/deleteProduct" class="tm-product-delete-link">
                                <i class="far fa-trash-alt tm-product-delete-icon"></i>
                              </a>
                            </td>
                        </tr>
                      <tr>
                          <th scope="row"><input type="checkbox" /></th>
                          <td>
                            <a href="${pageContext.request.contextPath}/admin/editProduct" style="color: white;">Product 2</a>
                          </td>
                          <td>750</td>
                          <td>1,250</td>
                          <td>
                            <a href="${pageContext.request.contextPath}/admin/deleteProduct" class="tm-product-delete-link">
                              <i class="far fa-trash-alt tm-product-delete-icon"></i>
                            </a>
                          </td>
                      </tr>
                      <tr>
                        <th scope="row"><input type="checkbox" /></th>
                        <td>
                          <a href="${pageContext.request.contextPath}/admin/editProduct" style="color: white;">Product 3</a>
                        </td>
                        <td>900</td>
                        <td>1,100</td>
                        <td>
                          <a href="${pageContext.request.contextPath}/admin/deleteProduct" class="tm-product-delete-link">
                            <i class="far fa-trash-alt tm-product-delete-icon"></i>
                          </a>
                        </td>
                      </tr>
                      <tr>
                          <th scope="row"><input type="checkbox" /></th>
                          <td>
                            <a href="${pageContext.request.contextPath}/admin/editProduct" style="color: white;">Product 4</a>
                          </td>
                          <td>600</td>
                          <td>1,400</td>
                          <td>
                            <a href="${pageContext.request.contextPath}/admin/deleteProduct" class="tm-product-delete-link">
                              <i class="far fa-trash-alt tm-product-delete-icon"></i>
                            </a>
                          </td>
                      </tr>
                    </tbody>
                </table>
            </div>
            <!-- table container -->
            <a href="${pageContext.request.contextPath}/admin/addProduct" class="btn btn-primary btn-block text-uppercase mb-3">Add new product</a>
            <a href="${pageContext.request.contextPath}/admin/deleteProduct" class="btn btn-primary btn-block text-uppercase mb-3">Delete Selected Products</a>
          </div>
        </div>        
        <%-- End All Products Table --%>
        
        <%-- All Categories Table --%>
        <div class="col-sm-12 col-md-12 col-lg-4 col-xl-4 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-product-categories">
            <h2 class="tm-block-title">Product Categories</h2>
            <div class="tm-product-table-container">
              <table class="table tm-table-small tm-product-table">
                <tbody>
                  <tr>
                    <td class="tm-product-name">Product Category 1</td>
                    <td class="text-center">
                      <a href="${pageContext.request.contextPath}/admin/deleteCategory" class="tm-product-delete-link">
                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td class="tm-product-name">Product Category 2</td>
                    <td class="text-center">
                      <a href="${pageContext.request.contextPath}/admin/deleteCategory" class="tm-product-delete-link">
                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td class="tm-product-name">Product Category 3</td>
                    <td class="text-center">
                      <a href="${pageContext.request.contextPath}/admin/deleteCategory" class="tm-product-delete-link">
                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                      </a>
                    </td>
                  </tr>
                  <tr>
                    <td class="tm-product-name">Product Category 4</td>
                    <td class="text-center">
                      <a href="${pageContext.request.contextPath}/admin/deleteCategory" class="tm-product-delete-link">
                        <i class="far fa-trash-alt tm-product-delete-icon"></i>
                      </a>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
            <!-- table container -->
            <a href="${pageContext.request.contextPath}/admin/addCategory" class="btn btn-primary btn-block text-uppercase mb-3">Add new category</a>
          </div>
        </div>
      </div>
    </div>

    <%@ include file="footer.jsp"%>    
  </body>
</html>
