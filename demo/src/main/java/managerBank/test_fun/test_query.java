package managerBank.test_fun;

import managerBank.Config.ConDB;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class test_query {

    public static void main(String[] args) {
        ConDB con = new ConDB();
        String query2 = "SELECT * FROM users WHERE email = 'king77nt54321@gmail.com'";
        try {
            // Thực thi truy vấn và lấy ResultSet
            ResultSet rs = con.statement.executeQuery(query2);
            
            // Kiểm tra và duyệt qua các kết quả trong ResultSet
            if (rs.next()) {
                // Lấy metadata của ResultSet
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                
                // Duyệt qua tất cả các cột và in giá trị
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnValue = rs.getString(i);
                    System.out.println(columnName + ": " + columnValue);
                }
            } else {
                System.out.println("No user found with this email.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
