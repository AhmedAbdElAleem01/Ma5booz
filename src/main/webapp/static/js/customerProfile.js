$(document).ready(function () {
    const customerId = new URLSearchParams(window.location.search).get("id");
    setInterval(() => fetchOrders(customerId), 10000); // Fetch every 10 seconds
});

function renderOrders(orders) {
    const $tbody = $("#orders-body");

    if (!orders || orders.length === 0) {
        $tbody.html(`<tr><td colspan="5">No Orders Yet</td></tr>`);
        return;
    }

    const ordersHTML = orders.map(order => `
        <tr>
            <td><b>${order.id}</b></td>
            <td><b>${order.totalCost}</b></td>
            <td>${order.orderedAt}</td>
            <td>${order.status}</td>
            <td>
                <a href="../admin/orderDetails?id=${order.id}" class="tm-user-view-link">
                    <i class="fas fa-eye tm-product-delete-icon"></i>
                </a>
            </td>
        </tr>
    `).join("");

    $tbody.html(ordersHTML);
}
function fetchOrders(customerId) {
    $.ajax({
        url: `../admin/customerProfile?action=fetchOrders`,
        type: "GET",
        data: { id: customerId },
        dataType: "json",
        headers: { "X-Requested-With": "XMLHttpRequest" },
        success: function(response) {
            renderOrders(response);
        },
        error: function(xhr, status, error) {
            console.error("Error fetching orders:", error);
            console.error("Details:", xhr.responseText)
        }
    });
}
