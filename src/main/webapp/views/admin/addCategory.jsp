<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title> Add New Category | IceDelights</title>
    <%@ include file="header.jsp" %>
  </head>

  <body>
    <%@ include file="navbar.jsp" %>

    <%-- Add New Product --%>
    <div class="container tm-mt-big tm-mb-big">
      <div class="row">
        <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
          <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
            <div class="row">
              <div class="col-12">
                <h2 class="tm-block-title d-inline-block">Add Category</h2>
              </div>
            </div>
            <div class="row tm-edit-product-row">
              <div class="col-xl-6 col-lg-6 col-md-12">
                <form action="${pageContext.request.contextPath}/admin/addCategory" method="post" class="tm-edit-product-form" enctype="multipart/form-data">
                  <div class="form-group mb-3">
                    <label for="name">Category Name</label>
                    <input id="name" name="name" type="text" class="form-control validate"required />
                  </div>
                  <div class="form-group mb-3">
                    <label for="description">Description</label>
                    <textarea class="form-control validate" rows="3" required></textarea>
                  </div>
              </div>                  
              <div class="form-group mb-3">
                  <label for="image">Upload Category Image</label>
                  <input id="image" name="image" type="file" class="form-control validate" accept="image/*" required />
              </div>
              <div class="col-12">
                <button type="submit" class="btn btn-primary btn-block text-uppercase">Add Category</button>
              </div>
            </div>
          </form>
        </div>
      </div>
      </div>
    </div>
    <%-- End Add New Product --%>
    <%@ include file="footer.jsp" %>
  </body>
</html>
