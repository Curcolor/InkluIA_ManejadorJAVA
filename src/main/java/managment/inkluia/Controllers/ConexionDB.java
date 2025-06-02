package managment.inkluia.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {

    // Función que retornará un objeto Connection para utilizar una base de datos SQL SERVER
    public static Connection conectar() throws SQLException {
        
        // Obtención en variables locales para un uso mas entendible
        String url = Propiedades.Props().getProperty("db.url");
        String user = Propiedades.Props().getProperty("db.user");
        String password = Propiedades.Props().getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}
