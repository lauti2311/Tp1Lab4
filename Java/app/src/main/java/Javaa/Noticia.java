package Javaa;

import java.sql.Date;

public class Noticia {
    private int id;
    private String titulo;
    private String resumen;
    private String imagen;
    private String contenido;
    private char publicada;
    private Date fechaPublicacion;
    private int idEmpresa; // Clave foránea
    private String empresa; // Nombre de la empresa asociada

    // Constructor
    public Noticia(int id, String titulo, String resumen, String imagen, String contenido,
                   char publicada, Date fechaPublicacion, int idEmpresa, String empresa) {
        this.id = id;
        this.titulo = titulo;
        this.resumen = resumen;
        this.imagen = imagen;
        this.contenido = contenido;
        this.publicada = publicada;
        this.fechaPublicacion = fechaPublicacion;
        this.idEmpresa = idEmpresa;
        this.empresa = empresa;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public char getPublicada() {
        return publicada;
    }

    public void setPublicada(char publicada) {
        this.publicada = publicada;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
