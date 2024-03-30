package Javaa;

import java.sql.*;
import java.util.Scanner;

public class Noticia {
    private int id;
    private String titulo;
    private String resumen;
    private String imagen;
    private String contenido;
    private char publicada;
    private Date fechaPublicacion;
    private int idEmpresa;

    public Noticia(int id, String titulo, String resumen, String imagen, String contenido,
                   char publicada, Date fechaPublicacion, int idEmpresa) {
        this.id = id;
        this.titulo = titulo;
        this.resumen = resumen;
        this.imagen = imagen;
        this.contenido = contenido;
        this.publicada = publicada;
        this.fechaPublicacion = fechaPublicacion;
        this.idEmpresa = idEmpresa;
    }

    // Getters y Setters
}
