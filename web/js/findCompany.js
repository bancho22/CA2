$(window).ready(init);

function init() {

    $("#find").click(findCompany);
}
;

function findCompany() {
                            

    $.ajax({
        type: 'GET',
        url: "api/company/byphone/" + $("#search").val(),
        success: function (company) {
            $("#table").html("");
            $("#table").html("<tr><th>Name</th>  <th>Description</th>  <th>Cvr</th>  <th>Number of Employees</th></tr>");
            $("#table").append('<tr><td>' +
                    company.name + '</td><td>' +
                    company.description + '</td><td>' +
                    company.cvr + '</td><td>' +
                    company.numEmployees + '</td><td>' +
                    company.markedValue + '</td><td>' 
                    
                    

                    );
            
            $("#find").html(company.name);
        }
    });
}
;
