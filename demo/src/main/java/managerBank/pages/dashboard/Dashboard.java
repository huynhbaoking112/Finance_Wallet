package managerBank.pages.dashboard;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard(String email) {
        init();
    }

    public void init() {
        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\dashboard.png");
        Image handle_background = pre_background.getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        ImageIcon icon_background = new ImageIcon(handle_background);
        // Set Icon background
        JLabel jLabel_background = new JLabel(icon_background);
        jLabel_background.setBounds(0, 0, 1000, 800);
        this.add(jLabel_background);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setTitle("Dashboard");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard("King Huynh");
    }
}


