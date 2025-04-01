var currentCatID = 0;


function fetchProducts(e, path, catID) {
    e.preventDefault();
    if(!catID) catID = 0;
    currentCatID = catID;

    console.log("Selected Category ID:", currentCatID);

    let request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            console.log("AJAX Response:", response);
            renderProducts(response, path);
            updatePagination(response.currentPage, response.totalPages);
        }
    };

    request.open("GET", path + "/shop?catID=" + encodeURIComponent(catID), true);
    request.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    request.send();
}


function getSearchResultAsync(path) {
    let request = new XMLHttpRequest();
    let query = document.getElementById("query").value;

    currentCatID = 0;
    
    request.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            renderProducts(response, path);
        }
    };

    request.open("POST", path + "/shop", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.setRequestHeader("X-Requested-With", "XMLHttpRequest");
    request.send("q=" + encodeURIComponent(query));
}


function renderProducts(response, path) {
    let products = response.products || response;
    const container = document.querySelector(".shop-products-con");
    const contextPath = path;

    console.log("Rendering products:", products);
    
    // Build HTML for each product
    const productsHTML = products.map(product => {
        return `
            <div class="col-xl-4 col-lg-6 col-md-6 col-sm-6">
                <div class="classic-box">
                    <a href="${contextPath}/product_details?productID=${product.id}">
                        <div class="classic_image_box box6">
                            <figure class="mb-0">
                                <img src="${contextPath}/static/img/${product.imageUrl}" alt="${product.name}" class="img-fluid" loading="lazy">
                            </figure>
                        </div>
                    </a>
                    <div class="classic_box_content">
                        <div class="text_wrapper position-relative">
                            <h6>${product.name}</h6>
                            <div class="rating">
                                <i class="fa-solid fa-star"></i>
                                <span>4.9/5</span>
                            </div>
                        </div>
                        <p class="text-size-16">${product.description}</p>
                        <div class="price_wrapper position-relative">
                            <span class="dollar">$<span class="counter">${product.price}</span></span>
                            <a href="${contextPath}/views/user/cart.jsp?id=${product.id}">
                                <img src="${contextPath}/static/img/cart.png" alt="Cart" class="img-fluid">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }).join('');
    
    container.innerHTML = productsHTML;
}


function fetchProductsPerPage(event, page) {
    event.preventDefault();
    $.ajax({
        url: "shop",
        type: "POST",
        data: { page: page, catID: currentCatID },
        dataType: "json",
        headers: { "X-Requested-With": "XMLHttpRequest" },
        success: function(response) {
            renderProducts(response, window.contextPath);
            updatePagination(response.currentPage, response.totalPages);
            console.log("Pagination response:", response);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching products:", error);
        }
    });
}


function updatePagination(currentPage, totalPages) {
    let pagination = document.querySelector(".pagination");
    if (!pagination) {
        console.error("Pagination container not found.");
        return;
    }
    pagination.innerHTML = "";
    

    for (let i = 1; i <= totalPages; i++) {
        pagination.innerHTML += `
            <li class="page-item ${i === currentPage ? 'active' : ''}">
                <a class="page-link" onclick="fetchProductsPerPage(event, ${i})">${i}</a>
            </li>`;
    }
    
    if (currentPage < totalPages) {
        pagination.innerHTML += `
            <li class="page-item next">
                <a class="page-link" onclick="fetchProductsPerPage(event, ${currentPage + 1})">
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>`;
    }
}
