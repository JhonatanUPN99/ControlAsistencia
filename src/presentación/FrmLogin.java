package presentación;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negocio.Autenticacion;

/**
 * Interfaz gráfica de inicio de sesión del sistema.
 * Permite validar credenciales contra la lógica de negocio.
 */
public class FrmLogin extends JFrame {
	
	//  Identificador de versión para la serialización.
	private static final long serialVersionUID = 1L;
    // =========================
    // COMPONENTES DE LA UI
    // =========================
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblMensaje;

    // =========================
    // LÓGICA DE AUTENTICACIÓN
    // =========================
    private Autenticacion auth = new Autenticacion();

    /**
     * Constructor del formulario.
     */
    public FrmLogin() {

        // Configuración básica de la ventana
        setTitle("Login - Sistema de Asistencia");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // =========================
        // LABEL USUARIO
        // =========================
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(30, 30, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 30, 150, 25);
        add(txtUsuario);

        // =========================
        // LABEL CONTRASEÑA
        // =========================
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(30, 70, 80, 25);
        add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 70, 150, 25);
        add(txtPassword);

        // =========================
        // BOTÓN LOGIN
        // =========================
        btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(120, 110, 120, 30);
        add(btnLogin);

        // =========================
        // LABEL MENSAJES
        // =========================
        lblMensaje = new JLabel("");
        lblMensaje.setBounds(30, 150, 250, 25);
        add(lblMensaje);

        // =========================
        // ACCIÓN DEL BOTÓN LOGIN
        // =========================
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtener datos ingresados
                String usuario = txtUsuario.getText();
                String clave = new String(txtPassword.getPassword());

                // Validar credenciales
                boolean acceso = auth.iniciarSesion(usuario, clave);

                // =========================
                // RESULTADO DE VALIDACIÓN
                // =========================
                if (acceso) {

                    lblMensaje.setText("Acceso correcto");

                    // FUTURO: abrir menú principal
                    // FrmMenu menu = new FrmMenu();
                    // menu.setVisible(true);
                    // dispose();

                } else {

                    lblMensaje.setText("Usuario o contraseña incorrectos");

                }
            }
        });
    }

    /**
     * Método principal para ejecutar el formulario.
     */
    public static void main(String[] args) {

        FrmLogin login = new FrmLogin();
        login.setVisible(true);

    }
}