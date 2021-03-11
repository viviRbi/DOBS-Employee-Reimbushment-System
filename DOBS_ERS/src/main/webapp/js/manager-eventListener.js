
// only use thes function when the DOM fully load
$(document).ready(function (){
    
    $("#viewAllEmployees").click(viewAllEmployees)
    $("#managerHome").click(displayManagerHome)

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
                $("#displayAllEmployees tbody tr")[i].addEventListener('click', () => getReimbushmentRequestOfOneEmp(data[i].id))
            }
        } else {
            alertPopUp("#managerMenu .alert", data.message)
        }
    }

    //--------------------- Go back to Manager menu by hide all <main> and show Manager menu <main> ----------------------------------------
    function displayManagerHome(){
        $("#displayAllEmployees tbody").empty()
        $("main").not("#managerMenu").removeClass("d-block")
        $("#managerMenu").removeClass("d-none")
    }
});

//---------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------- Get Reimbushment Requests ----------------------------------------
function getReimbushmentRequestOfOneEmp(eid){
    console.log(eid)
    fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=pending&eid="+eid)
    .then(res => res.json())
    .then(data=> console.log(data))
}

(function (){
    // if the user session exists
    if (Boolean(sessionStorage.getItem("user"))== true){

        // Manager menu Button
        //view all Pending
        $("#viewAllPendinReimbushmentRequest").click(()=>{
            fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=pending&eid=-1")
            .then(res => res.json())
            .then(data=> console.log(data))
        })
        // view all Resolved
        $("#viewAllPendinReimbushmentRequest").click(()=>{
            fetch(globalVariable.backendRoot + "/"+globalVariable.viewReimbushment+"?status=resolved&eid=-1")
            .then(res => res.json())
            .then(data=> console.log(data))
        })
    }  
})()