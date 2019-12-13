
<%@page import="utiles.Utiles"%>
<%@page import="controladores.AgendamientosControlador"%>
<%@page import="modelos.Agendamientos"%>
<%@page import="modelos.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_agendamiento = Integer.parseInt(request.getParameter("id_agendamiento"));
    String sfecha_agendamiento = request.getParameter("fecha_agendamiento");
    java.sql.Date fecha_agendamiento = Utiles.stringToSqlDate(sfecha_agendamiento);
    int id_persona = Integer.parseInt(request.getParameter("id_persona")); 

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Personas persona = new Personas();
    persona.setId_persona(id_persona);
   
    Agendamientos agendamiento = new Agendamientos(); 
    agendamiento.setId_agendamiento(id_agendamiento);
    agendamiento.setFecha_agendamiento(fecha_agendamiento);
    agendamiento.setPersonas(persona);
      
    
    if (AgendamientosControlador.agregar(agendamiento)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_agendamiento", String.valueOf(agendamiento.getId_agendamiento()));
    out.print(obj);
    out.flush();
    
%>