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
import modelos.Ciudades;
import modelos.EstadosCiviles;
import modelos.Generos;
import modelos.Personas;
import modelos.TiposPersonas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author Administrator
 */
public class PersonasControlador {

    public static boolean agregar (Personas persona){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into personas(nombre_persona,apellido_persona,direccion_persona,correo_persona,ci_persona,telefono_persona,edad_persona,id_ciudad,id_estadocivil,id_genero,id_tipopersona)"
                + "values('" + persona.getNombre_persona() + "','" + persona.getApellido_persona() + "','" + persona.getDireccion_persona() + "','" + persona.getCorreo_persona()+ "','" + persona.getCi_persona() + "','" + persona.getTelefono_persona() + "','" + persona.getEdad_persona() + "','" + persona.getCiudades().getId_ciudad() + "','" + persona.getEstadosciviles().getId_estadocivil() + "','" + persona.getGeneros().getId_genero() + "','" + persona.getTipospersonas().getId_tipopersona() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}

    public static Personas buscarId(Personas persona) {
        if (Conexion.conectar()) {
            String sql = "select * from personas p, ciudades c, estadosciviles ec, generos g, tipospersonas tp where p.id_ciudad=c.id_ciudad and p.id_estadocivil=ec.id_estadocivil and p.id_genero=g.id_genero and p.id_tipopersona=tp.id_tipopersona and p.id_persona='" + persona.getId_persona() + "'";
            System.out.println("sql " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    persona.setId_persona(rs.getInt("id_persona"));
                    persona.setNombre_persona(rs.getString("nombre_persona"));
                    persona.setApellido_persona(rs.getString("apellido_persona"));
                    persona.setDireccion_persona(rs.getString("direccion_persona"));
                    persona.setCorreo_persona(rs.getString("correo_persona"));
                    persona.setCi_persona(rs.getString("ci_persona"));
                    persona.setTelefono_persona(rs.getString("telefono_persona"));
                    persona.setEdad_persona(rs.getString("edad_persona"));

                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    persona.setCiudades(ciudad);

                    EstadosCiviles estadocivil = new EstadosCiviles();
                    estadocivil.setId_estadocivil(rs.getInt("id_estadocivil"));
                    estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                    persona.setEstadosciviles(estadocivil);

                    Generos genero = new Generos();
                    genero.setId_genero(rs.getInt("id_genero"));
                    genero.setNombre_genero(rs.getString("nombre_genero"));
                    persona.setGeneros(genero);
                    
                    TiposPersonas tipopersona = new TiposPersonas();
                    tipopersona.setId_tipopersona(rs.getInt("id_tipopersona"));
                    tipopersona.setNombre_tipopersona(rs.getString("nombre_tipopersona"));
                    persona.setTipospersonas(tipopersona);

                } else {
                    persona.setId_persona(0);
                    persona.setNombre_persona("");
                    persona.setApellido_persona("");
                    persona.setDireccion_persona("");
                    persona.setCorreo_persona("");
                    persona.setCi_persona("");
                    persona.setTelefono_persona("");
                    persona.setEdad_persona("");
                    //return null;
                    //return persona;
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt(0));
                    ciudad.setNombre_ciudad(rs.getString(""));
                    persona.setCiudades(ciudad);

                    EstadosCiviles estadocivil = new EstadosCiviles();
                    estadocivil.setId_estadocivil(rs.getInt(0));
                    estadocivil.setNombre_estadocivil(rs.getString(""));
                    persona.setEstadosciviles(estadocivil);

                    Generos genero = new Generos();
                    genero.setId_genero(rs.getInt(0));
                    genero.setNombre_genero(rs.getString(""));
                    persona.setGeneros(genero);
                    
                    TiposPersonas tipopersona = new TiposPersonas();
                    tipopersona.setId_tipopersona(rs.getInt(0));
                    tipopersona.setNombre_tipopersona(rs.getString(""));
                    persona.setTipospersonas(tipopersona);
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return persona;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from personas where upper(nombre_persona) like '%"
                        + nombre.toUpperCase() + "%'" + "order by id_persona offset " + offset + " limit "
                        + Utiles.REGISTROS_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("apellido_persona") + "</td>"
                                + "<td>" + rs.getString("direccion_persona") + "</td>"
                                + "<td>" + rs.getString("correo_persona") + "</td>"
                                + "<td>" + rs.getString("ci_persona") + "</td>"
                                + "<td>" + rs.getString("telefono_persona") + "</td>"
                                + "<td>" + rs.getString("edad_persona") + "</td>"
//                                + "<td>" + rs.getString("id_ciudad") + "</td>"
//                                + "<td>" + rs.getString("nombre_ciudad") + "</td>"
//                                + "<td>" + rs.getString("id_estadocivil") + "</td>"
//                                + "<td>" + rs.getString("nombre_estadocivil") + "</td>"
//                                + "<td>" + rs.getString("id_genero") + "</td>"
//                                + "<td>" + rs.getString("nombre_genero") + "</td>"
//                                + "<td>" + rs.getString("id_tipopersona") + "</td>"
//                                + "<td>" + rs.getString("nombre_tipopersona") + "</td>"
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

    public static boolean modificarPersona(Personas persona) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update personas set nombre_persona='" + persona.getNombre_persona() + "', apellido_persona='" + persona.getApellido_persona() + "', direccion_persona='" + persona.getDireccion_persona() + "', correo_persona='" + persona.getCorreo_persona() + "', ci_persona='" + persona.getCi_persona() + "', telefono_persona='" + persona.getTelefono_persona() + "', edad_persona='" + persona.getEdad_persona() + "', id_ciudad='" + persona.getCiudades().getId_ciudad() + "', id_estadocivil='" + persona.getEstadosciviles().getId_estadocivil() + "', id_genero='" + persona.getGeneros().getId_genero() + "', id_tipopersona='" + persona.getTipospersonas().getId_tipopersona() + "',"
                    + " where id_persona=" + persona.getId_persona();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Personas persona) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from personas where id_persona=" + persona.getId_persona();
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
