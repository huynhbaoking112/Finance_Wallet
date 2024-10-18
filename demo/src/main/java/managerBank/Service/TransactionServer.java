package managerBank.Service;


import java.sql.*;
import java.text.SimpleDateFormat;

import managerBank.DTO.TranferRepond;
import managerBank.utils.EmailSender;

public class TransactionServer {
    public Connection connection;
    public Statement statement;
    public TransactionServer() {
         try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/walletsystem", "root", "");
          //  System.out.println("ket noi voi db cua dat thanh cong");
            statement = connection.createStatement();
        }  
        catch (Exception e1) {

          //  System.out.println("Không thể kết nối toi Mysql cua dat, thử kết nối toi mysql cua king");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/walletSystem", "root", "King_112");
                statement = connection.createStatement();
            } catch (Exception e2) {
               // System.out.println("Không thể kết nối với database.");
                EmailSender.sendToDev("", e2.getMessage());
            }
        }
    }


    // Phương thức chuyển tiền 
    public boolean transferMoney(String fromAccount, String toAccount, int amount) {
      int retryCount = 5; // Số lần thử tối đa
      while (retryCount > 0) {
          try {
              // Bắt đầu transaction
              connection.setAutoCommit(false);
              // Lấy số dư và phiên bản tài khoản người gửi
              String selectQuery = "SELECT user_id, name, balance, version FROM walletsystem.users "+
                                "LEFT JOIN walletsystem.wallet"+
                                " ON wallet.id = users.id "+
								"WHERE users.email = ? or phone = ?";
              PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
              selectStmt.setString(1, fromAccount);
              selectStmt.setString(2, fromAccount);
              ResultSet rs = selectStmt.executeQuery();
  
              if (!rs.next()) {
                  System.out.println("Sender account not found!");
                  connection.rollback();
                  return false;
              }
  
              int fromBalance = rs.getInt("balance");
              int idSender = rs.getInt("user_id");
              int fromVersion = rs.getInt("version");
  
              if (fromBalance < amount) {
                  System.out.println("Insufficient balance!");
                  connection.rollback();
                  return false;
              }
  
              // Lấy số dư và phiên bản tài khoản người nhận
              selectStmt.setString(1, toAccount);
              selectStmt.setString(2, toAccount);
              rs = selectStmt.executeQuery();
  
              if (!rs.next()) {
                  System.out.println("Receiver account not found!");
                  connection.rollback();
                  return false;
              }
  
              int toBalance = rs.getInt("balance");
              int toVersion = rs.getInt("version");
              int idReceiver = rs.getInt("user_id");
              // Cập nhật số dư mới
              fromBalance -= amount;
              toBalance += amount;
  
              // Cập nhật tài khoản người gửi
              String updateFromQuery = "UPDATE wallet SET balance = ?, version = version + 1 WHERE id = ? AND version = ?";
              PreparedStatement updateFromStmt = connection.prepareStatement(updateFromQuery);
              updateFromStmt.setInt(1, fromBalance);
              updateFromStmt.setInt(2, idSender);
              updateFromStmt.setInt(3, fromVersion);
  
              int affectedRowsFrom = updateFromStmt.executeUpdate();
              if (affectedRowsFrom == 0) {
                  System.out.println("Optimistic lock failed for sender account!");
                  connection.rollback();
                  retryCount--; // Giảm số lần thử
                  continue; // Thử lại
              }
  
              // Cập nhật tài khoản người nhận
              String updateToQuery = "UPDATE wallet SET balance = ?, version = version + 1 WHERE id = ? AND version = ?";
              PreparedStatement updateToStmt = connection.prepareStatement(updateToQuery);
              updateToStmt.setInt(1, toBalance);
              updateToStmt.setInt(2, idReceiver);
              updateToStmt.setInt(3, toVersion);
  
              int affectedRowsTo = updateToStmt.executeUpdate();
              if (affectedRowsTo == 0) {
                  System.out.println("Optimistic lock failed for receiver account!");
                  connection.rollback();
                  retryCount--; // Giảm số lần thử
                  continue; // Thử lại
              }
  
              // Commit transaction nếu cả hai cập nhật thành công
              connection.commit();
              System.out.println("Transaction successful!");
              return true;
  
          } catch (SQLException e) {
              e.printStackTrace();
              try {
                  connection.rollback();
              } catch (SQLException rollbackEx) {
                  rollbackEx.printStackTrace();
              }
              return false;
          } finally {
              try {
                  // Đảm bảo auto-commit được bật lại
                  connection.setAutoCommit(true);
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
      }
      
      // Sau 5 lần thử vẫn không thành công
    //  System.out.println("Transaction failed after 5 attempts!");
      return false;
  }
  

//   public TranferRepond saveTransactionBill (int idSender, int idReceiver , int amount , String tranferMessage){
//     TranferRepond tranferRepond = new TranferRepond();
   
//     try {
//         String SQL= " INSERT INTO walletsystem.transaction_log(id_sender, id_received,amount,message)" +
//                 " VALUES (?,?,?,?)" ;
//         PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//         preparedStatement.setInt(1, idSender);
//         preparedStatement.setInt(2, idReceiver);
//         preparedStatement.setInt(3, amount);
//         preparedStatement.setString(4, tranferMessage);
//         int colum= preparedStatement.executeUpdate();
//         if(colum == 0 ){
//             System.out.println("luu hoa don khong thanh cong");
//             tranferRepond.setResult(false);
//             System.out.println("cot khong thay doi");
//         } else{
//             ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//             if (generatedKeys.next()) {
//                 int IDbill = generatedKeys.getInt("ID");
//                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                 Timestamp timestamp = generatedKeys.getTimestamp("time");
//                 String billDate = dateFormat.format(timestamp);
                
//                 tranferRepond.setResult(true);
//                 tranferRepond.setIdTranferBill(IDbill);
//                 tranferRepond.setTranferBillDate(billDate);
//                 System.out.println("cotddddddddddddd dddddddddddthay doi");
//                 return tranferRepond;
//             }
//         }


//     } catch (SQLException e) {
//         System.out.println("sssssssss");
//         tranferRepond.setResult(false);
//     }
//     return tranferRepond;
//   }
// }


    public TranferRepond saveTransactionBill(int idSender, int idReceiver, int amount, String tranferMessage) {
        TranferRepond tranferRepond = new TranferRepond();

        String SQL = "INSERT INTO walletsystem.transaction_log(id_sender, id_received, amount, message) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, idSender);
            preparedStatement.setInt(2, idReceiver);
            preparedStatement.setInt(3, amount);
            preparedStatement.setString(4, tranferMessage);

            int colum = preparedStatement.executeUpdate();
            if (colum == 0) {
                System.out.println("Lưu hóa đơn không thành công");
                tranferRepond.setResult(false);
                return tranferRepond;
            } else {
                
                String billSQL = " SELECT ID, amount, message, time FROM walletsystem.transaction_log WHERE id_sender = ? AND id_received = ? ORDER BY ID DESC LIMIT 1";
               
                try(PreparedStatement billPreparedStatement = connection.prepareStatement(billSQL)){
                    billPreparedStatement.setInt(1, idSender);
                    billPreparedStatement.setInt(2, idReceiver);
                    ResultSet rs = billPreparedStatement.executeQuery();
                    
                    if(rs.next()){
                        int IDbill = rs.getInt("ID"); // Lấy khóa tự động
                        Timestamp timestamp = rs.getTimestamp("time"); // Nếu bạn đã thêm cột thời gian vào bảng
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String billDate = dateFormat.format(timestamp);
                        
                        tranferRepond.setResult(true);
                        tranferRepond.setIdTranferBill(IDbill);
                        tranferRepond.setTranferBillDate(billDate);
                    }
                }catch (SQLException e){

                }
                return tranferRepond;
            }
            
        } catch (SQLException e) {
            System.out.println("Loi SQL: " + e.getMessage());
            tranferRepond.setResult(false);
        }

        return tranferRepond;
    }
}





























