
$("#employeeLogin, #managerLogin").click(function(e){
   e.preventDefault()
   const loginCredential = getLoginCredential($(e.currentTarget).closest("form").attr("name"))
  const a = sendLoginRequest(loginCredential)
})

// Get login username and password depends on whichever form submited using its name
function getLoginCredential(formName){
     let loginCredential= {
         username : $(`[name=${formName}] input[name=inputUserName]`).val(),
         password : $(`[name=${formName}] input[name=inputPassword]`).val(),
         role : $(`[name=${formName}] input[name=inputRole]`).val()
     }
    return loginCredential
}

function sendLoginRequest(loginCredential){

    fetch(globalVariable.backendRoot + "/login", {
        method: "POST",
        body: JSON.stringify(loginCredential),
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        }
    }).then(response => response.json())
    .then(res => loginResponse(res, loginCredential))
    .catch(err => console.log(err))
}

// Check info inside obj to see we got error or a valid user
function loginResponse(res, loginCredential){
    console.log("----------")
   var responseType = res.role? 'user' : 'error' 
    if (responseType === 'user'){
        console.log("f")
        sessionStorage.setItem(sessionKey,res)
        window.location.replace(`${globalVariable.backendRoot}/${(res.role)}-home.html`)
    } else {
        console.log("ooo")
        console.log(`#${loginCredential.role}LoginError`)
        $(`#${loginCredential.role}LoginError`).text(res.errorMessage)
    }
}
