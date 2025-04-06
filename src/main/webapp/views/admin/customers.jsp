<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title> Customers Review | IceDelights</title>
    <%@ include file="header.jsp" %>
  </head>

  <body id="reportsPage">
    <%@ include file="navbar.jsp" %>
    <%-- Customers Page --%>
    <div class="container">
      <div class="row tm-content-row">
        <%-- All Customers Table --%>
        <div class="col-sm-12 col-md-12 col-lg-8 col-xl-8 tm-block-col">
          <div class="tm-bg-primary-dark tm-block tm-block-products">
            <div class="tm-product-table-container">
              <table class="table table-hover tm-table-small tm-product-table tm-block-scroll">
                  <thead>
                    <tr>
                      <th scope="col">ID</th>
                      <th scope="col">USERNAME</th>
                      <th scope="col">EMAIL</th>
                      <th scope="col">PHONE NUMBER</th>
                      <th scope="col">CREATED AT</th>
                      <th scope="col">&nbsp;</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="customer" items="${customers}">
                      <tr>
                        <td>${customer.id}</td>
                        <td>${customer.username}</td>
                        <td>${customer.email}</td>
                        <td>${customer.phoneNumber}</td>
                        <td>${customer.createdAt}</td>
                        <td>
                          <a href="${pageContext.request.contextPath}/admin/customerProfile?id=${customer.id}" class="tm-user-view-link">
                            <i class="fas fa-arrow-right tm-product-delete-icon"></i>
                          </a>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
            </div>
          </div>
        </div>        
        <%-- End All Customers Table --%>
      </div>
    </div>
    <%@ include file="footer.jsp"%>   
  </body>
</html>
