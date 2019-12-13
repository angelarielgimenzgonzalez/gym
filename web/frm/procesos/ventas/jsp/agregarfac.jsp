
<%@page import="modelos.Agendamientos"%>
<%@page import="controladores.FacturasControlador"%>
<%@page import="modelos.Facturas"%>
<%@page import="modelos.Personas"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_factura = Integer.parseInt(request.getParameter("id_factura"));
    String sfecha_factura = request.getParameter("fecha_factura");
    java.sql.Date fecha_factura = Utiles.stringToSqlDate(sfecha_factura);
   // int id_establecimiento = Integer.parseInt(request.getParameter("id_establecimiento"));
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    //int id_agendamiento = Integer.parseInt(request.getParameter("id_agendamiento"));
    //int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    //int id_puesto = Integer.parseInt(request.getParameter("id_puesto"));
    int numero_factura = Integer.parseInt(request.getParameter("numero_factura"));
    String estado_factura = request.getParameter("estado_factura");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Personas persona = new Personas();
    persona.setId_persona(id_persona);
    
    //Agendamientos agendamiento = new Agendamientos();
    //agendamiento.setId_agendamiento(id_agendamiento);
 
    
    Facturas factura = new Facturas();
    factura.setId_factura(id_factura);
    factura.setFecha_factura(fecha_factura);
    factura.setNumero_factura(numero_factura);
    factura.setEstado_factura(estado_factura);
    factura.setPersona(persona);
   // factura.setAgendamiento(agendamiento);
    
    
    /*if (persona.getId_persona() == 0) {
    persona.setId_persona(5);
    }
    
    if (timbrado.getId_timbrado() == 0){
        timbrado.setId_timbrado(1);
    }*/
    if (FacturasControlador.agregarfac(factura)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_factura", String.valueOf(factura.getId_factura()));
    out.print(obj);
    out.flush();
    
%>
