package modelo;

/**
 * Representa un usuario autorizado para acceder
 * al sistema de control de asistencia.
 */
public class Usuario {

    // Identificador único del usuario.
    private int idUsuario;

    // Nombre utilizado para iniciar sesión.
    private String nombreUsuario;

    // Contraseña del usuario.
    private String contrasena;

    // Rol asociado al usuario.
    private Rol rol;

    /**
     * Constructor de la clase Usuario.
     *
     * @param idUsuario Identificador del usuario.
     * @param nombreUsuario Nombre de inicio de sesión.
     * @param contrasena Contraseña del usuario.
     * @param rol Rol asignado al usuario.
     */
    public Usuario(int idUsuario,
                   String nombreUsuario,
                   String contrasena,
                   Rol rol) {

        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;

    }

    /**
     * Devuelve el identificador del usuario.
     */
    public int getIdUsuario() {

        return idUsuario;

    }

    /**
     * Modifica el identificador del usuario.
     */
    public void setIdUsuario(int idUsuario) {

        this.idUsuario = idUsuario;

    }

    /**
     * Devuelve el nombre de usuario.
     */
    public String getNombreUsuario() {

        return nombreUsuario;

    }

    /**
     * Modifica el nombre de usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {

        this.nombreUsuario = nombreUsuario;

    }

    /**
     * Devuelve la contraseña del usuario.
     */
    public String getContrasena() {

        return contrasena;

    }

    /**
     * Modifica la contraseña.
     */
    public void setContrasena(String contrasena) {

        this.contrasena = contrasena;

    }

    /**
     * Devuelve el rol del usuario.
     */
    public Rol getRol() {

        return rol;

    }

    /**
     * Modifica el rol asignado.
     */
    public void setRol(Rol rol) {

        this.rol = rol;

    }

    /**
     * Devuelve el nombre de usuario cuando el objeto
     * se imprime en consola o en un componente Swing.
     */
    @Override
    public String toString() {

        return nombreUsuario;

    }

}