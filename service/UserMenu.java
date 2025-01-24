package service;

import repository.UsersRepository;

import java.util.Scanner;

public class UserMenu {
    public UserMenu() {
    }

    public void menu(String email) {
        UsersRepository usersRepository = new UsersRepository();
        String pseudo = usersRepository.getPseudo(email);
        System.out.println("Bienvenue USER " + pseudo);
        System.out.println("Choissier ce que vous shouaiter faire");
        System.out.println("1 Gestion des stocks");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("FEATURE EN COURT DE DEV");
                break;
        }
    }
}
