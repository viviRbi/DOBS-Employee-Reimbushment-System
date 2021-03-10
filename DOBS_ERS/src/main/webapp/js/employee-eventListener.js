
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
    .then (data => console.log(data))
}

function getSubmitReimbushmentInfo(){
    const amount = $("#submitReimbushmentForm input[name=amount]").val()
    const type = $("#submitReimbushmentForm select[name=type]").val()
    const employee_id = JSON.parse(sessionStorage.getItem("user")).id
    //const receipt = $("input[name=receipt]")[0].files[0];
    //let formData = new FormData();
    //formData.append("receipt", receipt)
    
    const reimbushmentReq = {
        amount: amount,
        typeid: type,
        employeeid: employee_id,
        //receipt: receipt
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