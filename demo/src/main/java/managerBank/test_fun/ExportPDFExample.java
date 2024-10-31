// package managerBank.test_fun;

// import com.itextpdf.text.Document;
// import com.itextpdf.text.DocumentException;
// import com.itextpdf.text.Element;
// import com.itextpdf.text.Paragraph;
// import com.itextpdf.text.pdf.PdfWriter;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;

// public class ExportPDFExample extends JFrame {


//     public static void exportToPDF() {
//         Document document = new Document();
        
//         try {
//             // Tạo luồng ghi PDF
//             PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
            
//             // Mở tài liệu PDF
//             document.open();
            
//             // Lấy nội dung từ JTextArea và thêm vào tài liệu PDF
//             document.add(new Paragraph("Nội dung từ JTextArea:"));
//             document.add(new Paragraph(textArea.getText()));
            
//             JOptionPane.showMessageDialog(this, "Xuất PDF thành công!");
            
//         } catch (FileNotFoundException | DocumentException e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(this, "Xuất PDF thất bại!");
//         } finally {
//             // Đóng tài liệu
//             document.close();
//         }
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             ExportPDFExample frame = new ExportPDFExample();
//             frame.setVisible(true);
//         });
//     }
// }
