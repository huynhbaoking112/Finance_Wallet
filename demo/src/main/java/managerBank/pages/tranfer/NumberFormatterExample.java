package managerBank.pages.tranfer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberFormatterExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberFormatterExample::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Number Formatter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);

        JTextField numberField = new JTextField("100",20);
        
        // Thêm DocumentListener để theo dõi thay đổi trong JTextField
        numberField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                formatNumber();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                formatNumber();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                formatNumber();
            }

            private void formatNumber() {
                String text = numberField.getText();
                // Xóa mọi ký tự không phải là số
                String numericText = text.replaceAll("[^\\d]", "");
                
                if (!numericText.isEmpty()) {
                    // Định dạng số với dấu phẩy
                    try {
                        long number = Long.parseLong(numericText);
                        DecimalFormat formatter = new DecimalFormat("#,###");
                        numberField.setText(formatter.format(number));
                        // Đặt con trỏ ở cuối ô text field
                        numberField.setCaretPosition(numberField.getText().length());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    numberField.setText(""); // Nếu không có số, đặt ô trống
                }
            }
        });

        frame.getContentPane().add(numberField);
        frame.setVisible(true);
    }
}
