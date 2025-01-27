package service;

import repository.UsersRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public Login() {
    }

    public static void performLogin() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" --- Connexion --- ");

        // Demande des informations d'authentification
        System.out.print("Entrez votre mail : ");
        String email = scanner.nextLine();

        System.out.print("Entrez votre mot de passe : ");
        String password = scanner.nextLine();

        // Authentification de l'utilisateur
        if (UsersRepository.authenticate(email, password)) {
            System.out.println("La connexion a réussi. Vous pouvez maintenant naviguer.");

            // Récupération du rôle de l'utilisateur
            String role = UsersRepository.getRole(email);

            if ("ADMIN".equals(role)) {
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.menu(email); // Navigation vers le menu admin
            } else if ("USER".equals(role)) {
                UserMenu userMenu = new UserMenu();
                userMenu.menu(email); // Navigation vers le menu utilisateur
            } else {
                System.out.println("Rôle inconnu. Contactez un administrateur.");
            }

        } else {
            System.out.println("La connexion a échoué. Merci de vérifier vos identifiants.");
        }

        // Fermeture du scanner
        scanner.close();
    }
}
