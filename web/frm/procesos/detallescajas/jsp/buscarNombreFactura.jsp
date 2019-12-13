<%@page import="controladores.FacturasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_factura = request.getParameter("bnombre_factura");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Búsqueda exitosa.";
    String contenido = FacturasControlador.buscarNombre(nombre_factura, pagina);

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    out.print(obj);
    out.flush();
%>