package managerBank.pages.dashboard;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import managerBank.Config.ConDB;
import managerBank.DTO.TranferRepond;
import managerBank.Model.UserDashboard;
import managerBank.Service.TransactionServer;
import managerBank.pages.login.Login;
import managerBank.pages.profile.Profile;
import managerBank.pages.tranfer.TransferConfirmationUI;
import managerBank.pages.tranfer.TransferUI;
import managerBank.pages.transaction.TransactionFrame;
import managerBank.utils.EmailSender;
import managerBank.utils.QRCodeGenerator;
import managerBank.utils.QRCodeReaderExample;
import managerBank.utils.Validation;

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

        // -------- Lấy tất cả thông tin cần thiết-------------------
        getData(email);

        // --------------------------Phần my Account ---------------------

        // Tạo JLabel cho tên
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

        // Tạo JLable số dư
        amountTotal = new JLabel("********");
        amountTotal.setFont(new Font("Arial", Font.BOLD, 32));
        amountTotal.setForeground(Color.WHITE);
        amountTotal.setBounds(462, 225, 250, 39);

        // Thêm JLabel vào JFrame
        this.add(amountTotal);

        // Tạo JButton cho biểu tượng con mắt
        JButton eyeButton = new JButton(new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\Eye.png")); // Thay
                                                                                                                     // đổi
                                                                                                                     // đường
                                                                                                                     // dẫn
                                                                                                                     // đến
                                                                                                                     // icon
        eyeButton.setBounds(730, 225, 29, 29); // Kích thước nút có thể thay đổi nếu cần
        eyeButton.setContentAreaFilled(false);
        eyeButton.setBorderPainted(false);
        eyeButton.setFocusPainted(false);

        // Thêm ActionListener cho nút mắt
        eyeButton.addActionListener(e -> {
            display = !display;
            if (display) {

                try {
                    String query = "select u.id, u.name, u.phone, u.email, w.balance FROM users AS u JOIN (SELECT id from users WHERE email = ?) AS u2 ON u.id = u2.id JOIN wallet AS w ON u.id = w.user_id";
                    PreparedStatement pre = conDB.connection.prepareStatement(query);
                    pre.setString(1, userDashboard.getEmail());
                    ResultSet rs = pre.executeQuery();

                    if (rs.next()) {
                        int balance = rs.getInt("balance");

                        String sodu = Validation.BalanceValidation(balance);
                        amountTotal.setText(sodu);

                    }

                } catch (Exception ex) {
                    EmailSender.sendToDev(userDashboard.getEmail(), ex.getMessage());
                }

            } else {
                amountTotal.setText("********");
            }
        }); // Cập nhật số dư khi nhấn nút
        this.add(eyeButton);

        // -------------- Phần gửi tiền -------------------

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
        hiddenMessage = new JTextField(" ");
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
        sendMoney.addActionListener(e -> {
            // Check các trường đã được điền chưa
            if (hiddenPayeeAddress.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            } else if (hiddenAmount.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            } else {
                try {
                    // String query = "select id from users where phone = ?";
                    String selectQuery = "SELECT user_id, name, balance, version FROM walletsystem.users " +
                            "LEFT JOIN walletsystem.wallet" +
                            " ON wallet.id = users.id " +
                            "WHERE users.email = ? or phone = ?";
                    PreparedStatement pre = conDB.connection.prepareStatement(selectQuery);
                    pre.setString(1, hiddenPayeeAddress.getText());
                    pre.setString(2, hiddenPayeeAddress.getText());
                    ResultSet rs = pre.executeQuery();

                    if (rs.next()) {

                        int id = rs.getInt("user_id");
                        // TranferRequest trans = new TranferRequest(userDashboard.getId(), id,
                        // Integer.parseInt(hiddenAmount.getText()), hiddenMessage.getText());
                        TransactionServer transHandler = new TransactionServer();
                        boolean tranSer = transHandler.transferMoney(userDashboard.getPhone(),
                                hiddenPayeeAddress.getText(), Integer.parseInt(hiddenAmount.getText()));

                        if (tranSer) {

                            TranferRepond tranferRepond = transHandler.saveTransactionBill(userDashboard.getId(), id,
                                    Integer.parseInt(hiddenAmount.getText()), hiddenMessage.getText());
                            setVisible(false);
                            System.out.println(tranferRepond.getAmount());
                            new TransferConfirmationUI(tranferRepond);

                            getData(userDashboard.getEmail());

                            amountTotal.setText(Validation.BalanceValidation(userDashboard.getBalance()));
                        } else {
                            JOptionPane.showMessageDialog(null, "Transaction Fail!");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    EmailSender.sendToDev(userDashboard.getEmail(), ex.getMessage());
                }
            }

        });

        // Thêm nút gửi tiền vào JFrame
        this.add(sendMoney);
        // ------------------------- Phần QR CODE
        // ----------------------------------------

        // Tạo nút ẩn USE QR CODE
        JButton dashboardButtonUse = new JButton();
        dashboardButtonUse.setBounds(667, 413, 264, 57);
        dashboardButtonUse.setContentAreaFilled(false);
        dashboardButtonUse.setBorderPainted(false);
        dashboardButtonUse.setFocusPainted(false);
        dashboardButtonUse.addActionListener(e -> {
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
                    if (rs.next()) {
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
                    EmailSender.sendToDev(userDashboard.getEmail(), ex.getMessage());
                }
            }
        });

        // Thêm nút vào JFrame
        this.add(dashboardButtonUse);

        // Tạo nút ẩn GET QR
        JButton dashboardButtonGet = new JButton();
        dashboardButtonGet.setBounds(667, 508, 264, 57);
        dashboardButtonGet.setContentAreaFilled(false);
        dashboardButtonGet.setBorderPainted(false);
        dashboardButtonGet.setFocusPainted(false);
        dashboardButtonGet.addActionListener(e -> {
            try {
                QRCodeGenerator.generateQRCodeImage(userDashboard.getPhone(), 350, 350);
            } catch (Exception ex) {
                EmailSender.sendToDev(userDashboard.getEmail(), ex.getMessage());
            }
        });

        // Thêm nút vào JFrame
        this.add(dashboardButtonGet);

        // --------------------------Phần thanh bên
        // trái-----------------------------------

        // Tạo nút ẩn Dashboard
        JButton dashboardButton = new JButton();
        dashboardButton.setBounds(45, 170, 174, 42);
        dashboardButton.setContentAreaFilled(false);
        dashboardButton.setBorderPainted(false);
        dashboardButton.setFocusPainted(false);
        dashboardButton.addActionListener(e -> {
            setVisible(false);
            new Dashboard(userDashboard.getEmail());
        });

        // Thêm nút vào JFrame
        this.add(dashboardButton);

        // Tạo nút ẩn Transaction
        JButton transactionButton = new JButton();
        transactionButton.setBounds(45, 250, 174, 42);
        transactionButton.setContentAreaFilled(false);
        transactionButton.setBorderPainted(false);
        transactionButton.setFocusPainted(false);
        transactionButton.addActionListener(e -> {
            setVisible(false);
            new TransferUI(userDashboard.getEmail());
        });

        // Thêm nút vào JFrame
        this.add(transactionButton);

        // Tạo nút ẩn Account
        JButton historyButton = new JButton();
        historyButton.setBounds(45, 320, 174, 42);
        historyButton.setContentAreaFilled(false);
        historyButton.setBorderPainted(false);
        historyButton.setFocusPainted(false);
        historyButton.addActionListener(e -> {
            setVisible(false);
            new TransactionFrame(userDashboard.getEmail(), userDashboard.getId());
        });

        // Thêm nút vào JFrame
        this.add(historyButton);

        // Tạo nút ẩn Profile
        JButton profileButton = new JButton();
        profileButton.setBounds(55, 470, 157, 63);
        profileButton.setContentAreaFilled(false);
        profileButton.setBorderPainted(false);
        profileButton.setFocusPainted(false);
        profileButton.addActionListener(e -> {
            setVisible(false);
            new Profile(userDashboard.getEmail());
        });

        // Thêm nút vào JFrame
        this.add(profileButton);

        // Tạo nút ẩn Logout
        JButton logoutButton = new JButton();
        logoutButton.setBounds(55, 590, 157, 63);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e -> {
            setVisible(false);
            new Login();
        });

        // Thêm nút vào JFrame
        this.add(logoutButton);

        init();
    }

    public void init() {

        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\background.jpg");
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

    public void getData(String emailGet) {
        try {
            String query = "select u.id, u.name, u.phone, u.email, w.balance FROM users AS u JOIN (SELECT id from users WHERE email = ?) AS u2 ON u.id = u2.id JOIN wallet AS w ON u.id = w.user_id";
            PreparedStatement pre = conDB.connection.prepareStatement(query);
            pre.setString(1, emailGet);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                int balance = rs.getInt("balance");

                // Đưa dữ liệu vào userDashboard
                userDashboard = new UserDashboard(id, name, phone, email, balance);
            }

        } catch (Exception e) {
            EmailSender.sendToDev(emailGet, e.getMessage());
        }

    }

    public static void main(String[] args) {
        new Dashboard("king77nt54321@gmail.com");
    }
}
