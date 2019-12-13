

<%@page import="controladores.FacturasControlador"%>
<%@page import="modelos.Facturas"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
   
   int id_factura = 0;
    if (request.getParameter("id_factura") != "") {
        id_factura = Integer.parseInt(request.getParameter("id_factura"));
        
      
    }
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    Facturas facturas = FacturasControlador.buscartotal(id_factura);
    if (facturas != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        facturas = new Facturas();
        facturas.setId_factura(0);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_factura", facturas.getId_factura());
    obj.put("numero_factura", facturas.getNumero_factura());
    obj.put("total", facturas.getTotal());
    System.out.println("total "+ facturas.getTotal());

    out.print(obj);
    out.flush();
%>