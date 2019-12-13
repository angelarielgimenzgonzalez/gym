



<%@page import="org.json.simple.JSONObject"%>
<%@page import="controladores.EstadosCivilesControlador"%>
<%@page import="modelos.EstadosCiviles"%>
<%
    String nombre_estadocivil= request.getParameter("nombre_estadocivil");

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    EstadosCiviles estadocivil = new EstadosCiviles();
    estadocivil.setNombre_estadocivil(nombre_estadocivil);

    
    EstadosCivilesControlador.buscarestadocivil(estadocivil);
    if (estadocivil.getId_estadocivil() == 0) {
        tipo = "success";
        mensaje = "Datos ya existentes";
        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
     System.out.println("tipo " + tipo);
    obj.put("mensaje", mensaje);
     System.out.println("mensaje " + mensaje);
    obj.put("nuevo", nuevo);
    obj.put("nombre_estadocivil", estadocivil.getNombre_estadocivil());
    

    out.print(obj);
    out.flush();
%>