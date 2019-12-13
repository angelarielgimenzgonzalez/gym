/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelos.Usuarios;

import utiles.Conexion;
import utiles.Utiles;


/**
 *
 * @author Administrador
 */
public class UsuariosControlador {
    public static boolean agregar (Usuarios usuario){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into usuarios(nombre_usuario,login_usuario,password_usuario)"
                + "values('" + usuario.getNombre_usuario() + "','"+usuario.getLogin_usuario()+"','" + Utiles.md5(Utiles.quitarGuiones(usuario.getPassword_usuario())) + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Usuarios buscarId(Usuarios usuario){
        if (Conexion.conectar()){
            String sql = "select * from usuarios where id_usuario='" + usuario.getId_usuario() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                    usuario.setLogin_usuario(rs.getString("login_usuario"));
                    usuario.setPassword_usuario(rs.getString("password_usuario"));
                }else{
                    usuario.setId_usuario(0);
                    usuario.setNombre_usuario("");
                    usuario.setLogin_usuario("");
                    usuario.setPassword_usuario("");
                    //return null;
                    //return usuario;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return usuario;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from usuarios where upper(nombre_usuario) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_usuario offset " + offset + " limit " +
                        Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_usuario") + "</td>"
                                + "<td>" + rs.getString("nombre_usuario") + "</td>"
                                + "<td>" + rs.getString("login_usuario") + "</td>"
                                + "<td>" + rs.getString("password_usuario") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td> colspan=2>No exixten registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("ERROR: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("ERROR: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    public static boolean modificar(Usuarios usuario){
    boolean valor = false;
        if (Conexion.conectar()) {
       
             String sql= "update usuarios set nombre_usuario = '"+usuario.getNombre_usuario()+"',login_usuario = '"+usuario.getLogin_usuario()+"',password_usuario = '"+usuario.getPassword_usuario()+"'"
                    +" where id_usuario = "+ usuario.getId_usuario();
            System.out.println("sql"+sql);
            try {
           
            
            Conexion.getSt().executeUpdate(sql);
            valor= true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
     return valor;
    }
    
    
     public static boolean eliminar(Usuarios usuario){
    boolean valor = false;
        if (Conexion.conectar()) {
         String sql="delete from usuarios where id_usuario = "+usuario.getId_usuario();
            
            try {
           
            
            
            Conexion.getSt().executeUpdate(sql);
            valor= true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
     return valor;
    }
     public static Usuarios validarAcceso(Usuarios usuario, HttpServletRequest request) {
        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarios  "
                        + "where login_usuario='" + Utiles.quitarGuiones(usuario.getLogin_usuario())
                        + "' and password_usuario='" + Utiles.md5(Utiles.quitarGuiones(usuario.getPassword_usuario())) + "'";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    
                    System.out.println("---> " + sql);
                    if (rs.next()) {
                        HttpSession sesion = request.getSession(true);
                       
                        usuario = new Usuarios();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setLogin_usuario(rs.getString("login_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setPassword_usuario(rs.getString("password_usuario"));
                       
                        sesion.setAttribute("usuarioLogueado", usuario);
                    } else {
                        usuario = null;
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return usuario;
    }

}
