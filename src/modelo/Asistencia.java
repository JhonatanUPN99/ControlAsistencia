package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import enumeraciones.EstadoAsistencia;

/**
 * Representa el registro de asistencia de un colaborador.
 *
 * Cada objeto almacena la información correspondiente
 * a un día específico de trabajo.
 */
public class Asistencia {

    // ==========================
    // ATRIBUTOS
    // ==========================

    // Identificador del registro de asistencia
    private int idAsistencia;

    // Fecha del registro
    private LocalDate fecha;

    // Hora de ingreso
    private LocalTime horaIngreso;

    // Hora de salida
    private LocalTime horaSalida;

    // Estado de la asistencia
    private EstadoAsistencia estado;

    // Colaborador asociado al registro
    private Colaborador colaborador;

    // ==========================
    // CONSTRUCTORES
    // ==========================

    /**
     * Constructor vacío.
     */
    public Asistencia() {

    }

    /**
     * Constructor con parámetros.
     */
    public Asistencia(int idAsistencia,
                      LocalDate fecha,
                      LocalTime horaIngreso,
                      LocalTime horaSalida,
                      EstadoAsistencia estado,
                      Colaborador colaborador) {

        this.idAsistencia = idAsistencia;
        this.fecha = fecha;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.estado = estado;
        this.colaborador = colaborador;
    }

    // ==========================
    // GETTERS Y SETTERS
    // ==========================

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public EstadoAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistencia estado) {
        this.estado = estado;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    // ==========================
    // MÉTODOS
    // ==========================

    /**
     * Verifica si la asistencia cuenta con hora de salida.
     *
     * @return true si la salida fue registrada,
     *         false en caso contrario.
     */
    public boolean tieneSalidaRegistrada() {

        return horaSalida != null;

    }

    /**
     * Devuelve una representación en texto del objeto.
     */
    @Override
    public String toString() {

        return "Asistencia{" +
                "idAsistencia=" + idAsistencia +
                ", fecha=" + fecha +
                ", horaIngreso=" + horaIngreso +
                ", horaSalida=" + horaSalida +
                ", estado='" + estado + '\'' +
                ", colaborador=" + colaborador.obtenerNombreCompleto() +
                '}';

    }

}