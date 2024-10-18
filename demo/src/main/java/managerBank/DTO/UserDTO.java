package managerBank.DTO;


public class UserDTO {
    private int userId;
    private String userName;
    private String phone;
    
    private int userBalance;
    
    public UserDTO() {
    }
    public UserDTO(int userId, String userName, int userBalance) {
        this.userId = userId;
        this.userName = userName;
        this.userBalance = userBalance;
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
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
