package repository;

import service.ItemsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<ItemsService> getAtributItems(){
        String query = "SELECT NAME, PRICE FROM ITEMS GROUP BY NAME, PRICE ORDER BY NAME ASC, PRICE ASC";
        try (Connection connection=ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()){
            List<ItemsService> items= new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                double price = resultSet.getDouble("PRICE");
                items.add(new ItemsService(name, price));
            }return items;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
    public void updateName(String oldName, String newName) throws SQLException {
        String query = "UPDATE ITEMS SET NAME = ? WHERE NAME = ?";

        try (Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, oldName);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " ligne(s) mise(s) à jour.");
        }
    }

    public void updatePrice(double newPrice ,String name) throws SQLException {
        String query = "UPDATE ITEMS SET PRICE = ? WHERE NAME = ?";
        try (Connection connexion = ConnexionRepository.getConnection();
        PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setString(2, name);
            int rowsAffected = preparedStatement.executeUpdate();

        }
    }

}
