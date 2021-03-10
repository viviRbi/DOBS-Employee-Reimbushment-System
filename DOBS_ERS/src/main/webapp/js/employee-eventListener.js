
$("#submitReimbushmentBtn").click(function(e) {
    e.preventDefault()
    let reimbushmentReq = getSubmitReimbushmentInfo()
    sendSubmitReimbushmentRequest(reimbushmentReq)
    })

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