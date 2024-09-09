package managerBank.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HandlePassword  {
     public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
