<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title> Customer Profile | IceDelights</title>
    <%@ include file="header.jsp" %>
  </head>

  <body id="reportsPage">
    <div class="" id="home">
      <%@ include file="navbar.jsp" %>
      <%-- Customer Profile --%>
      <div class="container">
        <div class="tm-block-col tm-col-account-settings">
          <div class="tm-bg-primary-dark tm-block tm-block-settings">
            <h2 class="tm-block-title">Account Details</h2>
            <form action="" class="tm-signup-form row">
              <div class="form-group col-lg-6">
                <label for="name">Name</label>
                <input id="name" name="name" type="text" class="form-control validate" disabled/>
              </div>
              <div class="form-group col-lg-6">
                <label for="email">Email</label>
                <input id="email" name="email" type="text" class="form-control validate" disabled/>
              </div>
              <div class="form-group col-lg-6">
                <label for="mobile">Phone Number</label>
                <input id="mobile" name="mobile" type="text" class="form-control validate" disabled/>
              </div>
              <div class="form-group col-lg-6">
                <label for="creditLimit">Credit Limit</label>
                <input id="creditLimit" name="creditLimit" type="text" class="form-control validate" disabled/>
              </div>
              <div class="form-group col-lg-6">
                <label for="birthDate">Birth Date</label>
                <input id="birthDate" name="birthDate" type="text" class="form-control validate" disabled/>
              </div>
              <div class="form-group col-lg-6">
                <label for="job">Job</label>
                <input id="job" name="job" type="text" class="form-control validate" disabled/>
              </div>
              <div class="form-group col-lg-12">
                <label for="address">Address</label>
                <input id="address" name="address" type="text" class="form-control validate" disabled/>
              </div>
            </form>
          </div>
        </div>
        <div class="col-12 tm-block-col">
            <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
                <h2 class="tm-block-title">Orders History</h2>
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col">ORDER ID</th>
                      <th scope="col">TOTAL COST</th>
                      <th scope="col">ORDERED DATE</th>
                      <th scope="col">STATUS</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                        <th scope="row"><a href="${pageContext.request.contextPath}/admin/orderDetails" style="color: white;"><b>#122349</b></a></th>
                        <td><b>120$</b></td>
                        <td>16:00, 12 NOV 2018</td>
                        <td>SHIPPED</td>
                    </tr>
                    <tr>
                        <th scope="row"><a href="${pageContext.request.contextPath}/admin/orderDetails" style="color: white;"><b>#124449</b></a></th>
                        <td><b>60.7$</b></td>
                        <td>12:00, 22 NOV 2018</td>
                        <td>PENDING</td>
                    </tr>
                    <tr>
                        <th scope="row"><a href="${pageContext.request.contextPath}/admin/orderDetails" style="color: white;"><b>#122348</b></a></th>
                        <td><b>37.9$</b></td>
                        <td>16:00, 12 NOV 2018</td>
                        <td>CANCELLED</td>
                    </tr>
                    <tr>
                        <th scope="row"><a href="${pageContext.request.contextPath}/admin/orderDetails" style="color: white;"><b>#140349</b></a></th>
                        <td><b>81.2$</b></td>
                        <td>16:00, 12 NOV 2018</td>
                        <td>FAILED</td>
                    </tr>
                  </tbody>
                </table>
            </div>
        </div>       
      </div>
      <%-- End Customer Profile --%>
    </div>
    <%@ include file="footer.jsp" %>
  </body>
</html>
