package presentación;

import java.awt.EventQueue;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import modelo.Permiso;
import negocio.ControlPermiso;


public class FrmPermiso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// =====================================================
	// ATRIBUTOS
	// =====================================================

	// Lógica del negocio
	private ControlPermiso controlPermiso;

	// Componentes de entrada
	private JTextField txtIdColaborador;
	private JTextField txtMotivo;

	// Tabla
	private JTable tablaPermisos;
	private DefaultTableModel modeloTabla;

	// Botones
	private JButton btnRegistrar;
	private JButton btnAprobar;
	private JButton btnDenegar;
	private JButton btnEliminar;
	private JButton btnVolver;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPermiso frame = new FrmPermiso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmPermiso() {

	    // Inicializar lógica de negocio
	    controlPermiso = new ControlPermiso();

	    // Configuración de la ventana
	    setTitle("Gestión de Permisos");
	    setSize(900, 550);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    getContentPane().setLayout(null);

	 // ===========================================
	 // ETIQUETAS
	 // ===========================================

	 // ID del colaborador
	 JLabel lblId = new JLabel("ID Colaborador:");
	 lblId.setBounds(30, 30, 120, 25);
	 getContentPane().add(lblId);

	 // Motivo
	 JLabel lblMotivo = new JLabel("Motivo:");
	 lblMotivo.setBounds(30, 70, 120, 25);
	 getContentPane().add(lblMotivo);
	 
	// ===========================================
	// CAMPOS DE TEXTO
	// ===========================================

	// Campo para el ID del colaborador
	txtIdColaborador = new JTextField();
	txtIdColaborador.setBounds(150, 30, 180, 25);
	getContentPane().add(txtIdColaborador);

	// Campo para el motivo del permiso
	txtMotivo = new JTextField();
	txtMotivo.setBounds(150, 70, 350, 25);
	getContentPane().add(txtMotivo);
	
	// ===========================================
	// TABLA DE PERMISOS
	// ===========================================

	// Modelo de la tabla
	modeloTabla = new DefaultTableModel();

	modeloTabla.addColumn("ID Permiso");
	modeloTabla.addColumn("Colaborador");
	modeloTabla.addColumn("Fecha");
	modeloTabla.addColumn("Motivo");
	modeloTabla.addColumn("Estado");

	// Crear la tabla
	tablaPermisos = new JTable(modeloTabla);

	// Scroll para la tabla
	JScrollPane scrollPane = new JScrollPane(tablaPermisos);
	scrollPane.setBounds(30, 180, 830, 260);
	getContentPane().add(scrollPane);
	
	// ===========================================
	// BOTONES
	// ===========================================

	// Registrar
	btnRegistrar = new JButton("Registrar");
	btnRegistrar.setBounds(560, 30, 130, 30);
	getContentPane().add(btnRegistrar);
	btnRegistrar.addActionListener(e -> registrarPermiso());

	// Aprobar
	btnAprobar = new JButton("Aprobar");
	btnAprobar.setBounds(700, 30, 130, 30);
	getContentPane().add(btnAprobar);
	btnAprobar.addActionListener(e -> aprobarPermiso());

	// Denegar
	btnDenegar = new JButton("Denegar");
	btnDenegar.setBounds(560, 70, 130, 30);
	getContentPane().add(btnDenegar);
	btnDenegar.addActionListener(e -> denegarPermiso());

	// Eliminar
	btnEliminar = new JButton("Eliminar");
	btnEliminar.setBounds(700, 70, 130, 30);
	getContentPane().add(btnEliminar);
	btnEliminar.addActionListener(e -> eliminarPermiso());

	// Volver
	btnVolver = new JButton("Volver");
	btnVolver.setBounds(730, 455, 120, 30);
	getContentPane().add(btnVolver);
	btnVolver.addActionListener(e -> volver());
	
	// Cargar los permisos existentes.
	cargarTabla();
	
	}
	/**
	 * Carga todos los permisos registrados en la tabla.
	 */
	private void cargarTabla() {

	    // Limpiar la tabla.
	    modeloTabla.setRowCount(0);

	    // Recorrer todos los permisos registrados.
	    for (Permiso permiso : controlPermiso.listar()) {

	        modeloTabla.addRow(new Object[]{

	                permiso.getIdPermiso(),

	                permiso.getColaborador().obtenerNombreCompleto(),

	                permiso.getFechaSolicitud(),

	                permiso.getMotivo(),

	                permiso.getEstado()

	        });

	    }

	}
	/**
	 * Registra una nueva solicitud de permiso.
	 */
	private void registrarPermiso() {

	    try {

	        // Obtener datos ingresados.
	        int idColaborador =
	                Integer.parseInt(txtIdColaborador.getText());

	        String motivo =
	                txtMotivo.getText();

	        // Registrar permiso.
	        boolean registrado =
	                controlPermiso.registrar(

	                        idColaborador,

	                        LocalDate.now(),

	                        motivo

	                );

	        if (registrado) {

	            JOptionPane.showMessageDialog(

	                    this,

	                    "Permiso registrado correctamente."

	            );

	            txtIdColaborador.setText("");
	            txtMotivo.setText("");

	            cargarTabla();

	        } else {

	            JOptionPane.showMessageDialog(

	                    this,

	                    "No fue posible registrar el permiso."

	            );

	        }

	    } catch (NumberFormatException ex) {

	        JOptionPane.showMessageDialog(

	                this,

	                "Ingrese un ID válido."

	        );

	    }

	}
	/**
	 * Aprueba el permiso seleccionado.
	 */
	private void aprobarPermiso() {

	    // Obtener la fila seleccionada.
	    int fila = tablaPermisos.getSelectedRow();

	    if (fila == -1) {

	        JOptionPane.showMessageDialog(

	                this,

	                "Seleccione un permiso."

	        );

	        return;

	    }

	    // Obtener el ID del permiso.
	    int id = (int) modeloTabla.getValueAt(fila, 0);

	    // Aprobar el permiso.
	    if (controlPermiso.aprobar(id)) {

	        JOptionPane.showMessageDialog(

	                this,

	                "Permiso aprobado correctamente."

	        );

	        cargarTabla();

	    }

	}
	/**
	 * Deniega el permiso seleccionado.
	 */
	private void denegarPermiso() {

	    int fila = tablaPermisos.getSelectedRow();

	    if (fila == -1) {

	        JOptionPane.showMessageDialog(

	                this,

	                "Seleccione un permiso."

	        );

	        return;

	    }

	    int id = (int) modeloTabla.getValueAt(fila, 0);

	    if (controlPermiso.denegar(id)) {

	        JOptionPane.showMessageDialog(

	                this,

	                "Permiso denegado correctamente."

	        );

	        cargarTabla();

	    }

	}
	/**
	 * Elimina el permiso seleccionado.
	 */
	private void eliminarPermiso() {

	    int fila = tablaPermisos.getSelectedRow();

	    if (fila == -1) {

	        JOptionPane.showMessageDialog(

	                this,

	                "Seleccione un permiso."

	        );

	        return;

	    }

	    int id = (int) modeloTabla.getValueAt(fila, 0);

	    int opcion = JOptionPane.showConfirmDialog(

	            this,

	            "¿Desea eliminar este permiso?",

	            "Confirmar",

	            JOptionPane.YES_NO_OPTION

	    );

	    if (opcion == JOptionPane.YES_OPTION) {

	        controlPermiso.eliminar(id);

	        JOptionPane.showMessageDialog(

	                this,

	                "Permiso eliminado correctamente."

	        );

	        cargarTabla();

	    }

	}
	/**
	 * Regresa al menú principal.
	 */
	private void volver() {

	    FrmMenu menu = new FrmMenu();

	    menu.setVisible(true);

	    dispose();

	}
}
