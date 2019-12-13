<%@page import="controladores.ActividadesControlador"%>

<%@page import="org.json.simple.JSONObject"%>

<% 
    String nombre_actividad = request.getParameter("bnombre_actividad");
   //   String precio_actividad = request.getParameter("bprecio_actividad");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    String mensaje = "Busqueda Exitosa";
    String contenido = ActividadesControlador.buscarNombre(nombre_actividad, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();

%>