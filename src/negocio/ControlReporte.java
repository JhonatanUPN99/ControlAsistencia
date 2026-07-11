package negocio;

import java.util.List;

import enumeraciones.EstadoAsistencia;

import modelo.Asistencia;
import modelo.Colaborador;
import modelo.Permiso;
import modelo.Reporte;
import persistencia.ArchivoReporte;


/**
 * Gestiona la lógica de negocio relacionada
 * con consultas y generación de reportes.
 */
public class ControlReporte {


    // ==================================
    // ATRIBUTOS
    // ==================================

    // Acceso a la capa de persistencia.
    private ArchivoReporte archivoReporte;


    // ==================================
    // CONSTRUCTOR
    // ==================================

    /**
     * Inicializa la conexión con la persistencia.
     */
    public ControlReporte() {

        archivoReporte = new ArchivoReporte();

    }
    /**
     * Obtiene todos los colaboradores registrados
     * para mostrarlos en los reportes.
     *
     * @return Lista de colaboradores.
     */
    public List<Colaborador> listarColaboradores() {

        return archivoReporte.generarReporteColaboradores();

    }
    /**
     * Obtiene todos los registros de asistencia.
     *
     * @return Lista de asistencias.
     */
    public List<Asistencia> listarAsistencias() {

        return archivoReporte.generarReporteAsistencias();

    }
    /**
     * Obtiene todos los permisos registrados.
     *
     * @return Lista de permisos.
     */
    public List<Permiso> listarPermisos() {

        return archivoReporte.generarReportePermisos();

    }
    /**
     * Obtiene la cantidad total de colaboradores registrados.
     *
     * @return Número total de colaboradores.
     */
    public int obtenerTotalColaboradores() {

        return archivoReporte.obtenerTotalColaboradores();

    }
    /**
     * Obtiene la cantidad total de asistencias registradas.
     *
     * @return Número total de asistencias.
     */
    public int obtenerTotalAsistencias() {

        return archivoReporte.obtenerTotalAsistencias();

    }
    /**
     * Obtiene la cantidad total de permisos registrados.
     *
     * @return Número total de permisos.
     */
    public int obtenerTotalPermisos() {

        return archivoReporte.obtenerTotalPermisos();

    }
    /**
     * Obtiene la cantidad de permisos pendientes.
     *
     * @return Número de permisos pendientes.
     */
    public int obtenerPermisosPendientes() {

        return archivoReporte.obtenerPermisosPendientes();

    }
    /**
     * Obtiene la cantidad de permisos aprobados.
     *
     * @return Número de permisos aprobados.
     */
    public int obtenerPermisosAprobados() {

        return archivoReporte.obtenerPermisosAprobados();

    }
    /**
     * Obtiene la cantidad de permisos rechazados.
     *
     * @return Número de permisos denegados.
     */
    public int obtenerPermisosDenegados() {

        return archivoReporte.obtenerPermisosDenegados();

    }
    /**
     * Genera un reporte individual de un colaborador.
     *
     * @param idColaborador Identificador del colaborador.
     * @return Reporte generado.
     */
    public Reporte generarReporteColaborador(int idColaborador) {


        // Buscar colaborador seleccionado.
        Colaborador colaborador = null;


        for (Colaborador c : listarColaboradores()) {


            if (c.getIdColaborador() == idColaborador) {

                colaborador = c;
                break;

            }

        }


        // Si no existe retorna null.
        if (colaborador == null) {

            return null;

        }


        int asistencias = 0;
        int tardanzas = 0;
        int inasistencias = 0;
        int permisos = 0;


        // Analizar asistencias del colaborador.
        for (Asistencia asistencia : listarAsistencias()) {

            // Verifica que pertenezca al colaborador buscado.
            if (asistencia.getColaborador()
                    .getIdColaborador() == idColaborador) {


                switch(asistencia.getEstado()) {


                    case PRESENTE:

                        asistencias++;
                        break;


                    case TARDANZA:

                        tardanzas++;
                        break;


                    case FALTA:

                        inasistencias++;
                        break;

                }

            }

        }


        // Contar permisos del colaborador.
        for (Permiso permiso : listarPermisos()) {


            if (permiso.getColaborador()
                    .getIdColaborador() == idColaborador) {


                permisos++;

            }

        }


        // Crear objeto reporte.
        return new Reporte(

                1,

                colaborador,

                asistencias,

                tardanzas,

                inasistencias,

                permisos

        );

    }
    /**
     * Busca un colaborador mediante su identificador.
     *
     * @param idColaborador ID del colaborador.
     * @return Colaborador encontrado o null.
     */
    public Colaborador buscarColaborador(int idColaborador) {


        // Recorre los colaboradores registrados.
        for (Colaborador colaborador : listarColaboradores()) {


            // Compara el identificador buscado.
            if (colaborador.getIdColaborador() == idColaborador) {


                return colaborador;

            }

        }


        // Si no existe devuelve null.
        return null;

    }
    /**
     * Genera reportes para todos los colaboradores registrados.
     *
     * @return Lista de reportes.
     */
    public List<Reporte> generarTodosLosReportes() {


        List<Reporte> reportes = new java.util.ArrayList<>();


        int idReporte = 1;


        // Recorre todos los colaboradores.
        for (Colaborador colaborador : listarColaboradores()) {


            Reporte reporte =
                    generarReporteColaborador(
                            colaborador.getIdColaborador()
                    );


            if (reporte != null) {


                reporte.setIdReporte(idReporte);

                reportes.add(reporte);

                idReporte++;

            }

        }


        return reportes;

    }

}