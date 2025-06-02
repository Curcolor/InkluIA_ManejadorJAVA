package managment.inkluia.Presentations;

import managment.inkluia.Businesslogic.Empresa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmpresaFrame extends JFrame {
    private JTable tablaEmpresas;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombreEmpresa, txtNIT, txtDireccion, txtTelefono, txtCorreo, txtSector;
    private JTextArea txtDescripcion;
    private JButton btnAgregar, btnEditar, btnEliminar, btnLimpiar, btnRefrescar, btnVerConVacantes, btnBuscar;
    private JTextField txtBusqueda;
    private int idSeleccionado = -1;

    public EmpresaFrame() {
        initComponents();
        cargarDatos();
    }

    private void initComponents() {
        setTitle("Gestión de Empresas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de la Empresa"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nombre de la empresa
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre Empresa:"), gbc);
        gbc.gridx = 1;
        txtNombreEmpresa = new JTextField(20);
        panelFormulario.add(txtNombreEmpresa, gbc);

        // NIT
        gbc.gridx = 2; gbc.gridy = 0;
        panelFormulario.add(new JLabel("NIT:"), gbc);
        gbc.gridx = 3;
        txtNIT = new JTextField(15);
        panelFormulario.add(txtNIT, gbc);

        // Dirección
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        panelFormulario.add(txtDireccion, gbc);

        // Teléfono
        gbc.gridx = 2; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 3;
        txtTelefono = new JTextField(15);
        panelFormulario.add(txtTelefono, gbc);

        // Correo de contacto
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Correo Contacto:"), gbc);
        gbc.gridx = 1;
        txtCorreo = new JTextField(20);
        panelFormulario.add(txtCorreo, gbc);

        // Sector
        gbc.gridx = 2; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Sector:"), gbc);
        gbc.gridx = 3;
        txtSector = new JTextField(15);
        panelFormulario.add(txtSector, gbc);

        // Descripción
        gbc.gridx = 0; gbc.gridy = 3;
        panelFormulario.add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.BOTH;
        txtDescripcion = new JTextArea(3, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        panelFormulario.add(scrollDesc, gbc);

        // Panel de botones
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.NONE;
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnRefrescar = new JButton("Refrescar");
        btnVerConVacantes = new JButton("Ver Con Vacantes");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnVerConVacantes);
          panelFormulario.add(panelBotones, gbc);

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
        panelBusqueda.add(new JLabel("Buscar por nombre:"));
        txtBusqueda = new JTextField(20);
        panelBusqueda.add(txtBusqueda);
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);

        // Tabla
        String[] columnas = {"ID", "Nombre Empresa", "NIT", "Sector", "Teléfono", "Correo", "Fecha Registro"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEmpresas = new JTable(modeloTabla);
        tablaEmpresas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tablaEmpresas);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        // Listener para selección de tabla
        tablaEmpresas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaEmpresas.getSelectedRow();
                if (selectedRow >= 0) {
                    cargarDatosFormulario(selectedRow);
                }
            }
        });        // Agregar eventos a botones
        btnAgregar.addActionListener(e -> agregarEmpresa());
        btnEditar.addActionListener(e -> editarEmpresa());
        btnEliminar.addActionListener(e -> eliminarEmpresa());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnRefrescar.addActionListener(e -> cargarDatos());
        btnVerConVacantes.addActionListener(e -> cargarDatosConVacantes());
        btnBuscar.addActionListener(e -> buscarEmpresa());

        // Agregar componentes al panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelBusqueda, BorderLayout.CENTER);
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);

        // Configuración de ventana
        pack();
        setLocationRelativeTo(null);
        
        // Estado inicial de botones
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void cargarDatos() {
        try {
            List<Empresa> empresas = Empresa.obtenerTodas();
            modeloTabla.setRowCount(0);
            
            // Actualizar columnas para vista normal
            String[] columnas = {"ID", "Nombre Empresa", "NIT", "Sector", "Teléfono", "Correo", "Fecha Registro"};
            modeloTabla.setColumnIdentifiers(columnas);
            
            for (Empresa empresa : empresas) {
                Object[] fila = {
                    empresa.getIdEmpresa(),
                    empresa.getNombreEmpresa(),
                    empresa.getNit(),
                    empresa.getSector(),
                    empresa.getTelefono(),
                    empresa.getCorreoContacto(),
                    empresa.getFechaRegistro()
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar las empresas: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }    }

    private void cargarDatosConVacantes() {
        try {
            List<Empresa> empresas = Empresa.obtenerTodas();
            modeloTabla.setRowCount(0);
            
            // Usar columnas estándar
            String[] columnas = {"ID", "Nombre Empresa", "NIT", "Dirección", "Teléfono", "Email", "Sector", "Descripción"};
            modeloTabla.setColumnIdentifiers(columnas);
            
            for (Empresa empresa : empresas) {
                Object[] fila = {
                    empresa.getIdEmpresa(),
                    empresa.getNombreEmpresa(),
                    empresa.getNit(),
                    empresa.getDireccion(),
                    empresa.getTelefono(),
                    empresa.getCorreoContacto(),
                    empresa.getSector(),
                    empresa.getDescripcion()
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar las empresas: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosFormulario(int fila) {
        idSeleccionado = (Integer) modeloTabla.getValueAt(fila, 0);
        
        // Obtener la empresa completa para mostrar todos los datos
        Empresa empresa = Empresa.obtener(idSeleccionado);
        if (empresa != null) {
            txtNombreEmpresa.setText(empresa.getNombreEmpresa());
            txtNIT.setText(empresa.getNit());
            txtDireccion.setText(empresa.getDireccion() != null ? empresa.getDireccion() : "");
            txtTelefono.setText(empresa.getTelefono() != null ? empresa.getTelefono() : "");
            txtCorreo.setText(empresa.getCorreoContacto() != null ? empresa.getCorreoContacto() : "");
            txtSector.setText(empresa.getSector() != null ? empresa.getSector() : "");
            txtDescripcion.setText(empresa.getDescripcion() != null ? empresa.getDescripcion() : "");
        }
        
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }

    private void agregarEmpresa() {
        if (!validarCampos()) {
            return;
        }

        try {
            Empresa empresa = new Empresa(
                txtNombreEmpresa.getText().trim(),
                txtNIT.getText().trim(),
                txtDireccion.getText().trim(),
                txtTelefono.getText().trim(),
                txtCorreo.getText().trim(),
                txtSector.getText().trim(),
                txtDescripcion.getText().trim()
            );
            
            if (empresa.crear()) {
                JOptionPane.showMessageDialog(this, "Empresa agregada exitosamente");
                limpiarFormulario();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar la empresa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar la empresa: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarEmpresa() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una empresa para editar");
            return;
        }

        if (!validarCampos()) {
            return;
        }

        try {
            Empresa empresa = new Empresa(
                txtNombreEmpresa.getText().trim(),
                txtNIT.getText().trim(),
                txtDireccion.getText().trim(),
                txtTelefono.getText().trim(),
                txtCorreo.getText().trim(),
                txtSector.getText().trim(),
                txtDescripcion.getText().trim()
            );
            empresa.setIdEmpresa(idSeleccionado);
            
            if (empresa.actualizar()) {
                JOptionPane.showMessageDialog(this, "Empresa actualizada exitosamente");
                limpiarFormulario();
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la empresa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al actualizar la empresa: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarEmpresa() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una empresa para eliminar");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea eliminar esta empresa?\nEsto también eliminará todas sus vacantes asociadas.",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                if (Empresa.eliminar(idSeleccionado)) {
                    JOptionPane.showMessageDialog(this, "Empresa eliminada exitosamente");
                    limpiarFormulario();
                    cargarDatos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la empresa", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar la empresa: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarFormulario() {
        txtNombreEmpresa.setText("");
        txtNIT.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtSector.setText("");
        txtDescripcion.setText("");
        idSeleccionado = -1;
        
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        
        tablaEmpresas.clearSelection();
    }

    private boolean validarCampos() {
        if (txtNombreEmpresa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la empresa es obligatorio");
            txtNombreEmpresa.requestFocus();
            return false;
        }

        if (txtNIT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El NIT es obligatorio");
            txtNIT.requestFocus();
            return false;
        }        return true;
    }

    private void buscarEmpresa() {
        String nombreBusqueda = txtBusqueda.getText().trim();
        
        if (nombreBusqueda.isEmpty()) {
            cargarDatos(); // Si no hay texto de búsqueda, mostrar todas las empresas
            return;
        }

        try {
            List<Empresa> empresasEncontradas = Empresa.buscarPorNombre(nombreBusqueda);
            modeloTabla.setRowCount(0);
            
            // Usar columnas estándar
            String[] columnas = {"ID", "Nombre Empresa", "NIT", "Sector", "Teléfono", "Correo", "Fecha Registro"};
            modeloTabla.setColumnIdentifiers(columnas);
            
            for (Empresa empresa : empresasEncontradas) {
                Object[] fila = {
                    empresa.getIdEmpresa(),
                    empresa.getNombreEmpresa(),
                    empresa.getNit(),
                    empresa.getSector(),
                    empresa.getTelefono(),
                    empresa.getCorreoContacto(),
                    empresa.getFechaRegistro()
                };
                modeloTabla.addRow(fila);
            }
            
            if (empresasEncontradas.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontraron empresas con el nombre: " + nombreBusqueda, 
                    "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar empresas: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
