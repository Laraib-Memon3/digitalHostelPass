package data;

import java.io.*;
import java.util.*;
import student.Students;

public class StudentData {
    private static final String STUDENT_FILE = "C:\\Users\\Laraib\\Documents\\digitalHostelPass\\src\\data\\students.txt";

    // Load all students from file
    public static List<Students> loadStudents() {
        List<Students> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    students.add(new Students(
                        parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
        return students;
    }

    // Save all students back to file
    public static void saveStudents(List<Students> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (Students s : students) {
                bw.write(s.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }
}
