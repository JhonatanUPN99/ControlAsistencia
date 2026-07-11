package principal;

import java.sql.Connection;

import conexion.ConexionBD;

/**
 * Clase temporal para verificar
 * la conexión con MySQL.
 */
public class PruebaConexion {

    public static void main(String[] args) {

        // Intentar establecer la conexión
        Connection conexion = ConexionBD.getConnection();

        // Verificar el resultado
        if (conexion != null) {

            System.out.println(
                    "Conexión establecida correctamente con MySQL."
            );

        } else {

            System.out.println(
                    "No fue posible establecer la conexión."
            );

        }

    }

}