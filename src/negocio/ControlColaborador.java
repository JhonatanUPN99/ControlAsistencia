package negocio;

import java.util.List;

import modelo.Colaborador;
import persistencia.ArchivoColaborador;

/**
 * Clase encargada de gestionar la lógica de negocio
 * relacionada con los colaboradores.
 */
public class ControlColaborador {

    // Acceso a la persistencia de colaboradores.
    private ArchivoColaborador archivoColaborador;

    /**
     * Constructor.
     * Inicializa la capa de persistencia.
     */
    public ControlColaborador() {

        archivoColaborador = new ArchivoColaborador();

    }

    /**
     * Registra un nuevo colaborador.
     *
     * @param nombres   Nombres del colaborador.
     * @param apellidos Apellidos del colaborador.
     * @param cargo     Cargo que desempeña.
     * @param area      Área donde trabaja.
     *
     * @return true si el registro fue exitoso.
     */
    public boolean registrarColaborador(String nombres,
                                        String apellidos,
                                        String cargo,
                                        String area) {

        // Validar que los datos obligatorios no estén vacíos.
        if (nombres.isBlank()
                || apellidos.isBlank()
                || cargo.isBlank()
                || area.isBlank()) {

            return false;

        }

        // Crear el objeto colaborador.
        Colaborador colaborador = new Colaborador(

                archivoColaborador.generarId(),

                nombres,

                apellidos,

                cargo,

                area

        );

        // Guardar en el archivo CSV.
        archivoColaborador.guardar(colaborador);

        return true;

    }

    /**
     * Devuelve todos los colaboradores registrados.
     *
     * @return Lista de colaboradores.
     */
    public List<Colaborador> listarColaboradores() {

        return archivoColaborador.leerTodos();

    }

    /**
     * Busca un colaborador por su identificador.
     *
     * @param id Identificador.
     * @return Colaborador encontrado o null.
     */
    public Colaborador buscarPorId(int id) {

        return archivoColaborador.buscarPorId(id);

    }


    /**
     * Elimina un colaborador.
     *
     * @param id Identificador del colaborador.
     */
    public void eliminarColaborador(int id) {

        archivoColaborador.eliminar(id);

    }
    
    /**
     * Actualiza la información de un colaborador.
     * @return true si la actualización fue exitosa.
     */
    public boolean actualizarColaborador(int id,
                                         String nombres,
                                         String apellidos,
                                         String cargo,
                                         String area) {

        // Verificar que exista el colaborador.
        Colaborador colaborador =
                archivoColaborador.buscarPorId(id);

        if (colaborador == null) {

            return false;

        }

        // Actualizar los datos del objeto.
        colaborador.setNombres(nombres);
        colaborador.setApellidos(apellidos);
        colaborador.setCargo(cargo);
        colaborador.setArea(area);

        // Guardar los cambios.
        archivoColaborador.actualizar(colaborador);

        return true;

    }

    /**
     * Obtiene un colaborador según su identificador.
     * @return Colaborador encontrado o null.
     */
    public Colaborador obtenerColaborador(int id) {

        return archivoColaborador.buscarPorId(id);

    }

}