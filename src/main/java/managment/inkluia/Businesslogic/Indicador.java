package managment.inkluia.Businesslogic;

import managment.inkluia.Controllers.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Indicador {
    private int idIndicador;
    private int idUsuario;
    private String tipo;
    private BigDecimal valor;
    private Timestamp fechaRegistro;

    // Constructores
    public Indicador() {}

    public Indicador(int idUsuario, String tipo, BigDecimal valor) {
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.valor = valor;
    }

    // Getters y Setters
    public int getIdIndicador() { return idIndicador; }
    public void setIdIndicador(int idIndicador) { this.idIndicador = idIndicador; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    // Métodos CRUD
    public boolean crear() {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_CrearIndicador(?,?,?)}")) {
            
            stmt.setInt(1, this.idUsuario);
            stmt.setString(2, this.tipo);
            stmt.setBigDecimal(3, this.valor);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Indicador obtener(int idIndicador) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerIndicador(?)}")) {
            
            stmt.setInt(1, idIndicador);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Indicador indicador = new Indicador();
                indicador.setIdIndicador(rs.getInt("IdIndicador"));
                indicador.setIdUsuario(rs.getInt("IdUsuario"));
                indicador.setTipo(rs.getString("Tipo"));
                indicador.setValor(rs.getBigDecimal("Valor"));
                indicador.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
                return indicador;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Indicador> obtenerTodos() {
        List<Indicador> indicadores = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_ObtenerIndicadores}");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Indicador indicador = new Indicador();
                indicador.setIdIndicador(rs.getInt("IdIndicador"));
                indicador.setIdUsuario(rs.getInt("IdUsuario"));
                indicador.setTipo(rs.getString("Tipo"));
                indicador.setValor(rs.getBigDecimal("Valor"));
                indicador.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
                indicadores.add(indicador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indicadores;
    }

    public static boolean eliminar(int idIndicador) {
        try (Connection conn = ConexionDB.conectar();
             CallableStatement stmt = conn.prepareCall("{call sp_EliminarIndicador(?)}")) {
            
            stmt.setInt(1, idIndicador);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<Indicador> buscarPorTipo(String tipo) {
        List<Indicador> indicadores = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Indicadores WHERE Tipo LIKE ? ORDER BY FechaRegistro DESC")) {
            
            stmt.setString(1, "%" + tipo + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Indicador indicador = new Indicador();
                indicador.setIdIndicador(rs.getInt("IdIndicador"));
                indicador.setIdUsuario(rs.getInt("IdUsuario"));
                indicador.setTipo(rs.getString("Tipo"));
                indicador.setValor(rs.getBigDecimal("Valor"));
                indicador.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
                indicadores.add(indicador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indicadores;
    }
}
