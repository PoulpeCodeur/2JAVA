package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemsRepository {
    public ItemsRepository() {}

    public void CreatItems(String name, double price , int quantity) throws SQLException {
        String query = "INSERT INTO ITEMS (NAME, PRICE, QUANTITY, SHOP) VALUES (?,?,?,FALSE)";

        try(Connection connexion = ConnexionRepository.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();

            System.out.println("\n====================================");
            System.out.println("L'article a été créé avec succès !");
            System.out.println("Cependant, cet article n'est pas encore lié à un shop.");
            System.out.println("====================================\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listProducts() throws SQLException {
        String query = "SELECT * FROM ITEMS";

        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                System.out.println("ID: " + id + ", Nom: " + name + ", Prix: " + price + "€");
            }

            System.out.println("====================================\n");

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteItem(int id) throws SQLException {
        String query = "DELETE FROM ITEMS WHERE ID = ?";

        try(Connection connexion = ConnexionRepository.getConnection()) {
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("\n====================================");
            System.out.println("Le produit avec l'ID " + id + " a bien été supprimé.");
            System.out.println("====================================\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
