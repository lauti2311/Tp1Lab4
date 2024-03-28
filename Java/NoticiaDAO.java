package Java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NoticiaDAO {
    private Connection connection;

    public NoticiaDAO() {
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarNoticia(Noticia noticia) {
        String sql = "INSERT INTO noticia (titulo, resumen, imagen, contenidoHtml, publicada, fechaPublicacion, idEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, noticia.getTitulo());
            statement.setString(2, noticia.getResumen());
            statement.setString(3, noticia.getImagen());
            statement.setString(4, noticia.getContenidoHtml());
            statement.setString(5, String.valueOf(noticia.getPublicada()));
            statement.setDate(6, new java.sql.Date(noticia.getFechaPublicacion().getTime()));
            statement.setInt(7, noticia.getIdEmpresa());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Otros métodos para baja, modificación y consulta de noticias
}
