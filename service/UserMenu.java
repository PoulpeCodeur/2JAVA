package service;

import repository.UsersRepository;
import java.sql.SQLException;
import java.util.Scanner;

public class UserMenu {
    public UserMenu() {
    }

    public void menu(String email) {
        UsersRepository usersRepository = new UsersRepository();
        String pseudo = usersRepository.getPseudo(email);
        String role = usersRepository.getRole(email);
        int userId = usersRepository.getUserId(email);

        System.out.println("\n--------------------------------------------------");
        System.out.println("Bienvenue, " + pseudo + " (" + role + ")");
        System.out.println("--------------------------------------------------");
        System.out.println("Choisissez l'action que vous souhaitez effectuer :");
        System.out.println("1. Gestion des stocks (en cours de développement)");
        System.out.println("2. Voir la liste des utilisateurs");
        System.out.println("3. Mettre à jour votre compte");
        System.out.println("4. Quitter");
        System.out.println("--------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();


        switch (option) {
            case 1:
                System.out.println("\nGestion des stocks : Fonctionnalité en cours de développement.");
                break;
            case 2:
                try {
                    System.out.println("\nListe des utilisateurs :");
                    usersRepository.listUsers();

                    System.out.println("Retour au menu principal...");
                    menu(email);
                } catch (SQLException e) {
                    System.out.println("Impossible d'afficher la liste des utilisateurs.");
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("\nMise à jour de votre compte :");

                System.out.print("Nouvel email : ");
                String newEmail = scanner.nextLine();
                System.out.print("Nouveau pseudo : ");
                String newPseudo = scanner.nextLine();

                System.out.print("Nouveau prénom : ");
                String newFirstName = scanner.nextLine();
                System.out.print("Nouveau nom : ");
                String newLastName = scanner.nextLine();

                usersRepository.updateUser(userId, newEmail, newFirstName, newLastName, newPseudo);
                System.out.println("Vos informations ont été mises à jour avec succès.");
                System.out.println("Retour au menu principal...");
                menu(email);
                break;

            case 4:
                break;

            default:
                System.out.println("Option invalide. Veuillez choisir une option valide.");
                break;
        }
    }
}
