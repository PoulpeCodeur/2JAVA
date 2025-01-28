package service;

import java.sql.SQLException;
import java.util.Scanner;

public class FirstMenu {
    public static void main(String[] args) throws SQLException {
        showMenu();
    }

    public static void showMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("=== Menu de Connexion ===");
        System.out.println("1. Se connecter");
        System.out.println("2. S'inscrire");
        System.out.print("Choisissez une option (1 ou 2) : ");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                Login login = new Login();
                login.performLogin();
                break;
            case 2:
                Register.registerUser();
                break;
            default:
                throw new IllegalStateException("Valeur inattendue : " + choice);
        }
    }
}
