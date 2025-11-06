package admin;
import login.Login;
public class AdminMenu {
    public static void show() {
        System.out.println("===== Admin Panel =====");
        if (Login.authenticateAdmin()) {
            System.out.println("Welcome, Admin!");
            // Admin functionalities can be added here
            //Login.changeAdminPassword();
        } else {
            System.out.println("Authentication failed! Returning to main menu.");
        }

    }
}
