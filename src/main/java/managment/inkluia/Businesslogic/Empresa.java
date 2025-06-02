package managment.inkluia.Businesslogic;

import managment.inkluia.Controllers.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private int idEmpresa;
    private String nombreEmpresa;
    private String nit;
    private String direccion;
    private String telefono;
    private String correoContacto;
    private String sector;
    private String descripcion;
    private Timestamp fechaRegistro;

    // Constructores
    public Empresa() {}

    public Empresa(String nombreEmpresa, String nit, String direccion, String telefono, 
                   String correoContacto, String sector, String descripcion) {
        this.nombreEmpresa = nombreEmpresa;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoContacto = correoContacto;
        this.sector = sector;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreoContacto() { return correoContacto; }
    public void setCorreoContacto(String correoContacto) { this.correoContacto = correoContacto; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    // Métodos CRUD
    public boolean crear() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_CrearEmpresa(?,?,?,?,?,?,?)}")) {
            
            stmt.setString(1, this.nombreEmpresa);
            stmt.setString(2, this.nit);
            stmt.setString(3, this.direccion);
            stmt.setString(4, this.telefono);
            stmt.setString(5, this.correoContacto);
            stmt.setString(6, this.sector);
            stmt.setString(7, this.descripcion);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Empresa obtener(int idEmpresa) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerEmpresa(?)}")) {
            
            stmt.setInt(1, idEmpresa);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt("IdEmpresa"));
                empresa.setNombreEmpresa(rs.getString("NombreEmpresa"));
                empresa.setNit(rs.getString("NIT"));
                empresa.setDireccion(rs.getString("Direccion"));
                empresa.setTelefono(rs.getString("Telefono"));
                empresa.setCorreoContacto(rs.getString("CorreoContacto"));
                empresa.setSector(rs.getString("Sector"));
                empresa.setDescripcion(rs.getString("Descripcion"));
                empresa.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
                return empresa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ActualizarEmpresa(?,?,?,?,?,?,?,?)}")) {
            
            stmt.setInt(1, this.idEmpresa);
            stmt.setString(2, this.nombreEmpresa);
            stmt.setString(3, this.nit);
            stmt.setString(4, this.direccion);
            stmt.setString(5, this.telefono);
            stmt.setString(6, this.correoContacto);
            stmt.setString(7, this.sector);
            stmt.setString(8, this.descripcion);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminar(int idEmpresa) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_EliminarEmpresa(?)}")) {
            
            stmt.setInt(1, idEmpresa);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }    public static List<Empresa> obtenerTodas() {
        List<Empresa> empresas = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vw_Empresas ORDER BY NombreEmpresa")) {
            
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt("IdEmpresa"));
                empresa.setNombreEmpresa(rs.getString("NombreEmpresa"));
                empresa.setNit(rs.getString("NIT"));
                empresa.setDireccion(rs.getString("Direccion"));
                empresa.setTelefono(rs.getString("Telefono"));
                empresa.setCorreoContacto(rs.getString("CorreoContacto"));
                empresa.setSector(rs.getString("Sector"));
                empresa.setDescripcion(rs.getString("Descripcion"));
                empresa.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        return empresas;
    }

    public static List<Empresa> buscarPorNombre(String nombre) {
        List<Empresa> empresas = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vw_Empresas WHERE NombreEmpresa LIKE ? ORDER BY NombreEmpresa")) {
            
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt("IdEmpresa"));
                empresa.setNombreEmpresa(rs.getString("NombreEmpresa"));
                empresa.setNit(rs.getString("NIT"));
                empresa.setDireccion(rs.getString("Direccion"));
                empresa.setTelefono(rs.getString("Telefono"));
                empresa.setCorreoContacto(rs.getString("CorreoContacto"));
                empresa.setSector(rs.getString("Sector"));
                empresa.setDescripcion(rs.getString("Descripcion"));
                empresa.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresas;
    }
}