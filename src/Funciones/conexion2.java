package Funciones;

import java.sql.*;

public class conexion2 {

    // Propiedades

    private static Connection conn = null;
    private String driver;
    private String url;
    private String usuario;
    private String password;

    // Constructor
    private conexion2() {

        String url = "jdbc:mysql://127.0.0.1/gomac_v_0707_dev";
        String driver = "com.mysql.jdbc.Driver";
        String usuario = "root";
        String password = "";
       // String password = "1234543219";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    } // Fin constructor

    // Métodos
    public static Connection getConnection() {

        if (conn == null) {
            new conexion2();
        }

        return conn;
    } // Fin getConnection
}
