document.addEventListener("DOMContentLoaded" , function() {
    let cookies = document.cookie.split(";");
    if (cookies != null) {
        for (let i = 0; i < cookies.length; i++) {
            let cookie = cookies[i].trim();
            if (cookie.startsWith("rememberMeCookie=")) {
                var savedCredentials = cookie.split("=")[1];
                var savedEmail = savedCredentials.split("+")[0];
                var savedPassword = savedCredentials.split("+")[1];
                $("#email").val(savedEmail);
                $("#password").val(savedPassword);
                $('#rememberMe').prop('checked', true);
            }
        }
    };
    let urlParams = new URLSearchParams(window.location.search);    
    if(urlParams.has("error-message")){
        let error =  document.getElementById('error-message'); 
        error.textContent = urlParams.get("error-message");
        error.style.visibility = 'visible';
    }
});     