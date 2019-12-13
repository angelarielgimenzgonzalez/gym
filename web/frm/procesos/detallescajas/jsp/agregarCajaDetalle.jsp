<%@page import="controladores.FacturasControlador"%>
<%@page import="controladores.CajasControlador"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.Cajas"%>
<%@page import="modelos.Facturas"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    int importe_caja = Integer.parseInt(request.getParameter("importe_caja"));
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    //int numero_factura = Integer.parseInt(request.getParameter("numero_factura"));
    int total = Integer.parseInt(request.getParameter("total"));
    int id_pago = Integer.parseInt(request.getParameter("id_pago"));
    String forma_pago = request.getParameter("forma_pago");
    int totald = importe_caja - total;
    String tipo = "error";
    
    String mensaje = "Datos no agregados.";
    
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setId_detallecaja(id_detallecaja);
    detallecaja.setVuelto(totald);
    detallecaja.setImporte_caja(importe_caja);
    //  detallecaja.setCantidad_cajafactura(cantidad_cajafactura);

    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);
    //facturafactura.setNumero_factura_factura(numero_factura_factura);
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    
    //Pagos pago = new Pagos();
   // pago.setId_pago(id_pago);
    //pago.setForma_pago(forma_pago);
    //detallecaja.setFactura(factura);
   // detallecaja.setCaja(caja);
    //detallecaja.setPago(pago);
        FacturasControlador.modificarestadocobro(factura);

    if (DetallesCajasControlador.agregar(detallecaja)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    factura = new Facturas();
    factura.setId_factura(id_factura);
    factura.setNumero_factura(0);
    //pago = new Pagos();
    //pago.setId_pago(id_pago);
   // pago.setForma_pago(forma_pago);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>