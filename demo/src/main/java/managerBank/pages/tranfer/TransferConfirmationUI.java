

// package managerBank.pages.tranfer;
// import javax.swing.*;
// import java.awt.*;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// public class TPBankBillUI extends JFrame {

//     public TPBankBillUI(String senderName, String senderAccount, 
//                         String receiverName, String receiverAccount, 
//                         String amount, String content) {
//         // Thiết lập tiêu đề cửa sổ
//         super("KDL Transfer Bill");

//         // Thiết lập giao diện chính
//         setSize(400, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);

//         // Tạo JPanel chính
//         JPanel panel = new JPanel() {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 super.paintComponent(g);
//                 // Đặt màu nền tối
//                 g.setColor(new Color(33, 11, 62));
//                 g.fillRect(0, 0, getWidth(), getHeight());
//             }
//         };
//         panel.setLayout(null);

//         // Logo TPBank giả lập
//         JLabel lblLogo = new JLabel("KDL WALLET", JLabel.CENTER);
//         lblLogo.setFont(new Font("Arial", Font.BOLD, 24));
//         lblLogo.setForeground(new Color(255, 165, 0)); // Màu cam
//         lblLogo.setBounds(100, 10, 200, 30);
//         panel.add(lblLogo);

//         // Dòng thông báo giao dịch thành công
//         JLabel lblSuccess = new JLabel("Giao Dịch Thành Công!", JLabel.CENTER);
//         lblSuccess.setFont(new Font("Arial", Font.BOLD, 16));
//         lblSuccess.setForeground(Color.WHITE);
//         lblSuccess.setBounds(100, 50, 200, 30);
//         panel.add(lblSuccess);

//         // Số tiền giao dịch
//         JLabel lblAmount = new JLabel(amount + " VND", JLabel.CENTER);
//         lblAmount.setFont(new Font("Arial", Font.BOLD, 28));
//         lblAmount.setForeground(Color.WHITE);
//         lblAmount.setBounds(50, 90, 300, 40);
//         panel.add(lblAmount);

//         // Tên người gửi
//         JLabel lblSender = new JLabel("Người gửi: " + senderName, JLabel.CENTER);
//         lblSender.setFont(new Font("Arial", Font.PLAIN, 14));
//         lblSender.setForeground(Color.WHITE);
//         lblSender.setBounds(50, 150, 300, 30);
//         panel.add(lblSender);

//         // Số tài khoản người gửi
//         JLabel lblSenderAccount = new JLabel("Số tài khoản: " + senderAccount, JLabel.CENTER);
//         lblSenderAccount.setFont(new Font("Arial", Font.PLAIN, 14));
//         lblSenderAccount.setForeground(Color.GRAY);
//         lblSenderAccount.setBounds(50, 180, 300, 30);
//         panel.add(lblSenderAccount);

//         // Tên người nhận
//         JLabel lblReceiver = new JLabel("Người nhận: " + receiverName, JLabel.CENTER);
//         lblReceiver.setFont(new Font("Arial", Font.PLAIN, 14));
//         lblReceiver.setForeground(Color.WHITE);
//         lblReceiver.setBounds(50, 220, 300, 30);
//         panel.add(lblReceiver);

//         // Số tài khoản người nhận
//         JLabel lblReceiverAccount = new JLabel("Số tài khoản: " + receiverAccount, JLabel.CENTER);
//         lblReceiverAccount.setFont(new Font("Arial", Font.PLAIN, 14));
//         lblReceiverAccount.setForeground(Color.GRAY);
//         lblReceiverAccount.setBounds(50, 250, 300, 30);
//         panel.add(lblReceiverAccount);

//         // Nội dung giao dịch
//         JLabel lblContent = new JLabel("Nội dung: " + content, JLabel.CENTER);
//         lblContent.setFont(new Font("Arial", Font.PLAIN, 14));
//         lblContent.setForeground(Color.WHITE);
//         lblContent.setBounds(50, 290, 300, 30);
//         panel.add(lblContent);

//         // Thời gian giao dịch
//         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//         String currentTime = formatter.format(new Date());
//         JLabel lblTime = new JLabel("Thời gian: " + currentTime, JLabel.CENTER);
//         lblTime.setFont(new Font("Arial", Font.PLAIN, 14));
//         lblTime.setForeground(Color.GRAY);
//         lblTime.setBounds(50, 330, 300, 30);
//         panel.add(lblTime);

//         // Nút "Lưu" và "Chia sẻ"
//         JButton btnSave = new JButton("Lưu hóa đơn");
//         btnSave.setBounds(60, 400, 120, 40);
//         btnSave.setBackground(new Color(103, 58, 183));
//         btnSave.setForeground(Color.WHITE);
//         panel.add(btnSave);

//         JButton btnShare = new JButton("Chia sẻ");
//         btnShare.setBounds(220, 400, 120, 40);
//         btnShare.setBackground(new Color(103, 58, 183));
//         btnShare.setForeground(Color.WHITE);
//         panel.add(btnShare);

//         // Thêm panel vào JFrame
//         add(panel);
//         setVisible(true);
//     }

//     public static void main(String[] args) {
//         // Ví dụ thông tin chuyển khoản
//         String senderName = "Nguyen Minh Nhat";
//         String senderAccount = "123456789";
//         String receiverName = "Nguyen Thi Phuong Tuyen";
//         String receiverAccount = "987654321";
//         String amount = "1,000,000";
//         String content = "Chuyen tien thanh toan";

//         new TPBankBillUI(senderName, senderAccount, receiverName, receiverAccount, amount, content);
//     }
// }































package managerBank.pages.tranfer;
import javax.swing.*;

import managerBank.DTO.TranferRepond;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class TransferConfirmationUI {
        public TransferConfirmationUI(){

        }
        public  TransferConfirmationUI( TranferRepond tranferRepond){
        // Tạo JFrame chính
        JFrame frame = new JFrame("TPBank Transfer Confirmation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 800);
        frame.setLayout(new BorderLayout());

        // Tạo Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Sử dụng layout null để tùy chỉnh vị trí từng phần tử
        mainPanel.setBackground(new Color(247, 247, 247)); // Màu nền sáng

     //   Icon xác nhận 
     // logo vi
       JLabel checkIcon = new JLabel(new ImageIcon("C:\\Users\\tiend\\OneDrive\\Máy tính\\ảnhnenchuyen.jpg")); // Thay thế bằng đường dẫn icon
        checkIcon.setBounds(170, 40, 100, 100); // Định vị trí và kích thước cho icon
        mainPanel.add(checkIcon);

        // Thông báo xác nhận
        JLabel successLabel = new JLabel("Giao Dịch Đã Được KDL WALLET Thực Hiện!");
        successLabel.setFont(new Font("Arial", Font.BOLD, 16));
        successLabel.setForeground(new Color(98, 0, 238)); // Màu tím đậm
        successLabel.setHorizontalAlignment(SwingConstants.CENTER);
        successLabel.setBounds(50, 160, 350, 30); // Vị trí và kích thước
        mainPanel.add(successLabel);

            // Số tiền chuyển
         NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedNumber = numberFormat.format(tranferRepond.getAmount()) +" VND";
      
      
        JLabel amountLabel = new JLabel(formattedNumber);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 36));
        amountLabel.setForeground(Color.BLACK);
        amountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        amountLabel.setBounds(100, 200, 250, 40); // Vị trí và kích thước
        mainPanel.add(amountLabel);

        // Cảnh báo chờ phản hồi
        JLabel sloganLabel = new JLabel("KDL Lựa chọn thông minh, lòng tin dẫn lối");
        sloganLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        sloganLabel.setForeground(new Color( 150, 50, 0)); // Màu cam
        sloganLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sloganLabel.setBounds(70, 250, 300, 30); // Vị trí và kích thước
        mainPanel.add(sloganLabel);

        // Các nút bấm: "Chia Sẻ Qua Email" và "Lưu Mẫu"
        JButton shareButton = new JButton("Chia Sẻ Qua Email");
        shareButton.setFont(new Font("Arial", Font.PLAIN, 14));
        shareButton.setBackground(new Color(255, 165, 0));
        shareButton.setForeground(Color.WHITE);
        shareButton.setBounds(60, 300, 160, 40); // Vị trí và kích thước
        mainPanel.add(shareButton);

        JButton saveButton = new JButton("Lưu Mẫu");
        saveButton.setFont(new Font("Arial", Font.PLAIN, 14));
        saveButton.setBackground(new Color(255, 165, 0));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBounds(230, 300, 150, 40); // Vị trí và kích thước
        mainPanel.add(saveButton);

        // Thông tin chi tiết giao dịch
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(6, 1));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1)); // Đường viền xám nhạt
        infoPanel.setBounds(30, 370, 380, 200); // Vị trí và kích thước

        // Thông tin giao dịch
        JLabel senderInfo = new JLabel("   Người gửi: " + tranferRepond.getSenderName()+ " - "+ tranferRepond.getSenderPhone() );
        JLabel receiverInfo = new JLabel("   Người nhận: "+ tranferRepond.getReceiverName() );
        JLabel transactionCode = new JLabel("   Mã giao dịch: 669V00924290AH9T");
        JLabel transactionContent = new JLabel("   Nội dung: " + tranferRepond.getTranferMessage());
        JLabel transferMethod = new JLabel("   Cách thức: Chuyển tiền nhanh Napas 247");
        JLabel transferTime = new JLabel("   Thời gian: " + tranferRepond.getTranferBillDate());

        senderInfo.setFont(new Font("Arial", Font.BOLD, 14));
        receiverInfo.setFont(new Font("Arial", Font.BOLD, 14));
        transactionCode.setFont(new Font("Arial", Font.BOLD, 14));
        transactionContent.setFont(new Font("Arial", Font.BOLD, 14));
        transferMethod.setFont(new Font("Arial", Font.BOLD, 14));
        transferTime.setFont(new Font("Arial", Font.BOLD, 14));

        infoPanel.add(senderInfo);
        infoPanel.add(receiverInfo);
        infoPanel.add(transactionCode);
        infoPanel.add(transactionContent);
        infoPanel.add(transferMethod);
        infoPanel.add(transferTime);
        mainPanel.add(infoPanel);

        // Các nút điều hướng khác: "Giao Dịch Khác" và "Trang Chủ"
        JButton otherTransactionButton = new JButton("Giao Dịch Khác");
        otherTransactionButton.setFont(new Font("Arial", Font.PLAIN, 14));
        otherTransactionButton.setBackground(new Color(128, 0, 128));
        otherTransactionButton.setForeground(Color.WHITE);
        otherTransactionButton.setBounds(60, 600, 150, 40); // Vị trí và kích thước
        otherTransactionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(tranferRepond.getSenderPhone());
               new TransferUI(tranferRepond.getSenderPhone());
               frame.setVisible(false);
            }
            
        });
        mainPanel.add(otherTransactionButton);

        JButton homeButton = new JButton("Trang Chủ");
        homeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        homeButton.setBackground(new Color(128, 0, 128));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBounds(230, 600, 150, 40); // Vị trí và kích thước
        mainPanel.add(homeButton);

        // Thêm Panel chính vào JFrame
        frame.add(mainPanel);
        frame.setVisible(true);
    }
        public static void main(String[] args) {
            TranferRepond tranferRepond = new TranferRepond();
            tranferRepond.setSenderName("Vu Tirn DAt");
            tranferRepond.setSenderPhone("03222777");
            tranferRepond.setReceiverName("King");
            tranferRepond.setReceiverPhone("0000000000000000");
            tranferRepond.setTranferMessage("test chuyen tien");
            tranferRepond.setAmount(500000);
            new TransferConfirmationUI(tranferRepond);
    }
}
