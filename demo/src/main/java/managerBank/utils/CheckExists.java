package managerBank.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import managerBank.Config.ConDB;

public class CheckExists {

    
    
    public static boolean checkExistsEmail(String email, ConDB con){
        try {
            String query2 = "SELECT id FROM users WHERE email = ?";
            PreparedStatement pre = con.connection.prepareStatement(query2);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();    
            return rs.next();
        } catch (Exception e) {
           EmailSender.sendToDev(email, e.getMessage());
        }
        return true;
    }

    public static boolean checkExistsEmailPhone(String email, String phone, ConDB con){
        try {
            String query2 = "SELECT id FROM users WHERE email = ? UNION SELECT id FROM users WHERE phone = ? ";
            PreparedStatement pre = con.connection.prepareStatement(query2);
            pre.setString(1, email);
            pre.setString(2, phone);
            ResultSet rs = pre.executeQuery();    
            return rs.next();
        } catch (Exception e) {
           EmailSender.sendToDev(email, e.getMessage());
        }
        return true;
    }

    public static boolean checkExistCCCD(String cccd, ConDB con){
        try {
            String query2 = "SELECT email FROM user_infor WHERE cccd = ?";
            PreparedStatement pre = con.connection.prepareStatement(query2);
            pre.setString(1, cccd);
            ResultSet rs = pre.executeQuery();    
            return rs.next();
        } catch (Exception e) {
           EmailSender.sendToDev(cccd, e.getMessage());
        }
        return true;
    }

    public static boolean checkPasswordCorrect(String email, String password, ConDB con){
        try {
            String query2 = "SELECT password FROM Users WHERE email = ?";
            PreparedStatement pre = con.connection.prepareStatement(query2);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();    
            if(rs.next()){
                String storedPassword = rs.getString("password");
                return HandlePassword.checkPassword(password, storedPassword);
            }else{
                return false;
            }
        } catch (Exception e) {
           EmailSender.sendToDev(email, e.getMessage());
        }
        return false;
    }

    public static boolean checkActive(String email, ConDB con){
        try {
            String query2 = "SELECT is_active FROM Users WHERE email = ? ";
            PreparedStatement pre = con.connection.prepareStatement(query2);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();    
            if(rs.next()){
                String active = rs.getString("is_active");
                return active.equals("1");
            }else{
                return false;
            }
        } catch (Exception e) {
           EmailSender.sendToDev(email, e.getMessage());
        }
        return false;
    }


}
