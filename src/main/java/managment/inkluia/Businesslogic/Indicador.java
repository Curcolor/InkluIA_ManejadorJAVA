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
    }    // Método para obtener indicadores con información de usuario usando la vista
    public static List<Object[]> obtenerIndicadoresUsuarios() {
        List<Object[]> indicadores = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vw_IndicadoresCompletos ORDER BY FechaRegistro DESC")) {
            
            while (rs.next()) {
                Object[] indicador = {
                    rs.getInt("IdIndicador"),
                    rs.getString("NombreCompleto"),
                    rs.getString("Rol"),
                    rs.getString("Tipo"),
                    rs.getBigDecimal("Valor"),
                    rs.getTimestamp("FechaRegistro")
                };
                indicadores.add(indicador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indicadores;
    }
}
