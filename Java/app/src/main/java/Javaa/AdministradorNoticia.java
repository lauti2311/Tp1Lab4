package Javaa;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class AdministradorNoticia {
    private Connection conn;

    public AdministradorNoticia(Connection conn) {
        this.conn = conn;
    }

    public List<Noticia> obtenerNoticias() throws SQLException {
        List<Noticia> noticias = new ArrayList<>();
        String sql = "SELECT n.*, e.Denominación AS Empresa " +
                "FROM Noticia n " +
                "JOIN Empresa e ON n.idEmpresa = e.Id";
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String titulo = resultSet.getString("Título de la noticia");
                String resumen = resultSet.getString("Resumen de la Noticia");
                String imagen = resultSet.getString("Imagen Noticia");
                String contenido = resultSet.getString("Contenido HTML");
                char publicada = resultSet.getString("Publicada").charAt(0);
                Date fechaPublicacion = resultSet.getDate("Fecha Publicación");
                int idEmpresa = resultSet.getInt("idEmpresa");
                String empresa = resultSet.getString("Empresa");

                Noticia noticia = new Noticia(id, titulo, resumen, imagen, contenido,
                        publicada, fechaPublicacion, idEmpresa, empresa);
                noticias.add(noticia);
            }
        }
        return noticias;
    }

    public void altaNoticia(Scanner scanner) throws SQLException {
        System.out.println("\nIngrese los datos de la noticia:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Resumen: ");
        String resumen = scanner.nextLine();
        System.out.print("Imagen: ");
        String imagen = scanner.nextLine();
        System.out.print("Contenido HTML: ");
        String contenido = scanner.nextLine();
        System.out.print("Publicada (Y/N): ");
        char publicada = scanner.nextLine().charAt(0);
        System.out.print("Fecha de Publicación (YYYY-MM-DD): ");
        String fechaPublicacion = scanner.nextLine();
        System.out.print("ID de Empresa: ");
        int idEmpresa = scanner.nextInt();

        String sql = "INSERT INTO Noticia (TituloNoticia, ResumenNoticia, ImagenNoticia, ContenidoHTML, Publicada, FechaPublicacion, idEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, titulo);
        statement.setString(2, resumen);
        statement.setString(3, imagen);
        statement.setString(4, contenido);
        statement.setString(5, String.valueOf(publicada));
        statement.setDate(6, Date.valueOf(fechaPublicacion));
        statement.setInt(7, idEmpresa);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("La noticia fue insertada exitosamente.");
        } else {
            System.out.println("No se pudo insertar la noticia.");
        }
    }

    public void bajaNoticia(Scanner scanner) throws SQLException {
        System.out.print("Ingrese el ID de la noticia que desea dar de baja: ");
        int id = scanner.nextInt();
        String sql = "DELETE FROM Noticia WHERE Id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("La noticia fue eliminada exitosamente.");
        } else {
            System.out.println("No se pudo eliminar la noticia. Verifique el ID proporcionado.");
        }
    }

    // Implementa los métodos para modificación y consulta de noticias
}
