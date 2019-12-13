
<%@page import="modelos.Actividades"%>
<%@page import="controladores.DetallesFacturasControlador"%>
<%@page import="modelos.DetallesFacturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallefactura = Integer.parseInt(request.getParameter("id_detallefactura"));
    int id_actividad = Integer.parseInt(request.getParameter("id_actividad"));
    //int cantidad_actividad_factura = Integer.parseInt(request.getParameter("cantidad_actividad_factura"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesFacturas detallefactura = new DetallesFacturas();
    detallefactura.setId_detallefactura(id_detallefactura);
    
    Actividades actividad = new Actividades();
    actividad.setId_actividad(id_actividad);
    
   //Stocks stock = new Stocks();
    //stock.setCantidad_stock(cantidad_actividad_factura);
    //stock.setActividad(actividad);
    //StocksControlador.sumar(stock);

    if (DetallesFacturasControlador.eliminar(detallefactura)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>