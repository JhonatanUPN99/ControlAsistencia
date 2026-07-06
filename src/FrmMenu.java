
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menú principal del sistema de asistencia.
 * Permite navegar entre los módulos principales.
 */
public class FrmMenu extends JFrame {

	//  Identificador de versión para la serialización.
	private static final long serialVersionUID = 1L;
	
    private JButton btnColaboradores;
    private JButton btnAsistencia;
    private JButton btnPermisos;
    private JButton btnCerrarSesion;

    public FrmMenu() {

        // =========================
        // CONFIGURACIÓN GENERAL
        // =========================
        setTitle("Menú Principal");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // =========================
        // TÍTULO
        // =========================
        JLabel lblTitulo = new JLabel("SISTEMA DE ASISTENCIA");
        lblTitulo.setBounds(120, 20, 200, 30);
        add(lblTitulo);

        // =========================
        // BOTÓN COLABORADORES
        // =========================
        btnColaboradores = new JButton("Colaboradores");
        btnColaboradores.setBounds(120, 70, 150, 30);
        add(btnColaboradores);

        // =========================
        // BOTÓN ASISTENCIA
        // =========================
        btnAsistencia = new JButton("Asistencia");
        btnAsistencia.setBounds(120, 110, 150, 30);
        add(btnAsistencia);

        // =========================
        // BOTÓN PERMISOS
        // =========================
        btnPermisos = new JButton("Permisos");
        btnPermisos.setBounds(120, 150, 150, 30);
        add(btnPermisos);

        // =========================
        // BOTÓN CERRAR SESIÓN
        // =========================
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(120, 200, 150, 30);
        add(btnCerrarSesion);

        // =========================
        // ACCIONES
        // =========================

        btnColaboradores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Abrir módulo colaboradores
                JOptionPane.showMessageDialog(null,
                        "Módulo de Colaboradores (por implementar)");

            }
        });

        btnAsistencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null,
                        "Módulo de Asistencia (por implementar)");

            }
        });

        btnPermisos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null,
                        "Módulo de Permisos (por implementar)");

            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Volver al login
                FrmLogin login = new FrmLogin();
                login.setVisible(true);

                dispose();

            }
        });
    }

    public static void main(String[] args) {

        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);

    }
}