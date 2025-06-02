package managment.inkluia.Presentations;

import managment.inkluia.Businesslogic.CursoUsuario;
import managment.inkluia.Businesslogic.Curso;
import managment.inkluia.Businesslogic.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class InscripcionFrame extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla;
    private JButton btnBuscar;
    private JTextField txtBusqueda;
    private JLabel lblBuscar;

    public InscripcionFrame() {
        initComponents();
        inicializarTabla();
        cargarCursos();
        cargarUsuarios();
        cargarDatos();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInscripciones = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbCurso = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox<>();
        btnInscribir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        
        // Componentes de búsqueda
        txtBusqueda = new JTextField();
        btnBuscar = new JButton();
        lblBuscar = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Inscripciones a Cursos");

        tablaInscripciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID Curso", "Título", "ID Usuario", "Usuario", "Fecha Inscripción"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaInscripciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInscripcionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaInscripciones);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Gestión de Inscripciones a Cursos");

        jLabel2.setText("Curso:");

        jLabel3.setText("Usuario:");

        btnInscribir.setText("Inscribir");
        btnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar Inscripción");
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
        });        btnVerDetalle.setText("Ver Detalle (Vista)");
        btnVerDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalleActionPerformed(evt);
            }
        });
        
        // Configuración de componentes de búsqueda
        lblBuscar.setText("Buscar por usuario:");
        txtBusqueda.setToolTipText("Buscar por nombre de usuario");
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
                            .addComponent(cmbCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(cmbUsuario, 0, 350, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInscribir)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerDetalle)
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
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30)                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInscribir)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnVerDetalle)
                    .addComponent(lblBuscar)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    private void inicializarTabla() {
        modeloTabla = (DefaultTableModel) tablaInscripciones.getModel();
    }

    private void cargarCursos() {
        cmbCurso.removeAllItems();
        List<Curso> cursos = Curso.obtenerTodos();
        for (Curso curso : cursos) {
            cmbCurso.addItem(curso.getIdCurso() + " - " + curso.getTitulo());
        }
    }

    private void cargarUsuarios() {
        cmbUsuario.removeAllItems();
        List<Usuario> usuarios = Usuario.obtenerTodos();
        for (Usuario usuario : usuarios) {
            cmbUsuario.addItem(usuario.getIdUsuario() + " - " + usuario.getNombreCompleto());
        }
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0);
        List<CursoUsuario> inscripciones = CursoUsuario.obtenerTodas();
        for (CursoUsuario inscripcion : inscripciones) {
            Object[] fila = {
                inscripcion.getIdCurso(),
                obtenerTituloCurso(inscripcion.getIdCurso()),
                inscripcion.getIdUsuario(),
                obtenerNombreUsuario(inscripcion.getIdUsuario()),
                inscripcion.getFechaInscripcion()
            };
            modeloTabla.addRow(fila);
        }    }

    private void cargarDatosConVista() {
        modeloTabla.setRowCount(0);
        List<CursoUsuario> inscripciones = CursoUsuario.obtenerTodas();
        for (CursoUsuario inscripcion : inscripciones) {
            Object[] fila = {
                inscripcion.getIdCurso(),
                obtenerTituloCurso(inscripcion.getIdCurso()),
                inscripcion.getIdUsuario(),
                obtenerNombreUsuario(inscripcion.getIdUsuario()),
                inscripcion.getFechaInscripcion()
            };
            modeloTabla.addRow(fila);
        }
    }

    private String obtenerTituloCurso(int idCurso) {
        Curso curso = Curso.obtener(idCurso);
        return curso != null ? curso.getTitulo() : "Curso no encontrado";
    }

    private String obtenerNombreUsuario(int idUsuario) {
        Usuario usuario = Usuario.obtener(idUsuario);
        return usuario != null ? usuario.getNombreCompleto() : "Usuario no encontrado";
    }

    private int obtenerId(String texto) {
        return Integer.parseInt(texto.split(" - ")[0]);
    }

    private void btnInscribirActionPerformed(java.awt.event.ActionEvent evt) {
        if (validarCampos()) {
            CursoUsuario inscripcion = new CursoUsuario(
                obtenerId(cmbCurso.getSelectedItem().toString()),
                obtenerId(cmbUsuario.getSelectedItem().toString())
            );
            
            if (inscripcion.inscribir()) {
                JOptionPane.showMessageDialog(this, "Inscripción realizada exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al realizar inscripción", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tablaInscripciones.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una inscripción de la tabla");
            return;
        }
        
        int idCurso = (int) modeloTabla.getValueAt(fila, 0);
        int idUsuario = (int) modeloTabla.getValueAt(fila, 2);
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de eliminar esta inscripción?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (CursoUsuario.eliminarInscripcion(idCurso, idUsuario)) {
                JOptionPane.showMessageDialog(this, "Inscripción eliminada exitosamente");
                cargarDatos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar inscripción", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {
        limpiarCampos();
    }

    private void btnVerDetalleActionPerformed(java.awt.event.ActionEvent evt) {
        cargarDatosConVista();
    }

    private void tablaInscripcionesMouseClicked(java.awt.event.MouseEvent evt) {
        int fila = tablaInscripciones.getSelectedRow();
        if (fila >= 0) {
            int idCurso = (int) modeloTabla.getValueAt(fila, 0);
            int idUsuario = (int) modeloTabla.getValueAt(fila, 2);
            
            // Buscar en los combobox
            for (int i = 0; i < cmbCurso.getItemCount(); i++) {
                if (cmbCurso.getItemAt(i).startsWith(idCurso + " - ")) {
                    cmbCurso.setSelectedIndex(i);
                    break;
                }
            }
            
            for (int i = 0; i < cmbUsuario.getItemCount(); i++) {
                if (cmbUsuario.getItemAt(i).startsWith(idUsuario + " - ")) {
                    cmbUsuario.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private boolean validarCampos() {
        if (cmbCurso.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un curso");
            return false;
        }
        if (cmbUsuario.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        if (cmbCurso.getItemCount() > 0) cmbCurso.setSelectedIndex(0);        if (cmbUsuario.getItemCount() > 0) cmbUsuario.setSelectedIndex(0);
    }    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String nombreBusqueda = txtBusqueda.getText().trim();
        
        if (nombreBusqueda.isEmpty()) {
            cargarDatos(); // Si no hay texto de búsqueda, mostrar todas las inscripciones
            return;
        }

        try {
            // Buscar usuarios por nombre
            List<Usuario> usuariosEncontrados = Usuario.buscarPorNombre(nombreBusqueda);
            
            // Si no encontramos usuarios, mostrar mensaje y salir
            if (usuariosEncontrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "No se encontraron usuarios con el nombre: " + nombreBusqueda, 
                    "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Limpiar la tabla
            modeloTabla.setRowCount(0);
            
            // Obtener todas las inscripciones
            List<CursoUsuario> todasInscripciones = CursoUsuario.obtenerTodas();
            
            // Filtrar las inscripciones para mostrar solo las de los usuarios encontrados
            for (CursoUsuario inscripcion : todasInscripciones) {
                for (Usuario usuario : usuariosEncontrados) {
                    if (inscripcion.getIdUsuario() == usuario.getIdUsuario()) {
                        Object[] fila = {
                            inscripcion.getIdCurso(),
                            obtenerTituloCurso(inscripcion.getIdCurso()),
                            inscripcion.getIdUsuario(),
                            usuario.getNombreCompleto(),
                            inscripcion.getFechaInscripcion()
                        };
                        modeloTabla.addRow(fila);
                        break; // Ya encontramos el usuario correspondiente, no necesitamos seguir buscando
                    }
                }
            }
            
            // Si no hay filas después de filtrar, significa que los usuarios encontrados no tienen inscripciones
            if (modeloTabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, 
                    "Los usuarios encontrados no tienen inscripciones", 
                    "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al buscar inscripciones: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInscribir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JComboBox<String> cmbCurso;
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaInscripciones;
}
