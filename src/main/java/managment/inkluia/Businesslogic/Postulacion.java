package managment.inkluia.Businesslogic;

import managment.inkluia.Controllers.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Postulacion {
    private int idPostulacion;
    private int idUsuario;
    private int idVacante;
    private Timestamp fechaPostulacion;
    private String estado;

    // Constructores
    public Postulacion() {}

    public Postulacion(int idUsuario, int idVacante) {
        this.idUsuario = idUsuario;
        this.idVacante = idVacante;
        this.estado = "Pendiente";
    }

    // Getters y Setters
    public int getIdPostulacion() { return idPostulacion; }
    public void setIdPostulacion(int idPostulacion) { this.idPostulacion = idPostulacion; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdVacante() { return idVacante; }
    public void setIdVacante(int idVacante) { this.idVacante = idVacante; }

    public Timestamp getFechaPostulacion() { return fechaPostulacion; }
    public void setFechaPostulacion(Timestamp fechaPostulacion) { this.fechaPostulacion = fechaPostulacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // Métodos CRUD
    public boolean crear() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_CrearPostulacion(?,?)}")) {
            
            stmt.setInt(1, this.idUsuario);
            stmt.setInt(2, this.idVacante);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Postulacion> obtenerTodas() {
        List<Postulacion> postulaciones = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerPostulaciones}");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Postulacion postulacion = new Postulacion();
                postulacion.setIdPostulacion(rs.getInt("IdPostulacion"));
                postulacion.setIdUsuario(rs.getInt("IdUsuario"));
                postulacion.setIdVacante(rs.getInt("IdVacante"));
                postulacion.setFechaPostulacion(rs.getTimestamp("FechaPostulacion"));
                postulacion.setEstado(rs.getString("Estado"));
                postulaciones.add(postulacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postulaciones;
    }

    public boolean actualizarEstado() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ActualizarEstadoPostulacion(?,?)}")) {
            
            stmt.setInt(1, this.idPostulacion);
            stmt.setString(2, this.estado);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminar(int idPostulacion) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_EliminarPostulacion(?)}")) {
            
            stmt.setInt(1, idPostulacion);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener postulaciones con detalle usando la vista
    public static List<Object[]> obtenerPostulacionesDetalle() {
        List<Object[]> postulaciones = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vw_PostulacionesDetalle")) {
            
            while (rs.next()) {
                Object[] postulacion = {
                    rs.getInt("IdPostulacion"),
                    rs.getString("Talento"),
                    rs.getString("Vacante"),
                    rs.getString("Estado"),
                    rs.getTimestamp("FechaPostulacion")
                };
                postulaciones.add(postulacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postulaciones;
    }
}
