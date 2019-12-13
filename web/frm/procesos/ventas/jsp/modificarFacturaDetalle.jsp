<%@page import="controladores.DetallesFacturasControlador"%>
<%@page import="modelos.Actividades"%>
<%@page import="modelos.Facturas"%>
<%@page import="modelos.DetallesFacturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_detallefactura = Integer.parseInt(request.getParameter("id_detallefactura"));
    int cantidad_factura = Integer.parseInt(request.getParameter("cantidad_factura"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int id_actividad = Integer.parseInt(request.getParameter("id_actividad"));
    int precio_total_factura = Integer.parseInt(request.getParameter("precio_total_factura"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    DetallesFacturas detallefactura = new DetallesFacturas();
    detallefactura.setId_detallefactura(id_detallefactura);
    //detallefactura.setCantidad_actividad_factura(cantidad_actividad_factura);
    detallefactura.setPrecio_total_factura(precio_total_factura);

    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);

    Actividades actividad = new Actividades();
    actividad.setId_actividad(id_actividad);

    detallefactura.setFactura(factura);
    detallefactura.setActividad(actividad);

    if (DetallesFacturasControlador.modificar(detallefactura)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>