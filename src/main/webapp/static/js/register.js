var req = null;
function isUsernameAvailable(){
    if(window.XMLHttpRequest){
        req = new XMLHttpRequest();
    }
    req.onreadystatechange = handleReq;
    req.open("GET", "http://localhost:9090/Ma5booz/uniqueUsername?username=" + document.getElementById("username").value, true);
    req.send(null);
}
function handleReq(){
    if(req.readyState == 4 && req.status == 200){
        document.getElementById("valid").innerText = req.responseText;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req1 = null;
function isEmailUnique(){
    if(window.XMLHttpRequest){
        req1 = new XMLHttpRequest();
    }
    req1.onreadystatechange = handleReq1;
    req1.open("GET", "http://localhost:9090/Ma5booz/uniqueEmail?email=" + document.getElementById("email").value, true);
    req1.send(null);
}
function handleReq1(){
    if(req1.readyState == 4 && req1.status == 200){
        document.getElementById("uniqueEmail").innerText = req1.responseText;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req2 = null;
function validateFName(){
    if(window.XMLHttpRequest){
        req2 = new XMLHttpRequest();
    }
    req2.onreadystatechange = handleReq2;
    req2.open("GET", "http://localhost:9090/Ma5booz/validName?name=" + document.getElementById("fname").value, true);
    req2.send(null);
}
function handleReq2(){
    if(req2.readyState == 4 && req2.status == 200){
        document.getElementById("validFName").innerText = req2.responseText;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req3 = null;
function validateLName(){
    if(window.XMLHttpRequest){
        req3 = new XMLHttpRequest();
    }
    req3.onreadystatechange = handleReq3;
    req3.open("GET", "http://localhost:9090/Ma5booz/validName?name=" + document.getElementById("lname").value, true);
    req3.send(null);
}
function handleReq3(){
    if(req3.readyState == 4 && req3.status == 200){
        document.getElementById("validLName").innerText = req3.responseText;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req4 = null;
function validatePhone(){
    if(window.XMLHttpRequest){
        req4 = new XMLHttpRequest();
    }
    req4.onreadystatechange = handleReq4;
    req4.open("GET", "http://localhost:9090/Ma5booz/validPhone?phone=" + document.getElementById("phoneNumber").value, true);
    req4.send(null);
}
function handleReq4(){
    if(req4.readyState == 4 && req4.status == 200){
        document.getElementById("validPhone").innerText = req4.responseText;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req5 = null;
function validateJob(){
    if(window.XMLHttpRequest){
        req5 = new XMLHttpRequest();
    }
    req5.onreadystatechange = handleReq5;
    req5.open("GET", "http://localhost:9090/Ma5booz/validName?name=" + document.getElementById("job").value, true);
    req5.send(null);
}
function handleReq5(){
    if(req5.readyState == 4 && req5.status == 200){
        document.getElementById("validJob").innerText = req5.responseText;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req6 = null;
function validatePassword(){
    if(window.XMLHttpRequest){
        req6 = new XMLHttpRequest();
    }
    req6.onreadystatechange = handleReq6;
    req6.open("GET", "http://localhost:9090/Ma5booz/validPassword?password=" + document.getElementById("password").value, true);
    req6.send(null);
}
function handleReq6(){
    if(req6.readyState == 4 && req6.status == 200){
        document.getElementById("validPassword").innerText = req6.responseText;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req7 = null;
function validateCity(){
    if(window.XMLHttpRequest){
        req7 = new XMLHttpRequest();
    }
    req7.onreadystatechange = handleReq7;
    req7.open("GET", "http://localhost:9090/Ma5booz/validCityStreet?name=" + document.getElementById("city").value, true);
    req7.send(null);
}
function handleReq7(){
    if(req7.readyState == 4 && req7.status == 200){
        document.getElementById("validCity").innerText = req7.responseText;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req8 = null;
function validateStreet(){
    if(window.XMLHttpRequest){
        req8 = new XMLHttpRequest();
    }
    req8.onreadystatechange = handleReq8;
    req8.open("GET", "http://localhost:9090/Ma5booz/validCityStreet?name=" + document.getElementById("street").value, true);
    req8.send(null);
}
function handleReq8(){
    if(req8.readyState == 4 && req8.status == 200){
        document.getElementById("validStreet").innerText = req8.responseText;
    }
}