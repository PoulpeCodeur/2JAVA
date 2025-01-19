package service;
import java.util.Scanner;

public class FirstMenu {

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
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
                System.out.println("Connection");
                break;
            case 2:
                System.out.println("S'inscrire");
                break;
            default:
                throw new IllegalStateException("Valeur innatendue :  " + choice);
        }
    }

}
