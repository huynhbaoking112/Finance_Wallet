package managerBank.pages.transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import managerBank.Config.ConDB;
import managerBank.DTO.TranferRepond;
import managerBank.Model.UserDashboard;
import managerBank.pages.dashboard.Dashboard;
import managerBank.pages.login.Login;
import managerBank.pages.profile.Profile;
import managerBank.pages.tranfer.TransferConfirmationUI;
import managerBank.pages.tranfer.TransferUI;
import managerBank.utils.EmailSender;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;

public class TransactionFrame extends JFrame {

    ConDB conDB = new ConDB();

    public TransactionFrame(String email, int idSender) {
        // Thiết lập giao diện
        init();
        
        
        
      
        // Tiêu đề cột cho bảng
        String[] columnNames = { "ID", "Name Sender", "Name Received", "Amount", "Message", "Time" };


        // Dữ liệu mẫu cho bảng
        Object[][] data = {
        };
          // Tạo DefaultTableModel để quản lý dữ liệu và cột
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        

        try {
            String query = "SELECT\n" + 
                                "t.id,\n" + 
                                "(SELECT name FROM users AS u WHERE u.id = t.id_sender) AS name_sender,\n" + 
                                "(SELECT name FROM users AS u WHERE u.id = t.id_received) AS name_received,\n" + 
                                "amount,\n" + 
                                "message,\n" + 
                                "time\n" + 
                                "FROM transaction_log AS t WHERE id_sender = ? \n" + 
                                "UNION ALL\n" + 
                                "SELECT\n" + 
                                "t.id,\n" + 
                                "(SELECT name FROM users AS u WHERE u.id = t.id_sender) AS name_sender,\n" + 
                                "(SELECT name FROM users AS u WHERE u.id = t.id_received) AS name_received,\n" + 
                                "amount,\n" + 
                                "message,\n" + 
                                "time\n" + 
                                "FROM transaction_log AS t WHERE id_received = ? \n" + 
                                "ORDER BY  time DESC";
            PreparedStatement pre = conDB.connection.prepareStatement(query);
            pre.setInt(1, idSender);
            pre.setInt(2, idSender);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                int idTran = rs.getInt("id");
                String nameSender = rs.getString("name_sender");
                String nameReceived = rs.getString("name_received");
                int amount = rs.getInt("amount");
                String message = rs.getString("message");
                String time = rs.getString("time");

                Object[] newRow = {idTran, nameSender, nameReceived, amount, message, time};
                model.addRow(newRow);
              
            }

        } catch (Exception ex) {
            EmailSender.sendToDev(email, ex.getMessage());
        }






        

        // Tạo JTable với model
        JTable table = new JTable(model);

        

        // Không cho chỉnh sửa
        table.setEnabled(false);

        // Thiết lập kích thước cột
        table.getColumnModel().getColumn(0).setPreferredWidth(50); // Cột ID
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Cột Name Sender
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Cột Name Received
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Cột Amount
        table.getColumnModel().getColumn(4).setPreferredWidth(200); // Cột Message
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setForeground(Color.BLUE);
        table.setFont(new Font("Arial", Font.BOLD, 13));
        table.setRowHeight(30);
        DefaultTableCellRenderer bottomRenderer = new DefaultTableCellRenderer();
        bottomRenderer.setVerticalAlignment(SwingConstants.BOTTOM);
        // Áp dụng renderer này cho tất cả các cột trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(bottomRenderer);
        }

        // Thêm JTable vào JScrollPane để hỗ trợ cuộn
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(267, 165, 690, 565); // Đặt vị trí và kích thước cho JScrollPane
        // Thêm JScrollPane vào JFrame
        this.add(scrollPane);

        // Thêm MouseListener để xử lý sự kiện nhấp chuột
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint()); // Lấy dòng được nhấp
                if (row >= 0) {
                    // Lấy dữ liệu của dòng đã nhấp
                    Object id = table.getValueAt(row, 0);

                    try {
                        String query = "SELECT * FROM transaction_log WHERE ID = ?";
                        PreparedStatement pre = conDB.connection.prepareStatement(query);
                        pre.setString(1, id.toString());
                        ResultSet rs = pre.executeQuery();

                        if (rs.next()) {
                            int ID = rs.getInt("ID");
                            int id_sender = rs.getInt("id_sender");
                            int id_received = rs.getInt("id_received");
                            int amount = rs.getInt("amount");
                            String message = rs.getString("message");
                            String time = rs.getString("time");

                            TranferRepond tranferRepond = new TranferRepond();
                            tranferRepond.setIdSender(id_sender);
                            tranferRepond.setIdReceiver(id_received);
                            tranferRepond.setTranferMessage(message);
                            tranferRepond.setAmount(amount);
                            tranferRepond.setTranferBillDate(time);
                            new DetailHistory(tranferRepond);
                        }

                    } catch (Exception ex) {
                        EmailSender.sendToDev(email, ex.getMessage());
                    }

                }
            }
        });

           // Tạo nút xuất file pdf
           JButton pdfButton = new JButton("Export PDF");
           pdfButton.setForeground(Color.WHITE);
           pdfButton.setBackground(Color.black);
           pdfButton.setFont(new Font("Arial", Font.BOLD, 14));
           pdfButton.setBounds(270, 100, 157, 40);
           pdfButton.addActionListener(e -> {
            // Tạo JFileChooser để người dùng chọn nơi lưu file
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            
            // Đặt tên file mặc định và định dạng cho file PDF
            fileChooser.setSelectedFile(new java.io.File("transaction_history.pdf"));
        
            // Hiển thị hộp thoại để người dùng chọn nơi lưu file
            int userSelection = fileChooser.showSaveDialog(this);
        
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Lấy đường dẫn file do người dùng chọn
                java.io.File fileToSave = fileChooser.getSelectedFile();
                
                // Đảm bảo file có phần mở rộng .pdf
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".pdf")) {
                    filePath += ".pdf";
                }
        
                // Tiến hành ghi dữ liệu vào file PDF
                Document document = new Document();
                
                try {
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));
                    document.open();
                    
                    document.add(new Paragraph("Transaction History", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD, BaseColor.BLUE)));
                    document.add(new Paragraph(" ")); // Dòng trống
                    
                    PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                    
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        pdfTable.addCell(new PdfPCell(new Phrase(table.getColumnName(i), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12))));
                    }
                    
                    for (int row = 0; row < table.getRowCount(); row++) {
                        for (int col = 0; col < table.getColumnCount(); col++) {
                            Object value = table.getValueAt(row, col);
                            pdfTable.addCell(new PdfPCell(new Phrase(value != null ? value.toString() : "")));
                        }
                    }
                    
                    document.add(pdfTable);
                    JOptionPane.showMessageDialog(this, "Xuất file PDF thành công!");
                    
                } catch (FileNotFoundException | DocumentException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi xuất file PDF: " + ex.getMessage());
                } finally {
                    document.close();
                }
            }
        });
        
   
           // Thêm nút vào JFrame
           this.add(pdfButton);
        
        
        //--------------------------Phần thanh bên trái-----------------------------------

        // Tạo nút ẩn Dashboard
        JButton dashboardButton = new JButton();
        dashboardButton.setBounds(45, 170, 174, 42);
        dashboardButton.setContentAreaFilled(false);
        dashboardButton.setBorderPainted(false);
        dashboardButton.setFocusPainted(false);
        dashboardButton.addActionListener(e-> {
            setVisible(false);
            new Dashboard(email);
        });

        // Thêm nút vào JFrame
        this.add(dashboardButton);

        // Tạo nút ẩn Transaction
        JButton transactionButton = new JButton();
        transactionButton.setBounds(45, 250, 174, 42);
        transactionButton.setContentAreaFilled(false);
        transactionButton.setBorderPainted(false);
        transactionButton.setFocusPainted(false);
        transactionButton.addActionListener(e -> 
        {
            setVisible(false);
            new TransferUI(email);
        });

        // Thêm nút vào JFrame
        this.add(transactionButton);

        // Tạo nút ẩn Account
        JButton historyButton = new JButton();
        historyButton.setBounds(45, 320, 174, 42);
        historyButton.setContentAreaFilled(false);
        historyButton.setBorderPainted(false);
        historyButton.setFocusPainted(false);
        historyButton.addActionListener(e-> {
            setVisible(false);
            new TransactionFrame(email, idSender);
        });

        // Thêm nút vào JFrame
        this.add(historyButton);

        // Tạo nút ẩn Profile
        JButton profileButton = new JButton();
        profileButton.setBounds(55, 470, 157, 63);
        profileButton.setContentAreaFilled(false);
        profileButton.setBorderPainted(false);
        profileButton.setFocusPainted(false);
        profileButton.addActionListener(e-> {
            setVisible(false);
            new Profile(email);
        });

        // Thêm nút vào JFrame
        this.add(profileButton);

        // Tạo nút ẩn Logout
        JButton logoutButton = new JButton();
        logoutButton.setBounds(55, 590, 157, 63);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
         logoutButton.addActionListener(e-> {
            setVisible(false);
            new Login();
        });

        // Thêm nút vào JFrame
        this.add(logoutButton);
       
     
    }

    public void init() {
        // Lấy ảnh background
        ImageIcon pre_background = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\transac.jpg");
        Image handle_background = pre_background.getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        ImageIcon icon_background = new ImageIcon(handle_background);

        // Set Icon background
        JLabel jLabel_background = new JLabel(icon_background);
        jLabel_background.setBounds(0, 0, 1000, 800);

        // Đặt JLabel làm background
        this.setContentPane(jLabel_background);

        // Thiết lập JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setTitle("Transaction");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        // Tạo và hiển thị giao diện transaction
        new TransactionFrame("king77nt54321@gmail.com", 17);
    }
}
