import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(300,200);
        setLayout(new GridLayout(3,2,10,10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        add(new JLabel(""));
        add(loginBtn);

        loginBtn.addActionListener(e -> login());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void login() {
        String user = usernameField.getText();
        String pass = new String(passwordField.getPassword());
        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM admins WHERE username=? AND password=?");
            pst.setString(1,user);
            pst.setString(2,pass);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                new StudentFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,"Invalid credentials!");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
