package managerBank.pages.tranfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankTransferUI extends JFrame {

    // Custom JPanel to draw background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the image to fill the entire panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public BankTransferUI() {
        // Load background image
        ImageIcon icon = new ImageIcon("demo\\src\\main\\java\\managerBank\\assets\\icon\\bg_wallet4.jpg"); // Provide path to your background image
        Image backgroundImage = icon.getImage();

        // Create custom background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout());  // Use GridBagLayout on this panel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Set padding

        // Create components
        JLabel receiverAccountLabel = new JLabel("Receiver Account:");
        JTextField receiverAccountField = new JTextField(20);
        
        JLabel senderNameLabel = new JLabel("Sender Name:");
        JTextField senderNameField = new JTextField(20);
        
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(20);
        
        JLabel messageLabel = new JLabel("Message:");
        JTextField messageField = new JTextField(20);
        
        JButton transferButton = new JButton("Transfer");

        // Set button background color
        transferButton.setBackground(new Color(60, 180, 75)); // Bright green button
        transferButton.setForeground(Color.WHITE);

        // Create JPanel wrappers for each JLabel to set background color
        JPanel receiverAccountPanel = new JPanel();
        receiverAccountPanel.setBackground(new Color(173, 216, 230)); // Light blue
        receiverAccountPanel.add(receiverAccountLabel);

        JPanel senderNamePanel = new JPanel();
        senderNamePanel.setBackground(new Color(173, 216, 230)); // Light blue
        senderNamePanel.add(senderNameLabel);

        JPanel amountPanel = new JPanel();
        amountPanel.setBackground(new Color(173, 216, 230)); // Light blue
        amountPanel.add(amountLabel);

        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(new Color(173, 216, 230)); // Light blue
        messagePanel.add(messageLabel);

        JPanel buttonPanel = new JPanel();
        //buttonPanel.setBackground(new Color(173, 216, 230)); // Light blue
        buttonPanel.add(transferButton);
        
        // Add components to the background panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(receiverAccountPanel, gbc);  // Add panel with label to GridBagLayout
        
        gbc.gridx = 1;
        backgroundPanel.add(receiverAccountField, gbc);  // Add text field
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(senderNamePanel, gbc);  // Add panel with label
        
        gbc.gridx = 1;
        backgroundPanel.add(senderNameField, gbc);  // Add text field
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(amountPanel, gbc);  // Add panel with label
        
        gbc.gridx = 1;
        backgroundPanel.add(amountField, gbc);  // Add text field
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundPanel.add(messagePanel, gbc);  // Add panel with label
        
        gbc.gridx = 1;
        backgroundPanel.add(messageField, gbc);  // Add text field
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        backgroundPanel.add(buttonPanel, gbc);  // Add panel with button
        
        // Action for the transfer button
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receiverAccount = receiverAccountField.getText();
                String senderName = senderNameField.getText();
                String amount = amountField.getText();
                String message = messageField.getText();
                
                // Handle the transfer logic here
                JOptionPane.showMessageDialog(null, 
                    "Sender: " + senderName + 
                    "\nTransfer to: " + receiverAccount + 
                    "\nAmount: " + amount + 
                    "\nMessage: " + message,
                    "Transfer Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Set frame properties
        setContentPane(backgroundPanel); // Set the background panel as content pane
        setSize(400, 350); // Adjust frame size for extra row
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Create and display the form
        SwingUtilities.invokeLater(() -> new BankTransferUI().setVisible(true));
    }
}
