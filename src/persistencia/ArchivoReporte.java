package persistencia;

import modelo.Asistencia;
import modelo.Permiso;

import java.util.List;

/**
 * Genera reportes utilizando la información
 * almacenada en los archivos del sistema.
 */
public class ArchivoReporte {

    private ArchivoAsistencia archivoAsistencia;
    private ArchivoPermiso archivoPermiso;

    public ArchivoReporte() {

        archivoAsistencia = new ArchivoAsistencia();
        archivoPermiso = new ArchivoPermiso();

    }

    /**
     * Obtiene todas las asistencias.
     */
    public List<Asistencia> obtenerReporteAsistencias() {

        return archivoAsistencia.leerTodos();

    }

    /**
     * Obtiene todos los permisos.
     */
    public List<Permiso> obtenerReportePermisos() {

        return archivoPermiso.leerTodos();

    }

}