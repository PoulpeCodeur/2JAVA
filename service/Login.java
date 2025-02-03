package service;

import repository.UsersRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public Login() {
    }

    public static void performLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" --- Connexion --- ");

        String email;
        boolean emailExists = false;

        do {
            System.out.print("Entrez votre mail : ");
            email = scanner.nextLine().trim();

            try {
                emailExists = UsersRepository.emailExists(email);
                if (!emailExists) {
                    System.out.println("Cet email n'existe pas. Veuillez entrer un email valide.");
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la vérification de l'email : " + e.getMessage());
                return;
            }
        } while (!emailExists);

        int attempts = 3;
        boolean isAuthenticated = false;

        while (attempts > 0) {
            System.out.print("Entrez votre mot de passe : ");
            String password = scanner.nextLine().trim();

            if (UsersRepository.authenticate(email, password)) {
                isAuthenticated = true;
                break;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Mot de passe incorrect. Il vous reste " + attempts + " tentative(s).");
                } else {
                    System.out.println("Trop de tentatives. Connexion annulée.");
                    scanner.close();
                    return;
                }
            }
        }

        if (isAuthenticated) {
            System.out.println("La connexion a réussi. Vous pouvez maintenant naviguer.");

            try {
                String role = UsersRepository.getRole(email);

                if ("ADMIN".equals(role)) {
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.menu(email);
                } else if ("USER".equals(role)) {
                    UserMenu userMenu = new UserMenu();
                    userMenu.menu(email);
                } else {
                    System.out.println("Rôle inconnu. Contactez un administrateur.");
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération du rôle : " + e.getMessage());
            }
        }

        scanner.close();
    }
}
