package Javaa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpresaDAO {
    private Connection connection;

    public EmpresaDAO() {
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarEmpresa(Empresa empresa) {
        String sql = "INSERT INTO empresa (denominacion, telefono, horarioAtencion, quienesSomos, latitud, longitud, domicilio, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, empresa.getDenominacion());
            statement.setString(2, empresa.getTelefono());
            statement.setString(3, empresa.getHorarioAtencion());
            statement.setString(4, empresa.getQuienesSomos());
            statement.setDouble(5, empresa.getLatitud());
            statement.setDouble(6, empresa.getLongitud());
            statement.setString(7, empresa.getDomicilio());
            statement.setString(8, empresa.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Otros métodos para baja, modificación y consulta de empresas
}
