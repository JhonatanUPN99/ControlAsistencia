package principal;

import dao.ColaboradorDAO;
import modelo.Colaborador;


/**
 * Clase temporal para validar
 * las operaciones CRUD del DAO.
 */
public class PruebaColaboradorDAO {


    public static void main(String[] args) {


        // ==========================================
        // CREAR OBJETO DAO
        // ==========================================
        ColaboradorDAO dao =
                new ColaboradorDAO();



        // ==========================================
        // 1. INSERTAR COLABORADOR
        // ==========================================

        Colaborador colaborador =
                new Colaborador();


        colaborador.setNombres("Carlos");

        colaborador.setApellidos("Ramirez");

        colaborador.setCargo("Analista");

        colaborador.setArea("Sistemas");


        boolean insertado =
                dao.insertar(colaborador);


        System.out.println(
                "Insertado: "
                + insertado);



        // ==========================================
        // 2. LISTAR COLABORADORES
        // ==========================================

        System.out.println(
                "\nLISTA DE COLABORADORES");


        for(Colaborador c : dao.listar()) {


            System.out.println(c);

        }
     // ==========================================
     // 3. ACTUALIZAR COLABORADOR
     // ==========================================

     // Buscar colaborador registrado
     Colaborador encontrado =
             dao.buscarPorId(1);


     // Validar que exista
     if(encontrado != null) {


         // Modificar información
         encontrado.setCargo("Administrador");


         encontrado.setArea("Recursos Humanos");


         // Ejecutar actualización
         boolean actualizado =
                 dao.actualizar(encontrado);


         System.out.println(
                 "Actualizado: "
                 + actualizado);

     }
     // ==========================================
     // 4. ELIMINAR COLABORADOR
     // ==========================================

     	boolean eliminado =
          dao.eliminar(1);


     	System.out.println(
          "Eliminado: "
          + eliminado);


    }

}