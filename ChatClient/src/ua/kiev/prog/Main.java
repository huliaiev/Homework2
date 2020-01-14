package ua.kiev.prog;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        try {
            user.addUser(new User("artem"));
            user.addUser(new User("petro"));
            user.addUser(new User("olga"));
            user.addUser(new User("nastya"));
            user.addUser(new User("dima"));

            System.out.println("Если у вас нет логина, необходима регистрация. Хотите зарегистрироваться сейчас? (Y/N)");
            String registration = scanner.nextLine();

            while (!registration.equalsIgnoreCase("Y") && (!registration.equalsIgnoreCase("N") )) {
                System.out.println("Повторите попытку ввода. Введите Y или N ");
                registration = scanner.nextLine();
            }

            if (registration.equalsIgnoreCase("Y")) {
                user.registration();
            }

            if (registration.equalsIgnoreCase("N")) {
                String text1 = "Используйте ранее зарегистрированный логин";
                System.out.println(text1);
            }

            System.out.println("Enter your login: ");
            String login1 = scanner.nextLine();

            System.out.println(user.searchByLogin(login1));

            if (user.searchByLogin(login1) == true)  {
                System.out.println("Добро пожаловать в систему");
            }

            if (user.searchByLogin(login1) == false) {
                System.out.println("Такого логина нет");

                while (user.searchByLogin(login1) == false) {
                    System.out.println("Повторите попытку ввода логина");
                    login1 = scanner.nextLine();
                }
            }

            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            System.out.println("Нажмите 1, чтобы получить список всех пользователей ");
            System.out.println("Нажмите 2, чтобы написать сообщение пользователю ");
            System.out.println("Нажмите 3, чтобы создать чат ");

            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) break;

                Message m = new Message(login1, text);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }

                switch (text) {
                    case "1":
                        user.showUsers();
                        break;
                    case "2":
                        user.privateMessages();
                        break;
                    case "3":
                        user.chatRoom();
                        break;

                    default:
                        return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

}
