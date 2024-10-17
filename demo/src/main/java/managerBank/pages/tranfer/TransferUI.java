package managerBank.pages.tranfer;

import javax.swing.*;

import managerBank.DTO.UserDTO;
import managerBank.Service.UserService;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.regex.Pattern;

public class TransferUI extends JFrame {

    public TransferUI() {

        UserService userService = new UserService();
        // Thiết lập cửa sổ
        setTitle("Chuyển tiền");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel thông tin tài khoản nguồn
        JPanel sourcePanel = new JPanel();
        sourcePanel.setLayout(new GridLayout(3, 1));
        sourcePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sourcePanel.setBackground(Color.WHITE);

        JLabel lblSourceName = new JLabel("Nguồn Tiền");
        lblSourceName.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        accountPanel.setBackground(Color.WHITE);

        JLabel lblSourceAccount = new JLabel("123 4567 789");
        lblSourceAccount.setFont(new Font("Arial", Font.PLAIN, 16));

        ImageIcon icon = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\bank.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(40, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel iconLabel = new JLabel(scaledIcon);

        accountPanel.add(iconLabel);
        accountPanel.add(lblSourceAccount);

        JLabel lblSourceBalance = new JLabel("100,000,000 VND");
        lblSourceBalance.setFont(new Font("Arial", Font.BOLD, 24));
        lblSourceBalance.setForeground(new Color(255, 165, 0));

        sourcePanel.add(lblSourceName);
        sourcePanel.add(accountPanel);
        sourcePanel.add(lblSourceBalance);

        // Panel form nhập thông tin chuyển tiền
        JPanel transferFormPanel = new JPanel();
        transferFormPanel.setLayout(new GridLayout(8, 1, 10, 10));
        transferFormPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField accountField = new JTextField("Số tài khoản");

        JTextField receiverAccountField = new JTextField(20);
        receiverAccountField.setEditable(false);

        accountField.addMouseListener(new MouseAdapter() {
            boolean isFirstReceiverClick = true; // Biến kiểm tra lần chọn đầu tiên ô receiver

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isFirstReceiverClick) {
                    accountField.setText(""); // Xóa nội dung lần đầu tiên
                    isFirstReceiverClick = false; // Đặt lại trạng thái
                }
            }
        });
        accountField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                String accountNumber = accountField.getText();

                UserDTO userDTO = null;
                if (accountNumber.length() > 9) {
                    userDTO = userService.findInfoByEmail(accountNumber);
                }
                if (userDTO != null) {
                    String receiverName = userDTO.getUserName();
                    receiverName = receiverName.toUpperCase();
                    receiverAccountField.setText(receiverName);
                } else {
                    receiverAccountField.setText("");
                }
            }

        });

        JTextField amountField = new JTextField("Số tiền VND");
        amountField.addMouseListener(new MouseAdapter() {
            boolean firstClick = true; // Biến để kiểm tra lần click đầu tiên

            @Override
            public void mouseClicked(MouseEvent e) {
                if (firstClick) {
                    amountField.setText(" "); // Xóa nội dung lần đầu tiên
                    firstClick = false; // Đặt lại biến sau khi click
                }
            }
        });
        amountField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                String amountFieldConten = amountField.getText();
                amountFieldConten = amountFieldConten.replace(",", "");
                amountFieldConten = amountFieldConten.replace(" VND", "");
                amountFieldConten = amountFieldConten.replace(" VN", "");
                System.out.println(amountFieldConten);
                // Biểu thức chính quy để kiểm tra xem có chữ hoặc số không
                Pattern pattern = Pattern.compile("[a-zA-Z]");
                boolean hasLetterOrDigit = pattern.matcher(amountFieldConten).find();

                if (hasLetterOrDigit) {
                    amountFieldConten = amountFieldConten.replaceAll("[a-zA-Z]", "");

                }

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (amountFieldConten.length() > 1) {
                        amountFieldConten = amountFieldConten.substring(0, amountFieldConten.length() - 1);
                    } else {
                        amountFieldConten = "0";
                    }
                }
                StringBuilder result = new StringBuilder();

                // Duyệt qua từng ký tự trong chuỗi
                int dem = 0;
                for (int i = amountFieldConten.length() - 1; i >= 0; i--) {
                    if (amountFieldConten.charAt(i) == ' ') {
                        continue;
                    }
                    if ((amountFieldConten.charAt(i) >= 'a') && (amountFieldConten.charAt(i) <= 'z')
                            ||
                            (amountFieldConten.charAt(i) >= 'A') && (amountFieldConten.charAt(i) <= 'Z')) {
                        JOptionPane.showMessageDialog(amountField, "Vui lòng không nhập kí tự trong ô số tiền");
                        if (amountFieldConten.length() > 1) {

                            amountFieldConten = amountFieldConten.substring(0, amountFieldConten.length() - 1);
                        } else {
                            amountFieldConten = "0";
                        }
                        break;
                    }
                    result.append(amountFieldConten.charAt(i));
                    dem++;
                    if (dem == 3) {
                        result.append(",");
                        dem = 0;
                    }
                }

                amountFieldConten = result.reverse().toString(); // Trả về chuỗi đã chuyển đổi
                System.out.println(amountFieldConten);
                if(amountFieldConten == null || amountFieldConten ==""){
                    amountField.setText("Nhập số tiền");

                    return;

                }
                if (amountFieldConten.charAt(0) == ',') {
                    amountFieldConten = amountFieldConten.substring(1, amountFieldConten.length());
                }
                if (amountFieldConten.charAt(0) == '0' && amountFieldConten.length() > 1) {
                    amountFieldConten = amountFieldConten.substring(1, amountFieldConten.length());
                }
                amountFieldConten = amountFieldConten + " VND";
                amountField.setText(amountFieldConten);
            }
        });

        // Tạo các nút số tiền
        JPanel amountButtonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        String[] amounts = { "100,000 VND", "200,000 VND", "300,000 VND", "1,000,000 VND", "2,000,000 VND",
                "5,000,000 VND" };
        for (String amount : amounts) {
            JButton amountButton = new JButton(amount);
            amountButton.addActionListener(new ActionListener() {
                boolean isFirstAmountClick = true; // Biến kiểm tra lần chọn đầu tiên ô amount

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isFirstAmountClick) {
                        amountField.setText(""); // Xóa nội dung lần đầu tiên
                        isFirstAmountClick = false; // Đặt lại trạng thái
                    }

                    amountField.setText(amount.substring(0, amount.length())); // Gán giá trị nút vào amountField
                }
            });
            amountButtonPanel.add(amountButton);
        }
        // nội dung chuyển tiền
        JTextArea contentField = new JTextArea("Nội dung chuyển tiền", 3, 20);
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contentField);

        // Panel chứa các nút nội dung
        JPanel contentButtonPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        String[] contents = { "mua hang", "chuc mung", "cafe", "an trua", "an toi", "tra no" };
        for (String content : contents) {
            JButton contentButton = new JButton(content);
            contentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    contentField.setText(content); // Gán giá trị nút vào contentField
                }
            });
            contentButtonPanel.add(contentButton);
        }

        // nút Tiếp tục - Xử lý sự kiện chuyển tiền
        JButton continueButton = new JButton("Tiếp tục");
        continueButton.setBackground(new Color(103, 58, 183));
        continueButton.setForeground(Color.WHITE);

        transferFormPanel.add(accountField);
        transferFormPanel.add(receiverAccountField);
        transferFormPanel.add(amountField);
        transferFormPanel.add(amountButtonPanel);
        transferFormPanel.add(scrollPane);
        transferFormPanel.add(contentButtonPanel);
        transferFormPanel.add(continueButton);

        add(sourcePanel, BorderLayout.NORTH);
        add(transferFormPanel, BorderLayout.CENTER);

        setVisible(true);

    }

    boolean isValid(String receiverNameField, String amountStr, int senderBalance) {
        amountStr = amountStr.replace(",", "");
        for (int i = 0; i < amountStr.length(); i++) {
            if (!Character.isDigit(amountStr.toString().charAt(i))) {
                JOptionPane.showMessageDialog(rootPane, "Vui lòng chỉ nhập số ở ô số tiền");
                return false;
            }
        }
        if (receiverNameField == "" || receiverNameField == null) {
            JOptionPane.showMessageDialog(rootPane, "Người nhận không tồn tại");
            return false;
        }
        try {
            int amountInt = Integer.parseInt(amountStr);
            if (senderBalance < amountInt) {
                JOptionPane.showMessageDialog(rootPane, "Số dư không đủ");
                return false;
            }
        } catch (NumberFormatException e) {
            // System.out.println("Chuỗi không phải là số hợp lệ.");
            return false;
        }

        return true;

    }

    public static void main(String[] args) {
        new TransferUI();
    }
}
