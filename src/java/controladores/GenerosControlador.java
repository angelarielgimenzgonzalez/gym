/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelos.Generos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class GenerosControlador {
    public static boolean agregar (Generos genero){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into generos(nombre_genero)"
                + "values('" + genero.getNombre_genero() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Generos buscarId(Generos genero){
        if (Conexion.conectar()){
            String sql = "select * from generos where id_genero='" + genero.getId_genero() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    genero.setId_genero(rs.getInt("id_genero"));
                    genero.setNombre_genero(rs.getString("nombre_genero"));
                }else{
                    genero.setId_genero(0);
                    genero.setNombre_genero("");
                    //return null;
                    //return genero;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return genero;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from generos where upper(nombre_genero) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_genero offset " + offset + " limit " +
                        Utiles.REGISTROS_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_genero") + "</td>"
                                + "<td>" + rs.getString("nombre_genero") + "</td>"
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
     public static boolean modificarGenero(Generos genero) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update generos set nombre_genero='" + genero.getNombre_genero() + "'"
                    + " where id_genero=" + genero.getId_genero();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Generos genero){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from generos where id_genero=" + genero.getId_genero();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("ERROR:" + ex);
             }
         }
         return valor;
     }
}
