package Javaa;

import java.sql.*;
import java.util.Scanner;

public class Empresa {
    private int id;
    private String denominacion;
    private String telefono;
    private String horario;
    private String quienesSomos;
    private double latitud;
    private double longitud;
    private String domicilio;
    private String email;

    public Empresa(int id, String denominacion, String telefono, String horario, String quienesSomos,
                   double latitud, double longitud, String domicilio, String email) {
        this.id = id;
        this.denominacion = denominacion;
        this.telefono = telefono;
        this.horario = horario;
        this.quienesSomos = quienesSomos;
        this.latitud = latitud;
        this.longitud = longitud;
        this.domicilio = domicilio;
        this.email = email;
    }

    // Getters y Setters
}

