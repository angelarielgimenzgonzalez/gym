<%@page import="modelos.TiposPersonas"%>
<%@page import="modelos.Generos"%>
<%@page import="modelos.EstadosCiviles"%>
<%@page import="modelos.Ciudades"%>
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

int id_ciudad=Integer.parseInt(request.getParameter("id_ciudad"));
String nombre_ciudad=request.getParameter("nombre_ciudad");

int id_estadocivil=Integer.parseInt(request.getParameter("id_estadocivil"));
String nombre_estadocivil=request.getParameter("nombre_estadocivil");

int id_genero=Integer.parseInt(request.getParameter("id_genero"));
String nombre_genero=request.getParameter("nombre_genero");

int id_tipopersona=Integer.parseInt(request.getParameter("id_tipopersona"));
String nombre_tipopersona=request.getParameter("nombre_tipopersona");

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

Ciudades ciudades = new Ciudades();
ciudades.setId_ciudad(id_ciudad);
ciudades.setNombre_ciudad(nombre_ciudad);
persona.setCiudades(ciudades);

EstadosCiviles estadosciviles = new EstadosCiviles();
estadosciviles.setId_estadocivil(id_estadocivil);
estadosciviles.setNombre_estadocivil(nombre_estadocivil);
persona.setEstadosciviles(estadosciviles);

Generos generos = new Generos();
generos.setId_genero(id_genero);
generos.setNombre_genero(nombre_genero);
persona.setGeneros(generos);

TiposPersonas tipospersonas = new TiposPersonas();
tipospersonas.setId_tipopersona(id_tipopersona);
tipospersonas.setNombre_tipopersona(nombre_tipopersona);
persona.setTipospersonas(tipospersonas);

if (PersonasControlador.modificarPersona(persona)){
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