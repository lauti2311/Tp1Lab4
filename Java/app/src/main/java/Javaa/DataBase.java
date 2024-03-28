package Javaa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//ACA HAY QUE CAMBIAR LOS DATOS DE LA BASE DE DATOS Bv

public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/tu_basededatos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}