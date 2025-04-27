import javax.swing.*;
import java.awt.*;

public class TicTacToe {
    private static char currentPlayer = 'X';
    private static JButton[][] buttons = new JButton[3][3];

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(230, 230, 250)); // Light background

        JPanel panel = new JPanel(new GridLayout(3, 3, 10, 10));
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font font = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton("");
                button.setFont(font);
                button.setBackground(new Color(176, 224, 230));
                buttons[i][j] = button;
                button.addActionListener(e -> buttonClicked(button));
                panel.add(button);
            }
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void buttonClicked(JButton button) {
        if (!button.getText().equals("")) return;

        button.setText(String.valueOf(currentPlayer));
        if (checkWin()) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(null, "It's a Draw!");
            resetBoard();
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private static boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (check(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (check(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }
        return check(buttons[0][0], buttons[1][1], buttons[2][2]) || check(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private static boolean check(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().isEmpty() &&
                b1.getText().equals(b2.getText()) &&
                b2.getText().equals(b3.getText());
    }

    private static boolean isBoardFull() {
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                if (button.getText().isEmpty()) return false;
            }
        }
        return true;
    }

    private static void resetBoard() {
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText("");
            }
        }
        currentPlayer = 'X';
    }
}
