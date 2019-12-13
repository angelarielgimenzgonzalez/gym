
<%@page import="utiles.Utiles"%>
<%@page import="modelos.Facturas"%>
<%@page import="modelos.Personas"%>
<%@page import="controladores.FacturasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
   //int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    //int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    //int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    String sfecha_factura = request.getParameter("fecha_factura");
    java.sql.Date fecha_factura = Utiles.stringToSqlDate(sfecha_factura);
    //int nro_factura_factura = Integer.parseInt(request.getParameter("nro_factura_factura"));

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    
    Personas persona = new Personas();
    persona.setId_persona(id_persona);
    

   
    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);
    factura.setFecha_factura(fecha_factura);
    //factura.setNro_factura_factura(nro_factura_factura);
    //factura.setTimbrado(timbrado);
    factura.setPersona(persona);
    //factura.setPuesto(puesto);
    //factura.setEstablecimiento(establecimiento);

    if (FacturasControlador.modificar(factura)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
