let pageRefreshIntervalID = null;
let currentPage = 1;
var currentCatID = 0;

function startPageAutoRefresh(page , cat) {
    // Clear previous interval
    if (pageRefreshIntervalID !== null) {
        clearInterval(pageRefreshIntervalID);
    }
    currentPage = page;
    if (cat !== undefined) currentCatID = cat;
    console.log("Auto-refreshing page:", page, "category:", currentCatID);
    pageRefreshIntervalID = setInterval(() => {
        fetchProductsPerPage({ preventDefault: () => {} }, currentPage, currentCatID);
    }, 5000); // refresh every 2 seconds
}

function stopPageAutoRefresh() {
    if (pageRefreshIntervalID !== null) {
        clearInterval(pageRefreshIntervalID);
        pageRefreshIntervalID = null;
        console.log("Auto-refresh stopped.");
    }
}

function fetchProducts(e,path, catID) {
    e.preventDefault();
    markCategory(e);
    if(!catID) catID = 0;
    currentCatID = catID;


    console.log("Selected Category ID:", currentCatID);

    let request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            console.log("AJAX Response:", response);
            renderProducts(response, path);
            console.log("current in fetchProducts and page passed: "+ response.currentPage);
            currentPage = response.currentPage;
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
                <div class="classic-box fixed_height">
                    <a href="${contextPath}/product_details?productID=${product.id}">
                        <div class="classic_image_box box6">
                            <figure class="mb-0">
                                <img src="${contextPath}/product-image/${product.imageUrl}" alt="${product.name}" class="img-fluid" loading="lazy">
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
                            <span class="dollar">EGP <span class="counter">${product.price}</span></span>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }).join('');

    container.innerHTML = productsHTML;
}

function fetchProductsPerPage(event, page, cat) {
    if (event && typeof event.preventDefault === "function") {
        event.preventDefault();
    }
    if(cat !== undefined) currentCatID = cat;
    
    $.ajax({
        url: "shop",
        type: "POST",
        data: { page: page, catID: currentCatID },
        dataType: "json",
        headers: { "X-Requested-With": "XMLHttpRequest" },
        success: function(response) {
            currentPage = response.currentPage;
            renderProducts(response, window.contextPath);
            updatePagination(response.currentPage, response.totalPages);
            console.log("Pagination response:", response);
            console.log("page" + page)

            startPageAutoRefresh(currentPage, currentCatID);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching products:", error);
        }
    });
}


function updatePagination(currentPage, totalPages) {
    console.log("current in updatePagination with current page rec: " + currentPage );
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
                <a class="page-link d-flex justify-content-center align-items-center"
                onclick="fetchProductsPerPage(event, ${currentPage + 1})">
                    next
                </a>
            </li>`;

    }
}

function updatePagination2(currentPage, totalPages) {
    let pagination = document.querySelector(".pagination");
    if (!pagination) {
        console.error("Pagination container not found.");
        return;
    }
    pagination.innerHTML = "";
    

    for (let i = 1; i <= totalPages; i++) {
        pagination.innerHTML += `
            <li class="page-item ${i === currentPage ? 'active' : ''}">
                <a class="page-link" onclick="applyPriceFilter(event, ${i})">${i}</a>
            </li>`;
    }
    
    if (currentPage < totalPages) {
        pagination.innerHTML += `
            <li class="page-item next">
                <a class="page-link d-flex justify-content-center align-items-center"
                onclick="applyPriceFilter(event, ${currentPage + 1})">
                    next
                </a>
            </li>`;

    }
}


function applyPriceFilter(event, page , cat) {
    event.preventDefault();
    stopPageAutoRefresh();
    if(cat !== undefined) currentCatID = cat;
    let minEle = document.getElementById('minPrice');
    let maxEle = document.getElementById('maxPrice');
    min = parseFloat(minEle.value);
    max = parseFloat(maxEle.value);
    $.ajax({
        url: "shop",
        type: "POST",
        data: { page: page, catID: currentCatID , minPrice: min, maxPrice:max},
        dataType: "json",
        headers: { "X-Requested-With": "XMLHttpRequest" },
        success: function(response) {
            renderProducts(response, window.contextPath);
            updatePagination2(response.currentPage, response.totalPages);
            console.log("Pagination response:", response);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching products:", error);
        }
    });
}

function markCategory(event) {
    const categories = document.querySelectorAll('.category-link');
    categories.forEach(category => {
        category.classList.remove('selected-category');
    });

    event.preventDefault();
    event.currentTarget.classList.add('selected-category');
    console.log(event.currentTarget);
}

function resetHandler(event) {
    fetchProductsPerPage(event, 1, currentCatID);
    startPageAutoRefresh(currentPage, currentCatID);
    document.getElementById('minPrice').value = 0;
    document.getElementById('maxPrice').value = 1000;
}

