import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private String status;

    public Student(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Status: " + status);
    }
}

public class DataHandler {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Add Student
    public static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Status (In Hostel / At Home): ");
        String status = sc.nextLine();

        students.add(new Student(id, name, status));
        System.out.println("Student added successfully!");
    }

    // Remove Student safely
    public static void removeStudent(int id) {
        boolean removed = students.removeIf(s -> s.getId() == id);
        if (removed) {
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Search Student
    public static void searchStudent(int id) {
        Student s = findStudentById(id);
        if (s != null) {
            System.out.println("Student Found:");
            s.display();
        } else {
            System.out.println("Student not found!");
        }
    }

    // Sort Students by ID
    public static void sortStudents() {
        students.sort((a, b) -> Integer.compare(a.getId(), b.getId()));
        System.out.println("Students sorted by ID!");
    }

    // View All Students
    public static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            s.display();
        }
    }

    // Approve Student
    public static void approveStudent(int id) {
        Student s = findStudentById(id);
        if (s != null) {
            s.setStatus("Approved");
            System.out.println("Student ID " + id + " approved.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Reject Student
    public static void rejectStudent(int id) {
        Student s = findStudentById(id);
        if (s != null) {
            s.setStatus("Rejected");
            System.out.println("Student ID " + id + " rejected.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Helper to find student by ID
    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}
