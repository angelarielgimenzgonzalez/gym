function fechaHoy() {

    var hoy = new Date().toJSON().slice(0,10);
    console.log(hoy);
    $("#fecha_factura").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}
function buscarIdAgendamiento() {
    var datosFormulario = $("#formPrograma").serialize();
    
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdagendamiento.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
           // $("#mensajes").html(json.mensaje);
            $("#id_agendamiento").val(json.id_agendamiento);
          //  $("#fecha_factura").val(json.fecha_agendamiento);
          $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#ci_persona").val(json.ci_persona);
            ///$("#contenidoDetalle").html(json.contenido_detalle);
            var fecha = $("#fecha_agendamiento").serialize();
            if (json.nuevo === "true") {
                //$("#botonAgregar").prop('disabled', false);
                //$("#botonModificar").prop('disabled', true);
                //$("#botonEliminar").prop('disabled', true);
               // siguienteCampo("#id_actividad", "#botonAgregar", true);
                //$("#detalle").prop('hidden', true);
            } else {
               // $("#botonAgregar").prop('disabled', true);
                //$("#botonModificar").prop('disabled', false);
                //$("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#id_tipopedido", "#botonModificar", true);
               // $("#detalle").prop('hidden', false);
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
function cargaagend() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert("cargaaaa"+datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdagendamiento.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
           // $("#mensajes").html(json.mensaje);
            $("#id_agendamiento").val(json.id_agendamiento);
          //  $("#fecha_factura").val(json.fecha_agendamiento);
          $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#ci_persona").val(json.ci_persona);
            ///$("#contenidoDetalle").html(json.contenido_detalle);
            //var fecha = $("#fecha_agendamiento").serialize();
            if (json.nuevo === "true") { alert("yo soy nuevo");
                //$("#botonAgregar").prop('disabled', false);
                //$("#botonModificar").prop('disabled', true);
                //$("#botonEliminar").prop('disabled', true);
               // siguienteCampo("#id_actividad", "#botonAgregar", true);
                //$("#detalle").prop('hidden', true);
            } else { 
                agregarFactura();
               // $("#botonAgregar").prop('disabled', true);
                //$("#botonModificar").prop('disabled', false);
                //$("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#id_tipopedido", "#botonModificar", true);
               // $("#detalle").prop('hidden', false);
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
function buscarIdFactura() {
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
            $("#id_factura").val(json.id_factura);
            $("#fecha_factura").val(json.fecha_factura);
            //var fecha = $("#fecha_factura").serialize();           
            $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#ci_persona").val(json.ci_persona);
            $("#numero_factura").val(json.numero_factura);
            $("#estado_factura").val(json.estado_factura);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#id_tipofactura", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
               // $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#id_tipofactura", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }
            if (json.estado_factura === "ANULADO" || json.estado_factura === "COBRADO") {
                $(".bloquearboton").prop('disabled', true);
                $("#agregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', true);
                $("#botonCobrar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
            } else {
                $(".bloquearboton").prop('disabled', false);
                $("#agregar").prop('disabled', false);
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

function buscarNombreFactura() {
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
                $("#id_factura").val(id);
                // $("#nombre_persona").focus();
                buscarIdFactura();
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

function agregarFactura() {
    var datosFormulario = $("#formPrograma").serialize();
    alert("esto es agregar factura"+datosFormulario);
    console.log(datosFormulario);
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
            $("#id_factura").val(json.id_factura);
            detalle();
            // $("#id_factura").focus;
            //$("#id_factura").select();
            //}
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

function modificarFactura() {
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
            $("#id_factura").focus;
            $("#id_factura").select();
            buscarIdFactura();
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

function eliminarFactura() {
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
            eliminarFacturaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_factura").focus;
            $("#id_factura").select();
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


//persona
function  buscarIdPersona() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
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

            console.log(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#apellido_persona").val(json.apellido_persona);
            $("#ci_persona").val(json.ci_persona);

            console.log(json.nombre_persona);

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

//buscarfactura
function buscarNumeroFactura() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("N° de factura repetido");
                $("#numero_factura").val("");
                $("#numero_factura").focus();
                //siguienteCampo("#descripcion_actividad", "#botonModificar", true);
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

function validarFormulario() {
    var valor = true;
    if ($("#nombre_factura").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Factura no puede estar vacio.");
        $("#nombre_factura").focus();
    }

    if ($("#nombre_persona").val().length < 2) {
        valor = false;
        $("#mensajes").html("Persona no puede estar vacio.");
        $("#id_persona").focus();
    }

    /*if ($("#nombre_tipofactura").val().length < 2) {
     valor = false;
     $("#mensajes").html("Tipo Factura no puede estar vacio.");
     $("#id_tipofactura").focus();
     }*/
    return valor;
}
function limpiarFormulario() {
    $("#id_factura").val("0");
    $("#nombre_factura").val("");
    $("#nombre_tipofactura").val("");
    $("#numero_factura").val("0");
    $("#estado_factura").val("");
    $("#nombre_persona").val("");
    $("#ci_persona").val("");
    $("#id_persona").val("0");
    $("#id_tipocfactura").val("0");
    $("#fecha_factura").val("");
}
function agregarLinea() {
    $("#id_detalle_factura").val("0");
    $("#id_actividad").val("0");
    $("#nombre_actividad").val("");
    //$("#nombre_marca").val("");
    $("#codigo_actividad").val("");
    $("#precio_factura_actividad").val("0");
    $("#iva_actividad").val("0");
    $("#preciototal_factura").val("0");
    //$("#nombre_iva").val("");
    //$("#porcentaje_iva").val("0");
    $("#ssubtotal_5").val("0");
    $("#ssubtotal_10").val("0");    
    $("#ssubtotal_exenta").val("0");
    $("#ttotalgravada_5").val("0");
    $("#ttotalgravada_10").val("0"); 
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_actividad").focus();
    $("#id_actividad").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    buscarIdFacturaDetalle();
    siguienteCampo("#id_actividad", "#cantidad_factura", true);
}
function editarLinea(id) {
    $("#id_detalle_factura").val(id);
    //$("#id_actividad").val("0");
    //$("#nombre_actividad").val("");
    //$("#nombre_marca").val("");
    //$("#codigo_actividad").val("");
    //$("#precio_factura_actividad").val("0");
    //$("#iva_actividad").val("0");
    $("#cantidad_factura").val("");
    $("#preciototal_factura").val("0");
    //$("#nombre_iva").val("");
    //$("#porcentaje_iva").val("0");
    $("#ssubtotal_5").val("0");
    $("#ssubtotal_10").val("0");
    $("#ssubtotal_exenta").val("0");
    $("#ttotalgravada_5").val("0");
    $("#ttotalgravada_10").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_actividad").focus();
    $("#id_actividad").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdFacturaDetalle();
    siguienteCampo("#cantidad_actividad_factura", "#botonModificarLinea", true);
}
// facturasactividads
function buscarIdFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
   // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_actividad").val(json.id_actividad);
            $("#nombre_actividad").val(json.nombre_actividad);
            //$("#codigo_actividad").val(json.codigo_actividad);
            $("#precio_actividad").val(json.precio_actividad);
            $("#iva_actividad").val(json.iva_actividad);
            //$("#nombre_marca").val(json.nombre_marca);
            $("#cantidad_factura").val(json.cantidad_factura);
            $("#precio_total_factura").val(json.precio_total_factura);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#nombre_actividad", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#nombre_actividad", "#botonModificar", true);
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
function buscarIdFacturaFacturaDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdFacturaFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoDetalle").html(json.contenido_detalle);
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
function agregarFacturaDetalle() { 
    var datosFormulario = $("#formLinea").serialize();
    var id_factura = $("#id_factura").val();
    datosFormulario += "&id_factura=" + id_factura;
    
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {  
            if (json.cantidad_stock !== -1){
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            $("#detalle").prop('hidden', false);
            buscarIdFactura();
            } else {
                
                $("#mensajes").html(json.mensaje);
            }
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
function modificarFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_factura = $("#id_factura").val();
    datosFormulario += "&id_factura=" + id_factura;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarFacturaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdFactura();
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
function eliminarFacturaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_factura = $("#id_factura").val();
    datosFormulario += "&id_factura=" + id_factura;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarFacturaDetalle.jsp',
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
            buscarIdFactura();

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
// actividades
function buscarIdActividad() {
    var datosFormulario = $("#formLinea").serialize();
  //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdActividades.jsp',
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
        url: 'jsp/buscarNombreActividad.jsp',
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
                $("#panelLinea").fadeIn("slow");
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


function iva(e) {
    //document.getElementById(e.id).value;
    var valor, valor1, total;
    valor = $("#iva_actividad").val();
    valor1 = $("#precio_total_factura").val();
    total = (valor1 * valor) / 100;

    if ($("#iva_actividad").val().trim() === "10") {

        $("#ssubtotal_10").val(total);
        $("#ssubtotal_5").val(0);
        $("#ssubtotal_exenta").val(0);
    } else {
        if ($("#iva_actividad").val().trim() === "5") {

            $("#ssubtotal_5").val(total);
            $("#ssubtotal_10").val(0);
            $("#ssubtotal_exenta").val(0);
        } else {
            $("#ssubtotal_5").val(0);
            $("#ssubtotal_10").val(0);
            $("#ssubtotal_exenta").val(0);
        }
    }
}

//ivagravado
function ivagravada(e) {
    //document.getElementById(e.id).value;
    var valorc, valorp, totalg;
    //valorc = $("#cantidad_actividad_factura").val();
    valorp = $("#precio_factura_actividad").val();
    totalg = (valorc * valorp);

    if ($("#iva_actividad").val().trim() === "10") {

        $("#ttotalgravada_10").val(totalg);
        $("#ttotalgravada_5").val(0);
    } else {
        if ($("#iva_actividad").val().trim() === "5") {

            $("#ttotalgravada_5").val(totalg);
            $("#ttotalgravada_10").val(0);
        } else {
            $("#ttotalgravada_5").val(0);
            $("#ttotalgravada_10").val(0);
        }
    }
}
/*function iva(e) {
    //document.getElementById(e.id).value;
    var valor, valor1, total;
    valor = $("#porcentaje_iva").val();
    valor1 = $("#precio_total_factura").val();
    total = (valor1 * valor) / 100;

    if ($("#porcentaje_iva").val().trim() === "10") {

        $("#ssubtotal_10").val(total);
        $("#ssubtotal_5").val(0);
        $("#ssubtotal_exenta").val(0);
    } else {
        if ($("#porcentaje_iva").val().trim() === "5") {

            $("#ssubtotal_5").val(total);
            $("#ssubtotal_10").val(0);
            $("#ssubtotal_exenta").val(0);
        } else {
            $("#ssubtotal_5").val(0);
            $("#ssubtotal_10").val(0);
            $("#ssubtotal_exenta").val(0);
        }
    }
}*/
//ivagravado
/*function ivagravada(e) {
    //document.getElementById(e.id).value;
    var valor, valor1, totalg;
    valor = $("#cantidad_actividad_factura").val();
    valor1 = $("#preciototal_factura").val();
    totalg = (valor1 * valor);

    if ($("#porcentaje_iva").val().trim() === "10") {

        $("#ttotalgravada_10").val(totalg);
        $("#ttotalgravada_5").val(0);
    } else {
        if ($("#porcentaje_iva").val().trim() === "5") {

            $("#ttotalgravada_5").val(totalg);
            $("#ttotalgravada_10").val(0);
        } else {
            $("#ssubtotal_5").val(0);
            $("#ssubtotal_10").val(0);
        }
    }
}*/

function validarcantidad() {
    var valor = true;
    if ($("#cantidad_factura").val().trim() === "" || $("#cantidad_factura").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Cantidad debe ser mayor a 0.");
        $("#cantidad_factura").focus();
    }


    return valor;
}

function pretotal(e) {
    //Aqui crearemos una función para que de acuerdo a la cantidad de actividads, se haga un sub total
    var valor1, valor2,/*valor3, valor4,*/ subtotal/*, totaliva, preciototal*/;
    valor1 = $("#precio_factura_actividad").val();
    valor2 = $("#cantidad_factura").val();
    /*valor3 = $("#porcentaje_iva").val();
    valor4 = $("#precio_total_factura").val();*/
    subtotal = valor1 * valor2;
    //totaliva = valor3 * valor4;
    //precio_total = subtotal + totaliva;
    //$("#precio_total_factura").val(preciototal);
    $("#precio_total_factura").val(subtotal);
}

function numeros(evt) {
    if (window.event) {//asignamos el valor de la tecla a keynum
        keynum = evt.keyCode; //IE
    } else {
        keynum = evt.which; //FF
    }
    //comprobamos si se encuentra en el rango numérico y que teclas no recibirá.
    if ((keynum > 47 && keynum < 58) || keynum === 8 || keynum === 13 || keynum === 6) {
        return true;
    } else {
        alert("Solo ingrese numeros");
        //$("#codigo_articulo").val('');
        return false;
    }
}
function modificarpendiente() {
    var datosFormulario = $("#formPrograma").serialize();
   
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarpendiente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");

        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar el estado.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }

    });
}
function buscaridcajaa(){
    aa = document.getElementById("id_factura").value;
                                            location.href = "../cajasdetalles/index.html?id_factura=" + aa;
}
function actualizarestado() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/actualizarestado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");

        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            modificarpendiente();
            $("#id_factura").focus();
            $("#id_factura").select();
               
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar el estado.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }

    });
}
function detalle() {
    var datosFormulario = $("#formPrograma").serialize();
   
    $.ajax({
        type: 'POST',
        url: 'jsp/detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
           buscarIdFactura();
           // buscarIdPedidoFactura();
            //AgregarDetalleFactura();
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
function agregarFac() {
    var datosFormulario = $("#formPrograma").serialize();
    
    console.log(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarfac.jsp',
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
            $("#id_factura").val(json.id_factura);
            buscarIdFactura();
            // $("#id_factura").focus;
            //$("#id_factura").select();
            //}
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
