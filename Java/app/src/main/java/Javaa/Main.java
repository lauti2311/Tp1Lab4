package Javaa;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Fcaultad";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String htmlContent = ""; // Contenido HTML que se insertará en el archivo index.html
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            if (connection != null) {
                System.out.println("Conexión establecida con la base de datos Facultad");

                // Consulta SQL para recuperar el ID y las denominaciones de las empresas
                String query = "SELECT id, denominacion FROM Empresa";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                // Construir la tabla HTML
                StringBuilder htmlTable = new StringBuilder();
                htmlTable.append("<table width=\"50%\" align=\"center\">");
                htmlTable.append("<tr>");
                htmlTable.append("<td width=\"50%\"><b>EMPRESA</b></td>");
                htmlTable.append("<td><b>VER PAGINA</b></td>");
                htmlTable.append("</tr>");

                // Iterar sobre los resultados y agregar cada fila a la tabla HTML
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String denominacion = resultSet.getString("denominacion");
                    htmlTable.append("<tr>");
                    htmlTable.append("<td>").append(denominacion).append("</td>");
                    htmlTable.append("<td><a href=\"home.html?id=").append(id).append("\">URL PAGINA HOME</a></td>");
                    htmlTable.append("</tr>");
                }

                htmlTable.append("</table>");

                // Guardar el contenido HTML generado
                htmlContent = htmlTable.toString();
            } else {
                System.out.println("No se pudo establecer conexión con la base de datos Facultad");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Reemplazar XXXXXXXX con el contenido HTML generado
        try {
            String indexPath = "index.html";
            // Borrar el contenido existente del archivo index.html
            FileWriter writer = new FileWriter(indexPath);
            writer.write("");
            writer.close();
            // Escribir el nuevo contenido generado en el archivo index.html
            Files.write(Paths.get(indexPath), htmlContent.getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Contenido HTML generado e integrado en index.html");

//        <-- UTILIZAR ESTE REEMPLAZO DE MAIN PARA PODER CREAR, ELIMINAR, CONSULTAR Y MODIFICAR LAS EMPRESAS Y NOTICIAS POR CONSOLA>


//        Connection conn = null;
//        Scanner scanner = new Scanner(System.in);
//
//        try {
//            Class.forName(JDBC_DRIVER);
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            AdministradorEmpresa adminEmpresa = new AdministradorEmpresa(conn);
//            AdministradorNoticia adminNoticia = new AdministradorNoticia(conn);
//
//            while (true) {
//                System.out.println("1. Administrar Empresas");
//                System.out.println("2. Administrar Noticias");
//                System.out.println("3. Salir");
//                System.out.print("Seleccione una opción: ");
//                int opcion = scanner.nextInt();
//                scanner.nextLine(); // Consumir la nueva línea
//
//                switch (opcion) {
//                    case 1:
//                        adminEmpresaMenu(adminEmpresa, scanner);
//                        break;
//                    case 2:
//                        adminNoticiaMenu(adminNoticia, scanner);
//                        break;
//                    case 3:
//                        conn.close();
//                        scanner.close();
//                        return;
//                    default:
//                        System.out.println("Opción no válida. Intente de nuevo.");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void adminEmpresaMenu(AdministradorEmpresa adminEmpresa, Scanner scanner) {
//        try {
//            while (true) {
//                System.out.println("\n1. Alta de Empresa");
//                System.out.println("2. Baja de Empresa");
//                System.out.println("3. Modificación de Empresa");
//                System.out.println("4. Consulta de Empresas Totales");
//                System.out.println("5. Consulta de Empresa por ID");
//                System.out.println("6. Volver");
//                System.out.print("Seleccione una opción: ");
//                int opcion = scanner.nextInt();
//                scanner.nextLine(); // Consumir la nueva línea
//
//                switch (opcion) {
//                    case 1:
//                        adminEmpresa.altaEmpresa(scanner);
//                        break;
//                    case 2:
//                        adminEmpresa.bajaEmpresa(scanner);
//                        break;
//                    case 3:
//                        adminEmpresa.modificarEmpresa(scanner);
//                        break;
//                    case 4:
//                        adminEmpresa.consultarEmpresas();
//                        break;
//                    case 5:
//                        System.out.print("Ingrese el ID de la empresa que desea consultar: ");
//                        int id = scanner.nextInt();
//                        adminEmpresa.consultarEmpresaPorId(id);
//                        break;
//                    case 6:
//                        return;
//                    default:
//                        System.out.println("Opción no válida. Intente de nuevo.");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void adminNoticiaMenu(AdministradorNoticia adminNoticia, Scanner scanner) {
//        try {
//            while (true) {
//                System.out.println("\n1. Alta de Noticia");
//                System.out.println("2. Baja de Noticia");
//                System.out.println("3. Modificación de Noticia");
//                System.out.println("4. Consulta de Noticias");
//                System.out.println("5. Volver");
//                System.out.print("Seleccione una opción: ");
//                int opcion = scanner.nextInt();
//                scanner.nextLine(); // Consumir la nueva línea
//
//                switch (opcion) {
//                    case 1:
//                        adminNoticia.altaNoticia(scanner);
//                        break;
//                    case 2:
//                        adminNoticia.bajaNoticia(scanner);
//                        break;
//                        //completar los case :D
//                    case 5:
//                        return;
//                    default:
//                        System.out.println("Opción no válida. Intente de nuevo.");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
