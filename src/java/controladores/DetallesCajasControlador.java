package controladores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import modelos.Cajas;
import modelos.DetallesCajas;
//import modelos.Pagos;
import modelos.Facturas;
import utiles.Conexion;
import utiles.Utiles;
/**
 *
 * @author Administrator
 */
public class DetallesCajasControlador {

    public static DetallesCajas buscarId(int id) throws SQLException {
        DetallesCajas detallecaja = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dc "
                        + "left join cajas c on c.id_caja=dc.id_caja "
                        + "left join facturas f on f.id_factura=dc.id_factura "
                       // + "left join pagos pa on pa.id_pago=dc.id_pago "
                        + "where dc.id_detallecaja=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    System.out.println("detallecj--> " + sql+id);
                    if (rs.next()) {
                        
                        detallecaja = new DetallesCajas();
                        detallecaja.setId_detallecaja(rs.getInt("id_detallecaja"));
                        detallecaja.setImporte_caja(rs.getInt("importe_caja"));
                        //detallecaja.setVuelto(rs.getInt("vuelto"));

                        Facturas factura = new Facturas();
                        factura.setId_factura(rs.getInt("id_factura"));
                        factura.setNumero_factura(rs.getInt("numero_factura"));
                        detallecaja.setFactura(factura);

                        Cajas caja = new Cajas();
                        caja.setId_caja(rs.getInt("id_caja"));
                        detallecaja.setCaja(caja);

                        //Pagos pago = new Pagos();
                        //pago.setId_pago(rs.getInt("id_pago"));
                        //pago.setForma_pago(rs.getString("forma_pago"));
                        //detallecaja.setPago(pago);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return detallecaja;
    }

    public static String buscarIdCaja(int id) throws SQLException {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dc "
                        + "left join cajas c on c.id_caja=dc.id_caja "
                        + "left join facturas f on f.id_factura=dc.id_factura "
                        //+ "left join pagos p on p.id_pago=dc.id_pago "
                        + "where dc.id_caja=" + id + " "
                        + "order by id_detallecaja";
                System.out.println("dca--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    DecimalFormat df = new DecimalFormat("#,###");
                    String tabla = "";
                    BigDecimal total = BigDecimal.ZERO;
                    while (rs.next()) {
                        BigDecimal cantidad = rs.getBigDecimal("importe_caja");
                        total = total.add(cantidad);
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                              //  + "<td>" + rs.getString("id_detallecaja") + "</td>"
                                + "<td>" + rs.getString("id_factura") + "</td>"
                                + "<td>" + rs.getString("numero_factura") + "</td>"
                                + "<td>" + rs.getString("id_pago") + "</td>"
                                //+ "<td>" + rs.getString("forma_pago") + "</td>"
                               // + "<td>" + rs.getString("total") + "</td>"
                                + "<td class='centrado'>" + df.format(cantidad) + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getString("id_detallecaja") + ")'"
                                + " type='button' class='btn bordo bordo1 btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
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

    public static String buscarNombre(String nombre, int pagina) throws SQLException {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dc "
                        + "left join cajas c on c.id_caja=dc.id_caja "
                        + "left join facturas f on f.id_factura=dc.id_factura "
                        ///+ "left join pagos pa on pa.id_pago=dc.id_pago "
                        + "where upper(f.nombre_factura) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_detallecaja "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_detallecaja") + "</td>"
                                + "<td>" + rs.getString("id_caja") + "</td>"
                                + "<td>" + rs.getString("id_pago") + "</td>"
                                + "<td>" + rs.getString("id_factura") + "</td>"
                                + "<td>" + rs.getString("numero_factura") + "</td>"
                                + "<td>" + rs.getInt("total") + "</td>"
                                + "<td>" + rs.getInt("iva_factura") + "</td>"
                                + "<td>" + rs.getInt("importe_caja") + "</td>"
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

    public static boolean agregar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into detallescajas "
                    + "(id_factura,importe,id_caja,vuelto) "
                    + "values (?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                
                ps.setInt(1, detallecaja.getFactura().getId_factura());
                ps.setInt(2, detallecaja.getImporte_caja());
                ps.setInt(3, detallecaja.getCaja().getId_caja());
                ps.setInt(4, detallecaja.getVuelto());
                //ps.setInt(5, detallecaja.getVuelto());
                System.out.println("sql"+sql);

                ps.executeUpdate();
                System.out.println("agregarcajadetalle "+sql);
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

    public static boolean modificar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update detallescajas set "
                    + "id_caja=?,"
                    + "id_factura=?,"
                    + "cantidad_facturacaja=? "
                    + "where id_detallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getCaja().getId_caja());
                ps.setInt(2, detallecaja.getFactura().getId_factura());
                //  ps.setInt(3, detallecaja.getCantidad_facturacaja());
                ps.setInt(4, detallecaja.getId_detallecaja());
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

    public static boolean eliminar(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where id_detallecaja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, detallecaja.getId_detallecaja());
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

    public static boolean eliminarc(DetallesCajas detallecaja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from detallescajas dc "
                        + "left join cajas c on c.id_caja=dc.id_caja "
                        + "left join facturas f on f.id_factura=dc.id_factura_factura "
                        + " where c.id_caja= " + detallecaja.getCaja().getId_caja();
                System.out.println("detalle " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {

                        String sqli = "update stocks set cantidad_stock= cantidad_stock - " + rs.getInt("cantidad_facturacaja") + " where id_factura=" + rs.getInt("id_factura") + "";

                        System.out.println(" descontar stock" + sqli);
                        try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                            psi.executeUpdate();
                            psi.close();
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

                    ps.close();
                    valor = true;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminarFacturaCaja(Cajas caja) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from detallescajas where id_caja=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, caja.getId_caja());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> " + caja.getId_caja());
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
