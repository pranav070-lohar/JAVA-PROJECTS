import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorApp {
    private static JTextField displayField;
    private static StringBuilder currentInput = new StringBuilder();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Desktop Calculator");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Light background

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setPreferredSize(new Dimension(400, 500));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBackground(Color.white);

        String[] buttons = {"7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"};

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.setBackground(new Color(176, 224, 230));
            button.setFocusPainted(false);
            button.addActionListener(e -> buttonPressed(button.getText()));
            buttonPanel.add(button);
        }

        panel.add(displayField, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void buttonPressed(String text) {
        switch (text) {
            case "C" -> {
                currentInput.setLength(0);
                displayField.setText("");
            }
            case "=" -> calculateResult();
            default -> {
                currentInput.append(text);
                displayField.setText(currentInput.toString());
            }
        }
    }

    private static void calculateResult() {
        try {
            String result = eval(currentInput.toString());
            displayField.setText(result);
            currentInput = new StringBuilder(result);
        } catch (Exception e) {
            displayField.setText("Error");
            currentInput.setLength(0);
        }
    }

    private static String eval(String expression) {
        // Very basic expression evaluation
        double result = 0;
        if (expression.contains("+")) {
            String[] parts = expression.split("\\+");
            result = Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
        } else if (expression.contains("-")) {
            String[] parts = expression.split("-");
            result = Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
        } else if (expression.contains("*")) {
            String[] parts = expression.split("\\*");
            result = Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
        } else if (expression.contains("/")) {
            String[] parts = expression.split("/");
            result = Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        }
        return String.valueOf(result);
    }
}
