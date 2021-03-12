
//---------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------- Get Reimbushment Requests of an employee By Id ----------------------------------------
// Employee menu button
(function (){
    let user = JSON.parse(sessionStorage.getItem("user"))
    const eid = user.id
    if (user.role == "employee"){
        $("#reimbushmentPendingReq").click(()=>{
            fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=pending&eid="+ eid)
            .then(res => res.json())
            .then(data=> console.log(data))
        })
        $("#reimbushmentResolvedReq").click(()=>{
            fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=resolved&eid="+ eid)
            .then(res => res.json())
            .then(data=> console.log(data))
        })
         // get employee id from Json data in session
        $("#reimbushmentAllReq").click(()=> getReimbushmentRequestOfOneEmp(eid))
    }
})()

//---------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------- Submit Reimbushment Request ----------------------------------------
// Functions called when Click submit
$("#submitReimbushmentBtn").click(function(e) {
    e.preventDefault()
    let reimbushmentReq = getSubmitReimbushmentInfo()
    sendSubmitReimbushmentRequest(reimbushmentReq)
    })

    // send request
function sendSubmitReimbushmentRequest(reimbushmentReq){
    fetch(globalVariable.backendRoot + "/submitReimbushment", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
          },
        body: JSON.stringify(reimbushmentReq),
    }).then (res => res.json())
    .then (data => {
        if (data.statusCode == 200) alertPopUp("#employeeSubmitReimbushmentSuccess", data.message)
        else alertPopUp("#employeeSubmitReimbushmentError", data.message)
    })
}
// get request body parameter
function getSubmitReimbushmentInfo(){
    const reimbushmentReq = {
        amount: $("#submitReimbushmentForm input[name=amount]").val(),
        typeid: $("#submitReimbushmentForm select[name=type]").val(),
        employeeid: JSON.parse(sessionStorage.getItem("user")).id,
        //receipt: $("#submitReimbushmentForm input[name=receipt]")[0].files[0]
    }
    //return formData
   return reimbushmentReq
}

// Thumnail for file
$("input[name=receipt]").change(function(){
    if($("#receiptPlaceholder img")) $("#receiptPlaceholder").empty()
    const reader = new FileReader()
    reader.onload = function(){
        const img = new Image()
        img.src = reader.result
        $("#receiptPlaceholder").append(img)
    }
    reader.readAsDataURL(this.files[0])
})


//---------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------- Get Reimbushment Requests of an employee By Id ----------------------------------------
// Employee menu button
// $("#reimbushmentPendingReq").click(()=>{
//     fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=pending&eid="+ eid)
//     .then(res => res.json())
//     .then(data=> console.log(data))
// })
// $("#reimbushmentResolvedReq").click(()=>{
//     fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=resolved&eid="+ eid)
//     .then(res => res.json())
//     .then(data=> console.log(data))
// })
//  // get employee id from Json data in session
//  if (Boolean(sessionStorage.getItem("user")) == true){
//     $("#reimbushmentAllReq").click(()=>{
//         let user = JSON.parse(sessionStorage.getItem("user"))
//         getReimbushmentRequestOfOneEmp(user.id)
//     })
// }