package managment.inkluia.Presentations;

import managment.inkluia.Businesslogic.Vacante;
import managment.inkluia.Businesslogic.Empresa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VacanteFrame extends javax.swing.JFrame {    private DefaultTableModel modeloTabla;
    private JButton btnBuscar;
    private JTextField txtBusqueda;
    private JLabel lblBuscar;

    public VacanteFrame() {
        initComponents();
        inicializarTabla();
        cargarEmpresas();
        cargarDatos();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVacantes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbEmpresa = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRequisitos = new javax.swing.JTextArea();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();        btnLimpiar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
          // Componentes de búsqueda
        txtBusqueda = new JTextField();
        btnBuscar = new JButton();
        lblBuscar = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Vacantes");

        tablaVacantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Empresa", "Título", "Descripción", "Fecha Publicación"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVacantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVacantesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaVacantes);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Gestión de Vacantes");

        jLabel2.setText("Empresa:");

        jLabel3.setText("Título:");

        jLabel4.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(3);
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel5.setText("Requisitos:");

        txtRequisitos.setColumns(20);
        txtRequisitos.setRows(3);
        jScrollPane3.setViewportView(txtRequisitos);

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
          // Configuración de componentes de búsqueda
        lblBuscar.setText("Buscar por título:");
        txtBusqueda.setToolTipText("Buscar por título de vacante");
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
                            .addComponent(cmbEmpresa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(txtTitulo)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(txtId))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)                        .addComponent(btnLimpiar)
                        .addGap(30, 30, 30)
                        .addComponent(lblBuscar)
                        .addGap(10, 10, 10)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnBuscar)))
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
                        .addComponent(cmbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30)                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(lblBuscar)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    private void inicializarTabla() {
        modeloTabla = (DefaultTableModel) tablaVacantes.getModel();
    }    private void cargarEmpresas() {
        cmbEmpresa.removeAllItems();
        List<Empresa> empresas = Empresa.obtenerTodas();
        for (Empresa empresa : empresas) {
            cmbEmpresa.addItem(empresa.getIdEmpresa() + " - " + empresa.getNombreEmpresa());
        }
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Vacante> vacantes = Vacante.obtenerTodas();
        for (Vacante vacante : vacantes) {
            Object[] fila = {
                vacante.getIdVacante(),
                obtenerNombreEmpresa(vacante.getIdEmpresa()),
                vacante.getTitulo(),
                vacante.getDescripcion(),
                vacante.getFechaPublicacion()
            };
            modeloTabla.addRow(fila);
        }    }

    private void cargarDatosConVista() {
        modeloTabla.setRowCount(0);
        List<Vacante> vacantes = Vacante.obtenerTodas();
        for (Vacante vacante : vacantes) {
            Object[] fila = {
                vacante.getIdVacante(),
                obtenerNombreEmpresa(vacante.getIdEmpresa()),
                vacante.getTitulo(),
                vacante.getDescripcion(),
                vacante.getRequisitos(),
                vacante.getFechaPublicacion()
            };
            modeloTabla.addRow(fila);
        }
    }private String obtenerNombreEmpresa(int idEmpresa) {
        Empresa empresa = Empresa.obtener(idEmpresa);
        return empresa != null ? empresa.getNombreEmpresa() : "Empresa no encontrada";
    }

    private int obtenerIdEmpresa(String texto) {
        return Integer.parseInt(texto.split(" - ")[0]);
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        if (validarCampos()) {
            Vacante vacante = new Vacante(
                obtenerIdEmpresa(cmbEmpresa.getSelectedItem().toString()),
                txtTitulo.getText(),
                txtDescripcion.getText(),
                txtRequisitos.getText()
            );
            
            if (vacante.crear()) {
                JOptionPane.showMessageDialog(this, "Vacante creada exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear vacante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una vacante de la tabla");
            return;
        }
        
        if (validarCampos()) {
            Vacante vacante = new Vacante();
            vacante.setIdVacante(Integer.parseInt(txtId.getText()));
            vacante.setTitulo(txtTitulo.getText());
            vacante.setDescripcion(txtDescripcion.getText());
            vacante.setRequisitos(txtRequisitos.getText());
            
            if (vacante.actualizar()) {
                JOptionPane.showMessageDialog(this, "Vacante actualizada exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar vacante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una vacante de la tabla");
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar esta vacante?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (Vacante.eliminar(Integer.parseInt(txtId.getText()))) {
                JOptionPane.showMessageDialog(this, "Vacante eliminada exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar vacante", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }


    private void tablaVacantesMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tablaVacantes.getSelectedRow();
        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            String empresaTexto = modeloTabla.getValueAt(fila, 1).toString();
            
            // Buscar la empresa en el combobox
            for (int i = 0; i < cmbEmpresa.getItemCount(); i++) {
                if (cmbEmpresa.getItemAt(i).contains(empresaTexto)) {
                    cmbEmpresa.setSelectedIndex(i);
                    break;
                }
            }
            
            txtTitulo.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtDescripcion.setText(modeloTabla.getValueAt(fila, 3).toString());
        }
    }

    private boolean validarCampos() {
        if (cmbEmpresa.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una empresa");
            return false;
        }
        if (txtTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título es obligatorio");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtId.setText("");
        if (cmbEmpresa.getItemCount() > 0) cmbEmpresa.setSelectedIndex(0);
        txtTitulo.setText("");
        txtDescripcion.setText("");        txtRequisitos.setText("");
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String tituloBusqueda = txtBusqueda.getText().trim();
        
        if (tituloBusqueda.isEmpty()) {
            cargarDatos(); // Si no hay texto de búsqueda, mostrar todas las vacantes
            return;
        }

        try {
            List<Vacante> vacantesEncontradas = Vacante.buscarPorTitulo(tituloBusqueda);
            modeloTabla.setRowCount(0);
            
            for (Vacante vacante : vacantesEncontradas) {
                Object[] fila = {
                    vacante.getIdVacante(),
                    obtenerNombreEmpresa(vacante.getIdEmpresa()),
                    vacante.getTitulo(),
                    vacante.getDescripcion(),
                    vacante.getFechaPublicacion()
                };
                modeloTabla.addRow(fila);
            }
            
            if (vacantesEncontradas.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontraron vacantes con el título: " + tituloBusqueda, 
                    "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar vacantes: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbEmpresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaVacantes;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextArea txtRequisitos;
    private javax.swing.JTextField txtTitulo;
}
