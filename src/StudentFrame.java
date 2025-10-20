import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class StudentFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private StudentDAO dao = new StudentDAO();
    private JTextField searchField;

    public StudentFrame() {
        setTitle("SmartStudent Management");
        setSize(1000, 550);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel(
                new String[]{"ID","Name","Roll","Dept","Email","Phone","Sub1","Sub2","Sub3","Total","Grade"},0
        );
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        JButton statsBtn = new JButton("Statistics");
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        topPanel.add(statsBtn);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton exportBtn = new JButton("Export CSV");
        bottomPanel.add(addBtn); bottomPanel.add(updateBtn);
        bottomPanel.add(deleteBtn); bottomPanel.add(exportBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addStudentDialog());
        updateBtn.addActionListener(e -> updateStudentDialog());
        deleteBtn.addActionListener(e -> deleteStudent());
        exportBtn.addActionListener(e -> CSVExporter.exportToCSV("students.csv"));
        searchBtn.addActionListener(e -> searchStudents());
        statsBtn.addActionListener(e -> showStatistics());

        loadStudents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadStudents() {
        model.setRowCount(0);
        List<Student> students = dao.getAllStudents();
        for (Student s : students) {
            model.addRow(new Object[]{
                    s.getId(), s.getName(), s.getRollNo(), s.getDept(), s.getEmail(),
                    s.getPhone(), s.getSub1(), s.getSub2(), s.getSub3(), s.getTotal(), s.getGrade()
            });
        }
    }

    private void addStudentDialog() {
        JTextField name = new JTextField(), roll = new JTextField(), dept = new JTextField();
        JTextField email = new JTextField(), phone = new JTextField();
        JTextField sub1 = new JTextField(), sub2 = new JTextField(), sub3 = new JTextField();

        Object[] fields = {"Name:", name,"Roll No:", roll,"Dept:", dept,
                "Email:", email,"Phone:", phone,"Sub1:", sub1,"Sub2:", sub2,"Sub3:", sub3};
        int result = JOptionPane.showConfirmDialog(this, fields,"Add Student", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            try {
                Student s = new Student(
                        name.getText(), roll.getText(), dept.getText(),
                        email.getText(), phone.getText(),
                        Integer.parseInt(sub1.getText()), Integer.parseInt(sub2.getText()), Integer.parseInt(sub3.getText())
                );
                dao.addStudent(s);
                loadStudents();
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this,"Subjects must be numbers!");
            }
        }
    }

    private void updateStudentDialog() {
        int selectedRow = table.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this,"Please select a student to update.");
            return;
        }
        int studentId = (int) model.getValueAt(selectedRow,0);

        JTextField name = new JTextField(model.getValueAt(selectedRow,1).toString());
        JTextField roll = new JTextField(model.getValueAt(selectedRow,2).toString());
        JTextField dept = new JTextField(model.getValueAt(selectedRow,3).toString());
        JTextField email = new JTextField(model.getValueAt(selectedRow,4).toString());
        JTextField phone = new JTextField(model.getValueAt(selectedRow,5).toString());
        JTextField sub1 = new JTextField(model.getValueAt(selectedRow,6).toString());
        JTextField sub2 = new JTextField(model.getValueAt(selectedRow,7).toString());
        JTextField sub3 = new JTextField(model.getValueAt(selectedRow,8).toString());

        Object[] fields = {"Name:", name,"Roll No:", roll,"Dept:", dept,
                "Email:", email,"Phone:", phone,"Sub1:", sub1,"Sub2:", sub2,"Sub3:", sub3};
        int result = JOptionPane.showConfirmDialog(this, fields,"Update Student", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            try {
                Student s = new Student(
                        name.getText(), roll.getText(), dept.getText(),
                        email.getText(), phone.getText(),
                        Integer.parseInt(sub1.getText()), Integer.parseInt(sub2.getText()), Integer.parseInt(sub3.getText())
                );
                s.setId(studentId);
                dao.updateStudent(s);
                loadStudents();
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this,"Subjects must be numbers!");
            }
        }
    }

    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if(selectedRow != -1){
            int studentId = (int) model.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?");
            if(confirm == JOptionPane.YES_OPTION){
                dao.deleteStudent(studentId);
                loadStudents();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
        }
    }

    private void searchStudents() {
        String keyword = searchField.getText().trim();
        if(keyword.isEmpty()){
            loadStudents();
            return;
        }
        List<Student> students = dao.searchStudents(keyword);
        model.setRowCount(0);
        for(Student s : students){
            model.addRow(new Object[]{
                    s.getId(), s.getName(), s.getRollNo(), s.getDept(), s.getEmail(),
                    s.getPhone(), s.getSub1(), s.getSub2(), s.getSub3(), s.getTotal(), s.getGrade()
            });
        }
    }

    private void showStatistics() {
        Map<String,Object> stats = dao.getStatistics();
        StringBuilder sb = new StringBuilder();
        sb.append("Total Students: ").append(stats.get("total")).append("\n");
        sb.append("Highest Total Marks: ").append(stats.get("max")).append("\n");
        sb.append("Lowest Total Marks: ").append(stats.get("min")).append("\n\n");
        sb.append("Department-wise Count:\n");
        Map<String,Integer> deptCounts = (Map<String,Integer>) stats.get("deptCounts");
        for(String dept : deptCounts.keySet()){
            sb.append(dept).append(": ").append(deptCounts.get(dept)).append("\n");
        }
        JOptionPane.showMessageDialog(this,sb.toString(),"Statistics",JOptionPane.INFORMATION_MESSAGE);
    }
}
