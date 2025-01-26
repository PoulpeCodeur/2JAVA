package service;

public class Items {
    private int id;
    private int inventory_id;
    private String name;
    private double price;
    private int quantity;

    public Items(int id, int inventory_id, String name, double price, int quantity) {
        this.id = id;
        this.inventory_id = inventory_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Items(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

