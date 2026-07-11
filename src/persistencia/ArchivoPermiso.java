package persistencia;

import enumeraciones.EstadoPermiso;
import modelo.Colaborador;
import modelo.Permiso;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la persistencia de los permisos
 * mediante un archivo CSV.
 */
public class ArchivoPermiso {

    private static final String RUTA_CARPETA = "archivos/";
    private static final String ARCHIVO = "permisos.csv";
    private static final String RUTA = RUTA_CARPETA + ARCHIVO;

    /**
     * Constructor.
     * Verifica la existencia de la carpeta de archivos.
     */
    public ArchivoPermiso() {

        File carpeta = new File(RUTA_CARPETA);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }

    /**
     * Guarda un permiso.
     */
    public void guardar(Permiso permiso) {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(RUTA, true))) {

            bw.write(
                    permiso.getIdPermiso() + ";" +
                    permiso.getFechaSolicitud() + ";" +
                    permiso.getMotivo() + ";" +
                    permiso.getEstado() + ";" +
                    permiso.getColaborador().getIdColaborador()
            );

            bw.newLine();

        } catch (IOException e) {

            System.out.println("Error al guardar permiso.");

        }

    }

    /**
     * Lee todos los permisos registrados.
     */
    public List<Permiso> leerTodos() {

        List<Permiso> lista = new ArrayList<>();

        File archivo = new File(RUTA);

        if (!archivo.exists()) {
            return lista;
        }

        ArchivoColaborador archivoColaborador =
                new ArchivoColaborador();

        try (BufferedReader br =
                     new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                Colaborador colaborador =
                        archivoColaborador.buscarPorId(
                                Integer.parseInt(datos[4]));

                Permiso permiso = new Permiso(

                        Integer.parseInt(datos[0]),

                        LocalDate.parse(datos[1]),

                        datos[2],

                        EstadoPermiso.valueOf(datos[3]),

                        colaborador

                );

                lista.add(permiso);

            }

        } catch (IOException e) {

            System.out.println("Error al leer permisos.");

        }

        return lista;

    }

    /**
     * Busca un permiso por su ID.
     */
    public Permiso buscarPorId(int id) {

        for (Permiso permiso : leerTodos()) {

            if (permiso.getIdPermiso() == id) {

                return permiso;

            }

        }

        return null;

    }

    /**
     * Actualiza un permiso existente.
     */
    public void actualizar(Permiso actualizado) {

        List<Permiso> lista = leerTodos();

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(RUTA))) {

            for (Permiso permiso : lista) {

                if (permiso.getIdPermiso()
                        == actualizado.getIdPermiso()) {

                    permiso = actualizado;

                }

                bw.write(
                        permiso.getIdPermiso() + ";" +
                        permiso.getFechaSolicitud() + ";" +
                        permiso.getMotivo() + ";" +
                        permiso.getEstado() + ";" +
                        permiso.getColaborador().getIdColaborador()
                );

                bw.newLine();

            }

        } catch (IOException e) {

            System.out.println("Error al actualizar permiso.");

        }

    }

    /**
     * Elimina un permiso.
     */
    public void eliminar(int id) {

        List<Permiso> lista = leerTodos();

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(RUTA))) {

            for (Permiso permiso : lista) {

                if (permiso.getIdPermiso() != id) {

                    bw.write(
                            permiso.getIdPermiso() + ";" +
                            permiso.getFechaSolicitud() + ";" +
                            permiso.getMotivo() + ";" +
                            permiso.getEstado() + ";" +
                            permiso.getColaborador().getIdColaborador()
                    );

                    bw.newLine();

                }

            }

        } catch (IOException e) {

            System.out.println("Error al eliminar permiso.");

        }

    }
    /**
     * Genera el siguiente ID disponible.
     *
     * @return siguiente ID consecutivo.
     */
    public int generarId() {

        // Obtener todos los permisos registrados.
        List<Permiso> lista = leerTodos();

        // Si aún no existen registros.
        if (lista.isEmpty()) {

            return 1;

        }

        // Buscar el mayor ID.
        int mayorId = 0;

        for (Permiso permiso : lista) {

            if (permiso.getIdPermiso() > mayorId) {

                mayorId = permiso.getIdPermiso();

            }

        }

        // Retornar el siguiente disponible.
        return mayorId + 1;

    }

}