/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Horarios;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author ALUMNO
 */
public class HorariosControlador {
   public static boolean agregar (Horarios horario){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into horarios(nombre_horario,horain_horario,horafin_horario)"
                + "values('" + horario.getNombre_horario() + "','" + horario.getHorain_horario() + "','" + horario.getHorafin_horario() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Horarios buscarId(Horarios horario){
        if (Conexion.conectar()){
            String sql = "select * from horarios where id_horario='" + horario.getId_horario() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    horario.setId_horario(rs.getInt("id_horario"));
                    horario.setNombre_horario(rs.getString("nombre_horario"));
                    horario.setHorain_horario(rs.getString("horain_horario"));
                    horario.setHorafin_horario(rs.getString("horafin_horario"));
                }else{
                    horario.setId_horario(0);
                    horario.setNombre_horario("");
                    horario.setHorain_horario("");
                    horario.setHorafin_horario("");
                    //return null;
                    //return horario;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return horario;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from horarios where upper(nombre_horario) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_horario offset " + offset + " limit " +
                        Utiles.REGISTROS_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_horario") + "</td>"
                                + "<td>" + rs.getString("nombre_horario") + "</td>"
                                + "<td>" + rs.getString("horain_horario") + "</td>"
                                + "<td>" + rs.getString("horafin_horario") + "</td>"
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
     public static boolean modificarHorario(Horarios horario) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update horarios set nombre_horario='" + horario.getNombre_horario() + "', horain_horario='" + horario.getHorain_horario() + "',horafin_horario='" + horario.getHorafin_horario()+ "'"
                    + " where id_horario=" + horario.getId_horario();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Horarios horario){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from horarios where id_horario=" + horario.getId_horario();
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
