var maxPerOrder = 5;

// function removeFromCartAsync(id, q) {
//     console.log(id, q);
//     $.ajax({
//         url: "cart",
//         type: "POST",
//         data: { productID: id, quantity: q },
//         dataType: "json",
//         headers: { "X-Requested-With": "XMLHttpRequest" },
//         success: function (response) {
//             console.log("Item removed:", response);
//         },
//         error: function (xhr, status, error) {
//             console.error("Error removing item:", error);
//         }
//     });
// }

function increaseValue(id, stock) {
    let productQEle = document.getElementById(`quantity-${id}`);
    let increaseBtn = document.getElementById(`increase-${id}`);
    let decreaseBtn = document.getElementById(`decrease-${id}`);
    let totalPrice = document.getElementById(`totalPrice-${id}`);
    let productQInt = parseInt(productQEle.textContent, 10);

    
    let availableQ = Math.min(maxPerOrder, stock);
    if (availableQ - productQInt > 0) {
        productQInt++;
        totalPrice.innerHTML = `<span>${(productQInt * parseFloat(totalPrice.dataset.price)).toFixed(2)}</span>`
        productQEle.textContent = productQInt;
        updateCartAsync(id, productQInt);
        
        decreaseBtn.disabled = false;
        if (productQInt >= availableQ) {
            increaseBtn.disabled = true;
        }
    }
}

function decreaseValue(id) {
    let productQEle = document.getElementById(`quantity-${id}`);
    let increaseBtn = document.getElementById(`increase-${id}`);
    let decreaseBtn = document.getElementById(`decrease-${id}`);
    let totalPrice = document.getElementById(`totalPrice-${id}`);
    let productQInt = parseInt(productQEle.textContent, 10);


    if (productQInt > 1) {
        productQInt--;
        productQEle.textContent = productQInt;
        
        totalPrice.innerHTML = `<span>${(productQInt * parseFloat(totalPrice.dataset.price)).toFixed(2)}</span>`
        updateCartAsync(id, productQInt);
        
        if (productQInt == 1) {
            decreaseBtn.disabled = true;
        }
        increaseBtn.disabled = false;
    }
}

function updateCartAsync(id, quantity) {
    $.ajax({
        url: "cart",
        type: "POST",
        data: { productID: id, quantity: quantity },
        dataType: "json",
        headers: { "X-Requested-With": "XMLHttpRequest" },
        success: function (response) {
            let data = typeof response === "string" ? JSON.parse(response) : response;
            document.getElementById("totalItems").textContent = parseInt(data.cartSize);
            document.getElementById("totalOrderPrice").textContent = parseFloat(data.totalPrice).toFixed(2);
            document.getElementById("grandTotal").textContent = (parseFloat(data.totalPrice)).toFixed(2);
            cartIcon.textContent = response.cartSize;
            if (parseInt(data.cartSize) === 0){
                console.log("cart is empty now...");
                // disable the proceed to checkout button
                const checkoutBtn = document.querySelector(".all_button");
                if (checkoutBtn) {
                    checkoutBtn.classList.add("disabled");
                    checkoutBtn.style.pointerEvents = "none";
                    checkoutBtn.setAttribute("onclick", "return false;");
                    checkoutBtn.setAttribute("href", "#");
                }
            }
            console.log("Cart updated:", response);
        },
        error: function (xhr, status, error) {
            console.error("Error updating cart:", error);
        }
    });
}
