<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title> Edit Product | IceDelights</title>
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
                <h2 class="tm-block-title d-inline-block">Edit Product</h2>
              </div>
            </div>
            <div class="row tm-edit-product-row">
              <div class="col-xl-6 col-lg-6 col-md-12">
                <form action="${pageContext.request.contextPath}/admin/editProduct" method="post" class="tm-edit-product-form" enctype="multipart/form-data">
                  <div class="form-group mb-3">
                    <label for="name">Product Name</label>
                    <input id="name" name="name" type="text" class="form-control validate" value="Product 1" required />
                  </div>
                  <div class="form-group mb-3">
                    <label for="description">Description</label>
                    <textarea class="form-control validate" rows="3" value="description of product 1 .." required></textarea>
                  </div>
                  <div class="form-group mb-3">
                    <label for="category">Category</label>
                    <select class="custom-select tm-select-accounts" id="category">
                      <option selected>Select category</option>
                      <option value="1" selected>Cakes</option>
                      <option value="2">CupCakes</option>
                      <option value="3">Ice Cream</option>
                      <option value="4">Seasional Ice Cream</option>
                    </select>
                  </div>
                  <div class="row">
                    <div class="form-group mb-3 col-xs-12 col-sm-6">
                        <label for="price">Unit Price</label>
                        <input id="price" name="price" type="text" value= "55.9$" class="form-control validate" data-large-mode="true"/>
                    </div>
                    <div class="form-group mb-3 col-xs-12 col-sm-6">
                      <label for="stock">Units In Stock </label>
                      <input id="stock" name="stock" type="text" value="1200" class="form-control validate" required />
                    </div>
                  </div>
              </div>                  
              <div class="form-group mb-3">
                  <img src="${pageContext.request.contextPath}/static/img/product-image.jpg" alt="Product image" class="img-fluid d-block mx-auto">
                  <label for="image">Upload New Product Image</label>
                  <input id="image" name="image" type="file" class="form-control validate" accept="image/*" required />
              </div>
              <div class="col-12">
                <button type="submit" class="btn btn-primary btn-block text-uppercase">Update Product</button>
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
