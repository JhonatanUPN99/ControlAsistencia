package persistencia;

import modelo.Asistencia;
import modelo.Colaborador;
import modelo.Permiso;

import java.util.List;

import enumeraciones.EstadoPermiso;

/**
 * Genera reportes utilizando la información
 * almacenada en los archivos del sistema.
 */
public class ArchivoReporte {

    private ArchivoAsistencia archivoAsistencia;
    private ArchivoPermiso archivoPermiso;
 // Acceso a los colaboradores registrados.
    private ArchivoColaborador archivoColaborador;

    public ArchivoReporte() {

        archivoAsistencia = new ArchivoAsistencia();
        archivoPermiso = new ArchivoPermiso();
        archivoColaborador = new ArchivoColaborador();

    }
    /**
     * Obtiene todos los colaboradores registrados.
     *
     * regresa Lista de colaboradores.
     */
    public List<Colaborador> obtenerReporteColaboradores() {

        return archivoColaborador.leerTodos();

    }
    /**
     * Devuelve la cantidad total de colaboradores.
     *
     * @return Número de colaboradores registrados.
     */
    public int obtenerTotalColaboradores() {

        return archivoColaborador.leerTodos().size();

    }
    /**
     * Obtiene todas las asistencias.
     */
    public List<Asistencia> obtenerReporteAsistencias() {

        return archivoAsistencia.leerTodos();

    }
    /**
     * Devuelve la cantidad total de asistencias registradas.
     *
     * @return Número de asistencias.
     */
    public int obtenerTotalAsistencias() {

        return archivoAsistencia.leerTodos().size();

    }
    /**
     * Obtiene todos los permisos.
     */
    public List<Permiso> obtenerReportePermisos() {

        return archivoPermiso.leerTodos();

    }
    /**
     * Devuelve la cantidad total de permisos registrados.
     *
     * @return Número de permisos.
     */
    public int obtenerTotalPermisos() {

        return archivoPermiso.leerTodos().size();

    }
    /**
     * Cuenta los permisos que se encuentran pendientes.
     *
     * @return Cantidad de permisos pendientes.
     */
    public int obtenerPermisosPendientes() {

        int contador = 0;

        // Recorre todos los permisos registrados.
        for (Permiso permiso : archivoPermiso.leerTodos()) {

            // Verifica si el estado actual es PENDIENTE.
            if (permiso.getEstado() == EstadoPermiso.PENDIENTE) {

                contador++;

            }

        }

        return contador;

    }
    /**
     * Cuenta los permisos aprobados por el supervisor.
     *
     * @return Cantidad de permisos aprobados.
     */
    public int obtenerPermisosAprobados() {

        int contador = 0;

        // Recorre todos los permisos almacenados.
        for (Permiso permiso : archivoPermiso.leerTodos()) {

            // Valida el estado aprobado.
            if (permiso.getEstado() == EstadoPermiso.APROBADO) {

                contador++;

            }

        }

        return contador;

    }
    /**
     * Cuenta los permisos rechazados.
     *
     * @return Cantidad de permisos denegados.
     */
    public int obtenerPermisosDenegados() {

        int contador = 0;

        // Recorre todos los permisos registrados.
        for (Permiso permiso : archivoPermiso.leerTodos()) {

            // Valida el estado denegado.
            if (permiso.getEstado() == EstadoPermiso.DENEGADO) {

                contador++;

            }

        }

        return contador;

    }
    /**
     * Obtiene todos los registros de asistencia
     * para generar consultas o reportes.
     *
     * @return Lista completa de asistencias.
     */
    public List<Asistencia> generarReporteAsistencias() {

        // Obtiene la información almacenada
        // desde el archivo CSV de asistencias.
        return archivoAsistencia.leerTodos();

    }
    /**
     * Obtiene todos los permisos registrados
     * para consultas administrativas.
     *
     * @return Lista completa de permisos.
     */
    public List<Permiso> generarReportePermisos() {

        // Obtiene la información almacenada
        // desde el archivo CSV de permisos.
        return archivoPermiso.leerTodos();

    }
    /**
     * Obtiene todos los colaboradores
     * registrados en el sistema.
     *
     * @return Lista de colaboradores.
     */
    public List<Colaborador> generarReporteColaboradores() {

        return archivoColaborador.leerTodos();

    }
}