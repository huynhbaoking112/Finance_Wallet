package managerBank;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import utils.EmailSender;

public class Signup extends JFrame implements ActionListener {
    
    Random ran = new Random();
    long first4 = (ran.nextLong()%9000L) + 1000L;
    String first = " " + Math.abs(first4);


    //Field name
    JTextField textName;
    
    //Field fname
    JTextField textfName;

    //DOB
    JDateChooser dateChooser;

    //Check gender
    JRadioButton jRadioButton_male;
    JRadioButton jRadioButton_female;

    //Field email
    JTextField textEmail;
    
    //Check Hôn nhân
    // JTextField textMs;
    JRadioButton m1;
    JRadioButton m2;
    JRadioButton m3;
    
    //Field address
    JTextField textAddress;
    
    //Field city
    JTextField textCity;
    

    //Field PIN
    JTextField textPin;
    
    //Field State
    JTextField textState;

    //Next Button
    JButton nextButton;

    public Signup(){
        super("APPLICATION FORM");

        //Tiêu đề
        JLabel label_welcome = new JLabel("APPLICATION FORM NO."+first);
        label_welcome.setFont(new Font("Raleway", Font.BOLD, 38));
        label_welcome.setBounds(160, 20 , 600, 40);
        this.add(label_welcome);
       
        //Tiêu đề page 1
        JLabel label_page1 = new JLabel("Page 1");
        label_page1.setFont(new Font("Raleway", Font.BOLD, 22));
        label_page1.setBounds(330, 70 , 600, 30);
        this.add(label_page1);
        
        //Tiêu đề person details
        JLabel label_person = new JLabel("Personal Details");
        label_person.setFont(new Font("Raleway", Font.BOLD, 22));
        label_person.setBounds(290, 90 , 600, 30);
        this.add(label_person);
        
        //Tiêu đề name
        JLabel label_name = new JLabel("Name :");
        label_name.setFont(new Font("Raleway", Font.BOLD, 22));
        label_name.setBounds(100, 190 , 100, 30);
        this.add(label_name);

        //field name
        textName = new JTextField();
        textName.setFont(new Font("Raleway", Font.BOLD, 14));
        textName.setBounds(300,190,400,30);
        add(textName);
        
        //Tiêu đề fname
        JLabel label_fname = new JLabel("Father's Name :");
        label_fname.setFont(new Font("Raleway", Font.BOLD, 22));
        label_fname.setBounds(100, 240 , 200, 30);
        this.add(label_fname);

        //field name
        textfName = new JTextField();
        textfName.setFont(new Font("Raleway", Font.BOLD, 14));
        textfName.setBounds(300,240,400,30);
        add(textfName);


        //Tiêu đề birth
        JLabel dob = new JLabel("Day of Birth :");
        dob.setFont(new Font("Raleway", Font.BOLD, 22));
        dob.setBounds(100, 340 , 200, 30);
        this.add(dob);

        //Mở lịch
        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105,105,105));
        dateChooser.setBounds(300,340,400,30);
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

        //Email
        JLabel jLabel_email = new JLabel("Email address :");
        jLabel_email.setFont(new Font("Raleway", Font.BOLD, 22));
        jLabel_email.setBounds(100, 440, 200, 30);
        this.add(jLabel_email);


        //Field Email
        textEmail = new JTextField();
        textEmail.setFont(new Font("Raleway", Font.BOLD, 14));
        textEmail.setBounds(300, 440, 400, 30);
        this.add(textEmail);

        // Tình trạng hôn nhân 
        JLabel jLabel_ms = new JLabel("Marital Status :");
        jLabel_ms.setFont(new Font("Raleway", Font.BOLD, 22));
        jLabel_ms.setBounds(100, 490, 200, 30);
        this.add(jLabel_ms);


        //Field hôn nhân
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




        // Tiêu đề City 
         JLabel jLabel_add = new JLabel("City :");
         jLabel_add.setFont(new Font("Raleway", Font.BOLD, 22));
         jLabel_add.setBounds(100, 540, 200, 30);
         this.add(jLabel_add);
 
 
         //Field city
         textCity = new JTextField();
         textCity.setFont(new Font("Raleway", Font.BOLD, 14));
         textCity.setBounds(300, 540, 400, 30);
         this.add(textCity);
         
         
         // Tiêu đề Pin 
         JLabel jLabel_Pin = new JLabel("Pin Code :");
         jLabel_Pin.setFont(new Font("Raleway", Font.BOLD, 22));
         jLabel_Pin.setBounds(100, 590, 200, 30);
         this.add(jLabel_Pin);
 
 
         //Field Pin
         textPin = new JTextField();
         textPin.setFont(new Font("Raleway", Font.BOLD, 14));
         textPin.setBounds(300, 590, 400, 30);
         this.add(textPin);
         
         // Tiêu đề State 
         JLabel jLabel_State = new JLabel("State :");
         jLabel_State.setFont(new Font("Raleway", Font.BOLD, 22));
         jLabel_State.setBounds(100, 640, 200, 30);
         this.add(jLabel_State);
 
 
         //Field State
         textState = new JTextField();
         textState.setFont(new Font("Raleway", Font.BOLD, 14));
         textState.setBounds(300, 640, 400, 30);
         this.add(textState);

        //Next Button
         nextButton = new JButton("Next");
         nextButton.setFont(new Font("Raleway", Font.BOLD, 14));
         nextButton.setBackground(Color.BLACK);
         nextButton.setForeground(Color.WHITE);
         nextButton.setBounds(620, 690, 80, 30);
         nextButton.addActionListener(this);
         this.add(nextButton);



        // Lay Anh Icon Bank
        ImageIcon i1 = new ImageIcon("C:\\Users\\huynh\\OneDrive\\Desktop\\swing\\icon\\bank.png");
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        // Set Icon Bank
        JLabel image = new JLabel(i3);
        image.setBounds(25, 10, 100, 100);
        this.add(image);


         //Set màu nền 
        getContentPane().setBackground(new Color(127, 179, 213 ));

        this.setLayout(null);
        this.setSize(850, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
            if(e.getSource() == nextButton){
                System.out.println("king");
            }

        } catch (Exception E) {
           EmailSender.sendToDev("", E.getMessage());
        }
    } 

    public static void main(String[] args) {
        new Signup();
    }

}
