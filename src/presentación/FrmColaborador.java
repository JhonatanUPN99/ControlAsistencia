package presentación;
import java.util.List;

import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Colaborador;
import negocio.ControlColaborador;

/**
 * Formulario de mantenimiento de colaboradores.
 */
public class FrmColaborador extends JFrame {

    private static final long serialVersionUID = 1L;

    // ==========================
    // CAMPOS
    // ==========================
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtCargo;
    private JTextField txtArea;

    // ==========================
    // TABLA
    // ==========================
    private JTable tblColaboradores;
    private JScrollPane scrollTabla;
    private DefaultTableModel modeloTabla;

    // ==========================
    // BOTONES
    // ==========================
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnVolver;

    // ==========================
    // NEGOCIO
    // ==========================
    private ControlColaborador control;

    // ID seleccionado
    private int idSeleccionado = -1;

    // ==========================
    // CONSTRUCTOR
    // ==========================
    public FrmColaborador() {

        control = new ControlColaborador();

        initialize();

        configurarTabla();

        cargarTabla();
    }

    // ==========================
    // INTERFAZ
    // ==========================
    private void initialize() {

        setTitle("Gestión de Colaboradores");
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE COLABORADORES");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(260, 20, 400, 30);
        getContentPane().add(lblTitulo);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setBounds(40, 80, 100, 25);
        getContentPane().add(lblNombres);

        txtNombres = new JTextField();
        txtNombres.setBounds(120, 80, 200, 25);
        getContentPane().add(txtNombres);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(40, 120, 100, 25);
        getContentPane().add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(120, 120, 200, 25);
        getContentPane().add(txtApellidos);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(40, 160, 100, 25);
        getContentPane().add(lblCargo);

        txtCargo = new JTextField();
        txtCargo.setBounds(120, 160, 200, 25);
        getContentPane().add(txtCargo);

        JLabel lblArea = new JLabel("Área:");
        lblArea.setBounds(40, 200, 100, 25);
        getContentPane().add(lblArea);

        txtArea = new JTextField();
        txtArea.setBounds(120, 200, 200, 25);
        getContentPane().add(txtArea);

        // BOTONES
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(380, 80, 120, 30);
        getContentPane().add(btnRegistrar);
        btnRegistrar.addActionListener(e -> registrarColaborador());

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(380, 120, 120, 30);
        getContentPane().add(btnActualizar);
        btnActualizar.addActionListener(e -> actualizarColaborador());


        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(380, 160, 120, 30);
        getContentPane().add(btnEliminar);
        btnEliminar.addActionListener(e -> eliminarColaborador());


        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(520, 80, 120, 30);
        getContentPane().add(btnLimpiar);
        btnLimpiar.addActionListener(e -> limpiarCampos());


        btnVolver = new JButton("Volver");
        btnVolver.setBounds(520, 120, 120, 30);
        getContentPane().add(btnVolver);
        btnVolver.addActionListener(e -> volver());

        // TABLA
        tblColaboradores = new JTable();

        scrollTabla = new JScrollPane(tblColaboradores);
        scrollTabla.setBounds(40, 260, 800, 250);

        getContentPane().add(scrollTabla);
    }

    // ==========================
    // TABLA CONFIG
    // ==========================
    private void configurarTabla() {

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombres");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Cargo");
        modeloTabla.addColumn("Área");

        tblColaboradores.setModel(modeloTabla);

        tblColaboradores.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {
                cargarSeleccion();
            }

        });
    }

    // ==========================
    // CARGAR DATOS
    // ==========================
    private void cargarTabla() {

        modeloTabla.setRowCount(0);

        List<Colaborador> lista = control.listarColaboradores();

        for (Colaborador c : lista) {

            modeloTabla.addRow(new Object[]{
                    c.getIdColaborador(),
                    c.getNombres(),
                    c.getApellidos(),
                    c.getCargo(),
                    c.getArea()
            });

        }
    }

    // ==========================
    // LIMPIAR CAMPOS
    // ==========================
    private void limpiarCampos() {

        txtNombres.setText("");
        txtApellidos.setText("");
        txtCargo.setText("");
        txtArea.setText("");

        idSeleccionado = -1;

        tblColaboradores.clearSelection();
    }

    // ==========================
    // SELECCIÓN TABLA
    // ==========================
    private void cargarSeleccion() {

        int fila = tblColaboradores.getSelectedRow();

        if (fila == -1) return;

        idSeleccionado = Integer.parseInt(
                modeloTabla.getValueAt(fila, 0).toString()
        );

        txtNombres.setText(modeloTabla.getValueAt(fila, 1).toString());
        txtApellidos.setText(modeloTabla.getValueAt(fila, 2).toString());
        txtCargo.setText(modeloTabla.getValueAt(fila, 3).toString());
        txtArea.setText(modeloTabla.getValueAt(fila, 4).toString());
    }
    
    /**
     * Registra un nuevo colaborador.
     */
    private void registrarColaborador() {

        boolean ok = control.registrarColaborador(
                txtNombres.getText(),
                txtApellidos.getText(),
                txtCargo.getText(),
                txtArea.getText()
        );

        if (ok) {

            JOptionPane.showMessageDialog(this,
                    "Colaborador registrado correctamente.");

            cargarTabla();
            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Complete todos los campos.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void actualizarColaborador() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(this,
                    "Seleccione un colaborador para actualizar.");

            return;
        }

        boolean ok = control.actualizarColaborador(
                idSeleccionado,
                txtNombres.getText(),
                txtApellidos.getText(),
                txtCargo.getText(),
                txtArea.getText()
        );

        if (ok) {

            JOptionPane.showMessageDialog(this,
                    "Colaborador actualizado correctamente.");

            cargarTabla();
            limpiarCampos();
        }
    }
    
    private void eliminarColaborador() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(this,
                    "Seleccione un colaborador.");

            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar este colaborador?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            control.eliminarColaborador(idSeleccionado);

            JOptionPane.showMessageDialog(this,
                    "Colaborador eliminado.");

            cargarTabla();
            limpiarCampos();
        }
    }
    
    private void volver() {

        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);

        this.dispose();
    }
    
    
    public static void main(String[] args) {

        FrmColaborador frm = new FrmColaborador();
        frm.setVisible(true);

    }
}
