function buscarIdAgendamiento() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_agendamiento").val(json.id_agendamiento);
            $("#fecha_agendamiento").val(json.fecha_agendamiento);
            $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#contenidoDetalle").html(json.contenido_detalle);
            var fecha = $("#fecha_agendamiento").serialize();
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
               // siguienteCampo("#id_actividad", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#id_tipopedido", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreAgendamiento() {
   
    var datosFormulario = $("#formBuscar").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_agendamiento").val(id);
                $("#fecha_agendamiento").val();
                $("#id_persona").val();
                $("#nombre_persona").focus();
                buscarIdAgendamiento();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarAgendamiento() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_agendamiento").val(json.id_agendamiento);
//            $("#fecha_agendamiento").val(json.fecha_agendamiento);
//             $("#id_persona").val(json.id_persona);
//            $("#nombre_persona").val(json.nombre_persona);
            buscarIdAgendamiento();
             $("#id_agendamiento").focus;
            $("#id_agendamiento").select();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarAgendamiento() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_agendamiento").focus;
            $("#id_agendamiento").select();
            buscarIdAgendamiento();
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
function eliminarAgendamiento() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            eliminarAgendamientoDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_agendamiento").focus;
            $("#id_agendamiento").select();
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


function  buscarIdPersona() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPersona.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_persona").val(json.id_persona);
           // console.log(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#apellido_persona").val(json.apellido_persona);
            $("#direccion_persona").val(json.direccion_persona);
//            $("#correo_persona").val(json.correo_persona);
//            $("#ci_persona").val(json.ci_persona);
//            $("#telefono_persona").val(json.telefono_persona);
//            $("#edad_persona").val(json.edad_persona);
//            $("#id_ciudad").val(json.id_ciudad);
//            $("#nombre_ciudad").val(json.nombre_ciudad);
//            $("#id_estadocivil").val(json.id_estadocivil);
//            $("#nombre_estadocivil").val(json.nombre_estadocivil);
//            $("#id_genero").val(json.id_genero);
//            $("#nombre_genero").val(json.nombre_genero);
//            $("#id_tipopersona").val(json.id_tipopersona);
//            $("#nombre_tipopersona").val(json.nombre_tipopersona);

          //  console.log(json.nombre_persona);

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                // siguienteCampo("#nombre_persona", "#botonModificar", true);
            } else {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
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
        url: 'jsp/buscarNombrePersona.jsp',
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


function validarFormulario() {
    var valor = true;
    if ($("#nombre_agendamiento").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Agendamiento no puede estar vacio.");
        $("#nombre_agendamiento").focus();
    }

    if ($("#nombre_persona").val().length < 2) {
        valor = false;
        $("#mensajes").html("Personas no puede estar vacio.");
        $("#id_persona").focus();
    }

//    if ($("#nombre_tipopedido").val().length < 2) {
//        valor = false;
//        $("#mensajes").html("Tipo Pedido no puede estar vacio.");
//        $("#id_tipopedido").focus();
//    }
    return valor;
}
function fechahoy(){
    var hoy = new Date().toJSON().slice(0,10);
    
    console.log(hoy);
    $("#fecha_agendamiento").val(hoy);
}
function limpiarFormulario() {
    $("#id_agendamiento").val("0");
    $("#fecha_agendamiento").val("");
    //$("#nombre_tipopedido").val("");
    $("#nombre_persona").val("");
    $("#id_persona").val("0");
    //$("#id_tipocpedido").val("0");

}
function agregarLinea() {
    $("#id_detalleagendamiento").val("0");
    $("#id_actividad").val("0");
    $("#nombre_actividad").val("");
//    $("#cantidad_articulopedido").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_actividad").focus();
    $("#id_actividad").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detalleagendamiento", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detalleagendamiento").val(id);
    $("#id_actividad").val("0");
    $("#nombre_actividad").val("");
//    $("#cantidad_articulopedido").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_actividad").focus();
    $("#id_actividad").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdAgendamientoDetalle;
   // siguienteCampo("#cantidad_articulopedido", "#botonModificarLinea", true);
}
// pedidosarticulos
function buscarIdAgendamientoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAgendamientoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_actividad").val(json.id_articulo);
            $("#nombre_actividad").val(json.nombre_articulo);
//            $("#cantidad_articulopedido").val(json.cantidad_articulopedido);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//function buscarIdPedidoPedidoDetalle() {
//    var datosFormulario = $("#formPrograma").serialize();
//    $.ajax({
//        type: 'POST',
//        url: 'jsp/buscarIdPedidoPedidoDetalle.jsp',
//        data: datosFormulario,
//        dataType: 'json',
//        beforeSend: function (objeto) {
//            $("#mensajes").html("Enviando datos al Servidor ...");
//        },
//        success: function (json) {
//            $("#mensajes").html(json.mensaje);
//            $("#contenidoDetalle").html(json.contenido_detalle);
//        },
//        error: function (e) {
//            $("#mensajes").html("No se pudo recuperar los datos.");
//        },
//        complete: function (objeto, exito, error) {
//            if (exito === "success") {
//            }
//        }
//    });
//}
function agregarAgendamientoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_agendamiento = $("#id_agendamiento").val();
    datosFormulario += "&id_agendamiento=" + id_agendamiento;
    
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarAgendamientoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdAgendamiento();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarAgendamientoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_agendamiento = $("#id_agendamiento").val();
    datosFormulario += "&id_agendamiento=" + id_agendamiento;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarAgendamientoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdAgendamiento();
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
function eliminarAgendamientoDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_agendamiento = $("#id_agendamiento").val();
    datosFormulario += "&id_agendamiento=" + id_agendamiento;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarAgendamientoDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdAgendamiento();

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
////// articulos
function buscarIdActividad() {
    var datosFormulario = $("#formLinea").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdActividad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_actividad").val(json.id_actividad);
            $("#nombre_actividad").val(json.nombre_actividad);
            $("#precio_actividad").val(json.precio_actividad);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreActividad() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreActividad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
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
                $("#panelLinea").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdCaja() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCaja.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
           
            
            if (json.nuevo === "true") {
              location.href="../cajas/index.html";
            } else {
                
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
