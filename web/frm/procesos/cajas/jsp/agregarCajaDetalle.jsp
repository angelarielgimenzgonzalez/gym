
<%@page import="controladores.FacturasControlador"%>
<%@page import="modelos.Facturas"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.Cajas"%>

<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    int importe_caja = Integer.parseInt(request.getParameter("importe_caja"));
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int numero_factura = Integer.parseInt(request.getParameter("numero_factura"));
    int total = Integer.parseInt(request.getParameter("total"));
    
    int totald = importe_caja - total;
    String tipo = "error";
    
    String mensaje = "Datos no agregados.";
    
    
    //  detallecaja.setCantidad_cajaventa(cantidad_cajaventa);

    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);
    factura.setNumero_factura(numero_factura);
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    
   /* Pagos pago = new Pagos();
    pago.setId_pago(id_pago);
    pago.setForma_pago(forma_pago);
    detallecaja.setVenta(venta);
    detallecaja.setCaja(caja);
    detallecaja.setPago(pago);*/
   DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setId_detallecaja(id_detallecaja);
    detallecaja.setVuelto(total);
    detallecaja.setImporte_caja(importe_caja);
    detallecaja.setCaja(caja);
        FacturasControlador.modificarestadocobro(factura);

    if (DetallesCajasControlador.agregar(detallecaja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    factura = new Facturas();
    factura.setId_factura(id_factura);
    factura.setNumero_factura(0);
 /*   pago = new Pagos();
    pago.setId_pago(id_pago);
    pago.setForma_pago(forma_pago);*/
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>