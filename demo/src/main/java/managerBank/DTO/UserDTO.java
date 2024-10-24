package managerBank.DTO;


public class UserDTO {
    private int userId;
    private String userName;
    private String userPhone;
    private int userBalance;
    private String userEmail;
    public UserDTO(int userId, String userName, String userPhone, int userBalance, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userBalance = userBalance;
        this.userEmail = userEmail;
    }

    public UserDTO() {
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public int getUserBalance() {
        return userBalance;
    }
    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String string) {
        this.userName = string;
    };
    public String getPhone() {
        return userPhone;
    }
    public void setPhone(String phone) {
        this.userPhone = phone;
    }
    
}
