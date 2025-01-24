package service;

import repository.ConnexionRepository;
import repository.UsersRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    public static void Login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" --- Connexion --- ");

        System.out.print("Entrez votre mail : ");
        String email = scanner.nextLine();

        System.out.print("Entrez votre mot de passe : ");
        String password = scanner.nextLine();

        UsersRepository usersRepository = new UsersRepository();
        if (usersRepository.authenticate(email, password)) {
            System.out.println("La connexion à réussie. Vous pouvez maintenant naviguer");
            String role = usersRepository.getRole(email);
            if (role.equals("ADMIN")) {
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.menu(email);

            } else if (role.equals("USER")) {
                UserMenu userMenu = new UserMenu();
                userMenu.menu(email);
            }

        } else {
            System.out.println("La connexion à échouée. Merci de vérifier vos identifiants.");
        }
    }

}
