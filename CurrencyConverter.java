import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Currency Converter");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(255, 250, 240));

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelAmount = new JLabel("Enter Amount:", SwingConstants.CENTER);
        JTextField amountField = new JTextField();

        JLabel labelFrom = new JLabel("From Currency (INR):", SwingConstants.CENTER);
        JComboBox<String> fromCurrency = new JComboBox<>(new String[]{"INR"});

        JLabel labelTo = new JLabel("To Currency:", SwingConstants.CENTER);
        JComboBox<String> toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "JPY", "GBP"});

        JButton convertButton = new JButton("Convert");
        JLabel resultLabel = new JLabel("Converted Amount: ", SwingConstants.CENTER);

        // Styling
        convertButton.setBackground(new Color(255, 127, 80));
        convertButton.setForeground(Color.white);
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));

        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 83.10);
        rates.put("EUR", 90.42);
        rates.put("JPY", 0.55);
        rates.put("GBP", 105.30);

        convertButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String to = (String) toCurrency.getSelectedItem();
                double converted = amount / rates.get(to);
                resultLabel.setText(String.format("Converted Amount: %.2f %s", converted, to));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(labelAmount);
        panel.add(amountField);
        panel.add(labelFrom);
        panel.add(fromCurrency);
        panel.add(labelTo);
        panel.add(toCurrency);
        panel.add(convertButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
