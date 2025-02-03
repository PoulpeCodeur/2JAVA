package repository;

import service.ItemsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
}
