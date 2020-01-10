package ua.kiev.prog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("в нашем чате нужна регистрация. Хотите зарегистрироваться ?");
        String registration = scanner.nextLine();

        if (registration.equalsIgnoreCase("Y")) {
            user.registration();
        }

        if (registration.equalsIgnoreCase("N")) {
            System.out.println("используйте ранее зарегистрированный логин");
        }

        try {
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();

            /*
            Проверка зарегистрирован ли такой логин

             */

            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) break;

                Message m = new Message(login, text);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
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
