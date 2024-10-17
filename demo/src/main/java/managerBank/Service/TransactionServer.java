package managerBank.Service;


import java.sql.*;

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
    public boolean transferMoney(int fromAccount, int toAccount, int amount) {
      int retryCount = 5; // Số lần thử tối đa
      while (retryCount > 0) {
          try {
              // Bắt đầu transaction
              connection.setAutoCommit(false);
              // Lấy số dư và phiên bản tài khoản người gửi
              String selectQuery = "SELECT balance, version FROM accounts WHERE id = ?";
              PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
              selectStmt.setInt(1, fromAccount);
              ResultSet rs = selectStmt.executeQuery();
  
              if (!rs.next()) {
                  System.out.println("Sender account not found!");
                  connection.rollback();
                  return false;
              }
  
              int fromBalance = rs.getInt("balance");
              int fromVersion = rs.getInt("version");
  
              if (fromBalance < amount) {
                  System.out.println("Insufficient balance!");
                  connection.rollback();
                  return false;
              }
  
              // Lấy số dư và phiên bản tài khoản người nhận
              selectStmt.setInt(1, toAccount);
              rs = selectStmt.executeQuery();
  
              if (!rs.next()) {
                  System.out.println("Receiver account not found!");
                  connection.rollback();
                  return false;
              }
  
              int toBalance = rs.getInt("balance");
              int toVersion = rs.getInt("version");
  
              // Cập nhật số dư mới
              fromBalance -= amount;
              toBalance += amount;
  
              // Cập nhật tài khoản người gửi
              String updateFromQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE id = ? AND version = ?";
              PreparedStatement updateFromStmt = connection.prepareStatement(updateFromQuery);
              updateFromStmt.setInt(1, fromBalance);
              updateFromStmt.setInt(2, fromAccount);
              updateFromStmt.setInt(3, fromVersion);
  
              int affectedRowsFrom = updateFromStmt.executeUpdate();
              if (affectedRowsFrom == 0) {
                  System.out.println("Optimistic lock failed for sender account!");
                  connection.rollback();
                  retryCount--; // Giảm số lần thử
                  continue; // Thử lại
              }
  
              // Cập nhật tài khoản người nhận
              String updateToQuery = "UPDATE accounts SET balance = ?, version = version + 1 WHERE id = ? AND version = ?";
              PreparedStatement updateToStmt = connection.prepareStatement(updateToQuery);
              updateToStmt.setInt(1, toBalance);
              updateToStmt.setInt(2, toAccount);
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
}
































