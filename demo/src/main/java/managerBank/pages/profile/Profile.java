package managerBank.pages.profile;



import javax.swing.*;

import managerBank.Config.ConDB;
import managerBank.DTO.TranferRepond;
import managerBank.Model.UserProfile;
import managerBank.pages.dashboard.Dashboard;
import managerBank.pages.login.Login;
import managerBank.pages.tranfer.TransferConfirmationUI;
import managerBank.pages.tranfer.TransferUI;
import managerBank.pages.transaction.TransactionFrame;
import managerBank.Service.TransactionServer;
import managerBank.utils.EmailSender;
import managerBank.utils.QRCodeGenerator;
import managerBank.utils.QRCodeReaderExample;
import managerBank.utils.ValidationBalance;

import java.awt.*;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Profile extends JFrame {
    ConDB conDB = new ConDB();


    // Jlable số dư
    JLabel amountTotal;
    
    // Biến hiển thị hay không hiển thị số dư
    boolean display = false;
    
    // Địa chỉ gửi tiền đi
    JTextField hiddenPayeeAddress;

    // Số tiền gửi đi
    JTextField hiddenAmount;
    
    // Tin nhắn gửi đi
    JTextField hiddenMessage;

    // UserProfile
    UserProfile userProfile;

    public Profile(String email) {
    
        //-------- Lấy tất cả thông tin cần thiết-------------------
        getData(email);
        

        //--------------------------Phần thông tin-----------------------------------

         //Tạo JLabel cho tên
         JLabel nameLabel = new JLabel(userProfile.getName());
         nameLabel.setBounds(456, 48, 359, 98);
         nameLabel.setFont(new Font("Arial", Font.BOLD, 45));
         nameLabel.setForeground(Color.WHITE);
     
        this.add(nameLabel);
        

         //Tạo JLabel cho cccd
         JLabel cccdLabel = new JLabel(userProfile.getCccd());
         cccdLabel.setBounds(460, 195, 449, 55);
         cccdLabel.setFont(new Font("Arial", Font.BOLD, 20));
         cccdLabel.setForeground(Color.BLACK);
     
        this.add(cccdLabel);


         //Tạo JLabel cho email
         JLabel emailLabel = new JLabel(userProfile.getEmail());
         emailLabel.setBounds(460, 282, 449, 55);
         emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
         emailLabel.setForeground(Color.BLACK);
     
        this.add(emailLabel);


         //Tạo JLabel cho phone
         JLabel phoneLabel = new JLabel(userProfile.getPhone());
         phoneLabel.setBounds(460, 370, 449, 55);
         phoneLabel.setFont(new Font("Arial", Font.BOLD, 20));
         phoneLabel.setForeground(Color.BLACK);
     
        this.add(phoneLabel);


         //Tạo JLabel cho address
         JLabel addressLabel = new JLabel(userProfile.getAddress());
         addressLabel.setBounds(460, 461, 449, 55);
         addressLabel.setFont(new Font("Arial", Font.BOLD, 20));
         addressLabel.setForeground(Color.BLACK);
     
        this.add(addressLabel);



        
        //--------------------------Phần thanh bên trái-----------------------------------

        // Tạo nút ẩn Dashboard
        JButton dashboardButton = new JButton();
        dashboardButton.setBounds(45, 170, 174, 42);
        dashboardButton.setContentAreaFilled(false);
        dashboardButton.setBorderPainted(false);
        dashboardButton.setFocusPainted(false);
        dashboardButton.addActionListener(e-> {
            setVisible(false);
            new Dashboard(userProfile.getEmail());
        });

        // Thêm nút vào JFrame
        this.add(dashboardButton);

        // Tạo nút ẩn Transaction
        JButton transactionButton = new JButton();
        transactionButton.setBounds(45, 250, 174, 42);
        transactionButton.setContentAreaFilled(false);
        transactionButton.setBorderPainted(false);
        transactionButton.setFocusPainted(false);
        transactionButton.addActionListener(e -> 
        {
            setVisible(false);
            new TransferUI(userProfile.getEmail());
        });

        // Thêm nút vào JFrame
        this.add(transactionButton);

        // Tạo nút ẩn Account
        JButton historyButton = new JButton();
        historyButton.setBounds(45, 320, 174, 42);
        historyButton.setContentAreaFilled(false);
        historyButton.setBorderPainted(false);
        historyButton.setFocusPainted(false);
        historyButton.addActionListener(e-> {
            setVisible(false);
            new TransactionFrame(userProfile.getEmail(), userProfile.getId());
        });

        // Thêm nút vào JFrame
        this.add(historyButton);

        // Tạo nút ẩn Profile
        JButton profileButton = new JButton();
        profileButton.setBounds(55, 470, 157, 63);
        profileButton.setContentAreaFilled(false);
        profileButton.setBorderPainted(false);
        profileButton.setFocusPainted(false);
        profileButton.addActionListener(e-> {
            setVisible(false);
            new Profile(userProfile.getEmail());
        });

        // Thêm nút vào JFrame
        this.add(profileButton);

        // Tạo nút ẩn Logout
        JButton logoutButton = new JButton();
        logoutButton.setBounds(55, 590, 157, 63);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e-> {
            setVisible(false);
            new Login();
        });

        // Thêm nút vào JFrame
        this.add(logoutButton);

        init();
    }

    public void init() {

        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\profile.jpg");
        // Image handle_background = pre_background.getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        // ImageIcon icon_background = new ImageIcon(handle_background);
        // Set Icon background
        JLabel jLabel_background = new JLabel(pre_background);
        jLabel_background.setBounds(0, 0, 1000, 800);
        this.add(jLabel_background);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setTitle("Profile");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void getData(String emailGet){
        try {
            String query = " SELECT\n" + 
                                "id, name, phone, dob, u.email , address , uf.cccd\n" + 
                                "FROM users AS u\n" + 
                                "JOIN user_infor AS uf\n" + 
                                "ON uf.email = u.email\n" + 
                                "WHERE u.email = ?" + 
                                "";
            PreparedStatement pre = conDB.connection.prepareStatement(query);
            pre.setString(1, emailGet);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone"); 
                String dob = rs.getString("dob"); 
                String email = rs.getString("email"); 
                String address = rs.getString("address"); 
                String cccd = rs.getString("cccd"); 
                
                //Đưa dữ liệu vào userProfile
                userProfile = new UserProfile(id, name, phone, dob, email, address, cccd);
            }



        } catch (Exception e) {
            EmailSender.sendToDev(emailGet,e.getMessage());
        }
        
    }

    public static void main(String[] args) {
        new Profile("king77nt54321@gmail.com");
    }
}


