
<%@page import="controladores.AgendamientosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_agendamiento = request.getParameter("bnombre_agendamiento");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = AgendamientosControlador.buscarNombre(nombre_agendamiento, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    System.out.println("mensaje"+contenido);
    
    out.print(obj);
    out.flush();
%>