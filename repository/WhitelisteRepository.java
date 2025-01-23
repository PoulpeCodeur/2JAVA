package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import repository.ConnexionRepository;

import static java.lang.Boolean.FALSE;

public class WhitelisteRepository {

    public WhitelisteRepository() {
    }

    public static boolean checkWhitelist(String email) {
        String query = "SELECT CONNEXION FROM WHITELIST WHERE email = ?";

        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                boolean isConnexion = resultSet.getBoolean("CONNEXION");
                if (!isConnexion) {
                    updateConnexionToTrue(email);
                    return true;
                }
                return false;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void updateConnexionToTrue(String email) {
        String query = "UPDATE WHITELIST SET CONNEXION = TRUE WHERE email = ?";

        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            int rowsAffected = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
