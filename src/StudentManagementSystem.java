import java.sql.*;

public class StudentManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "****";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT id, name, email FROM Student";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
