package negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.Rol;
import modelo.Usuario;

/**
 * Gestiona el inicio y cierre de sesión de los usuarios
 * del sistema.
 */
public class Autenticacion {

    // Lista de usuarios disponibles en el sistema.
    private List<Usuario> usuarios;

    // Usuario que actualmente ha iniciado sesión.
    private Usuario usuarioActual;

    /**
     * Constructor.
     * Inicializa la lista de usuarios y carga el
     * usuario administrador por defecto.
     */
    public Autenticacion() {

        usuarios = new ArrayList<>();

        cargarUsuarios();

    }

    /**
     * Carga los usuarios iniciales del sistema.
     * Para el Sprint 1 únicamente se registra
     * un administrador.
     */
    private void cargarUsuarios() {

        // Crear el rol administrador.
        Rol administrador = new Rol(

                1,

                "Administrador"

        );

        // Crear el usuario administrador.
        Usuario admin = new Usuario(

                1,

                "admin",

                "admin123",

                administrador

        );

        // Agregar el usuario a la lista.
        usuarios.add(admin);

    }

    /**
     * Valida las credenciales del usuario.
     *
     * @param nombreUsuario Usuario ingresado.
     * @param contrasena Contraseña ingresada.
     * @return true si las credenciales son válidas.
     */
    public boolean iniciarSesion(String nombreUsuario,
                                 String contrasena) {

        // Recorrer todos los usuarios registrados.
        for (Usuario usuario : usuarios) {

            // Comparar usuario y contraseña.
            if (usuario.getNombreUsuario().equals(nombreUsuario)
                    &&
                usuario.getContrasena().equals(contrasena)) {

                // Guardar el usuario autenticado.
                usuarioActual = usuario;

                return true;

            }

        }

        // Credenciales incorrectas.
        return false;

    }

    /**
     * Cierra la sesión actual.
     */
    public void cerrarSesion() {

        usuarioActual = null;

    }

    /**
     * Devuelve el usuario autenticado.
     *
     * @return Usuario actual o null.
     */
    public Usuario getUsuarioActual() {

        return usuarioActual;

    }

    /**
     * Indica si existe una sesión iniciada.
     *
     * @return true si hay un usuario autenticado.
     */
    public boolean haySesionActiva() {

        return usuarioActual != null;

    }

}