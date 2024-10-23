package managerBank.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.encoder.QRCode;
// import com.google.zxing.qrcode.encoder.QRCodeEncoder;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class QRCodeGenerator {

    public static void generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        System.out.println(text);
         // Hiển thị JFileChooser cho người dùng chọn vị trí và tên tệp
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify where to save the file");
        
        // Chỉ định rằng người dùng có thể chọn tệp
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        // Hiển thị hộp thoại và lấy phản hồi từ người dùng
        int userSelection = fileChooser.showSaveDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            // Nếu tệp không có đuôi mở rộng, thêm ".png" vào
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".png")) {
                filePath += ".png"; // Thêm đuôi ".png" nếu không có
            }
            
            // Tạo mã QR và lưu vào vị trí đã chọn
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());
        } else {
            System.out.println("Save operation was canceled by the user.");
        }
    }

    public static void main(String[] args) {
        try {
            generateQRCodeImage("0374583408", 350, 350);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}