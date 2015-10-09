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
          var person = {firstName:$("#firstName"), lastName:$("#lastName")};  
          JSON.stringify(person);

    $.ajax({
        type: 'POST',
        url: "api/person/",
        data:person,
        success: function (person) {
            
            $("#person").html(person.firstName + " " + person.lastName);
            
            
            
        }
    });
}
;



