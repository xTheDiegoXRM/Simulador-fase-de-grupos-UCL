package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/champions";
    private static final String USER = "root";
    private static final String PASS = "Banette0354";
    private static Connection conexion;
    
    public static Connection conectar() {
        try{
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        return conexion;
        }
    }
}
