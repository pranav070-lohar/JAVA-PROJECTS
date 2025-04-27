import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FileEncryptDecrypt {
    public static void main(String[] args) {
        JFrame frame = new JFrame("File Encrypt/Decrypt");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(255, 250, 240));

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField filePathField = new JTextField();
        JTextField keyField = new JTextField();
        JButton encryptBtn = new JButton("Encrypt File");
        JButton decryptBtn = new JButton("Decrypt File");

        encryptBtn.setBackground(new Color(255, 99, 71));
        decryptBtn.setBackground(new Color(30, 144, 255));
        encryptBtn.setForeground(Color.white);
        decryptBtn.setForeground(Color.white);
        encryptBtn.setFont(new Font("Arial", Font.BOLD, 16));
        decryptBtn.setFont(new Font("Arial", Font.BOLD, 16));

        encryptBtn.addActionListener(e -> processFile(filePathField.getText(), keyField.getText(), true));
        decryptBtn.addActionListener(e -> processFile(filePathField.getText(), keyField.getText(), false));

        panel.add(new JLabel("Enter File Path:", SwingConstants.CENTER));
        panel.add(filePathField);
        panel.add(new JLabel("Enter Key (1-255):", SwingConstants.CENTER));
        panel.add(keyField);
        panel.add(encryptBtn);
        panel.add(decryptBtn);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void processFile(String path, String keyStr, boolean encrypt) {
        try {
            int key = Integer.parseInt(keyStr);
            File inputFile = new File(path);
            if (!inputFile.exists()) throw new IOException("File not found!");

            File outputFile = new File((encrypt ? "encrypted_" : "decrypted_") + inputFile.getName());
            FileInputStream fis = new FileInputStream(inputFile);
            FileOutputStream fos = new FileOutputStream(outputFile);

            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data ^ key);
            }

            fis.close();
            fos.close();

            JOptionPane.showMessageDialog(null, (encrypt ? "Encrypted" : "Decrypted") + " successfully: " + outputFile.getName());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
