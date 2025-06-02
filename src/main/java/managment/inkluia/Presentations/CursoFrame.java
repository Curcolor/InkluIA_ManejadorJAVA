package managment.inkluia.Presentations;

import managment.inkluia.Businesslogic.Curso;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CursoFrame extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;

    public CursoFrame() {
        initComponents();
        inicializarTabla();
        cargarDatos();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtAccesibilidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUrl = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Cursos");

        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Título", "Descripción", "Accesibilidad", "URL"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCursosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCursos);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Gestión de Cursos");

        jLabel2.setText("Título:");

        jLabel3.setText("Descripción:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(3);
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel4.setText("Accesibilidad:");

        jLabel5.setText("URL del Contenido:");

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
                            .addComponent(txtTitulo)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel4)
                            .addComponent(txtAccesibilidad)
                            .addComponent(jLabel5)
                            .addComponent(txtUrl, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(txtId))
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
                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAccesibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        modeloTabla = (DefaultTableModel) tablaCursos.getModel();
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<Curso> cursos = Curso.obtenerTodos();
        for (Curso curso : cursos) {
            Object[] fila = {
                curso.getIdCurso(),
                curso.getTitulo(),
                curso.getDescripcion(),
                curso.getAccesibilidad(),
                curso.getUrlContenido()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {
        if (validarCampos()) {
            Curso curso = new Curso(
                txtTitulo.getText(),
                txtDescripcion.getText(),
                txtAccesibilidad.getText(),
                txtUrl.getText()
            );
            
            if (curso.crear()) {
                JOptionPane.showMessageDialog(this, "Curso creado exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear curso", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso de la tabla");
            return;
        }
        
        if (validarCampos()) {
            Curso curso = new Curso();
            curso.setIdCurso(Integer.parseInt(txtId.getText()));
            curso.setTitulo(txtTitulo.getText());
            curso.setDescripcion(txtDescripcion.getText());
            curso.setAccesibilidad(txtAccesibilidad.getText());
            curso.setUrlContenido(txtUrl.getText());
            
            if (curso.actualizar()) {
                JOptionPane.showMessageDialog(this, "Curso actualizado exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar curso", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso de la tabla");
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar este curso?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (Curso.eliminar(Integer.parseInt(txtId.getText()))) {
                JOptionPane.showMessageDialog(this, "Curso eliminado exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar curso", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    private void tablaCursosMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tablaCursos.getSelectedRow();
        if (fila >= 0) {
            txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
            txtTitulo.setText(modeloTabla.getValueAt(fila, 1).toString());
            txtDescripcion.setText(modeloTabla.getValueAt(fila, 2).toString());
            txtAccesibilidad.setText(modeloTabla.getValueAt(fila, 3).toString());
            txtUrl.setText(modeloTabla.getValueAt(fila, 4).toString());
        }
    }

    private boolean validarCampos() {
        if (txtTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título es obligatorio");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtTitulo.setText("");
        txtDescripcion.setText("");
        txtAccesibilidad.setText("");
        txtUrl.setText("");
    }

    // Variables declaration
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaCursos;
    private javax.swing.JTextField txtAccesibilidad;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtUrl;
}
