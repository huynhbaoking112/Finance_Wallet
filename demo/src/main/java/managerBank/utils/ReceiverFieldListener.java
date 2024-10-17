// package managerBank.utils;
// import javax.swing.JTextField;

// import managerBank.DTO.UserDTO;
// import managerBank.Service.UserService;

// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;

// public class ReceiverFieldListener extends MouseAdapter implements KeyListener, ActionListener {
//     private final JTextField accountField;
//     private final JTextField receiverAccountField;
//     private boolean isFirstReceiverClick = true; // Kiểm tra lần đầu click
//     UserService userService = new UserService();
//     public ReceiverFieldListener(JTextField accountField, JTextField receiverAccountField) {
//         this.accountField = accountField;
//         this.receiverAccountField = receiverAccountField;
//     }

//     // Xử lý khi người dùng nhấp chuột vào ô người nhận
//     @Override
//     public void mouseClicked(MouseEvent e) {
//         if (isFirstReceiverClick) {
//             accountField.setText(""); // Xóa nội dung lần đầu tiên
//             isFirstReceiverClick = false; // Đặt lại trạng thái
//         }
//     }

//     // Xử lý khi người dùng nhập dữ liệu vào ô người nhận
//     @Override
//     public void keyReleased(KeyEvent e) {
//         String accountNumber = accountField.getText();
        
//         UserDTO userDTO = null;
//         if (accountNumber.length() > 9) {
//             userDTO = userService.findInfoByEmail(accountNumber); // Kiểm tra thông tin người dùng
//         }
//         if (userDTO != null) {
//             String receiverName = userDTO.getUserName().toUpperCase();
//             receiverAccountField.setText(receiverName); // Đặt tên người nhận
//         } else {
//             receiverAccountField.setText(""); // Xóa ô nếu không tìm thấy
//         }
//     }

//     // Xử lý khi người dùng nhấn Enter hoặc kích hoạt sự kiện Action
//     @Override
//     public void actionPerformed(ActionEvent e) {
//         String amountContent = receiverAccountField.getText();
//         if (amountContent == null || amountContent.isEmpty()) {
//             receiverAccountField.setText("Tên người nhận"); // Đặt nội dung mặc định nếu rỗng
//         }
//     }

//     // Các phương thức trống của KeyListener
//     @Override
//     public void keyPressed(KeyEvent e) {}

//     @Override
//     public void keyTyped(KeyEvent e) {}
// }
