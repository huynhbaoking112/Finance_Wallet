package managerBank.Model;

public class UserDashboard {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private int balance;
    
    public UserDashboard(int id, String name, String phone, String email, int balance) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.balance = balance;
    }

}
