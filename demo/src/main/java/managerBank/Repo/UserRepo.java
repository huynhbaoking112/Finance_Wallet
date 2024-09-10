package managerBank.Repo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import managerBank.Config.ConDB;
import managerBank.Model.User;
import managerBank.utils.EmailSender;
import managerBank.utils.HandlePassword;

public class UserRepo {
    

    public UserRepo(){
    }
    public void saveToDB(User user){
         // Chống tấn công sql injection nếu nâng cấp lên bảo mật cấp procedure thì tốt hơn  (đã nâng cấp lên procedure)
                // String query = "INSERT INTO users (name, father_name, dob, gender, email, marital_status, address, city, passwordcode, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                // PreparedStatement pstmt = con1.connection.prepareStatement(query);
                /*Một số ưu điểm của procedure hơn viết query thông thường
                    - Được lưu trữ và biên dịch sẵn trên csdl hiệu suất cao hơn với viết query thông thường 
                    - Giảm băng thôngclecl
                    - Ẩn dấu query khi bị tấn công server
                    - Dễ dàng thay đổi nghiệp vụ
                */ 
        try {
        ConDB DBConect = new ConDB();
        String query = "CALL signInUser(?,?,?,?,?,?,?,?);";
                PreparedStatement pstmt = DBConect.connection.prepareStatement(query);


                
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getPhone());
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dobStr = sdf.format(user.getDob());
                pstmt.setString(3, dobStr);
                pstmt.setString(4, user.getGender());
                pstmt.setString(5, user.getEmail());
                pstmt.setString(6, user.getMarital());
                pstmt.setString(7, user.getAddress());
                pstmt.setString(8, HandlePassword.hashPassword(user.getPassword()));


                // Thực thi câu lệnh
                pstmt.executeUpdate();

                // Giải phóng
                pstmt.close();
                DBConect.connection.close();

            }
        catch (SQLException E) {
            EmailSender.sendToDev("", E.getMessage());
            JOptionPane.showMessageDialog(null, "Error occurred while inserting data: " + E.getMessage());
        }
    }
    
}
