package service;

import repository.WhitelisteRepository;
import repository.UsersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Register {
    public static void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" --- Inscription --- ");

        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();
        WhitelisteRepository whitelisteRepository = new WhitelisteRepository();
        if (!whitelisteRepository.checkWhitelist(email)) {
            System.out.println("Votre email n'a pas l'autorisation de s'inscrire. Contactez votre administrateur.");
            return;
        }

        System.out.print("Entrez votre prénom : ");
        String firstName = scanner.nextLine();

        System.out.print("Entrez votre nom de famille : ");
        String lastName = scanner.nextLine();

        System.out.print("Entrez votre pseudo : ");
        String pseudo = scanner.nextLine();

        System.out.print("Entrez votre mot de passe : ");
        String password = scanner.nextLine();

        UsersRepository usersRepository = new UsersRepository();
        if (usersRepository.saveUser(email, firstName, lastName, pseudo, password)) {
            System.out.println("Votre inscription à été prise en compte, vous pouvez désormais vous connecter");
        } else {
            System.out.println("Une erreur s'est déroulé lors de l'inscription.");
        }
    }

}
