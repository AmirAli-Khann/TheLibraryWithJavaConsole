package com.amirali.fakhrabadi;

import java.util.Scanner;

public class InputController {

    private static Scanner scanner;

    public static String getCommand(String prompt) {
        scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public static int getInteger(String promp) {
        System.out.println(promp);
        while (true) {
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }else InputController.showMassage("Please Enter a Numeric Value ");
        }
    }

    public static void showMassage(String prompt) {

        System.out.println(prompt);
    }

    public static void showMassageInLine(String prompt) {
        System.out.print(prompt);
    }

    public static void showFormattedMassage(int ID, String book) {
        System.out.printf("%d       %s\n", ID, book);
    }

}

