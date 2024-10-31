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
import managerBank.Model.User;
import managerBank.Service.UserService;
import managerBank.pages.dashboard.Dashboard;
import managerBank.pages.login.Login;
import managerBank.utils.CheckExists;
public class Confirm_account  extends JFrame {
       // Text welcome
       JLabel jLabel_welcome;

       // Text Card No:
       JLabel jLabel_cardNo;
   
       // Text PIN
       JLabel jLabel_pin;
   
       // Field Card
       JTextField cardField;
   
       // Field Password
       JPasswordField passwordField;
   
       // Button Register
       JButton jButton_register;
   
       // Button signin
       JButton jButton_signin;
   
       // Button clear
       JButton jButton_clear;
    
    public Confirm_account(){
        super("Bank Management System");

        // Lay Anh Icon Bank
        ImageIcon i1 = new ImageIcon("src\\main\\java\\managerBank\\assets\\icon\\bank.png");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        // Set Icon Bank
        JLabel image = new JLabel(i3);
        image.setBounds(375, 10, 100, 100);
        this.add(image);

        // Lay anh Icon card
        ImageIcon pre_card = new ImageIcon("src\\main\\java\\managerBank\\assets\\icon\\card.png");
        Image handle_card = pre_card.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon card = new ImageIcon(handle_card);
        // Set Icon card
        JLabel jLabel_card = new JLabel(card);
        jLabel_card.setBounds(700, 350, 100, 100);
        this.add(jLabel_card);

        // Welcome text
        jLabel_welcome = new JLabel("Confirm Account");
        jLabel_welcome.setForeground(Color.WHITE);
        jLabel_welcome.setFont(new Font("AvantGarde", Font.BOLD, 38));
        jLabel_welcome.setBounds(230, 125, 600, 40);
        this.add(jLabel_welcome);

        // Card Text
        jLabel_cardNo = new JLabel("Email:");
        jLabel_cardNo.setForeground(Color.WHITE);
        jLabel_cardNo.setFont(new Font("Ralway", Font.BOLD, 28));
        jLabel_cardNo.setBounds(150, 190, 375, 30);
        this.add(jLabel_cardNo);

        // card field
        cardField = new JTextField(15);
        cardField.setBounds(325, 190, 230, 30);
        cardField.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(cardField);

        // Pin text
        jLabel_pin = new JLabel("Password:");
        jLabel_pin.setForeground(Color.WHITE);
        jLabel_pin.setFont(new Font("Ralway", Font.BOLD, 28));
        jLabel_pin.setBounds(150, 250, 375, 30);
        this.add(jLabel_pin);

        // pin field
        passwordField = new JPasswordField(15);
        passwordField.setBounds(325, 250, 230, 30);
        passwordField.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(passwordField);

        // button SIGN IN
        jButton_signin = new JButton("CONFIRM");
        jButton_signin.setFont(new Font("Arial", Font.BOLD, 14));
        jButton_signin.setForeground(Color.WHITE);
        jButton_signin.setBackground(Color.black);
        jButton_signin.setBounds(300, 300, 200, 30);
        jButton_signin.addActionListener(e->{
             String email = cardField.getText();
                @SuppressWarnings("deprecation")
                String password = passwordField.getText();
                ConDB con = new ConDB();

                //Check các trường đã được điền chưa
                if(email.equals("")||password.equals("")){
                    JOptionPane.showMessageDialog(null, "Fill all the fields");
                }
                //check email va password co dung khong va tai khoan co duoc xac nhan chua
                else if(!CheckExists.checkExistsEmail(email, con)){
                    JOptionPane.showMessageDialog(null, "Email not exists");
                }
                else if(!CheckExists.checkPasswordCorrect(email, password, con)){
                    JOptionPane.showMessageDialog(null, "Password is not correct");
                }
                else if (CheckExists.checkActive(email, con)){
                    JOptionPane.showMessageDialog(null, "Account is active");    
                }
                //Sang trang
                else{
                    new Signup_page_two(email);
                    this.setVisible(false);
                }
        });
        this.add(jButton_signin);

        // button CLEAR
        jButton_clear = new JButton("CLEAR");
        jButton_clear.setFont(new Font("Arial", Font.BOLD, 14));
        jButton_clear.setForeground(Color.WHITE);
        jButton_clear.setBackground(Color.black);
        jButton_clear.setBounds(550, 300, 100, 30);
        jButton_clear.addActionListener(e->{
            cardField.setText("");
            passwordField.setText("");
        });
        this.add(jButton_clear);
        // button back
        JButton jButton_back = new JButton("Back");
        jButton_back.setFont(new Font("Arial", Font.BOLD, 14));
        jButton_back.setForeground(Color.WHITE);
        jButton_back.setBackground(Color.black);
        jButton_back.setBounds(550, 350, 100, 30);
        jButton_back.addActionListener(e->{
            this.setVisible(false);
            new Login();
        });
        this.add(jButton_back);

       

        // Lay anh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\backbg.png");
        Image handle_background = pre_background.getImage().getScaledInstance(850, 480, Image.SCALE_DEFAULT);
        ImageIcon icon_background = new ImageIcon(handle_background);
        // Set Icon background
        JLabel jLabel_background = new JLabel(icon_background);
        jLabel_background.setBounds(0, 0, 850, 480);
        this.add(jLabel_background);

        // Vô hiệu hoá Layout mặc định
        setLayout(null);
        this.setSize(850, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

   
    public static void main(String[] args) {
      new  Confirm_account();
    }

}
