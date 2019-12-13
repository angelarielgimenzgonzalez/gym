

<%@page import="modelos.Usuarios"%>
<%@page import="controladores.UsuariosControlador"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<% 
int id_usuario=Integer.parseInt(request.getParameter("id_usuario"));
String nombre_usuario=request.getParameter("nombre_usuario");
String login_usuario=request.getParameter("login_usuario");
String password_usuario=request.getParameter("password_usuario");

String tipo ="error";
String mensaje ="Datos no agregados.";

Usuarios usuarios = new Usuarios();//crea un objeto
usuarios.setId_usuario(id_usuario);
usuarios.setNombre_usuario(nombre_usuario);
usuarios.setLogin_usuario(login_usuario);
usuarios.setPassword_usuario(password_usuario);

if (UsuariosControlador.agregar(usuarios)){
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
