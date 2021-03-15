
//---------------------------------------------------------------------------------------------------------------------------------------------
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
            $("#sayHi").text(`Hi ${user.username}`)
            const role = user.role
            let currentUrl = window.location.href
            switch (role){
                case "employee":
                    if (currentUrl.includes(globalVariable.backendRoot + "/manager") || currentUrl.includes(globalVariable.backendRoot + "/login"))
                        window.location.replace(globalVariable.backendRoot+"/employee-home.html")                       
                    break
                case "manager":
                    if (currentUrl.includes(globalVariable.backendRoot + "/employee")|| currentUrl.includes(globalVariable.backendRoot + "/login"))
                        window.location.replace(globalVariable.backendRoot+"/manager-home.html")
                    break
            }
        }
    })();

//---------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------ Log in and Log out ----------------------------------------
// only use these function when the dom fully loaded
$(document).ready(
    function (){
        //--------------------------------------- Log In ----------------------------------------
        $("#employeeLogin, #managerLogin").click(function(e){
            e.preventDefault()
            // get the form name of this button
            const formName = $(e.currentTarget).closest("form").attr("name")
            const loginCredential = getLoginCredential(formName)
            // get login username, role(hidden input) and password
            sendLoginRequest(loginCredential)
        })
        
        // Get login username and password depends on whichever form submited using its name
        function getLoginCredential(formName){
        		const username= $(`[name=${formName}] input[name=inputUserName]`).val()
                const  password = $(`[name=${formName}] input[name=inputPassword]`).val()
                const  role = $(`[name=${formName}] input[name=inputRole]`).val()
 
            let loginCredential= {
                username : username,
                password : password,
                role : role
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
            	.then(res => {
            		if(res) loginResponse(res, loginCredential)
            		else  alertPopUp(".header-md","No response")
            })
        }
        
        // Check info inside obj to see we got error or a valid user
        function loginResponse(res, loginCredential){
            const responseType = (res.role == "user" || res.role == "employee" || res.role == "manager")? "user" : "error"
            if (responseType == "user"){
                sessionStorage.setItem(responseType,JSON.stringify(res))
                console.log(res)
                window.location.replace(`${globalVariable.backendRoot}/${(res.role)}-home.html`)
            } else {
                $(`#${loginCredential.role}LoginError`).text(res.message)
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

//---------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------- Get Reimbushment Requests of an employee By Id, used by both employee and manager ----------------------------------------
//----------------------------------------------- View all Reimbushment of 1 employee----------------------------------------
function getReimbushmentRequestOfOneEmp(eid){
    fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=all&eid="+eid)
    .then(res => res.json())
    .then(data=> {
        if(data != null){
            console.log(data)
            displayReimbushment(data, "#displayReimbushment")
    }else
            alertPopUp("#managerMenu .alert", "No data found")
    })
}

//---------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------- Reuseable code for Manager and Employee ----------------------------------------

// alert popUp then fadeout after some times by append an alert message then remove it
function alertPopUp(selector, message, timeOut=3000){
    $(selector).append(`<p>${message}</p>`)
        setTimeout(()=> $(selector).empty(),timeOut)
        if (selector.text()) selector.text('')
}