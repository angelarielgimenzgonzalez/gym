<%@page import="modelos.Cajas"%>
<%@page import="modelos.Facturas"%>
<%@page import="controladores.DetallesCajasControlador"%>
<%@page import="modelos.DetallesCajas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_caja = Integer.parseInt(request.getParameter("id_caja"));
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    int id_detallecaja = Integer.parseInt(request.getParameter("id_detallecaja"));
    int importe_caja = Integer.parseInt(request.getParameter("importe_caja"));
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    Cajas caja = new Cajas();
    caja.setId_caja(id_caja);
    
    DetallesCajas detallecaja = new DetallesCajas();
    detallecaja.setId_detallecaja(id_detallecaja);
    Facturas factura= new Facturas();
    factura.setId_factura(id_factura);



    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>