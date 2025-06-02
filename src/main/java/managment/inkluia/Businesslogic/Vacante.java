package managment.inkluia.Businesslogic;

import managment.inkluia.Controllers.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Vacante {
    private int idVacante;
    private int idEmpresa;
    private String titulo;
    private String descripcion;
    private String requisitos;
    private Timestamp fechaPublicacion;

    // Constructores
    public Vacante() {}

    public Vacante(int idEmpresa, String titulo, String descripcion, String requisitos) {
        this.idEmpresa = idEmpresa;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.requisitos = requisitos;
    }

    // Getters y Setters
    public int getIdVacante() { return idVacante; }
    public void setIdVacante(int idVacante) { this.idVacante = idVacante; }

    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getRequisitos() { return requisitos; }
    public void setRequisitos(String requisitos) { this.requisitos = requisitos; }

    public Timestamp getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(Timestamp fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    // Métodos CRUD
    public boolean crear() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_CrearVacante(?,?,?,?)}")) {
            
            stmt.setInt(1, this.idEmpresa);
            stmt.setString(2, this.titulo);
            stmt.setString(3, this.descripcion);
            stmt.setString(4, this.requisitos);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Vacante> obtenerTodas() {
        List<Vacante> vacantes = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerVacantes}");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Vacante vacante = new Vacante();
                vacante.setIdVacante(rs.getInt("IdVacante"));
                vacante.setIdEmpresa(rs.getInt("IdEmpresa"));
                vacante.setTitulo(rs.getString("Titulo"));
                vacante.setDescripcion(rs.getString("Descripcion"));
                vacante.setRequisitos(rs.getString("Requisitos"));
                vacante.setFechaPublicacion(rs.getTimestamp("FechaPublicacion"));
                vacantes.add(vacante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacantes;
    }

    public boolean actualizar() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ActualizarVacante(?,?,?,?)}")) {
            
            stmt.setInt(1, this.idVacante);
            stmt.setString(2, this.titulo);
            stmt.setString(3, this.descripcion);
            stmt.setString(4, this.requisitos);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminar(int idVacante) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_EliminarVacante(?)}")) {
            
            stmt.setInt(1, idVacante);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener vacantes con información de empresa usando la vista
    public static List<Object[]> obtenerVacantesConEmpresas() {
        List<Object[]> vacantes = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vw_VacantesConEmpresas")) {
            
            while (rs.next()) {
                Object[] vacante = {
                    rs.getInt("IdVacante"),
                    rs.getString("Titulo"),
                    rs.getString("Descripcion"),
                    rs.getTimestamp("FechaPublicacion"),
                    rs.getString("Empresa")
                };
                vacantes.add(vacante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacantes;
    }
}
