/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 function getAllMembers()
{
    let url = "http://localhost:8080/ca1/api/groupmembers/all"

     fetch(url)
        .then(res => res.json()) //in flow1, just do it
        .then(data => {
         // Inside this callback, and only here, the response data is available
         console.log("data",data);
        /* data now contains the response, converted to JavaScript
           Observe the output from the log-output above
           Now, just build your DOM changes using the data*/       
      })
}

document.getElementById("allMembersButton").addEventListener("click", getAllMembers);
