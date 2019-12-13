
<%@page import="modelos.DetalleAgendamientos"%>
<%@page import="controladores.DetallesAgendamientoControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleagendamiento = Integer.parseInt(request.getParameter("id_detalleagendamiento"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetalleAgendamientos detalleAgendamientos = new DetalleAgendamientos();
    detalleAgendamientos.setId_detalleagendamiento(id_detalleagendamiento);

    if (DetallesAgendamientoControlador.eliminar(detalleAgendamientos)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>