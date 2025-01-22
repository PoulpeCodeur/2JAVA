package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import repository.ConnexionRepository;

public class UsersRepository {

    public UsersRepository() {
    }

    public static boolean saveUser(String email, String firstName, String lastName, String pseudo, String password) {
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
