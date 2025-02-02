package service;

import repository.ItemsRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ItemsService {
    private String name;
    private double price;


    public ItemsService() {
    }

    public ItemsService( String name, double price) {

        this.name = name;
        this.price = price;
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
            System.out.println(i+"- "+"Product: "+item.getName()+"aux prix de "+item.getPrice());
            i++;
        }
        ItemsService item=choiseItems(items);
        return item;
    }
    public ItemsService choiseItems(List<ItemsService> items) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Qu'elle items souhaiter vous séléctionner");
        int choice = scanner.nextInt();
        ItemsService itemchoise= items.get(choice-1);
        return itemchoise;
    }
}
