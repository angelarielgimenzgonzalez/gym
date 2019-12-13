<%@page import="controladores.GenerosControlador"%>

<%@page import="org.json.simple.JSONObject"%>

<% 
    String nombre_genero = request.getParameter("bnombre_genero");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda Exitosa";
    String contenido = GenerosControlador.buscarNombre(nombre_genero, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();

%>