package rocod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Action {
    static Scanner sc = new Scanner(System.in);
    
    // Database connection method
    //Connection Method to SQLITE
public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver
            con = DriverManager.getConnection("jdbc:sqlite:roco.db"); // Establish connection
            System.out.println("Connection Successful");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }

    public static void addStudents() {
        System.out.print("Student First Name: ");
        String fname = sc.nextLine();
        System.out.print("Student Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Student Email: ");
        String email = sc.nextLine();
        System.out.print("Student Status: ");
        String status = sc.nextLine();

        String sql = "INSERT INTO Student (s_fname, s_lname, s_email, s_status) VALUES (?, ?, ?, ?)";
        Action action = new Action();
        action.addRecord(sql, fname, lname, email, status);
    }

    public static void updateStudents() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();  // Consume newline
        System.out.print("Enter new Student First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter new Student Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter new Student Email: ");
        String email = sc.nextLine();
        System.out.print("Enter new Student Status: ");
        String status = sc.nextLine();

        String sql = "UPDATE Student SET s_fname = ?, s_lname = ?, s_email = ?, s_status = ? WHERE s_id = ?";
        Action action = new Action();
        action.updateRecord(sql, fname, lname, email, status, id);
    }

    public void addRecord(String sql, String... values) {
        try (Connection conn = this.connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]);
            }

            pstmt.executeUpdate();
            System.out.println("Record added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    public void updateRecord(String sql, String fname, String lname, String email, String status, int id) {
        try (Connection conn = this.connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, email);
            pstmt.setString(4, status);
            pstmt.setInt(5, id);

            pstmt.executeUpdate();
            System.out.println("Record updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }

    public static void deleteStudents() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        String sql = "DELETE FROM Student WHERE s_id = ?";
        Action action = new Action();
        action.deleteRecord(sql, id);
    }

    public void deleteRecord(String sql, int id) {
        try (Connection conn = this.connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Record deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }

    public static void viewStudents() {
        String sql = "SELECT * FROM Student";
        Action action = new Action();
        action.viewRecords(sql);
    }

    public void viewRecords(String sql) {
        try (Connection conn = this.connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Adjust column indexes based on your table schema
                int id = rs.getInt("s_id");
                String fname = rs.getString("s_fname");
                String lname = rs.getString("s_lname");
                String email = rs.getString("s_email");
                String status = rs.getString("s_status");
                System.out.println("ID: " + id + ", Name: " + fname + " " + lname + ", Email: " + email + ", Status: " + status);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        }
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
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please choose a valid option.");
        }
    }
}
