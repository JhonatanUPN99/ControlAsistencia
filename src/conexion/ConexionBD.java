package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión entre el sistema
 * y la base de datos MySQL.
 */
public class ConexionBD {
	// ======================================
	// DATOS DE CONEXIÓN
	// ======================================

	// Dirección de la base de datos
	private static final String URL =
	        "jdbc:mysql://localhost:3306/ControlAsistenciaDB";

	// Usuario de MySQL
	private static final String USUARIO = "root";

	// Contraseña de MySQL
	private static final String PASSWORD = "Anubisd2.pr069";
	
	/**
	 * Abre una conexión con la base de datos.
	 *
	 * @return objeto Connection
	 */
	public static Connection getConnection() {

	    try {

	        return DriverManager.getConnection(
	                URL,
	                USUARIO,
	                PASSWORD
	        );

	    } catch (SQLException e) {

	        System.out.println(
	                "Error de conexión: " + e.getMessage()
	        );

	        return null;

	    }

	}

}
