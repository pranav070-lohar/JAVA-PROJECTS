import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PasswordGenerator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Generator");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(255, 239, 213));

        JPanel panel = new JPanel(new GridLayout(8, 1, 10, 10));
        panel.setPreferredSize(new Dimension(350, 400));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField lengthInput = new JTextField();
        JCheckBox lower = new JCheckBox("Include Lowercase Letters");
        JCheckBox upper = new JCheckBox("Include Uppercase Letters");
        JCheckBox digits = new JCheckBox("Include Numbers");
        JCheckBox symbols = new JCheckBox("Include Symbols");
        JButton generateBtn = new JButton("Generate Password");
        JTextField resultField = new JTextField();
        resultField.setEditable(false);

        generateBtn.setBackground(new Color(255, 127, 80));
        generateBtn.setForeground(Color.white);
        generateBtn.setFont(new Font("Arial", Font.BOLD, 16));

        generateBtn.addActionListener(e -> {
            String lowers = "abcdefghijklmnopqrstuvwxyz";
            String uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String nums = "0123456789";
            String syms = "!@#$%^&*()_+=<>?";
            StringBuilder pool = new StringBuilder();

            if (lower.isSelected()) pool.append(lowers);
            if (upper.isSelected()) pool.append(uppers);
            if (digits.isSelected()) pool.append(nums);
            if (symbols.isSelected()) pool.append(syms);

            try {
                int length = Integer.parseInt(lengthInput.getText());
                if (pool.length() == 0 || length <= 0) {
                    JOptionPane.showMessageDialog(frame, "Select options and enter valid length!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Random rand = new Random();
                StringBuilder password = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    int idx = rand.nextInt(pool.length());
                    password.append(pool.charAt(idx));
                }
                resultField.setText(password.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for length!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(new JLabel("Password Length:", SwingConstants.CENTER));
        panel.add(lengthInput);
        panel.add(lower);
        panel.add(upper);
        panel.add(digits);
        panel.add(symbols);
        panel.add(generateBtn);
        panel.add(resultField);

        frame.add(panel);
        frame.setVisible(true);
    }
}
