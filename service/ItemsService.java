package service;

import repository.ItemsRepository;

import java.sql.SQLException;

public class ItemsService {
    private final ItemsRepository itemsRepository;

    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
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
}
