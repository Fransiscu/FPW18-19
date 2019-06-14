/* rendo stringa formattata come richiesto */
function createElement(user){
    return $("<li>").attr({"id" : "id_user"}).append(user.nome + ", " + user.cognome + " " + "(" + user.id + ")");
}

function stateSuccess(data){
    var authorId = $("#authorPick");
    $(authorId).empty();
    for(var instance in data){  // ciclo su data 
        $(authorId).append(createElement(data[instance]));  // chiamo createElement per l'elemento
    }
}

/* funzione errore */
function stateFailure(data, state){
    console.log(state);
}

/* parte ajax */
$(document).ready(function() {
    /* listenere per tasti premuti nel campo input di id author */
    $("#author").keyup(function(event){ 
       $.ajax({
          url: "suggest.json", 
          data: {cmd: "author",
                 toSearch: event.target.value
          },
          dataType: 'json',
          success: function(data, state){stateSuccess(data);},  // se il fetch del json va a buon fine
          error: function(data, state){stateFailure(data, state);}  // altrimenti
       });
    });
    
    /* listener per click nella class author_list */
    $(".author_list").click(function (event) {
        $("#author").val(event.target.innerText);
        $(".author_list").empty(); // pulisco la lista dopo il click per pura estetica
    });
    
    
    
});
