package managerBank.pages.signup;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import managerBank.Config.ConDB;
import managerBank.utils.CheckExists;
import managerBank.utils.EmailSender;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Signup extends JFrame implements ActionListener {


    // Field name
    JTextField textName;

    // Field fname
    JTextField textPhone;

    // DOB
    JDateChooser dateChooser;

    // Check gender
    JRadioButton jRadioButton_male;
    JRadioButton jRadioButton_female;

    // Field email
    JTextField textEmail;

    // Check Hôn nhân
    // JTextField textMs;
    JRadioButton m1;
    JRadioButton m2;
    JRadioButton m3;

    // Field address
    JTextField textAddress;

    // Field PIN
    JTextField textPin;

    // Next Button
    JButton nextButton;

    public Signup() {
        super("APPLICATION FORM");

        // Tiêu đề
        JLabel label_welcome = new JLabel("APPLICATION FORM ");
        label_welcome.setFont(new Font("Raleway", Font.BOLD, 38));
        label_welcome.setBounds(160, 20, 600, 40);
        this.add(label_welcome);

        // Tiêu đề page 1
        JLabel label_page1 = new JLabel("Page 1");
        label_page1.setFont(new Font("Raleway", Font.BOLD, 22));
        label_page1.setBounds(330, 70, 600, 30);
        this.add(label_page1);

        // Tiêu đề person details
        JLabel label_person = new JLabel("Personal Details");
        label_person.setFont(new Font("Raleway", Font.BOLD, 22));
        label_person.setBounds(290, 90, 600, 30);
        this.add(label_person);

        // Tiêu đề name
        JLabel label_name = new JLabel("Name :");
        label_name.setFont(new Font("Raleway", Font.BOLD, 22));
        label_name.setBounds(100, 190, 100, 30);
        this.add(label_name);

        // field name
        textName = new JTextField();
        textName.setFont(new Font("Raleway", Font.BOLD, 14));
        textName.setBounds(300, 190, 400, 30);
        add(textName);

        // Tiêu đề phone
        JLabel label_phone = new JLabel("Phone :");
        label_phone.setFont(new Font("Raleway", Font.BOLD, 22));
        label_phone.setBounds(100, 240, 200, 30);
        this.add(label_phone);

        // field phone
        textPhone = new JTextField();
        textPhone.setFont(new Font("Raleway", Font.BOLD, 14));
        textPhone.setBounds(300, 240, 400, 30);
        add(textPhone);

        // Tiêu đề address
        JLabel label_address = new JLabel("Address :");
        label_address.setFont(new Font("Raleway", Font.BOLD, 22));
        label_address.setBounds(100, 290, 200, 30);
        this.add(label_address);

        // field address
        textAddress = new JTextField();
        textAddress.setFont(new Font("Raleway", Font.BOLD, 14));
        textAddress.setBounds(300, 290, 400, 30);
        add(textAddress);

        // Tiêu đề birth
        JLabel dob = new JLabel("Day of Birth :");
        dob.setFont(new Font("Raleway", Font.BOLD, 22));
        dob.setBounds(100, 340, 200, 30);
        this.add(dob);

        // Mở lịch
        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105, 105, 105));
        dateChooser.setBounds(300, 340, 400, 30);
        dateChooser.setFont(new Font("Raleway", Font.BOLD, 18));
        this.add(dateChooser);

        // Tiêu đề giới tính
        JLabel jLabel_gender = new JLabel("Gender :");
        jLabel_gender.setFont(new Font("Raleway", Font.BOLD, 22));
        jLabel_gender.setBounds(100, 390, 200, 30);
        this.add(jLabel_gender);

        // Chọn nam hoặc nữ
        jRadioButton_male = new JRadioButton("Male");
        jRadioButton_male.setFont(new Font("Raleway", Font.BOLD, 14));
        jRadioButton_male.setBackground(new Color(127, 179, 213));
        jRadioButton_male.setBounds(300, 390, 60, 30);
        this.add(jRadioButton_male);

        jRadioButton_female = new JRadioButton("Female");
        jRadioButton_female.setFont(new Font("Raleway", Font.BOLD, 14));
        jRadioButton_female.setBackground(new Color(127, 179, 213));
        jRadioButton_female.setBounds(380, 390, 100, 30);
        this.add(jRadioButton_female);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton_female);
        buttonGroup.add(jRadioButton_male);

        // Email
        JLabel jLabel_email = new JLabel("Email address :");
        jLabel_email.setFont(new Font("Raleway", Font.BOLD, 22));
        jLabel_email.setBounds(100, 440, 200, 30);
        this.add(jLabel_email);

        // Field Email
        textEmail = new JTextField();
        textEmail.setFont(new Font("Raleway", Font.BOLD, 14));
        textEmail.setBounds(300, 440, 400, 30);
        this.add(textEmail);

        // Tình trạng hôn nhân
        JLabel jLabel_ms = new JLabel("Marital Status :");
        jLabel_ms.setFont(new Font("Raleway", Font.BOLD, 22));
        jLabel_ms.setBounds(100, 490, 200, 30);
        this.add(jLabel_ms);

        // Field hôn nhân
        m1 = new JRadioButton("Married");
        m1.setBounds(300, 490, 100, 30);
        m1.setBackground(new Color(127, 179, 213));
        m1.setFont(new Font("Raleway", Font.BOLD, 14));
        this.add(m1);

        m2 = new JRadioButton("Unmarried");
        m2.setBounds(450, 490, 100, 30);
        m2.setBackground(new Color(127, 179, 213));
        m2.setFont(new Font("Raleway", Font.BOLD, 14));
        this.add(m2);

        m3 = new JRadioButton("Other");
        m3.setBounds(600, 490, 100, 30);
        m3.setBackground(new Color(127, 179, 213));
        m3.setFont(new Font("Raleway", Font.BOLD, 14));
        this.add(m3);

        ButtonGroup bg = new ButtonGroup();
        bg.add(m1);
        bg.add(m2);
        bg.add(m3);



        // Tiêu đề Pin
        JLabel jLabel_Pin = new JLabel("Pin Code :");
        jLabel_Pin.setFont(new Font("Raleway", Font.BOLD, 22));
        jLabel_Pin.setBounds(100, 540, 200, 30);
        this.add(jLabel_Pin);

        // Field Pin
        textPin = new JTextField();
        textPin.setFont(new Font("Raleway", Font.BOLD, 14));
        textPin.setBounds(300, 540, 400, 30);
        this.add(textPin);

        // Next Button
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Raleway", Font.BOLD, 14));
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setBounds(620, 590, 80, 30);
        nextButton.addActionListener(this);
        this.add(nextButton);

       init();

    }

    private void init(){
         // Lay Anh Icon Bank
         ImageIcon i1 = new ImageIcon("C:\\Users\\huynh\\OneDrive\\Desktop\\swing\\icon\\bank.png");
         Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
         ImageIcon i3 = new ImageIcon(i2);
         // Set Icon Bank
         JLabel image = new JLabel(i3);
         image.setBounds(25, 10, 100, 100);
         this.add(image);
         // Set màu nền
         getContentPane().setBackground(new Color(127, 179, 213));
         this.setLayout(null);
         this.setSize(850, 800);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setLocationRelativeTo(null);
         this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = textName.getText();
        String phone = textPhone.getText();
        Date dob = dateChooser.getDate();
        String gender = jRadioButton_male.isSelected() ? "male" : "female";
        String email = textEmail.getText();
        String marital = m1.isSelected() ? "Married" : m2.isSelected() ? "Unmarried" : "Other";
        String address = textAddress.getText();
        String pin = textPin.getText();

        try {

            //Connect DB
            ConDB con1 = new ConDB();

            //Check các trường đã được điền chưa
            if (name.equals("") || phone.equals("") || gender.equals("") || email.equals("") || marital.equals("")
                    || address.equals("") || pin.equals("")|| dob == null) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            }
            
            //Check email hay phone đã tồn tại chưa
            else if(CheckExists.checkExistsEmailPhone(email, phone, con1)){
                JOptionPane.showMessageDialog(null, "Email or Phone is exists!");
            }
            // Tạo user mới và wallet của nó
            else {
                // Chống tấn công sql injection nếu nâng cấp lên bảo mật cấp procedure thì tốt hơn  (đã nâng cấp lên procedure)
                // String query = "INSERT INTO users (name, father_name, dob, gender, email, marital_status, address, city, pincode, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                // PreparedStatement pstmt = con1.connection.prepareStatement(query);
                /*Một số ưu điểm của procedure hơn viết query thông thường
                    - Được lưu trữ và biên dịch sẵn trên csdl hiệu suất cao hơn với viết query thông thường 
                    - Giảm băng thông
                    - Ẩn dấu query khi bị tấn công server
                    - Dễ dàng thay đổi nghiệp vụ
                */ 
                String query = "CALL signInUser(?,?,?,?,?,?,?,?);";
                PreparedStatement pstmt = con1.connection.prepareStatement(query);


                
                pstmt.setString(1, name);
                pstmt.setString(2, phone);
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dobStr = sdf.format(dob);
                pstmt.setString(3, dobStr);
                pstmt.setString(4, gender);
                pstmt.setString(5, email);
                pstmt.setString(6, marital);
                pstmt.setString(7, address);
                pstmt.setString(8, pin);


                // Thực thi câu lệnh
                pstmt.executeUpdate();

                // Giải phóng
                pstmt.close();
                con1.connection.close();

                //Chuyển sang page 2
                new Signup_page_two();
                this.setVisible(false);
            }
        } catch (SQLException E) {
            EmailSender.sendToDev("", E.getMessage());
            JOptionPane.showMessageDialog(null, "Error occurred while inserting data: " + E.getMessage());
        }
    }

    public static void main(String[] args) {
        new Signup();
    }

}


