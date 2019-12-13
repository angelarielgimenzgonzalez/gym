<%@page import="controladores.FacturasControlador"%>
<%@page import="modelos.Facturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int numero_factura = Integer.parseInt(request.getParameter("numero_factura"));
    String tipo = "error";
    String mensaje = "Datos no repetidos";
    String nuevo = "true";
    Facturas factura = new Facturas();
    factura.setNumero_factura(numero_factura);

    FacturasControlador.buscarNumFactura(numero_factura);
    if (factura.getId_factura() == 0) {
        tipo = "success";
        mensaje = "Numero de factura repetido";
        nuevo = "false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("numero_factura", factura.getNumero_factura());

    out.print(obj);
    out.flush();
%>