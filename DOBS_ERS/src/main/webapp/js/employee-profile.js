//--------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------- Change to edit --------------------------------------------------------


$("#editProfile").click(()=>{
    changeSubmitBtnState($("#submitProfile"))
    editModeInput()
})

function changeSubmitBtnState(submitBtn){
    submitBtn.removeClass("btn-secondary")
    submitBtn.addClass("btn-primary")
    submitBtn.attr("disabled", false)
}

function editModeInput(){
    const allInputs = $("#employeeProfileForm input")
    $("#employeeProfileForm input[name=password]").closest(".form-group").removeClass("d-none")
    $("#employeeProfileForm input[name=newpassword]").closest(".form-group").removeClass("d-none")
    for (let i= 0; i < allInputs.length; i++){
        $(allInputs[i]).removeAttr("readonly")
    }
}

//--------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------- Get Profile info --------------------------------------------------------

(function (){
    let user = JSON.parse(sessionStorage.getItem("user"))
    const eid = user.id
    if (user.role == "employee"){
        fetchEmpInfo(eid)
    } else window.location.replace(globalVariable.backendRoot)
})()

function fetchEmpInfo(eid){
    fetch(`${globalVariable.backendRoot}/viewProfile?eid=${eid}`)
    .then(res=> res.json())
    .then(data=>seedProfile(data))
}

function seedProfile(data){
    $("#employeeProfileForm input[name=username]").val(data.username)
    $("#employeeProfileForm input[name=firstname]").val(data.firstname)
    $("#employeeProfileForm input[name=lastname]").val(data.lastname)
    $("#employeeProfileForm input[name=email]").val(data.email)
}


//--------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------- Update Profile info --------------------------------------------------------
$("#submitProfile").click((e) => {
    e.preventDefault()
    const data = getUpdatedInfo()
    let error
    if (data != null){
        updateProfile(data, data.username)
    }else alertPopUp("#employeeProfileError", `<p class="text-danger">Please fill the form carefully</p>`)
})

function updateProfile(data, username){
    fetch(globalVariable.backendRoot + "/updateProfile",{
        method:"POST",
        headers: {
            'Content-Type': 'application/json',
          },
        body: JSON.stringify(data)
    }) .then(res => res.json()).then((data) => {
        if(data.statusCode == 200){
            // Change value of User in session storage
            let changeData= JSON.parse(sessionStorage.getItem("user"))
            changeData.username = username
            sessionStorage.setItem("user", JSON.stringify(changeData))
            // Change Hi text for edited username
            $("#sayHi").text("Hi "+ username)
            // alert success
            alertPopUp("#employeeProfileError", `<p class="text-success">${data.message}</p>`)
            setTimeout(() => window.location.reload(), 4000)
        }else{
            alertPopUp("#employeeProfileError", `<p class="text-danger">${data.message}</p>`)
        }
    })
}

function getUpdatedInfo(){
    const updateData={
        username : $("#employeeProfileForm input[name=username]").val(),
        firstname : $("#employeeProfileForm input[name=firstname]").val(),
        lastname : $("#employeeProfileForm input[name=lastname]").val(),
        email : $("#employeeProfileForm input[name=email]").val(),
        password : $("#employeeProfileForm input[name=password]").val(),
        newpassword : $("#employeeProfileForm input[name=newpassword]").val()
    }
    if (validateInfo(updateData) == true) return updateData
}

function validateInfo(data){
    const {username, firstname, lastname, email, password, newpassword} = data
    const emailRegex = /\S+@\S+\.\S+/
    if (username.trim().length <2 || firstname.trim().length <2 || lastname.trim().length <2 || email.trim().length <2 || password.trim().length <2 || newpassword.trim().length <2){
        alertPopUp("#employeeProfileError", `<p class="text-danger">Please don't leave a blank</p>`)
        return false
   } if (!email.match(emailRegex)){
        alertPopUp("#employeeProfileError", `<p class="text-danger">Please fill a valid email</p>`)
        return false
    }else return true
}