package managerBank.test_fun;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class QRCodeSwingApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("QR Code Scanner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        JButton openButton = new JButton("Open QR Code Image");
        JTextField resultField = new JTextField(30);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    QRCodeReaderExample.readQRCode(selectedFile.getAbsolutePath());
                    // Update resultField with the extracted text
                }
            }
        });

        frame.add(openButton);
        frame.add(resultField);

        frame.setVisible(true);
    }
}
