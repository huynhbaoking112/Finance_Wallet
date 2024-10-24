package managerBank.pages.dashboard;

import javax.swing.*;

import managerBank.Config.ConDB;
import managerBank.DTO.TranferRequest;
import managerBank.Model.UserDashboard;
import managerBank.Service.TransactionServer;
import managerBank.Service.UserService;
import managerBank.utils.CheckExists;
import managerBank.utils.EmailSender;
import managerBank.utils.QRCodeGenerator;
import managerBank.utils.QRCodeReaderExample;

import java.awt.*;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dashboard extends JFrame {
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

    // UserDashboard
    UserDashboard userDashboard;

    public Dashboard(String email) {
        
        //-------- Lấy tất cả thông tin cần thiết-------------------
        getData(email);
        


        //--------------------------Phần my Account ---------------------

        //Tạo JLabel cho tên
        JLabel nameLabel = new JLabel(userDashboard.getName());
        nameLabel.setBounds(461, 142, 189, 41);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);

        // Thêm JLabel vào JFrame
        this.add(nameLabel);

        // Taọ JLable cho số điện thoại
        JLabel phoneLabel = new JLabel(userDashboard.getPhone());
        phoneLabel.setBounds(462, 184, 246, 37);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 32));
        phoneLabel.setForeground(Color.WHITE);

        // Thêm JLabel vào JFrame
        this.add(phoneLabel);

        //Tạo JLable số dư 
        amountTotal = new JLabel("********");
        amountTotal.setFont(new Font("Arial", Font.BOLD, 32));
        amountTotal.setForeground(Color.WHITE);
        amountTotal.setBounds(462, 225, 160, 39);
        
        // Thêm JLabel vào JFrame
        this.add(amountTotal);

        // Tạo JButton cho biểu tượng con mắt
        JButton eyeButton = new JButton(new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\Eye.png")); // Thay đổi đường dẫn đến icon
        eyeButton.setBounds(730, 225, 29, 29); // Kích thước nút có thể thay đổi nếu cần
        eyeButton.setContentAreaFilled(false);
        eyeButton.setBorderPainted(false);
        eyeButton.setFocusPainted(false);
        
        // Thêm ActionListener cho nút mắt
        eyeButton.addActionListener(e -> {
            display = !display;
            if(display){
                amountTotal.setText(Integer.toString(userDashboard.getBalance()));
            }else{
                amountTotal.setText("********");
            }
        }); // Cập nhật số dư khi nhấn nút
        this.add(eyeButton);

        //-------------- Phần gửi tiền -------------------



        // Tạo field payee
        hiddenPayeeAddress = new JTextField();
        hiddenPayeeAddress.setBounds(329, 413, 264, 57); 
        hiddenPayeeAddress.setFont(new Font("Arial", Font.BOLD, 24));

        // Thêm khung nhập vào JFrame
        this.add(hiddenPayeeAddress);

        // Tạo field amount
        hiddenAmount = new JTextField();
        hiddenAmount.setBounds(329, 508, 264, 57); 
        hiddenAmount.setFont(new Font("Arial", Font.BOLD, 24));

        // Thêm khung nhập vào JFrame
        this.add(hiddenAmount);

        
        // Tạo field message
        hiddenMessage = new JTextField();
        hiddenMessage.setBounds(329, 612, 264, 57); 
        hiddenMessage.setFont(new Font("Arial", Font.BOLD, 24));

        // Thêm khung nhập vào JFrame
        this.add(hiddenMessage);


        // Nút gửi tiền
        JButton sendMoney = new JButton("");
        sendMoney.setBounds(329, 690, 264, 57);
        sendMoney.setContentAreaFilled(false);
        sendMoney.setBorderPainted(false);
        sendMoney.setFocusPainted(false);
        sendMoney.addActionListener(e->{
              //Check các trường đã được điền chưa
                if(hiddenPayeeAddress.equals("")){
                    JOptionPane.showMessageDialog(null, "Fill all the fields");
                }
                else if(hiddenAmount.equals("")){
                    JOptionPane.showMessageDialog(null, "Fill all the fields");
                }
                else if(hiddenMessage.equals("")){
                    JOptionPane.showMessageDialog(null, "Fill all the fields");
                }else{
                    try {
                        String query = "select id from users where phone = ?";
                    PreparedStatement pre = conDB.connection.prepareStatement(query);
                    pre.setString(1, hiddenPayeeAddress.getText());
                    ResultSet rs = pre.executeQuery();
                    
                    if (rs.next()){
                        int id = rs.getInt("id");
                        // TranferRequest trans = new TranferRequest(userDashboard.getId(), id, Integer.parseInt(hiddenAmount.getText()), hiddenMessage.getText());
                        TransactionServer transHandler =  new TransactionServer();
                        Boolean tranSer = transHandler.transferMoney(userDashboard.getPhone(), hiddenPayeeAddress.getText() ,Integer.parseInt(hiddenAmount.getText()));
                        if(tranSer){
                            transHandler.saveTransactionBill(userDashboard.getId(), id, Integer.parseInt(hiddenAmount.getText()), hiddenMessage.getText());
                        }else{
                            JOptionPane.showMessageDialog(null, "Transaction Fail!");
                        }
                    }
                    } catch (Exception ex) {
                        EmailSender.sendToDev(userDashboard.getEmail(),ex.getMessage());
                    }
                }
                
                
        });

        // Thêm nút gửi tiền vào JFrame
        this.add(sendMoney);
        //------------------------- Phần QR CODE ----------------------------------------
        
        // Tạo nút ẩn USE QR CODE
         JButton hiddenButtonUse = new JButton();
         hiddenButtonUse.setBounds(667, 413, 264, 57);
         hiddenButtonUse.setContentAreaFilled(false);
         hiddenButtonUse.setBorderPainted(false);
         hiddenButtonUse.setFocusPainted(false);
         hiddenButtonUse.addActionListener(e-> {
            JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String result = QRCodeReaderExample.readQRCode(selectedFile.getAbsolutePath());
                    // hiddenPayeeAddress.setText(result);
                    try {
                        String query = "SELECT name from users where phone = ?";
                        PreparedStatement pre = conDB.connection.prepareStatement(query);
                        pre.setString(1, result);
                        ResultSet rs = pre.executeQuery();
                        if(rs.next()){
                            String name = rs.getString("name");
                            int confirmation = JOptionPane.showConfirmDialog(null,
                            "Is the name " + name + " correct?", "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                        
                        // Kiểm tra phản hồi từ người dùng
                        if (confirmation == JOptionPane.YES_OPTION) {
                            hiddenPayeeAddress.setText(result); 
                        } else {
                            return;
                        }                            
                        }
                    } catch (Exception ex) {
                        EmailSender.sendToDev(userDashboard.getEmail(),ex.getMessage());
                    }
                }
         });
 
         // Thêm nút vào JFrame
         this.add(hiddenButtonUse);


        // Tạo nút ẩn GET QR
         JButton hiddenButtonGet = new JButton();
         hiddenButtonGet.setBounds(667, 508, 264, 57);
         hiddenButtonGet.setContentAreaFilled(false);
         hiddenButtonGet.setBorderPainted(false);
         hiddenButtonGet.setFocusPainted(false);
         hiddenButtonGet.addActionListener(e-> {
            try {
                QRCodeGenerator.generateQRCodeImage(userDashboard.getPhone(), 350, 350);
            } catch (Exception ex) {
                EmailSender.sendToDev(userDashboard.getEmail(),ex.getMessage());            }
        });
 
         // Thêm nút vào JFrame
         this.add(hiddenButtonGet);


        //--------------------------Phần thanh bên trái-----------------------------------

        // Tạo nút ẩn Dashboard
        JButton hiddenButton = new JButton();
        hiddenButton.setBounds(22, 149, 174, 42);
        hiddenButton.setContentAreaFilled(false);
        hiddenButton.setBorderPainted(false);
        hiddenButton.setFocusPainted(false);
        hiddenButton.addActionListener(e-> System.out.println("king"));

        // Thêm nút vào JFrame
        this.add(hiddenButton);

        // Tạo nút ẩn Transaction
        JButton hiddenButton2 = new JButton();
        hiddenButton2.setBounds(22, 226, 174, 42);
        hiddenButton2.setContentAreaFilled(false);
        hiddenButton2.setBorderPainted(false);
        hiddenButton2.setFocusPainted(false);
        hiddenButton2.addActionListener(e-> System.out.println("king2"));

        // Thêm nút vào JFrame
        this.add(hiddenButton2);

        // Tạo nút ẩn Account
        JButton hiddenButton3 = new JButton();
        hiddenButton3.setBounds(22, 294, 174, 42);
        hiddenButton3.setContentAreaFilled(false);
        hiddenButton3.setBorderPainted(false);
        hiddenButton3.setFocusPainted(false);
        hiddenButton3.addActionListener(e-> System.out.println("king3"));

        // Thêm nút vào JFrame
        this.add(hiddenButton3);

        // Tạo nút ẩn Profile
        JButton hiddenButton4 = new JButton();
        hiddenButton4.setBounds(33, 445, 157, 63);
        hiddenButton4.setContentAreaFilled(false);
        hiddenButton4.setBorderPainted(false);
        hiddenButton4.setFocusPainted(false);
        hiddenButton4.addActionListener(e-> System.out.println("king4"));

        // Thêm nút vào JFrame
        this.add(hiddenButton4);

        // Tạo nút ẩn Logout
        JButton hiddenButton5 = new JButton();
        hiddenButton5.setBounds(33, 565, 157, 63);
        hiddenButton5.setContentAreaFilled(false);
        hiddenButton5.setBorderPainted(false);
        hiddenButton5.setFocusPainted(false);
        hiddenButton5.addActionListener(e-> System.out.println("king5"));

        // Thêm nút vào JFrame
        this.add(hiddenButton5);

        init();
    }

    public void init() {

        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\background.jpg");
        // Image handle_background = pre_background.getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
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

    public void getData(String emailGet){
        try {
            String query = "select u.id, u.name, u.phone, u.email, w.balance FROM users AS u JOIN (SELECT id from users WHERE email = ?) AS u2 ON u.id = u2.id JOIN wallet AS w ON u.id = w.user_id";
            PreparedStatement pre = conDB.connection.prepareStatement(query);
            pre.setString(1, emailGet);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone"); 
                String email = rs.getString("email"); 
                int balance = rs.getInt("balance"); 
                //Đưa dữ liệu vào userDashboard
                userDashboard = new UserDashboard(id, name, phone, email, balance);
            }

        } catch (Exception e) {
            EmailSender.sendToDev(emailGet,e.getMessage());
        }
        
    }

    public static void main(String[] args) {
        new Dashboard("king77nt54321@gmail.com");
    }
}


