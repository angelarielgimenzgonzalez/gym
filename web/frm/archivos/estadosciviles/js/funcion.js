function agregarEstadoCivil(){

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
            $("#id_estadocivil").focus();
            $("#id_estadocivil").select();
        },
        error: function (e){
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_estadocivil").focus();
        }
    });
}
function  buscarIdEstadoCivil(){
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
            $("#id_estadocivil").val(json.id_estadocivil);
           
            console.log(json.id_estadocivil);
           
            $("#nombre_estadocivil").val(json.nombre_estadocivil);
            console.log(json.nombre_estadocivil);

            if (json.nuevo==="true"){
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', true);
                $("#botonEliminar").prop('disable', true);
                siguienteCampo("#nombre_estadocivil", "#botonModificar", true);
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
function buscarNombreEstadoCivil(){
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
                $("#id_estadocivil").val(id);
                $("#nombre_estadocivil").focus();
                buscarIdEstadoCivil();
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

function modificarEstadoCivil(){
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
            $("#id_estadocivil").focus();
            $("#id_estadocivil").select();
        },
        error: function (e) {
            
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
          
        }
    });
}
function eliminarEstadoCivil(){
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
           
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_estadocivil").focus();
            $("#id_estadocivil").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            
            if (exito === "success"){
                
            }
        }
    });
}
function validarFormulario(){
    var valor = true;
    if ($("#nombre_estadocivil").val().trim() === ""){
        valor = false;
        $("#mensajes").html("El campo nombre no pued estar vacio.");
        $("#nombe_estadocivil").focus();
    }
    return valor;
}
function limpiarFormulario(){
    $("#id_estadocivil").val("0");
    $("#nombre_estadocivil").val("");
}
function buscarEstadosciviles() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarestadocivil.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {
                agregarEstadoCivil();

            } else {
                alert("Nombre de estado civil existente");
                $("#nombre_estadocivil").val("");
                $("#nombre_estadocivil").focus();
                
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
    
    
    }
