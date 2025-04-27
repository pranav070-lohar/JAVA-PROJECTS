import javax.swing.*;
import java.awt.*;

public class TemperatureConverter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Temperature Converter");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Soft background

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setPreferredSize(new Dimension(350, 300));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label1 = new JLabel("Enter Temperature:", SwingConstants.CENTER);
        JTextField tempInput = new JTextField();

        String[] options = {"Celsius to Fahrenheit", "Fahrenheit to Celsius"};
        JComboBox<String> conversionType = new JComboBox<>(options);

        JButton convertBtn = new JButton("Convert");
        JLabel resultLabel = new JLabel("Result: ", SwingConstants.CENTER);

        convertBtn.setBackground(new Color(100, 149, 237));
        convertBtn.setForeground(Color.white);
        convertBtn.setFont(new Font("Arial", Font.BOLD, 16));

        convertBtn.addActionListener(e -> {
            try {
                double temp = Double.parseDouble(tempInput.getText());
                String type = (String) conversionType.getSelectedItem();
                double result;
                if (type.equals("Celsius to Fahrenheit")) {
                    result = (temp * 9/5) + 32;
                } else {
                    result = (temp - 32) * 5/9;
                }
                resultLabel.setText(String.format("Result: %.2f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(label1);
        panel.add(tempInput);
        panel.add(conversionType);
        panel.add(convertBtn);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
