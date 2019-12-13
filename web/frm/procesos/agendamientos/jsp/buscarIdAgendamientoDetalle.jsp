
<%@page import="modelos.Actividades"%>
<%@page import="modelos.Agendamientos"%>
<%@page import="controladores.DetallesAgendamientoControlador"%>
<%@page import="modelos.DetalleAgendamientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleagendamiento = Integer.parseInt(request.getParameter("id_detalleagendamiento"));
    String id_agendamiento = request.getParameter("id_agendamiento");

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetalleAgendamientos detalleAgendamiento = DetallesAgendamientoControlador.buscarId(id_detalleagendamiento);
    if (detalleAgendamiento != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detalleAgendamiento = new DetalleAgendamientos();
        detalleAgendamiento.setId_detalleagendamiento(0);
        
        Agendamientos agendamiento = new Agendamientos();
        agendamiento.setId_agendamiento(0);
        detalleAgendamiento.setAgendamiento(agendamiento);
        
        Actividades actividad = new Actividades();
        actividad.setId_actividad(0);
        actividad.setNombre_actividad("");
        actividad.setPrecio_actividad(0);
        detalleAgendamiento.setAgendamiento(agendamiento);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detalleagendamiento", String.valueOf(detalleAgendamiento.getId_detalleagendamiento()));
    obj.put("id_agendamiento", String.valueOf(detalleAgendamiento.getAgendamiento().getId_agendamiento()));
    obj.put("id_actividad", String.valueOf(detalleAgendamiento.getActividad().getId_actividad()));
    obj.put("nombre_actividad", detalleAgendamiento.getActividad().getNombre_actividad());
    obj.put("precio_actividad", detalleAgendamiento.getActividad().getPrecio_actividad());
    //obj.put("cantidad_articulopedido", String.valueOf(detallepedido.getCantidad_articulopedido()));
    
    out.print(obj);
    out.flush();
%>