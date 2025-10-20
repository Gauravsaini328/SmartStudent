import java.sql.*;
import java.util.Scanner;

public class AdminService {
    private final Scanner scanner = new Scanner(System.in);
    private final Connection connection = DatabaseConnection.getConnection();

    public void addStudent() {
        try {
            System.out.println("\n=== ADD NEW STUDENT ===");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Phone: ");
            String phone = scanner.nextLine();
            System.out.print("DOB (YYYY-MM-DD): ");
            String dob = scanner.nextLine();

            String sql = "INSERT INTO Student (name, email, phone, date_of_birth) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, phone);
                stmt.setString(4, dob);

                int rows = stmt.executeUpdate();
                if (rows > 0) System.out.println(" Student added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void viewAllStudents() {
        try {
            String sql = "SELECT * FROM Student ORDER BY id";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                boolean found = false;
                System.out.println("\n=== ALL STUDENTS ===");
                while (rs.next()) {
                    found = true;
                    System.out.printf("ID: %d | Name: %-20s | Email: %-25s | Phone: %-15s | DOB: %s%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("date_of_birth"));
                }

                if (!found) System.out.println("No students found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
    }

    public void viewAllCourses() {
        try {
            String sql = "SELECT * FROM Course ORDER BY id";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                boolean found = false;
                System.out.println("\n=== AVAILABLE COURSES ===");
                while (rs.next()) {
                    found = true;
                    System.out.printf("ID: %d | Course: %-25s | Duration: %2d weeks%n",
                            rs.getInt("id"),
                            rs.getString("course_name"),
                            rs.getInt("duration_weeks"));
                    System.out.println(" Description: " + rs.getString("description") + "\n");
                }

                if (!found) System.out.println("No courses found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving courses: " + e.getMessage());
        }
    }

    public void enrollStudentInCourse() {
        try {
            System.out.println("\n=== ENROLL STUDENT IN COURSE ===");
            viewAllStudents();
            System.out.print("Student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();

            viewAllCourses();
            System.out.print("Course ID: ");
            int courseId = scanner.nextInt();
            scanner.nextLine();

            String checkSql = "SELECT * FROM Enrollment WHERE student_id = ? AND course_id = ? AND status = 'enrolled'";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
                checkStmt.setInt(1, studentId);
                checkStmt.setInt(2, courseId);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    System.out.println(" Student is already enrolled!");
                    return;
                }
            }

            String sql = "INSERT INTO Enrollment (student_id, course_id) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, studentId);
                stmt.setInt(2, courseId);
                int rows = stmt.executeUpdate();
                if (rows > 0) System.out.println(" Student enrolled successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    public void viewStudentEnrollments() {
        try {
            viewAllStudents();
            System.out.print("\nEnter student ID to view enrollments: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();

            String sql = "SELECT e.enrollment_id, c.course_name, e.enrollment_date, e.status " +
                    "FROM Enrollment e JOIN Course c ON e.course_id = c.id " +
                    "WHERE e.student_id = ? ORDER BY e.enrollment_date DESC";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, studentId);
                ResultSet rs = stmt.executeQuery();

                boolean found = false;
                System.out.println("\n=== Enrollments for Student ID: " + studentId + " ===");
                while (rs.next()) {
                    found = true;
                    System.out.printf("Enrollment ID: %d | Course: %-25s | Date: %s | Status: %s%n",
                            rs.getInt("enrollment_id"),
                            rs.getString("course_name"),
                            rs.getString("enrollment_date"),
                            rs.getString("status"));
                }

                if (!found) System.out.println("No enrollments found for this student.");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving enrollments: " + e.getMessage());
        }
    }

    public void updateStudent() {
        try {
            viewAllStudents();
            System.out.print("\nEnter student ID to update: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("New email (press enter to keep current): ");
            String email = scanner.nextLine();
            System.out.print("New phone (press enter to keep current): ");
            String phone = scanner.nextLine();

            if (email.isEmpty() && phone.isEmpty()) {
                System.out.println(" No changes provided.");
                return;
            }

            StringBuilder sql = new StringBuilder("UPDATE Student SET ");
            boolean comma = false;
            if (!email.isEmpty()) { sql.append("email=?"); comma = true; }
            if (!phone.isEmpty()) {
                if (comma) sql.append(", ");
                sql.append("phone=?");
            }
            sql.append(" WHERE id=?");

            try (PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
                int idx = 1;
                if (!email.isEmpty()) stmt.setString(idx++, email);
                if (!phone.isEmpty()) stmt.setString(idx++, phone);
                stmt.setInt(idx, studentId);

                int rows = stmt.executeUpdate();
                if (rows > 0) System.out.println(" Student info updated successfully!");
                else System.out.println("Student not found!");
            }

        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public void closeResources() {
        scanner.close();
    }
}
