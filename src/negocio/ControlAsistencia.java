package negocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import enumeraciones.EstadoAsistencia;
import modelo.Asistencia;
import modelo.Colaborador;
import persistencia.ArchivoAsistencia;
import persistencia.ArchivoColaborador;

/**
 * Controla la lógica de negocio del registro de asistencia.
 */
public class ControlAsistencia {

    private ArchivoAsistencia archivoAsistencia;
    private ArchivoColaborador archivoColaborador;

    public ControlAsistencia() {
        archivoAsistencia = new ArchivoAsistencia();
        archivoColaborador = new ArchivoColaborador();
    }

    /**
     * Registra ingreso de un colaborador.
     */
    public boolean registrarIngreso(int idColaborador) {

        Colaborador colaborador =
                archivoColaborador.buscarPorId(idColaborador);

        if (colaborador == null) {
            return false;
        }

        LocalDate fechaActual = LocalDate.now();

        // Verificar si ya existe asistencia del día
        Asistencia asistenciaExistente =
                archivoAsistencia.buscarPorColaboradorYFecha(
                        idColaborador,
                        fechaActual);

        // Si ya existe ingreso registrado, no permitir duplicado
        if (asistenciaExistente != null
                && asistenciaExistente.getHoraIngreso() != null) {
            return false;
        }

        LocalTime horaIngreso = LocalTime.now();
        LocalTime horaLimite = LocalTime.of(8, 0);

        EstadoAsistencia estado =
                (horaIngreso.isAfter(horaLimite))
                        ? EstadoAsistencia.TARDANZA
                        : EstadoAsistencia.PRESENTE;

        Asistencia asistencia = new Asistencia(
                generarId(),
                fechaActual,
                horaIngreso,
                null,
                estado,
                colaborador
        );

        archivoAsistencia.guardar(asistencia);

        return true;
    }

    /**
     * Registra salida de un colaborador.
     */
    public boolean registrarSalida(int idColaborador) {

        Asistencia asistencia =
                archivoAsistencia.buscarPorColaboradorYFecha(
                        idColaborador,
                        LocalDate.now());

        // No permitir salida sin ingreso previo
        if (asistencia == null
                || asistencia.getHoraIngreso() == null) {
            return false;
        }

        // Evitar doble salida
        if (asistencia.getHoraSalida() != null) {
            return false;
        }

        asistencia.setHoraSalida(LocalTime.now());

        archivoAsistencia.actualizar(asistencia);

        return true;
    }

    /**
     * Lista todas las asistencias.
     */
    public List<Asistencia> listar() {
        return archivoAsistencia.leerTodos();
    }

    /**
     * Genera ID automático incremental.
     */
    private int generarId() {

        List<Asistencia> lista =
                archivoAsistencia.leerTodos();

        if (lista.isEmpty()) {
            return 1;
        }

        int mayorId = 0;

        for (Asistencia asistencia : lista) {
            if (asistencia.getIdAsistencia() > mayorId) {
                mayorId = asistencia.getIdAsistencia();
            }
        }

        return mayorId + 1;
    }
}