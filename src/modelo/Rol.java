package modelo;

/**
 * Representa el rol que posee un usuario dentro del sistema.
 * Ejemplo: Administrador o Empleado.
 */
public class Rol {

    // Identificador único del rol.
    private int idRol;

    // Nombre del rol.
    private String nombreRol;

    /**
     * Constructor de la clase Rol.
     *
     * @param idRol Identificador del rol.
     * @param nombreRol Nombre del rol.
     */
    public Rol(int idRol, String nombreRol) {

        this.idRol = idRol;
        this.nombreRol = nombreRol;

    }

    /**
     * Devuelve el identificador del rol.
     */
    public int getIdRol() {

        return idRol;

    }

    /**
     * Modifica el identificador del rol.
     */
    public void setIdRol(int idRol) {

        this.idRol = idRol;

    }

    /**
     * Devuelve el nombre del rol.
     */
    public String getNombreRol() {

        return nombreRol;

    }

    /**
     * Modifica el nombre del rol.
     */
    public void setNombreRol(String nombreRol) {

        this.nombreRol = nombreRol;

    }

    /**
     * Devuelve el nombre del rol cuando el objeto
     * se imprime en consola o en componentes Swing.
     */
    @Override
    public String toString() {

        return nombreRol;

    }

}