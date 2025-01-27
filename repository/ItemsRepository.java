package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemsRepository {
    public ItemsRepository() {}

    public void CreatItems(String name, double price , int quantity) throws SQLException {
        String query = "INSERT INTO ITEMS (NAME, PRICE, QUANTITY,SHOP) VALUES (?,?,?,FALSE)";

        try(Connection connexion = ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();

            System.out.println("L'article à été créé avec succès !");
            System.out.println("Mais n'est pas relier a un shop");


        }catch (Exception e){
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
                System.out.println("ID: " + id + ", Nom: " + name + ", Prix: " + price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteItem(int id) throws SQLException {
        String query = "DELETE FROM ITEMS WHERE ID = ?";
        try(Connection connexion = ConnexionRepository.getConnection())
        {
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
