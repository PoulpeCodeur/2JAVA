package service;

import repository.ItemsRepository;

public class ItemsService {
    private final ItemsRepository itemsRepository;

    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public void createNewItems(String name, double price, int quantity, int inventoryId) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Un nom est obligatoire pour créer l'article");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Le prix doit être supérieur à 0");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("La quantité ne peux pas être inférieur à 0");
        }

        Items newItem = new Items(name, price, quantity);
        newItem.setInventory_id(inventoryId);

        itemsRepository.addItems(newItem);
        System.out.println("L'article à été ajouté avec succès !");

    }
}
