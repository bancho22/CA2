$(window).ready(init);

function init() {

    $("#find").click(findPerson);
}
;

function findPerson() {
    $.ajax({
        type: 'GET',
        url: "api/person/byphone/" + $("#search").val(),
        success: function (data) {
            $.each(data, function (person) {
                $("#table").append('<tr><td>' +
                        person.firstName + '</td><td>' +
                        person.lastName + '</td><td>' +
                        $.each(person.phones, function (phone) {
                            phone.number;
                        }

                        )
                        );
            });

        }
    });
}
;






