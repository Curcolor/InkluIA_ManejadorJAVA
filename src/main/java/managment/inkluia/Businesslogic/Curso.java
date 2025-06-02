package managment.inkluia.Businesslogic;

import managment.inkluia.Controllers.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int idCurso;
    private String titulo;
    private String descripcion;
    private String accesibilidad;
    private String urlContenido;

    // Constructores
    public Curso() {}

    public Curso(String titulo, String descripcion, String accesibilidad, String urlContenido) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.accesibilidad = accesibilidad;
        this.urlContenido = urlContenido;
    }

    // Getters y Setters
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getAccesibilidad() { return accesibilidad; }
    public void setAccesibilidad(String accesibilidad) { this.accesibilidad = accesibilidad; }

    public String getUrlContenido() { return urlContenido; }
    public void setUrlContenido(String urlContenido) { this.urlContenido = urlContenido; }

    // Métodos CRUD
    public boolean crear() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_CrearCurso(?,?,?,?)}")) {
            
            stmt.setString(1, this.titulo);
            stmt.setString(2, this.descripcion);
            stmt.setString(3, this.accesibilidad);
            stmt.setString(4, this.urlContenido);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Curso> obtenerTodos() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerCursos}");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("IdCurso"));
                curso.setTitulo(rs.getString("Titulo"));
                curso.setDescripcion(rs.getString("Descripcion"));
                curso.setAccesibilidad(rs.getString("Accesibilidad"));
                curso.setUrlContenido(rs.getString("URLContenido"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public boolean actualizar() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ActualizarCurso(?,?,?,?,?)}")) {
            
            stmt.setInt(1, this.idCurso);
            stmt.setString(2, this.titulo);
            stmt.setString(3, this.descripcion);
            stmt.setString(4, this.accesibilidad);
            stmt.setString(5, this.urlContenido);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminar(int idCurso) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_EliminarCurso(?)}")) {
            
            stmt.setInt(1, idCurso);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
