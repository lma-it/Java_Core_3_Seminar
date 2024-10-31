package seminar3homework.service;

import java.util.Scanner;

public class InputService {
    private static final Scanner scanner = new Scanner(System.in);

    public static String input(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

}
