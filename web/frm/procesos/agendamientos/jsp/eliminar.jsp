
<%@page import="controladores.AgendamientosControlador"%>
<%@page import="modelos.Agendamientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_agendamiento = Integer.parseInt(request.getParameter("id_agendamiento"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    Agendamientos agendamiento = new Agendamientos();
    agendamiento.setId_agendamiento(id_agendamiento);

    if (AgendamientosControlador.eliminar(agendamiento)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>