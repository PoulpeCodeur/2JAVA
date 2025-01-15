package src;

import repository.ConnexionRepository;

import java.sql.Connection;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection = ConnexionRepository.getConnection();
        if (connection != null) {
            System.out.println("Connexion établie avec succès !");
            try {
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Erreur lors de la fermeture de la connexion.");
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }
    }
}

