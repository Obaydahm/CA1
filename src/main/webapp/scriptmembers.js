/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 

let url = "https://omoussa.com/CA1/api/groupmembers/all"

fetch(url)
    .then(res => res.json()) //in flow1, just do it
    .then(data => {
     // Inside this callback, and only here, the response data is available
     console.log("data",data);
    /* data now contains the response, converted to JavaScript
       Observe the output from the log-output above
       Now, just build your DOM changes using the data*/    

    let table = document.getElementById("membersTable");
    //stores all the keys from object 1 in the array (id, name, colourLevelOfStudent)
    let membersHeaders = Object.keys(data[0]);
    generateTableData(table, data);
    generateTableHead(table, membersHeaders);
    

  });
      
      
  document.getElementById("reloadMembersButton").onclick = function()
  {
        let url = "https://omoussa.com/CA1/api/groupmembers/all"

        fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
             // Inside this callback, and only here, the response data is available
             console.log("data",data);
            /* data now contains the response, converted to JavaScript
               Observe the output from the log-output above
               Now, just build your DOM changes using the data*/    


        let table = document.getElementById("membersTable");
        
        //create a new table body, and insert data into it
        let newTableBody = document.createElement("tbody");
        generateTableData(newTableBody, data);
        
        //gets the old table body
        let oldTableBody = table.tBodies[0];
        
        //replace the old table body, with the new one
        table.replaceChild(newTableBody, oldTableBody);

        console.log("Members got reloaded");      

        }); 
  }
      
      
//generates the table headers
function generateTableHead(table, membersHeaders)
{
    //create a tablehead
    var thead = table.createTHead();
    //create a row
    var row = thead.insertRow();

    //for each key, create a <th> and insert the key into it.
    for(var key of membersHeaders)
    {
        var th = document.createElement("th");
        var text = document.createTextNode(key);
        //insert the key into a <th> </th>
        th.appendChild(text);
        //insert the <th> into the row
        row.appendChild(th);
    }
}
    
//generate the data of the table
function generateTableData(table, membersData)
{
    //for each element in the array, insert a row
    for(var element of membersData)
    {
        var row = table.insertRow();

        //for each key the element consists of, take the value of the key and insert into a cell
        for(key in element)
        {
            var cell = row.insertCell();
            var text = document.createTextNode(element[key]);
            cell.appendChild(text);
        }
    }
}

