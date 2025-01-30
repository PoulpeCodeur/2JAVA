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

        String pseudo=usersRepository.getPseudo(email);

        System.out.println("Bienvenue ADMIN " + pseudo);
        System.out.println("Choissier ce que vous shouaiter faire");
        System.out.println("1 Créer un produit");
        System.out.println("2 Créer un Shop");
        System.out.println("3 Créer un utilisateur");
        //Gestion produit changer le prix, le nom
        System.out.println("4 Gestion produit");
        //Gestion utilisateur changer le nom, l'email, pseudo, le role, mdp
        System.out.println("5 Gestion utilisateur");
        //Gestion shop ajouter ou suprimer un employer d'un shop , ajouter un produit a un shop ou gerere les stock du shop
        System.out.println("6 Gestion Shops");
        System.out.println("7 Suprimer utilisateur");
        System.out.println("8 Supprimer produit");
        System.out.println("9 Supprimer Shops");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option){
            case 1:
                createProduct(email);
                break;
            case 2:
                createShop(email);
                break;
            case 3:
                createUser(email);
                break;
            case 4:
                System.out.println("FEATURE EN COURT DE DEV");
                break;
            case 5:
                System.out.println("FEATURE EN COURT DE DEV");
                break;
            case 6:
                System.out.println("FEATURE EN COURT DE DEV");
                break;
            case 7:
                deleteUsers();
                break;
            case 8:
                deleteItem();
                break;
            case 9:
                System.out.println("FEATURE EN COURT DE DEV");
                break;
        }
    }

    private void createProduct(String email) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Créer un produit :");

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
        System.out.println("Veuillez rentreé les parametre de votre shop");
        System.out.print("Nom du shop : ");
        String name = scanner.nextLine();
        UsersRepository usersRepository = new UsersRepository();
        int userId=usersRepository.getUserId(email);
        StoresRepository storesRepository = new StoresRepository();
        storesRepository.createStore(userId, name);

    }

    private void createUser(String email) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer l'email du user pour la whiteliste");
        System.out.println("Email du user : ");
        String mail = scanner.nextLine();
        UsersRepository usersRepository = new UsersRepository();
        int userId=usersRepository.getUserId(email);
        WhitelisteRepository whitelisteRepository = new WhitelisteRepository();
        whitelisteRepository.createWhitelist(mail,userId);
    }


    private void deleteUsers() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UsersRepository usersRepository = new UsersRepository();

        System.out.println("Liste des utilisateurs :");
        usersRepository.listUsers();

        System.out.println("ID de l'utilisateur à supprimer : ");
        int id = scanner.nextInt();

        usersRepository.deleteUsers(id);

        System.out.println("L'utilisateur a bien été supprimé.");

        System.out.println("Liste des utilisateurs après la suppression : ");
        usersRepository.listUsers();
    }

    private void deleteItem() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ItemsRepository itemsRepository = new ItemsRepository();

        System.out.println("Liste des produits :");
        ItemsRepository.listProducts();

        System.out.println("ID du produit à supprimer : ");
        int id = scanner.nextInt();
        itemsRepository.deleteItem(id);

        System.out.println("Le produit à bien été supprimé");

        System.out.println("Liste des produits après la suppression :");
        itemsRepository.listProducts();
    }

    private void ProductMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qu'est ce que vous souhaiter faire ?");
        System.out.println("\n1- changer le nom d'un produit");
        System.out.println("2- changer le prix d'un produit");

        int option = scanner.nextInt();
        switch (option){
            case 1:
                break;
            case 2:
                break;
        }
    }
}
