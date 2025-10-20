import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO {
    private final Connection connection = DatabaseConnection.getConnection();

    public void addStudent(Student s) {
        String sql = "INSERT INTO students (name, roll_no, department, email, phone, subject1, subject2, subject3, total, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getRollNo());
            stmt.setString(3, s.getDept());
            stmt.setString(4, s.getEmail());
            stmt.setString(5, s.getPhone());
            stmt.setInt(6, s.getSub1());
            stmt.setInt(7, s.getSub2());
            stmt.setInt(8, s.getSub3());
            stmt.setInt(9, s.getTotal());
            stmt.setString(10, s.getGrade());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void updateStudent(Student s) {
        String sql = "UPDATE students SET name=?, roll_no=?, department=?, email=?, phone=?, subject1=?, subject2=?, subject3=?, total=?, grade=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getRollNo());
            stmt.setString(3, s.getDept());
            stmt.setString(4, s.getEmail());
            stmt.setString(5, s.getPhone());
            stmt.setInt(6, s.getSub1());
            stmt.setInt(7, s.getSub2());
            stmt.setInt(8, s.getSub3());
            stmt.setInt(9, s.getTotal());
            stmt.setString(10, s.getGrade());
            stmt.setInt(11, s.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setRollNo(rs.getString("roll_no"));
                s.setDept(rs.getString("department"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setSub1(rs.getInt("subject1"));
                s.setSub2(rs.getInt("subject2"));
                s.setSub3(rs.getInt("subject3"));
                list.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Student> searchStudents(String keyword) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? OR roll_no LIKE ? OR department LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String key = "%" + keyword + "%";
            stmt.setString(1, key);
            stmt.setString(2, key);
            stmt.setString(3, key);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setRollNo(rs.getString("roll_no"));
                s.setDept(rs.getString("department"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setSub1(rs.getInt("subject1"));
                s.setSub2(rs.getInt("subject2"));
                s.setSub3(rs.getInt("subject3"));
                list.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            int rows = stmt.executeUpdate();
            if(rows > 0) System.out.println("✅ Student deleted successfully!");
            else System.out.println("⚠️ Student not found!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Map<String,Object> getStatistics() {
        Map<String,Object> stats = new HashMap<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) AS total FROM students");
            if(rs1.next()) stats.put("total", rs1.getInt("total"));

            ResultSet rs2 = stmt.executeQuery("SELECT MAX(total) AS max, MIN(total) AS min FROM students");
            if(rs2.next()) {
                stats.put("max", rs2.getInt("max"));
                stats.put("min", rs2.getInt("min"));
            }

            ResultSet rs3 = stmt.executeQuery("SELECT department, COUNT(*) AS count FROM students GROUP BY department");
            Map<String,Integer> deptCounts = new HashMap<>();
            while(rs3.next()) {
                deptCounts.put(rs3.getString("department"), rs3.getInt("count"));
            }
            stats.put("deptCounts", deptCounts);

        } catch(SQLException e) { e.printStackTrace(); }
        return stats;
    }
}
