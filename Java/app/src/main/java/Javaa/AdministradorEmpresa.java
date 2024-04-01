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

    public void modificarEmpresa(Scanner scanner) throws SQLException {
        System.out.print("Ingrese el ID de la empresa que desea modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        // Consultar la empresa actual para obtener sus datos
        String sqlSelect = "SELECT * FROM Empresa WHERE Id = ?";
        PreparedStatement selectStatement = conn.prepareStatement(sqlSelect);
        selectStatement.setInt(1, id);
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            // Mostrar los datos actuales de la empresa
            System.out.println("Datos actuales de la empresa:");
            System.out.println("Denominación: " + resultSet.getString("Denominacion"));
            System.out.println("Teléfono: " + resultSet.getString("Telefono"));
            System.out.println("Horario de Atención: " + resultSet.getString("HorarioAtencion"));
            System.out.println("Quiénes Somos: " + resultSet.getString("QuienesSomos"));
            System.out.println("Latitud: " + resultSet.getDouble("Latitud"));
            System.out.println("Longitud: " + resultSet.getDouble("Longitud"));
            System.out.println("Domicilio: " + resultSet.getString("Domicilio"));
            System.out.println("Email: " + resultSet.getString("Email"));

            // Solicitar al usuario los nuevos datos
            System.out.println("\nIngrese los nuevos datos de la empresa:");
            System.out.print("Denominación: ");
            String nuevaDenominacion = scanner.nextLine();
            System.out.print("Teléfono: ");
            String nuevoTelefono = scanner.nextLine();
            System.out.print("Horario de Atención: ");
            String nuevoHorario = scanner.nextLine();
            System.out.print("Quiénes Somos: ");
            String nuevosQuienesSomos = scanner.nextLine();
            System.out.print("Latitud: ");
            double nuevaLatitud = scanner.nextDouble();
            System.out.print("Longitud: ");
            double nuevaLongitud = scanner.nextDouble();
            scanner.nextLine(); // Consumir la nueva línea
            System.out.print("Domicilio: ");
            String nuevoDomicilio = scanner.nextLine();
            System.out.print("Email: ");
            String nuevoEmail = scanner.nextLine();

            // Actualizar los datos de la empresa en la base de datos
            String sqlUpdate = "UPDATE Empresa SET Denominacion = ?, Telefono = ?, HorarioAtencion = ?, QuienesSomos = ?, Latitud = ?, Longitud = ?, Domicilio = ?, Email = ? WHERE Id = ?";
            PreparedStatement updateStatement = conn.prepareStatement(sqlUpdate);
            updateStatement.setString(1, nuevaDenominacion);
            updateStatement.setString(2, nuevoTelefono);
            updateStatement.setString(3, nuevoHorario);
            updateStatement.setString(4, nuevosQuienesSomos);
            updateStatement.setDouble(5, nuevaLatitud);
            updateStatement.setDouble(6, nuevaLongitud);
            updateStatement.setString(7, nuevoDomicilio);
            updateStatement.setString(8, nuevoEmail);
            updateStatement.setInt(9, id);

            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La empresa fue modificada exitosamente.");
            } else {
                System.out.println("No se pudo modificar la empresa. Verifique el ID proporcionado.");
            }
        } else {
            System.out.println("No se encontró ninguna empresa con el ID proporcionado.");
        }
    }

    public void consultarEmpresas() throws SQLException {
        String sql = "SELECT * FROM Empresa";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("\nListado de Empresas:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("Id"));
                System.out.println("Denominación: " + resultSet.getString("Denominacion"));
                System.out.println("Teléfono: " + resultSet.getString("Telefono"));
                System.out.println("Horario de Atención: " + resultSet.getString("HorarioAtencion"));
                System.out.println("Quiénes Somos: " + resultSet.getString("QuienesSomos"));
                System.out.println("Latitud: " + resultSet.getDouble("Latitud"));
                System.out.println("Longitud: " + resultSet.getDouble("Longitud"));
                System.out.println("Domicilio: " + resultSet.getString("Domicilio"));
                System.out.println("Email: " + resultSet.getString("Email"));
                System.out.println();
            }
        }
    }

    public void consultarEmpresaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Empresa WHERE Id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            System.out.println("\nDatos de la empresa con ID " + id + ":");
            System.out.println("Denominación: " + resultSet.getString("Denominacion"));
            System.out.println("Teléfono: " + resultSet.getString("Telefono"));
            System.out.println("Horario de Atención: " + resultSet.getString("HorarioAtencion"));
            System.out.println("Quiénes Somos: " + resultSet.getString("QuienesSomos"));
            System.out.println("Latitud: " + resultSet.getDouble("Latitud"));
            System.out.println("Longitud: " + resultSet.getDouble("Longitud"));
            System.out.println("Domicilio: " + resultSet.getString("Domicilio"));
            System.out.println("Email: " + resultSet.getString("Email"));
        } else {
            System.out.println("No existe una empresa con el ID proporcionado.");
        }
    }


    // Implementa los métodos para modificación y consulta de empresas
}
