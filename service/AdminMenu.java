package service;

import repository.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public AdminMenu() {
    }

    public void menu(String email) throws SQLException {

        UsersRepository usersRepository = new UsersRepository();

        String pseudo = usersRepository.getPseudo(email);

        System.out.println("====================================");
        System.out.println("Bienvenue ADMIN " + pseudo);
        System.out.println("====================================");
        System.out.println("Choisissez ce que vous souhaitez faire :");
        System.out.println("1 - Créer un produit");
        System.out.println("2 - Créer un Shop");
        System.out.println("3 - Créer un utilisateur");
        System.out.println("4 - Gestion produit");
        System.out.println("5 - Modification utilisateur");
        // ajouter un employer a un shop, ajouter un item a un shop, gérer les stocks
        System.out.println("6 - Gestion Shops");
        System.out.println("7 - Supprimer utilisateur");
        System.out.println("8 - Supprimer produit");
        System.out.println("9 - Supprimer Shop");
        System.out.println("10 - Quitter");
        System.out.println("====================================");
        System.out.print("Choisissez vous souhaitez faire (entre 1 et 10) : ");
        UserService userService = new UserService();

        Scanner scanner = new Scanner(System.in);
        int option = -1;

        while (true) {
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                if (option >= 1 && option <= 10) {
                    break;
                } else {
                    System.out.println("Nombre invalide, veuillez choisir un numéro entre 1 et 10.");
                }
            } else {
                System.out.println("Veuillez entrer un nombre entier pour l'option (entre 1 et 10) :");
                scanner.next();
            }
        }

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
                ProductMenu(email);
                break;
            case 5:
                int id=userService.displayAllUsers();
                ModificateUser(id);
                menu(email);
                break;
            case 6:
                StoreMenu();
                menu(email);
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
            ItemsService itemsService = new ItemsService();
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

        String mail;
        while (true) {
            System.out.print("Email du user à ajouter à la whiteliste : ");
            mail = scanner.nextLine();
            //^ indique le début de la chaine
            //\w.- correspond à toutes les lettres et chiffre, accepte le point et tiret
            //@ symble obligatoire
            //[a-zA-Z\d.-] correspond au nom de domaine
            //[a-zA-Z]{2,} correspond à l'extension du domaine
            //$ fin de la chaine
            if (mail.matches("^[\\w.-]+@Istore\\.com$")) {
                break;
            } else {
                System.out.println("Erreur : Veuillez entrer une adresse email valide !");
            }
        }

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

    private void ProductMenu(String email) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("Gestion produit :");
        System.out.println("====================================");
        System.out.println("1 - Changer le nom d'un produit");
        System.out.println("2 - Changer le prix d'un produit");
        System.out.println("====================================");

        ItemsService itemsService = new ItemsService();
        ItemsRepository itemsRepository = new ItemsRepository();
        List<ItemsService> listeItems = itemsRepository.getAtributItems();

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                ItemsService item = itemsService.DisplayItems(listeItems);
                String name = item.getName();
                System.out.println("Veuillez rentrer le nouveau nom du produit :");
                String newName = scanner.nextLine();

                ItemsRepository itemRepository = new ItemsRepository();
                itemRepository.updateName(name, newName);

                menu(email);
                break;

            case 2:
                ItemsService items = itemsService.DisplayItems(listeItems);
                String names = items.getName();
                System.out.println("Veuillez rentrer le nouveau prix du produit :");
                int newPrice = scanner.nextInt();

                ItemsRepository itemsRepository1 = new ItemsRepository();
                itemsRepository1.updatePrice(newPrice, names);

                menu(email);
                break;

            default:
                System.out.println("Option invalide, veuillez réessayer.");
                ProductMenu(email);
                break;
        }
    }


    private void StoreMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("Que souhaiter vous faire ?");
        System.out.println("====================================");
        System.out.println("1. Liste des magasins");
        System.out.println("2. Ajouter un employer a un shop");
        System.out.println("3. Ajouter un produit a un shop");
        System.out.println("4. Suprimer un produit a un shop");
        System.out.println("5. Suprimer un employer a un shop");
        System.out.println("6. Gestions des stocks");
        ShopService shopService = new ShopService();
        UserService userService = new UserService();
        EmployeesRepository employeesRepository = new EmployeesRepository();
        ItemsService itemsService = new ItemsService();
        InventoryRepository inventoryRepository = new InventoryRepository();

        switch (scanner.nextInt()) {
            case 1:
                //return un id
                // afficher la liste des magasin + choice magasin
                int id=shopService.displayShop();
                //menu pour sois lister les employer ou les produits
                ShopMenuEmployerOrProduct(id);
                break;
            case 2:
                int id2=shopService.displayShop();
                //list employer + choice employer
                int id21=userService.DisplayEmployees();
                employeesRepository.addEmployees(id2,id21);
                // ajouter un employer au shop
                break;
            case 3:
                //liste shop + choice shop
                int id3=shopService.displayShop();
                //liste produit + choice produit
                int id31=itemsService.displayItemsForShop();
                //Ajouter un produit au shop
                inventoryRepository.addItemsInShope(id3,id31);
                break;
            case 4:
                //list des shop + choice magasin
                int id4=shopService.displayShop();
                int id41=itemsService.displayItemsDeletedForShop(id4);
                inventoryRepository.deletItemsInShope(id4,id41);
                break;
            case 5:
                //list des shop + choice magasin
                int id5=shopService.displayShop();
                int id51=userService.DisplayEmployeesForShop(id5);
                employeesRepository.deleteEmployee(id5,id51);
                break;
            case 6:
                // liste des shop + choice shop
                int id6=shopService.displayShop();
                // Menu supp or add stock
                MenuGestionStock(id6);
                break;
        }
    }

    private void ShopMenuEmployerOrProduct(int shopid) throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("Que souhaiter vous faire ?");
        System.out.println("====================================");
        System.out.println("1. Liste des employers");
        System.out.println("2. Liste des produits");
        System.out.println("====================================");
        int option = scanner.nextInt();
        EmployeesRepository employeesRepository = new EmployeesRepository();
        ItemsRepository itemsRepository = new ItemsRepository();
        switch (option){
            case 1:
                //affichage des employer du shop choisi
                employeesRepository.displayEmployees(shopid);
                break;
            case 2:
                //affichage des produits du shop choisi
                itemsRepository.displayItemForShop(shopid);
                break;
        }
    }

    public void MenuGestionStock(int shopId) throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("Que souhaiter vous faire ?");
        System.out.println("====================================");
        System.out.println("1. Augmenter le stock d'un produit ");
        System.out.println("2. Retirer le stock d'un produit ");
        System.out.println("====================================");
        int option = scanner.nextInt();
        ItemsService itemsService = new ItemsService();
        InventoryRepository inventoryRepository = new InventoryRepository();
        switch (option){
            case 1:
                int itemAdd=itemsService.displayItemsDeletedForShop(shopId);
                inventoryRepository.addStockInShope(itemAdd);
                break;
            case 2:
                int itemSup=itemsService.displayItemsDeletedForShop(shopId);
                inventoryRepository.delStockInShope(itemSup);
                break;
        }
    }
    //public void updateUser(int userId, String newEmail, String newFirstName, String newLastName, String newPseudo)

    public void ModificateUser(int userId) throws SQLException {
        UsersRepository usersRepository = new UsersRepository();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nMise à jour de votre compte :");
        System.out.print("Nouvel email : ");
        String newEmail = scanner.nextLine();

        if (!newEmail.matches("^[\\w.-]+@Istore\\.com$")) {
            System.out.println("Erreur : Veuillez entrer une adresse email valide !");
            return;
        }

        System.out.print("Nouveau pseudo : ");
        String newPseudo = scanner.nextLine();
        System.out.print("Nouveau prénom : ");
        String newFirstName = scanner.nextLine();
        System.out.print("Nouveau nom : ");
        String newLastName = scanner.nextLine();

        usersRepository.updateUser(userId, newEmail, newFirstName, newLastName, newPseudo);
        System.out.println("Vos informations ont été mises à jour avec succès.");
        System.out.println("Retour au menu principal...");
    }
}