package managerBank.utils;

public class ValidationBalance {
    public static String BalanceValidation (int number){

        String balance = Integer.toString(number);
        StringBuilder result = new StringBuilder();

        // Duyệt qua từng ký tự trong chuỗi
        int dem = 0;
        for (int i = balance.length() - 1; i >= 0; i--) {
            if (balance.charAt(i) == ' ') {
                continue;
            }
            if ((balance.charAt(i) >= 'a') && (balance.charAt(i) <= 'z')
                    ||
               (balance.charAt(i) >= 'A') && (balance.charAt(i) <= 'Z')) {
                    //  JOptionPane.showMessageDialog(amountField, "Vui lòng không nhập kí tự trong ô số tiền");
                    if (balance.length() > 1) {
                        balance = balance.substring(0, balance.length() - 1);
                    } else {
                        balance = "0";
                    }
                
                    balance = balance+" VND";
                 
                    return balance;
            }
            result.append(balance.charAt(i));
            dem++;
            if (dem == 3) {
                result.append(".");
                dem = 0;
            }
        }

        balance = result.reverse().toString(); // Trả về chuỗi đã chuyển đổi
       
      
        if (balance.charAt(0) == '.') {
            balance = balance.substring(1, balance.length());
        }
        if (balance.charAt(0) == '0' && balance.length() > 1) {
            balance = balance.substring(1, balance.length());
        }
        balance = balance + " VND";
      
        return balance;
    }
    
}
