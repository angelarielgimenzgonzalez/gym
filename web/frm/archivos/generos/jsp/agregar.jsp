<%@page import="controladores.GenerosControlador"%>
<%@page import="modelos.Generos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<% 
int id_genero=Integer.parseInt(request.getParameter("id_genero"));
String nombre_genero=request.getParameter("nombre_genero");

String tipo ="error";
String mensaje ="Datos no agregados.";

Generos genero = new Generos();
genero.setId_genero(id_genero);
genero.setNombre_genero(nombre_genero);

if (GenerosControlador.agregar(genero)){
    tipo="success";
    mensaje="Datos agregados.";
}

JSONObject obj =new JSONObject();
obj.put("tipo", tipo);
obj.put("mensaje", mensaje);
System.out.println("mensaje"+mensaje);
out.print(obj);
out.flush();
%>
