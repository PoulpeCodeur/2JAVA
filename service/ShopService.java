package service;

import repository.StoresRepository;

import java.util.List;
import java.util.Scanner;

public class ShopService {
    private String name;
    private int id;

    public ShopService(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public ShopService() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int displayShop(){
        System.out.println("==================================================================");
        System.out.println("Voici la liste des shop qu'elle shop souhaiter vous séléctionner ?");
        System.out.println("==================================================================");
        StoresRepository storesRepository = new StoresRepository();
        List<ShopService> shops= storesRepository.getShopAtribute();
        int i=1;
        System.out.println("==================================================================");
        for(ShopService shop:shops){
            System.out.println(i+"-"+shop.getName());
            i++;
        }
        System.out.println("==================================================================");
        Scanner scanner = new Scanner(System.in);
        int shop=scanner.nextInt();
        int id=shops.get(shop-1).getId();
        return id;
    }
}
