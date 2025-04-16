var maxPerOrder = 5;
var increaseBtn;
var decreaseBtn;
var productQEle;
var productQInt;
var cartIcon;
var cartIconNum;
var addtoCartBtn;
var messageEle;

function increaseValue() {
    productQInt = parseInt(productQEle.textContent, 10);
    let availableQ = Math.min(maxPerOrder, stock);
    
    if (availableQ - productQInt >= 1) {
        productQInt++;
        decreaseBtn.disabled = false;
        if (availableQ - productQInt === 0) {
            increaseBtn.disabled = true;
            let message = availableQ == maxPerOrder ? "You can only have 5 per order" : `Only ${stock} available now`;
            messageEle.style.color = 'red'
            messageEle.textContent = message;

            setTimeout(()=>{
                messageEle.textContent = '';
            }, 3000);
        }
    }
    
    productQEle.textContent = productQInt;
}

function decreaseValue() {
    productQInt = parseInt(productQEle.textContent, 10);
    
    if (productQInt > 1) {
        productQInt--;
        if (productQInt == 1) {
            decreaseBtn.disabled = true;
        }
        increaseBtn.disabled = false;
    }
    productQEle.textContent = productQInt;
}

function addToCartAsync() {
    $.ajax({
        url: "cart",
        type: "POST",
        data: { productID: productId, quantity: productQInt },
        dataType: "json",
        headers: { "X-Requested-With": "XMLHttpRequest" },
        success: function(response) {
            cartIcon.textContent = response.cartSize;
            messageEle.style.color = 'green';
            messageEle.textContent = productQInt + " items added to cart!"
            messageEle.style.transform = 'translateY(0px)';

            setTimeout(() => {
                messageEle.textContent = "";
                messageEle.style.transform = 'translateY(-10px)';
            }, 3000);
            console.log("Pagination response:", response);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching products:", error);
        }
    });
}

document.addEventListener('DOMContentLoaded', function () {
    productQEle = document.getElementById("productQ");
    increaseBtn = document.querySelector('.value-button.increase-button');
    decreaseBtn = document.querySelector('.value-button.decrease-button');
    addtoCartBtn = document.querySelector(".text-decoration-none.all_button");
    cartIcon = document.getElementById('cartIcon');
    messageEle = document.getElementById("message");
    cartIconNum = parseInt(cartIcon.textContent);

    productQInt = 1;

    if (decreaseBtn) { decreaseBtn.disabled = true; }
    console.log(stock);
    if (stock <= 0) {
        if (increaseBtn) { increaseBtn.disabled = true; }
        if(addtoCartBtn) { addtoCartBtn.disabled = true;}
        productQInt = 0;
    }

    productQEle.textContent = productQInt;
});
