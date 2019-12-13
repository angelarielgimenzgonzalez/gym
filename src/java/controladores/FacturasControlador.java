 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import modelos.Facturas;
import modelos.Personas;
import utiles.Conexion;
import utiles.Utiles;

/**
 *
 * @author angel
 */
public class FacturasControlador {
     public static Facturas buscarId(int id) {
        Facturas factura = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select id_factura,fecha_factura,numero_factura,estado_factura,f.id_persona,nombre_persona,ci_persona from facturas f "
                        + "left join personas p on p.id_persona=f.id_persona "
                        //+ "left join establecimientos e on v.id_establecimiento=e.id_establecimiento "
                        //+ "left join puestos pu on v.id_puesto=pu.id_puesto "
                        + "where estado_factura !='COBRADO' and id_factura=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        factura = new Facturas();
                        factura.setId_factura(rs.getInt("id_factura"));
                        factura.setFecha_factura(rs.getDate("fecha_factura"));
                        factura.setNumero_factura(rs.getInt("numero_factura"));
                        factura.setEstado_factura(rs.getString("estado_factura"));
                        
                        
                        Personas persona = new Personas();
                        persona.setId_persona(rs.getInt("id_persona"));
                        persona.setNombre_persona(rs.getString("nombre_persona"));
                        persona.setCi_persona(rs.getString("ci_persona"));
                        factura.setPersona(persona);
                       
                    } else {

                        try {

                            String sqli = "SELECT COUNT (*) AS Ultimo, COUNT(numero_factura) FROM facturas";
                            try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                                int num = 0;
                                ResultSet rsi = psi.executeQuery();
                                if (rsi.next()) {
                                    factura = new Facturas();
                                    num = rsi.getInt("Ultimo");
                                    factura.setId_factura(0);
                                    factura.setNumero_factura(num + 1);

                                    factura.setEstado_factura("PENDIENTE");
                                    java.sql.Date fecha_factura = new java.sql.Date(new java.util.Date().getTime());
                                    factura.setFecha_factura(fecha_factura);

                                    Personas persona = new Personas();
                                    persona.setId_persona(0);
                                    persona.setNombre_persona("");
                                    persona.setCi_persona("");
                                    factura.setPersona(persona);


                                } else {
                                    num = 1;
                                }

                                psi.close();
                            }
                        } catch (SQLException ex) {
                            System.out.println("--> " + ex.getLocalizedMessage());
                        }
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return factura;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturas f "
                        + "left join personas p on p.id_persona=f.id_persona "
                        //+ "left join timbrados t on t.id_timbrado=v.id_timbrado "
                        //+ "left join establecimientos e on e.id_establecimiento=v.id_establecimiento "
                       // + "left join puestos pu on pu.id_puesto=v.id_puesto "
                        + "where upper(nombre_persona) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "and f.estado_factura !='COBRADO' order by id_factura "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_factura") + "</td>"
                                //+ "<td>" + rs.getString("id_establecimiento") + "</td>"
                                //+ "<td>" + rs.getString("nombre_establecimiento") + "</td>"
                                //+ "<td>" + rs.getString("ruc_establecimiento") + "</td>"
                               // + "<td>" + rs.getString("id_puesto") + "</td>"
                                //+ "<td>" + rs.getString("id_timbrado") + "</td>"
                                //+ "<td>" + rs.getString("numero_timbrado") + "</td>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("ci_persona") + "</td>"
                                + "<td>" + rs.getString("numero_factura") + "</td>"
                                + "<td>" + rs.getString("estado_factura") + "</td>"
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

    public static boolean agregar(Facturas factura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            Date v1 = factura.getFecha_factura();
            int v2 = factura.getPersona().getId_persona();
            //int v3 = factura.getTimbrado().getId_timbrado();
            int v3 = factura.getNumero_factura();
            String v4 = factura.getEstado_factura();
            int v5 = factura.getAgendamiento().getId_agendamiento();
            //int v6 = factura.getPuesto().getId_puesto();
            //int v7 = factura.getEstablecimiento().getId_establecimiento();

            String sql = "insert into facturas(fecha_factura ,id_persona, numero_factura, estado_factura, id_agendamiento) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "','" + v5 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura = keyset.getInt(1);
                    factura.setId_factura(id_factura);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }
    public static boolean agregarDet(Facturas factura) {
        boolean valor = false;
        if (Conexion.conectar()) {

            String cangrejo = "insert into facturasdetalles (cantidad_factura, id_actividad, id_factura,precio_total_factura)\n" +
"select cantidad, id_actividad, id_factura, precio_total \n" +
"from detallesagendamientos, facturas where detallesagendamientos.id_agendamiento = facturas.id_agendamiento\n" +
"and detallesagendamientos.id_agendamiento =  " + factura.getAgendamiento().getId_agendamiento()+ "";

            System.out.println("cangrejo" + cangrejo);

            try {
                Conexion.getSt().executeUpdate(cangrejo, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura = keyset.getInt(1);
                    factura.setId_factura(id_factura);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Facturas factura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturas set id_persona=?,"
                    //+ "id_timbrado=?,"
                    + "numero_factura=?,"
                    + "estado_factura=?,"
                    //+ "id_establecimiento=?,"
                    //+ "id_puesto=? "
                    + "where id_factura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, factura.getPersona().getId_persona());
               // ps.setInt(2, factura.getTimbrado().getId_timbrado());
                ps.setInt(2, factura.getNumero_factura());
                ps.setString(4, factura.getEstado_factura());
                //ps.setInt(4, factura.getEstablecimiento().getId_establecimiento());
                //ps.setInt(5, factura.getPuesto().getId_puesto());
                ps.setInt(3, factura.getId_factura());
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

    public static boolean eliminar(Facturas factura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from facturas where id_factura=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, factura.getId_factura());
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

    public static boolean modificarestado(Facturas factura) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturas set estado_factura='ANULADO'  "
                    + " where id_factura=" + factura.getId_factura();
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
    
    public static boolean modificarestadocobro(Facturas factura) throws SQLException {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update facturas set estado_factura='COBRADO'"
                    + " where id_factura=" + factura.getId_factura();
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
    
    public static Facturas buscarNumFactura(int id) throws SQLException {
        Facturas facturas = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "SELECT COUNT(*) AS Ultimo, COUNT(numero_factura) FROM facturas ";

                System.out.println("sss" + sqli);
                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {

                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        facturas = new Facturas();

                        facturas.setNumero_factura(rsi.getInt("Ultimo"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturas;
    }
 /*public static Facturas buscarTotalfactura(int id) throws SQLException {
        Facturas facturas = null;

        if (Conexion.conectar()) {
            try {
                String sqli = "select v.numero_factura,v.id_factura,SUM(total) as total from facturas v "
                        + "left join detallesfacturas dv on dv.id_factura=v.id_factura "
                        + "where v.id_factura=? "
                        + "group by v.id_factura";

                System.out.println("sss" + id);

                try (PreparedStatement psi = Conexion.getConn().prepareStatement(sqli)) {
                    psi.setInt(1, id);
                    ResultSet rsi = psi.executeQuery();
                    if (rsi.next()) {
                        facturas = new Facturas();
                        facturas.setId_factura(rsi.getInt("id_factura"));
                        facturas.setNumero_factura(rsi.getInt("numero_factura"));
                        facturas.setTotal(rsi.getInt("total"));

                    }
                    psi.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturas;
    }*/
    public static Facturas buscarId3(int id) {
        Facturas facturas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturasdetalles df "
                        + "left join facturas f on f.id_factura=df.id_factura "
                        + "left join actividades a on a.id_activadad=df.id_actividad "
                        + "where df.id_factura=" + id + " "
                        + "order by id_detallefactura";
                System.out.println("sqltotal " + sql + id);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    System.out.println("sqltotal " + ps);
                    ResultSet rs = ps.executeQuery();

                    //BigDecimal iva = BigDecimal.ZERO;
                    if (rs.next()) {
                        int cantidad = rs.getInt("cantidad_factura");
                        int totales = 0;
                    //int cant = Integer.parseInt(rs.getString("cantidad_producto_factura"));
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

                        // System.out.println("total"+total);
                        //iva
                        //BigDecimal subiva = rs.getBigDecimal("iva_producto");
                        //iva = iva.add(subiva);
                        //subtotaliva
                    }

                    String sql2 = "select v.id_factura, v.numero_factura, sum(precio_actividad * " + cantidad + ") +" + totales + "as TotalaPagar from facturasdetalles dv left join facturas v on v.id_factura=dv.id_factura left join actividades p on p.id_actividad=dv.id_actividad where dv.id_factura=" + id + " group by v.id_factura, v.numero_factura";
                    System.out.println("total " + sql2);
                    try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                        ResultSet rs1 = ps1.executeQuery();
                        if (rs1.next()) {
                            int total_pagar = rs1.getInt("TotalaPagar");
                            facturas = new Facturas();
                            facturas.setId_factura(id);
                            facturas.setNumero_factura(rs.getInt("numero_factura"));
                            facturas.setTotal(total_pagar);

                        }
                        System.out.println("Total a Pagar " + sql2);
                        System.out.println(rs1.getString("TotalaPagar"));
                        ps1.close();
                    } catch (SQLException ex) {
                        System.out.println("--> " + ex.getLocalizedMessage());

                    }
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturas;
    }

    public static Facturas buscartotal(int id) {
        Facturas facturas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturasdetalles dv "
                        + "left join facturas v on v.id_factura=dv.id_factura "
                        + "left join actividades p on p.id_actividad=dv.id_actividad "
                        + "where dv.id_factura=" + id + " "
                        + "order by id_detallefactura";
                System.out.println("sqltotal " + sql + id);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                    int factura = rs.getInt("numero_factura");
                    int totales = 0;
                    int totale = 0;
                    int total5 = 0;
                    int total10 = 0;
                    int iva = 0;
                    int totalexentas = 0;
                    int totaliva5 = 0;
                    int totaliva10 = 0;

                    int liqiva5 = 0;
                    int liqiva10 = 0;
                    
                    
                    while (rs.next()) {
                        iva = rs.getInt("iva_actividad");
                        int cantidad = rs.getInt("cantidad_factura");
                        //int factura = rs.getInt("precio_factura_producto");
                        if (iva == 0) {

                            totalexentas = factura * cantidad;
                            totaliva5 = 0;
                            totaliva10 = 0;
                            totale = totale + totalexentas;

                        } else if (iva == 5) {

                            totalexentas = 0;
                            totaliva10 = 0;

                            totaliva5 = factura * cantidad;
                            total5 = total5 + totaliva5;

                        } else {

                            totalexentas = 0;
                            totaliva5 = 0;
                            totaliva10 = factura * cantidad;
                            total10 = total10 + totaliva10;
                        }

                        liqiva5 = total5 / 21;
                        liqiva10 = total10 / 11;

                        totales = liqiva5 + liqiva10;
   
                    }
                       String sql2 = "select v.id_factura, v.numero_factura, sum(precio_actividad * cantidad_factura) as TotalaPagar from facturasdetalles dv left join facturas v on v.id_factura=dv.id_factura left join actividades p on p.id_actividad=dv.id_actividad where dv.id_factura=" + id + " group by v.id_factura";
                System.out.println("total " + sql2);
                try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sql2)) {

                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        int total_pagar = rs1.getInt("TotalaPagar");
                        //int factura = rs1.getInt("numero_factura");
                        facturas = new Facturas();
                        facturas.setId_factura(id);
                        facturas.setNumero_factura(factura);
                        facturas.setTotal(total_pagar);

                    }
                    System.out.println("Total a Pagar " + sql2);
                    System.out.println(rs1.getString("TotalaPagar"));
                    ps1.close();
                } catch (SQLException ex) {
                    System.out.println("--> " + ex.getLocalizedMessage());

                }
                    }
                    ps.close();
                }
                
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturas;
    }
    
    public static Facturas buscartotalbien(int id) {
        Facturas facturas = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from facturasdetalles dv "
                        + "left join facturas v on v.id_factura=dv.id_factura "
                        + "left join actividades p on p.id_actividad=dv.id_actividad "
                        + "where dv.id_factura=" + id + " "
                        + "order by id_detallefactura";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                    DecimalFormat df = new DecimalFormat("#,###");
                    //BigDecimal iva = BigDecimal.ZERO;
                    int totales = 0;
                    //int cant = Integer.parseInt(rs.getString("cantidad_producto_factura"));
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

                    }
                String sqlbien = "select v.id_factura, v.numero_factura, sum(precio_actividad * to_number(cantidad_factura,'999999999999D99')) + " + totales + " as TotalaPagar from facturasdetalles dv left join facturas v on v.id_factura=dv.id_factura left join actividades p on p.id_actividad=dv.id_actividad where dv.id_factura=" + id + " group by v.id_factura";
                System.out.println("total " + sqlbien);
                
                try (PreparedStatement ps1 = Conexion.getConn().prepareStatement(sqlbien)) {

                    ResultSet rs1 = ps1.executeQuery();
                    if (rs1.next()) {
                        int total_pagar = rs1.getInt("TotalaPagar");
                        //int factura = rs1.getInt("numero_factura");
                        facturas = new Facturas();
                        facturas.setId_factura(id);
                        facturas.setNumero_factura(rs.getInt("numero_factura"));
                        facturas.setTotal(total_pagar);

                    }
                    System.out.println("Total a Pagar " + sqlbien);
                    System.out.println(rs1.getString("TotalaPagar"));
                    ps1.close();
                } catch (SQLException ex) {
                    System.out.println("--> " + ex.getLocalizedMessage());

                }
                    }
                        
                    ps.close();
                    
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return facturas;
    }
    public static boolean agregarfac(Facturas factura) {
        boolean valor = false;
        if (Conexion.conectar()) {
            Date v1 = factura.getFecha_factura();
            int v2 = factura.getPersona().getId_persona();
            //int v3 = factura.getTimbrado().getId_timbrado();
            int v3 = factura.getNumero_factura();
            String v4 = factura.getEstado_factura();
            int v5 =2;
            //int v6 = factura.getPuesto().getId_puesto();
            //int v7 = factura.getEstablecimiento().getId_establecimiento();

            String sql = "insert into facturas(fecha_factura ,id_persona, numero_factura, estado_factura, id_agendamiento) "
                    + "values('" + v1 + "','" + v2 + "','" + v3 + "','" + v4 + "','" + v5 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_factura = keyset.getInt(1);
                    factura.setId_factura(id_factura);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }
}
