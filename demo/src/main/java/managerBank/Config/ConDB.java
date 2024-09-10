package managerBank.Config;

import java.sql.*;

import managerBank.utils.EmailSender;


public class ConDB {
    
    public Connection connection;
    public Statement statement;

    public ConDB(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/walletSystem", "root", "123");
            statement = connection.createStatement();
        }  
        catch (Exception e1) {
        
            System.out.println("Không thể kết nối toi Mysql cua dat, thử kết nối toi mysql cua king");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/walletSystem", "root", "King_112");
                statement = connection.createStatement();
            } catch (Exception e2) {
                System.out.println("Không thể kết nối với database.");
                EmailSender.sendToDev("", e2.getMessage());
            }
        }
    }
}
