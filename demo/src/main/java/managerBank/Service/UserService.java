package managerBank.Service;


import managerBank.Model.User;
import managerBank.Repo.UserRepo;

public class UserService {
    //Connect DB
    UserRepo repo;
    public UserService(){
        repo = new UserRepo();
    };

    public void signUp(User user){
        repo.saveToDB(user);
    } 


}
    