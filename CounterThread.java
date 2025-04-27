import javax.swing.*;
import java.awt.*;

public class CounterThread {
    private static JLabel counterLabel;
    private static int counter = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Multi-threaded Counter");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(255, 248, 220)); // Light background

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Multi-thread Counter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        counterLabel = new JLabel("Counter: 0", SwingConstants.CENTER);
        counterLabel.setFont(new Font("Arial", Font.BOLD, 32));
        counterLabel.setForeground(new Color(60, 179, 113));

        JButton startButton = new JButton("Start Counting");
        startButton.setBackground(new Color(100, 149, 237));
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Arial", Font.BOLD, 18));

        startButton.addActionListener(e -> startCounting());

        panel.add(titleLabel);
        panel.add(counterLabel);
        panel.add(startButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void startCounting() {
        Thread t1 = new Thread(() -> updateCounter("Thread-A"));
        Thread t2 = new Thread(() -> updateCounter("Thread-B"));

        t1.start();
        t2.start();
    }

    private static void updateCounter(String threadName) {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                counter++;
                counterLabel.setText("Counter: " + counter + " (" + threadName + ")");
                try {
                    Thread.sleep(500); // Slow down to visually see changes
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
