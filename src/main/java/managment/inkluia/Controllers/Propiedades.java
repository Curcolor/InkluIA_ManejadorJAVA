package managment.inkluia.Controllers;

import java.io.InputStream;
import java.util.Properties;

public class Propiedades {
    public static Properties Props(){
        Properties props = new Properties();
        
        // Manejo de errores de properties
        try (InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró el archivo db.properties en other resources");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar db.properties: " + e.getMessage(), e);
        }    
        return props;
    }
}
