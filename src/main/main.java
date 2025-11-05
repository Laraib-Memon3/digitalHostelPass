package main;

import admin.AdminMenu;
import student.StudentMenu;

import java.util.Scanner;

public class main {
    private static void handleChoice(char choice) {
        switch (Character.toLowerCase(choice)) {
            case 'u':
                StudentMenu.show();
                break;
            case 'a':
                AdminMenu.show();
                break;
            case 'e':
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private static void showMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("Enter 'u' for Student Menu");
        System.out.println("Enter 'a' for Admin Menu");
        System.out.println("Enter 'e' to Exit");
        System.out.print("Please enter your choice: ");

        try (Scanner scanner = new Scanner(System.in)) {
            char choice = scanner.next().charAt(0);
            handleChoice(choice);
        }
    }

    public static void main(String[] args) {
        showMainMenu();
    }
}
