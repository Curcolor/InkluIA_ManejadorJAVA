package managment.inkluia.Presentations;

import managment.inkluia.Businesslogic.TipoDiscapacidad;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TipoDiscapacidadFrame extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;

    public TipoDiscapacidadFrame() {
        initComponents();
        inicializarTabla();
        cargarDatos();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTipos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Tipos de Discapacidad");

        tablaTipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nombre", "Descripción"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaTipos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTiposMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTipos);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Gestión de Tipos de Discapacidad");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
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
                            .addComponent(txtNombre)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(txtId))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)))
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
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    private void inicializarTabla() {
        modeloTabla = (DefaultTableModel) tablaTipos.getModel();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<TipoDiscapacidad> tipos = TipoDiscapacidad.obtenerTodos();
        for (TipoDiscapacidad tipo : tipos) {
            Object[] fila = {
                tipo.getIdDiscapacidad(),
                tipo.getNombre(),
                tipo.getDescripcion()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        if (validarCampos()) {
            TipoDiscapacidad tipo = new TipoDiscapacidad(
                txtNombre.getText(),
                txtDescripcion.getText()
            );
            
            if (tipo.crear()) {
                JOptionPane.showMessageDialog(this, "Tipo de discapacidad creado exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear tipo de discapacidad", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de la tabla");
            return;
        }
        
        if (validarCampos()) {
            TipoDiscapacidad tipo = new TipoDiscapacidad();
            tipo.setIdDiscapacidad(Integer.parseInt(txtId.getText()));
            tipo.setNombre(txtNombre.getText());
            tipo.setDescripcion(txtDescripcion.getText());
            
            if (tipo.actualizar()) {
                JOptionPane.showMessageDialog(this, "Tipo de discapacidad actualizado exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar tipo de discapacidad", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de la tabla");
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar este tipo de discapacidad?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (TipoDiscapacidad.eliminar(Integer.parseInt(txtId.getText()))) {
                JOptionPane.showMessageDialog(this, "Tipo de discapacidad eliminado exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar tipo de discapacidad", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    private void tablaTiposMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tablaTipos.getSelectedRow();
        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtDescripcion.setText(modeloTabla.getValueAt(fila, 2).toString());
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
    }

    // Variables declaration
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaTipos;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
}
