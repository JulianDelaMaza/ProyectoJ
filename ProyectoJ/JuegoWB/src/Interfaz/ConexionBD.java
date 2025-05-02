package Interfaz;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String bbdd = "jdbc:mysql://localhost/clientes";
    private static final String usuario = "root";
    private static final String clave = "12344321";

    public static Connection getConexion() {
        Connection conex = null;
        try {
            Class.forName(driver);
            conex = DriverManager.getConnection(bbdd, usuario, clave);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos.\n" + e.getMessage());
            e.printStackTrace();
        }

        return conex;
    }
}