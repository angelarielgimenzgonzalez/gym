
<%@page import="controladores.ActividadesControlador"%>
<%@page import="modelos.Actividades"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_actividad = Integer.parseInt(request.getParameter("id_actividad"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Actividades actividad = new Actividades();
    actividad.setId_actividad(id_actividad);
    ActividadesControlador.buscarId(actividad);
    if (actividad.getId_actividad() != 0) {
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
    obj.put("id_actividad", actividad.getId_actividad());
    System.out.println("id " + actividad.getId_actividad());
    obj.put("nombre_actividad", actividad.getNombre_actividad());
    obj.put("precio_actividad", actividad.getPrecio_actividad());
    obj.put("turno_actividad", actividad.getTurno_actividad());
    obj.put("iva_actividad", actividad.getIva_actividad());
    out.print(obj);
    out.flush();
%>