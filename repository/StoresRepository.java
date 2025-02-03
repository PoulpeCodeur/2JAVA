package repository;

import service.ShopService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoresRepository {
    public StoresRepository() {
    }

    public static void createStore(int userId, String storeName) throws SQLException {
        String query = "INSERT INTO STORES (NAME, CREATED_BY) VALUES(?, ?)";

        try (Connection connection = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, storeName);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

            System.out.println("\n====================================");
            System.out.println("Le shop a été créé avec succès !");
            System.out.println("====================================\n");

        } catch (SQLException e) {
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

                System.out.println("ID : " + id + ", Nom du shop : " + name +
                        ", Créé par : " + createdBy + ", Créé le : " + createdAt);
            }

            System.out.println("====================================\n");

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
                System.out.println("\n====================================");
                System.out.println("Le magasin avec l'ID " + id + " a bien été supprimé.");
                System.out.println("====================================\n");
            } else {
                System.out.println("\n====================================");
                System.out.println("Aucun magasin trouvé avec l'ID " + id + ".");
                System.out.println("====================================\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<ShopService> getShopAtribute(){
        String query = "SELECT NAME ,ID FROM STORES";

        try(Connection connection = ConnexionRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery()) {
            List<ShopService> shops = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int id = resultSet.getInt("ID");
                shops.add(new ShopService(name,id));
            }return shops;

        }catch (SQLException e){
            e.printStackTrace();
        };
        return null;
    }
}
