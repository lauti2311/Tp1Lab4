package Javaa;

import java.sql.*;
import java.util.Scanner;

public class AdministradorEmpresa {
    private Connection conn;

    public AdministradorEmpresa(Connection conn) {
        this.conn = conn;
    }

    public void altaEmpresa(Scanner scanner) throws SQLException {
        System.out.println("\nIngrese los datos de la empresa:");
        System.out.print("Denominación: ");
        String denominacion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Horario de Atención: ");
        String horario = scanner.nextLine();
        System.out.print("Quienes Somos: ");
        String quienesSomos = scanner.nextLine();
        System.out.print("Latitud: ");
        double latitud = scanner.nextDouble();
        System.out.print("Longitud: ");
        double longitud = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        String sql = "INSERT INTO Empresa (Denominacion, Telefono, HorarioAtencion, QuienesSomos, Latitud, Longitud, Domicilio, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, denominacion);
        statement.setString(2, telefono);
        statement.setString(3, horario);
        statement.setString(4, quienesSomos);
        statement.setDouble(5, latitud);
        statement.setDouble(6, longitud);
        statement.setString(7, domicilio);
        statement.setString(8, email);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("La empresa fue insertada exitosamente.");
        } else {
            System.out.println("No se pudo insertar la empresa.");
        }
    }

    public void bajaEmpresa(Scanner scanner) throws SQLException {
        System.out.print("Ingrese el ID de la empresa que desea dar de baja: ");
        int id = scanner.nextInt();
        String sql = "DELETE FROM Empresa WHERE Id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("La empresa fue eliminada exitosamente.");
        } else {
            System.out.println("No se pudo eliminar la empresa. Verifique el ID proporcionado.");
        }
    }

    // Implementa los métodos para modificación y consulta de empresas
}
