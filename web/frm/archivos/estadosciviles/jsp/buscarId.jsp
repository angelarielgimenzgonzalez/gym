
<%@page import="controladores.EstadosCivilesControlador"%>
<%@page import="modelos.EstadosCiviles"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    EstadosCiviles estadocivil = new EstadosCiviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    EstadosCivilesControlador.buscarId(estadocivil);
    if (estadocivil.getId_estadocivil() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados";
        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
     System.out.println("tipo " + tipo);
    obj.put("mensaje", mensaje);
     System.out.println("mensaje " + mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_estadocivil", estadocivil.getId_estadocivil());
    System.out.println("id " + estadocivil.getId_estadocivil());
    obj.put("nombre_estadocivil", estadocivil.getNombre_estadocivil());
    System.out.println("nombre " + estadocivil.getNombre_estadocivil());

    out.print(obj);
    out.flush();
%>