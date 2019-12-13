
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Facturas"%>
<%@page import="modelos.Personas"%>
<%@page import="controladores.FacturasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
  
    String tipo = "error";
    String mensaje = "Datos no modificados.";

   
   
    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);
   

    if (FacturasControlador.modificarestado(factura)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
