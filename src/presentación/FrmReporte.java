package presentación;


import javax.swing.*;

import negocio.ControlReporte;


/**
 * Ventana encargada de mostrar
 * información consolidada del sistema.
 */
public class FrmReporte extends JFrame {


    // ==================================
    // ATRIBUTOS
    // ==================================

    // Controlador de lógica de reportes.
    private ControlReporte controlReporte;


    // Componentes visuales.
    private JLabel lblTitulo;

    private JButton btnResumen;

    private JButton btnReporteColaborador;

    private JButton btnVolver;



    // ==================================
    // CONSTRUCTOR
    // ==================================

    public FrmReporte() {


        // Inicializar lógica de negocio.
        controlReporte = new ControlReporte();


        // Configuración general.
        setTitle("Módulo de Reportes");

        setSize(500,400);

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        // Crear interfaz.
        construirInterfaz();



        // Crear eventos.
        agregarEventos();


    }
    /**
     * Construye los elementos visuales
     * del formulario.
     */
    private void construirInterfaz() {


        // ==========================
        // TÍTULO
        // ==========================

        lblTitulo = new JLabel(
                "REPORTE DEL SISTEMA"
        );

        lblTitulo.setBounds(
                160,
                30,
                250,
                30
        );

        add(lblTitulo);



        // ==========================
        // BOTÓN RESUMEN
        // ==========================

        btnResumen = new JButton(
                "Resumen General"
        );

        btnResumen.setBounds(
                150,
                90,
                180,
                35
        );

        add(btnResumen);



        // ==========================
        // BOTÓN REPORTE COLABORADOR
        // ==========================

        btnReporteColaborador =
                new JButton(
                        "Reporte Colaborador"
                );


        btnReporteColaborador.setBounds(
                150,
                150,
                180,
                35
        );


        add(btnReporteColaborador);



        // ==========================
        // BOTÓN VOLVER
        // ==========================

        btnVolver =
                new JButton(
                        "Volver"
                );


        btnVolver.setBounds(
                150,
                230,
                180,
                35
        );


        add(btnVolver);


    }
    /**
     * Asigna las acciones
     * a los botones del formulario.
     */
    private void agregarEventos() {


        // ==============================
        // BOTÓN RESUMEN GENERAL
        // ==============================

        btnResumen.addActionListener(e -> mostrarResumenGeneral());



        // ==============================
        // BOTÓN REPORTE COLABORADOR
        // ==============================

        btnReporteColaborador.addActionListener(
                e -> generarReporteColaborador()
        );



        // ==============================
        // BOTÓN VOLVER
        // ==============================

        btnVolver.addActionListener(
                e -> volverMenu()
        );


    }
    /**
     * Muestra un resumen general
     * del estado actual del sistema.
     */
    private void mostrarResumenGeneral() {


        int colaboradores =
                controlReporte.obtenerTotalColaboradores();


        int asistencias =
                controlReporte.obtenerTotalAsistencias();


        int permisos =
                controlReporte.obtenerTotalPermisos();


        int pendientes =
                controlReporte.obtenerPermisosPendientes();


        int aprobados =
                controlReporte.obtenerPermisosAprobados();


        int denegados =
                controlReporte.obtenerPermisosDenegados();



        String mensaje =

                "===== RESUMEN GENERAL =====\n\n"

                + "Colaboradores registrados: "
                + colaboradores
                + "\n\n"

                + "Asistencias registradas: "
                + asistencias
                + "\n\n"

                + "Permisos registrados: "
                + permisos
                + "\n\n"

                + "Estados de permisos:\n"

                + "Pendientes: "
                + pendientes
                + "\n"

                + "Aprobados: "
                + aprobados
                + "\n"

                + "Denegados: "
                + denegados;



        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Resumen del Sistema",
                JOptionPane.INFORMATION_MESSAGE
        );


    }
    /**
     * Solicita un colaborador
     * y genera su reporte individual.
     */
    private void generarReporteColaborador() {


        // Solicita el ID del colaborador.
        String entrada =
                JOptionPane.showInputDialog(
                        this,
                        "Ingrese ID del colaborador:"
                );


        // Validar si el usuario canceló.
        if (entrada == null) {

            return;

        }


        try {


            // Convertir texto a número.
            int idColaborador =
                    Integer.parseInt(entrada);



            // Solicitar reporte al controlador.
            modelo.Reporte reporte =
                    controlReporte.generarReporteColaborador(
                            idColaborador
                    );



            // Validar existencia.
            if (reporte == null) {


                JOptionPane.showMessageDialog(
                        this,
                        "No existe un colaborador con ese ID.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );


                return;

            }



            // Mostrar información obtenida.
            mostrarInformacionReporte(reporte);



        } catch (NumberFormatException e) {


            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar un número válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }
    /**
     * Muestra la información
     * contenida en un objeto Reporte.
     *
     * @param reporte reporte generado.
     */
    private void mostrarInformacionReporte(
            modelo.Reporte reporte) {


        String mensaje =


                "===== REPORTE DEL COLABORADOR =====\n\n"


                + "Colaborador: "
                + reporte.getColaborador()
                        .obtenerNombreCompleto()


                + "\n\nAsistencias: "
                + reporte.getTotalAsistencias()


                + "\nTardanzas: "
                + reporte.getTotalTardanzas()


                + "\nInasistencias: "
                + reporte.getTotalInasistencias()


                + "\nPermisos: "
                + reporte.getTotalPermisos();



        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Reporte Individual",
                JOptionPane.INFORMATION_MESSAGE
        );

    }
    /**
     * Cierra el módulo actual
     * y retorna al menú principal.
     */
    private void volverMenu() {


        // Crear nuevamente el menú principal.
        FrmMenu menu = new FrmMenu();


        // Mostrar menú.
        menu.setVisible(true);


        // Cerrar ventana actual.
        dispose();

    }
    public static void main(String[] args) {

        FrmReporte frm = new FrmReporte();
        frm.setVisible(true);

    }

}