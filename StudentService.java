import java.io.*;
import java.util.*;

public class StudentService {

    private static final String FILE_NAME = "students.txt";

    // Add Student
    public void addStudent(Student s) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(s.toString());
            bw.newLine();
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding student!");
        }
    }

    // View Students
    public void viewStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Student List ---");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println("ID: " + data[0] + ", Name: " + data[1] +
                        ", Age: " + data[2] + ", Course: " + data[3]);
            }
        } catch (Exception e) {
            System.out.println("No records found.");
        }
    }

    // Search Student
    public void searchStudent(int id) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (Integer.parseInt(data[0]) == id) {
                    System.out.println("\nStudent Found:");
                    System.out.println("ID: " + data[0] + ", Name: " + data[1] +
                            ", Age: " + data[2] + ", Course: " + data[3]);
                    found = true;
                    break;
                }
            }

            if (!found)
                System.out.println("Student not found!");

        } catch (Exception e) {
            System.out.println("Error searching student.");
        }
    }

    // Update Student
    public void updateStudent(int id, String newName, int newAge, String newCourse) {
        List<String> students = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (Integer.parseInt(data[0]) == id) {
                    line = id + "," + newName + "," + newAge + "," + newCourse;
                    updated = true;
                }
                students.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error updating student.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String s : students) {
                bw.write(s);
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error writing updated file.");
        }

        if (updated)
            System.out.println("Student updated successfully!");
        else
            System.out.println("Student not found!");
    }

    // Delete Student
    public void deleteStudent(int id) {
        List<String> students = new ArrayList<>();
        boolean deleted = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (Integer.parseInt(data[0]) == id) {
                    deleted = true;
                    continue; // skip this student
                }
                students.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error deleting student.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String s : students) {
                bw.write(s);
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error rewriting file.");
        }

        if (deleted)
            System.out.println("Student deleted successfully!");
        else
            System.out.println("Student not found!");
    }
}
