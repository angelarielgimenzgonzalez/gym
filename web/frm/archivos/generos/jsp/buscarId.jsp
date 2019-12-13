
<%@page import="controladores.GenerosControlador"%>
<%@page import="modelos.Generos"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    int id_genero = Integer.parseInt(request.getParameter("id_genero"));

    String tipo = "error";
    String mensaje = "Datos no encontrados";
    String nuevo = "true";
    Generos genero = new Generos();
    genero.setId_genero(id_genero);
    GenerosControlador.buscarId(genero);
    if (genero.getId_genero() != 0) {
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
    obj.put("id_genero", genero.getId_genero());
    System.out.println("id " + genero.getId_genero());
    obj.put("nombre_genero", genero.getNombre_genero());
    System.out.println("nombre " + genero.getNombre_genero());

    out.print(obj);
    out.flush();
%>