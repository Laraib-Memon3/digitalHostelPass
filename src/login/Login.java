package login;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import student.Students;

public class Login {
    private static final String CREDENTIALS_FILE = "src/data/admin_credentials.txt";
    private static final Scanner SCANNER = new Scanner(System.in);

    // Authenticate admin credentials from file
    public static boolean authenticateAdmin() {
        System.out.print("Enter admin username: ");
        String username = SCANNER.nextLine().trim();
        System.out.print("Enter admin password: ");
        String password = SCANNER.nextLine().trim();

        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            String storedUser = reader.readLine().trim();
            String storedPass = reader.readLine().trim();

            return username.equals(storedUser) && password.equals(storedPass);
        } catch (IOException e) {
            System.out.println("Error reading credentials file: " + e.getMessage());
            e.printStackTrace(); // This will print the full stack trace
            return false;
        }
    }

    // Authenticate student using ArrayList/LinkedList data
   public static Students authenticateStudent(List<Students> students) {
    try {
        System.out.println("DEBUG: Starting student authentication...");

        System.out.print("Enter your CMS ID: ");
        String id = SCANNER.nextLine().trim();
        System.out.print("Enter your password: ");
        String pass = SCANNER.nextLine().trim();
        System.out.println("DEBUG: Received input ID=" + id + ", Pass=" + pass);

        if (students == null) {
            System.out.println("DEBUG: Students list is null!");
            return null;
        }

        System.out.println("DEBUG: Total students loaded: " + students.size());

        for (Students s : students) {
            if (s != null) {
                System.out.println("DEBUG: Checking student " + s.getCmsId());
                if (s.getCmsId().equals(id) && s.getPassword().equals(pass)) {
                    System.out.println("Welcome, " + s.getName() + "!");
                    return s;
                }
            } else {
                System.out.println("DEBUG: Found null student in list!");
            }
        }

        System.out.println("DEBUG: No matching student found.");
        return null;

    } catch (Exception e) {
        System.out.println("Error during student authentication: " + e.getMessage());
        e.printStackTrace(); 
        return null;
    }
}


    // Change admin password
    public static void changeAdminPassword() {
        System.out.print("Enter current password: ");
        String current = SCANNER.nextLine().trim();

        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            reader.readLine(); // skip username
            String storedPass = reader.readLine().trim();

            if (!current.equals(storedPass)) {
                System.out.println("Incorrect current password!");
                return;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace(); // This will print the full stack trace
            return;
        }

        System.out.print("Enter new password: ");
        String newPass = SCANNER.nextLine().trim();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CREDENTIALS_FILE))) {
            writer.write("admin\n");
            writer.write(newPass + "\n");
            System.out.println("Password updated successfully!");
        } catch (IOException e) {
            System.out.println("Error updating password: " + e.getMessage());
            e.printStackTrace(); // This will print the full stack trace
        }
    }

    // Prompt for student ID
    public static int promptStudentId() {
        System.out.print("Enter your Student ID: ");
        int id = SCANNER.nextInt();
        SCANNER.nextLine(); // consume newline
        return id;
    }
}
