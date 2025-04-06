<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title> Add New Product | IceDelights</title>
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
                <h2 class="tm-block-title d-inline-block">Add Product</h2>
              </div>
            </div>
            <div class="row tm-edit-product-row">
              <div class="col-xl-6 col-lg-6 col-md-12">
                <form action="${pageContext.request.contextPath}/admin/addProduct" method="post" class="tm-edit-product-form" enctype="multipart/form-data">
                  <div class="form-group mb-3">
                    <label for="name">Product Name</label>
                    <input id="name" name="name" type="text" class="form-control validate"required />
                  </div>
                  <div class="form-group mb-3">
                    <label for="description">Description</label>
                    <textarea class="form-control validate" rows="3" name="description" required></textarea>
                  </div>
                  <div class="form-group mb-3">
                    <label for="category">Category</label>
                    <select class="custom-select tm-select-accounts" name="category">
                      <option selected>Select category</option>
                      <option value="Cakes">Cakes</option>
                      <option value="Cupcakes">Cupcakes</option>
                      <option value="Ice cream">Ice cream</option>
                      <option value="Seasonal Flavours">Seasonal Flavours</option>
                    </select>
                  </div>
                  <div class="row">
                    <div class="form-group mb-3 col-xs-12 col-sm-6">
                        <label for="price">Unit Price</label>
                        <input id="price" name="price" type="text" class="form-control validate" data-large-mode="true"/>
                    </div>
                    <div class="form-group mb-3 col-xs-12 col-sm-6">
                      <label for="stock">Units In Stock </label>
                      <input id="stock" name="stock" type="number" class="form-control validate" required />
                    </div>
                  </div>
              </div>          
              <div class="col-xl-6 col-lg-6 col-md-12">
                <div class="form-group mb-3">
                  <label for="ingredients">Ingredients</label>
                  <textarea class="form-control validate" rows="3" name="ingredients" required></textarea>
                </div>
                <div class="form-group mb-3">
                    <label for="image">Upload Product Image</label>
                    <input id="image" name="image" type="file" class="form-control validate" accept="image/*" required />
                </div>
              </div>
              <div class="col-12">
                <button type="submit" class="btn btn-primary btn-block text-uppercase">Add Product</button>
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
