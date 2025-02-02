package repository;

import java.sql.*;

public class StoresRepository {
    public StoresRepository() {
    }

    public  static void createStore(int userId , String storeName ) throws SQLException {
        String query="INSERT INTO STORES (NAME, CREATED_BY) VALUES(?,?)";

        try(Connection connection = ConnexionRepository.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(query)) {
            preparedStatement.setString(1, storeName);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

            System.out.println("Le shop à été créé avec succès !");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listStores() throws SQLException {
        String query = "SELECT * FROM STORES";

        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String createdBy = resultSet.getString("CREATED_BY");
                Timestamp createdAt = resultSet.getTimestamp("CREATED_AT");

                System.out.println("Id : " + id + ", Nom du shop : " + name +
                        ", Créé par : " + createdBy + ", Créé le : " + createdAt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteStore(int id) throws SQLException {
        String query = "DELETE FROM STORES WHERE ID = ?";

        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Le magasin avec l'ID " + id + " a été supprimé.");
            } else {
                System.out.println("Aucun magasin trouvé avec l'ID " + id + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

