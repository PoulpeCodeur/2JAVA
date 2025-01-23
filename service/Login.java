package service;

import repository.ConnexionRepository;

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

        if (authenticate(email, password)) {
            System.out.println("La connexion à réussie. Vous pouvez maintenant naviguer");
        } else {
            System.out.println("La connexion à échouée. Merci de vérifier vos identifiants.");
        }
    }

    private static boolean authenticate(String email, String password) {
        String query = "SELECT password FROM USERS WHERE email = ?";
        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return password.equals(storedPassword);
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
