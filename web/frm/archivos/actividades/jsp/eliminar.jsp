<%@page import="controladores.ActividadesControlador"%>
<%@page import="modelos.Actividades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<% 
int id_actividad=Integer.parseInt(request.getParameter("id_actividad"));
String nombre_actividad=request.getParameter("nombre_actividad");

String tipo ="error";
String mensaje ="Datos no agregados.";

Actividades actividad = new Actividades();
actividad.setId_actividad(id_actividad);
actividad.setNombre_actividad(nombre_actividad);

if (ActividadesControlador.eliminar(actividad)){
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
