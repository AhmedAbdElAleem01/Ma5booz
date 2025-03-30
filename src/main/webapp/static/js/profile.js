document.getElementById("edit").addEventListener("click", function() {
    let inputs = document.querySelectorAll("#creditLimitForm input");
    inputs.forEach(input => input.removeAttribute("disabled")); 
    document.getElementById("submit").removeAttribute("disabled"); 
});
document.getElementById("editAddress").addEventListener("click", function() {
    let inputs = document.querySelectorAll("#addressForm input");
    inputs.forEach(input => input.removeAttribute("disabled")); 
    document.getElementById("saveAddress").removeAttribute("disabled"); 
});
document.getElementById("editAccount").addEventListener("click", function() {
    let inputs = document.querySelectorAll("#accountForm input");
    inputs.forEach(input => input.removeAttribute("disabled")); 
    document.getElementById("saveAccount").removeAttribute("disabled"); 
});
let username = document.getElementById("username");
username.addEventListener("blur",function(){
    $.ajax({
        url: `./profile?username=${username.value}`,
        type: "GET",
        success: function (response) {
            if (response === "taken") {
                $("#isValid").text("Username is already taken.");
            } else {
                $("#isValid").text("");
            }
        },
        error: function () {
            $("#isValid").text("Error checking username.");
        }
    });
});