

//--------------------- Redirect if doesn't have permission to view a webpage ----------------------------------------
    
    // redirect to home page if trying to go to employee or manager page while not a user
    (function (){
        if (sessionStorage.getItem('user') == null){
            let currentUrl = window.location.href
            if(currentUrl.includes(globalVariable.backendRoot + "/employee") 
            || currentUrl.includes(globalVariable.backendRoot + "/manager")) 
                window.location.replace(globalVariable.backendRoot)
        }
    })();
    
    // redirect if manager or employee trying to access other page
    (function (){
        let user = JSON.parse(sessionStorage.getItem('user'))
        if (Boolean(user)){
            console.log(user)
            const role = user.role
            console.log(role)
            let currentUrl = window.location.href
            switch (role){
                case "employee":
                    if (currentUrl.includes(globalVariable.backendRoot + "/manager"))
                        window.location.replace(globalVariable.backendRoot+"/employee-home.html")
                    break
                case "manager":
                    if (currentUrl.includes(globalVariable.backendRoot + "/employee"))
                        window.location.replace(globalVariable.backendRoot+"/manager-home.html")
                    break
            }
        }
    })();

$(document).ready(
    function (){
        //--------------------------------------- Log In ----------------------------------------
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
        
        // Send filled login form to backend 
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
            var responseType = res.role? 'user' : 'error' 
            if (responseType === 'user'){
                sessionStorage.setItem(responseType,JSON.stringify(res))
                console.log(res)
                window.location.replace(`${globalVariable.backendRoot}/${(res.role)}-home.html`)
            } else {
                // console.log("ooo")
                // console.log(`#${loginCredential.role}LoginError`)
                $(`#${loginCredential.role}LoginError`).text(res.errorMessage)
            }
        }
        //--------------------------------------- Log Out ----------------------------------------
        (function (){
            if ($('.logout')){
                $('.logout').click((e) => {
                    sessionStorage.removeItem('user')
                    e.currentTarget.setAttribute("href",globalVariable.backendRoot + "/logout")
                })
            } 
        })();        
    }
);

 