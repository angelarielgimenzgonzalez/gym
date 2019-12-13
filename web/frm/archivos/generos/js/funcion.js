function agregarGenero(){
    
    var datosFormulario =$("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data:  datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
             var mensaje1 = $("#mensajes").html(json.mensaje);
            console.log(mensaje1);
            limpiarFormulario();
            $("#id_genero").focus();
            $("#id_genero").select();
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_genero").focus();
        }
    });
}
function  buscarIdGenero(){
    var datosFormulario=$("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
            $("#id_genero").val(json.id_genero);
           
            console.log(json.id_genero);
           
            $("#nombre_genero").val(json.nombre_genero);
            console.log(json.nombre_genero);

            if (json.nuevo==="true"){
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', true);
                $("#botonEliminar").prop('disable', true);
                siguienteCampo("#nombre_genero", "#botonModificar", true);
            }
        },
        error: function (e){
            $("#mensajes").html("No se puede recuperar datos");
        },
        complete: function(objet, exito, error){
            if (exito==="success"){   
            }
        }
    });
}
function buscarNombreGenero(){
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
            $("#contenidoBusqueda").css("display", "none");
            
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function(){
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_genero").val(id);
                $("#nombre_genero").focus();
                buscarIdGenero();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e){
            $("#mensajes").html("No se pudo buscar los registros.");
        },
        complete: function (objeto,exito, error){
            if (exito === "success"){
                
            }
        }
        
    });
}

function modificarGenero(){
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_genero").focus();
            $("#id_genero").select();
        },
        error: function (e) {
            
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
          
        }
    });
}
function eliminarGenero(){
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            //alert ("entro en beforesent eliminar");
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
           // alert ("eliminado");
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_genero").focus();
            $("#id_genero").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            //alert ("Termino la eliminacion");
            if (exito === "success"){
                
            }
        }
    });
}
function validarFormulario(){
    var valor = true;
    if ($("#nombre_genero").val().trim() === ""){
        valor = false;
        $("#mensajes").html("El campo nombre no pued estar vacio.");
        $("#nombe_genero").focus();
    }
    return valor;
}
function limpiarFormulario(){
    $("#id_genero").val("0");
    $("#nombre_genero").val("");
}


