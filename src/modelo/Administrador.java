package modelo;

/**
 * Representa a un administrador del sistema.
 *
 * Hereda toda la información de un colaborador
 * y añade un nivel de acceso para controlar
 * los permisos administrativos.
 */
public class Administrador extends Colaborador {

    // Nivel de acceso del administrador
    private int nivelAcceso;

    /**
     * Constructor vacío.
     */
    public Administrador() {

    }

    /**
     * Constructor con parámetros.
     *
     * @param idColaborador Identificador del administrador.
     * @param nombres Nombre(s).
     * @param apellidos Apellidos.
     * @param cargo Cargo.
     * @param area Área.
     * @param nivelAcceso Nivel de acceso.
     */
    public Administrador(int idColaborador,
                         String nombres,
                         String apellidos,
                         String cargo,
                         String area,
                         int nivelAcceso) {

        // Inicializa los atributos heredados
        super(idColaborador, nombres, apellidos, cargo, area);

        // Inicializa el atributo propio
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    /**
     * Devuelve la información del administrador.
     */
    @Override
    public String toString() {

        return super.toString() +
                ", nivelAcceso=" + nivelAcceso;

    }

}