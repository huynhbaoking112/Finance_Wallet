package managerBank.utils;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextExtractor {

    // Phương thức để trích xuất chuỗi số từ văn bản
    public static List<String> extractNumbers(String text) {
        List<String> numbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String number = matcher.group();
            // Lọc các chuỗi số có độ dài từ 9 đến 12 ký tự (hoặc tùy theo yêu cầu)
            if (number.length() >= 9 && number.length() <= 12) {
                numbers.add(number); // Chỉ thêm chuỗi số phù hợp
            }
        }

        return numbers;
    }

    public static String extractTextFromImage(File imageFile) {
        Tesseract tesseract = new Tesseract();
        // Set path tới thư mục chứa tesseract.exe (nếu cần)
        tesseract.setDatapath(
                "C:\\Users\\huynh\\OneDrive\\Desktop\\basecode\\demo\\src\\main\\java\\managerBank\\utils");
        tesseract.setLanguage("vie"); // Thiết lập ngôn ngữ tiếng Việt

        try {
            return tesseract.doOCR(imageFile);
        } catch (TesseractException e) {
            e.printStackTrace();
            return "Lỗi trong quá trình OCR";
        }
    }
}
