package java;

import java.sql.Date;

public class Noticia {
    private int id;
    private String titulo;
    private String resumen;
    private String imagen;
    private String contenidoHtml;
    private char publicada;
    private Date fechaPublicacion;
    private int idEmpresa;

    public Noticia(int id, String titulo, String resumen, String imagen, String contenidoHtml, char publicada,
                   Date fechaPublicacion, int idEmpresa) {
        this.id = id;
        this.titulo = titulo;
        this.resumen = resumen;
        this.imagen = imagen;
        this.contenidoHtml = contenidoHtml;
        this.publicada = publicada;
        this.fechaPublicacion = fechaPublicacion;
        this.idEmpresa = idEmpresa;
    }

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

    public String getContenidoHtml() {
        return contenidoHtml;
    }

    public void setContenidoHtml(String contenidoHtml) {
        this.contenidoHtml = contenidoHtml;
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
}
