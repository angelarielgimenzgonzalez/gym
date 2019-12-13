function agregarHorario(){
    
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
            $("#id_horario").focus();
            $("#id_horario").select();
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_horario").focus();
        }
    });
}
function  buscarIdHorario(){
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
            $("#id_horario").val(json.id_horario);
           
            console.log(json.id_horario);
           
            $("#nombre_horario").val(json.nombre_horario);
            $("#horain_horario").val(json.horain_horario);
            $("#horafin_horario").val(json.horafin_horario);
            console.log(json.nombre_horario);

            if (json.nuevo==="true"){
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', true);
                $("#botonEliminar").prop('disable', true);
                siguienteCampo("#nombre_horario", "#botonModificar", true);
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
function buscarNombreHorario(){
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
                $("#id_horario").val(id);
                $("#nombre_horario").focus();
                buscarIdHorario();
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

function modificarHorario(){
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
            $("#id_horario").focus();
            $("#id_horario").select();
        },
        error: function (e) {
            
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
          
        }
    });
}
function eliminarHorario(){
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
            $("#id_horario").focus();
            $("#id_horario").select();
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
    if ($("#nombre_horario").val().trim() === ""){
        valor = false;
        $("#mensajes").html("El campo nombre no puede estar vacio.");
        $("#nombe_horario").focus();
    }
    return valor;
}
function limpiarFormulario(){
    $("#id_horario").val("0");
    $("#nombre_horario").val("");
    $("#horain_horario").val("");
    $("#horafin_horario").val("");
}


