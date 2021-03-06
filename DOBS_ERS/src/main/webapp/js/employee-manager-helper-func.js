

//---------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------- Display Table of Reimbushment --------------------------------------------------------

// // Callback function to display reimbushment info in a table body
function displayReimbushment(data, queryValue){
    let user = JSON.parse(sessionStorage.getItem("user"))
    if (data[0].id){
        $("main").addClass("d-none")
        $(queryValue).removeClass("d-none")
        // get status name in the for loop base on position of statusName array
        let statusName= ["Pending", "Resolved", "Reject"]
        for(let i= 0; i<data.length; i++) {
        	//let statusName= ["Pending", "Resolved", "Reject"]
            // if there is a resolver id, display info, else, value = -----
            $(queryValue+ " tbody").append(`
                <tr class="pointable" id=${data[i].id}>
                    <td>${data[i].id}</td>
                    <td>${data[i].amount}</td>
                    <td>${convertTimeStampToDate(data[i].submited)}</td>
                    <td data-id="employee-${data[i].author}">${data[i].authorUserName}</td>
                    ${data[i].resolver >0?'<td>'+convertTimeStampToDate(data[i].resolved)+'</td>' : '<td>Not yet</td>'}
                    ${data[i].resolver >0?'<td data-id="manager-'+data[i].author+'">'+data[i].resolverUserName+'</td>' : '<td>Not yet</td>'}
                    <td >${typeName(data[i].typeid)}</td>
                   <td class="status${statusName[data[i].statusid-1]}">${statusName[data[i].statusid-1]}</td>
                    ${user.role =="manager" && data[i].statusid == 1 ?'<td><input name="selected" type="checkbox" value='+data[i].id+'></td>' : '<td></td>'}
                </tr>
            `)
        }
    } else {
        alertPopUp("#managerMenu .alert", data.message)
    }
}

function convertTimeStampToDate(timeStamp){
    var date = new Date(timeStamp);
    return date.toDateString()
}

function typeName(typeID){
    let typeName;
    switch (typeID){
        case 1:
            typeName = 'LODGING'
            break
        case 2:
            typeName = 'TRAVEL'
            break
        case 3:
            typeName = 'FOOD'
            break
        default:
            typeName = 'OTHER'
    }
    return typeName
}