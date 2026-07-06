package persistencia;

import modelo.Colaborador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la persistencia de los colaboradores
 * mediante un archivo CSV.
 */
public class ArchivoColaborador {

    // Carpeta donde se almacenan todos los archivos del sistema.
    private static final String RUTA_CARPETA = "archivos/";

    // Nombre del archivo.
    private static final String ARCHIVO = "colaboradores.csv";

    // Ruta completa del archivo.
    private static final String RUTA = RUTA_CARPETA + ARCHIVO;

    /**
     * Constructor.
     * Verifica que exista la carpeta "archivos".
     */
    public ArchivoColaborador() {

        File carpeta = new File(RUTA_CARPETA);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    /**
     * Guarda un colaborador al final del archivo.
     */
    public void guardar(Colaborador colaborador) {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(RUTA, true))) {

            bw.write(
                    colaborador.getIdColaborador() + ";" +
                    colaborador.getNombres() + ";" +
                    colaborador.getApellidos() + ";" +
                    colaborador.getCargo() + ";" +
                    colaborador.getArea()
            );

            bw.newLine();

        } catch (IOException e) {

            System.out.println("Error al guardar colaborador.");

        }

    }

    /**
     * Lee todos los colaboradores registrados.
     */
    public List<Colaborador> leerTodos() {

        List<Colaborador> lista = new ArrayList<>();

        File archivo = new File(RUTA);

        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader br =
                     new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                Colaborador colaborador = new Colaborador(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4]
                );

                lista.add(colaborador);

            }

        } catch (IOException e) {

            System.out.println("Error al leer colaboradores.");

        }

        return lista;

    }

    /**
     * Busca un colaborador por su ID.
     */
    public Colaborador buscarPorId(int id) {

        for (Colaborador colaborador : leerTodos()) {

            if (colaborador.getIdColaborador() == id) {

                return colaborador;

            }

        }

        return null;

    }

    /**
     * Actualiza la información de un colaborador.
     */
    public void actualizar(Colaborador actualizado) {

        List<Colaborador> lista = leerTodos();

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(RUTA))) {

            for (Colaborador colaborador : lista) {

                if (colaborador.getIdColaborador()
                        == actualizado.getIdColaborador()) {

                    colaborador = actualizado;

                }

                bw.write(
                        colaborador.getIdColaborador() + ";" +
                        colaborador.getNombres() + ";" +
                        colaborador.getApellidos() + ";" +
                        colaborador.getCargo() + ";" +
                        colaborador.getArea()
                );

                bw.newLine();

            }

        } catch (IOException e) {

            System.out.println("Error al actualizar colaborador.");

        }

    }

    /**
     * Elimina un colaborador por su ID.
     */
    public void eliminar(int id) {

        List<Colaborador> lista = leerTodos();

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(RUTA))) {

            for (Colaborador colaborador : lista) {

                if (colaborador.getIdColaborador() != id) {

                    bw.write(
                            colaborador.getIdColaborador() + ";" +
                            colaborador.getNombres() + ";" +
                            colaborador.getApellidos() + ";" +
                            colaborador.getCargo() + ";" +
                            colaborador.getArea()
                    );

                    bw.newLine();

                }

            }

        } catch (IOException e) {

            System.out.println("Error al eliminar colaborador.");

        }

    }
    
    /**
     * Genera el siguiente ID disponible.
     *
     * @return ID consecutivo.
     */
    public int generarId() {

        List<Colaborador> lista = leerTodos();

        if (lista.isEmpty()) {
            return 1;
        }

        int mayorId = 0;

        for (Colaborador colaborador : lista) {

            if (colaborador.getIdColaborador() > mayorId) {
                mayorId = colaborador.getIdColaborador();
            }

        }

        return mayorId + 1;

    }

}