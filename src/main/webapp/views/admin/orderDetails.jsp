<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title> Order Details | IceDelights</title>
    <%@ include file="header.jsp" %>
  </head>

  <body id="reportsPage">
    <div class="" id="home">
      <%@ include file="navbar.jsp" %>
      <%-- Order Details --%>
      <div class="container">
        <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
                <h2 class="tm-block-title">Order</h2>
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col">PRODUCT</th>
                      <th scope="col">QUANTITY</th>
                      <th scope="col">PRICE</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                        <td>Product 1</td>
                        <td>3</td>
                        <td><b>120$</b></td>
                    </tr>
                    <tr>
                        <td>Product 2</td>
                        <td>2</td>
                        <td><b>80$</b></td>
                    </tr>
                    <tr>
                        <td>Product 3</td>
                        <td>1</td>
                        <td><b>90$</b></td>
                    </tr>
                    <tr>
                        <td>Product 4</td>
                        <td>3</td>
                        <td><b>120$</b></td>
                    </tr>
                  </tbody>
                </table>
            </div>
        </div>       
      </div>
      <%-- End Order Details --%>
    </div>
    <%@ include file="footer.jsp" %>
  </body>
</html>
