package java;

public class Empresa {
    private int id;
    private String denominacion;
    private String telefono;
    private String horarioAtencion;
    private String quienesSomos;
    private double latitud;
    private double longitud;
    private String domicilio;
    private String email;

    public Empresa(int id, String denominacion, String telefono, String horarioAtencion, String quienesSomos,
    double latitud, double longitud, String domicilio, String email) {
        this.id = id;
        this.denominacion = denominacion;
        this.telefono = telefono;
        this.horarioAtencion = horarioAtencion;
        this.quienesSomos = quienesSomos;
        this.latitud = latitud;
        this.longitud = longitud;
        this.domicilio = domicilio;
        this.email = email;
        }

    public int getId() {
    return id;
    }

    public void setId(int id) {
    this.id = id;
    }

    public String getDenominacion() {
    return denominacion;
    }

    public void setDenominacion(String denominacion) {
    this.denominacion = denominacion;
    }

    public String getTelefono() {
    return telefono;
    }

    public void setTelefono(String telefono) {
    this.telefono = telefono;
    }

    public String getHorarioAtencion() {
    return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
    this.horarioAtencion = horarioAtencion;
    }

    public String getQuienesSomos() {
    return quienesSomos;
    }

    public void setQuienesSomos(String quienesSomos) {
    this.quienesSomos = quienesSomos;
    }

    public double getLatitud() {
    return latitud;
    }

    public void setLatitud(double latitud) {
    this.latitud = latitud;
    }

    public double getLongitud() {
    return longitud;
    }

    public void setLongitud(double longitud) {
    this.longitud = longitud;
    }

    public String getDomicilio() {
    return domicilio;
    }

    public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
    }

    public String getEmail() {
    return email;
    }

    public void setEmail(String email) {
    this.email = email;
    }

}
