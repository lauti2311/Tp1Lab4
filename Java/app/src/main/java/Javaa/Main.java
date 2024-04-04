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
                System.out.println("4. Consulta de Empresas");
                System.out.println("5. Volver");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        adminEmpresa.altaEmpresa(scanner);
                        break;
                    // Implementa el resto de las opciones del menú de empresa
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
                    // Implementa el resto de las opciones del menú de noticia
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
