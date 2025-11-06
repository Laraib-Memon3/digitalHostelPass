package student;

import login.Login;
import java.util.List;

public class StudentMenu {
    
    public static void show(List<Students> students) {
        System.out.println("===== Student Login =====");
        Students authenticatedStudent = Login.authenticateStudent(students);
        
        if (authenticatedStudent != null) {
            showStudentOptions(authenticatedStudent);
        } else {
            System.out.println("Authentication failed! Returning to main menu.");
        }
    }

    private static void showStudentOptions(Students student) {
        System.out.println("\n=== Student Menu ===");
        System.out.println("1. View Profile");
        System.out.println("2. Request Pass");
        System.out.println("3. View Pass History");
        System.out.println("4. Logout");
        // Add more menu options and implementations as needed
    }
}
