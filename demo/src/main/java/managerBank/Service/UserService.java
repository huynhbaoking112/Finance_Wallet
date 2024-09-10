package managerBank.Service;


import javax.swing.JOptionPane;
import managerBank.Config.ConDB;
import managerBank.Model.User;
import managerBank.utils.CheckExists;

public class UserService {
    //Connect DB
    ConDB con1;
    public UserService(){
        con1 = new ConDB();
    };

    public void signUp(User user){
            //Check các trường đã được điền chưa
            if (user.getName().equals("") || user.getPhone().equals("") || user.getGender().equals("") || user.getEmail().equals("") || user.getMarital().equals("")
                    || user.getAddress().equals("") || user.getPassword().equals("")|| user.getDob() == null) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            }
            // kiểm tra xem mật có trên 8 kí tự hay chưa
            else if(user.getPassword().length() < 8){
                JOptionPane.showMessageDialog(null, "Password must be at least 8 character!!!");
            }
            else if(!user.getPassword().equals(user.getComfirmPassword())){
                JOptionPane.showMessageDialog(null, "Password  and Comfirm Password is not same !!!");
            }
            //Check email hay phone đã tồn tại chưa
            else if(CheckExists.checkExistsEmailPhone(user.getEmail(), user.getPhone(), con1)){
                JOptionPane.showMessageDialog(null, "Email or Phone is exists!");
            }

             // Tạo user mới và wallet của nó
            else {
               
              con1.saveToDB(user);
        } 
    }
    

}