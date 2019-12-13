
<%@page import="controladores.TiposPersonasControlador"%>
<%@page import="modelos.TiposPersonas"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_tipopersona = Integer.parseInt(request.getParameter("id_tipopersona"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    TiposPersonas tipopersona = new TiposPersonas();
    tipopersona.setId_tipopersona(id_tipopersona);
    TiposPersonasControlador.buscarId(tipopersona);
    if (tipopersona.getId_tipopersona() != 0) {
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
    obj.put("id_tipopersona", tipopersona.getId_tipopersona());
    System.out.println("id " + tipopersona.getId_tipopersona());
    obj.put("nombre_tipopersona", tipopersona.getNombre_tipopersona());
    System.out.println("nombre " + tipopersona.getNombre_tipopersona());

    out.print(obj);
    out.flush();
%>