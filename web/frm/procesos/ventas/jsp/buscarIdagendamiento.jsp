

<%@page import="controladores.DetallesAgendamientoControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="controladores.AgendamientosControlador"%>
<%@page import="modelos.Agendamientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_agendamiento = Integer.parseInt(request.getParameter("id_agendamiento"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Agendamientos agendamiento = AgendamientosControlador.buscarIdfacturado(id_agendamiento);
    if (agendamiento.getId_agendamiento() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }

    //String contenido_detalle = DetallesAgendamientoControlador.buscarIdAgendamiento(id_agendamiento);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_agendamiento", agendamiento.getId_agendamiento());
    //obj.put("fecha_agendamiento", String.valueOf(agendamiento.getFecha_agendamiento()));
    obj.put("id_persona", String.valueOf(agendamiento.getPersonas().getId_persona()));
    obj.put("nombre_persona", String.valueOf(agendamiento.getPersonas().getNombre_persona()));
    obj.put("ci_persona", String.valueOf(agendamiento.getPersonas().getCi_persona()));
    //obj.put("contenido_detalle", contenido_detalle);

    out.print(obj);
    out.flush();
%>