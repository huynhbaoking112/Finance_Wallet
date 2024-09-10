package managerBank.Config;

import java.sql.*;

import managerBank.utils.EmailSender;


public class ConDB {
    
    public Connection connection;
    public Statement statement;

    public ConDB(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/walletSystem", "root", "King_112");
            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println("Loi tai connect DB");
            EmailSender.sendToDev("", e.getMessage());
        }
    }

}
