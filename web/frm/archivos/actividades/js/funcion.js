function agregarActividad() {

    var datosFormulario = $("#formPrograma").serialize();
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            var mensaje1 = $("#mensajes").html(json.mensaje);
            console.log(mensaje1);
            limpiarFormulario();
            $("#id_actividad").focus();
            $("#id_actividad").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_actividad").focus();
        }
    });
}
function buscarIdActividad() {
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
            $("#id_actividad").val(json.id_actividad);

           // console.log(json.id_actividad);

            $("#nombre_actividad").val(json.nombre_actividad);
            $("#precio_actividad").val(json.precio_actividad);
            $("#turno_actividad").val(json.turno_actividad);
            $("#iva_actividad").val(json.iva_actividad);
           // console.log(json.nombre_actividad);


            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', true);
                $("#botonEliminar").prop('disable', true);
                siguienteCampo("#nombre_actividad", "#botonModificar", true);
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
function buscarNombreActividad() {
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
                $("#id_actividad").val(id);
                $("#nombre_actividad").focus();
                buscarIdActividad();
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

function modificarActividad() {
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
            $("#id_actividad").focus();
            $("#id_actividad").select();
        },
        error: function (e) {

            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}
function eliminarActividad() {
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
            $("#id_actividad").focus();
            $("#id_actividad").select();
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
    if ($("#nombre_actividad").val().trim() === "") {
        valor = false;
        $("#mensajes").html("El campo nombre no puede estar vacio.");
        $("#nombe_actividad").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_actividad").val("0");
    $("#nombre_actividad").val("");
    $("#precio_actividad").val("");
    $("#turno_actividad").val("");
    $("#iva_actividad").val("");
}


