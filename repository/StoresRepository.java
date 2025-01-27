package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
