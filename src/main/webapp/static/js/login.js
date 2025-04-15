document.addEventListener("DOMContentLoaded" , function() {
    let urlParams = new URLSearchParams(window.location.search);
    if(urlParams.has("error-message")){
        let error =  document.getElementById('error-message');
        error.textContent = urlParams.get("error-message");
        error.style.visibility = 'visible';
    }
});