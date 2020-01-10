package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    String login;
    List<User> usersArray = new ArrayList<>();

    public User(String login) {
        this.login = login;
    }

    public User() {
        super();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public void addUser(User user) {
        if (usersArray.size() < 100) {
            usersArray.add(user);
        } else {
            System.out.println("Chat zapolnen");
        }
    }

    public void registration() {
        System.out.println("Введите логин");
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        User user1 = new User(login);
        addUser(user1);
        System.out.println("Спасибо за регистрацию на нашем чате");
    }

    public boolean searchByLogin(String login) {
        for (User element : usersArray) {
            if (element.getLogin() == login) {
                return true;
            }
        }
        return false;
    }


}