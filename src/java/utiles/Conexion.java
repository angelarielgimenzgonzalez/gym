/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
public class Conexion {
    private static String driver = "org.postgresql.Driver";
    private static String servidor = "localhost";
    private static String puerto = "5432";
    private static String baseDato = "gym_ultra";
    private static String usuario = "postgres";
    //private static String clave = "root"; 
    private static String clave = "1";
    private static Connection conn;
    private static Statement st;

    public static boolean conectar() {
        boolean valor = false;
        try {
            Class.forName(driver);
            String url = "jdbc:postgresql://" + servidor + ":" + puerto + "/" + baseDato;
            System.out.println("Hola "+url);
            conn = DriverManager.getConnection(url, usuario, clave);
            st = conn.createStatement();
            valor = true;
            System.out.println("conexion exitosa");
            //jdbc:postgresql://:localhost:5432/grupohendy

        } catch (ClassNotFoundException ex) {
            System.err.println("Error" + ex);
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        return valor;

    }

    public static boolean cerrar() {
        boolean valor = false;
        try {
            st.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        }
        return valor;
    }

    public static Connection getConn() {
        return conn;

    }

    public static Statement getSt() {
        return st;
    }
}
    

