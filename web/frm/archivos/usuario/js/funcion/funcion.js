function agregarRubro() {
    alert("llega");
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function(json) {
            $("#mensaje").html(json.mensaje);
            limpiarFormulario();
            $("#id_rubro").focus();
            $("#id_rubro").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_rubro").focus();
        }
    });
}
function buscarIdRubro() {
    //alert("llega id");
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("enviando datos al servidor ...");
            alert("before");

        },
        exitosa: function (json) {
            //alert("success");
            $("#mensaje").html(json.mensaje);
            $("#id_rubro").val(json.id_rubro);

            $("#nombre_rubro").val(json.nombre_rubro);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
//                siguienteCampo("#nombre_rubro", "#botonAgregar", true);

            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //              siguienteCampo("#nombre_rubro", "#botonModificar", true);
            }

        },
        error: function (e) {
            //alert("error");
            $("#mensaje").html("no se pudo recuperar los datos. ");

        },
        complete: function (objeto, exito, error) {
            //alert("complete");
            if (exito === "exitosa") {

            }
        }
    });
}

function buscarNombreRubro() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({

        type: 'POST',
        url: "jsp/buscarNombe.jsp",
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (xhr) {
            $("#mensajes").html("Enviando datos al servidor");
            $("#contenidoBusqueda").css("display", "none");

        },
        success: function (json) {
            $("#mensaje").html("json.mensaje");
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html();
                $("#id_rubro").val(id);
                $("#nombre_rubro").focus();
                buscarIdRubro();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");

            });

        },
        error: function (e) {
            $("#mensajes").html("no se pudo buscar regidtros");

        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }

        }
    });
}


