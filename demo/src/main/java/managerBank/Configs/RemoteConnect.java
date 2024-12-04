package managerBank.Configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import managerBank.utils.EmailSender;

public class RemoteConnect {
    
    public Connection connection;
    public Statement statement;

    public RemoteConnect(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/walletsystem", "root", "");
           // System.out.println("ket noi voi db cua dat thanh cong");
            statement = connection.createStatement();
        }  
        catch (Exception e1) {

          //  System.out.println("Không thể kết nối toi Mysql cua dat, thử kết nối toi mysql cua king");
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
