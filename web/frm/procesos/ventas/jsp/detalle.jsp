<%@page import="controladores.FacturasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="modelos.Facturas"%>
<%@page import="modelos.Agendamientos"%>
<%@page import="utiles.Utiles"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_agendamiento = Integer.parseInt(request.getParameter("id_agendamiento"));
   

    String tipo = "error";
    String mensaje = "Datos no modificados.";

  
    Agendamientos agendamiento = new Agendamientos();
    agendamiento.setId_agendamiento(id_agendamiento);
    //ajuste.setNumero_factura(numero_factura);
    //ajuste.setTimbrado_ajuste(timbrado_ajuste);

    //ajuste.setProveedor(proveedor);
    Facturas factura = new Facturas();
    factura.setAgendamiento(agendamiento);
    
    



   
        
            FacturasControlador.agregarDet(factura);
       

    

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_venta", String.valueOf(factura.getId_factura()));
    //obj.put("condicion_venta", String.valueOf(factura.getCondicion_venta()));
    System.out.println("numero factura" + factura.getId_factura());
    out.print(obj);
    out.flush();
%>