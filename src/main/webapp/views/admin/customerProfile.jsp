<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                <input id="name" name="name" type="text" class="form-control validate" value="${fn:escapeXml(customer.name)}"/>
              </div>
              <div class="form-group col-lg-6">
                <label for="email">Email</label>
                <input id="email" name="email" type="text" class="form-control validate" value="${fn:escapeXml(customer.email)}"/>
              </div>
              <div class="form-group col-lg-6">
                <label for="mobile">Phone Number</label>
                <input id="mobile" name="mobile" type="text" class="form-control validate" value="${fn:escapeXml(customer.phoneNumber)}"/>
              </div>
              <div class="form-group col-lg-6">
                <label for="creditLimit">Credit Limit</label>
                <input id="creditLimit" name="creditLimit" type="text" class="form-control validate" value="${fn:escapeXml(customer.creditLimit)}"/>
              </div>
              <div class="form-group col-lg-6">
                <label for="birthDate">Birth Date</label>
                <input id="birthDate" name="birthDate" type="text" class="form-control validate" value="${fn:escapeXml(customer.birthDate)}"/>
              </div>
              <div class="form-group col-lg-6">
                <label for="job">Job</label>
                <input id="job" name="job" type="text" class="form-control validate" value="${fn:escapeXml(customer.job)}"/>
              </div>
              <div class="form-group col-lg-12">
                <label for="address">Address</label>
                <input id="address" name="address" type="text" class="form-control validate" value="${fn:escapeXml(address)}"/>
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
                      <th scope="col">&nbsp;</th>
                    </tr>
                  </thead>
                  <tbody id="orders-body">
                    <c:choose>
                      <c:when test="${not empty orders}">
                        <c:forEach var="order" items="${orders}">
                          <tr>
                            <td><b>${order.id}</b></td>
                            <td><b>${order.totalCost}</b></td>
                            <td>${order.orderedAt}</td>
                            <td>${order.status}</td>
                            <td>
                              <a href="${pageContext.request.contextPath}/admin/orderDetails?id=${order.id}" class="tm-user-view-link">
                                <i class="fas fa-eye tm-product-delete-icon"></i>
                              </a>
                            </td>
                          </tr>
                        </c:forEach>
                      </c:when>
                      <c:otherwise>
                        <tr>
                          <td colspan="5" style="text-align: center;">No orders yet.</td>
                        </tr>
                      </c:otherwise>
                    </c:choose>
                  </tbody>
                </table>
            </div>
        </div>       
      </div>
      <%-- End Customer Profile --%>
    </div>
    <%@ include file="footer.jsp" %>
    <script src="${pageContext.request.contextPath}/static/js/customerProfile.js"></script> 
  </body>
</html>
