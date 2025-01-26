package repository;

import service.Items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemsRepository {
    private final Connection connection;

    public ItemsRepository() {
        this.connection = ConnexionRepository.getConnection();
    }

    public boolean addItems(Items items) {
        String sql = "INSERT INTO ITEMS (NAME, PRICE, QUANTITY) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, items.getName());
            statement.setDouble(2, items.getPrice());
            statement.setInt(3, items.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
