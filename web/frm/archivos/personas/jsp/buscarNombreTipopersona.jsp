<%@page import="controladores.TiposPersonasControlador"%>

<%@page import="org.json.simple.JSONObject"%>

<% 
    String nombre_tipopersona = request.getParameter("bnombre_tipopersona");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda Exitosa";
    String contenido = TiposPersonasControlador.buscarNombre(nombre_tipopersona, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();

%>