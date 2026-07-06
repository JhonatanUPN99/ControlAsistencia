package persistencia;

import enumeraciones.EstadoAsistencia;
import modelo.Asistencia;
import modelo.Colaborador;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
/**
 * Gestiona la persistencia de las asistencias
 * mediante un archivo CSV.
 */
public class ArchivoAsistencia {

    private static final String RUTA_CARPETA = "archivos/";
    private static final String ARCHIVO = "asistencias.csv";
    private static final String RUTA = RUTA_CARPETA + ARCHIVO;

    /**
     * Constructor.
     * Verifica que exista la carpeta de almacenamiento.
     */
    public ArchivoAsistencia() {

        File carpeta = new File(RUTA_CARPETA);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

    }
    
    private static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Guarda un registro de asistencia.
     * Solo almacena el ID del colaborador.
     */
    public void guardar(Asistencia asistencia) {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(RUTA, true))) {

            // ===============================
            // VALIDACIÓN DE HORA DE INGRESO
            // ===============================
        	DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
        	
        	String horaIngreso = (asistencia.getHoraIngreso() == null)
        	        ? ""
        	        : formato.format(asistencia.getHoraIngreso());

            // ===============================
            // VALIDACIÓN DE HORA DE SALIDA
            // ===============================
        	String horaSalida = (asistencia.getHoraSalida() == null)
        	        ? ""
        	        : formato.format(asistencia.getHoraSalida());

            // ===============================
            // ESCRITURA SEGURA EN CSV
            // ===============================
            bw.write(
            		
            		asistencia.getIdAsistencia() + ";" +
            		        asistencia.getFecha() + ";" +
            		        horaIngreso + ";" +
            		        horaSalida + ";" +
            		        asistencia.getEstado() + ";" +
            		        asistencia.getColaborador().getIdColaborador()
            );

            bw.newLine();

        } catch (IOException e) {

            System.out.println("Error al guardar asistencia: " + e.getMessage());

        }
    }
   

    /**
     * Lee todas las asistencias registradas.
     */
    public List<Asistencia> leerTodos() {

        List<Asistencia> lista = new ArrayList<>();

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
                                Integer.parseInt(datos[5]));

                LocalTime horaIngreso = null;
                LocalTime horaSalida = null;

                // VALIDAR hora ingreso
                if (datos[2] != null &&
                        !datos[2].equals("null") &&
                        !datos[2].isBlank()) {

                    horaIngreso = LocalTime.parse(datos[2]);
                }

                // VALIDAR hora salida
                if (datos[3] != null &&
                        !datos[3].equals("null") &&
                        !datos[3].isBlank()) {

                    horaSalida = LocalTime.parse(datos[3]);
                }

                // CREAR OBJETO CON VALORES SEGUROS
                Asistencia asistencia = new Asistencia(

                        Integer.parseInt(datos[0]),

                        LocalDate.parse(datos[1]),

                        horaIngreso,

                        horaSalida,

                        EstadoAsistencia.valueOf(datos[4]),

                        colaborador

                );

                lista.add(asistencia);

            }

        } catch (IOException e) {

            System.out.println("Error al leer asistencias.");

        }

        return lista;

    }

    /**
     * Busca la asistencia de un colaborador
     * en una fecha determinada.
     */
    public Asistencia buscarPorColaboradorYFecha(
            int idColaborador,
            LocalDate fecha) {

        List<Asistencia> lista = leerTodos();

        for (Asistencia asistencia : lista) {

            if (asistencia != null
                    && asistencia.getColaborador() != null
                    && asistencia.getFecha() != null
                    && asistencia.getColaborador().getIdColaborador() == idColaborador
                    && asistencia.getFecha().equals(fecha)) {

                return asistencia;

            }
        }

        return null;
    }

    /**
     * Actualiza una asistencia existente.
     */
    public void actualizar(Asistencia actualizada) {

        List<Asistencia> lista = leerTodos();

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(RUTA))) {

            for (Asistencia asistencia : lista) {

                if (asistencia.getIdAsistencia()
                        == actualizada.getIdAsistencia()) {

                    asistencia = actualizada;

                }
                String horaIngreso = (asistencia.getHoraIngreso() == null)
                        ? ""
                        : FORMATO_HORA.format(asistencia.getHoraIngreso());

                String horaSalida = (asistencia.getHoraSalida() == null)
                        ? ""
                        : FORMATO_HORA.format(asistencia.getHoraSalida());

                bw.write(
                        asistencia.getIdAsistencia() + ";" +
                        asistencia.getFecha() + ";" +
                        horaIngreso + ";" +
                        horaSalida + ";" +
                        asistencia.getEstado() + ";" +
                        asistencia.getColaborador().getIdColaborador()
                );

                bw.newLine();

            }

        } catch (IOException e) {

            System.out.println("Error al actualizar asistencia.");

        }

    }

    /**
     * Elimina una asistencia por ID.
     */
    public void eliminar(int id) {

        List<Asistencia> lista = leerTodos();

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(RUTA))) {

            for (Asistencia asistencia : lista) {

                if (asistencia.getIdAsistencia() != id) {

                    bw.write(
                            asistencia.getIdAsistencia() + ";" +
                            asistencia.getFecha() + ";" +
                            asistencia.getHoraIngreso() + ";" +
                            asistencia.getHoraSalida() + ";" +
                            asistencia.getEstado() + ";" +
                            asistencia.getColaborador().getIdColaborador()
                    );

                    bw.newLine();

                }

            }

        } catch (IOException e) {

            System.out.println("Error al eliminar asistencia.");

        }

    }

}