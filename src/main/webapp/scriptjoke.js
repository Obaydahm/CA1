/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let table = document.getElementById("table");
let url = "https://omoussa.com/CA1/api/joke/all";
function showTable(jokes) {
    let tablehead = '<col width="5%"><col width="25%"><col width="70%"><thead><tr><th>#</th><th>Type</th><th>Joke</th></tr></thead><tbody>';
    let tabledata = jokes.map(obj => "<tr><td>" + obj.id + "</td><td>" + obj.type + "</td><td>" + obj.joke + "</td>");
    let tab = tablehead.concat(tabledata.join('') + "</tbody>");
    table.innerHTML = tab;
}

fetch(url)
.then(res => res.json()) //in flow1, just do it
.then(data => {
    // Inside this callback, and only here, the response data is available
    window.onload = showTable(data);
});

function getById(id){
	fetch("https://omoussa.com/CA1/api/joke/" + id)
	.then(res => res.json()) //in flow1, just do it
	.then(data => {
		document.getElementById("jokeinfo").innerHTML = "<b>ID:</b> " + data.id + "<br> <b>Type:</b> " + data.type + "<br>" + data.joke;
	    console.log(data)
	});
}


function getRandomJoke(){
	fetch("https://omoussa.com/CA1/api/joke/random")
	.then(res => res.json()) //in flow1, just do it
	.then(data => {
		document.getElementById("randomjoke").innerHTML = "<b>ID:</b> " + data.id + "<br> <b>Type:</b> " + data.type + "<br>" + data.joke;
	    console.log(data)
	});
}