import java.io.FileWriter;
import java.sql.*;
import javax.swing.JOptionPane;

public class CSVExporter {
    public static void exportToCSV(String filePath) {
        String sql = "SELECT * FROM students";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql);
             FileWriter fw = new FileWriter(filePath)) {

            fw.append("Name,Roll,Dept,Email,Phone,Sub1,Sub2,Sub3,Total,Grade\n");
            while (rs.next()) {
                fw.append(rs.getString("name")).append(",")
                        .append(rs.getString("roll_no")).append(",")
                        .append(rs.getString("department")).append(",")
                        .append(rs.getString("email")).append(",")
                        .append(rs.getString("phone")).append(",")
                        .append(rs.getInt("subject1")+"").append(",")
                        .append(rs.getInt("subject2")+"").append(",")
                        .append(rs.getInt("subject3")+"").append(",")
                        .append(rs.getInt("total")+"").append(",")
                        .append(rs.getString("grade")).append("\n");
            }

            JOptionPane.showMessageDialog(null,"Data exported to "+filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
