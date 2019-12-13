
<%@page import="modelos.Actividades"%>
<%@page import="controladores.DetallesFacturasControlador"%>
<%@page import="modelos.DetallesFacturas"%>
<%@page import="controladores.FacturasControlador"%>
<%@page import="modelos.Facturas"%>
<%@page import="modelos.Personas"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    //int id_detallefactura = Integer.parseInt(request.getParameter("id_detallefactura"));
    // int cantidad_producto_factura = Integer.parseInt(request.getParameter("cantidad_producto_factura"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int id_actividad = Integer.parseInt(request.getParameter("id_actividad"));
    int precio_total_factura = Integer.parseInt(request.getParameter("precio_actividad"));
    int cantidad_factura = Integer.parseInt(request.getParameter("cantidad_factura"));
    //   String nombre_actividad = request.getParameter("nombre_actividad");
    //  String precio_actividad = request.getParameter("precio_actividad");
    /*int subtotal_5 = Integer.parseInt(request.getParameter("ssubtotal_5"));
    int subtotal_10 = Integer.parseInt(request.getParameter("ssubtotal_10"));
    int subtotal_exenta = Integer.parseInt(request.getParameter("ssubtotal_exenta"));
    int totalgravada_5 = Integer.parseInt(request.getParameter("ttotalgravada_5"));
    int totalgravada_10 = Integer.parseInt(request.getParameter("ttotalgravada_10"));*/
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    DetallesFacturas detallefactura = new DetallesFacturas();
    //  detallefactura.setId_detallefactura(id_detallefactura);
    detallefactura.setPrecio_total_factura(precio_total_factura);
    detallefactura.setCantidad_factura(cantidad_factura);
    
    Actividades actividad = new Actividades();
    actividad.setId_actividad(id_actividad);
    //  actividad.setNombre_actividad(nombre_actividad);
    // actividad.setPrecio_actividad(precio_actividad);

    /*Ubicaciones ubicacion = new Ubicaciones();
    ubicacion.setId_ubicacion(id_producto);
    producto.setUbicacion(ubicacion);*/
    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);

    /*factura.setSubtotal_10(subtotal_10);
    factura.setSubtotal_5(subtotal_5);
    factura.setSubtotal_exenta(subtotal_exenta);
    factura.setTotalgravada_10(totalgravada_10);
    factura.setTotalgravada_5(totalgravada_5);
    FacturasControlador.totalgravadas(factura);
    FacturasControlador.subtotaliva(factura);*/
    detallefactura.setFactura(factura);
    detallefactura.setActividad(actividad);
    
    if (DetallesFacturasControlador.agregar(detallefactura)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    //obj.put("cantidad_stock", stock.getCantidad_stock());
    out.print(obj);
    out.flush();
%>
