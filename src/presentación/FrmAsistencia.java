package presentación;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import modelo.Asistencia;
import negocio.ControlAsistencia;

/**
 * Formulario de control de asistencia.
 */
public class FrmAsistencia extends JFrame {

    private static final long serialVersionUID = 1L;

    // ======================
    // NEGOCIO
    // ======================
    private ControlAsistencia control;

    // ======================
    // COMPONENTES
    // ======================
    private JTextField txtIdColaborador;

    private JButton btnIngreso;
    private JButton btnSalida;
    private JButton btnVolver;

    private JTable tabla;
    private DefaultTableModel modelo;

    public FrmAsistencia() {

        control = new ControlAsistencia();

        setTitle("Control de Asistencia");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        inicializarComponentes();
        configurarTabla();
        cargarTabla();
    }
    
    private void inicializarComponentes() {

        JLabel lblId = new JLabel("ID Colaborador:");
        lblId.setBounds(30, 30, 120, 25);
        add(lblId);

        txtIdColaborador = new JTextField();
        txtIdColaborador.setBounds(150, 30, 150, 25);
        add(txtIdColaborador);

        // BOTONES
        btnIngreso = new JButton("Registrar Ingreso");
        btnIngreso.setBounds(330, 30, 150, 25);
        add(btnIngreso);

        btnSalida = new JButton("Registrar Salida");
        btnSalida.setBounds(490, 30, 150, 25);
        add(btnSalida);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(650, 30, 100, 25);
        add(btnVolver);

        // EVENTOS
        btnIngreso.addActionListener(e -> registrarIngreso());
        btnSalida.addActionListener(e -> registrarSalida());
        btnVolver.addActionListener(e -> volver());
    }

    private void configurarTabla() {

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Ingreso");
        modelo.addColumn("Salida");
        modelo.addColumn("Estado");
        modelo.addColumn("Colaborador");

        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 80, 700, 350);

        add(scroll);
    }
    
    private void cargarTabla() {

        modelo.setRowCount(0);

        List<Asistencia> lista = control.listar();

        for (Asistencia a : lista) {

            modelo.addRow(new Object[]{
                    a.getIdAsistencia(),
                    a.getFecha(),
                    a.getHoraIngreso(),
                    a.getHoraSalida(),
                    a.getEstado(),
                    a.getColaborador().getNombres()
            });
        }
    }
    
    private void registrarIngreso() {

        try {

            int id = Integer.parseInt(txtIdColaborador.getText());

            boolean ok = control.registrarIngreso(id);

            if (ok) {

                JOptionPane.showMessageDialog(this,
                        "Ingreso registrado correctamente");

                cargarTabla();

            } else {

                JOptionPane.showMessageDialog(this,
                        "No se pudo registrar ingreso (ya existe o no existe colaborador)");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "ID inválido");
        }
    }

    private void registrarSalida() {

        try {

            int id = Integer.parseInt(txtIdColaborador.getText());

            boolean ok = control.registrarSalida(id);

            if (ok) {

                JOptionPane.showMessageDialog(this,
                        "Salida registrada correctamente");

                cargarTabla();

            } else {

                JOptionPane.showMessageDialog(this,
                        "No se pudo registrar salida");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "ID inválido");
        }
    }
    
    private void volver() {

        new FrmMenu().setVisible(true);
        this.dispose();
    }
    
    public static void main(String[] args) {

        FrmAsistencia frm = new FrmAsistencia();
        frm.setVisible(true);

    }
}