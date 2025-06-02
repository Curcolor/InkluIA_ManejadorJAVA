package managment.inkluia.Businesslogic;

import managment.inkluia.Controllers.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoUsuario {
    private int idCurso;
    private int idUsuario;
    private Timestamp fechaInscripcion;

    // Constructores
    public CursoUsuario() {}

    public CursoUsuario(int idCurso, int idUsuario) {
        this.idCurso = idCurso;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public Timestamp getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(Timestamp fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

    // Métodos CRUD
    public boolean inscribir() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_InscribirCurso(?,?)}")) {
            
            stmt.setInt(1, this.idCurso);
            stmt.setInt(2, this.idUsuario);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<CursoUsuario> obtenerTodas() {
        List<CursoUsuario> inscripciones = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerInscripcionesCurso}");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                CursoUsuario inscripcion = new CursoUsuario();
                inscripcion.setIdCurso(rs.getInt("IdCurso"));
                inscripcion.setIdUsuario(rs.getInt("IdUsuario"));
                inscripcion.setFechaInscripcion(rs.getTimestamp("FechaInscripcion"));
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }

    public static boolean eliminarInscripcion(int idCurso, int idUsuario) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_EliminarInscripcionCurso(?,?)}")) {
            
            stmt.setInt(1, idCurso);
            stmt.setInt(2, idUsuario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener cursos inscritos con detalle usando la vista
    public static List<Object[]> obtenerCursosInscritos() {
        List<Object[]> inscripciones = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vw_CursosInscritos")) {
            
            while (rs.next()) {
                Object[] inscripcion = {
                    rs.getInt("IdCurso"),
                    rs.getString("Titulo"),
                    rs.getInt("IdUsuario"),
                    rs.getString("NombreCompleto"),
                    rs.getTimestamp("FechaInscripcion")
                };
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }
}
