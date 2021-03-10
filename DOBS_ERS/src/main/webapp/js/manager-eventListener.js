
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
        if (data){
            $("main").addClass("d-none")
            $("#displayAllEmployees").removeClass("d-none")
            data.forEach(e => {
                $("#displayAllEmployees tbody").append(`
                    <tr class="pointable" id=${e.id}>
                        <td>${e.id}</td>
                        <td>${e.username}</td>
                        <td>${e.firstname}</td>
                        <td>${e.lastname}</td>
                        <td>${e.email}</td>
                    </tr>
                `)
            });
        } else {
            alertPopUp("#managerMenu .alert", "Cannot find any employee")
        }
    }

    //--------------------- Go back to Manager menu by hide all <main> and show Manager menu <main> ----------------------------------------
    function displayManagerHome(){
        $("#displayAllEmployees tbody").empty()
        $("main").not("#managerMenu").removeClass("d-block")
        $("#managerMenu").removeClass("d-none")
    }
});

