
<%@page import="controladores.DetallesAgendamientoControlador"%>
<%@page import="modelos.Actividades"%>
<%@page import="modelos.Agendamientos"%>
<%@page import="modelos.DetalleAgendamientos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
//    int id_detalleagendamiento = Integer.parseInt(request.getParameter("id_detalleagendamiento"));
    //int cantidad_articulopedido = Integer.parseInt(request.getParameter("cantidad_articulopedido"));
    int id_agendamiento = Integer.parseInt(request.getParameter("id_agendamiento"));
    int id_actividad = Integer.parseInt(request.getParameter("id_actividad")); 
    int precio_actividad = Integer.parseInt(request.getParameter("precio_actividad"));
    String nombre_actividad = request.getParameter("nombre_actividad");

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    
    
    //detalleagendamiento.setCantidad_articulopedido(cantidad_articulopedido);
    
    Agendamientos agendamiento = new Agendamientos();
    agendamiento.setId_agendamiento(id_agendamiento);
    
    Actividades actividad = new Actividades();
    actividad.setId_actividad(id_actividad);
    actividad.setNombre_actividad(nombre_actividad);
    actividad.setPrecio_actividad(precio_actividad);
    
    DetalleAgendamientos detalleagendamiento= new DetalleAgendamientos();
   //detalleagendamiento.setId_detalleagendamiento(id_detalleagendamiento);
    
    detalleagendamiento.setAgendamiento(agendamiento);
    detalleagendamiento.setActividad(actividad);
      
    if (DetallesAgendamientoControlador.agregar(detalleagendamiento)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>