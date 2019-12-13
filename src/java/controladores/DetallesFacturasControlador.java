/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Actividades;
import modelos.DetallesFacturas;
import modelos.Facturas;
import utiles.Conexion;
import utiles.Utiles;
import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
//import modelos.Ivas;

public class DetallesFacturasControlador {

    public static DetallesFacturas buscarId(int id) {
        DetallesFacturas detallefactura = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturasdetalles df "
                        + "left join facturas f on f.id_factura=df.id_factura "
                        + "left join actividades p on p.id_actividad=df.id_actividad "
                     + "where df.id_detallefactura=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        detallefactura = new DetallesFacturas();
                        detallefactura.setId_detallefactura(rs.getInt("id_detallefactura"));
                        detallefactura.setPrecio_total_factura(rs.getInt("precio_total_factura"));
                        detallefactura.setCantidad_factura(rs.getInt("cantidad_factura"));
                        

                        Actividades actividad = new Actividades();
                        actividad.setId_actividad(rs.getInt("id_actividad"));
                        actividad.setNombre_actividad(rs.getString("nombre_actividad"));
                        actividad.setPrecio_actividad(rs.getInt("precio_actividad"));
                        //actividad.setIva_actividad(rs.getInt("iva_actividad"));

                        detallefactura.setActividad(actividad);

                        Facturas factura = new Facturas();
                        factura.setId_factura(rs.getInt("id_factura"));
                        detallefactura.setFactura(factura);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallefactura;
    }

    public static String buscarIdFactura(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturasdetalles df "
                        + "left join facturas f on f.id_factura=df.id_factura "
                        + "left join actividades a on a.id_actividad=df.id_actividad "
                        + "where df.id_factura=" + id + " "
                        + "order by id_detallefactura";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    BigDecimal total1 = BigDecimal.ZERO;
                    BigDecimal stotal = BigDecimal.ZERO;
                    //BigDecimal iva = BigDecimal.ZERO;
                    int totales = 0;
                    //int cant = Integer.parseInt(rs.getString("cantidad_actividad_factura"));
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int totalf = 0;
                    int totalt = 0;
                    //BigDecimal total10 = BigDecimal.ZERO;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;
                    
                    
                    while (rs.next()) {
                        iva = rs.getInt("iva_actividad");
                        int cantidad1 = rs.getInt("cantidad_factura");
                        int factura = rs.getInt("precio_actividad");
                        if (iva == 0) {

                            totalexentas = factura * cantidad1;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = factura * cantidad1;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = factura * cantidad1;
                            total10 = total10 + totaliva10;
                        }

                        liqiva5 = total5 / 21;
                        liqiva10 = total10 / 11;

                        totales = liqiva5 + liqiva10;

                        totalf = total5 + totale + total10;
                        totalt = total5 + totale + total10 + totales;
                        
                        BigDecimal cantidad = rs.getBigDecimal("cantidad_factura");
                        
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        BigDecimal subtotal = rs.getBigDecimal("precio_actividad");
                        total1 = total1.add(subtotal);
                        //para el subtotal
                        BigDecimal subtotalp = subtotal.multiply(cantidad);
                        stotal = stotal.add(subtotalp);
                        //iva
                        //BigDecimal subiva = rs.getBigDecimal("iva_actividad");
                        //iva = iva.add(subiva);
                        //subtotaliva
                        

                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallefactura") + "</td>"
                                + "<td>" + rs.getString("id_actividad") + "</td>"
                                + "<td>" + rs.getString("nombre_actividad") + "</td>"
                                //+ "<td>" + rs.getString("codigo_actividad") + "</td>"
                                + "<td>" + rs.getString("precio_actividad") + "</td>"
                                + "<td class='centrado'>" + rs.getString("cantidad_factura") + "</td>"
                               // + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>" + df.format(subtotalp) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallefactura") + ")'"
                                + " type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen registros ...</td></tr>";
                    } else {
                        tabla += "<tr><td colspan=4>TOTAL</td><td class='centrado'>" + df.format(total) +"</td><td colspan=2 ><b><P ALIGN=left>" + df.format(totalf) + " Gs." + "</b></td>";
                        //tabla += "<tr><td colspan=5><b>SUB-TOTAL:</b></td><td class='left'><b>" + df.format(totale) + "</b></td><td><b>" + df.format(total5) + "</b></td><th>" + df.format(total10) + "</td></tr>";
                        /*tabla += "<tr><td colspan=6><b>LIQUIDACION DEL IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(total) + " </td> </tr>";
                        tabla += "<tr><td colspan=7 ><b><P ALIGN=right>TOTAL A PAGAR:</b></td><td class='left'><b>" + df.format(totalf) + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";*/
                        tabla += "<tr><td colspan=9><b>Liquidacion de IVA:</b>&nbsp;&nbsp;&nbsp; <b>(5%):</b> " + "" + df.format(liqiva5) + "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <b>(10%):</b>&nbsp;" + df.format(liqiva10) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b> TOTAL I.V.A:</b> &nbsp; " + df.format(totales) + " </td> </tr>";

                        //tabla += "<tr><td colspan=9 ><b><P ALIGN=left>Total Grabada: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";
                      tabla += "<tr><td colspan=9 ><b><P ALIGN=left>Total a Pagar: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + df.format(totalf) + " Gs." + "</b></td></tr>";  //+ df.format(totald) +"</td></tr>";
                        
                        //String sql2 = "select sum(" + cant + " * precio_factura_actividad) + " + total + " as TotalaPagar from facturasdetalles dv left join facturas v on v.id_factura=dv.id_factura left join actividades p on p.id_actividad=dv.id_actividad where dv.id_factura="+ id + "";
                        //System.out.println("total " + sql2);
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
                String sql = "select * from facturasdetalles df "
                        + "left join facturas f on f.id_factura=df.id_factura "
                        + "left join actividades a on a.id_actividad=df.id_actividad "
                        + "where upper(a.nombre_actividad) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallefactura "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallefactura") + "</td>"
                                + "<td>" + rs.getString("id_factura") + "</td>"
                                + "<td>" + rs.getString("id_actividad") + "</td>"
                                + "<td>" + rs.getString("nombre_actividad") + "</td>"
                                //+ "<td>" + rs.getString("codigo_actividad") + "</td>"
                                + "<td>" + rs.getString("precio_actividad") + "</td>"
                                + "<td>" + rs.getString("iva_actividad") + "</td>"
                                //+ "<td>" + rs.getString("porcentaje_iva") + "</td>"
                                //+ "<td>" + rs.getString("nombre_iva") + "</td>"

                                + "<td>" + rs.getInt("precio_total_factura") + "</td>"
                                + "<td>" + rs.getInt("cantidad_factura") + "</td>"
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

    public static boolean agregar(DetallesFacturas detallefactura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into facturasdetalles "
                    + "(id_factura, id_actividad, precio_total_factura, cantidad_factura) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallefactura.getFactura().getId_factura());
                ps.setInt(2, detallefactura.getActividad().getId_actividad());
                //ps.setInt(3, detallefactura.getCantidad_actividad_factura());
                ps.setInt(3, detallefactura.getPrecio_total_factura());
                ps.setInt(4, detallefactura.getCantidad_factura());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
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

    public static boolean modificar(DetallesFacturas detallefactura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturasdetalles set "
                    + "id_factura=?,"
                    + "id_actividad=?,"
                    //+ "cantidad_actividad_factura=?,"
                    + "precio_total_factura=? "
                    + "where id_detallefactura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallefactura.getFactura().getId_factura());
                ps.setInt(2, detallefactura.getActividad().getId_actividad());
                //ps.setInt(3, detallefactura.getCantidad_actividad_factura());
                ps.setInt(3, detallefactura.getPrecio_total_factura());
                ps.setInt(4, detallefactura.getId_detallefactura());
                ps.executeUpdate();
                ps.close();
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

    public static boolean eliminar(DetallesFacturas detallefactura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from facturasdetalles where id_detallefactura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallefactura.getId_detallefactura());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
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

    public static boolean eliminarActividadFactura(Facturas factura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from facturasdetalles where id_factura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, factura.getId_factura());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + factura.getId_factura());
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
