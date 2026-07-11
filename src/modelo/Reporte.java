package modelo;

//==============================================================
// Clase Reporte
//==============================================================
// Esta clase representa un resumen de la información de
// asistencia de un colaborador.
//
// No registra asistencias ni permisos; únicamente almacena
// información procesada para posteriormente mostrarla o
// exportarla a un archivo mediante ArchivoReporte.
//==============================================================

public class Reporte {

    //==========================================================
    // ATRIBUTOS
    //==========================================================

    // Identificador único del reporte.
    private int idReporte;

    // Colaborador al que pertenece el reporte.
    private Colaborador colaborador;

    // Cantidad de asistencias registradas.
    private int totalAsistencias;

    // Cantidad de tardanzas registradas.
    private int totalTardanzas;

    // Cantidad de inasistencias registradas.
    private int totalInasistencias;

    // Cantidad de permisos registrados.
    private int totalPermisos;

    //==========================================================
    // CONSTRUCTOR
    //==========================================================
    // Inicializa todos los atributos del reporte.
    //==========================================================

    public Reporte(int idReporte,
                   Colaborador colaborador,
                   int totalAsistencias,
                   int totalTardanzas,
                   int totalInasistencias,
                   int totalPermisos) {

        this.idReporte = idReporte;
        this.colaborador = colaborador;
        this.totalAsistencias = totalAsistencias;
        this.totalTardanzas = totalTardanzas;
        this.totalInasistencias = totalInasistencias;
        this.totalPermisos = totalPermisos;
    }

    //==========================================================
    // GETTERS
    //==========================================================

    // Devuelve el identificador del reporte.
    public int getIdReporte() {
        return idReporte;
    }

    // Devuelve el colaborador asociado al reporte.
    public Colaborador getColaborador() {
        return colaborador;
    }

    // Devuelve el número de asistencias.
    public int getTotalAsistencias() {
        return totalAsistencias;
    }

    // Devuelve el número de tardanzas.
    public int getTotalTardanzas() {
        return totalTardanzas;
    }

    // Devuelve el número de inasistencias.
    public int getTotalInasistencias() {
        return totalInasistencias;
    }

    // Devuelve el número de permisos.
    public int getTotalPermisos() {
        return totalPermisos;
    }

    //==========================================================
    // SETTERS
    //==========================================================

    // Modifica el identificador del reporte.
    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    // Modifica el colaborador asociado.
    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    // Modifica el total de asistencias.
    public void setTotalAsistencias(int totalAsistencias) {
        this.totalAsistencias = totalAsistencias;
    }

    // Modifica el total de tardanzas.
    public void setTotalTardanzas(int totalTardanzas) {
        this.totalTardanzas = totalTardanzas;
    }

    // Modifica el total de inasistencias.
    public void setTotalInasistencias(int totalInasistencias) {
        this.totalInasistencias = totalInasistencias;
    }

    // Modifica el total de permisos.
    public void setTotalPermisos(int totalPermisos) {
        this.totalPermisos = totalPermisos;
    }

    //==========================================================
    // MÉTODO toString()
    //==========================================================
    // Convierte el objeto Reporte en una cadena de texto.
    // Este método será utilizado por ArchivoReporte para
    // escribir la información dentro del archivo reportes.txt.
    //==========================================================

    @Override
    public String toString() {

        return "========================================\n" +
               "ID Reporte      : " + idReporte + "\n" +
               "Colaborador     : " + colaborador.obtenerNombreCompleto() + "\n" +
               "Asistencias     : " + totalAsistencias + "\n" +
               "Tardanzas       : " + totalTardanzas + "\n" +
               "Inasistencias   : " + totalInasistencias + "\n" +
               "Permisos        : " + totalPermisos + "\n" +
               "========================================";
    }

}