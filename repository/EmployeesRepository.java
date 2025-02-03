package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeesRepository {
    public EmployeesRepository() {
    }

    public void displayEmployees(int id){
        String sql ="SELECT U.FIRST_NAME, U.LAST_NAME FROM EMPLOYEES E JOIN USERS U ON E.USER_ID = U.ID WHERE E.STORE_ID = ?";
        try (Connection connection=ConnexionRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("====================================");
            System.out.println("=========Liste des Employers========");
            while (resultSet.next()) {
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                System.out.println(firstName + " " + lastName);
            }
            System.out.println("====================================");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public void addEmployees(int shop, int user){
        String sql ="INSERT EMPLOYEES (USER_ID, STORE_ID) VALUES (?,?)";
        try (Connection connection=ConnexionRepository.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql)){
            preparedStatement.setInt(1, user);
            preparedStatement.setInt(2, shop);
            preparedStatement.execute();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
