
// only use thes function when the DOM fully load
$(document).ready(function (){
    
    $("#viewAllEmployees").click(viewAllEmployees)
    $("#managerHome").click(displayManagerHome)


     //---------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------- View All Employees ----------------------------------------
    function viewAllEmployees(){
        fetch(globalVariable.backendRoot + "/allEmployees",{
            method:"GET",
            headers: {
                'Content-Type': 'application/json',
              }
        }).then (res => res.json())
        .then (data => displayEmployeeProfiles(data))
        .catch(err=>$("#managerMenu .alert").val("Cannot load employees profile"))
    }
    
    // Callback function to display employee info in a table body
    // If there is some data, hide Manager Menu and display Employees table, else send a message
    function displayEmployeeProfiles(data){
        if (data[0].id){
            $("main").addClass("d-none")
            $("#displayAllEmployees").removeClass("d-none")
            for(let i= 0; i<data.length; i++) {
                $("#displayAllEmployees tbody").append(`
                    <tr class="pointable" id=${data[i].id}>
                        <td>${data[i].id}</td>
                        <td>${data[i].username}</td>
                        <td>${data[i].firstname}</td>
                        <td>${data[i].lastname}</td>
                        <td>${data[i].email}</td>
                    </tr>
                `)
                // get Reimbushment Request Of One Employee base on clicked table row
                $("#displayAllEmployees tbody tr")[i].addEventListener('click', () => getReimbushmentRequestOfOneEmp(data[i].id))
            }
        } else {
            alertPopUp("#managerMenu .alert", data.message)
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------- Back button to hide container and remove table ----------------------------------------
    function displayManagerHome(){
        //empty all Viewing Table body
        $("#displayReimbushment tbody").empty()
        $("#displayResolvedReimbushment tbody").empty()
        $("#displayAllEmployees tbody").empty()
        

        // remove display-block for all <main> that is not main menu
        $("main").not("#managerMenu").removeClass("d-block")
        $("main").not("#managerMenu").addClass("d-none")
        // remove display-none for main menu
        $("#managerMenu").removeClass("d-none")
    }
});

//---------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------- View Pending/ Ressolved Reimbushment ----------------------------------------
(function (){
    // if the user session exists
    let user = JSON.parse(sessionStorage.getItem("user"))
    if (user.role== "manager"){
        // Manager menu Button ----
        //view all Pending
        $("#viewAllPendingReimbushmentRequest").click(()=>{
            fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=pending&eid=-1")
            .then(res => res.json())
            .then(data=> {
                if(data != null)
                // in employee-manager-helper-func
                    displayReimbushment(data, "#displayReimbushment")
                else
                    alertPopUp("#managerMenu .alert", "No data found")
            })
        })
        // view all Resolved
        $("#viewAllResolvedReimbushmentRequest").click(()=>{
            fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=resolved&eid=-1")
            .then(res => res.json())
            .then(data=> {
                if(data != null)
                // in employee-manager-helper-func
                    displayReimbushment(data, "#displayResolvedReimbushment")
                else
                    alertPopUp("#managerMenu .alert", "No data found")
            })
        })
        // Approve/ Reject event 
        approveRejectReimbushment("approve")
        approveRejectReimbushment("reject")
    }  
})();

//-------------------------------------------------------APPROVE/ REJECT REIMBUSHMENT CALLBACK FUNC----------------------------------------------------------------------


// fetch approve/reject API
function approveRejectReimbushment(actionType){
    $(`#${actionType}Reimbushment`).click((e)=>{
        e.preventDefault()
        let idArray =[]
        let selectedCheckboxes = $("#displayReimbushmentForm input[name=selected]:checked")
        for (let i=0; i<selectedCheckboxes.length; i++){
            idArray.push(selectedCheckboxes[i].value)
        }

        fetch(`${globalVariable.backendRoot}/manager/${actionType}Reimbushment`,{
            method: "POST",
            body: JSON.stringify(idArray),
            headers: {
                "Content-type": "application/json; charset=UTF-8",
            }
        })
        .then(res => res.json())
        .then(data=> {
            if(data != null){
                removeApprovedRejectedRow(idArray)
                alertPopUp("#displayReimbushment .alert", `<p class="text-success">${data.message}</p>`)
            } else
                alertPopUp("#displayReimbushment .alert", "<p class='text-warning'>No data found</p>")
        })
    })
}

// remove approved, rejected row immediately in frontend (since user need to refresh the page for it to update) 
function removeApprovedRejectedRow(idArray){
    for (let i=0; i< idArray.length; i++){
        $(`#displayReimbushmentForm tr#${idArray[i]}`).addClass('d-none')
    }
}
//-------------------------------------------------------END APPROVE/ REJECT REIMBUSHMENT----------------------------------------------------------------------