package managerBank.pages.tranfer;

import javax.swing.*;

import managerBank.DTO.TranferRepond;
import managerBank.DTO.UserDTO;
import managerBank.Service.TransactionServer;
import managerBank.Service.UserService;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.awt.event.MouseAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class TransferUI extends JFrame {

    UserDTO senderAccount ;
    UserDTO receiverAccount;
    public TransferUI(String senderEmail) {
        UserService userService = new UserService();
        TransactionServer transactionServer = new TransactionServer();
        senderAccount = userService.findInfoByEmailOrPhone(senderEmail);// lấy thông tin người dùng
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
        String phone = senderAccount.getPhone();
        JLabel lblSourceAccount = new JLabel(phone);
        lblSourceAccount.setFont(new Font("Arial", Font.PLAIN, 16));

        ImageIcon icon = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\bank.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(40, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel iconLabel = new JLabel(scaledIcon);

        accountPanel.add(iconLabel);
        accountPanel.add(lblSourceAccount);
         NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedNumber = numberFormat.format(senderAccount.getUserBalance()) +" VND";
      
        JLabel lblSourceBalance = new JLabel(formattedNumber);
        lblSourceBalance.setFont(new Font("Arial", Font.BOLD, 24));
        lblSourceBalance.setForeground(new Color(255, 165, 0));

        sourcePanel.add(lblSourceName);
        sourcePanel.add(accountPanel);
        sourcePanel.add(lblSourceBalance);

        // Panel form nhập thông tin chuyển tiền
        JPanel transferFormPanel = new JPanel();
        transferFormPanel.setLayout(new GridLayout(8, 1, 10, 10));
        transferFormPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField receiverAccountField = new JTextField("Số tài khoản");

        JTextField receiverNameField = new JTextField(20);
        receiverNameField.setEditable(false);

        receiverAccountField.addMouseListener(new MouseAdapter() {
            boolean isFirstReceiverClick = true; // Biến kiểm tra lần chọn đầu tiên ô receiver

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isFirstReceiverClick) {
                    receiverAccountField.setText(""); // Xóa nội dung lần đầu tiên
                    isFirstReceiverClick = false; // Đặt lại trạng thái
                }
            }
        });
        receiverAccountField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                String accountNumber = receiverAccountField.getText();
                accountNumber = accountNumber.replace(" ", "");
                receiverAccountField.setText(accountNumber);
                // Xử lý trường hợp người nhận trùng với người gửi
                if(accountNumber.equals(senderEmail) || accountNumber.equals(senderAccount.getPhone())){
                    return;
                }
                
                if (accountNumber.length() > 5) {
                    receiverAccount = userService.findInfoByEmailOrPhone(accountNumber);
                }
                if (receiverAccount != null) {
                    String receiverName = receiverAccount.getUserName();
                    receiverName = receiverName.toUpperCase();
                    receiverNameField.setText(receiverName);
                } else {
                    receiverNameField.setText("");
                }
            }

        });

        JTextField amountField = new JTextField("Số tiền: ");
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
        // nó đang bình thường khi nhấn bằng english
        amountField.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) {
               char keyChar = e.getKeyChar();
               if(!Character.isDigit(keyChar) && keyChar != '\b'){
                e.consume();
               }
            }
            @Override
            public void keyTyped (KeyEvent e) {
                char keyChar = e.getKeyChar();
                if(!Character.isDigit(keyChar) && keyChar != '\b'){
                 e.consume();
                }
             }
            @Override
            public void keyReleased(KeyEvent e) {
                String amountFieldConten = amountField.getText();
                amountFieldConten = amountFieldConten.replaceAll("[^0-9]", "");
              
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (amountFieldConten.length() > 1) {
                        amountFieldConten = amountFieldConten.substring(0, amountFieldConten.length() - 1);
                    } else {
                        amountFieldConten = "0";
                    }
                }
                   char keyChar = e.getKeyChar();
               if(!Character.isDigit(keyChar) && keyChar != '\b'){
                e.consume();
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
                            //  JOptionPane.showMessageDialog(amountField, "Vui lòng không nhập kí tự trong ô số tiền");
                            if (amountFieldConten.length() > 1) {
                                amountFieldConten = amountFieldConten.substring(0, amountFieldConten.length() - 1);
                            } else {
                                amountFieldConten = "0";
                            }
                        
                            amountFieldConten = amountFieldConten+" VND";
                            amountField.setText(amountFieldConten);
                            return;
                    }
                    result.append(amountFieldConten.charAt(i));
                    dem++;
                    if (dem == 3) {
                        result.append(",");
                        dem = 0;
                    }
                }

                amountFieldConten = result.reverse().toString(); // Trả về chuỗi đã chuyển đổi
               
                if(amountFieldConten == null || amountFieldConten ==""){
                    amountField.setText("Số tiền: ");
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
                @Override
                public void actionPerformed(ActionEvent e) {
                    amountField.setText(amount.substring(0, amount.length())); // Gán giá trị nút vào amountField
                }
            });
            amountButtonPanel.add(amountButton);
        }
        // nội dung chuyển tiền
        JTextArea contentField = new JTextArea(senderAccount.getUserName()+ " chuyển tiền", 3, 20);
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contentField);

        // Panel chứa các nút nội dung
        JPanel contentButtonPanel = new JPanel(new GridLayout(1, 6, 5, 10));
       
        String[] contents = { "mua hàng", "chúc mừng", "cafe", "ăn trưa", "ăn tối", "trả nợ" };
        String senderNameMessage = senderAccount.getUserName()+ " chuyển tiền ";
        for (String content : contents) {
            JButton contentButton = new JButton(content);
            contentButton.setMargin(new Insets(1, 0, 1, 0));
            contentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    contentField.setText(senderNameMessage + content); // Gán giá trị nút vào contentField
                }
            });
            contentButtonPanel.add(contentButton);
        }

        // nút Tiếp tục - Xử lý sự kiện chuyển tiền
        JButton continueButton = new JButton("Tiếp tục");
        continueButton.setBackground(new Color(103, 58, 183));
        continueButton.setForeground(Color.WHITE);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean checkTranferForm = isValid(receiverNameField.getText(), amountField.getText(), senderAccount.getUserBalance());
                System.out.println(checkTranferForm);
                if (checkTranferForm){
                    String amountStr = amountField.getText();
                    amountStr = amountStr.replaceAll("[a-zA-Z]", "");// loại bỏ các kí tự trong chuỗi
                    amountStr= amountStr.replace(",", "");
                   
                    int amountMoney = Integer.parseInt(amountStr.trim());
                    boolean tranferResult = transactionServer.transferMoney(senderEmail, receiverAccountField.getText(), amountMoney);

                    if (tranferResult){
                        TranferRepond transactionBillResult =transactionServer.saveTransactionBill(senderAccount.getUserId(), receiverAccount.getUserId(), amountMoney, contentField.getText());
                        if(transactionBillResult.getIsResult()){
                            TranferRepond tranferBillRepond =new TranferRepond();
                            tranferBillRepond.setAmount(amountMoney);
                            tranferBillRepond.setSenderName(senderAccount.getUserName());
                            tranferBillRepond.setSenderPhone(senderAccount.getPhone());

                            tranferBillRepond.setReceiverName(receiverAccount.getUserName());
                            tranferBillRepond.setIdTranferBill(transactionBillResult.getIdTranferBill());
                            tranferBillRepond.setTranferMessage(contentField.getText());
                            tranferBillRepond.setTranferBillDate(transactionBillResult.getTranferBillDate());
                            System.out.println(transactionBillResult.getIsResult());
                            new TransferConfirmationUI(tranferBillRepond);
                            setVisible(false);
                        }
                    
                    }
                    }else{
                        return;
                    }
            }
            
            boolean isValid(String receiverNameField, String amountStr, int senderBalance) {
                
                amountStr = amountStr.replaceAll("[a-zA-Z]", "");// loại bỏ các kí tự trong chuỗi
                amountStr= amountStr.replace(",", "");
            
                if (receiverNameField == "" || receiverNameField == null) {
            
                    JOptionPane.showMessageDialog(null, "Người nhận không tồn tại");
                    return false;
                }
                try {
                // System.out.println("chuyenssss");
                    int amountInt = Integer.parseInt(amountStr.trim());
                    if (senderBalance < amountInt) {
                    
                        JOptionPane.showMessageDialog(null, "Số dư không đủ");
                        return false;
                    }
                } catch (NumberFormatException e) {
                    // System.out.println("Chuỗi không phải là số hợp lệ.");
                
                    return false;
                }
                return true;
            }
        });
       




        transferFormPanel.add(receiverAccountField);
        transferFormPanel.add(receiverNameField);
        transferFormPanel.add(amountField);
        transferFormPanel.add(amountButtonPanel);
        transferFormPanel.add(scrollPane);
        transferFormPanel.add(contentButtonPanel);
        transferFormPanel.add(continueButton);

        add(sourcePanel, BorderLayout.NORTH);
        add(transferFormPanel, BorderLayout.CENTER);
        setVisible(true);
      
    }

    


    public static void main(String[] args) {
        new TransferUI("king77nt54321@gmail.com");
    }
}
