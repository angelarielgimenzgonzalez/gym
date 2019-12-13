
<%@page import="controladores.AgendamientosControlador"%>
<%@page import="modelos.Agendamientos"%>
<%@page import="modelos.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_agendamiento = Integer.parseInt(request.getParameter("id_agendamiento"));
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Personas persona = new Personas();
    persona.setId_persona(id_persona);

    Agendamientos agendamiento = new Agendamientos();
    agendamiento.setId_agendamiento(id_agendamiento);
    agendamiento.setPersonas(persona);
   
    if (AgendamientosControlador.modificar(agendamiento)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>