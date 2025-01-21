package service;

import repository.ConnexionRepository;

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
        //J'ai mis la vérification ici pour que l'email soit vérifier directement,
        //Sinon il faut terminer tout le processus d'inscription.
        if (!checkWhitelist(email)) {
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

        if (saveUser(email, firstName, lastName, pseudo, password)) {
            System.out.println("Votre inscription à été prise en compte, vous pouvez désormais vous connecter");
        } else {
            System.out.println("Une erreur s'est déroulé lors de l'inscription.");
        }
    }
    //Sert à vérifier la présence de l'email dans la whitelist
    private static boolean checkWhitelist(String email) {
        String query = "SELECT * FROM WHITELIST WHERE email = ?";
        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Sauvegarde les données user dans la bdd
    private static boolean saveUser(String email, String firstName, String lastName, String pseudo, String password) {
        String query = "INSERT INTO USERS (email, first_name, last_name, pseudo, password, role, created_at) " + "VALUES (?, ?, ?, ?, ?, 'USER', NOW())";
        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, pseudo);
            //Je vais ajouter un SHA-256 pour hacher les mots de passe
            // Afin qu'ils ne soient pas en clair
            preparedStatement.setString(5, password);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
