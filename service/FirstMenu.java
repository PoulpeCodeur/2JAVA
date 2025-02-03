package service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FirstMenu {

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println("\n=== Menu de Connexion ===");
            System.out.println("1. Se connecter");
            System.out.println("2. S'inscrire");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option (1, 2 ou 3) : ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Choix invalide ! Veuillez entrer 1, 2 ou 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : veuillez entrer un nombre (1, 2 ou 3).");
                scanner.nextLine();
            }
        }

        try {
            switch (choice) {
                case 1:
                    Login.performLogin();
                    break;
                case 2:
                    Register.registerUser();
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Erreur inattendue.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }
}
