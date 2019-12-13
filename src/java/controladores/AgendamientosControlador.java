/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Agendamientos;
import modelos.Personas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class AgendamientosControlador {
    public static boolean agregar(Agendamientos agendamiento) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
           String e="PENDIENTE";
            String sql = "insert into agendamientos(fecha_agendamiento, estado_agendamiento, id_persona) "
                    + "values('" + agendamiento.getFecha_agendamiento()+ "','" +e+ "','"               
                    + agendamiento.getPersonas().getId_persona()+ "')";
            
            /*String sql = "insert into ajustes(id_usuario,fecha_ajuste,motivo_ajuste) "
                    + "values('" + ajuste.getUsuario().getId_usuario() + "','"
                    + ajuste.getFecha_ajuste() + "','"
                    + ajuste.getMotivo_ajuste() + "')";*/
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_agendamiento = keyset.getInt(1);
                    agendamiento.setId_agendamiento(id_agendamiento);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(AgendamientosControlador.class.getName()).log(Level.SEVERE, null, ex.getLocalizedMessage());
                //System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }
public static boolean modificarestado(Agendamientos agendamiento) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update agendamientos set estado_agendamiento='FACTURADO'  "
                    + " where id_agendamiento=" + agendamiento.getId_agendamiento();
            try {

                Conexion.getSt().executeUpdate(sql);

                Conexion.getConn().setAutoCommit(false);
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
    
    public static Agendamientos buscarId(int id){
        Agendamientos agendamiento = new Agendamientos();
        if (Conexion.conectar()){
            String sql = "select * from agendamientos a, personas p where a.id_persona=p.id_persona and id_agendamiento='" +id+ "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    agendamiento.setId_agendamiento(rs.getInt("id_agendamiento"));
                    agendamiento.setFecha_agendamiento(rs.getDate("fecha_agendamiento"));
                    
                    Personas persona = new Personas();
                    persona.setId_persona(rs.getInt("id_persona"));
                    persona.setNombre_persona(rs.getString("nombre_persona"));
                    persona.setCi_persona(rs.getString("ci_persona"));
                   agendamiento.setPersonas(persona);
                           
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return agendamiento;
    }
     public static Agendamientos buscarIdfacturado(int id){
        Agendamientos agendamiento = new Agendamientos();
        if (Conexion.conectar()){
            String sql = "select * from agendamientos a, personas p where a.id_persona=p.id_persona and estado_agendamiento='PENDIENTE' and id_agendamiento='" +id+ "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    agendamiento.setId_agendamiento(rs.getInt("id_agendamiento"));
                    agendamiento.setFecha_agendamiento(rs.getDate("fecha_agendamiento"));
                    
                    Personas persona = new Personas();
                    persona.setId_persona(rs.getInt("id_persona"));
                    persona.setNombre_persona(rs.getString("nombre_persona"));
                    persona.setCi_persona(rs.getString("ci_persona"));
                   agendamiento.setPersonas(persona);
                           
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return agendamiento;
    }
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from agendamientos a "
                        + "left join personas p on a.id_persona = p.id_persona "
                        + "where upper(nombre_persona) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_agendamiento "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_agendamiento") + "</td>"
                                + "<td>" + rs.getString("fecha_agendamiento") + "</td>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"                     
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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
     public static boolean modificar(Agendamientos agendamiento) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update agendamientos set id_persona=? "
                    + "where id_agendamieno=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, agendamiento.getPersonas().getId_persona());
                ps.setInt(2, agendamiento.getId_agendamiento());
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
public static boolean eliminar(Agendamientos agendamiento ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from agendamientos where id_agendamiento=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, agendamiento.getId_agendamiento());
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
}
