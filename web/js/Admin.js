$(window).ready(init);

function init() {

    $("#get").click(getPerson);
}
;

function getPerson() {
                            

    $.ajax({
        type: 'GET',
        url: "api/person/" + $("#id").val(),
        success: function (person) {
            
            $("#table").html(person.firstName + " " + person.lastName);
            
            
            
        }
    });
}
;
function addPerson() {
                            

    $.ajax({
        type: 'POST',
        url: "api/person/",
        data:$("#firstName") + $("#lastName"),
        success: function (person) {
            
            $("#table").html(person.firstName + " " + person.lastName);
            
            
            
        }
    });
}
;



