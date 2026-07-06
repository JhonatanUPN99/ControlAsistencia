package modelo;

import java.time.LocalDate;
import enumeraciones.EstadoPermiso;
/**
 * Representa una solicitud de permiso realizada por un colaborador.
 */
public class Permiso {

    // ==========================
    // ATRIBUTOS
    // ==========================

    // Identificador del permiso
    private int idPermiso;

    // Fecha en que se registra la solicitud
    private LocalDate fechaSolicitud;

    // Motivo del permiso
    private String motivo;

    // Estado del permiso
    private EstadoPermiso estado;
    // Colaborador que solicita el permiso
    private Colaborador colaborador;

    // ==========================
    // CONSTRUCTORES
    // ==========================

    /**
     * Constructor vacío.
     */
    public Permiso() {

    }

    /**
     * Constructor con parámetros.
     */
    public Permiso(int idPermiso,
                   LocalDate fechaSolicitud,
                   String motivo,
                   EstadoPermiso estado,
                   Colaborador colaborador) {

        this.idPermiso = idPermiso;
        this.fechaSolicitud = fechaSolicitud;
        this.motivo = motivo;
        this.estado = estado;
        this.colaborador = colaborador;
    }

    // ==========================
    // GETTERS Y SETTERS
    // ==========================

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoPermiso getEstado() {
        return estado;
    }

    public void setEstado(EstadoPermiso estado) {
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
     * Indica si el permiso fue aprobado.
     *
     */
    public boolean estaAprobado() {
        return estado == EstadoPermiso.APROBADO;
    }

    /**
     * Devuelve una representación en texto del permiso.
     */
    @Override
    public String toString() {

        return "Permiso{" +
                "idPermiso=" + idPermiso +
                ", fechaSolicitud=" + fechaSolicitud +
                ", motivo='" + motivo + '\'' +
                ", estado='" + estado + '\'' +
                ", colaborador=" + colaborador.obtenerNombreCompleto() +
                '}';

    }

}