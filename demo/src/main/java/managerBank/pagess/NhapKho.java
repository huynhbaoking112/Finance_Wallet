package managerBank.pagess;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NhapKho  extends  JFrame{
    JTextField timf;
    JButton tim;
    JTextField soluongf;
    JButton them;
    JButton nhapHang;
    JTextField tongTienf;  
    JButton dash;
    JButton nhapKho;
    JButton xuatKho;
    JButton phieuXuat;
    JButton phieuNhap;
    JButton profile;
    JButton logout;
    public NhapKho (){

        // Nut tim kiem
        tim = new JButton();
        tim.setBounds(710, 85, 98, 44);
        tim.setContentAreaFilled(false);
        tim.setBorderPainted(false);
        tim.setFocusPainted(false);
        tim.addActionListener(e -> {
           

        });
        // Nut them
        them = new JButton();
        them.setBounds(622, 722, 111, 54);
        them.setContentAreaFilled(false);
        them.setBorderPainted(false);
        them.setFocusPainted(false);
        them.addActionListener(e -> {
           

        });

        //Nut nhap
        nhapHang = new JButton();
        nhapHang.setBounds(1206, 716, 174, 42);
        nhapHang.setContentAreaFilled(false);
        nhapHang.setBorderPainted(false);
        nhapHang.setFocusPainted(false);
        nhapHang.addActionListener(e -> {
           

        });

        //Field so luong
        soluongf = new JTextField();
        soluongf.setBounds(490, 722, 111, 54);
        soluongf.setFont(new Font("Arial", Font.BOLD, 24));
        //Field tim kiem
        timf = new JTextField();
        timf.setBounds(270, 68, 423, 75);
        timf.setFont(new Font("Arial", Font.BOLD, 24));
        //Field tong tien
        tongTienf = new JTextField();
        tongTienf.setBounds(916, 719, 238, 57);
        tongTienf.setFont(new Font("Arial", Font.BOLD, 24));
        tongTienf.setEditable(false);
        
        dash = new JButton();
        dash.setBounds(45, 70, 174, 42);
        dash.setContentAreaFilled(false);
        dash.setBorderPainted(false);
        dash.setFocusPainted(false);
        dash.addActionListener(e -> {
           
            new Dashboard2();
            this.setVisible(false);
        });
        nhapKho = new JButton();
        nhapKho.setBounds(45, 136, 174, 42);
        nhapKho.setContentAreaFilled(false);
        nhapKho.setBorderPainted(false);
        nhapKho.setFocusPainted(false);
        nhapKho.addActionListener(e -> {
           

        });
        xuatKho = new JButton();
        xuatKho.setBounds(45, 204, 174, 42);
        xuatKho.setContentAreaFilled(false);
        xuatKho.setBorderPainted(false);
        xuatKho.setFocusPainted(false);
        xuatKho.addActionListener(e -> {
           new XuatKho();
           this.setVisible(false);

        });
        phieuXuat = new JButton();
        phieuXuat.setBounds(45, 274, 174, 42);
        phieuXuat.setContentAreaFilled(false);
        phieuXuat.setBorderPainted(false);
        phieuXuat.setFocusPainted(false);
        phieuXuat.addActionListener(e -> {
           new PhieuXuat();
           this.setVisible(false);

        });
        phieuNhap = new JButton();
        phieuNhap.setBounds(45, 341, 174, 42);
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


        this.add(timf);
        this.add(soluongf);
        this.add(tongTienf);
        this.add(nhapHang);
        this.add(them);
        this.add(tim);
        init();
    }

    public void init() {

        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\nhapkho.jpg");
        // Image handle_background = pre_background.getImage().getScaledInstance(1000,
        // 800, Image.SCALE_SMOOTH);
        // ImageIcon icon_background = new ImageIcon(handle_background);
        // Set Icon background
        JLabel jLabel_background = new JLabel(pre_background);
        jLabel_background.setBounds(0, 0, 1417, 800);
        this.add(jLabel_background);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1450, 850);
        this.setTitle("Dashboard");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new NhapKho();
    }
}
