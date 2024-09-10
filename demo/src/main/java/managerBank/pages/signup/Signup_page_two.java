package managerBank.pages.signup;


import javax.swing.*;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

import managerBank.Config.ConDB;
import managerBank.pages.login.Login;
import managerBank.utils.CheckExists;
import managerBank.utils.EmailSender;
import managerBank.utils.TextExtractor;

public class Signup_page_two  extends JFrame implements ActionListener{
    
    //Emessage (Nếu bật thì thay đổi số dư, gửi email cho người dùng)
    JRadioButton on;
    JRadioButton off;

    //Hạn mức giao dịch(Nếu vượt thì gửi email xác nhận giao dịch cho người dùng)
    JTextField textLimit;

    //Button Đường dẫn file chứng minh
    JButton jButtonFile;

    //Tiêu đề mã của căn cước
    JLabel jLabel_code;
    String code = "";

    //Nút hoàn thành
    JButton jButton_completed;

    //Email
    String email;

    public Signup_page_two(String email){
        this.email = email;
        //Tiêu đề page 2
        JLabel jLabel_context = new JLabel("SIGNUP PAGE 2");
        jLabel_context.setFont(new Font("Raleway", Font.BOLD, 38));
        jLabel_context.setForeground(Color.WHITE);
        jLabel_context.setBounds(260, 40, 600, 40);
        this.add(jLabel_context);

       
        // Tiêu đề Emessage
        JLabel jLabel_emessage = new JLabel("E_message: ");
        jLabel_emessage.setFont(new Font("Raleway",Font.BOLD,20));
        jLabel_emessage.setForeground(Color.WHITE);
        jLabel_emessage.setBounds(25, 120, 150, 30);
        this.add(jLabel_emessage);


        //Check emessage
        on = new JRadioButton("ON");
        on.setFont(new Font("Raleway", Font.BOLD, 18));
        on.setOpaque(false);
        on.setForeground(Color.WHITE);
        on.setBounds(200, 120, 60, 30);
        this.add(on); 

        off = new JRadioButton("OFF");
        off.setFont(new Font("Raleway", Font.BOLD, 18));
        off.setOpaque(false);
        off.setForeground(Color.WHITE);
        off.setBounds(280, 120, 80, 30);
        this.add(off);

        ButtonGroup gr = new ButtonGroup();
        gr.add(on);
        gr.add(off);


        // Tiêu đề Hạn mức giao dịch
        JLabel jLabel_limit = new JLabel("Transaction limit: ");
        jLabel_limit.setFont(new Font("Raleway",Font.BOLD,20));
        jLabel_limit.setForeground(Color.WHITE);
        jLabel_limit.setBounds(25, 170, 200, 30);
        this.add(jLabel_limit);

        //Field Limit
        textLimit = new JTextField();
        textLimit.setFont(new Font("Raleway", Font.BOLD, 18));
        textLimit.setBounds(200, 170, 400, 30);
        textLimit.setForeground(Color.WHITE);
        textLimit.setOpaque(false);
        ((AbstractDocument) textLimit.getDocument()).setDocumentFilter(new NumericFilter());
        this.add(textLimit);

        // Tiêu đề chọn image căn cước
        JLabel jLabel_cc = new JLabel("Chọn ảnh căn cước công dân mặt trước: ");
        jLabel_cc.setFont(new Font("Raleway",Font.BOLD,20));
        jLabel_cc.setForeground(Color.WHITE);
        jLabel_cc.setBounds(25, 220, 500, 30);
        this.add(jLabel_cc);
        
        //JButton choose file
        jButtonFile = new JButton("Add file");
        jButtonFile.setFont(new Font("Raleway",Font.BOLD,20));
        jButtonFile.setForeground(Color.WHITE);
        jButtonFile.setBackground(Color.BLACK);
        jButtonFile.setBounds(425, 220, 150, 30);
        jButtonFile.addActionListener(this);
        this.add(jButtonFile);
        
        // Tiêu đề mã căn cước
        jLabel_code = new JLabel("Mã căn cước: ");
        jLabel_code.setFont(new Font("Raleway",Font.BOLD,20));
        jLabel_code.setForeground(Color.WHITE);
        jLabel_code.setBounds(25, 270, 500, 30);
        this.add(jLabel_code);


        //Hoàn thành việc đăng ký
        jButton_completed = new JButton("Completed");
        jButton_completed.setFont(new Font("Raleway",Font.BOLD,20));
        jButton_completed.setForeground(Color.WHITE);
        jButton_completed.setBackground(Color.BLACK);
        jButton_completed.setBounds(25, 320, 150, 30);
        jButton_completed.addActionListener(this);
        this.add(jButton_completed);


      
        init();
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jButtonFile){
            //Bật class chọn file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(null);

            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = fileChooser.getSelectedFile();
                //Gọi hàm trích xuất văn bản
                String extractedText = TextExtractor.extractTextFromImage(selectedFile);
                //Goị hàm trích xuất số từ văn bản
                List<String> numbers = TextExtractor.extractNumbers(extractedText);
                if(numbers.size()>0){
                    code = numbers.get(0);
                    jLabel_code.setText("Mã căn cước: "+numbers.get(0));
                }else{
                    JOptionPane.showMessageDialog(null, "Ảnh không đúng định dạng");
                }
            }
        }else{
            
            //Connect DB
            ConDB con = new ConDB();

            boolean e_message = on.isSelected()?true:false;
            String trans_limit = textLimit.getText();
            String cccd = code;

              //check xem các trường đã được điền hết chưa 
              if(code.equals("") || trans_limit.equals("")){
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            }
            //Check xem căn cước công dân có tồn  tại chưa nếu có thì tiếp tục nếu chưa thì thông báo 
            else if(CheckExists.checkExistCCCD(cccd, con)){
                JOptionPane.showMessageDialog(null, "CCCD has been used");
            }
            //Sau khi thêm vào cơ sở dữ liệu sẽ được chuyển về trang đăng nhập
            else{
               try {
                String query = "CALL confirmUser(?,?,?,?);";
                PreparedStatement pstmt = con.connection.prepareStatement(query);
                pstmt.setString(1, email);
                pstmt.setBoolean(2, e_message);
                pstmt.setString(3, cccd);
                pstmt.setString(4, trans_limit);
                // Thực thi câu lệnh
                pstmt.executeUpdate();
                // Giải phóng
                pstmt.close();
                con.connection.close();

                //Thông báo đăng ký hoàn tất 
                JOptionPane.showMessageDialog(null, "SignUp Success");
                //Chuyển sang page đăng nhập 
                new Login();
                this.setVisible(false);
               } catch (Exception E) {
                EmailSender.sendToDev(email, "Lỗi khi thêm infor_user vào DB");
               }
            }
        }
    }

    

    private void init(){
         // Lay Anh Icon Bank
         ImageIcon i1 = new ImageIcon("C:\\Users\\huynh\\OneDrive\\Desktop\\basecode\\demo\\src\\main\\java\\managerBank\\assets\\icon\\bank.png");
         Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
         ImageIcon i3 = new ImageIcon(i2);


         //Lay Anh background
         ImageIcon ib1 = new ImageIcon("C:\\Users\\huynh\\OneDrive\\Desktop\\basecode\\demo\\src\\main\\java\\managerBank\\assets\\icon\\bg_wallet5.jpg");
         Image ib2 = ib1.getImage().getScaledInstance(850, 474, Image.SCALE_SMOOTH);
         ImageIcon ib3 = new ImageIcon(ib2);
         
         // Set Icon Bank
         JLabel image = new JLabel(i3);
         image.setBounds(25, 10, 100, 100);
         this.add(image);

         //Set image backround
          JLabel bg = new JLabel(ib3);
          bg.setBounds(0, 0, 850, 474);
          this.add(bg);



         // Set màu nền
        //  getContentPane().setBackground(new Color(127, 179, 213));
         this.setLayout(null);
         this.setSize(850, 474);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setLocationRelativeTo(null);
         this.setTitle("SignUp page 2");
         this.setVisible(true);
    }

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
    }
}


// Lớp NumericFilter để chỉ cho phép nhập số
class NumericFilter extends DocumentFilter {

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isNumeric(string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isNumeric(text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isNumeric(String text) {
        return text.matches("\\d+"); // Chỉ cho phép ký tự số
    }
}


