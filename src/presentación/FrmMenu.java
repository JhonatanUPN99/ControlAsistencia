package presentación;

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
    private JButton btnReportes;
    
    public FrmMenu() {

        // =========================
        // CONFIGURACIÓN GENERAL
        // =========================
        setTitle("Menú Principal");
        setSize(400, 350);
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
        // BOTÓN REPORTES
        // =========================

        btnReportes = new JButton("Reportes");
        btnReportes.setBounds(120,190,150,30);
        add(btnReportes);

        // =========================
        // BOTÓN CERRAR SESIÓN
        // =========================
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(120, 230, 150, 30);
        add(btnCerrarSesion);

        // =========================
        // ACCIONES
        // =========================
        
        btnReportes.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                FrmReporte reporte =
                        new FrmReporte();


                reporte.setVisible(true);


                dispose();

            }

        });

        btnColaboradores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                FrmColaborador ventana = new FrmColaborador();

                ventana.setVisible(true);

                dispose();

            }

        });

        btnAsistencia.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                FrmAsistencia ventana = new FrmAsistencia();

                ventana.setVisible(true);

                dispose();

            }

        });

        btnPermisos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                FrmPermiso ventana = new FrmPermiso();

                ventana.setVisible(true);

                dispose();

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