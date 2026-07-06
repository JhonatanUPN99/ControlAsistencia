package modelo;

/**
 * La clase Colaborador representa a un empleado registrado
 * dentro del sistema de control de asistencia.
 *
 * Contiene únicamente la información básica del colaborador
 * y los métodos necesarios para acceder o modificar dichos datos.
 */
public class Colaborador {

    // ==========================
    // ATRIBUTOS
    // ==========================

    // Identificador único del colaborador
    private int idColaborador;

    // Nombres del colaborador
    private String nombres;

    // Apellidos del colaborador
    private String apellidos;

    // Cargo que desempeña dentro de la empresa
    private String cargo;

    // Área o departamento al que pertenece
    private String area;


    // ==========================
    // CONSTRUCTORES
    // ==========================

    /**
     * Constructor vacío.
     * Permite crear un objeto sin inicializar sus atributos.
     */
    public Colaborador() {

    }

    /**
     * Constructor con parámetros.
     * Inicializa todos los atributos del colaborador.
     *
     * @param idColaborador Identificador único.
     * @param nombres Nombre(s) del colaborador.
     * @param apellidos Apellidos del colaborador.
     * @param cargo Cargo que desempeña.
     * @param area Área donde trabaja.
     */
    public Colaborador(int idColaborador,
                       String nombres,
                       String apellidos,
                       String cargo,
                       String area) {

        this.idColaborador = idColaborador;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.area = area;
    }


    // ==========================
    // GETTERS Y SETTERS
    // ==========================

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    // ==========================
    // MÉTODOS
    // ==========================

    /**
     * Devuelve el nombre completo del colaborador.
     *
     * @return Nombre y apellido concatenados.
     */
    public String obtenerNombreCompleto() {

        return nombres + " " + apellidos;

    }

    /**
     * Devuelve una representación en texto del objeto.
     * Este método sobrescribe el comportamiento heredado
     * de la clase Object.
     */
    @Override
    public String toString() {

        return "Colaborador{" +
                "id=" + idColaborador +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", cargo='" + cargo + '\'' +
                ", area='" + area + '\'' +
                '}';

    }

}