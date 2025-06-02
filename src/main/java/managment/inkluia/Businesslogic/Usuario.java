/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managment.inkluia.Businesslogic;

import managment.inkluia.Controllers.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int idUsuario;
    private String nombreCompleto;
    private String correo;
    private String contrasena;
    private String rol;
    private Integer idDiscapacidad;
    private Timestamp fechaRegistro;

    // Constructores
    public Usuario() {}

    public Usuario(String nombreCompleto, String correo, String contrasena, String rol, Integer idDiscapacidad) {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.idDiscapacidad = idDiscapacidad;
    }

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Integer getIdDiscapacidad() { return idDiscapacidad; }
    public void setIdDiscapacidad(Integer idDiscapacidad) { this.idDiscapacidad = idDiscapacidad; }

    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    // Métodos CRUD
    public boolean crear() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_CrearUsuario(?,?,?,?,?)}")) {
            
            stmt.setString(1, this.nombreCompleto);
            stmt.setString(2, this.correo);
            stmt.setString(3, this.contrasena);
            stmt.setString(4, this.rol);
            if (this.idDiscapacidad != null) {
                stmt.setInt(5, this.idDiscapacidad);
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Usuario obtener(int idUsuario) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerUsuario(?)}")) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setNombreCompleto(rs.getString("NombreCompleto"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setRol(rs.getString("Rol"));
                usuario.setIdDiscapacidad(rs.getObject("IdDiscapacidad", Integer.class));
                usuario.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizar() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ActualizarUsuario(?,?,?,?,?,?)}")) {
            
            stmt.setInt(1, this.idUsuario);
            stmt.setString(2, this.nombreCompleto);
            stmt.setString(3, this.correo);
            stmt.setString(4, this.contrasena);
            stmt.setString(5, this.rol);
            if (this.idDiscapacidad != null) {
                stmt.setInt(6, this.idDiscapacidad);
            } else {
                stmt.setNull(6, Types.INTEGER);
            }
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminar(int idUsuario) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_EliminarUsuario(?)}")) {
            
            stmt.setInt(1, idUsuario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }    public static List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vw_Usuarios ORDER BY NombreCompleto")) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setNombreCompleto(rs.getString("NombreCompleto"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setRol(rs.getString("Rol"));
                usuario.setIdDiscapacidad(rs.getObject("IdDiscapacidad", Integer.class));
                usuario.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static List<Usuario> buscarPorNombre(String nombre) {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM vw_Usuarios WHERE NombreCompleto LIKE ? ORDER BY NombreCompleto")) {
            
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setNombreCompleto(rs.getString("NombreCompleto"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setContrasena(rs.getString("Contrasena"));
                usuario.setRol(rs.getString("Rol"));
                usuario.setIdDiscapacidad(rs.getObject("IdDiscapacidad", Integer.class));
                usuario.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}
