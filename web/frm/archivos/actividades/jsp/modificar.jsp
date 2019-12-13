<%@page import="controladores.ActividadesControlador"%>
<%@page import="modelos.Actividades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<% 
int id_actividad=Integer.parseInt(request.getParameter("id_actividad"));
String nombre_actividad=request.getParameter("nombre_actividad");
String precio_actividad=request.getParameter("precio_actividad");
String turno_actividad=request.getParameter("turno_actividad");
int iva_actividad=Integer.parseInt(request.getParameter("iva_actividad"));

String tipo ="error";
String mensaje ="Datos no agregados.";

Actividades actividad = new Actividades();
actividad.setId_actividad(id_actividad);
actividad.setNombre_actividad(nombre_actividad);
actividad.setPrecio_actividad(precio_actividad);
actividad.setTurno_actividad(turno_actividad);
actividad.setIva_actividad(iva_actividad);
actividad.setId_actividad(id_actividad);

if (ActividadesControlador.modificarActividad(actividad)){
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