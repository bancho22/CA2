$(window).ready(init);

function init() {

    $("#find").click(findPerson);
}
;

function findPerson() {
    $.ajax({
        type: 'GET',
        url: "api/person/byphone/" + $("#search").val(),
        success: function (person) {
            $("#table").html("");
            $("#table").html("<tr><th>First Name</th>  <th>Last Name</th>  <th>Phone</th>  <th>Adress</th></tr>");
            $("#table").append('<tr><td>' +
                    person.firstName + '</td><td>' +
                    person.lastName + '</td><td>' +
                    person.address.street + '</td><td>'
                    

                    );

        }
    });
}
;