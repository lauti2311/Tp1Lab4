package Javaa;

import java.sql.*;
import java.util.Scanner;

public class AdministradorNoticia {
    private Connection conn;

    public AdministradorNoticia(Connection conn) {
        this.conn = conn;
    }

    public void altaNoticia(Scanner scanner) {
        try {

            System.out.println("\nIngrese los datos de la noticia:");
            System.out.print("Título: ");
            String titulo = validarCampo("Título", scanner.nextLine());
            System.out.print("Resumen: ");
            String resumen = validarCampo("Resumen", scanner.nextLine());
            System.out.print("Imagen: ");
            String imagen = validarCampo("Imagen", scanner.nextLine());
            System.out.print("Contenido HTML: ");
            String contenido = validarCampo("Contenido HTML", scanner.nextLine());
            System.out.print("Publicada (Y/N): ");
            char publicada = validarPublicada(scanner.nextLine().toLowerCase()); // Convertir a minúsculas
            System.out.print("Fecha de Publicación (YYYY-MM-DD): ");
            String fechaPublicacion = validarFecha(scanner.nextLine());

            String sql = "INSERT INTO Noticia (TituloNoticia, ResumenNoticia, ImagenNoticia, ContenidoHTML, Publicada, FechaPublicacion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, titulo);
            statement.setString(2, resumen);
            statement.setString(3, imagen);
            statement.setString(4, contenido);
            statement.setString(5, String.valueOf(publicada));
            statement.setDate(6, Date.valueOf(fechaPublicacion));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("La noticia fue insertada exitosamente.");
            } else {
                System.out.println("No se pudo insertar la noticia.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar la noticia: " + e.getMessage());
        } catch (CampoInvalidoException e) {
            System.out.println("Error en el campo '" + e.getCampo() + "': " + e.getMessage());
        }
    }

    private String validarCampo(String nombreCampo, String valor) throws CampoInvalidoException {
        if (valor.isEmpty()) {
            throw new CampoInvalidoException(nombreCampo, "El campo no puede estar vacío");
        }
        return valor;
    }

    private char validarPublicada(String valor) throws CampoInvalidoException {
        if (valor.isEmpty() || (valor.charAt(0) != 'y' && valor.charAt(0) != 'n')) { // Permitir 'y' o 'n' en minúsculas
            throw new CampoInvalidoException("Publicada", "El valor debe ser 'Y' o 'N'");
        }
        return valor.charAt(0);
    }

    private String validarFecha(String valor) throws CampoInvalidoException {
        try {
            Date.valueOf(valor);
            return valor;
        } catch (IllegalArgumentException e) {
            throw new CampoInvalidoException("Fecha de Publicación", "Formato de fecha incorrecto (debe ser YYYY-MM-DD)");
        }
    }


    class CampoInvalidoException extends Exception {
        private String campo;

    public CampoInvalidoException(String campo, String mensaje) {
        super(mensaje);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
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

    public void mostrarNoticias() throws SQLException {
        String sql = "SELECT * FROM Noticia";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        boolean noticiasEncontradas = false;

        while (rs.next()) {
            noticiasEncontradas = true;

            int id = rs.getInt("Id");
            String titulo = rs.getString("TituloNoticia");
            String resumen = rs.getString("ResumenNoticia");
            String imagen = rs.getString("ImagenNoticia");
            String contenido = rs.getString("ContenidoHTML");
            char publicada = rs.getString("Publicada").charAt(0);
            String fechaPublicacion = rs.getString("FechaPublicacion");

            System.out.println("ID: " + id);
            System.out.println("Título: " + titulo);
            System.out.println("Resumen: " + resumen);
            System.out.println("Imagen: " + imagen);
            System.out.println("Contenido HTML: " + contenido);
            System.out.println("Publicada: " + publicada);
            System.out.println("Fecha de Publicación: " + fechaPublicacion);
            System.out.println("----------------------");
        }

        if (!noticiasEncontradas) {
            System.out.println("No hay noticias existentes.");
        }
    }

    public void modificarNoticia(Scanner scanner) {
        try {
            System.out.print("Ingrese el ID de la noticia que desea modificar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            System.out.println("\nIngrese los nuevos datos de la noticia:");
            System.out.print("Título: ");
            String titulo = validarCampo("Título", scanner.nextLine());
            System.out.print("Resumen: ");
            String resumen = validarCampo("Resumen", scanner.nextLine());
            System.out.print("Imagen: ");
            String imagen = validarCampo("Imagen", scanner.nextLine());
            System.out.print("Contenido HTML: ");
            String contenido = validarCampo("Contenido HTML", scanner.nextLine());
            System.out.print("Publicada (Y/N): ");
            char publicada = validarPublicada(scanner.nextLine().toLowerCase());
            System.out.print("Fecha de Publicación (YYYY-MM-DD): ");
            String fechaPublicacion = validarFecha(scanner.nextLine());

            String sql = "UPDATE Noticia SET TituloNoticia = ?, ResumenNoticia = ?, ImagenNoticia = ?, ContenidoHTML = ?, Publicada = ?, FechaPublicacion = ? WHERE Id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, titulo);
            statement.setString(2, resumen);
            statement.setString(3, imagen);
            statement.setString(4, contenido);
            statement.setString(5, String.valueOf(publicada));
            statement.setDate(6, Date.valueOf(fechaPublicacion));
            statement.setInt(7, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La noticia fue modificada exitosamente.");
            } else {
                System.out.println("No se pudo modificar la noticia. Verifique el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar la noticia: " + e.getMessage());
        } catch (CampoInvalidoException e) {
            System.out.println("Error en el campo '" + e.getCampo() + "': " + e.getMessage());
        }
    }


}
