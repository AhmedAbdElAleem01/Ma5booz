var req = null;
function isUsernameAvailable(){
    if(window.XMLHttpRequest){
        req = new XMLHttpRequest();
    }
    req.onreadystatechange = () => {
        if(req.readyState == 4 && req.status == 200){
            document.getElementById("valid").innerText = req.responseText;
        }
    };
    req.open("GET", "/valid?Username=" + document.getElementById("username").value, true);
    req.send(null);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req1 = null;
function isEmailUnique(){
    if(window.XMLHttpRequest){
        req1 = new XMLHttpRequest();
    }
    req1.onreadystatechange = () => {
        if(req1.readyState == 4 && req1.status == 200){
            document.getElementById("uniqueEmail").innerText = req1.responseText;
        }
    };
    req1.open("GET", "/valid?Email=" + document.getElementById("email").value, true);
    req1.send(null);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req2 = null;
function validateFName(){
    if(window.XMLHttpRequest){
        req2 = new XMLHttpRequest();
    }
    req2.onreadystatechange = () => {
        if(req2.readyState == 4 && req2.status == 200){
            document.getElementById("validFName").innerText = req2.responseText;
        }
    };
    req2.open("GET", "/valid?Name=" + document.getElementById("fname").value, true);
    req2.send(null);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req3 = null;
function validateLName(){
    if(window.XMLHttpRequest){
        req3 = new XMLHttpRequest();
    }
    req3.onreadystatechange = () => {
        if(req3.readyState == 4 && req3.status == 200){
            document.getElementById("validLName").innerText = req3.responseText;
        }
    };
    req3.open("GET", `/valid?Name=` + document.getElementById("lname").value, true);
    console.log("Request sent to: " , `${window.contextPath}`);

    req3.send(null);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req4 = null;
function validatePhone(){
    if(window.XMLHttpRequest){
        req4 = new XMLHttpRequest();
    }
    req4.onreadystatechange = () => {
        if(req4.readyState == 4 && req4.status == 200){
            document.getElementById("validPhone").innerText = req4.responseText;
        }
    };
    req4.open("GET", "/valid?Phone=" + document.getElementById("phoneNumber").value, true);
    req4.send(null);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req5 = null;
function validateJob(){
    if(window.XMLHttpRequest){
        req5 = new XMLHttpRequest();
    }
    req5.onreadystatechange = () => {
         if(req5.readyState == 4 && req5.status == 200){
            document.getElementById("validJob").innerText = req5.responseText;
         }
    };
    req5.open("GET", "/valid?Job=" + document.getElementById("job").value, true);
    req5.send(null);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req6 = null;
function validatePassword(){
    if(window.XMLHttpRequest){
        req6 = new XMLHttpRequest();
    }
    req6.onreadystatechange = () => {
        if(req6.readyState == 4 && req6.status == 200){
            document.getElementById("validPassword").innerText = req6.responseText;
        }
    };
    req6.open("GET", "/valid?Password=" + document.getElementById("password").value, true);
    req6.send(null);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req7 = null;
function validateCity(){
    if(window.XMLHttpRequest){
        req7 = new XMLHttpRequest();
    }
    req7.onreadystatechange = () => {
        if(req7.readyState == 4 && req7.status == 200){
            document.getElementById("validCity").innerText = req7.responseText;
        }
    };
    req7.open("GET", "/valid?City=" + document.getElementById("city").value, true);
    req7.send(null);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req8 = null;
function validateStreet(){
    if(window.XMLHttpRequest){
        req8 = new XMLHttpRequest();
    }
    req8.onreadystatechange = () => {
        if(req8.readyState == 4 && req8.status == 200){
            document.getElementById("validStreet").innerText = req8.responseText;
        }
    };
    req8.open("GET", "/valid?Street=" + document.getElementById("street").value, true);
    req8.send(null);
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var req9 = null;
function validateCredit(){
    if(window.XMLHttpRequest){
        req9 = new XMLHttpRequest();
    }
    req9.onreadystatechange = () => {
        if(req9.readyState == 4 && req9.status == 200){
            document.getElementById("validCredit").innerText = req9.responseText;
        }
    };
    req9.open("GET", "/valid?Credit=" + document.getElementById("creditLimit").value, true);
    req9.send(null);
}