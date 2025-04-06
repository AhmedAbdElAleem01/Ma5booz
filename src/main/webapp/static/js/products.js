function deleteProduct(event, productId) {
    var row = event.target.closest('tr');
    $.ajax({
        url: `../admin/deleteProduct?id=${productId}`,
        type: "GET",
        headers: { "X-Requested-With": "XMLHttpRequest" },
        success: function(response) {
            if(response==="success")
                row.remove();
        },
        error: function(xhr, status, error) {
            console.error("Error deleting product:", error);
        }
    });
}
