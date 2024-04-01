package Javaa;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Fcaultad";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            AdministradorEmpresa adminEmpresa = new AdministradorEmpresa(conn);
            AdministradorNoticia adminNoticia = new AdministradorNoticia(conn);

            while (true) {
                System.out.println("1. Administrar Empresas");
                System.out.println("2. Administrar Noticias");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        adminEmpresaMenu(adminEmpresa, scanner);
                        break;
                    case 2:
                        adminNoticiaMenu(adminNoticia, scanner);
                        break;
                    case 3:
                        conn.close();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void adminEmpresaMenu(AdministradorEmpresa adminEmpresa, Scanner scanner) {
        try {
            while (true) {
                System.out.println("\n1. Alta de Empresa");
                System.out.println("2. Baja de Empresa");
                System.out.println("3. Modificación de Empresa");
                System.out.println("4. Consulta de Empresas Totales");
                System.out.println("5. Consulta de Empresa por ID");
                System.out.println("6. Volver");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        adminEmpresa.altaEmpresa(scanner);
                        break;
                    case 2:
                        adminEmpresa.bajaEmpresa(scanner);
                        break;
                    case 3:
                        adminEmpresa.modificarEmpresa(scanner);
                        break;
                    case 4:
                        adminEmpresa.consultarEmpresas();
                        break;
                    case 5:
                        System.out.print("Ingrese el ID de la empresa que desea consultar: ");
                        int id = scanner.nextInt();
                        adminEmpresa.consultarEmpresaPorId(id);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void adminNoticiaMenu(AdministradorNoticia adminNoticia, Scanner scanner) {
        try {
            while (true) {
                System.out.println("\n1. Alta de Noticia");
                System.out.println("2. Baja de Noticia");
                System.out.println("3. Modificación de Noticia");
                System.out.println("4. Consulta de Noticias");
                System.out.println("5. Volver");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        adminNoticia.altaNoticia(scanner);
                        break;
                    case 2:
                        adminNoticia.bajaNoticia(scanner);
                        break;
                    case 3:
                        adminNoticia.modificarNoticia(scanner);
                        break;
                    case 4:
                        adminNoticia.mostrarNoticias();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
