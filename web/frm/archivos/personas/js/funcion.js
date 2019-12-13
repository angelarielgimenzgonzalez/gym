function agregarPersona() {

    var datosFormulario = $("#formPrograma").serialize();
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            //alert("antes");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            var mensaje1 = $("#mensajes").html(json.mensaje);
            console.log(mensaje1);
            limpiarFormulario();
            $("#id_persona").focus();
            $("#id_persona").select();
            //alert("exito");
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
            alert("error");
        },
        complete: function (objeto, exito, error) {
            $("#id_persona").focus();
        }
    });
}
function  buscarIdPersona() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_persona").val(json.id_persona);

            console.log(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#apellido_persona").val(json.apellido_persona);
            $("#direccion_persona").val(json.direccion_persona);
            $("#correo_persona").val(json.correo_persona);
            $("#ci_persona").val(json.ci_persona);
            $("#telefono_persona").val(json.telefono_persona);
            $("#edad_persona").val(json.edad_persona);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);
            $("#id_genero").val(json.id_genero);
            $("#nombre_genero").val(json.nombre_genero);
            $("#id_tipopersona").val(json.id_tipopersona);
            $("#nombre_tipopersona").val(json.nombre_tipopersona);

            console.log(json.nombre_persona);

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                // siguienteCampo("#nombre_persona", "#botonModificar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#nombre_persona", "#tru", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se puede recuperar datos");
        },
        complete: function (objet, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombrePersona() {
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
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_persona").val(id);
                $("#nombre_persona").focus();
                buscarIdPersona();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar los registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }

    });
}

function modificarPersona() {
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
            $("#id_persona").focus();
            $("#id_persona").select();
        },
        error: function (e) {

            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}
function eliminarPersona() {
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
            $("#id_persona").focus();
            $("#id_persona").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            //alert ("Termino la eliminacion");
            if (exito === "success") {

            }
        }
    });
}
function validarFormulario() {
    var valor = true;
    if ($("#nombre_persona").val().trim() === "") {
        valor = false;
        $("#mensajes").html("El campo nombre no puede estar vacio.");
        $("#nombe_persona").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_persona").val("0");
    $("#nombre_persona").val("");
    $("#apellido_persona").val("");
    $("#direccion_persona").val("");
    $("#correo_persona").val("");
    $("#ci_persona").val("");
    $("#telefono_persona").val("");
    $("#edad_persona").val("");
    $("#id_ciudad").val(0);
    $("#nombre_ciudad").val("");
    $("#id_estadocivil").val(0);
    $("#nombre_estadocivil").val("");
    $("#id_genero").val(0);
    $("#nombre_genero").val("");
    $("#id_tipopersona").val(0);
    $("#nombre_tipopersona").val("");
}
function  buscarIdCiudad(){
    var datosFormulario=$("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCiudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto){
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json){
            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
           
            console.log(json.id_ciudad);
           
            $("#nombre_ciudad").val(json.nombre_ciudad);
            console.log(json.nombre_ciudad);

            if (json.nuevo==="true"){
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', true);
                $("#botonEliminar").prop('disable', true);
                siguienteCampo("#nombre_ciudad", "#botonModificar", true);
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
function buscarNombreCiudad(){
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCiudad.jsp',
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
                $("#id_ciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudad();
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
function  buscarIdEstadoCivil(){
    var datosFormulario=$("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEstadocivil.jsp',
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
        url: 'jsp/buscarNombreEstadocivil.jsp',
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
function  buscarIdGenero(){
    var datosFormulario=$("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdGenero.jsp',
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
        url: 'jsp/buscarNombreGenero.jsp',
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
function  buscarIdTipoPersona() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTipopersona.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_tipopersona").val(json.id_tipopersona);

            console.log(json.id_tipopersona);

            $("#nombre_tipopersona").val(json.nombre_tipopersona);
            console.log(json.nombre_tipopersona);

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', true);
                $("#botonEliminar").prop('disable', true);
                siguienteCampo("#nombre_tipopersona", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se puede recuperar datos");
        },
        complete: function (objet, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreTipoPersona() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreTipopersona.jsp',
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
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_tipopersona").val(id);
                $("#nombre_tipopersona").focus();
                buscarIdTipoPersona();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar los registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }

    });
}
