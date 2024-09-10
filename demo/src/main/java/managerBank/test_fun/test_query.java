package managerBank.test_fun;

import managerBank.Config.ConDB;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;

public class test_query {

    public static void main(String[] args) {
        ConDB con = new ConDB();
        String query2 = "SELECT id FROM users WHERE email = 'king77nt54321@gmail.com' UNION SELECT id FROM users WHERE phone = '0374583408'";
        try {
            // Thực thi truy vấn và lấy ResultSet
            ResultSet rs = con.statement.executeQuery(query2);
            
            // Kiểm tra và duyệt qua các kết quả trong ResultSet
            System.out.println(rs.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
