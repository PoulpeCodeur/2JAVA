package service;

import repository.EmployeesRepository;
import repository.UsersRepository;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String pseudo;
    private String role;

    public UserService() {
    }

    public UserService(int id,String email, String firstName, String lastName, String pseudo, String role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudo = pseudo;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int DisplayEmployees(){
        System.out.println("============================================================================");
        System.out.println("Voici la liste des Employees qu'elle Employees souhaiter vous séléctionner ?");
        System.out.println("============================================================================");
        UsersRepository usersRepository = new UsersRepository();
        List<UserService> users= usersRepository.getUsersNoShop();
        int i=1;
        System.out.println("==================================================================");
        for (UserService user : users) {
            System.out.println(i+"- "+user.getFirstName()+" "+user.getLastName()+" Role: "+user.getRole());
            i++;
        }
        System.out.println("==================================================================");
        Scanner scanner = new Scanner(System.in);
        int employees=scanner.nextInt();
        int id= users.get(employees-1).getId();
        return id;
    };

    public int DisplayEmployeesForShop(int shopId){
        System.out.println("============================================================================");
        System.out.println("Voici la liste des Employees qu'elle Employees souhaiter vous séléctionner ?");
        System.out.println("============================================================================");
        EmployeesRepository employeesRepository = new EmployeesRepository();
        List<UserService> users=employeesRepository.EmployeesForDelete(shopId) ;
        int i=1;
        System.out.println("==================================================================");
        for (UserService user : users) {
            System.out.println(i+"- "+user.getFirstName()+" "+user.getLastName()+" Role: "+user.getRole());
            i++;
        }
        System.out.println("==================================================================");
        Scanner scanner = new Scanner(System.in);
        int employees=scanner.nextInt();
        int id= users.get(employees-1).getId();
        return id;
    };
}
