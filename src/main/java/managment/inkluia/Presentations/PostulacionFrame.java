package managment.inkluia.Presentations;

import managment.inkluia.Businesslogic.Postulacion;
import managment.inkluia.Businesslogic.Usuario;
import managment.inkluia.Businesslogic.Vacante;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PostulacionFrame extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;

    public PostulacionFrame() {
        initComponents();
        inicializarTabla();
        cargarUsuarios();
        cargarVacantes();
        cargarDatos();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPostulaciones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbVacante = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnActualizarEstado = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        btnVerDetalle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Postulaciones");

        tablaPostulaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Talento", "Vacante", "Estado", "Fecha Postulación"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPostulaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPostulacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPostulaciones);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Gestión de Postulaciones");

        jLabel2.setText("Usuario (Talento):");

        jLabel3.setText("Vacante:");

        jLabel4.setText("Estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Pendiente", "Aceptado", "Rechazado", "En revisión"}));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnActualizarEstado.setText("Actualizar Estado");
        btnActualizarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEstadoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        txtId.setEditable(false);

        btnVerDetalle.setText("Ver Detalle (Vista)");
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(cmbUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(cmbVacante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(cmbEstado, 0, 350, Short.MAX_VALUE)
                            .addComponent(txtId))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarEstado)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerDetalle)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbVacante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizarEstado)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnVerDetalle))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    private void inicializarTabla() {
        modeloTabla = (DefaultTableModel) tablaPostulaciones.getModel();
    }

    private void cargarUsuarios() {
        cmbUsuario.removeAllItems();
        List<Usuario> usuarios = Usuario.obtenerTodos();
        for (Usuario usuario : usuarios) {
            if ("Talento".equals(usuario.getRol())) {
                cmbUsuario.addItem(usuario.getIdUsuario() + " - " + usuario.getNombreCompleto());
            }
        }
    }

    private void cargarVacantes() {
        cmbVacante.removeAllItems();
        List<Vacante> vacantes = Vacante.obtenerTodas();
        for (Vacante vacante : vacantes) {
            cmbVacante.addItem(vacante.getIdVacante() + " - " + vacante.getTitulo());
        }
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Postulacion> postulaciones = Postulacion.obtenerTodas();
        for (Postulacion postulacion : postulaciones) {
            Object[] fila = {
                postulacion.getIdPostulacion(),
                obtenerNombreUsuario(postulacion.getIdUsuario()),
                obtenerTituloVacante(postulacion.getIdVacante()),
                postulacion.getEstado(),
                postulacion.getFechaPostulacion()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void cargarDatosConVista() {
        modeloTabla.setRowCount(0);
        List<Object[]> postulaciones = Postulacion.obtenerPostulacionesDetalle();
        for (Object[] postulacion : postulaciones) {
            modeloTabla.addRow(postulacion);
        }
    }

    private String obtenerNombreUsuario(int idUsuario) {
        Usuario usuario = Usuario.obtener(idUsuario);
        return usuario != null ? usuario.getNombreCompleto() : "Usuario no encontrado";
    }

    private String obtenerTituloVacante(int idVacante) {
        List<Vacante> vacantes = Vacante.obtenerTodas();
        for (Vacante vacante : vacantes) {
            if (vacante.getIdVacante() == idVacante) {
                return vacante.getTitulo();
            }
        }
        return "Vacante no encontrada";
    }

    private int obtenerId(String texto) {
        return Integer.parseInt(texto.split(" - ")[0]);
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        if (validarCampos()) {
            Postulacion postulacion = new Postulacion(
                obtenerId(cmbUsuario.getSelectedItem().toString()),
                obtenerId(cmbVacante.getSelectedItem().toString())
            );
            
            if (postulacion.crear()) {
                JOptionPane.showMessageDialog(this, "Postulación creada exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear postulación", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnActualizarEstadoActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una postulación de la tabla");
            return;
        }
        
        Postulacion postulacion = new Postulacion();
        postulacion.setIdPostulacion(Integer.parseInt(txtId.getText()));
        postulacion.setEstado(cmbEstado.getSelectedItem().toString());
        
        if (postulacion.actualizarEstado()) {
            JOptionPane.showMessageDialog(this, "Estado actualizado exitosamente");
            cargarDatos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar estado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una postulación de la tabla");
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar esta postulación?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (Postulacion.eliminar(Integer.parseInt(txtId.getText()))) {
                JOptionPane.showMessageDialog(this, "Postulación eliminada exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar postulación", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {
        cargarDatosConVista();
    }

    private void tablaPostulacionesMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tablaPostulaciones.getSelectedRow();
        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            String usuario = modeloTabla.getValueAt(fila, 1).toString();
            String vacante = modeloTabla.getValueAt(fila, 2).toString();
            String estado = modeloTabla.getValueAt(fila, 3).toString();
            
            // Buscar en los combobox
            for (int i = 0; i < cmbUsuario.getItemCount(); i++) {
                if (cmbUsuario.getItemAt(i).contains(usuario)) {
                    cmbUsuario.setSelectedIndex(i);
                    break;
                }
            }
            
            for (int i = 0; i < cmbVacante.getItemCount(); i++) {
                if (cmbVacante.getItemAt(i).contains(vacante)) {
                    cmbVacante.setSelectedIndex(i);
                    break;
                }
            }
            
            cmbEstado.setSelectedItem(estado);
        }
    }

    private boolean validarCampos() {
        if (cmbUsuario.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario");
            return false;
        }
        if (cmbVacante.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una vacante");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtId.setText("");
        if (cmbUsuario.getItemCount() > 0) cmbUsuario.setSelectedIndex(0);
        if (cmbVacante.getItemCount() > 0) cmbVacante.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
    }

    // Variables declaration
    private javax.swing.JButton btnActualizarEstado;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JComboBox<String> cmbVacante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPostulaciones;
    private javax.swing.JTextField txtId;
}
