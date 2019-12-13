
<%@page import="modelos.Agendamientos"%>
<%@page import="modelos.Actividades"%>
<%@page import="modelos.DetalleAgendamientos"%>
<%@page import="controladores.DetallesAgendamientoControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_detalleagendamiento = Integer.parseInt(request.getParameter("id_detalleagendamiento"));
    //int cantidad_articulopedido = Integer.parseInt(request.getParameter("cantidad_articulopedido"));
   int id_agendamiento = Integer.parseInt(request.getParameter("id_agendamiento"));
    int id_actividad = Integer.parseInt(request.getParameter("id_actividad")); 
    

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    DetalleAgendamientos detalleAgendamientos = new DetalleAgendamientos();
    detalleAgendamientos.setId_detalleagendamiento(id_detalleagendamiento);
    //detalleAgendamientos.setCantidad_articulopedido(cantidad_articulopedido);
    
    Agendamientos agendamientos = new Agendamientos();
    agendamientos.setId_agendamiento(id_agendamiento);
    
    Actividades actividades = new Actividades();
    actividades.setId_actividad(id_actividad);
    
    detalleAgendamientos.setAgendamiento(agendamientos);
    detalleAgendamientos.setActividad(actividades);
      
    if (DetallesAgendamientoControlador.modificar(detalleAgendamientos)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>