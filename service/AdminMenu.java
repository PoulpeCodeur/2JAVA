package service;

import repository.UsersRepository;
import repository.ItemsRepository;

import java.util.Scanner;

public class AdminMenu {
    public AdminMenu() {
    }

    public void menu(String email) {

        UsersRepository usersRepository = new UsersRepository();
        ItemsRepository itemsRepository = new ItemsRepository();
        ItemsService itemsService = new ItemsService(itemsRepository);

        String pseudo=usersRepository.getPseudo(email);

        System.out.println("Bienvenue ADMIN " + pseudo);
        System.out.println("Choissier ce que vous shouaiter faire");
        System.out.println("1 Créer un produit");
        System.out.println("2 Créer un Shop");
        System.out.println("3 Créer un utilisateur");
        //Gestion  produit changer le prix, le nom
        System.out.println("4 Gestion produit");
        //Gestion utilisateur changer le nom , l'email , pseudo , le role , mdp
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
                createProduct(itemsService);
                break;
            case 2:
                System.out.println("FEATURE EN COURT DE DEV");
                break;
            case 3:
                System.out.println("FEATURE EN COURT DE DEV");
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
                System.out.println("FEATURE EN COURT DE DEV");
                break;
            case 8:
                System.out.println("FEATURE EN COURT DE DEV");
                break;
            case 9:
                System.out.println("FEATURE EN COURT DE DEV");
                break;
        }
    }

    private void createProduct(ItemsService itemsService) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Créer un produit :");

        System.out.print("Nom du produit : ");
        String name = scanner.nextLine();

        System.out.print("Prix du produit : ");
        double price = scanner.nextDouble();

        System.out.print("Quantité du produit : ");
        int quantity = scanner.nextInt();

        System.out.print("ID d'inventaire : ");
        int inventoryId = scanner.nextInt();

        try {
            itemsService.createNewItems(name, price, quantity, inventoryId);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
