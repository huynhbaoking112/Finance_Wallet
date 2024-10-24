package managerBank.Model;

public class UserProfile {
    private int id;
    private String name;
    private String phone;
    private String dob;
    private String email;
    private String address;
    private String cccd;
    public UserProfile(int id, String name, String phone, String dob, String email, String address, String cccd) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.cccd = cccd;
    }
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
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCccd() {
        return cccd;
    }
    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

}
