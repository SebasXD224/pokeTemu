package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConection {

    
    // Método estático que retorna la conexión abierta
    public static Connection getConnection(String dbPath) throws SQLException {
        
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }
}
