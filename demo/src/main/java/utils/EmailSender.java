package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    // Phương thức để đọc nội dung HTML từ file
    private static String readHtmlFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    //Phương thức gửi email chính
    public static void sendEmail(String recipientEmail, String subject, String htmlFilePath, Map<String, String> a) {
        // Cấu hình các thuộc tính để gửi email
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP server của Gmail
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Xác thực email và mật khẩu
        String username = "n22dccn146@student.ptithcm.edu.vn"; 
        String password = "n22dccn146#200804"; 

        // Tạo session với thông tin đăng nhập
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Đọc nội dung HTML từ file
            String htmlContent = readHtmlFromFile(htmlFilePath);


            // Thay thế {{username}} bằng tên người dùng thực tế
            for (Map.Entry<String, String> entry : a.entrySet()) {
                htmlContent = htmlContent.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }


            // Tạo một email mới
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Địa chỉ email người gửi
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail)); // Địa chỉ email người nhận
            message.setSubject(subject); // Tiêu đề email

            // Thiết lập nội dung email là HTML
            message.setContent(htmlContent, "text/html; charset=utf-8");

            // Gửi email
            Transport.send(message);

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    //Phương thức gửi email cho dev
    public static void sendToDev(String user, String errorMess){

        // Đường dẫn tới tệp HTML
        String htmlFilePath = "C:\\Users\\huynh\\OneDrive\\Desktop\\basecode\\demo\\src\\main\\java\\assets\\errorForDev.html"; 
        Map<String, String> placeholders = new HashMap<>();
        LocalDate currentDate = LocalDate.now();
        placeholders.put("timestamp", currentDate.toString());
        placeholders.put("user", user);
        placeholders.put("errorDescription", errorMess);
        // Gửi email
        sendEmail("king77nt54321@gmail.com", "Error from KDL-Wallet", htmlFilePath, placeholders); 

    }
    
    //Phương thức gửi email chào mừng
    public static void welcomEmail(String email, String username){

        // Đường dẫn tới tệp HTML
        String htmlFilePath = "C:\\Users\\huynh\\OneDrive\\Desktop\\basecode\\demo\\src\\main\\java\\assets\\errorForDev.html"; 
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("username", username);
        // Gửi email
        sendEmail(email, "Welcome to KDL-Wallet", htmlFilePath, placeholders); 

    }

    public static void main(String[] args) {
        // Đường dẫn tới tệp HTML
        // String htmlFilePath = "C:\\Users\\huynh\\OneDrive\\Desktop\\basecode\\demo\\src\\main\\java\\assets\\errorForDev.html"; 

        //  Map<String, String> placeholders = new HashMap<>();
        //  LocalDate currentDate = LocalDate.now();
        // placeholders.put("timestamp", currentDate.toString());
        // placeholders.put("user", "king");
        // placeholders.put("errorDescription", "loi ne");

        // // Gửi email
        // sendEmail("king77nt54321@gmail.com", "Welcome to KDL-Wallet", htmlFilePath, placeholders);
    }
}
