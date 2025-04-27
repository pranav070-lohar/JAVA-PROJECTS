import javax.swing.*;
import java.awt.*;

public class PasswordStrengthChecker {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Strength Checker");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(240, 255, 255)); // Light background

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setPreferredSize(new Dimension(400, 250));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Enter Password:", SwingConstants.CENTER);
        JTextField passwordInput = new JTextField();
        JButton checkBtn = new JButton("Check Strength");
        JLabel resultLabel = new JLabel("Strength: ", SwingConstants.CENTER);

        checkBtn.setBackground(new Color(123, 104, 238));
        checkBtn.setForeground(Color.white);
        checkBtn.setFont(new Font("Arial", Font.BOLD, 16));

        checkBtn.addActionListener(e -> {
            String password = passwordInput.getText();
            int strength = 0;

            if (password.length() >= 8) strength++;
            if (password.matches(".*[A-Z].*")) strength++;
            if (password.matches(".*[a-z].*")) strength++;
            if (password.matches(".*[0-9].*")) strength++;
            if (password.matches(".*[!@#$%^&*()_+=<>?].*")) strength++;

            String strengthLevel;
            switch (strength) {
                case 5 -> strengthLevel = "Very Strong 🔥";
                case 4 -> strengthLevel = "Strong 💪";
                case 3 -> strengthLevel = "Medium 🙂";
                case 2 -> strengthLevel = "Weak 😕";
                default -> strengthLevel = "Very Weak 😟";
            }
            resultLabel.setText("Strength: " + strengthLevel);
        });

        panel.add(label);
        panel.add(passwordInput);
        panel.add(checkBtn);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
