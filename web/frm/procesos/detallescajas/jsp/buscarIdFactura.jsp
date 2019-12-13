

<%@page import="groovy.sql.Sql"%>
<%@page import="controladores.DetallesFacturasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="controladores.FacturasControlador"%>
<%@page import="modelos.Facturas"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
     int id_factura = Integer.parseInt(request.getParameter("id_factura"));
   
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Facturas factura = FacturasControlador.buscarId(id_factura);
    if (factura.getId_factura() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    String contenido_detalle = DetallesFacturasControlador.buscarIdFactura(id_factura);

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_factura", String.valueOf(factura.getId_factura()));
    obj.put("fecha_factura", String.valueOf(factura.getFecha_factura()));
    obj.put("id_persona", String.valueOf(factura.getPersona().getId_persona()));
    obj.put("nombre_persona", factura.getPersona().getNombre_persona());
    obj.put("ci_persona", factura.getPersona().getCi_persona());
    obj.put("numero_factura", factura.getNumero_factura());
    obj.put("estado_factura", factura.getEstado_factura());
    obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>