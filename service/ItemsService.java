package service;

import repository.InventoryRepository;
import repository.ItemsRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ItemsService {
    private int id;
    private String name;
    private double price;
    private int quantity;


    public ItemsService() {
    }

    public ItemsService( String name, double price) {

        this.name = name;
        this.price = price;
    }

    public ItemsService(int id, String name, double price,int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void CheckItems(String name, double price, int quantity) throws SQLException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Un nom est obligatoire pour créer l'article");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Le prix doit être supérieur à 0");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("La quantité ne peux pas être inférieur à 0");
        }

        ItemsRepository itemsRepository = new ItemsRepository();
        itemsRepository.CreatItems(name,price,quantity);
    }

    public ItemsService DisplayItems(List<ItemsService> items) throws SQLException {
        int i=1;
        for (ItemsService item : items) {
            System.out.println("====================================");
            System.out.println(i+" - "+"Produit : "+item.getName()+" - Prix : "+item.getPrice() + "€");
            i++;
        }
        ItemsService item=choiseItems(items);
        return item;
    }
    public ItemsService choiseItems(List<ItemsService> items) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel item souhaitez vous sélectionner ? ");
        int choice = scanner.nextInt();
        ItemsService itemchoise= items.get(choice-1);
        return itemchoise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int displayItemsForShop() throws SQLException {
        System.out.println("====================================================================");
        System.out.println("Voici la liste des items, quel item souhaitez vous sélectionner ?");
        System.out.println("====================================================================");
        InventoryRepository shopRepository = new InventoryRepository();
        List<ItemsService> items=shopRepository.getItemsNoHaveStore();
        int i=1;
        System.out.println("==================================================================");
        for (ItemsService item : items) {
            System.out.println(i+"- "+item.getName()+" au prix de "+item.getPrice()+"pour une quantité de "+item.getQuantity());
            i++;
        }
        System.out.println("==================================================================");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        int id = items.get(choice-1).getId();
        return id;
    };
    public int displayItemsDeletedForShop(int id) throws SQLException {
        System.out.println("====================================================================");
        System.out.println("Voici la liste des items qu'elle items souhaiter vous séléctionner ?");
        System.out.println("====================================================================");
        InventoryRepository shopRepository = new InventoryRepository();
        List<ItemsService> items=shopRepository.getItemsHaveStore(id);
        int i=1;
        System.out.println("==================================================================");
        for (ItemsService item : items) {
            System.out.println(i+"- "+item.getName()+" aux prix de "+item.getPrice()+"pour une quantité de "+item.getQuantity());
            i++;
        }
        System.out.println("==================================================================");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        int itemId = items.get(choice-1).getId();
        return itemId;
    };
}
