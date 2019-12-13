<%@page import="controladores.PersonasControlador"%>

<%@page import="org.json.simple.JSONObject"%>

<% 
    String nombre_persona = request.getParameter("bnombre_persona");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda Exitosa";
    String contenido = PersonasControlador.buscarNombre(nombre_persona, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();

%>