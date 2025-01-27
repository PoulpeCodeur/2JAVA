package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
