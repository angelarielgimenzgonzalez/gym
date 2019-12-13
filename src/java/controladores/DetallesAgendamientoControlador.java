/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Actividades;
import modelos.Agendamientos;
import modelos.DetalleAgendamientos;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Alumno
 */
public class DetallesAgendamientoControlador {

    public static DetalleAgendamientos buscarId(int id) {
        DetalleAgendamientos detalleagendamiento = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesagendamientos da "
                        + "left join agendamientos a on a.id_agendamiento=da.id_agendamiento "
                        + "left join actividades ac on ac.id_actividad=da.id_actividad"
                        + "where da.id_detalleagendamiento=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detalleagendamiento = new DetalleAgendamientos();
                        detalleagendamiento.setId_detalleagendamiento(rs.getInt("id_detalleagendamiento"));
                        //detalleagendamiento.setCantidad_articulopedido(rs.getInt("cantidad_articulopedido"));

                        Actividades actividad = new Actividades();
                        actividad.setId_actividad(rs.getInt("id_actividad"));
                        actividad.setNombre_actividad(rs.getString("nombre_actividad"));
                       actividad.setPrecio_actividad(rs.getInt("precio_actividad"));
                        detalleagendamiento.setActividad(actividad);

                        Agendamientos agendamiento = new Agendamientos();
                        agendamiento.setId_agendamiento(rs.getInt("id_agendamiento"));
                        detalleagendamiento.setAgendamiento(agendamiento);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }

        }
        Conexion.cerrar();
        return detalleagendamiento;

    }

    public static String buscarIdAgendamiento(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesagendamientos da "
                        + "left join agendamientos a  on a.id_agendamiento=da.id_agendamiento "
                        + "left join actividades ac on ac.id_actividad=da.id_actividad "
                        + "where da.id_agendamiento=" + id + " "
                        + "order by id_detalleagendamiento";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    //DecimalFormat df = new DecimalFormat( "#,###.00" );
                    String tabla = "";
                    //BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        // BigDecimal cantidad = rs.getBigDecimal("cantidad_articulopedido");
                        //total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detalleagendamiento") + "</td>"
                                + "<td>" + rs.getString("id_actividad") + "</td>"
                                + "<td>" + rs.getString("nombre_actividad") + "</td>"
                                + "<td>" + rs.getString("precio_actividad") + "</td>"
                                //+ "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detalleagendamiento") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-calendar'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    } else {
                        //tabla += "<tr><td colspan=3>TOTAL</td><td class='centrado'>"+df.format(total)+"</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallesagendamientos da "
                        + "left join agendamientos a on a.id_agendamiento=da.id_agendamiento "
                        + "left join actividades ac on ac.id_actividad=da.id_actividad "
                        + "where upper(a.nombre_actividad) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detalleagendamiento "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detalleagendamiento") + "</td>"
                                + "<td>" + rs.getString("id_agendamiento") + "</td>"
                                + "<td>" + rs.getString("id_actividad") + "</td>"
                                + "<td>" + rs.getString("nombre_actividad") + "</td>"
                                // + "<td>" + rs.getInt("cantidad_articulopedido") + "</td>" 
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(DetalleAgendamientos detalleagendamiento) {
        boolean valor = false;
        if (Conexion.conectar()) { 
            int c=1;
            String sql = "insert into detallesagendamientos "
                    + "(id_agendamiento,id_actividad,cantidad,precio_total) "
                    + "values (?,?,"+c+",?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleagendamiento.getAgendamiento().getId_agendamiento());
                ps.setInt(2, detalleagendamiento.getActividad().getId_actividad());
                ps.setInt(3, detalleagendamiento.getActividad().getPrecio_actividad());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(DetalleAgendamientos detalleAgendamientos) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallesagendamientos set "
                    + "id_agendamiento=?,"
                    + "id_actividad=?,"
                    + "where id_detalleagendamiento=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleAgendamientos.getAgendamiento().getId_agendamiento());
                ps.setInt(2, detalleAgendamientos.getActividad().getId_actividad());
                //ps.setInt(3, detallepedido.getCantidad_articulopedido());
                ps.setInt(4, detalleAgendamientos.getId_detalleagendamiento());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(DetalleAgendamientos detalleAgendamientos) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesagendamientos where id_detallepedido=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detalleAgendamientos.getId_detalleagendamiento());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarArticuloPedido(Agendamientos agendamiento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallesagendamientos where id_agendamiento=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, agendamiento.getId_agendamiento());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + agendamiento.getId_agendamiento());
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return false;
    }
}
