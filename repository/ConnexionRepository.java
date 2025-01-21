package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionRepository {
    private final static String URL = "jdbc:mysql://localhost:3307/supinfo";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de charger le driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Impossible de se connecter à la base de données.");
            e.printStackTrace();
        }
        return connexion;
    }
}
