function agregarUsuario(){
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
            $("#id_usuario").focus();
            $("#id_usuario").select();
        },
        error: function (e){
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            $("#id_usuario").focus();
        }
    });
}
function buscarIdUsuario(){
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
            $("#id_usuario").val(json.id_usuario);
           
           // console.log(json.id_usuario);
           
            $("#nombre_usuario").val(json.nombre_usuario);
            $("#login_usuario").val(json.login_usuario);
            $("#password_usuario").val(json.password_usuario);
            
            //console.log(json.nombre_usuario);

            if (json.nuevo==="true"){
                $("#botonAgregar").prop('disable', false);
                $("#botonModificar").prop('disable', true);
                $("#botonEliminar").prop('disable', true);
               // siguienteCampo("#nombre_usuario", "#botonModificar", true);
            }
            else {
                $("#botonAgregar").prop('disable', true);
                $("#botonModificar").prop('disable', false);
                $("#botonEliminar").prop('disable', false);
                
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
function buscarNombreUsuario(){
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
                $("#id_usuario").val(id);
                $("#nombre_usuario").focus();
                buscarIdUsuario();
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

function agregarUsuario(){
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al servidor...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_usuario").focus();
            $("#id_usuario").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_usuario").focus();
        }
    });
}
function modificarUsuario(){
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
            $("#id_usuario").focus();
            $("#id_usuario").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            
        }
    });
}
function eliminarUsuario(){
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
            $("#id_usuario").focus();
            $("#id_usuario").select();
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
    if ($("#nombre_usuario").val().trim() === ""){
        valor = false;
        $("#mensajes").html("El campo nombre no pued estar vacio.");
        $("#nombe_usuario").focus();
    }
    return valor;
}
function limpiarFormulario(){
    $("#id_usuario").val("0");
    $("#nombre_usuario").val("");
    $("#login_usuario").val("");
    $("#password_usuario").val("");
}
function siguienteCampo(actual, siguiente, preventDefault) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            if (preventDefault) {
                event.preventDefault();
            }
            $(siguiente).focus();
            $(siguiente).select();
        }
    });
}
function enterCampo(actual, ejecutar) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            eval(ejecutar);
        }
    });
}
function validarAcceso() {
    $("mensajes").html("Mensajes del Sistema");
    if ($("usuario_usuario").val() === "") {
        $("mensajes").html("Usuario no debe estar vacio.");
        setTimeout(' location.reload()', 1500);
    } else if ($("password_usuario").val() === "") {
        $("mensajes").html("Clave no debe estar vacio.");
        setTimeout(' location.reload()', 1500);
    } else {
        validarAccesoAjax();
    }
}
function validarAccesoAjax() {
    var datosFormulario = $("#formAcceso").serialize();
//alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/validarAcceso.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.acceso === "true") {
                location.href = "menu.html";
            } else {
                $("#mensajes").html("Credencial Invalida");
                setTimeout(' location.reload()', 1500);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo conectar con el servidor Error:" + e.status);
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function verificarSesion(programa) {
    
    var url = 'jsp/verificarSesion.jsp';
    if (programa) {
        url = '../../../jsp/verificarSesion.jsp';
    }
    var datosFormulario = $("#formAcceso").serialize();
    $.ajax({
        type: 'POST',
        url: url,
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.activo === "false") {
                if (programa) {
                    location.href = "../../../index.html";
                } else {
                    location.href = "index.html";
                }
            }
            $("#snombre_empresa").html(json.nombre_empresa);
            $("#susuario_usuario").html(json.usuario_usuario);
       //   $("#slogin_usuario").html(json.login_usuario);
          
            $("#mensajes").html(json.mensaje);
        },
        error: function (e) {
            $("#mensajes").html("ERROR: No se pudo recuperar la sesión.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function cerrarSesion() {
    var datosFormulario = $("#formAcceso").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/cerrarSesion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html("Sesión Cerrada.");
        },
        error: function (e) {
            $("#mensajes").html("No se pudo cerrar la sesión.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function sololetras(e) {
            var e;
                key = e.keyCode || e.which;
                tecla = String.fromCharCode(key).toLowerCase();
                letras = "  abcdefghijkmnñopqrstuvwxyz";
                especiales = [8, 37, 39, 46];

                tecla_especial = false;
                for (var i in especiales) {
                    if (key === especiales[i]) {
                        tecla_especial = true;
                        break;
                    }
                }

                if (letras.indexOf(tecla) === -1 && !tecla_especial)
                    return false;
            }