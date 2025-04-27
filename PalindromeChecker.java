import javax.swing.*;
import java.awt.*;

public class PalindromeChecker {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Palindrome Checker");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(250, 240, 230));

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setPreferredSize(new Dimension(350, 250));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Enter text:", SwingConstants.CENTER);
        JTextField textInput = new JTextField();
        JButton checkButton = new JButton("Check Palindrome");
        JLabel resultLabel = new JLabel("Result: ", SwingConstants.CENTER);

        checkButton.setBackground(new Color(60, 179, 113));
        checkButton.setForeground(Color.white);
        checkButton.setFont(new Font("Arial", Font.BOLD, 16));

        checkButton.addActionListener(e -> {
            String input = textInput.getText().replaceAll("[^a-zA-Z]", "").toLowerCase();
            String reversed = new StringBuilder(input).reverse().toString();
            if (input.equals(reversed)) {
                resultLabel.setText("Result: It's a Palindrome!");
            } else {
                resultLabel.setText("Result: Not a Palindrome.");
            }
        });

        panel.add(label);
        panel.add(textInput);
        panel.add(checkButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
