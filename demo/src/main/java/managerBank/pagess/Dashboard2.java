package managerBank.pagess;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dashboard2 extends  JFrame{

    JButton dash;
    JButton nhapKho;
    JButton xuatKho;
    JButton phieuXuat;
    JButton phieuNhap;
    JButton profile;
    JButton logout;

    public Dashboard2 (){

        dash = new JButton();
        dash.setBounds(45, 71, 174, 42);
        dash.setContentAreaFilled(false);
        dash.setBorderPainted(false);
        dash.setFocusPainted(false);
        dash.addActionListener(e -> {
           

        });
        nhapKho = new JButton();
        nhapKho.setBounds(45, 130, 174, 42);
        nhapKho.setContentAreaFilled(false);
        nhapKho.setBorderPainted(false);
        nhapKho.setFocusPainted(false);
        nhapKho.addActionListener(e -> {
           
            new NhapKho();
            this.setVisible(false);
        });
        xuatKho = new JButton();
        xuatKho.setBounds(50, 200, 174, 42);
        xuatKho.setContentAreaFilled(false);
        xuatKho.setBorderPainted(false);
        xuatKho.setFocusPainted(false);
        xuatKho.addActionListener(e -> {
           new XuatKho();
           this.setVisible(false);

        });
        phieuXuat = new JButton();
        phieuXuat.setBounds(45, 270, 174, 42);
        phieuXuat.setContentAreaFilled(false);
        phieuXuat.setBorderPainted(false);
        phieuXuat.setFocusPainted(false);
        phieuXuat.addActionListener(e -> {
           new PhieuXuat();
           this.setVisible(false);

        });
        phieuNhap = new JButton();
        phieuNhap.setBounds(45, 340, 174, 42);
        phieuNhap.setContentAreaFilled(false);
        phieuNhap.setBorderPainted(false);
        phieuNhap.setFocusPainted(false);
        phieuNhap.addActionListener(e -> {
           new PhieuNhap();
           this.setVisible(false);

        });
        profile = new JButton();
        profile.setBounds(55, 470, 157, 63);
        profile.setContentAreaFilled(false);
        profile.setBorderPainted(false);
        profile.setFocusPainted(false);
        profile.addActionListener(e -> {
           

        });
        logout = new JButton();
        logout.setBounds(55, 590, 157, 63);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setFocusPainted(false);
        logout.addActionListener(e -> {
           
            

        });

        
        
        this.add(nhapKho);
        this.add(xuatKho);
        this.add(phieuNhap);
        this.add(phieuXuat);
        this.add(profile);
        this.add(logout);
        this.add(dash);
        init();
    }

    public void init() {

        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\admindashboard.jpg");
        // Image handle_background = pre_background.getImage().getScaledInstance(1000,
        // 800, Image.SCALE_SMOOTH);
        // ImageIcon icon_background = new ImageIcon(handle_background);
        // Set Icon background
        JLabel jLabel_background = new JLabel(pre_background);
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
        new Dashboard2();
    }
}