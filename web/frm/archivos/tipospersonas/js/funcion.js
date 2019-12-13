function agregarTipoPersona(){
    alert("Exito");
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
            $("#id_tipopersona").focus();
            $("#id_tipopersona").select();
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_tipopersona").focus();
        }
    });
}
function  buscarIdTipoPersona(){
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
            $("#id_tipopersona").val(json.id_tipopersona);
           
            console.log(json.id_tipopersona);
           
            $("#nombre_tipopersona").val(json.nombre_tipopersona);
            console.log(json.nombre_tipopersona);

            if (json.nuevo==="true"){
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', true);
                $("#botonEliminar").prop('disable', true);
                siguienteCampo("#nombre_tipopersona", "#botonModificar", true);
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
function buscarNombreTipoPersona(){
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
                $("#id_tipopersona").val(id);
                $("#nombre_tipopersona").focus();
                buscarIdTipoPersona();
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

function modificarTipoPersona(){
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
            $("#id_tipopersona").focus();
            $("#id_tipopersona").select();
        },
        error: function (e) {
            
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
          
        }
    });
}
function eliminarTipoPersona(){
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
            $("#id_tipopersona").focus();
            $("#id_tipopersona").select();
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
    if ($("#nombre_tipopersona").val().trim() === ""){
        valor = false;
        $("#mensajes").html("El campo nombre no pued estar vacio.");
        $("#nombe_tipopersona").focus();
    }
    return valor;
}
function limpiarFormulario(){
    $("#id_tipopersona").val("0");
    $("#nombre_tipopersona").val("");
}


