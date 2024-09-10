package managerBank.Service;


import managerBank.Model.User;
import managerBank.Repo.UserRepo;

public class UserService {
    //Connect DB
    UserRepo con1;
    public UserService(){
        con1 = new UserRepo();
    };

    public void signUp(User user){
        con1.saveToDB(user);
    } 
}
    