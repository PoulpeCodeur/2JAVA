package repository;

import service.ItemsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryRepository {

    public InventoryRepository() {
    }

    public List<ItemsService> getItemsNoHaveStore() {
        String sql ="SELECT ID, NAME, PRICE, QUANTITY FROM ITEMS WHERE ID NOT IN (SELECT ITEM_ID FROM INVENTORY WHERE ITEM_ID IS NOT NULL);";
        try (Connection connection=ConnexionRepository.getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ItemsService> items = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                double price = resultSet.getDouble("PRICE");
                int quantity = resultSet.getInt("QUANTITY");
                items.add(new ItemsService(id, name, price, quantity));
            }
            return items;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    };

    public List<ItemsService> getItemsHaveStore(int idShop) {
        String sql="SELECT I.ID, I.NAME, I.PRICE, I.QUANTITY \n" +
                "FROM ITEMS I " +
                "JOIN INVENTORY INV ON I.ID = INV.ITEM_ID \n" +
                "WHERE INV.STORE_ID = ?;";
        try (Connection connection=ConnexionRepository.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setInt(1, idShop);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ItemsService> items = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                double price = resultSet.getDouble("PRICE");
                int quantity = resultSet.getInt("QUANTITY");
                items.add(new ItemsService(id, name, price, quantity));
            }
            return items;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void  addItemsInShope(int shopId,int itemId){
        String sql ="INSERT INTO INVENTORY (STORE_ID,ITEM_ID) VALUES(?,?)";
        try(Connection connection=ConnexionRepository.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, shopId);
            preparedStatement.setInt(2, itemId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    public void deletItemsInShope(int shopId,int itemId){
        String sql ="DELETE FROM INVENTORY WHERE STORE_ID = ? AND ITEM_ID = ?";
        try (Connection connection=ConnexionRepository.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setInt(1, shopId);
            preparedStatement.setInt(2, itemId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addStockInShope(int itemId){
        String sql="UPDATE ITEMS SET QUANTITY = QUANTITY + ? WHERE ID = ?";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Séléctionner la quantitté que vous vouler ajouter !");
        int quantity = scanner.nextInt();
        try (Connection connection=ConnexionRepository.getConnection();
        PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, itemId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delStockInShope(int itemId){
        String sql="UPDATE ITEMS SET QUANTITY = CASE WHEN QUANTITY - ? < 0 THEN 0 ELSE QUANTITY - ? END WHERE ID = ?;";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Séléctionner la quantitté que vous vouler enlever !");
        int quantity = scanner.nextInt();
        try (Connection connection=ConnexionRepository.getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setInt(3, itemId);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
