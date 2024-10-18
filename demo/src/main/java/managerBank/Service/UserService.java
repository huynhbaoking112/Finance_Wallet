package managerBank.Service;




import java.sql.*;
import managerBank.DTO.UserDTO;
import managerBank.Model.User;
import managerBank.Repo.UserRepo;

public class UserService {
    //Connect DB
    UserRepo repo;
    public UserService(){
        repo = new UserRepo();
    };

    public void signUp(User user){
        repo.saveToDB(user);
    } 
    public UserDTO findInfoByEmailOrPhone(String email){
        UserDTO userDTO = new UserDTO();
        try {
            String selectQuery = " SELECT user_id, name, balance, phone FROM walletsystem.wallet \n" + //
                                "LEFT JOIN users\n" + //
                                "ON wallet.id = users.id \n" + //
                                "WHERE email= ? or phone = ?";
            PreparedStatement selectStmt = repo.getDBConect().connection.prepareStatement(selectQuery);
            selectStmt.setString(1, email);
            selectStmt.setString(2, email);
            ResultSet rs = selectStmt.executeQuery();
            if(rs.next()){
                userDTO.setUserName((String)rs.getString("name"));
                userDTO.setPhone((String)rs.getString("phone"));
                userDTO.setUserBalance(rs.getInt("balance"));
                userDTO.setUserId(rs.getInt("user_id"));
                
            }else{
                System.out.println("USER NOT FOUND");
                return null;
            }
            return  userDTO;
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
        return null;
    }
    public UserDTO findInfoByEmail(String email){
        UserDTO userDTO = new UserDTO();
        try {
            String selectQuery = " SELECT user_id, name, balance, phone FROM walletsystem.wallet \n" + //
                                "LEFT JOIN users\n" + //
                                "ON wallet.id = users.id \n" + //
                                "WHERE email= ?";
            PreparedStatement selectStmt = repo.getDBConect().connection.prepareStatement(selectQuery);
            selectStmt.setString(1, email);
            ResultSet rs = selectStmt.executeQuery();
            if(rs.next()){
                userDTO.setUserName((String)rs.getString("name"));
                userDTO.setPhone((String)rs.getString("phone"));
                userDTO.setUserBalance(rs.getInt("balance"));
                userDTO.setUserId(rs.getInt("user_id"));
                
            }else{
                System.out.println("USER NOT FOUND");
                return null;
            }
            return  userDTO;
        } catch (SQLException e) {
          
            e.printStackTrace();
        }
        return null;
    }
    public UserDTO findInfoByPhone(String phone){
        UserDTO userDTO = new UserDTO();
        try {
            String selectQuery = " SELECT id, name FROM walletsystem.users WHERE phone = ?";
            PreparedStatement selectStmt = repo.getDBConect().connection.prepareStatement(selectQuery);
            selectStmt.setString(1, phone);
            ResultSet rs = selectStmt.executeQuery();
            if(rs.next()){
                userDTO.setUserName((String)rs.getString("name"));
                userDTO.setPhone((String)rs.getString("phone"));
                userDTO.setUserBalance(rs.getInt("balance"));
                userDTO.setUserId(rs.getInt("user_id"));
                return  userDTO;
            }
            System.out.println("USER NOT FOUND IN USER Service in find by phone");
            return null;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getBalanceWithPhone(String phone){
        
        try {
            String selectQuery = " SELECT user_id, name, balance, phone FROM walletsystem.wallet \n" + //
                                "LEFT JOIN users\n" + //
                                "ON wallet.id = users.id \n" + //
                                "WHERE phone= ?";
            PreparedStatement selectStmt = repo.getDBConect().connection.prepareStatement(selectQuery);
            selectStmt.setString(1, phone);
            ResultSet rs = selectStmt.executeQuery();
            int balance =0;
            if(rs.next()){
                balance = rs.getInt("balance");
                return  balance;
            }
            System.out.println("USER NOT FOUND IN USER Service in get balance by phone");
            return -1;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
    