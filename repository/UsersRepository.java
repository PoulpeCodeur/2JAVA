package repository;

import java.sql.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsersRepository {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static boolean saveUser(String email, String firstName, String lastName, String pseudo, String password) {
        String hashedPassword = passwordEncoder.encode(password);

        String query = "INSERT INTO USERS (email, first_name, last_name, pseudo, password, role, created_at) " + "VALUES (?, ?, ?, ?, ?, 'USER', NOW())";
        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, pseudo);
            preparedStatement.setString(5, hashedPassword);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\n====================================");
                System.out.println("L'utilisateur a été créé avec succès !");
                System.out.println("====================================\n");
            }
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean authenticate(String email, String password) {
        String query = "SELECT password FROM USERS WHERE email = ?";
        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                return passwordEncoder.matches(password, storedPassword);
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean emailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM USERS WHERE email = ?";
        try (Connection connection = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public static String getRole(String email) {
        String query = "SELECT ROLE FROM USERS WHERE email = ?";
        try (Connection connection = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("ROLE");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPseudo(String email) {
        String query = "SELECT PSEUDO FROM USERS WHERE email = ?";
        try (Connection connection = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("PSEUDO");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getUserId(String email) {
        String query = "SELECT ID FROM USERS WHERE email = ?";
        try (Connection connection = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public static void listUsers() throws SQLException {
        String query = "SELECT * FROM USERS";

        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String email = resultSet.getString("EMAIL");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String pseudo = resultSet.getString("PSEUDO");
                String role = resultSet.getString("ROLE");
                String createdAt = resultSet.getString("CREATED_AT");

                System.out.println("ID : " + id + ", Email : " + email +
                        ", Prénom : " + firstName + ", Nom : " + lastName +
                        ", Pseudo : " + pseudo + ", Rôle : " + role +
                        ", Créé le : " + createdAt);
            }

            System.out.println("====================================\n");

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateUser(int userId, String newEmail, String newFirstName, String newLastName, String newPseudo) {
        String query = "UPDATE USERS SET email = ?, first_name = ?, last_name = ?, pseudo = ? WHERE ID = ?";
        try (Connection connection = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, newFirstName);
            preparedStatement.setString(3, newLastName);
            preparedStatement.setString(4, newPseudo);
            preparedStatement.setInt(5, userId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("\n====================================");
                System.out.println("Mise à jour réussie !");
                System.out.println("====================================\n");
            } else {
                System.out.println("\n====================================");
                System.out.println("Échec de la mise à jour.");
                System.out.println("====================================\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String query = "DELETE FROM USERS WHERE ID = ?";
        try (Connection connexion = ConnexionRepository.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\n====================================");
                System.out.println("L'utilisateur avec l'ID " + id + " a été supprimé.");
                System.out.println("====================================\n");
            } else {
                System.out.println("\n====================================");
                System.out.println("Aucun utilisateur trouvé avec l'ID " + id + ".");
                System.out.println("====================================\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
