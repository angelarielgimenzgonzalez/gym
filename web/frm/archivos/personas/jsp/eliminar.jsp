<%@page import="controladores.PersonasControlador"%>
<%@page import="modelos.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<% 
int id_persona=Integer.parseInt(request.getParameter("id_persona"));
String nombre_persona=request.getParameter("nombre_persona");
String apellido_persona=request.getParameter("apellido_persona");
String direccion_persona=request.getParameter("direccion_persona");
String correo_persona=request.getParameter("correo_persona");
String ci_persona=request.getParameter("ci_persona");
String telefono_persona=request.getParameter("telefono_persona");
String edad_persona=request.getParameter("edad_persona");

String tipo ="error";
String mensaje ="Datos no agregados.";

Personas persona = new Personas();
persona.setId_persona(id_persona);
persona.setNombre_persona(nombre_persona);
persona.setApellido_persona(apellido_persona);
persona.setDireccion_persona(direccion_persona);
persona.setCorreo_persona(correo_persona);
persona.setCi_persona(ci_persona);
persona.setTelefono_persona(telefono_persona);
persona.setEdad_persona(edad_persona);

if (PersonasControlador.eliminar(persona)){
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
