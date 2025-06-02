package managment.inkluia.Presentations;

import managment.inkluia.Businesslogic.Indicador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class IndicadorFrame extends JFrame {
    private JTable tablaIndicadores;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre, txtTipo, txtValor;
    private JButton btnAgregar, btnEditar, btnEliminar, btnLimpiar, btnRefrescar;
    private Indicador indicadorBL;
    private int idSeleccionado = -1;

    public IndicadorFrame() {
        indicadorBL = new Indicador();
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setTitle("Gestión de Indicadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Indicador"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        panelFormulario.add(txtNombre, gbc);

        // Tipo
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        txtTipo = new JTextField(20);
        panelFormulario.add(txtTipo, gbc);

        // Valor
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Valor:"), gbc);
        gbc.gridx = 1;
        txtValor = new JTextField(20);
        panelFormulario.add(txtValor, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnRefrescar = new JButton("Refrescar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRefrescar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panelFormulario.add(panelBotones, gbc);        // Tabla
        String[] columnas = {"ID", "Usuario", "Tipo", "Valor", "Fecha Registro"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaIndicadores = new JTable(modeloTabla);
        tablaIndicadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tablaIndicadores);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        // Listener para selección de tabla
        tablaIndicadores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaIndicadores.getSelectedRow();
                if (selectedRow >= 0) {
                    cargarDatosFormulario(selectedRow);
                }
            }
        });

        // Agregar eventos a botones
        btnAgregar.addActionListener(e -> agregarIndicador());
        btnEditar.addActionListener(e -> editarIndicador());
        btnEliminar.addActionListener(e -> eliminarIndicador());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnRefrescar.addActionListener(e -> cargarDatos());

        // Agregar componentes al panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);

        // Configuración de ventana
        pack();
        setLocationRelativeTo(null);
        
        // Estado inicial de botones
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }    private void cargarDatos() {
        try {
            List<Object[]> indicadores = Indicador.obtenerIndicadoresUsuarios();
            modeloTabla.setRowCount(0);
            
            for (Object[] indicador : indicadores) {
                modeloTabla.addRow(indicador);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar los indicadores: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    private void cargarDatosFormulario(int fila) {
        idSeleccionado = (Integer) modeloTabla.getValueAt(fila, 0);
        txtTipo.setText((String) modeloTabla.getValueAt(fila, 2));
        txtValor.setText(modeloTabla.getValueAt(fila, 3).toString());
        txtNombre.setText(""); // No hay campo nombre en los datos reales
        
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }private void agregarIndicador() {
        if (!validarCampos()) {
            return;
        }

        try {
            Indicador indicador = new Indicador(
                1, // ID del usuario actual (hardcoded para este ejemplo)
                txtTipo.getText().trim(),
                new java.math.BigDecimal(txtValor.getText().trim())
            );
            
            if (indicador.crear()) {
                JOptionPane.showMessageDialog(this, "Indicador agregado exitosamente");
                limpiarFormulario();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar el indicador", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar el indicador: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    private void editarIndicador() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un indicador para editar");
            return;
        }

        JOptionPane.showMessageDialog(this, "La funcionalidad de editar no está implementada en la lógica de negocio");
    }    private void eliminarIndicador() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un indicador para eliminar");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea eliminar este indicador?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                if (Indicador.eliminar(idSeleccionado)) {
                    JOptionPane.showMessageDialog(this, "Indicador eliminado exitosamente");
                    limpiarFormulario();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el indicador", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar el indicador: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtTipo.setText("");
        txtValor.setText("");
        idSeleccionado = -1;
        
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        tablaIndicadores.clearSelection();
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
            txtNombre.requestFocus();
            return false;
        }

        if (txtTipo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El tipo es obligatorio");
            txtTipo.requestFocus();
            return false;
        }

        if (txtValor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El valor es obligatorio");
            txtValor.requestFocus();
            return false;
        }

        try {
            Double.parseDouble(txtValor.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor debe ser un número válido");
            txtValor.requestFocus();
            return false;
        }

        return true;
    }
}
