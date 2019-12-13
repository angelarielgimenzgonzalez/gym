<%@page import="controladores.CiudadesControlador"%>

<%@page import="org.json.simple.JSONObject"%>

<% 
    String nombre_ciudad = request.getParameter("bnombre_ciudad");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda Exitosa";
    String contenido = CiudadesControlador.buscarNombre(nombre_ciudad, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();

%>