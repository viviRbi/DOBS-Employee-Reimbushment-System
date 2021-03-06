
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
    .then(res => {
        sessionStorage.setItem("user",res)
        console.log(res)
        const role = res.role.toLowerCase()
       window.location.replace(`${globalVariable.backendRoot}/${(role)}-home.html`)
    }).catch(err => console.log(err))
}

