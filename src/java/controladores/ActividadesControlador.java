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
import modelos.Actividades;
import modelos.Horarios;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class ActividadesControlador {

    public static boolean agregar(Actividades actividad) {
        boolean valor = false;
        if (Conexion.conectar()) {

            String sql = "insert into actividades(nombre_actividad,precio_actividad,turno_actividad,iva_actividad)"
                    + "values('" + actividad.getNombre_actividad() + "', '" + actividad.getPrecio_actividad() + "', '" + actividad.getTurno_actividad() + "', '" + actividad.getIva_actividad() + "')";

            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        return valor;

    }

    public static Actividades buscarId(Actividades actividad) {
        if (Conexion.conectar()) {
            String sql = "select * from actividades where id_actividad='" + actividad.getId_actividad() + "'";
            System.out.println("sql " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    actividad.setId_actividad(rs.getInt("id_actividad"));
                    actividad.setNombre_actividad(rs.getString("nombre_actividad"));
                    actividad.setPrecio_actividad(rs.getInt("precio_actividad"));
                    actividad.setTurno_actividad(rs.getString("turno_actividad"));
                    actividad.setIva_actividad(rs.getInt("iva_actividad"));
                    
                    /* Horarios horario = new Horarios();
                     horario.setId_horario(rs.getInt("id_horario"));
                     horario.setNombre_horario(rs.getString("nombre_horario"));*/
                   
                } else {
                    actividad.setId_actividad(0);
                    actividad.setNombre_actividad("");
                    actividad.setPrecio_actividad(0);
                    actividad.setTurno_actividad("");
                    actividad.setIva_actividad(0);
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return actividad;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from actividades where upper(nombre_actividad) like '%"
                        + nombre.toUpperCase() + "%'" + "order by id_actividad offset " + offset + " limit "
                        + Utiles.REGISTROS_PAGINA;
                System.out.println("Llega"+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_actividad") + "</td>"
                                + "<td>" + rs.getString("nombre_actividad") + "</td>"
                                + "<td>" + rs.getString("precio_actividad") + "</td>"
                                + "<td>" + rs.getString("turno_actividad") + "</td>"
                                + "<td>" + rs.getInt("iva_actividad") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
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

    public static boolean modificarActividad(Actividades actividad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update actividades set nombre_actividad='" + actividad.getNombre_actividad() + "', precio_actividad='" + actividad.getPrecio_actividad() + "', turno_actividad='" + actividad.getTurno_actividad() +"', iva_actividad='" + actividad.getIva_actividad() + "'"
                    + " where id_actividad=" + actividad.getId_actividad();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Actividades actividad) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from actividades where id_actividad=" + actividad.getId_actividad();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("ERROR:" + ex);
            }
        }
        return valor;
    }

    public static Actividades buscaractividades(Actividades actividad) {
        if (Conexion.conectar()) {
            String sql = "select * from actividades where nombre_actividad='" + actividad.getNombre_actividad() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    actividad.setId_actividad(0);
                } else {
                    actividad.setId_actividad(-1);
                }
            } catch (SQLException ex) {
                System.err.println("Error: " + ex);
            }

        }
        return actividad;
    }

}
