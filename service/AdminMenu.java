package service;

import repository.StoresRepository;
import repository.UsersRepository;
import repository.ItemsRepository;
import repository.WhitelisteRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminMenu {
    public AdminMenu() {
    }

    public void menu(String email) throws SQLException {

        UsersRepository usersRepository = new UsersRepository();
        ItemsRepository itemsRepository = new ItemsRepository();
        ItemsService itemsService = new ItemsService(itemsRepository);

        String pseudo = usersRepository.getPseudo(email);

        System.out.println("====================================");
        System.out.println("Bienvenue ADMIN " + pseudo);
        System.out.println("====================================");
        System.out.println("Choisissez ce que vous souhaitez faire :");
        System.out.println("1 - Créer un produit");
        System.out.println("2 - Créer un Shop");
        System.out.println("3 - Créer un utilisateur");
        System.out.println("4 - Gestion produit");
        System.out.println("5 - Gestion utilisateur");
        System.out.println("6 - Gestion Shops");
        System.out.println("7 - Supprimer utilisateur");
        System.out.println("8 - Supprimer produit");
        System.out.println("9 - Supprimer Shop");
        System.out.println("10 - Quitter");
        System.out.println("====================================");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                createProduct(email);
                System.out.println("Retour au menu principal...");
                menu(email);
                break;
            case 2:
                createShop(email);
                System.out.println("Retour au menu principal...");
                menu(email);
                break;
            case 3:
                createUser(email);
                System.out.println("Retour au menu principal...");
                menu(email);
                break;
            case 4:
                System.out.println("Feature en cours de développement...");
                break;
            case 5:
                System.out.println("Feature en cours de développement...");
                break;
            case 6:
                System.out.println("Feature en cours de développement...");
                break;
            case 7:
                deleteUsers();
                System.out.println("Retour au menu principal...");
                menu(email);
                break;
            case 8:
                deleteItem();
                System.out.println("Retour au menu principal...");
                menu(email);
                break;
            case 9:
                deleteStore();
                System.out.println("Retour au menu principal...");
                menu(email);
                break;

            case 10:
                break;

            default:
                System.out.println("Option invalide, veuillez réessayer.");
                menu(email);
                break;
        }
    }

    private void createProduct(String email) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("Créer un produit :");
        System.out.println("====================================");

        System.out.print("Nom du produit : ");
        String name = scanner.nextLine();

        System.out.print("Prix du produit : ");
        double price = scanner.nextDouble();

        System.out.print("Quantité du produit : ");
        int quantity = scanner.nextInt();

        try {
            ItemsService itemsService = new ItemsService(new ItemsRepository());
            itemsService.CheckItems(name, price, quantity);
            menu(email);

        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createShop(String email) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("Créer un shop :");
        System.out.println("====================================");

        System.out.print("Nom du shop : ");
        String name = scanner.nextLine();
        UsersRepository usersRepository = new UsersRepository();
        int userId = usersRepository.getUserId(email);
        StoresRepository storesRepository = new StoresRepository();
        storesRepository.createStore(userId, name);
    }

    private void createUser(String email) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("Créer un utilisateur :");
        System.out.println("====================================");

        System.out.print("Email du user à ajouter à la whiteliste : ");
        String mail = scanner.nextLine();
        UsersRepository usersRepository = new UsersRepository();
        int userId = usersRepository.getUserId(email);
        WhitelisteRepository whitelisteRepository = new WhitelisteRepository();
        whitelisteRepository.createWhitelist(mail, userId);
    }

    private void deleteUsers() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UsersRepository usersRepository = new UsersRepository();

        System.out.println("====================================");
        System.out.println("Liste des utilisateurs :");
        System.out.println("====================================");

        usersRepository.listUsers();

        System.out.print("ID de l'utilisateur à supprimer : ");
        int id = scanner.nextInt();

        usersRepository.deleteUser(id);

        System.out.println("====================================");
        System.out.println("L'utilisateur a bien été supprimé.");
        System.out.println("====================================");

        System.out.println("Liste des utilisateurs après suppression : ");
        usersRepository.listUsers();

    }

    private void deleteItem() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ItemsRepository itemsRepository = new ItemsRepository();

        System.out.println("====================================");
        System.out.println("Liste des produits :");
        System.out.println("====================================");

        ItemsRepository.listProducts();

        System.out.print("ID du produit à supprimer : ");
        int id = scanner.nextInt();
        itemsRepository.deleteItem(id);

        System.out.println("====================================");
        System.out.println("Le produit a bien été supprimé.");
        System.out.println("====================================");

        System.out.println("Liste des produits après suppression : ");
        itemsRepository.listProducts();

    }

    private void deleteStore() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        StoresRepository storesRepository = new StoresRepository();

        System.out.println("====================================");
        System.out.println("Liste des magasins :");
        System.out.println("====================================");

        StoresRepository.listStores();

        System.out.print("ID du magasin à supprimer : ");
        int id = scanner.nextInt();
        storesRepository.deleteStore(id);

        System.out.println("====================================");
        System.out.println("Le magasin a bien été supprimé.");
        System.out.println("====================================");

        System.out.println("Liste des magasins après suppression : ");
        StoresRepository.listStores();

    }

    private void ProductMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("Gestion produit :");
        System.out.println("====================================");
        System.out.println("1 - Changer le nom d'un produit");
        System.out.println("2 - Changer le prix d'un produit");

        int option = scanner.nextInt();
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            default:
                System.out.println("Option invalide, veuillez réessayer.");
                ProductMenu();
                break;
        }
    }
}
