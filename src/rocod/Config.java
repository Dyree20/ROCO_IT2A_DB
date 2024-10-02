package rocod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Config {
    static Scanner sc = new Scanner(System.in);

    public static void addStudents() {
        Config conf = new Config();
        System.out.print("Student First Name: ");
        String fname = sc.nextLine();
        System.out.print("Student Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Student Email: ");
        String email = sc.nextLine();
        System.out.print("Student Status: ");
        String status = sc.nextLine();

        String sql = "INSERT INTO Student (s_fname, s_lname, s_email, s_status) VALUES (?, ?, ?, ?)";

        
    }

    public static void updateStudents() {
        Config conf = new Config();
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Student First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter new Student Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter new Student Email: ");
        String email = sc.nextLine();
        System.out.print("Enter new Student Status: ");
        String status = sc.nextLine();

        String sql = "UPDATE Student SET s_fname = ?, s_lname = ?, s_email = ?, s_status = ? WHERE s_id = ?";

        
    }

    public static void deleteStudents() {
        Config conf = new Config();
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM Student WHERE s_id = ?";

        
    }

    public static void viewStudents() {
        Config conf = new Config();

        String sql = "SELECT * FROM Student";

        
    }

    public static void Choices() {
        System.out.println("1. ADD");
        System.out.println("2. UPDATE");
        System.out.println("3. DELETE");
        System.out.println("4. VIEW");
        System.out.println("5. EXIT");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                Action.addStudents();
                break;
            case 2:
                Action.updateStudents();
                break;
            case 3:
                Action.deleteStudents();
                break;
            case 4:
                Action.viewStudents();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
        }
    }

}