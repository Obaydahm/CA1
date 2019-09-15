/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let table = document.getElementById("table");
let url = "https://omoussa.com/CA1/api/car/all";

function showtable(cars) {
    let tablehead = "<tr>" + Object.keys(cars[0]).map(x => "<td>" + x.toUpperCase() + "</td>").join('') + "</tr>";
    let tabledata = cars.map(obj => "<tr>"+Object.keys(obj).map(x => "<td>" + obj[x] + "</td>").join('')+"</tr>").join('');
    table.innerHTML = tablehead.concat(tabledata);
}

fetch(url)
        .then(res => res.json()) //in flow1, just do it
        .then(data => {
            // Inside this callback, and only here, the response data is available
            window.onload = showtable(data);
            document.getElementById("filterb").onclick = function (e) {
                let cars_filtered;
                let filter = document.getElementById("filter");
                if(filter.value === '') {
                    table.innerHTML = "<h2>Please enter a value!</h2>";
                    return;
                }
                else if (!isNaN(filter.value)) {
                    cars_filtered = data.filter(obj => obj.price <= filter.value);
                    if (cars_filtered.length === 0) {
                        table.innerHTML = "<h2>No cars cheaper than " + filter.value + "kr :-(</h2>";
                        return;
                    }
                } else {
                    cars_filtered = data.filter(obj => obj.make.toLowerCase().startsWith(filter.value.toLowerCase()));
                    if (cars_filtered.length === 0) {
                        table.innerHTML = "<h2>No cars from the make: " + filter.value + " :-(</h2>";
                        return;
                    }
                }
                showtable(cars_filtered);
            };
            document.getElementById("filterbyear").onclick = function (e) {
                let cars_filtered;
                let filter = document.getElementById("filteryear");
                cars_filtered = data.filter(obj => obj.year <= filter.value);
                if(filter.value === '') {
                    table.innerHTML = "<h2>Please enter a value!</h2>";
                }
                else if (cars_filtered.length === 0) {
                    table.innerHTML = "<h2>No cars older than " + filter.value + " :-(</h2>";
                    return;
                }
                showtable(cars_filtered);
            };
            document.getElementById("sortb").onclick = function (e) {
                e.preventDefault();
                var cars_sorted;
                let sort = document.getElementById("sort");
                if (sort.value === "make") {
                    cars_sorted = data.sort(function (a, b) {
                        return a.make.toLowerCase().localeCompare(b.make.toLowerCase());
                    });
                } else if (sort.value === "model") {
                    cars_sorted = data.sort(function (a, b) {
                        return a.model.toLowerCase().localeCompare(b.model.toLowerCase());
                    });
                } else if (sort.value === "year") {
                    cars_sorted = data.sort(function (a, b) {
                        return a.year < b.year;
                    });
                } else {
                    cars_sorted = data.sort(function (a, b) {
                        return a.price < b.price;
                    });
                }

                showtable(cars_sorted);
            };
            // data.map(property => "<td>"+property+"</td>");
            /* data now contains the response, converted to JavaScript
             Observe the output from the log-output above
             Now, just build your DOM changes using the data*/
        });

