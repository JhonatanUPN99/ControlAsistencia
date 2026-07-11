package dao;

import conexion.ConexionBD;
import modelo.Colaborador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * Gestiona las operaciones CRUD
 * de los colaboradores en MySQL.
 */
public class ColaboradorDAO {
	/**
	 * Inserta un colaborador en la base de datos.
	 *
	 * @param colaborador Objeto que será almacenado.
	 * @return true si la operación fue exitosa.
	 */
	public boolean insertar(Colaborador colaborador) {

	    // ==========================================
	    // SENTENCIA SQL
	    // ==========================================
	    String sql =
	            "INSERT INTO colaboradores "
	          + "(nombres, apellidos, cargo, area) "
	          + "VALUES (?, ?, ?, ?)";

	    // ==========================================
	    // CONEXIÓN Y EJECUCIÓN
	    // ==========================================
	    try (Connection conexion = ConexionBD.getConnection();

	         PreparedStatement ps =
	                 conexion.prepareStatement(sql)) {

	        // Asignar valores a cada parámetro
	        ps.setString(1, colaborador.getNombres());

	        ps.setString(2, colaborador.getApellidos());

	        ps.setString(3, colaborador.getCargo());

	        ps.setString(4, colaborador.getArea());

	        // Ejecutar el INSERT
	        int filas = ps.executeUpdate();

	        return filas > 0;

	    } catch (SQLException e) {

	        System.out.println(
	                "Error al insertar colaborador: "
	                        + e.getMessage());

	        return false;

	    }

	}
	/**
	 * Obtiene todos los colaboradores registrados
	 * en la base de datos.
	 *
	 * @return Lista de colaboradores.
	 */
	public List<Colaborador> listar() {

	    // Lista donde se almacenarán los registros obtenidos
	    List<Colaborador> lista = new ArrayList<>();

	    // Consulta SQL
	    String sql = "SELECT * FROM colaboradores";

	    try (Connection conexion = ConexionBD.getConnection();

	         PreparedStatement ps =
	                 conexion.prepareStatement(sql);

	         ResultSet rs = ps.executeQuery()) {

	        // Recorrer todos los registros obtenidos
	        while (rs.next()) {

	            Colaborador colaborador = new Colaborador();

	            colaborador.setIdColaborador(
	                    rs.getInt("id_colaborador"));

	            colaborador.setNombres(
	                    rs.getString("nombres"));

	            colaborador.setApellidos(
	                    rs.getString("apellidos"));

	            colaborador.setCargo(
	                    rs.getString("cargo"));

	            colaborador.setArea(
	                    rs.getString("area"));

	            lista.add(colaborador);

	        }

	    } catch (SQLException e) {

	        System.out.println(
	                "Error al listar colaboradores: "
	                        + e.getMessage());

	    }

	    return lista;

	}
	/**
	 * Busca un colaborador mediante su identificador.
	 *
	 * @param id Identificador del colaborador.
	 * @return Colaborador encontrado o null si no existe.
	 */
	public Colaborador buscarPorId(int id) {

	    // ==========================================
	    // CONSULTA SQL PARAMETRIZADA
	    // ==========================================
	    String sql =
	            "SELECT * FROM colaboradores "
	          + "WHERE id_colaborador = ?";


	    // Variable donde se almacenará el resultado
	    Colaborador colaborador = null;


	    try (Connection conexion = ConexionBD.getConnection();

	         PreparedStatement ps =
	                 conexion.prepareStatement(sql)) {


	        // ==========================================
	        // ASIGNAR ID A LA CONSULTA
	        // ==========================================
	        ps.setInt(1, id);


	        // Ejecutar consulta
	        ResultSet rs = ps.executeQuery();


	        // ==========================================
	        // CREAR OBJETO SI EXISTE REGISTRO
	        // ==========================================
	        if (rs.next()) {


	            colaborador = new Colaborador();


	            colaborador.setIdColaborador(
	                    rs.getInt("id_colaborador"));


	            colaborador.setNombres(
	                    rs.getString("nombres"));


	            colaborador.setApellidos(
	                    rs.getString("apellidos"));


	            colaborador.setCargo(
	                    rs.getString("cargo"));


	            colaborador.setArea(
	                    rs.getString("area"));

	        }


	    } catch (SQLException e) {


	        System.out.println(
	                "Error al buscar colaborador: "
	                + e.getMessage());

	    }


	    return colaborador;

	}
	/**
	 * Actualiza la información de un colaborador existente.
	 *
	 * @param colaborador Objeto con los nuevos datos.
	 * @return true si la actualización fue correcta.
	 */
	public boolean actualizar(Colaborador colaborador) {


	    // ==========================================
	    // SENTENCIA SQL DE ACTUALIZACIÓN
	    // ==========================================
	    String sql =
	            "UPDATE colaboradores SET "
	          + "nombres = ?, "
	          + "apellidos = ?, "
	          + "cargo = ?, "
	          + "area = ? "
	          + "WHERE id_colaborador = ?";


	    try (Connection conexion =
	                 ConexionBD.getConnection();

	         PreparedStatement ps =
	                 conexion.prepareStatement(sql)) {


	        // ==========================================
	        // ASIGNAR NUEVOS VALORES
	        // ==========================================

	        ps.setString(1,
	                colaborador.getNombres());


	        ps.setString(2,
	                colaborador.getApellidos());


	        ps.setString(3,
	                colaborador.getCargo());


	        ps.setString(4,
	                colaborador.getArea());


	        // Identificar qué registro actualizar
	        ps.setInt(5,
	                colaborador.getIdColaborador());



	        // ==========================================
	        // EJECUTAR UPDATE
	        // ==========================================

	        int filas =
	                ps.executeUpdate();


	        return filas > 0;



	    } catch (SQLException e) {


	        System.out.println(
	                "Error al actualizar colaborador: "
	                + e.getMessage());


	        return false;

	    }

	}
	/**
	 * Elimina un colaborador de la base de datos.
	 *
	 * @param id Identificador del colaborador.
	 * @return true si la eliminación fue correcta.
	 */
	public boolean eliminar(int id) {


	    // ==========================================
	    // SENTENCIA SQL DE ELIMINACIÓN
	    // ==========================================
	    String sql =
	            "DELETE FROM colaboradores "
	          + "WHERE id_colaborador = ?";


	    try (Connection conexion =
	                 ConexionBD.getConnection();

	         PreparedStatement ps =
	                 conexion.prepareStatement(sql)) {


	        // ==========================================
	        // ASIGNAR ID DEL REGISTRO A ELIMINAR
	        // ==========================================

	        ps.setInt(1, id);


	        // ==========================================
	        // EJECUTAR DELETE
	        // ==========================================

	        int filas =
	                ps.executeUpdate();


	        return filas > 0;



	    } catch (SQLException e) {


	        System.out.println(
	                "Error al eliminar colaborador: "
	                + e.getMessage());


	        return false;

	    }

	}

}