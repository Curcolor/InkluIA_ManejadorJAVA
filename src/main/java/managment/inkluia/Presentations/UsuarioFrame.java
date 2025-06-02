package managment.inkluia.Presentations;

import managment.inkluia.Businesslogic.Usuario;
import managment.inkluia.Businesslogic.TipoDiscapacidad;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioFrame extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;

    public UsuarioFrame() {
        initComponents();
        inicializarTabla();
        cargarTiposDiscapacidad();
        cargarDatos();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmbDiscapacidad = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Usuarios");

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nombre Completo", "Correo", "Rol", "Discapacidad", "Fecha Registro"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Gestión de Usuarios");

        jLabel2.setText("Nombre Completo:");

        jLabel3.setText("Correo:");

        jLabel4.setText("Contraseña:");

        jLabel5.setText("Rol:");

        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Talento", "Empresa", "ONG"}));

        jLabel6.setText("Tipo de Discapacidad:");

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

        jLabel7.setText("Buscar por nombre:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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
                            .addComponent(txtNombre)
                            .addComponent(jLabel3)
                            .addComponent(txtCorreo)
                            .addComponent(jLabel4)
                            .addComponent(txtContrasena)
                            .addComponent(jLabel5)
                            .addComponent(cmbRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(cmbDiscapacidad, 0, 250, Short.MAX_VALUE)
                            .addComponent(txtId)
                            .addComponent(jLabel7)
                            .addComponent(txtBusqueda)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbDiscapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar))
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
        modeloTabla = (DefaultTableModel) tablaUsuarios.getModel();
    }

    private void cargarTiposDiscapacidad() {
        cmbDiscapacidad.removeAllItems();
        cmbDiscapacidad.addItem("Sin discapacidad");
        List<TipoDiscapacidad> tipos = TipoDiscapacidad.obtenerTodos();
        for (TipoDiscapacidad tipo : tipos) {
            cmbDiscapacidad.addItem(tipo.getNombre());
        }
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Usuario> usuarios = Usuario.obtenerTodos();
        for (Usuario usuario : usuarios) {
            Object[] fila = {
                usuario.getIdUsuario(),
                usuario.getNombreCompleto(),
                usuario.getCorreo(),
                usuario.getRol(),
                obtenerNombreDiscapacidad(usuario.getIdDiscapacidad()),
                usuario.getFechaRegistro()
            };
            modeloTabla.addRow(fila);
        }
    }

    private String obtenerNombreDiscapacidad(Integer idDiscapacidad) {
        if (idDiscapacidad == null) return "Sin discapacidad";
        List<TipoDiscapacidad> tipos = TipoDiscapacidad.obtenerTodos();
        for (TipoDiscapacidad tipo : tipos) {
            if (tipo.getIdDiscapacidad() == idDiscapacidad) {
                return tipo.getNombre();
            }
        }
        return "Sin discapacidad";
    }

    private Integer obtenerIdDiscapacidad(String nombre) {
        if ("Sin discapacidad".equals(nombre)) return null;
        List<TipoDiscapacidad> tipos = TipoDiscapacidad.obtenerTodos();
        for (TipoDiscapacidad tipo : tipos) {
            if (tipo.getNombre().equals(nombre)) {
                return tipo.getIdDiscapacidad();
            }
        }
        return null;
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        if (validarCampos()) {
            Usuario usuario = new Usuario(
                txtNombre.getText(),
                txtCorreo.getText(),
                new String(txtContrasena.getPassword()),
                cmbRol.getSelectedItem().toString(),
                obtenerIdDiscapacidad(cmbDiscapacidad.getSelectedItem().toString())
            );
            
            if (usuario.crear()) {
                JOptionPane.showMessageDialog(this, "Usuario creado exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla");
            return;
        }
        
        if (validarCampos()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(Integer.parseInt(txtId.getText()));
            usuario.setNombreCompleto(txtNombre.getText());
            usuario.setCorreo(txtCorreo.getText());
            usuario.setContrasena(new String(txtContrasena.getPassword()));
            usuario.setRol(cmbRol.getSelectedItem().toString());
            usuario.setIdDiscapacidad(obtenerIdDiscapacidad(cmbDiscapacidad.getSelectedItem().toString()));
            
            if (usuario.actualizar()) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla");
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar este usuario?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (Usuario.eliminar(Integer.parseInt(txtId.getText()))) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tablaUsuarios.getSelectedRow();
        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtCorreo.setText(modeloTabla.getValueAt(fila, 2).toString());
            cmbRol.setSelectedItem(modeloTabla.getValueAt(fila, 3).toString());
            cmbDiscapacidad.setSelectedItem(modeloTabla.getValueAt(fila, 4).toString());
        }
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String nombreBusqueda = txtBusqueda.getText().trim();
        if (nombreBusqueda.isEmpty()) {
            cargarDatos();
        } else {
            modeloTabla.setRowCount(0);
            List<Usuario> usuarios = Usuario.buscarPorNombre(nombreBusqueda);
            for (Usuario usuario : usuarios) {
                Object[] fila = {
                    usuario.getIdUsuario(),
                    usuario.getNombreCompleto(),
                    usuario.getCorreo(),
                    usuario.getRol(),
                    obtenerNombreDiscapacidad(usuario.getIdDiscapacidad()),
                    usuario.getFechaRegistro()
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
            return false;
        }
        if (txtCorreo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El correo es obligatorio");
            return false;
        }
        if (txtContrasena.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "La contraseña es obligatoria");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtContrasena.setText("");
        cmbRol.setSelectedIndex(0);
        cmbDiscapacidad.setSelectedIndex(0);
        txtBusqueda.setText("");
    }

    // Variables declaration
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbDiscapacidad;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtBusqueda;
}
