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

        System.out.println("Bienvenue " + pseudo);
        System.out.println("Choisissez ce que vous souhaitez faire");
        System.out.println("1 Gestion des stocks");
        System.out.println("2 Voir la liste des utilisateurs");
        System.out.println("3 Mettre à jour son compte");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.println("FEATURE EN COURS DE DEV");
                break;
            case 2:
                try {
                    usersRepository.listUsers();
                } catch (SQLException e) {
                    System.out.println("Erreur lors de l'affichage des utilisateurs.");
                    e.printStackTrace();
                }
                break;
            case 3:

                System.out.println("Nouvel email : ");
                String newEmail = scanner.nextLine();
                System.out.println("Nouveau pseudo : ");
                String newPseudo = scanner.nextLine();

                System.out.println("Nouveau prénom : ");
                String newFirstName = scanner.nextLine();
                System.out.println("Nouveau nom : ");
                String newLastName = scanner.nextLine();

                usersRepository.updateUser(userId, newEmail, newFirstName, newLastName, newPseudo);
                break;
        }
    }
}
