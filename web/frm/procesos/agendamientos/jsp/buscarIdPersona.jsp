
<%@page import="controladores.PersonasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Personas persona = new Personas();
    persona.setId_persona(id_persona);
    PersonasControlador.buscarId(persona);
    if (persona.getId_persona() != 0) {
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
    obj.put("id_persona", persona.getId_persona());
    obj.put("nombre_persona", persona.getNombre_persona());
    obj.put("apellido_persona", persona.getApellido_persona());
   obj.put("direccion_persona", persona.getDireccion_persona());
    //obj.put("correo_persona", persona.getCorreo_persona());
    //obj.put("ci_persona", persona.getCi_persona());
    //obj.put("telefono_persona", persona.getTelefono_persona());
    //obj.put("edad_persona", persona.getEdad_persona());
    //obj.put("id_ciudad", persona.getCiudades().getId_ciudad());
    //obj.put("nombre_ciudad", persona.getCiudades().getNombre_ciudad());
    //obj.put("id_estadocivil", persona.getEstadosciviles().getId_estadocivil() );
    //obj.put("nombre_estadocivil", persona.getEstadosciviles().getNombre_estadocivil());
    //obj.put("id_genero", persona.getGeneros().getId_genero() );
    //obj.put("nombre_genero", persona.getGeneros().getNombre_genero());
    //obj.put("id_tipopersona", persona.getTipospersonas().getId_tipopersona() );
   // obj.put("nombre_tipopersona", persona.getTipospersonas().getNombre_tipopersona());

    out.print(obj);
    out.flush();
%>