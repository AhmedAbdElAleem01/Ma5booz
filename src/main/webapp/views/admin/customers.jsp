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
              <table class="table table-hover tm-table-small tm-product-table">
                  <thead>
                    <tr>
                      <th scope="col">USERNAME</th>
                      <th scope="col">EMAIL</th>
                      <th scope="col">PHONE NUMBER</th>
                    </tr>
                  </thead>
                  <tbody>
                      <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/customerProfile" style="color: white;">username_1</a>
                        </td>
                          <td>email@example.com</td>
                          <td>01007043501</td>
                      </tr>
                      <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/customerProfile" style="color: white;">username_2</a>
                        </td>
                          <td>email@example.com</td>
                          <td>01007043501</td>
                      </tr>
                      <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/customerProfile" style="color: white;">username_3</a>
                        </td>
                          <td>email@example.com</td>
                          <td>01007043501</td>
                      </tr>
                      <tr>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/customerProfile" style="color: white;">username_4</a>
                        </td>
                          <td>email@example.com</td>
                          <td>01007043501</td>
                      </tr>
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
