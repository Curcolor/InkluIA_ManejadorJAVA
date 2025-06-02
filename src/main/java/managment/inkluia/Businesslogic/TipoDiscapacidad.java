package managment.inkluia.Businesslogic;

import managment.inkluia.Controllers.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoDiscapacidad {
    private int idDiscapacidad;
    private String nombre;
    private String descripcion;

    // Constructores
    public TipoDiscapacidad() {}

    public TipoDiscapacidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdDiscapacidad() { return idDiscapacidad; }
    public void setIdDiscapacidad(int idDiscapacidad) { this.idDiscapacidad = idDiscapacidad; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // Métodos CRUD
    public boolean crear() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_CrearTipoDiscapacidad(?,?)}")) {
            
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.descripcion);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static TipoDiscapacidad obtener(int idDiscapacidad) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTipoDiscapacidad(?)}")) {
            
            stmt.setInt(1, idDiscapacidad);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                TipoDiscapacidad tipo = new TipoDiscapacidad();
                tipo.setIdDiscapacidad(rs.getInt("IdDiscapacidad"));
                tipo.setNombre(rs.getString("Nombre"));
                tipo.setDescripcion(rs.getString("Descripcion"));
                return tipo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<TipoDiscapacidad> obtenerTodos() {
        List<TipoDiscapacidad> tipos = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTiposDiscapacidad}");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                TipoDiscapacidad tipo = new TipoDiscapacidad();
                tipo.setIdDiscapacidad(rs.getInt("IdDiscapacidad"));
                tipo.setNombre(rs.getString("Nombre"));
                tipo.setDescripcion(rs.getString("Descripcion"));
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
    }

    public boolean actualizar() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ActualizarTipoDiscapacidad(?,?,?)}")) {
            
            stmt.setInt(1, this.idDiscapacidad);
            stmt.setString(2, this.nombre);
            stmt.setString(3, this.descripcion);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminar(int idDiscapacidad) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_EliminarTipoDiscapacidad(?)}")) {
            
            stmt.setInt(1, idDiscapacidad);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<TipoDiscapacidad> buscarPorNombre(String nombre) {
        List<TipoDiscapacidad> tipos = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vw_TiposDiscapacidad WHERE Nombre LIKE ? ORDER BY Nombre")) {
            
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                TipoDiscapacidad tipo = new TipoDiscapacidad();
                tipo.setIdDiscapacidad(rs.getInt("IdDiscapacidad"));
                tipo.setNombre(rs.getString("Nombre"));
                tipo.setDescripcion(rs.getString("Descripcion"));
                tipos.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
    }
}
