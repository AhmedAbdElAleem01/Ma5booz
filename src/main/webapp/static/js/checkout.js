var req = null;
function validatePhone(){
    if(window.XMLHttpRequest){
        req = new XMLHttpRequest();
    }
    req.onreadystatechange = () => {
        if(req.readyState == 4 && req.status == 200){
            document.getElementById("validPhone").innerText = req.responseText;
        }
    };
    req.open("GET", "http://localhost:9090/Ma5booz/validPhone?phone=" + document.getElementById("phoneNumber").value, true);
    req.send(null);
}

var req2 = null;
function validateCity(){
    if(window.XMLHttpRequest){
        req2 = new XMLHttpRequest();
    }
    req2.onreadystatechange = () => {
        if(req2.readyState == 4 && req2.status == 200){
            document.getElementById("validCity").innerText = req2.responseText;
        }
    };
    req2.open("GET", "http://localhost:9090/Ma5booz/validCityStreet?name=" + document.getElementById("city").value, true);
    req2.send(null);
}

var req3 = null;
function validateStreet(){
    if(window.XMLHttpRequest){
        req3 = new XMLHttpRequest();
    }
    req3.onreadystatechange = () => {
        if(req3.readyState == 4 && req3.status == 200){
            document.getElementById("validStreet").innerText = req3.responseText;
        }
    };
    req3.open("GET", "http://localhost:9090/Ma5booz/validCityStreet?name=" + document.getElementById("street").value, true);
    req3.send(null);
}