
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Agendamientos"%>
<%@page import="modelos.Personas"%>
<%@page import="controladores.AgendamientosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_agendamiento = Integer.parseInt(request.getParameter("id_agendamiento"));
  
    String tipo = "error";
    String mensaje = "Datos no modificados.";

   
   
    Agendamientos agendamiento = new Agendamientos();
    agendamiento.setId_agendamiento(id_agendamiento);
   

    if (AgendamientosControlador.modificarestado(agendamiento)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
