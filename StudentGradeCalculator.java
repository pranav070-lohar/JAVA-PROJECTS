import javax.swing.*;
import java.awt.*;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Grade Calculator");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setPreferredSize(new Dimension(350, 250));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Enter grades separated by commas:", SwingConstants.CENTER);
        JTextField gradeInput = new JTextField();
        JButton calcButton = new JButton("Calculate Average");
        JLabel resultLabel = new JLabel("Average: ", SwingConstants.CENTER);

        calcButton.setBackground(new Color(218, 112, 214));
        calcButton.setForeground(Color.white);
        calcButton.setFont(new Font("Arial", Font.BOLD, 16));

        calcButton.addActionListener(e -> {
            try {
                String[] grades = gradeInput.getText().split(",");
                double total = 0;
                for (String grade : grades) {
                    total += Double.parseDouble(grade.trim());
                }
                double avg = total / grades.length;
                resultLabel.setText(String.format("Average: %.2f", avg));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numbers separated by commas!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(label);
        panel.add(gradeInput);
        panel.add(calcButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
