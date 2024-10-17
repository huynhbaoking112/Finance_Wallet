package managerBank.test_fun;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
// import com.google.zxing.qrcode.encoder.QRCode;
// import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
// import com.google.zxing.qrcode.encoder.QRCodeEncoder;

import java.io.File;
import java.io.IOException;

public class QRCodeGenerator {

    public static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());
    }

    public static void main(String[] args) {
        try {
            generateQRCodeImage("king77nt54321@gmail.com", 350, 350, "demo\\src\\main\\java\\managerBank\\test_fun\\QRCode.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}