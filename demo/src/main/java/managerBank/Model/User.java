package managerBank.Model;

import java.util.Date;

public class User {
    private String name;
    private String phone;
    private Date dob;
    private String gender;
    private String email;
    private String marital; 
    private String address;
    private String password; 
    private String comfirmPassword;
    public User(String name, String phone, Date dob, String gender, String email, String marital,String address, String password,
    String comfirmPassword) {
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.marital = marital;
        this.address= address;
        this.password = password;
        this.comfirmPassword = comfirmPassword;
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
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMarital() {
        return marital;
    }
    public void setMarital(String marital) {
        this.marital = marital;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String address) {
        this.password = address;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getComfirmPassword() {
        return comfirmPassword;
    }
    public void setComfirmPassword(String comfirmPassword) {
        this.comfirmPassword = comfirmPassword;
    }


}
