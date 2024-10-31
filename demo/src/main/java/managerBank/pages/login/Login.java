package managerBank.pages.login;

import javax.swing.*;

import managerBank.Config.ConDB;
import managerBank.pages.dashboard.Dashboard;
import managerBank.pages.signup.Confirm_account;
import managerBank.pages.signup.Signup;
import managerBank.utils.CheckExists;
import managerBank.utils.EmailSender;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

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

    public Login() {
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
        jLabel_welcome = new JLabel("WELCOME TO KDL WALLET");
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
        jButton_signin = new JButton("SIGN IN");
        jButton_signin.setFont(new Font("Arial", Font.BOLD, 14));
        jButton_signin.setForeground(Color.WHITE);
        jButton_signin.setBackground(Color.black);
        jButton_signin.setBounds(300, 300, 100, 30);
        jButton_signin.addActionListener(this);
        this.add(jButton_signin);

        // button CLEAR
        jButton_clear = new JButton("CLEAR");
        jButton_clear.setFont(new Font("Arial", Font.BOLD, 14));
        jButton_clear.setForeground(Color.WHITE);
        jButton_clear.setBackground(Color.black);
        jButton_clear.setBounds(430, 300, 100, 30);
        jButton_clear.addActionListener(this);
        this.add(jButton_clear);

        // button Register
        jButton_register = new JButton("REGISTER");
        jButton_register.setFont(new Font("Arial", Font.BOLD, 14));
        jButton_register.setForeground(Color.WHITE);
        jButton_register.setBackground(Color.black);
        jButton_register.setBounds(300, 350, 230, 30);
        jButton_register.addActionListener(this);
        this.add(jButton_register);
       
        // button Active
        JButton jButton_verify = new JButton("Verify Account");
        jButton_verify.setFont(new Font("Arial", Font.BOLD, 14));
        jButton_verify.setForeground(Color.WHITE);
        jButton_verify.setBackground(Color.black);
        jButton_verify.setBounds(300, 400, 230, 30);
        jButton_verify.addActionListener(e->{
            this.setVisible(false);
            new Confirm_account();
        });
        this.add(jButton_verify);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        try {            
            if (e.getSource() == jButton_signin) {

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
                else if(!CheckExists.checkActive(email, con)){
                    JOptionPane.showMessageDialog(null, "Account locked or unverified");
                }
                //Sang trang
                else{
                    new Dashboard(email);
                    this.setVisible(false);
                }

               

            } else if (e.getSource() == jButton_clear) {
                cardField.setText("");
                passwordField.setText("");
            } else if (e.getSource() == jButton_register) {
                new Signup();
                this.setVisible(false);
            }

        } catch (Exception E) {
           EmailSender.sendToDev("", E.getMessage());
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
