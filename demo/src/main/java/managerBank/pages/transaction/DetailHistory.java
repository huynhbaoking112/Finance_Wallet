package managerBank.pages.transaction;

import javax.swing.*;

import managerBank.DTO.TranferRepond;
import managerBank.DTO.UserDTO;
import managerBank.Service.UserService;
import managerBank.pages.dashboard.Dashboard;
import managerBank.pages.tranfer.TransferUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class DetailHistory {
        UserDTO senderUser ;
        UserDTO receiverUser;
        UserService userService;
        public DetailHistory(){

        }
        public  DetailHistory( TranferRepond tranferRepond){
        // Tạo JFrame chính
        userService = new UserService();
        senderUser = userService.findUserbyID(tranferRepond.getIdSender());
        receiverUser = userService.findUserbyID(tranferRepond.getIdReceiver());
        JFrame frame = new JFrame("KDL WALLET Transfer Confirmation");
        frame.setBounds(550, 150, 450, 700);
      //  frame.setSize(450, 800);
        frame.setLayout(new BorderLayout());

        // Tạo Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Sử dụng layout null để tùy chỉnh vị trí từng phần tử
        mainPanel.setBackground(new Color(247, 247, 247)); // Màu nền sáng

     //   Icon xác nhận 
     // logo vi
       JLabel checkIcon = new JLabel(new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\logoxacnhan.jpg")); 
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
        
        JLabel senderInfo = new JLabel("   Người gửi: " + senderUser.getUserName()+ " - "+ senderUser.getPhone());
        JLabel receiverInfo = new JLabel("   Người nhận: "+ receiverUser.getUserName());
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



        // Thêm Panel chính vào JFrame
        frame.add(mainPanel);
        frame.setVisible(true);
    }
        public static void main(String[] args) {
            TranferRepond tranferRepond = new TranferRepond();
            tranferRepond.setIdSender(17);
            tranferRepond.setIdReceiver(18);
            tranferRepond.setTranferMessage("test chuyen tien");
            tranferRepond.setAmount(500000);
            new DetailHistory(tranferRepond);
    }
}


