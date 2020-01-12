package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    String login;
    List<User> usersArray = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

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

    //метод добавления пользователей в Arraylist
    public void addUser(User user) {
        if (usersArray.size() < 100) {
            usersArray.add(user);
        } else {
            System.out.println("Chat zapolnen");
        }
    }

    //метод регистрации в чате
    public void registration() {
        System.out.println("Введите ваш новый логин: ");
        String login = scanner.nextLine();

        for (User element : usersArray) {
            while (element.getLogin().equalsIgnoreCase(login)) {
                System.out.println("Такой логин уже есть. Попробуйте другой");
                login = scanner.nextLine();
            }
        }
        User user1 = new User(login);
        addUser(user1);

    }

    //метод проверки логина в базе
    public boolean searchByLogin(String login) {
        for (User element : usersArray) {
            if (element.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }

    //метод для получения списка всех пользователей
    public void showUsers() {
        for (User element : usersArray) {
            System.out.println(element.getLogin());
        }
    }

    //функция приватных сообщений
    public void privateMessages() {
        System.out.println("Введите логин прользователя, кому вы хотите написать");
        String string2 = scanner.nextLine();

        for (User element : usersArray) {
            if (element.getLogin().equalsIgnoreCase(string2)) {
                System.out.println("Такой пользователь есть");
                break;
            }

        }
        System.out.println("Введите текст сообщения для пользователя " + string2);
        String string3 = scanner.nextLine();
        System.out.println("Пользователь: " + string2 + " ,Вам сообщение: " + string3);
    }

    //чат комната
    public void chatRoom() {
        System.out.println("Введите логины пользователей, с которыми вы хотите начать общение");
        String string3 = scanner.nextLine();

        String[] arraysString = string3.split("[, ?.@]+");

        for (String element : arraysString) {
            System.out.println(element);
        }

       /*
       
       проверка есть ли такие имена в базе

        */

    }


    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", usersArray=" + usersArray +
                '}';
    }
}