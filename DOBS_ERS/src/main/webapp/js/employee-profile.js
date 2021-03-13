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
    $("#employeeProfileForm input[name=oldpassword]").closest(".form-group").removeClass("d-none")
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
        console.log(eid)
        fetchEmpInfo(eid)
    } else window.location.replace(globalVariable.backendRoot)
})()

function fetchEmpInfo(eid){
    fetch(`${globalVariable.backendRoot}/viewProfile?eid=${eid}`)
    .then(res=> res.json())
    .then(data=>console.log(data))
}