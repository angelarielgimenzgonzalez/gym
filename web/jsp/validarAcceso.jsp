
<%@page import="controladores.UsuariosControlador"%>
<%@page import="modelos.Usuarios"%>


<%@page import="org.json.simple.JSONObject"%>
<%
    String login_usuario = request.getParameter("login_usuario");
    String password_usuario = request.getParameter("password_usuario");
    String acceso = "false";
    //Usuarios usuario = new Usuarios(0, "", password_usuario, login_usuario);
    Usuarios usuario = new Usuarios();
    usuario.setLogin_usuario(login_usuario);
    usuario.setPassword_usuario(password_usuario);
    if (UsuariosControlador.validarAcceso(usuario, request) != null) {
        acceso = "true";
    }
    JSONObject obj = new JSONObject();
    obj.put("acceso", acceso);
    out.print(obj);
    out.flush();
%>
