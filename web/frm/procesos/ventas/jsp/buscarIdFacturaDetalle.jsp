<%//@page import="modelos.Ivas"%>
<%@page import="modelos.Actividades"%>
<%@page import="modelos.Facturas"%>
<%@page import="controladores.DetallesFacturasControlador"%>
<%@page import="modelos.DetallesFacturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalle_factura = Integer.parseInt(request.getParameter("id_detalle_factura"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    DetallesFacturas detallefactura = DetallesFacturasControlador.buscarId(id_detalle_factura);
    if (detallefactura != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        detallefactura = new DetallesFacturas();
        detallefactura.setId_detallefactura(0);
        detallefactura.setPrecio_total_factura(0);

        Facturas factura = new Facturas();
        factura.setId_factura(0);
        detallefactura.setFactura(factura);

        Actividades actividad = new Actividades();
        actividad.setId_actividad(0);
        actividad.setNombre_actividad("");
        actividad.setPrecio_actividad(0);
        actividad.setIva_actividad(0);
        //actividad.setPrecio_factura_actividad(0);
        detallefactura.setActividad(actividad);
        
        /*Ivas iva = new Ivas();
        iva.setPorcentaje_iva(0);
        actividad.setIva(iva);*/
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_detalle_factura", String.valueOf(detallefactura.getId_detallefactura()));
    obj.put("id_factura", String.valueOf(detallefactura.getFactura().getId_factura()));
    obj.put("id_actividad", String.valueOf(detallefactura.getActividad().getId_actividad()));
    obj.put("nombre_actividad", detallefactura.getActividad().getNombre_actividad());
    obj.put("precio_actividad", detallefactura.getActividad().getPrecio_actividad());
    //obj.put("precio_factura_actividad", detallefactura.getActividad().getPrecio_factura_actividad());
    obj.put("iva_actividad", detallefactura.getActividad().getIva_actividad());
    /*obj.put("nombre_iva", detallefactura.getActividad().getIva().getNombre_iva());
    obj.put("porcentaje_iva", detallefactura.getActividad().getIva().getPorcentaje_iva());
    obj.put("nombre_marca", String.valueOf(detallefactura.getActividad().getMarca().getNombre_marca()));*/
  obj.put("cantidad_factura", String.valueOf(detallefactura.getCantidad_factura()));
    obj.put("precio_total_factura", String.valueOf(detallefactura.getPrecio_total_factura()));

    out.print(obj);
    out.flush();
%>