package negocio;

import java.time.LocalDate;
import java.util.List;

import enumeraciones.EstadoPermiso;
import modelo.Colaborador;
import modelo.Permiso;
import persistencia.ArchivoColaborador;
import persistencia.ArchivoPermiso;

/**
 * Gestiona la lógica de negocio relacionada
 * con las solicitudes de permisos laborales.
 */
public class ControlPermiso {

    // Acceso a la persistencia de permisos.
    private ArchivoPermiso archivoPermiso;

    // Acceso a la persistencia de colaboradores.
    private ArchivoColaborador archivoColaborador;

    /**
     * Constructor.
     * Inicializa las clases de persistencia.
     */
    public ControlPermiso() {

        archivoPermiso = new ArchivoPermiso();
        archivoColaborador = new ArchivoColaborador();

    }

    /**
     * Registra una nueva solicitud de permiso.
     *
     * @param idColaborador Identificador del colaborador.
     * @param fechaSolicitud Fecha de la solicitud.
     * @param motivo Motivo del permiso.
     * @return true si el registro fue exitoso.
     */
    public boolean registrar(int idColaborador,
                             LocalDate fechaSolicitud,
                             String motivo) {

        // Verificar que el colaborador exista.
        Colaborador colaborador =
                archivoColaborador.buscarPorId(idColaborador);

        if (colaborador == null) {

            return false;

        }

        // Validar que el motivo no esté vacío.
        if (motivo == null || motivo.isBlank()) {

            return false;

        }

        // Crear el objeto permiso con estado inicial PENDIENTE.
        Permiso permiso = new Permiso(

        		archivoPermiso.generarId(),

                fechaSolicitud,

                motivo,

                EstadoPermiso.PENDIENTE,

                colaborador

        );

        // Guardar el permiso.
        archivoPermiso.guardar(permiso);

        return true;

    }

    /**
     * Aprueba una solicitud de permiso.
     *
     * @param id Identificador del permiso.
     * @return true si fue aprobado.
     */
    public boolean aprobar(int id) {

        Permiso permiso = archivoPermiso.buscarPorId(id);

        if (permiso == null) {

            return false;

        }

        permiso.setEstado(EstadoPermiso.APROBADO);

        archivoPermiso.actualizar(permiso);

        return true;

    }

    /**
     * Deniega una solicitud de permiso.
     *
     * @param id Identificador del permiso.
     * @return true si fue denegado.
     */
    public boolean denegar(int id) {

        Permiso permiso = archivoPermiso.buscarPorId(id);

        if (permiso == null) {

            return false;

        }

        permiso.setEstado(EstadoPermiso.DENEGADO);

        archivoPermiso.actualizar(permiso);

        return true;

    }

    /**
     * Busca un permiso por su ID.
     *
     * @param id Identificador del permiso.
     * @return Permiso encontrado o null.
     */
    public Permiso buscar(int id) {

        return archivoPermiso.buscarPorId(id);

    }

    /**
     * Devuelve todos los permisos registrados.
     *
     * @return Lista de permisos.
     */
    public List<Permiso> listar() {

        return archivoPermiso.leerTodos();

    }
    /**
     * Actualiza un permiso existente.
     *
     * @param permiso Permiso actualizado.
     */
    public void actualizar(Permiso permiso) {

        archivoPermiso.actualizar(permiso);

    }
    /**
     * Elimina un permiso existente.
     *
     * @param id Identificador del permiso.
     */
    public void eliminar(int id) {

        archivoPermiso.eliminar(id);

    }

}