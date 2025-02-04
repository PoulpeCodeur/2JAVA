package repository;

import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<UserService> EmployeesForDelete(int id){
        String sql ="SELECT U.ID,U.EMAIL,U.FIRST_NAME,U.LAST_NAME,U.PSEUDO,U.ROLE FROM EMPLOYEES E JOIN USERS U ON E.USER_ID = U.ID WHERE E.STORE_ID = ?;";
        try (Connection connection=ConnexionRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("====================================");
            System.out.println("=========Liste des Employers========");
            List<UserService> employees = new ArrayList<>();
            while (resultSet.next()) {
                int userId = resultSet.getInt("ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String email = resultSet.getString("EMAIL");
                String pseudo = resultSet.getString("PSEUDO");
                String role = resultSet.getString("ROLE");
                employees.add(new UserService(userId, firstName, lastName, email, pseudo, role));
            }
            return employees;
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

    public void deleteEmployee(int shopid, int userid){
        String sql ="DELETE FROM EMPLOYEES WHERE USER_ID = ? AND STORE_ID = ?";
        try (Connection connection=ConnexionRepository.getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setInt(1, userid);
            preparedStatement.setInt(2, shopid);
            preparedStatement.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
