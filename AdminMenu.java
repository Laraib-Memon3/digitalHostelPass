import java.util.Scanner;

public class AdminMenu {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. View All Students");
            System.out.println("2. Search Student");
            System.out.println("3. Add Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Sort Students");
            System.out.println("6. Approve Student Application");
            System.out.println("7. Reject Student Application");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1 -> DataHandler.displayAllStudents();

                case 2 -> {

                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    DataHandler.searchStudent(id);
                }

                case 3 -> DataHandler.addStudent();

                case 4 -> {

                    System.out.print("Enter Student ID to remove: ");
                    int rid = sc.nextInt();
                    DataHandler.removeStudent(rid);

                }

                case 5 -> DataHandler.sortStudents();

                case 6 -> {

                    System.out.print("Enter Student ID to approve: ");
                    int approveId = sc.nextInt();
                    DataHandler.approveStudent(approveId);
                }

                case 7 -> {

                    System.out.print("Enter Student ID to reject: ");
                    int rejectId = sc.nextInt();
                    DataHandler.rejectStudent(rejectId);
                }

                case 8 -> System.out.println("Exiting Admin Menu...");

                default -> System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 8);
    }
}
